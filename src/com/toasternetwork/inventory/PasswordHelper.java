package com.toasternetwork.inventory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A helping hand with Passwords
 */
public class PasswordHelper {
	/**
	 * Hashes a plaintext password
	 * @param plainText The plaintext password
	 * @return A Hashed password in SHA512
	 */
	public static String hashPassword(String plainText) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			byte[] message = digest.digest(plainText.getBytes());
			BigInteger bi = new BigInteger(1, message);
			StringBuilder hash = new StringBuilder(bi.toString(16));

			while(hash.length() < 32)
				hash.insert(0, "0");

			return hash.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
