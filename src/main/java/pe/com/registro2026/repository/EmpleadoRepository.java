package pe.com.registro2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.registro2026.entity.EmpleadoEntity;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long>{
	//agregamos un Query personalizado
	@Query("""
			SELECT e
			FROM EmpleadoEntity e
			WHERE (:state IS NULL OR e.estado = :state)
			  AND (
			       :search IS NULL
			       OR :search = ''
			       OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(e.apellidopaterno) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(e.apellidomaterno) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(e.numerodocumento) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(e.telefono) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(e.celular) LIKE LOWER(CONCAT('%', :search, '%'))
			       OR LOWER(e.correo) LIKE LOWER(CONCAT('%', :search, '%'))
			  )
			""")
	Page<EmpleadoEntity> findAllCustom(
			Pageable pageable,
			@Param("search") String search,
			@Param("state") Boolean state);
}
