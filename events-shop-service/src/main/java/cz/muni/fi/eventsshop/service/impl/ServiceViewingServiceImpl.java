package cz.muni.fi.eventsshop.service.impl;

import cz.muni.fi.eventsshop.service.ServiceViewingService;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by ondrejvelisek on 10.5.17.
 */
@ApplicationScoped
public class ServiceViewingServiceImpl implements ServiceViewingService {

	ConcurrentMap<String, Long> viewingServices = new ConcurrentHashMap<>();

	@Override
	public void putViewingService(String sessionId, long serviceId) {
		viewingServices.put(sessionId, serviceId);
	}

	@Override
	public void removeViewingService(String sessionId) {
		viewingServices.remove(sessionId);
	}

	@Override
	public int getCountForService(long serviceId) {
		return Collections.frequency(viewingServices.values(), serviceId);
	}

	@Override
	public Long getServiceIdBySession(String sessionId) {
		return viewingServices.get(sessionId);
	}

	@Override
	public Map<Long, Integer> getViewingCount() {
		Map<Long, Integer> count = new HashMap<>();
		for (Long serviceId : viewingServices.values()) {
			count.put(serviceId, Collections.frequency(viewingServices.values(), serviceId));
		}
		return count;
	}

}
