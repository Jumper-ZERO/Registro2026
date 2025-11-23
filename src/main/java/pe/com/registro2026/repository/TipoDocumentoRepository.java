package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.TipoDocumentoEntity;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Long>{
	//agregamos un Query personalizado
	@Query("""
			SELECT t
			FROM TipoDocumentoEntity t
			WHERE t.estado = true
			  AND (
			       :search IS NULL
			       OR :search = ''
			       OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<TipoDocumentoEntity> findAllCustom(@Param("search") String search, Pageable pageable);
}
