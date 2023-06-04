package catamusico.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import catamusico.webapp.domain.Login;
import catamusico.webapp.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public Login login(String email, String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Login login = loginRepository.findByEmail(email).orElse(null);
		if (login != null && passwordEncoder.matches(password, login.getPassword())) {
			return login;
		}
		return null;
	}

	public Login save(Login login) {
		Login loginToSave = new Login();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(login.getPassword()); // encript password
		loginToSave.setPassword(hashedPassword);
		loginToSave.setEmail(login.getEmail());
		return loginRepository.save(loginToSave);
	}
}
