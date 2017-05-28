package cz.muni.fi.eventsshop.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by ondrejvelisek on 10.5.17.
 */
public interface ServiceViewingFacade {

	void putViewingService(String sessionId, long serviceId);

	void removeViewingService(String sessionId);

	int getCountForService(long serviceId);

	Long getServiceIdBySession(String sessionId);

	Map<Long, Integer> getViewingCount();

}
