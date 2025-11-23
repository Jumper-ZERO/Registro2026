package pe.com.registro2026.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pe.com.registro2026.entity.ProveedorEntity;

public interface ProveedorService {
	List<ProveedorEntity> findAll();
	Page<ProveedorEntity> findAllCustom(String search, int page, int size);
	ProveedorEntity findById(Long id);
	ProveedorEntity add(ProveedorEntity id);
	ProveedorEntity update(Long id, ProveedorEntity obj);
	ProveedorEntity delete(Long id);
	ProveedorEntity enable(Long id);
}
