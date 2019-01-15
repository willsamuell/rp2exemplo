package com.unipampa.atividade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;


@SpringBootApplication
@RestController
@RequestMapping(value = "/user")
public class SpringBootRestApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootRestApplication.class, args);
  }

  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  @RequestMapping(value = "/create",method = RequestMethod.POST)
  public @ResponseBody Usuario create_user(Usuario user){
    return usuarioRepositorio.save(user);   
  }

  @RequestMapping(value ="/get", method=RequestMethod.GET)
  public @ResponseBody Usuario get_user(@RequestParam(value="id") long id){
    return usuarioRepositorio.findOne(id);
  }
  
  @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public Usuario update_user(@RequestBody Usuario user, @PathVariable Long id) {
	  usuarioRepositorio.findOne(id);	  
	  user.toString();
	  user.setName(user.getName());
	  user.setEmail(user.getEmail());
	  
	  usuarioRepositorio.save(user);

	  return user;
	  
  }

  @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
  public @ResponseBody String delete_user(@RequestParam(value="id") long id){
    usuarioRepositorio.delete(id);
    return "Usuario Deletado.";
  }

  @RequestMapping(value = "/error")
  public String error() {
    return "Ocorreu um erro.";
  }
}
