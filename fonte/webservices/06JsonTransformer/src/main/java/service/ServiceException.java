package service;

import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

import model.JsonFormatter;

public class ServiceException implements JsonFormatter {
	private String message;
	private Map<String, Object> properties;

	public String getMessage() {
		return message;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public ServiceException(String message) {
		this.message = message;
		this.properties = new TreeMap<String, Object>();
	}

	public ServiceException add(String property, Object value) {
		this.properties.put(property, value);
		return this;
	}

	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("message", this.getMessage());
		JSONArray props = new JSONArray();
		props.put(properties);
		obj.put("properties", props);
		return obj;
	}

}
