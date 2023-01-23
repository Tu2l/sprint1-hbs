package com.hbs.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
	private static final Logger LOGGER;
	static {
		LOGGER = LogManager.getLogger(LoggerUtil.class);
	}

	LoggerUtil() {
	}

	public static void logInfo(Object object) {
		LOGGER.info(object);
	}
}
