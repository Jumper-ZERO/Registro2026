package pe.com.registro2026.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.com.registro2026.RecordState;
import pe.com.registro2026.entity.CategoriaEntity;
import pe.com.registro2026.repository.CategoriaRepository;
import pe.com.registro2026.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	// inyeccion de repositorio
	@Autowired
	private CategoriaRepository repositorio;

	@Override
	public List<CategoriaEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Page<CategoriaEntity> query(String search, int page, int size, RecordState recordState) {
		Boolean state = switch (recordState) {
			case ACTIVE -> true;
			case INACTIVE -> false;
			case ALL -> null;
		};

		Pageable pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(pageable, search, state);
	}

	@Override
	public CategoriaEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public CategoriaEntity add(CategoriaEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public CategoriaEntity update(Long id, CategoriaEntity obj) {
		CategoriaEntity categoria = repositorio.findById(id).get();
		BeanUtils.copyProperties(obj, categoria);
		return repositorio.save(categoria);
	}

	@Override
	public CategoriaEntity delete(Long id) {
		// repositorio.delete(id);
		CategoriaEntity categoria = repositorio.findById(id).get();
		categoria.setEstado(false);
		return repositorio.save(categoria);
	}

	@Override
	public CategoriaEntity enable(Long id) {
		CategoriaEntity categoria = repositorio.findById(id).get();
		categoria.setEstado(true);
		return repositorio.save(categoria);
	}
}
