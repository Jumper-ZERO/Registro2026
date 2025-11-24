package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.TipoDocumentoEntity;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Long>{
	@Query("""
			SELECT t
			FROM TipoDocumentoEntity t
			WHERE (:state IS NULL OR t.estado = :state)
			  AND (
			       :search IS NULL
			       OR :search = ''
			       OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<TipoDocumentoEntity> findAllCustom(
			Pageable pageable,
			@Param("search") String search,
			@Param("state") Boolean state);
}
