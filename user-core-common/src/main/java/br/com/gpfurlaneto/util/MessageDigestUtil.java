package br.com.gpfurlaneto.util;

import java.security.MessageDigest;

public class MessageDigestUtil {

//	private static final Logger log = LoggerFactory.getLogger(MessageDigestUtil.class);
	
	public static String encrypt(String password) throws Exception {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString().toLowerCase();
		} catch (Exception e) {
//			log.debug("Erro ao obter hash de senha!", e);
			throw new Exception("Erro inesperado!");
		}
	}
}
