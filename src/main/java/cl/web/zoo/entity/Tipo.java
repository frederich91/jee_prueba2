package cl.web.zoo.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipo")
public class Tipo implements java.io.Serializable {


  private Integer id;
  private String nobre;
  private String desc;
  private Set<Animal> animals = new HashSet<Animal>(0);

  public Tipo() {}


  public Tipo(String nobre, String desc) {
    this.nobre = nobre;
    this.desc = desc;
  }

  public Tipo(String nobre, String desc, Set<Animal> animals) {
    this.nobre = nobre;
    this.desc = desc;
    this.animals = animals;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)


  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  @Column(name = "nobre", nullable = false, length = 50)
  public String getNobre() {
    return this.nobre;
  }

  public void setNobre(String nobre) {
    this.nobre = nobre;
  }


  @Column(name = "desc", nullable = false, length = 50)
  public String getDesc() {
    return this.desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipo")
  public Set<Animal> getAnimals() {
    return this.animals;
  }

  public void setAnimals(Set<Animal> animals) {
    this.animals = animals;
  }



}


