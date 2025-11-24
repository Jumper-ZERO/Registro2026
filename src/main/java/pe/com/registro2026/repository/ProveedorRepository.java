package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.ProveedorEntity;

public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Long>{
	@Query("""
			SELECT p
			FROM ProveedorEntity p
			WHERE (:state IS NULL OR p.estado = :state)
			  AND (
			       :search IS NULL
			       OR :search = ''
			       OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(p.representante) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(p.ruc) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(p.direccion) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(p.telefono) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(p.celular) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(p.correo) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<ProveedorEntity> findAllCustom(
			Pageable pageable,
			@Param("search") String search,
			@Param("state") Boolean state);
}
