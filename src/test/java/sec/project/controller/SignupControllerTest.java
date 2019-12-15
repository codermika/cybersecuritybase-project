package sec.project.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class SignupControllerTest {

	@Test
	public void testCrypto() {
		TextEncryptor encryptor = Encryptors.delux("kddskRdls!klslsk", "5c0744940b5c369b");
		String encrypted = encryptor.encrypt("Mika");
		System.out.println("Encrypted text:" + encrypted);
		assertNotEquals("Mika", encrypted);
		assertEquals("Mika", encryptor.decrypt(encrypted));
	}

}
