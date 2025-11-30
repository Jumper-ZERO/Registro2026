package pe.com.registro2026.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.com.registro2026.entity.UsuarioEntity;
import pe.com.registro2026.repository.UsuarioRepository;
import pe.com.registro2026.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("Intentando cargar usuario: {}", username);

        UsuarioEntity usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado: {}", username);
                    return new UsernameNotFoundException("Usuario no encontrado");
                });

        log.info("Usuario encontrado. Rol: {}", usuario.getRole());

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole())
                .build();
    }
}
