package pe.com.registro2026.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pe.com.registro2026.entity.MarcaEntity;

public interface MarcaService {
	List<MarcaEntity> findAll();
	Page<MarcaEntity> findAllCustom(String search, int page, int size);
	MarcaEntity findById(Long id);
	MarcaEntity add(MarcaEntity id);
	MarcaEntity update(Long id, MarcaEntity obj);
	MarcaEntity delete(Long id);
	MarcaEntity enable(Long id);
}
