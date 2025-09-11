//package com.devsu.customer_service.domain.helpers;
//
//import com.devsu.customer_service.application.configuration.CryptoSecretConfig;
//import com.devsu.customer_service.domain.exception.AESEncryptionException;
//import lombok.RequiredArgsConstructor;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//import java.util.Base64;
//
//@RequiredArgsConstructor
//public class AESEncryptor {
//
//	private final CryptoSecretConfig cryptoSecretConfig;
//
//	private static final String ALGORITHM = "AES";
//
//	public String encrypt(String data) {
//		try {
//			Cipher cipher = Cipher.getInstance(ALGORITHM);
//			SecretKeySpec keySpec = new SecretKeySpec(this.cryptoSecretConfig.getKey().getBytes(), ALGORITHM);
//			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
//			return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
//		} catch(Exception e) {
//			throw new AESEncryptionException("Error encrypting", e);
//		}
//	}
//
//	public String decrypt(String encryptedData) throws AESEncryptionException {
//		try {
//			Cipher cipher = Cipher.getInstance(ALGORITHM);
//			SecretKeySpec keySpec = new SecretKeySpec(this.cryptoSecretConfig.getKey().getBytes(), ALGORITHM);
//			cipher.init(Cipher.DECRYPT_MODE, keySpec);
//			byte[] decoded = Base64.getDecoder().decode(encryptedData);
//			return new String(cipher.doFinal(decoded));
//		} catch(Exception e) {
//			throw new AESEncryptionException("Error decrypting", e);
//		}
//	}
//
//}
