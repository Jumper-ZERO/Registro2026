package pe.com.registro2026.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.com.registro2026.RecordState;
import pe.com.registro2026.entity.DistritoEntity;
import pe.com.registro2026.repository.DistritoRepository;
import pe.com.registro2026.service.DistritoService;

@Service
public class DistritoServiceImpl implements DistritoService {
	// inyeccion de repositorio
	@Autowired
	private DistritoRepository repositorio;

	@Override
	public List<DistritoEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Page<DistritoEntity> query(String search, int page, int size, RecordState recordState) {
		Boolean state = switch (recordState) {
			case ACTIVE -> true;
			case INACTIVE -> false;
			case ALL -> null;
		};

		Pageable pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(pageable, search, state);
	}

	@Override
	public Page<DistritoEntity> getActives(String search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(pageable, search, true);
	}

	@Override
	public Page<DistritoEntity> getAll(String search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(pageable, search, null);
	}

	@Override
	public DistritoEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public DistritoEntity add(DistritoEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public DistritoEntity update(Long id, DistritoEntity obj) {
		DistritoEntity distrito = repositorio.findById(id).get();
		BeanUtils.copyProperties(obj, distrito);
		return repositorio.save(distrito);
	}

	@Override
	public DistritoEntity delete(Long id) {
		// repositorio.delete(id);
		DistritoEntity distrito = repositorio.findById(id).get();
		distrito.setEstado(false);
		return repositorio.save(distrito);
	}

	@Override
	public DistritoEntity enable(Long id) {
		DistritoEntity distrito = repositorio.findById(id).get();
		distrito.setEstado(true);
		return repositorio.save(distrito);
	}
}
