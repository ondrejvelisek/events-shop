package cz.muni.fi.eventsshop.service.batching;

import javax.batch.runtime.BatchRuntime;

/**
 * Created by pato on 29.5.2017.
 */
public class Job implements Runnable {
    public void run() {
        BatchRuntime.getJobOperator().start("batchletJob", null);
    }
}
