package pe.com.registro2026.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {
    public UserDetails loadUserByUsername(String username);
}
