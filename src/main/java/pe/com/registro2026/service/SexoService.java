package pe.com.registro2026.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pe.com.registro2026.entity.SexoEntity;


public interface SexoService {
	List<SexoEntity> findAll();
	Page<SexoEntity> findAllCustom(String search, int page, int size);
	SexoEntity findById(Long id);
	SexoEntity add(SexoEntity id);
	SexoEntity update(Long id, SexoEntity obj);
	SexoEntity delete(Long id);
	SexoEntity enable(Long id);
}
