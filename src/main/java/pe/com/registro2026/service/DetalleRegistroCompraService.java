package pe.com.registro2026.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pe.com.registro2026.entity.DetalleRegistroCompraEntity;

public interface DetalleRegistroCompraService {
	List<DetalleRegistroCompraEntity> findAll();
	Page<DetalleRegistroCompraEntity> findAllCustom(String search, int page, int size);
	DetalleRegistroCompraEntity findById(Long id);
	DetalleRegistroCompraEntity add(DetalleRegistroCompraEntity id);
	DetalleRegistroCompraEntity update(Long id, DetalleRegistroCompraEntity obj);
	DetalleRegistroCompraEntity delete(Long id);
	DetalleRegistroCompraEntity enable(Long id);
}
