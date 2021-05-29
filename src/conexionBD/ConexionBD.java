/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionBD;

import java.sql.*;


/**
 *
 * @author 57301
 */
public class ConexionBD {
    //Atributos
    protected Connection conexionBD = null;
    
    //Constructor
    public ConexionBD(){   
    }
    
    //Metodo que se conecta con BD
    public Connection realizarConexionBD() {
		String url = "jdbc:mysql://localhost:3306/BDbiblioteca";
		String usuario = "root";
		String contrasegna = "";
		try {
			conexionBD = DriverManager.getConnection(url, usuario, contrasegna);
		} catch (Exception e) {
			System.out.println("Error en la conexion");
		}
		return conexionBD;
	}
}
