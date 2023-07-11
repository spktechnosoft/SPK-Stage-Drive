package com.stagedriving.modules.commons.utils;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.github.rollingmetrics.counter.ResetOnSnapshotCounter;
import com.github.rollingmetrics.histogram.HdrBuilder;
import com.google.inject.Singleton;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

@Data
@Slf4j
@Singleton
public class MetricsHelper {

    private ConcurrentHashMap<String, Object> data = new ConcurrentHashMap<>();
    private MetricRegistry metrics;

    public Counter counter(Class clazz, String... names) {
        return metrics.counter(MetricRegistry.name(clazz.getSimpleName(), names));
    }

    public Histogram histogram(Class clazz, String... names) {
        return metrics.histogram(MetricRegistry.name(clazz.getSimpleName(), names));
    }

    public ResetOnSnapshotCounter resetOnSnapshotCounter(Class clazz, String... names) {
        String name = MetricRegistry.name(clazz.getSimpleName(), names);
        name = name.replaceAll("\\s+","-");
        if (data.containsKey(name)) {
            return (ResetOnSnapshotCounter) data.get(name);
        }

        ResetOnSnapshotCounter counter = new ResetOnSnapshotCounter();
        metrics.register(name, (Gauge<Long>) counter::getSum);
        data.put(name, counter);
        return counter;
    }

    public Histogram hdrHistogram(Class clazz, String... names) {
        String name = MetricRegistry.name(clazz.getSimpleName(), names);
        if (data.containsKey(name)) {
            return (Histogram) data.get(name);
        }

        HdrBuilder builder = new HdrBuilder();
        builder.resetReservoirOnSnapshot();
        Histogram histogram = builder.buildAndRegisterHistogram(metrics, name);
        data.put(name, histogram);

        return histogram;
    }

}
