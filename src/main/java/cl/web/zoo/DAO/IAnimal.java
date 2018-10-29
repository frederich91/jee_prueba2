package cl.web.zoo.DAO;

import org.springframework.data.repository.CrudRepository;
import cl.web.zoo.entity.Animal;

public interface IAnimal extends CrudRepository<Animal, Integer>{

}
