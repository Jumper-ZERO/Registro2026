package pe.com.registro2026.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.com.registro2026.RecordState;
import pe.com.registro2026.entity.EmpleadoEntity;
import pe.com.registro2026.repository.EmpleadoRepository;
import pe.com.registro2026.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	// inyeccion de repositorio
	@Autowired
	private EmpleadoRepository repositorio;
	
	@Override
	public List<EmpleadoEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Page<EmpleadoEntity> query(String search, int page, int size, RecordState recordState) {
		Boolean state = switch (recordState) {
			case ACTIVE -> true;
			case INACTIVE -> false;
			case ALL -> null;
		};

		Pageable pageable = PageRequest.of(page, size);
		return repositorio.findAllCustom(pageable, search, state);
	}

	@Override
	public EmpleadoEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public EmpleadoEntity add(EmpleadoEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public EmpleadoEntity update(Long id, EmpleadoEntity obj) {
		EmpleadoEntity empleado=repositorio.findById(id).get();
		BeanUtils.copyProperties(obj, empleado);
		return repositorio.save(empleado);
	}

	@Override
	public EmpleadoEntity delete(Long id) {
		EmpleadoEntity empleado=repositorio.findById(id).get();
		empleado.setEstado(false);
		return repositorio.save(empleado);
	}

	@Override
	public EmpleadoEntity enable(Long id) {
		EmpleadoEntity empleado=repositorio.findById(id).get();
		empleado.setEstado(true);
		return repositorio.save(empleado);
	}

}
