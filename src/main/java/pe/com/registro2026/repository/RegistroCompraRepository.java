package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.RegistroCompraEntity;

public interface RegistroCompraRepository extends JpaRepository<RegistroCompraEntity, Long> {
	@Query("""
			SELECT r
			FROM RegistroCompraEntity r
			WHERE (:state IS NULL OR r.estado = :state)
			  AND (
				:search IS NULL
				OR :search = ''
				OR LOWER(CAST(r.codigo AS string)) LIKE LOWER(CONCAT('%', :search, '%'))
				OR LOWER(r.proveedor.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
				OR LOWER(r.empleado.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<RegistroCompraEntity> findAllCustom(
			Pageable pageable,
			@Param("search") String search,
			@Param("state") Boolean state);
}
