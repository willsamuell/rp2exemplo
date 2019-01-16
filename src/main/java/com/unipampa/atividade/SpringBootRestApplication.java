package com.unipampa.atividade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
@RestController
@RequestMapping(value = "/user")
public class SpringBootRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApplication.class, args);
	}

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	/*
	 * Método para inserir usuários no banco de dados
	 * 
	 * @param name, email
	 * 
	 * @return user
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody Usuario create_user(Usuario user) {
		return usuarioRepositorio.save(user);
	}

	/*
	 * Método para buscar determinado usuário pelo seu id
	 * 
	 * @param id
	 * 
	 * @return user
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody Usuario get_user(@RequestParam(value = "id") long id) {
		return usuarioRepositorio.findOne(id);
	}

	/*
	 * Método para buscar todos os usuários
	 * 
	 * @page Page you want to retrieve, 0 indexed and defaults to 0.
	 * 
	 * @size Size of the page you want to retrieve, defaults to 20.
	 * 
	 * @sort=firstname&sort=lastname,asc
	 */
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Page<Usuario> get_users(Usuario user, Pageable pageable) {
		return usuarioRepositorio.findAll(pageable);
		// return "users";
	}

	/*
	 * Método de filtro por nome
	 */
	public List<Usuario> findByNameLike(String name) {
		List<Usuario> ret = usuarioRepositorio.findByNameLike(name);

		if (null == ret) {
			System.out.println("Erro");
		} else {
			ret.size();
		}
		return (ret);
	}

	@RequestMapping(value = "/filtro/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Usuario>> SearchForUserByName(@PathVariable("name") String name) {
		return (new ResponseEntity<List<Usuario>>(usuarioRepositorio.findByNameLike(name), HttpStatus.OK));
	}

	/*
	 * Método para atualizar determinado cliente
	 * 
	 * @param id
	 * 
	 * @return user
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Usuario update_user(@RequestBody Usuario user, @PathVariable Long id) {
		usuarioRepositorio.findOne(id);
		user.toString();
		user.setName(user.getName());
		user.setEmail(user.getEmail());

		usuarioRepositorio.save(user);
		return user;

	}

	/*
	 * Método para deletar um usuário
	 * 
	 * @param id
	 * 
	 * @return user
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public @ResponseBody String delete_user(@RequestParam(value = "id") long id) {
		usuarioRepositorio.delete(id);
		return "Usuario Deletado.";
	}

	/*
	 * Método para lançar um erro
	 */
	@RequestMapping(value = "/error")
	public String error() {
		return "Ocorreu um erro.";
	}

}
