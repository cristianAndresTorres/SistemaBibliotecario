/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Date;
import java.time.LocalDate;

/**
 *
 * @author 57301
 */
public class Prestamo {
    //Atributos
    private String id_Prestamo;
    private String id_Libro;
    private String id_Ejemplar; 
    private String titulo_libro;
    private String fecha_prestamo;
    private String fecha_devolucion;
    private boolean estado;
    private boolean ampliarPrestamo;
    
    //Constructor
    public Prestamo(String id_Prestamo,String id_Libro,String id_Ejemplar,String titulo_libro,
                    String fecha_prestamo, String fecha_devolucion,boolean estado,boolean ampliarPrestamo){
        
        this.id_Prestamo = id_Prestamo;
        this.id_Libro = id_Libro;
        this.id_Ejemplar = id_Ejemplar;
        this.titulo_libro = titulo_libro;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.estado = estado;
        this.ampliarPrestamo = ampliarPrestamo;
        
    }
    //--------------------------------------------------------------------------
    //Metodos getters and setters
    //-------------------------------------------------------------------------

    public String getId_Prestamo() {
        return id_Prestamo;
    }

    public void setId_Prestamo(String id_Prestamo) {
        this.id_Prestamo = id_Prestamo;
    }

    public String getId_Libro() {
        return id_Libro;
    }

    public void setId_Libro(String id_Libro) {
        this.id_Libro = id_Libro;
    }

    public String getId_Ejemplar() {
        return id_Ejemplar;
    }

    public void setId_Ejemplar(String id_Ejemplar) {
        this.id_Ejemplar = id_Ejemplar;
    }

    public String getTitulo_libro() {
        return titulo_libro;
    }

    public void setTitulo_libro(String titulo_libro) {
        this.titulo_libro = titulo_libro;
    }

    public String getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(String fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isAmpliarPrestamo() {
        return ampliarPrestamo;
    }

    public void setAmpliarPrestamo(boolean ampliarPrestamo) {
        this.ampliarPrestamo = ampliarPrestamo;
    }
    
    
    
}
