package pe.com.registro2026.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pe.com.registro2026.entity.RolEntity;

public interface RolService {
	List<RolEntity> findAll();
	Page<RolEntity> findAllCustom(String search, int page, int size);
	RolEntity findById(Long id);
	RolEntity add(RolEntity id);
	RolEntity update(Long id, RolEntity obj);
	RolEntity delete(Long id);
	RolEntity enable(Long id);
}
