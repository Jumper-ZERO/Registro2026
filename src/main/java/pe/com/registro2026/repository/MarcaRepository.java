package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.MarcaEntity;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Long>{
	@Query("""
			SELECT m
			FROM MarcaEntity m
			WHERE m.estado = true
			  AND (
			       :search IS NULL
			       OR :search = ''
			       OR LOWER(m.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<MarcaEntity> findAllCustom(@Param("search") String search, Pageable pageable);
}
