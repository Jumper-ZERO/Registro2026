package pe.com.registro2026.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.com.registro2026.RecordState;
import pe.com.registro2026.entity.ProductoEntity;
import pe.com.registro2026.repository.ProductoRepository;
import pe.com.registro2026.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	// inyeccion de repositorio
	@Autowired
	private ProductoRepository repositorio;

	@Override
	public List<ProductoEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Page<ProductoEntity> query(String search, int page, int size, RecordState recordState) {
		Boolean state = switch (recordState) {
			case ACTIVE -> true;
			case INACTIVE -> false;
			case ALL -> null;
		};

		Pageable pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(pageable, search, state);
	}

	@Override
	public ProductoEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public ProductoEntity add(ProductoEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public ProductoEntity update(Long id, ProductoEntity obj) {
		ProductoEntity producto = repositorio.findById(id).get();
		BeanUtils.copyProperties(obj, producto);
		return repositorio.save(producto);
	}

	@Override
	public ProductoEntity delete(Long id) {
		// repositorio.delete(id);
		ProductoEntity producto = repositorio.findById(id).get();
		producto.setEstado(false);
		return repositorio.save(producto);
	}

	@Override
	public ProductoEntity enable(Long id) {
		ProductoEntity producto = repositorio.findById(id).get();
		producto.setEstado(true);
		return repositorio.save(producto);
	}
}
