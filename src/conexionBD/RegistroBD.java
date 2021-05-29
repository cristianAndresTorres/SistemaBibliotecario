/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import logica.Libro;
import logica.Persona;
import logica.Prestamo;
import java.util.Date;


/**
 *
 * @author Alba
 */
public class RegistroBD {
    //Acceso a la base de datos
    //Atributos
    private Connection miConexion;
    private ConexionBD BDcolegio = new ConexionBD();
    private String sqlConsulta;
    private PreparedStatement consulta;
    private ResultSet rsConsulta;
    private Statement miConsulta;
    private ConsultaBD cb = new ConsultaBD();
    
    
    public RegistroBD(){
        
    }
    
    //Registrar------------------------------------------------------------------------
    public boolean registrarAutor(String nombreAutor) {
            try {
                //1-> realizar conexion
                Connection conexionBD = BDcolegio.realizarConexionBD();
                //2-> Preparar Consulta-preParada
                String sqlResgistro = "Insert into Autor (nombreAutor)"+
                " values(?)";

                PreparedStatement consultaRegristro = conexionBD.prepareStatement(sqlResgistro);

                //3-> Pasar parametros de la consulta
                consultaRegristro.setString(1, nombreAutor);
                //4-> realizar modificacion
                consultaRegristro.execute();
                conexionBD.close();
                return true;
            } catch (Exception e) {
                    System.out.println("registrarAutor - Registro - error");
                    return false;
            }
    }
    
    public boolean registrarCategoria(String categoria) {
            try {
                //1-> realizar conexion
                Connection conexionBD = BDcolegio.realizarConexionBD();
                //2-> Preparar Consulta-preParada
                String sqlResgistro = "Insert into Categoria (des_categoria)"+
                " values(?)";

                PreparedStatement consultaRegristro = conexionBD.prepareStatement(sqlResgistro);

                //3-> Pasar parametros de la consulta
                consultaRegristro.setString(1, categoria);
                //4-> realizar modificacion
                consultaRegristro.execute();
                conexionBD.close();
                return true;
            } catch (Exception e) {
                    System.out.println("registrarCategoria - Registro - error");
                    return false;
            }
    }
    
    public boolean registrarEditorial(String editorial) {
            try {
                //1-> realizar conexion
                Connection conexionBD = BDcolegio.realizarConexionBD();
                //2-> Preparar Consulta-preParada
                String sqlResgistro = "Insert into Editorial (des_editorial)"+
                " values(?)";

                PreparedStatement consultaRegristro = conexionBD.prepareStatement(sqlResgistro);

                //3-> Pasar parametros de la consulta
                consultaRegristro.setString(1, editorial);
                //4-> realizar modificacion
                consultaRegristro.execute();
                conexionBD.close();
                return true;
            } catch (Exception e) {
                    System.out.println("registrarEditorial - Registro - error");
                    return false;
            }
    }
    
    public boolean registrarLibro(Libro libro) {
            try {
                //1-> realizar conexion
                Connection conexionBD = BDcolegio.realizarConexionBD();
                //2-> Preparar Consulta-preParada
                String sqlResgistro = "Insert into Libro "+
                " values(?,?,?,?,?,?,?)";

                PreparedStatement consultaRegristro = conexionBD.prepareStatement(sqlResgistro);

                //3-> Pasar parametros de la consulta
                consultaRegristro.setString(1, libro.getId_libro());
                consultaRegristro.setInt(2, libro.getId_ejemplar());
                consultaRegristro.setString(3, libro.getNombreLibro());
                consultaRegristro.setBoolean(4, true);
                consultaRegristro.setInt(5, cb.consultaIDautores(libro.getAutor()));
                consultaRegristro.setInt(6, cb.consultaIDCategoria(libro.getCategoria()));
                consultaRegristro.setInt(7, cb.consultaIDeditorial(libro.getEditorial()));
                //4-> realizar modificacion
                consultaRegristro.execute();
                conexionBD.close();
                return true;
            } catch (Exception e) {
                    System.out.println("registrarLibro - Registro - error");
                    return false;
            }
    }
    
    public boolean registrarPersona(Persona miPersona) {
            try {
                //1-> realizar conexion
                Connection conexionBD = BDcolegio.realizarConexionBD();
                //2-> Preparar Consulta-preParada
                String sqlResgistro = "Insert into Persona"+
                " values(?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement consultaRegristro = conexionBD.prepareStatement(sqlResgistro);

                //3-> Pasar parametros de la consulta
                consultaRegristro.setString(1, miPersona.getID_Documento());
                consultaRegristro.setString(2, miPersona.getPri_nombre());
                consultaRegristro.setString(3, miPersona.getSeg_nombre());
                consultaRegristro.setString(4, miPersona.getPri_apellido());
                consultaRegristro.setString(5, miPersona.getSeg_apellido());
                consultaRegristro.setString(6, miPersona.getTel_contacto());
                consultaRegristro.setString(7, miPersona.getTipo_doc());
                consultaRegristro.setString(8, miPersona.getID_Documento());
                consultaRegristro.setString(9, miPersona.getTipoUsuario());
                consultaRegristro.setBoolean(10, true);
                //4-> realizar modificacion
                consultaRegristro.execute();
                conexionBD.close();
                return true;
            } catch (Exception e) {
                    System.out.println("registrarUsuario - Registro - error");
                    return false;
            }
    }
    
    public boolean registrarUsuario(Persona persona) {
            try {
                //1-> realizar conexion
                Connection conexionBD = BDcolegio.realizarConexionBD();
                //2-> Preparar Consulta-preParada
                String sqlResgistro = "Insert into Usuario(Persona_ID_Documento,cantidad_libros,stock_libros,estado_prestamo) "+
                " values(?,?,?,?)";

                PreparedStatement consultaRegristro = conexionBD.prepareStatement(sqlResgistro);

                //3-> Pasar parametros de la consulta
                consultaRegristro.setString(1, persona.getID_Documento());
                if(persona.getTipoUsuario()=="2"){
                    consultaRegristro.setInt(2, 40);
                }else if(persona.getTipoUsuario()=="3"){
                    consultaRegristro.setInt(2, 1);
                }else{
                    consultaRegristro.setInt(2, 2);
                }
                consultaRegristro.setInt(3, 0);
                consultaRegristro.setBoolean(4, true);
                //4-> realizar modificacion
                consultaRegristro.execute();
                conexionBD.close();
                return true;
            } catch (Exception e) {
                    System.out.println("registrarUsuario - Registro - error");
                    return false;
            }
    }
    
    //Registra
public boolean registrarPrestamo(Prestamo miPrestamo) {
            try {
                //1-> realizar conexion
                Connection conexionBD = BDcolegio.realizarConexionBD();
                //2-> Preparar Consulta-preParada
                String sqlResgistro = "Insert into Prestamo(fecha_entrega,fecha_devolucion,estado,ampliar_prestamo,Libro_ID_libro,Libro_ID_ejemplar,Usuario_Persona_ID_Documento) "+
                " values(?,?,?,?,?,?,?)";

                PreparedStatement consultaRegristro = conexionBD.prepareStatement(sqlResgistro);

                //3-> Pasar parametros de la consulta
                consultaRegristro.setString(1, miPrestamo.getFecha_prestamo());
                consultaRegristro.setString(2, miPrestamo.getFecha_devolucion());
                consultaRegristro.setBoolean(3, false);
                consultaRegristro.setBoolean(4, false);
                consultaRegristro.setString(5, miPrestamo.getId_Libro());
                consultaRegristro.setInt(6, Integer.parseInt(miPrestamo.getId_Ejemplar()));
                consultaRegristro.setString(7, miPrestamo.getTitulo_libro());
                
                //4-> realizar modificacion
                consultaRegristro.execute();
                conexionBD.close();
                return true;
            } catch (Exception e) {
                    System.out.println("registrarPrestamo - Registro - error");
                    return false;
            }
    }
    
}
