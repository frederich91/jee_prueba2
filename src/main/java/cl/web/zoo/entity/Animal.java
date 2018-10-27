package cl.web.zoo.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "animal")
public class Animal implements java.io.Serializable {


  private int codigo;
  private Sector sector;
  private Tipo tipo;
  private String nombre;
  private float peso;
  private String genero;
  private Date fchNac;
  private Date fchIngreso;
  private Date fchMuerte;

  public Animal() {}


  public Animal(int codigo, Sector sector, Tipo tipo, String nombre, float peso, String genero,
      Date fchIngreso) {
    this.codigo = codigo;
    this.sector = sector;
    this.tipo = tipo;
    this.nombre = nombre;
    this.peso = peso;
    this.genero = genero;
    this.fchIngreso = fchIngreso;
  }

  public Animal(int codigo, Sector sector, Tipo tipo, String nombre, float peso, String genero,
      Date fchNac, Date fchIngreso, Date fchMuerte) {
    this.codigo = codigo;
    this.sector = sector;
    this.tipo = tipo;
    this.nombre = nombre;
    this.peso = peso;
    this.genero = genero;
    this.fchNac = fchNac;
    this.fchIngreso = fchIngreso;
    this.fchMuerte = fchMuerte;
  }

  @Id


  @Column(name = "codigo", unique = true, nullable = false)
  public int getCodigo() {
    return this.codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sector_id", nullable = false)
  public Sector getSector() {
    return this.sector;
  }

  public void setSector(Sector sector) {
    this.sector = sector;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tipo_id", nullable = false)
  public Tipo getTipo() {
    return this.tipo;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }


  @Column(name = "nombre", nullable = false, length = 25)
  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


  @Column(name = "peso", nullable = false, precision = 6)
  public float getPeso() {
    return this.peso;
  }

  public void setPeso(float peso) {
    this.peso = peso;
  }


  @Column(name = "genero", nullable = false, length = 8)
  public String getGenero() {
    return this.genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "fch_nac", length = 10)
  public Date getFchNac() {
    return this.fchNac;
  }

  public void setFchNac(Date fchNac) {
    this.fchNac = fchNac;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "fch_ingreso", nullable = false, length = 10)
  public Date getFchIngreso() {
    return this.fchIngreso;
  }

  public void setFchIngreso(Date fchIngreso) {
    this.fchIngreso = fchIngreso;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "fch_muerte", length = 10)
  public Date getFchMuerte() {
    return this.fchMuerte;
  }

  public void setFchMuerte(Date fchMuerte) {
    this.fchMuerte = fchMuerte;
  }

}


