package pe.com.registro2026.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pe.com.registro2026.entity.DistritoEntity;

public interface DistritoService {
	List<DistritoEntity> findAll();
	Page<DistritoEntity> findAllCustom(String search, int page, int size);
	DistritoEntity findById(Long id);
	DistritoEntity add(DistritoEntity id);
	DistritoEntity update(Long id, DistritoEntity obj);
	DistritoEntity delete(Long id);
	DistritoEntity enable(Long id);
}
