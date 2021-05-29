/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author 57301
 */
public class Usuario extends Persona{
    //Atributos
    private int cantidad_libros;
    private int stock_libros;
    private boolean estado_prestamo;
    
    
    //Constructor
    public Usuario(String ID_Documento, String pri_nombre, String seg_nombre, 
                        String pri_apellido, String seg_apellido, String tel_contacto, 
                            String tipo_doc, String tipoUsuario,int cantidad_libros, int stock_libros, 
                                boolean estado_prestamo){
        super(ID_Documento, pri_nombre, seg_nombre, pri_apellido, seg_apellido, tel_contacto, tipo_doc, tipoUsuario);
        this.cantidad_libros = cantidad_libros;
        this.stock_libros = stock_libros;
        this.estado_prestamo = estado_prestamo;
    }
    
    public Usuario(int cantidad_libros,int stock_libros,boolean estado_prestamo,String ID_Documento){
        this.cantidad_libros = cantidad_libros;
        this.stock_libros = stock_libros;
        this.estado_prestamo = estado_prestamo;
        this.ID_Documento = ID_Documento;
        
    }
    
    //Metodos getters and setters

    public int getCantidad_libros() {return cantidad_libros;}
    public void setCantidad_libros(int cantidad_libros) {this.cantidad_libros = cantidad_libros;}
    public int getStock_libros() {return stock_libros;}
    public void setStock_libros(int stock_libros) {this.stock_libros = stock_libros;}
    public boolean getEstado_prestamo() {return estado_prestamo;}
    public void setEstado_prestamo(boolean estado_prestamo) {this.estado_prestamo = estado_prestamo;}
}
