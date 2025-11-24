package pe.com.registro2026.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.com.registro2026.RecordState;
import pe.com.registro2026.entity.DetalleRegistroCompraEntity;
import pe.com.registro2026.repository.DetalleRegistroCompraRepository;
import pe.com.registro2026.service.DetalleRegistroCompraService;

@Service
public class DetalleRegistroCompraServiceImpl implements DetalleRegistroCompraService{

	@Autowired
	private DetalleRegistroCompraRepository repositorio;
	
	@Override
	public List<DetalleRegistroCompraEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Page<DetalleRegistroCompraEntity> query(String search, int page, int size, RecordState recordState) {
		Boolean state = switch (recordState) {
			case ACTIVE -> true;
			case INACTIVE -> false;
			case ALL -> null;
		};

		Pageable pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(pageable, search, state);
	}

	@Override
	public DetalleRegistroCompraEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public DetalleRegistroCompraEntity add(DetalleRegistroCompraEntity obj) {
		return repositorio.save(obj);
	}
	
	@Override
	public DetalleRegistroCompraEntity update(Long id, DetalleRegistroCompraEntity obj) {
		DetalleRegistroCompraEntity detalleregiscompra=repositorio.findById(id).get();
		BeanUtils.copyProperties(obj, detalleregiscompra);
		return repositorio.save(detalleregiscompra);
	}

	@Override
	public DetalleRegistroCompraEntity delete(Long id) {
		DetalleRegistroCompraEntity detalleregiscompra=repositorio.findById(id).get();
		return repositorio.save(detalleregiscompra);
	}

	@Override
	public DetalleRegistroCompraEntity enable(Long id) {
		DetalleRegistroCompraEntity detalleregiscompra=repositorio.findById(id).get();
		return repositorio.save(detalleregiscompra);
	}
}
