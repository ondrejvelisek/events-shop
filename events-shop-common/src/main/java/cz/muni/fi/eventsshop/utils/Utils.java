package cz.muni.fi.eventsshop.utils;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonValue;

/**
 *
 */
public class Utils {

	public static JsonValue convertThrowableToSimpleJson(Throwable e) {

		if (e == null) {
			return JsonValue.NULL;
		}

		return Json.createObjectBuilder()
				.add("type", e.getClass().getName())
				.add("message", e.getMessage())
				.add("cause", convertThrowableToSimpleJson(e.getCause()))
				.build();
	}

}
