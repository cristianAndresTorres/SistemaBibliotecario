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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import logica.Libro;
import logica.Persona;
import logica.Prestamo;
import logica.Usuario;

/**
 *
 * @author 57301
 */
public class ConsultaBD {
    //Atributos
    private Connection miConexion;
    private ConexionBD BDcolegio = new ConexionBD();
    private String sqlConsulta;
    private PreparedStatement consulta;
    private ResultSet rsConsulta;
    private Statement miConsulta;
    //Listas 
    //String--------------------------------------------------------------------
    private ArrayList<String>autores = new ArrayList<>();
    private ArrayList<String>categorias = new ArrayList<>();
    private ArrayList<String>editoriales = new ArrayList<>();
    private ArrayList<Prestamo>listaPrestamos = new ArrayList<>();
    //Libros--------------------------------------------------------------------
    private ArrayList<Libro> listaLibros = new ArrayList<>();
    private Libro libro;
    private ArrayList<Persona> listaPersona = new ArrayList<>();
    
    public ConsultaBD(){
        
    }
    //--------------------------------------------------------------------------
    //Obtiene toda la lista de autores
    //--------------------------------------------------------------------------
    public ArrayList<String> obtenerAutores(){
        try {
            //Realizar conexion (puente)
            miConexion = BDcolegio.realizarConexionBD();
            //Preparar consulta
            miConsulta = miConexion.createStatement();

            sqlConsulta = "Select nombreAutor from Autor ORDER BY nombreAutor ASC";
            //Realizar consulta
            rsConsulta = miConsulta.executeQuery(sqlConsulta);

            //Recorrer tabla
            while(rsConsulta.next()) {
                    autores.add(rsConsulta.getString("nombreAutor"));
                    //System.out.println(autores.get(1));
            }
            //4->Terminar sentencia
            miConsulta.close();
            miConexion.close();
        } catch (Exception e) {
                System.out.print("ConsultaBD_Obtener autores-error");
        }        
        return autores;
    }
    //--------------------------------------------------------------------------
    //Obtiene toda la lista de categorias 
    //--------------------------------------------------------------------------
    public ArrayList<String> obtenerCategorias(){
        try {
            //Realizar conexion (puente)
            miConexion = BDcolegio.realizarConexionBD();
            //Preparar consulta
            miConsulta = miConexion.createStatement();

            sqlConsulta = "Select des_categoria from Categoria ORDER BY des_categoria ASC";
            //Realizar consulta
            rsConsulta = miConsulta.executeQuery(sqlConsulta);

            //Recorrer tabla
            while(rsConsulta.next()) {
                    categorias.add(rsConsulta.getString("des_categoria"));
            }
            //4->Terminar sentencia
            miConsulta.close();
            miConexion.close();
        } catch (Exception e) {
                System.out.print("ConsultaBD_Obtener categorias-error");
        }        
        return categorias;
    }
    //--------------------------------------------------------------------------
    //Obtiene toda la lista de editoriales
    //--------------------------------------------------------------------------
    public ArrayList<String> obtenerEditorial(){
        try {
            //Realizar conexion (puente)
            miConexion = BDcolegio.realizarConexionBD();
            //Preparar consulta
            miConsulta = miConexion.createStatement();

            sqlConsulta = "Select des_editorial from Editorial ORDER BY des_editorial ASC";
            //Realizar consulta
            rsConsulta = miConsulta.executeQuery(sqlConsulta);

            //Recorrer tabla
            while(rsConsulta.next()) {
                    editoriales.add(rsConsulta.getString("des_editorial"));
            }
            //4->Terminar sentencia
            miConsulta.close();
            miConexion.close();
        } catch (Exception e) {
                System.out.print("ConsultaBD_Obtener editorial-error");
        }        
        return editoriales;
    }
    //--------------------------------------------------------------------------
    //Obtiene toda la lista de los catalogos de libros
    //--------------------------------------------------------------------------
    public ArrayList<Libro> obtenerTodosLibros(String nombreLibro, String nombreAutor, String categoria, String editorial){
        try {
            
             //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select ID_libro,ID_ejemplar,nombre_libro,estado_libro,nombreAutor,des_editorial,des_categoria "
                    + "from Libro,Autor,Editorial,Categoria where nombre_libro LIKE ? and nombreAutor LIKE ? and des_categoria LIKE ? and des_editorial LIKE ? and Autor_ID_Autor=ID_Autor and Categoria_ID_Categoria=ID_Categoria and Editorial_ID_Editorial=ID_Editorial";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, nombreLibro);     
            consulta.setString(2, nombreAutor);
            consulta.setString(3, categoria);
            consulta.setString(4, editorial);
            rsConsulta = consulta.executeQuery();
            //Validación
            
             //Realizar consulta
            listaLibros.removeAll(listaLibros);
            int x=0;
            while(rsConsulta.next()){ 
                        
                listaLibros.add(new Libro(rsConsulta.getString("nombreAutor"), rsConsulta.getString("des_categoria"), rsConsulta.getString("des_editorial"), 
                                rsConsulta.getBoolean("estado_libro"), rsConsulta.getInt("ID_ejemplar"), rsConsulta.getString("ID_libro"), rsConsulta.getString("nombre_libro")));
            
                
            }
            System.out.println("Cantidad_libros"+listaLibros.size());
            //4->Terminar sentencia
            rsConsulta.close();
            miConexion.close();
            return listaLibros;
        } catch (Exception e) {
                System.out.print("Consulta obtener_todos_libros-error");
                return listaLibros;
        }        
    }  
    //--------------------------------------------------------------------------
    //Consulta si el usuario Existe
    //--------------------------------------------------------------------------
    public int consultarUsuario(String id_Usuario, String contrasegna){
        int seleccion_Panel = -1;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select tipoUsuario,estadoUsuario from Persona where ID_Documento=? and contrasegna=?";

            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, id_Usuario);
            consulta.setString(2, contrasegna);
            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {
                //Si el estado es verdadero se le permite el acceso
                if(rsConsulta.getBoolean("estadoUsuario")){
                    //Se selecciona el panel[1,2,3,4]
                    seleccion_Panel = rsConsulta.getInt("tipoUsuario");
                }else{
                    //0 usuarioBloqueado
                    seleccion_Panel = 0;
                } 
	    }
            rsConsulta.close();
            miConexion.close();
            System.out.print("Hello it's me"+seleccion_Panel);
            return seleccion_Panel;
        }catch (Exception e) {
            System.out.println("consultarUsuario - Select - error");
            return seleccion_Panel;
	}
    }
    //--------------------------------------------------------------------------
    //Obtiene al usuario[2,3,4]
    //--------------------------------------------------------------------------
    public Usuario obtenerUsuario(String id_Usuario){
        Usuario miUsuario=null;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select * from Persona,Usuario where ID_Documento=? and Persona_ID_Documento=ID_Documento";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, id_Usuario);            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {  
                miUsuario = new Usuario(rsConsulta.getString("ID_Documento"), rsConsulta.getString("pri_nombre"),rsConsulta.getString("seg_nombre"),rsConsulta.getString("pri_apellido"), 
                                                    rsConsulta.getString("seg_apellido"), rsConsulta.getString("tel_contacto"), rsConsulta.getString("TipoDocumento_ID_tipo_doc"), rsConsulta.getString("tipoUsuario"), 
                                                        rsConsulta.getInt("cantidad_libros"), rsConsulta.getInt("stock_libros"), rsConsulta.getBoolean("estado_prestamo"));
	    }
            rsConsulta.close();
            miConexion.close();
            return miUsuario;
        }catch (Exception e) {
            System.out.println("obtenerUsuario - Select - error");
            return miUsuario;
	}
    }
    
    
    //--------------------------------------------------------------------------
    //Obtiene al Administrador
    //--------------------------------------------------------------------------
    public Persona obtenerAdministrador(String id_Usuario){
        Persona miAdministrador=null;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select * from Persona where ID_Documento=?";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, id_Usuario);            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {  
                miAdministrador = new Persona(rsConsulta.getString("ID_Documento"), rsConsulta.getString("pri_nombre"),rsConsulta.getString("seg_nombre"),rsConsulta.getString("pri_apellido"), 
                                                    rsConsulta.getString("seg_apellido"), rsConsulta.getString("tel_contacto"), rsConsulta.getString("TipoDocumento_ID_tipo_doc"), rsConsulta.getString("tipoUsuario"));
	    }
            rsConsulta.close();
            miConexion.close();
            return miAdministrador;
        }catch (Exception e) {
            System.out.println("obtenerUsuario - Select - error");
            return miAdministrador;
	}
    }
    
    
    
    //--------------------------------------------------------------------------
    //Obtener listado prestamos para un usuario en especifico
    //--------------------------------------------------------------------------
    public ArrayList<Prestamo> consultarPrestamo(String id_Usuario){
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select ID_Prestamo,Libro_ID_libro,Libro_ID_ejemplar,"
                            + "nombre_libro,fecha_entrega,fecha_devolucion,estado,"
                                + "ampliar_prestamo from Prestamo,Libro where Usuario_Persona_ID_Documento=? "
                                    + "and Libro_ID_libro=ID_libro and Libro_ID_ejemplar=ID_ejemplar";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, id_Usuario);            
            rsConsulta = consulta.executeQuery();
            //Validación
            
             //Realizar consulta
            listaPrestamos.removeAll(listaPrestamos);
            int x=0;
            while(rsConsulta.next()){  
                listaPrestamos.add(new Prestamo(rsConsulta.getString("ID_Prestamo"), rsConsulta.getString("Libro_ID_libro"), rsConsulta.getString("Libro_ID_ejemplar"), 
                                        rsConsulta.getString("nombre_libro"),rsConsulta.getString("fecha_entrega"),  rsConsulta.getString("fecha_devolucion"), rsConsulta.getBoolean("estado"), rsConsulta.getBoolean("ampliar_prestamo")));
            
            }
            
            rsConsulta.close();
            miConexion.close();
            return listaPrestamos;
        }catch (Exception e) {
            System.out.println("obtenerUsuario - Select - error");
            return listaPrestamos;
	}
    }  
    
    public boolean consultaAutor(String nombreAutor){
        boolean estado = false;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select nombreAutor from Autor where nombreAutor=?";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, nombreAutor);            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {  
                estado = true;
	    }
            rsConsulta.close();
            miConexion.close();
            return estado;
        }catch (Exception e) {
            System.out.println("consultaAutor - Select - error");
            return estado;
	}
        
    }
    
    public boolean consultaCategoria(String categoria){
        boolean estado = false;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select des_categoria from Categoria where des_categoria=?";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, categoria);            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {  
                estado = true;
	    }
            rsConsulta.close();
            miConexion.close();
            return estado;
        }catch (Exception e) {
            System.out.println("consultaCategoria - Select - error");
            return estado;
	}  
    }
            
    public boolean consultaEditorias(String des_editorial){
    boolean estado = false;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select des_editorial from Editorial where des_editorial=?";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, des_editorial);            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {  
                estado = true;
	    }
            rsConsulta.close();
            miConexion.close();
            return estado;
        }catch (Exception e) {
            System.out.println("consultaCategoria - Select - error");
            return estado;
	}  
    }
    
    public boolean consultarID_Libro(Libro libro){
        boolean estado = false;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select ID_libro from Libro where ID_libro=?";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, libro.getId_libro());            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {  
                estado = true;
	    }
            rsConsulta.close();
            miConexion.close();
            return estado;
        }catch (Exception e) {
            System.out.println("consultarID_Libro - Select - error");
            return estado;
	}  
    }
    
    public int consultarNombreLibro(Libro libro){
        int cantidad = 0;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select nombre_libro,ID_ejemplar from Libro where nombre_libro=? and ID_libro=? ORDER BY ID_ejemplar DESC;";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, libro.getNombreLibro()); 
            consulta.setString(2, libro.getId_libro()); 
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {  
                cantidad = rsConsulta.getInt("ID_ejemplar");
	    }
            rsConsulta.close();
            miConexion.close();
            return cantidad;
        }catch (Exception e) {
            System.out.println("consultarID_Libro - Select - error");
            return cantidad;
	}  
    }
    public int consultaIDautores(String autor){
        int numero = 0;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select ID_Autor from Autor where nombreAutor=?";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, autor);            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {  
                numero = rsConsulta.getInt("ID_Autor");
	    }
            rsConsulta.close();
            miConexion.close();
            return numero;
        }catch (Exception e) {
            System.out.println("consultaCategoria - Select - error");
            return numero;
	}  
    }
    public int consultaIDCategoria(String categoria){
        int numero = 0;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select ID_Categoria from Categoria where des_categoria=?";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, categoria);            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {  
                numero = rsConsulta.getInt("ID_Categoria");
	    }
            rsConsulta.close();
            miConexion.close();
            return numero;
        }catch (Exception e) {
            System.out.println("consultaCategoria - Select - error");
            return numero;
	}  
    }
    public int consultaIDeditorial(String des_editorial){
        int numero = 0;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select ID_Editorial from Editorial where des_editorial=?";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, des_editorial);            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {  
                numero = rsConsulta.getInt("ID_Editorial");
	    }
            rsConsulta.close();
            miConexion.close();
            return numero;
        }catch (Exception e) {
            System.out.println("consultaCategoria - Select - error");
            return numero;
	}  
    }
    
     public ArrayList<Persona> obtenerUsuarios(){
        try {
            //Realizar conexion (puente)
            miConexion = BDcolegio.realizarConexionBD();
            //Preparar consulta
            miConsulta = miConexion.createStatement();

            sqlConsulta = "Select * from Persona ORDER BY pri_nombre ASC";
            //Realizar consulta
            rsConsulta = miConsulta.executeQuery(sqlConsulta);
            listaPersona.removeAll(listaPersona);
            //Recorrer tabla
            while(rsConsulta.next()) {                   
                    listaPersona.add(new Persona(rsConsulta.getString("ID_Documento"), rsConsulta.getString("pri_nombre"), rsConsulta.getString("seg_nombre"), rsConsulta.getString("pri_apellido"), rsConsulta.getString("seg_apellido"), rsConsulta.getString("tel_contacto"), rsConsulta.getString("estadoUsuario"), rsConsulta.getString("tipoUsuario")));
            }
            //4->Terminar sentencia
            miConsulta.close();
            miConexion.close();
        } catch (Exception e) {
                System.out.print("obtenerAdministrador autores-error");
        }        
        return listaPersona;
    }
    
     public Persona obtenerUsuGeneral(String id_Usuario){
        Persona miUsuarioGeneral=null;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select * from Persona where ID_Documento=?";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, id_Usuario);            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {  
                miUsuarioGeneral = new Persona(rsConsulta.getString("ID_Documento"), rsConsulta.getString("pri_nombre"),rsConsulta.getString("seg_nombre"),rsConsulta.getString("pri_apellido"), 
                                                    rsConsulta.getString("seg_apellido"), rsConsulta.getString("tel_contacto"), rsConsulta.getString("estadoUsuario"), rsConsulta.getString("tipoUsuario"));
	    }
            rsConsulta.close();
            miConexion.close();
            return miUsuarioGeneral;
        }catch (Exception e) {
            System.out.println("obtenerUsuario - Select - error");
            return miUsuarioGeneral;
	}
    }
     
     //--------------------------------------------------------------------------
//Consulta BD
    public boolean consultarEstadoUsuario(String id_Usuario){
        boolean seleccion_Panel = false;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select estadoUsuario from Persona where ID_Documento=?";

            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, id_Usuario);
            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {
                //Si el estado es verdadero se le permite el acceso
                if(rsConsulta.getBoolean("estadoUsuario")){
                    //Se selecciona el panel[1,2,3,4]
                    seleccion_Panel = true;
                }else{
                    //0 usuarioBloqueado
                    seleccion_Panel = false;
                } 
	    }
            rsConsulta.close();
            miConexion.close();
            System.out.print("Hello it's me"+seleccion_Panel);
            return seleccion_Panel;
        }catch (Exception e) {
            System.out.println("consultarUsuario - Select - error");
            return seleccion_Panel;
	}
    }
    public boolean consultarEstadoLibro(String id_libro, int ejemplar){
        boolean estado = false;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select estado_libro from Libro where ID_libro=? and ID_ejemplar=?";
            
            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, id_libro);  
            consulta.setInt(2, ejemplar);  
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {
                //Si el estado es verdadero se le permite el acceso
                if(rsConsulta.getBoolean("estado_libro")){
                    //Se selecciona el panel[1,2,3,4]
                    estado = true;
                }else{
                    //0 usuarioBloqueado
                    estado = false;
                } 
	    }
            rsConsulta.close();
            miConexion.close();
            return estado;
        }catch (Exception e) {
            System.out.println("consultarID_Libro - Select - error");
            return estado;
	}  
    }
    
    
    public Usuario obtenerUsuario_general(String id_Usuario){
        Usuario miUsuario = null;
        try {
            //1->realizar conexion
            miConexion = BDcolegio.realizarConexionBD();
            //2->Preparar consulta
            sqlConsulta = "Select cantidad_libros,stock_libros,estado_prestamo,Persona_ID_Documento from Usuario where Persona_ID_Documento=?";

            consulta =miConexion.prepareStatement(sqlConsulta);
            //3->Pasar parametro de la consulta
            consulta.setString(1, id_Usuario);
            
            rsConsulta = consulta.executeQuery();
            //Validación
            if(rsConsulta.next()) {
               
                miUsuario = new Usuario(rsConsulta.getInt("cantidad_libros"), rsConsulta.getInt("stock_libros"), rsConsulta.getBoolean("estado_prestamo"), rsConsulta.getString("Persona_ID_Documento"));
                    //Se selecciona el panel[1,2,3,4]
                
	    }
            rsConsulta.close();
            miConexion.close();
            return miUsuario;
        }catch (Exception e) {
            System.out.println("consultarUsuario - Select - error");
            return miUsuario;
	}
    }
}
