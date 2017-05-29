package cz.muni.fi.eventsshop.facade;

import cz.muni.fi.eventsshop.service.ServiceViewingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

/**
 * Created by ondrejvelisek on 10.5.17.
 */
@ApplicationScoped
public class ServiceViewingFacadeImpl implements ServiceViewingFacade {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	private ServiceViewingService serviceViewingService;

	@Override
	public void putViewingService(String sessionId, long serviceId) {
		serviceViewingService.putViewingService(sessionId, serviceId);
	}

	@Override
	public void removeViewingService(String sessionId) {
		serviceViewingService.removeViewingService(sessionId);
	}

	@Override
	public int getCountForService(long serviceId) {
		return serviceViewingService.getCountForService(serviceId);
	}

	@Override
	public Long getServiceIdBySession(String sessionId) {
		return serviceViewingService.getServiceIdBySession(sessionId);
	}

	@Override
	public Map<Long, Integer> getViewingCount() {
		return serviceViewingService.getViewingCount();
	}

}
