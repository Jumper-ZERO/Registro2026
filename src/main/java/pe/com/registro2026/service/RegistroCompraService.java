package pe.com.registro2026.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pe.com.registro2026.entity.RegistroCompraEntity;

public interface RegistroCompraService {
	List<RegistroCompraEntity> findAll();
	Page<RegistroCompraEntity> findAllCustom(String search, int page, int size);
	RegistroCompraEntity findById(Long id);
	RegistroCompraEntity add(RegistroCompraEntity id);
	RegistroCompraEntity update(Long id, RegistroCompraEntity obj);
	RegistroCompraEntity delete(Long id);
	RegistroCompraEntity enable(Long id);
}
