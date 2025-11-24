package pe.com.registro2026.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.com.registro2026.RecordState;
import pe.com.registro2026.entity.MarcaEntity;
import pe.com.registro2026.repository.MarcaRepository;
import pe.com.registro2026.service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService {
	// inyeccion de repositorio
	@Autowired
	private MarcaRepository repositorio;

	@Override
	public List<MarcaEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Page<MarcaEntity> query(String search, int page, int size, RecordState recordState) {
		Boolean state = switch (recordState) {
			case ACTIVE -> true;
			case INACTIVE -> false;
			case ALL -> null;
		};

		Pageable pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(pageable, search, state);
	}

	@Override
	public MarcaEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public MarcaEntity add(MarcaEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public MarcaEntity update(Long id, MarcaEntity obj) {
		MarcaEntity marca = repositorio.findById(id).get();
		BeanUtils.copyProperties(obj, marca);
		return repositorio.save(marca);
	}

	@Override
	public MarcaEntity delete(Long id) {
		// repositorio.delete(id);
		MarcaEntity marca = repositorio.findById(id).get();
		marca.setEstado(false);
		return repositorio.save(marca);
	}

	@Override
	public MarcaEntity enable(Long id) {
		MarcaEntity marca = repositorio.findById(id).get();
		marca.setEstado(true);
		return repositorio.save(marca);
	}
}
