package com.bee.team.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {

	/**
	 * Construit un JSON (String) a partir d'une map
	 * @param map
	 * @return
	 */
	public static String getJsonFromMap(Map<?, ?> map) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return "";
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getJsonFromMapString(Map<String, String> map) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return "";
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Construit une Map a partir d'un Json (String)
	 * @param json
	 * @return
	 */
	public static HashMap<String, Object> getMapFromJson(String json) {
		if (StringUtils.isNotEmpty(json)) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				HashMap<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
				return map;
			} catch (JsonParseException e) {
				e.printStackTrace();
				return null;
			} catch (JsonMappingException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * Construit une Map a partir d'un Json (String)
	 * @param json
	 * @return
	 */
	public static HashMap<String, String> getMapStringFromJson(String json) {
		if (StringUtils.isNotEmpty(json)) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				HashMap<String, String> map = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
				return map;
			} catch (JsonParseException e) {
				e.printStackTrace();
				return null;
			} catch (JsonMappingException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * Update un champ d'un json
	 * @param json
	 * @param key
	 * @param val
	 * @return
	 */
	public static String updateJson(String json, String key, String val) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootNode = mapper.readTree(json);
			((ObjectNode) rootNode).put(key, val);
			String tmp = mapper.writeValueAsString(rootNode);
			return tmp;
		} catch (JsonParseException e) {
			e.printStackTrace();
			return json;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			return json;
		}
	}

	public static String updateJson(String json, String key, Integer val) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootNode = mapper.readTree(json);
			((ObjectNode) rootNode).put(key, val);
			String tmp = mapper.writeValueAsString(rootNode);
			return tmp;
		} catch (JsonParseException e) {
			e.printStackTrace();
			return json;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * Affichage lisible avec formatage d'un Json
	 * @param json
	 */
	public static void prettyPrintJson(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootNode = mapper.readTree(json);
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
