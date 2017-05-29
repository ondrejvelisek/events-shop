package cz.muni.fi.eventsshop.service;

import java.util.Map;

/**
 * Created by ondrejvelisek on 10.5.17.
 */
public interface ServiceViewingService {

	void putViewingService(String sessionId, long serviceId);

	void removeViewingService(String sessionId);

	int getCountForService(long serviceId);

	Long getServiceIdBySession(String sessionId);

	Map<Long, Integer> getViewingCount();
}
