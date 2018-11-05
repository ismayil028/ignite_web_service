package com.test.message.database;

import java.util.HashMap;
import java.util.Map;

import com.test.message.model.Message;
import com.test.message.model.Profile;

public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<Long, Profile> profile = new HashMap<>();

	public static Map<Long, Message> getMessages() {
		return messages;
	}
	public static Map<Long, Profile> getProfile(){
		return profile;
	}
}
