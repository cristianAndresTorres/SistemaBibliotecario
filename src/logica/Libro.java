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
public class Libro {
    //Atributos
    private String autor;
    private String categoria;
    private String editorial;
    private boolean estadoLibro;
    private int id_ejemplar;
    private String id_libro;
    private String nombreLibro;
    
    //constructor
    public Libro(String autor, String categoria, String editorial, boolean estadoLibro, 
                    int id_ejemplar, String id_libro, String nombreLibro){
        this.autor = autor;
        this.categoria = categoria;
        this.editorial = editorial;
        this.estadoLibro = estadoLibro;
        this.id_ejemplar = id_ejemplar;
        this.id_libro = id_libro;
        this.nombreLibro = nombreLibro;
    }
    
     public Libro(int id_ejemplar, String id_libro, boolean estadol){
        this.id_ejemplar = id_ejemplar;
        this.id_libro = id_libro;
        this.estadoLibro=estadol;
     }
    
    //--------------------------------------------------------------------------
    //Metodos getters and setters
    //--------------------------------------------------------------------------
    public String getAutor() {return autor;}
    public void setAutor(String autor) {this.autor = autor;}
    public String getCategoria() {return categoria;}
    public void setCategoria(String categoria) {this.categoria = categoria;}
    public String getEditorial() {return editorial;}
    public void setEditorial(String editorial) {this.editorial = editorial;}
    public boolean isEstadoLibro() {return estadoLibro;}
    public void setEstadoLibro(boolean estadoLibro) {this.estadoLibro = estadoLibro;}
    public int getId_ejemplar() { return id_ejemplar;}
    public void setId_ejemplar(int id_ejemplar) {this.id_ejemplar = id_ejemplar;}
    public String getId_libro() {return id_libro;}
    public void setId_libro(String id_libro) {this.id_libro = id_libro;}
    public String getNombreLibro() {return nombreLibro;}
    public void setNombreLibro(String nombreLibro) {this.nombreLibro = nombreLibro;}

}
