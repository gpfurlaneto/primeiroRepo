package br.com.gpfurlaneto.util;

import org.junit.Assert;
import org.junit.Test;

public class MessageDigestUtilTest {

	@Test
	public void encryptTest(){
		try {
			Assert.assertEquals("e8d95a51f3af4a3b134bf6bb680a213a", MessageDigestUtil.encrypt("senha"));
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
