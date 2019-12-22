package sec.project.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class SignupControllerTest {

	@Test
	public void testCrypto() {
		TextEncryptor encryptor = Encryptors.delux("kddskRdls!klslsk", "5c0744940b5c369b");
		String encrypted = encryptor.encrypt("123456-1234");
		System.out.println("Encrypted text:" + encrypted);
		assertNotEquals("123456-1234", encrypted);
		assertEquals("123456-1234", encryptor.decrypt(encrypted));
	}

}
