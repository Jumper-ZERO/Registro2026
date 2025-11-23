package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.RegistroCompraEntity;

public interface RegistroCompraRepository extends JpaRepository<RegistroCompraEntity, Long> {
	@Query("""
			SELECT d
			FROM RegistroCompraEntity d
			WHERE d.estado = true
			  AND (
				:search IS NULL
				OR :search = ''
				OR LOWER(CAST(d.codigo AS string)) LIKE LOWER(CONCAT('%', :search, '%'))
				OR LOWER(d.proveedor.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
				OR LOWER(d.empleado.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<RegistroCompraEntity> findAllCustom(@Param("search") String search, Pageable pageable);
}
