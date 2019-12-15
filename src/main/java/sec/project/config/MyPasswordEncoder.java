package sec.project.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence rawPassword) {
		System.out.println("MyPasswordEncoder.encode");
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		System.out.println("MyPasswordEncoder.matches");
		return rawPassword.toString().equals(encodedPassword);
	}

}
