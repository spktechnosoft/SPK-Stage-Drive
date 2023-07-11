package com.stagedriving.modules.commons.tasks;

import com.google.common.collect.ImmutableMultimap;
import com.google.inject.Inject;
import io.dropwizard.servlets.tasks.Task;
import org.hibernate.SessionFactory;

import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: simone
 * Date: 24/07/13
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 */
public class StgdrvClearDBCacheTask extends Task {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(StgdrvClearDBCacheTask.class);

    @Inject
    private SessionFactory sessionFactory;

    public StgdrvClearDBCacheTask(Runtime runtime) {
        super("clearDbCache");
    }

    public StgdrvClearDBCacheTask() {
        this(Runtime.getRuntime());
    }

    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception {

        sessionFactory.getCache().evictAllRegions();
        sessionFactory.getCache().evictEntityRegions();
        sessionFactory.getCache().evictCollectionRegions();
        sessionFactory.getCache().evictDefaultQueryRegion();
        sessionFactory.getCache().evictQueryRegions();

    }
}
