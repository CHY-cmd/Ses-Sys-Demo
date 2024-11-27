package com.itec.util;

import java.util.UUID;

public class IDUtil {
	public static String getID() {
		UUID randomUUID = UUID.randomUUID();
		String idString = randomUUID.toString().replace("-", "");
		return idString;
	}
}
