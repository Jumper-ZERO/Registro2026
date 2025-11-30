package pe.com.registro2026.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.registro2026.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
}
