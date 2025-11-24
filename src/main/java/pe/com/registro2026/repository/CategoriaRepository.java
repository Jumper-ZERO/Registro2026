package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
	@Query("""
			SELECT c
			FROM CategoriaEntity c
			WHERE (:state IS NULL OR c.estado = :state)
			  AND (
			       :search IS NULL
			       OR :search = ''
			       OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<CategoriaEntity> findAllCustom(
			Pageable pageable,
			@Param("search") String search,
			@Param("state") Boolean state);
}
