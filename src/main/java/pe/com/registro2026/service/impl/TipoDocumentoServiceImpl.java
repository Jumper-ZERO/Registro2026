package pe.com.registro2026.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import pe.com.registro2026.entity.TipoDocumentoEntity;
import pe.com.registro2026.repository.TipoDocumentoRepository;
import pe.com.registro2026.service.TipoDocumentoService;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

	// inyeccion de repositorio
	@Autowired
	private TipoDocumentoRepository repositorio;
	
	@Override
	public List<TipoDocumentoEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Page<TipoDocumentoEntity> findAllCustom(String search, int page, int size) {
		PageRequest pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(search, pageable);
	}

	@Override
	public TipoDocumentoEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public TipoDocumentoEntity add(TipoDocumentoEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public TipoDocumentoEntity update(Long id, TipoDocumentoEntity obj) {
		TipoDocumentoEntity tipodocumento=repositorio.findById(id).get();
		BeanUtils.copyProperties(obj, tipodocumento);
		return repositorio.save(tipodocumento);
	}

	@Override
	public TipoDocumentoEntity delete(Long id) {
		TipoDocumentoEntity tipodocumento=repositorio.findById(id).get();
		tipodocumento.setEstado(false);
		return repositorio.save(tipodocumento);
	}

	@Override
	public TipoDocumentoEntity enable(Long id) {
		TipoDocumentoEntity tipodocumento=repositorio.findById(id).get();
		tipodocumento.setEstado(true);
		return repositorio.save(tipodocumento);
	}
}
