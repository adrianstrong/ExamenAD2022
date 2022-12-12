package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import models.Ejemplar;
import models.Libro;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author FranciscoRomeroGuill
 */
public class BibliotecaDAO {
    
    private static SessionFactory sessionFactory;
    
    static{
        try{
        sessionFactory = new Configuration().configure().buildSessionFactory();
            System.out.println("Conexión realizada");
        }catch(Exception ex){
            System.out.println("Error iniciando Hibernate");
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void saveLibro(Libro e) {
        try (var sesion = sessionFactory.getCurrentSession().getSessionFactory().openSession();) {
            sesion.beginTransaction();
            sesion.save(e);
            sesion.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error al guardar libro");
            System.out.println(ex);
        }

    }
  
    public HashSet<Libro> findByEstado(String estado){
        
        HashSet<Libro> salida = new HashSet<Libro>();

        try (var sesion = sessionFactory.getCurrentSession().getSessionFactory().openSession();) {
            List<Libro> libros;
            libros = sesion.createSQLQuery("SELECT * FROM libro WHERE id = (SELECT libro_id FROM ejemplar WHERE estado ='"+ estado+"')")
                    .addEntity(Libro.class)
                    .getResultList();

            for (Libro libro : libros) {
                salida.add(libro);
            }

        } catch (Exception ex) {
            System.out.println("Error al buscar libro");
            System.out.println(ex);
        }
        return salida;
        
    }
    
    public void printInfo(){
        
        /* 
          Muestra por consola todos los libros de la biblioteca y el número
          de ejemplares disponibles de cada uno.
          
          Debe imprimirlo de esta manera:
        
          Biblioteca
          ----------
          Como aprender java en 24h. (3)
          Como ser buena persona (1)
          Aprobando exámenes fácilmente (5)
          ...
        
        */
        try (var sesion = sessionFactory.getCurrentSession().getSessionFactory().openSession();) {
 /*           sesion.createSQLQuery("SELECT titulo, COUNT(*) FROM libro l INNER JOIN ejemplar ON l.id = ejemplar.libro_id GROUP BY titulo")
                    .addEntity(Libro.class)
                    .addEntity(Ejemplar.class)
                    .getResultList();
*/
             var lista = sesion.createQuery("FROM Libro l INNER JOIN Ejemplar e ON l.id = e.libro_id GROUP BY l.titulo").getResultList();
                for (Object libro : lista) {
                    System.out.println(libro);
                }
        } catch (Exception ex) {
            System.out.println("Error al imprimir info");
            System.out.println(ex);
        }

    }
    
}
