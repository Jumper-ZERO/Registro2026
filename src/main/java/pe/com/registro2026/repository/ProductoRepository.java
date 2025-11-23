package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{
	@Query("""
			SELECT p
			FROM ProductoEntity p
			WHERE p.estado = true
			  AND (
			       :search IS NULL
			       OR :search = ''
			       OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<ProductoEntity> findAllCustom(@Param("search") String search, Pageable pageable);
}
