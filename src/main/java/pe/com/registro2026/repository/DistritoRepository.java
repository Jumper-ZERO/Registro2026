package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.DistritoEntity;

public interface DistritoRepository extends JpaRepository<DistritoEntity, Long> {
	@Query("""
			SELECT d
			FROM DistritoEntity d
			WHERE d.estado = true
			  AND (
			       :search IS NULL
			       OR :search = ''
			       OR LOWER(d.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<DistritoEntity> findAllCustom(@Param("search") String search, Pageable pageable);
}
