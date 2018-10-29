package cl.web.zoo.DAO;

import org.springframework.data.repository.CrudRepository;
import cl.web.zoo.entity.Usuario;


public interface IUsuario extends CrudRepository<Usuario, Integer>{

}
