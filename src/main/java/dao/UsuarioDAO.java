package dao;

import java.security.spec.ECField;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UsuarioDAO {
    
    private Connection connection;
    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/examen", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* Completar consultas */
    static final String INSERT_QUERY = "INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `dni`) VALUES  (NULL, ?, ?, ?);";
    static final String LIST_QUERY="SELECT * FROM usuario";
    static final String GET_BY_DNI="SELECT * FROM usuario WHERE dni = ?";

    public void connect() {
        try {
            Statement stmt = connection.createStatement();
            System.out.println("Base de datos conectada");
        } catch (Exception e) {
            System.out.println("Base de datos no conectada");
            throw new RuntimeException(e);
        }
    }
    
    public void close(){
        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error al cerrar la base de datos");     
        }
    }

    /**
     * Completar código para guardar un usuario
     * Este método no debe generar el id del usuario, ya que la base
     * de datos es la que lo genera.
     * Una vez obtenido el id del usuario tras la consulta sql,
     * hay que modificarlo en el objeto que se pasa como parámetro
     * de forma que pase a tener el id correcto.
     */
    public void save(Usuario user){
        try (var pst = connection.prepareStatement(INSERT_QUERY, RETURN_GENERATED_KEYS)) {

            pst.setString(1, user.getNombre());
            pst.setString(2, user.getApellidos());
            pst.setString(3, user.getDni());

            if (pst.executeUpdate() > 0) {

                var keys = pst.getGeneratedKeys();
                keys.next();

                user.setId(keys.getLong(1));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }


    public ArrayList<Usuario> list(){
        var salida = new ArrayList<Usuario>(0);

        try (PreparedStatement pst = connection.prepareStatement(LIST_QUERY)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(rs.getLong("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setDni(rs.getString("dni"));

                salida.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return salida;
    }    
    
    public Usuario getByDNI(String dni){
        Usuario salida = new Usuario();
           try (var pst = connection.prepareStatement(GET_BY_DNI)) {
                pst.setString(1, dni);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    salida.setId(rs.getLong("id"));
                    salida.setNombre(rs.getString("nombre"));
                    salida.setApellidos(rs.getString("apellidos"));
                    salida.setDni(rs.getString("dni"));
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        return salida;
    }    
}
