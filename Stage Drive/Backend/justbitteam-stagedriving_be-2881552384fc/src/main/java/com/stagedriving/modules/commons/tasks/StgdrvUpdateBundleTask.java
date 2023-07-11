package com.stagedriving.modules.commons.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableMultimap;
import com.google.inject.Inject;
import com.stagedriving.modules.bundle.controllers.BundleController;
import io.dropwizard.servlets.tasks.Task;

import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: simone
 * Date: 24/07/13
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 */
public class StgdrvUpdateBundleTask extends Task {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(StgdrvUpdateBundleTask.class);

    @Inject
    private BundleController bundleController;

    public StgdrvUpdateBundleTask(Runtime runtime) {
        super("updateBundle");
    }

    public StgdrvUpdateBundleTask() {
        this(Runtime.getRuntime());
    }

    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception {

        new Thread(new Runnable() {
            public void run(){
                try {
                    bundleController.updateBundleCache();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    LOGGER.error("Oops", e);
                }
            }
        }).start();

    }
}
