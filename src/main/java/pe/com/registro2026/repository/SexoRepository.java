package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.SexoEntity;

public interface SexoRepository extends JpaRepository<SexoEntity, Long> {
	@Query("""
			SELECT s
			FROM SexoEntity s
			WHERE s.estado = true
			  AND (
			       :search IS NULL
			       OR :search = ''
			       OR LOWER(s.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<SexoEntity> findAllCustom(@Param("search") String search, Pageable pageable);
}
