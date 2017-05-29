package cz.muni.fi.eventsshop.service.batching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.inject.Singleton;

/**
 * Created by patrik.cyprian on 29.5.2017.
 */

@Singleton
public class MailBatchJobRunner {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Schedule(hour = "19", minute = "45", second = "00")
    public void myJob() {
        log.debug("Mailing started");
        BatchRuntime.getJobOperator().start("batchletJob", null);
    }
}
