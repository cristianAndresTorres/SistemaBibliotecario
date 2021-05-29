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
public class Persona {
    //Atributos
    protected String ID_Documento;
    private String pri_nombre;
    private String seg_nombre;
    private String pri_apellido;
    private String seg_apellido;
    private String tel_contacto; 
    private String tipo_doc;
    private String tipoUsuario;
    
    //Constructor
    public Persona(String ID_Documento,String pri_nombre,String seg_nombre,String pri_apellido,String seg_apellido,String tel_contacto,
                        String tipo_doc,String tipoUsuario){
        //Inicialización de los atributos
        this.ID_Documento = ID_Documento;
        this.pri_nombre = pri_nombre;
        this.seg_nombre = seg_nombre;
        this.pri_apellido = pri_apellido;
        this.seg_apellido = seg_apellido;
        this.tel_contacto = tel_contacto;
        this.tipo_doc = tipo_doc;
        this.tipoUsuario = tipoUsuario;
    }
    public Persona(){
        
}
    //Metodos getters and setters

    public String getID_Documento() {return ID_Documento;}
    public void setID_Documento(String ID_Documento) {this.ID_Documento = ID_Documento;}
    public String getPri_nombre() {return pri_nombre;}
    public void setPri_nombre(String pri_nombre) { this.pri_nombre = pri_nombre;}
    public String getSeg_nombre() {return seg_nombre;}
    public void setSeg_nombre(String seg_nombre) {this.seg_nombre = seg_nombre;}
    public String getPri_apellido() {return pri_apellido;}
    public void setPri_apellido(String pri_apellido) {this.pri_apellido = pri_apellido;}
    public String getSeg_apellido() {return seg_apellido;}
    public void setSeg_apellido(String seg_apellido) { this.seg_apellido = seg_apellido;}
    public String getTel_contacto() {return tel_contacto;}
    public void setTel_contacto(String tel_contacto) {this.tel_contacto = tel_contacto;}
    public String getTipo_doc() {return tipo_doc;}
    public void setTipo_doc(String tipo_doc) {this.tipo_doc = tipo_doc;}
    public String getTipoUsuario() {return tipoUsuario;}
    public void setTipoUsuario(String tipoUsuario) {this.tipoUsuario = tipoUsuario;}

}
