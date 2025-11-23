package pe.com.registro2026.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pe.com.registro2026.entity.ProductoEntity;

public interface ProductoService {
	List<ProductoEntity> findAll();
	Page<ProductoEntity> findAllCustom(String search, int page, int size);
	ProductoEntity findById(Long id);
	ProductoEntity add(ProductoEntity id);
	ProductoEntity update(Long id, ProductoEntity obj);
	ProductoEntity delete(Long id);
	ProductoEntity enable(Long id);
}
