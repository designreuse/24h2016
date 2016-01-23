package com.bee.team.base;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseService {
	private static final Logger	logger	= LoggerFactory.getLogger(BaseService.class);

	public String getMessage(String key, String locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", new Locale(locale));
		String message;
		try {
			message = bundle.getString(key);
		} catch (java.util.MissingResourceException mre) {
			logger.warn("Missing key for '" + key + "'");
			return "";
		}
		return message;
	}

	public String getMessage(String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
		String message;
		try {
			message = bundle.getString(key);
		} catch (java.util.MissingResourceException mre) {
			logger.warn("Missing key for '" + key + "'");
			return "";
		}
		return message;
	}

	public String getMessage(String key) {
		String message;
		try {
			Locale loc = new Locale("fr");
			ResourceBundle bundle = ResourceBundle.getBundle("messages", loc);
			message = bundle.getString(key);
		} catch (java.util.MissingResourceException mre) {
			logger.warn("Missing key for '" + key + "'");
			return "";
		}
		return message;
	}

	public String getMessageWithParam(String key, String locale, Object... param) {
		String message;
		try {
			Locale loc = new Locale(locale);
			ResourceBundle bundle = ResourceBundle.getBundle("messages", loc);
			message = bundle.getString(key);
			message = MessageFormat.format(message, param);
		} catch (java.util.MissingResourceException mre) {
			logger.warn("Missing key for '" + key + "'");
			return "???" + key + "???";
		}
		return message;
	}

	public String getConfigWithParam(String key, String locale, Object... param) {
		String message;
		try {
			Locale loc = new Locale(locale);
			ResourceBundle bundle = ResourceBundle.getBundle("application", loc);
			message = bundle.getString(key);
			message = MessageFormat.format(message, param);
		} catch (java.util.MissingResourceException mre) {
			logger.warn("Missing key for '" + key + "'");
			return "???" + key + "???";
		}
		return message;
	}

	public String getConfig(String key) {
		String message;
		try {
			message = ResourceBundle.getBundle("application").getString(key);
		} catch (java.util.MissingResourceException mre) {
			logger.warn("Missing key for '" + key + "'");
			return "";
		}
		return message;
	}

	public String getFullPath() {
		return getConfig("appFullURL");
	}
}
