/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesUsuarioGeneral;

import disegnoComponentes.ObjDecoradorServicio;
import disegnoComponentes.ObjGraficosServicio;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import logica.Persona;

/**
 *
 * @author 57301
 */
public class PanelDatosPersonal extends JPanel{
    //Atributos
    private JLabel jl_titulo,jle_ID_Documento,jle_nombres,jle_apellidos,jle_telefono,jle_tipoUsu;
    //Diseño de la ventana
    private ObjGraficosServicio objetoGrafico;
    private ObjDecoradorServicio objetoDecorador;
    
    public PanelDatosPersonal(){
        //Diseño del panel
        objetoDecorador = ObjDecoradorServicio.obtenerServicio();
        objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        
        //Diseño de ventana
        this.setSize(600, 450);
        this.setBackground(objetoDecorador.getColorGrisClaro());
        this.setLayout(null);
	this.setVisible(true);
        
        inicializarJLabel();
    }
    
    public void inicializarJLabel(){
        jl_titulo = objetoGrafico.crearJLabel("Información del usuario", 80, 40, 400, 50, objetoDecorador.getFontTprincipal(), Color.BLACK, objetoDecorador.getcMano());
        this.add(jl_titulo);
        
        jle_ID_Documento = objetoGrafico.crearJLabel("",40, 150, 400, 50, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        jle_ID_Documento.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(jle_ID_Documento);
        jle_nombres = objetoGrafico.crearJLabel("", 40, 200, 400, 50, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        jle_nombres.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(jle_nombres);
        jle_apellidos = objetoGrafico.crearJLabel("",40, 250, 400, 50, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        jle_apellidos.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(jle_apellidos);
        jle_telefono = objetoGrafico.crearJLabel("",40, 300, 400, 50, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        jle_telefono.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(jle_telefono);
        jle_tipoUsu = objetoGrafico.crearJLabel("",40, 100, 400, 50, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        jle_tipoUsu.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(jle_tipoUsu);        
    }
    
    public void actualizarInfo(Persona persona){
        jle_ID_Documento.setText("Documento de identificación: "+persona.getID_Documento()+" "+persona.getTipo_doc());
        jle_nombres.setText("Nombres: "+persona.getPri_nombre()+" "+persona.getSeg_apellido());
        jle_apellidos.setText("Apellidos: "+persona.getPri_apellido()+" "+persona.getSeg_apellido());
        jle_telefono.setText("Teléfono contacto: "+persona.getTel_contacto());
        switch(persona.getTipoUsuario()){
            case "1":
                jle_tipoUsu.setText("Tipo de usuario : Administrador");
                break;
            case "2":
                jle_tipoUsu.setText("Tipo de usuario : Decente");
                break;
            case "3":
                jle_tipoUsu.setText("Tipo de usuario : Estudiante");
                break;  
            case "4":
                jle_tipoUsu.setText("Tipo de usuario : Administrativo");
                break;    
        }
    }
    
    
}
