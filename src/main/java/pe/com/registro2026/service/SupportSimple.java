package pe.com.registro2026.service;

import java.util.List;

import org.springframework.data.domain.Page;

import pe.com.registro2026.RecordState;

public interface SupportSimple<T> {
	List<T> findAll();
	Page<T> query(String search, int page, int size, RecordState state);
	Page<T> getActives(String search, int page, int size);
	Page<T> getAll(String search, int page, int size);
	T findById(Long id);
	T add(T id);
	T update(Long id, T obj);
	T delete(Long id);
	T enable(Long id);
}
