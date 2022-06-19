package Auxiliar;

import Auxiliar.Constantes;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionEstatica {

    //********************* Atributos *************************
    private static java.sql.Connection Conex;
    //Atributo a través del cual hacemos la conexión física.
    private static java.sql.Statement Sentencia_SQL;
    //Atributo que nos permite ejecutar una sentencia SQL
    private static java.sql.ResultSet Conj_Registros;

    public static void abrirBD() {
        try {
            //Cargar el driver/controlador
            String controlador = "com.mysql.jdbc.Driver";
            Class.forName(controlador);

            String URL_BD = "jdbc:mysql://localhost/" + Constantes.BBDD;

            //Realizamos la conexión a una BD con un usuario y una clave.
            Conex = java.sql.DriverManager.getConnection(URL_BD, Constantes.usuario, Constantes.password);
            Sentencia_SQL = Conex.createStatement();
            System.out.println("Conexion realizada con éxito");
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public static void cerrarBD() {
        try {
            Conex.close();
            System.out.println("Desconectado de la Base de Datos");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de Desconexion", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Si devuelve true es que existe el Usuario.
     * @param usuario
     * @param clave
     * @return 
     */
    public static boolean existeUsuario(String usuario, String clave) {
        boolean existe = false;
        try {
            String sentencia = "SELECT * FROM usuarios WHERE Correo = '" + usuario + "' AND Clave = '" + clave + "'";
            ConexionEstatica.Conj_Registros = ConexionEstatica.Sentencia_SQL.executeQuery(sentencia);
            if (ConexionEstatica.Conj_Registros.next())
            {
                String cosa = Conj_Registros.getString("Correo");
                String cosa2 = Conj_Registros.getString("Nombre");
                int cosa3 = Conj_Registros.getInt("Edad");
                String cosa4 = Conj_Registros.getString("Clave");
                int cosa5 = Conj_Registros.getInt("Admin");
                existe= true;
            }
        } catch (SQLException ex) {
            System.out.println("Error en el acceso a la BD.");
        }
        return existe;
    }

}
