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
import logica.Usuario;

/**
 *
 * @author Alba
 */
public class ModificaBD {
    //Acceso a la base de datos
    //Atributos
    private Connection miConexion;
    private ConexionBD BDcolegio = new ConexionBD();
    private String sqlConsulta;
    private PreparedStatement consulta;
    private ResultSet rsConsulta;
    private Statement miConsulta;
    private ConsultaBD cb = new ConsultaBD();
    
    //-------------------------------------------------------------------------
    //Constructor
    public ModificaBD(){
        
    }
    
    
    //int 
    //Modificar--------------------------------------------------------------------------
    public boolean modificar_Libro(Libro miLibro) {
        try {
            //1->realizar Conexion
            Connection conexionBD = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            String sqlModificacion = "Update Libro set Autor_ID_Autor=?, Categoria_ID_Categoria=?, Editorial_ID_Editorial=?"+
            " where ID_libro=? and ID_ejemplar=?";

            consulta = conexionBD.prepareStatement(sqlModificacion);

            //3-> pasar los parametros
            consulta.setInt(1, cb.consultaIDautores(miLibro.getAutor()));//(Nuevo saldo disponible en la cuenta);
            consulta.setInt(2, cb.consultaIDCategoria(miLibro.getCategoria()));//acumulado diario del dinero a retirar
            consulta.setInt(3, cb.consultaIDeditorial(miLibro.getEditorial()));//Id de la cuenta a actualizar
            consulta.setString(4, miLibro.getId_libro());//(Nuevo saldo disponible en la cuenta);
            consulta.setInt(5, miLibro.getId_ejemplar());//acumulado diario del dinero a retirar
            //4->Terminar sentencia
            consulta.execute();
            conexionBD.close();
            return true;
        } catch (Exception e) {
            System.out.println("ModificarCuentaRetiro - Update - error");
            return false;
        }
    }
     public boolean modificar_Persona(Persona miPersona) {
        try {
            //1->realizar Conexion
            Connection conexionBD = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            String sqlModificacion = "Update Persona set pri_nombre=?, seg_nombre=?, pri_apellido=?, seg_apellido=?, tel_contacto=?, estadoUsuario=?"+
            " where ID_Documento=?";

            consulta = conexionBD.prepareStatement(sqlModificacion);

            //3-> pasar los parametros
            consulta.setString(1, miPersona.getPri_nombre());//(Nuevo saldo disponible en la cuenta);
            consulta.setString(2, miPersona.getSeg_nombre());//acumulado diario del dinero a retirar
            consulta.setString(3, miPersona.getPri_apellido());//Id de la cuenta a actualizar
            consulta.setString(4, miPersona.getSeg_apellido());//(Nuevo saldo disponible en la cuenta);
            consulta.setString(5, miPersona.getTel_contacto());//acumulado diario del dinero a retirar
            consulta.setBoolean(6, Boolean.parseBoolean(miPersona.getTipo_doc()));//acumulado diario del dinero a retirar
            consulta.setString(7, miPersona.getID_Documento());
            
            //4->Terminar sentencia
            consulta.execute();
            conexionBD.close();
            return true;
        } catch (Exception e) {
            System.out.println("ModificarCuentaRetiro - Update - error");
            return false;
        }
    }
    
      //Modificar--------------------------------------------------------------------------
    
    public boolean modificar_Usuario_cantidad(Usuario miUsuario) {
        try {
            //1->realizar Conexion
            Connection conexionBD = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            String sqlModificacion = "Update Usuario set stock_libros=?, estado_prestamo=?"+
            " where Persona_ID_Documento=?";

            consulta = conexionBD.prepareStatement(sqlModificacion);
            
            //3-> pasar los parametros
            consulta.setInt(1, miUsuario.getStock_libros());//(Nuevo saldo disponible en la cuenta);
            consulta.setBoolean(2, miUsuario.getEstado_prestamo());//acumulado diario del dinero a retirar
            consulta.setString(3, miUsuario.getID_Documento());//Id de la cuenta a actualizar

            //4->Terminar sentencia
            consulta.execute();
            conexionBD.close();
            return true;
        } catch (Exception e) {
            System.out.println("ModificarCuentaRetiro - Update - error");
            return false;
        }
    }
    
    public boolean modificar_Libro_Estado(Libro miLibro) {
        try {
            //1->realizar Conexion
            Connection conexionBD = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            String sqlModificacion = "Update Libro set estado_libro=?"+
            " where ID_libro=? AND ID_ejemplar=?";

            consulta = conexionBD.prepareStatement(sqlModificacion);
            
            //3-> pasar los parametros
            consulta.setBoolean(1, miLibro.isEstadoLibro());//(Nuevo saldo disponible en la cuenta);
            consulta.setString(2, miLibro.getId_libro());//acumulado diario del dinero a retirar
            consulta.setInt(3, miLibro.getId_ejemplar());//Id de la cuenta a actualizar

            //4->Terminar sentencia
            consulta.execute();
            conexionBD.close();
            return true;
        } catch (Exception e) {
            System.out.println("ModificarCuentaRetiro - Update - error");
            return false;
        }
    }
    
     public boolean modificar_Estado_Prestamo(Prestamo prestamo) {
        try {
            //1->realizar Conexion
            Connection conexionBD = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            String sqlModificacion = "Update Prestamo set estado=?"+
            " where Libro_ID_libro=? and Libro_ID_ejemplar=? and Usuario_Persona_ID_Documento=? and estado=false";

            consulta = conexionBD.prepareStatement(sqlModificacion);
            
            //3-> pasar los parametros
            consulta.setBoolean(1, prestamo.isEstado());//(Nuevo saldo disponible en la cuenta);
            consulta.setString(2, prestamo.getId_Libro());//acumulado diario del dinero a retirar
            consulta.setInt(3, Integer.parseInt(prestamo.getId_Ejemplar()));//Id de la cuenta a actualizar
            consulta.setString(4, prestamo.getTitulo_libro());
            
            //4->Terminar sentencia
            consulta.execute();
            conexionBD.close();
            return true;
        } catch (Exception e) {
            System.out.println("modificar_Estado_Prestamo - Update - error");
            return false;
        }
    }
     
}