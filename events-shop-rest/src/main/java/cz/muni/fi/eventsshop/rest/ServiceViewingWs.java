package cz.muni.fi.eventsshop.rest;

import cz.muni.fi.eventsshop.facade.ServiceViewingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.InternalServerErrorException;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Set;

/**
 * Created by ondrejvelisek on 10.5.17.
 */
@ServerEndpoint("/ws")
public class ServiceViewingWs {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public static final String JOIN_ACTION = "ws/VIEWING_SERVICE_JOIN";
	public static final String LEAVE_ACTION = "ws/VIEWING_SERVICE_LEAVE";
	public static final String COUNT_ACTION = "VIEWING_SERVICE_COUNT";

	@Inject
	private ServiceViewingFacade serviceViewingFacade;

	@OnOpen
	public void open(Session session, EndpointConfig conf) {
		log.trace("new Websocket was opened. Session id: "+session.getId());

	}

	@OnMessage
	public void message(Reader messageReader, Session session) {
		log.trace("new websocket message was received, Session id: "+session.getId());

		JsonReader reader = Json.createReader(messageReader);
		JsonObject message = reader.readObject();
		String actionType = message.getString("type");
		Long serviceId = message.getJsonNumber("serviceId").longValue();

		switch (actionType) {
			case JOIN_ACTION :
				serviceViewingFacade.putViewingService(session.getId(), serviceId);
				break;
			case LEAVE_ACTION :
				serviceViewingFacade.removeViewingService(session.getId());
				break;
		}

		sendServiceViewing(session.getOpenSessions());
	}


	@OnError
	public void error(Session session, Throwable error) {
		log.trace("Websocket error, Session id: "+session.getId());
		throw new InternalServerErrorException(error);
	}


	@OnClose
	public void close(Session session, CloseReason reason) {
		log.trace("Websocket closed, Session id: "+session.getId()+", reason: "+reason.toString());

		Long lastServiceId = serviceViewingFacade.getServiceIdBySession(session.getId());

		if (lastServiceId != null) {

			serviceViewingFacade.removeViewingService(session.getId());

			sendServiceViewing(session.getOpenSessions());

		}

	}

	private void sendServiceViewing(Set<Session> sessions) {

		JsonObjectBuilder countBuilder = Json.createObjectBuilder();

		for (Map.Entry<Long, Integer> count : serviceViewingFacade.getViewingCount().entrySet()) {
			countBuilder.add(count.getKey().toString(), count.getValue());
		}

		JsonObject json = Json.createObjectBuilder()
				.add("type", COUNT_ACTION)
				.add("count", countBuilder)
				.build();

		try {
			for (Session session : sessions) {
				session.getBasicRemote().sendText(json.toString());
			}
		} catch (IOException e) {
			throw new InternalServerErrorException(e);
		}
	}

}
