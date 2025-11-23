package pe.com.registro2026.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pe.com.registro2026.entity.EmpleadoEntity;

public interface EmpleadoService {
	List<EmpleadoEntity> findAll();
	Page<EmpleadoEntity> findAllCustom(String search, int page, int size);
	EmpleadoEntity findById(Long id);
	EmpleadoEntity add(EmpleadoEntity id);
	EmpleadoEntity update(Long id, EmpleadoEntity obj);
	EmpleadoEntity delete(Long id);
	EmpleadoEntity enable(Long id);
}
