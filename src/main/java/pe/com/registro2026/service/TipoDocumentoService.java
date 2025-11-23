package pe.com.registro2026.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pe.com.registro2026.entity.TipoDocumentoEntity;


public interface TipoDocumentoService {
	List<TipoDocumentoEntity> findAll();
	Page<TipoDocumentoEntity> findAllCustom(String search, int page, int size);
	TipoDocumentoEntity findById(Long id);
	TipoDocumentoEntity add(TipoDocumentoEntity id);
	TipoDocumentoEntity update(Long id, TipoDocumentoEntity obj);
	TipoDocumentoEntity delete(Long id);
	TipoDocumentoEntity enable(Long id);
}
