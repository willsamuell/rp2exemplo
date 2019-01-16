package com.unipampa.atividade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long>, PagingAndSortingRepository<Usuario, Long> {

	
	List<Usuario> findByNameLike(String name);

}