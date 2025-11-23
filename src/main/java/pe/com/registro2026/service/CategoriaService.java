package pe.com.registro2026.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pe.com.registro2026.entity.CategoriaEntity;


public interface CategoriaService {
	List<CategoriaEntity> findAll();
	Page<CategoriaEntity> findAllCustom(String search, int page, int size);
	CategoriaEntity findById(Long id);
	CategoriaEntity add(CategoriaEntity id);
	CategoriaEntity update(Long id, CategoriaEntity obj);
	CategoriaEntity delete(Long id);
	CategoriaEntity enable(Long id);
}
