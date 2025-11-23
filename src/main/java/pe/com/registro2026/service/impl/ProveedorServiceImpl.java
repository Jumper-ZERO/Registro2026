package pe.com.registro2026.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.com.registro2026.entity.ProveedorEntity;
import pe.com.registro2026.repository.ProveedorRepository;
import pe.com.registro2026.service.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService{

	@Autowired
	private ProveedorRepository repositorio;
	
	@Override
	public List<ProveedorEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Page<ProveedorEntity> findAllCustom(String search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(search, pageable);
	}

	@Override
	public ProveedorEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public ProveedorEntity add(ProveedorEntity obj) {
		return repositorio.save(obj);
	}
	
	@Override
	public ProveedorEntity update(Long id, ProveedorEntity obj) {
		ProveedorEntity proveedor=repositorio.findById(id).get();
		BeanUtils.copyProperties(obj, proveedor);
		return repositorio.save(proveedor);
	}

	@Override
	public ProveedorEntity delete(Long id) {
		//repositorio.delete(id);
		ProveedorEntity proveedor=repositorio.findById(id).get();
		proveedor.setEstado(false);
		return repositorio.save(proveedor);
	}

	@Override
	public ProveedorEntity enable(Long id) {
		ProveedorEntity proveedor=repositorio.findById(id).get();
		proveedor.setEstado(true);
		return repositorio.save(proveedor);
	}
}
