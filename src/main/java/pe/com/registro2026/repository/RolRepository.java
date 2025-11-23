package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Long>{
	//agregamos un Query personalizado
	@Query("""
			SELECT r
			FROM RolEntity r
			WHERE r.estado = true
			  AND (
			       :search IS NULL
			       OR :search = ''
			       OR LOWER(r.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<RolEntity> findAllCustom(@Param("search") String search, Pageable pageable);
}
