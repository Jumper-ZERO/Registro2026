package pe.com.registro2026.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.com.registro2026.RecordState;
import pe.com.registro2026.entity.SexoEntity;
import pe.com.registro2026.repository.SexoRepository;
import pe.com.registro2026.service.SexoService;

@Service
public class SexoServiceImpl implements SexoService {

	// inyeccion de repositorio
	@Autowired
	private SexoRepository repositorio;
	
	@Override
	public List<SexoEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Page<SexoEntity> query(String search, int page, int size, RecordState recordState) {
		Boolean state = switch (recordState) {
			case ACTIVE -> true;
			case INACTIVE -> false;
			case ALL -> null;
		};

		Pageable pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(pageable, search, state);
	}

	@Override
	public SexoEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public SexoEntity add(SexoEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public SexoEntity update(Long id, SexoEntity obj) {
		SexoEntity sexo=repositorio.findById(id).get();
		BeanUtils.copyProperties(obj, sexo);
		return repositorio.save(sexo);
	}

	@Override
	public SexoEntity delete(Long id) {
		SexoEntity sexo=repositorio.findById(id).get();
		sexo.setEstado(false);
		return repositorio.save(sexo);
	}

	@Override
	public SexoEntity enable(Long id) {
		SexoEntity sexo=repositorio.findById(id).get();
		sexo.setEstado(true);
		return repositorio.save(sexo);
	}
}
