package catamusico.webapp.service;

import catamusico.webapp.domain.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import catamusico.webapp.domain.Login;
import catamusico.webapp.repository.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Login user = loginRepository.findByEmail(email).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new CustomUserDetails(user);
    }
}
