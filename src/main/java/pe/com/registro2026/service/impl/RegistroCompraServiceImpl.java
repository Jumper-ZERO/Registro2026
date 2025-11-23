package pe.com.registro2026.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.com.registro2026.entity.RegistroCompraEntity;
import pe.com.registro2026.repository.RegistroCompraRepository;
import pe.com.registro2026.service.RegistroCompraService;

@Service
public class RegistroCompraServiceImpl implements RegistroCompraService{

	// inyeccion de repositorio
	@Autowired
	private RegistroCompraRepository repositorio;
	
	@Override
	public List<RegistroCompraEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Page<RegistroCompraEntity> findAllCustom(String search, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(search, pageable);
	}

	@Override
	public RegistroCompraEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public RegistroCompraEntity add(RegistroCompraEntity obj) {
		return repositorio.save(obj);
	}
	
	@Override
	public RegistroCompraEntity update(Long id, RegistroCompraEntity obj) {
		RegistroCompraEntity registrocompra=repositorio.findById(id).get();
		BeanUtils.copyProperties(obj, registrocompra);
		return repositorio.save(registrocompra);
	}

	@Override
	public RegistroCompraEntity delete(Long id) {
		//repositorio.delete(id);
		RegistroCompraEntity registrocompra=repositorio.findById(id).get();
		registrocompra.setEstado(false);
		return repositorio.save(registrocompra);
	}

	@Override
	public RegistroCompraEntity enable(Long id) {
		RegistroCompraEntity registrocompra=repositorio.findById(id).get();
		registrocompra.setEstado(true);
		return repositorio.save(registrocompra);
	}

}
