package com.bee.team.util;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import com.springcryptoutils.core.cipher.Mode;
import com.springcryptoutils.core.cipher.symmetric.CiphererWithStaticKeyImpl;

public class CryptoUtil implements Serializable {
	private static CiphererWithStaticKeyImpl	cipher;

	private static CiphererWithStaticKeyImpl getInstance() {
		if (cipher == null) {
			cipher = new CiphererWithStaticKeyImpl();
			cipher.setKey("Rs3xEA16I52XJpsWwkw4GrB8l6FiVGK/");
			cipher.setInitializationVector("AQIDBAUGAQI=");
			cipher.afterPropertiesSet();
		}
		return cipher;
	}

	public static byte[] encrypt(String message) {
		getInstance().setMode(Mode.ENCRYPT);
		return getInstance().encrypt(message.getBytes(StandardCharsets.UTF_8));
	}

	public static String decrypt(byte[] message) {
		getInstance().setMode(Mode.DECRYPT);
		return new String(getInstance().encrypt(message), StandardCharsets.UTF_8);
	}
}
