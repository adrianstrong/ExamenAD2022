package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String titulo;
    @Column
    private String autor;

    /*
    Completar con los métodos y atributos que sean necesarios
    */    
    
    public Libro() {
    }

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + '}';
    }
    
    

    
}
