package catamusico.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import catamusico.webapp.domain.Login;
import catamusico.webapp.domain.Musician;
import catamusico.webapp.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private MusicianService musicianService;

	public Login save(Login login) {
		// if (!loginRepository.findByEmail(login.getEmail()).isPresent()) {
			Login loginToSave = new Login();
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(login.getPassword()); // encript password
			loginToSave.setPassword(hashedPassword);
			loginToSave.setEmail(login.getEmail());
			return loginRepository.save(loginToSave);
			// }
			// return null;
	}

	public boolean isMusician(String email) {
		Login login = loginRepository.findByEmail(email).orElse(null);
		if (login != null) {
			Musician music = musicianService.findByLogin(login.getId());
			return music != null;
		}
		return false;
	}

	public Login findByEmail(String email){
		return loginRepository.findByEmail(email).orElse(null);
	}
}
