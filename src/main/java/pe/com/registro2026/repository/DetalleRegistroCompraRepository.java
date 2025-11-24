package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.DetalleRegistroCompraEntity;

public interface DetalleRegistroCompraRepository extends JpaRepository<DetalleRegistroCompraEntity, Long>{
	@Query("""
			SELECT d
			FROM DetalleRegistroCompraEntity d
			WHERE (
			       :search IS NULL
			       OR :search = ''
			       OR LOWER(d.producto.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<DetalleRegistroCompraEntity> findAllCustom(
			Pageable pageable,
			@Param("search") String search,
			@Param("state") Boolean state);
}
