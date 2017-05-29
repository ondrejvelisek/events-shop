package cz.muni.fi.eventsshop.service.batching;

import javax.enterprise.concurrent.LastExecution;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.Trigger;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by pato on 29.5.2017.
 */
@Stateless
public class MailStatelessEJB {
    @Resource
    ManagedScheduledExecutorService executor;

    public void runJob() {
        executor.schedule(new Job(), new Trigger() {

            public Date getNextRunTime(LastExecution lastExecutionInfo, Date taskScheduledTime) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(taskScheduledTime);
                cal.add(Calendar.DATE, 1);
                return cal.getTime();
            }

            public boolean skipRun(LastExecution lastExecutionInfo, Date scheduledRunTime) {
                return null == lastExecutionInfo;
            }

        });
    }

    public void cancelJob() {
        executor.shutdown();
    }
}
