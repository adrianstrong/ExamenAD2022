package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Ejemplar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String estado; /* excelente, bueno, regular, malo */
    @Column
    private Integer edicion;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro_id;

    public Ejemplar() {
    }

    public Ejemplar(String estado, Integer edicion) {
        this.estado = estado;
        this.edicion = edicion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getEdicion() {
        return edicion;
    }

    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }

    @Override
    public String toString() {
        return "Ejemplar{" + "id=" + id + ", estado=" + estado + ", edicion=" + edicion + '}';
    }
    
    
}
