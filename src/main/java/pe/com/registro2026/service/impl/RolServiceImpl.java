package pe.com.registro2026.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.com.registro2026.entity.RolEntity;
import pe.com.registro2026.repository.RolRepository;
import pe.com.registro2026.service.RolService;

@Service
public class RolServiceImpl implements RolService {

	// inyeccion de repositorio
	@Autowired
	private RolRepository repositorio;
	
	@Override
	public List<RolEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Page<RolEntity> findAllCustom(String search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(search, pageable);
	}

	@Override
	public RolEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public RolEntity add(RolEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public RolEntity update(Long id, RolEntity obj) {
		RolEntity rol=repositorio.findById(id).get();
		BeanUtils.copyProperties(obj, rol);
		return repositorio.save(rol);
	}

	@Override
	public RolEntity delete(Long id) {
		RolEntity rol=repositorio.findById(id).get();
		rol.setEstado(false);
		return repositorio.save(rol);
	}

	@Override
	public RolEntity enable(Long id) {
		RolEntity rol=repositorio.findById(id).get();
		rol.setEstado(true);
		return repositorio.save(rol);
	}
}
