/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesVistaInicial;

import disegnoComponentes.ObjDecoradorServicio;
import disegnoComponentes.ObjGraficosServicio;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author 57301
 */
public class PanelIngreso extends JPanel{
    //Atributos
    protected JButton jbtnIngreso;
    protected JButton jbtnIMG;
    private ImageIcon imgColegio;
    protected JTextField jtUsuario;
    protected JPasswordField jpContrasegna;
    private JLabel jlTitulo, jlIngreso;
    
    //Controlador
    PanelIngresoControlador panelIngresoControlador;
    
    //Diseño de la ventana
    private ObjGraficosServicio objetoGrafico;
    private ObjDecoradorServicio objetoDecorador;
    
    //Constructor
    public PanelIngreso(PanelIngresoControlador panelIngresoControlador){
        //Diseño del panel
        objetoDecorador = ObjDecoradorServicio.obtenerServicio();
        objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        //Oyente
        this.panelIngresoControlador = panelIngresoControlador;
        
        //Caracteristicas de la ventana
	this.setSize(250, 450);
	this.setBackground(objetoDecorador.getColorVerdeClaro());
	this.setLayout(null);
	this.setVisible(true);
        
        //componentes de la ventana
        this.crearComponentes();
    }
    
    //crear componentes del panel
    public void crearComponentes(){
        
        jlTitulo = objetoGrafico.crearJLabel("Iniciar sesión", 0, 0, 250, 65, objetoDecorador.getFontTprincipal(), objetoDecorador.getColorGrisOscuro(), objetoDecorador.getcMano());
        this.add(jlTitulo);
        
        imgColegio = new ImageIcon("src/img/ingreso.png");
        jbtnIMG = objetoGrafico.crearBoton("",45,50,150,150, null, null, null, imgColegio);
        this.add(jbtnIMG);
        
        jtUsuario = objetoGrafico.crearJTextField("20172020116", 25, 250, 200, 45, Color.WHITE, Color.BLACK, objetoDecorador.getColorAzulOscuro());
        this.add(jtUsuario);
        
        jpContrasegna = objetoGrafico.crearJPasswordField("123456789", 25, 310, 200, 45, Color.WHITE, Color.BLACK, objetoDecorador.getColorAzulOscuro());
        this.add(jpContrasegna);
        
        
        
        
        jbtnIngreso = objetoGrafico.crearBoton("Ingresar", 25, 375, 200, 50, Color.white, objetoDecorador.getColorAzulOscuro(), objetoDecorador.getcMano(), null);
        jbtnIngreso.addActionListener(panelIngresoControlador);
        this.add(jbtnIngreso);
    }
    
    public void reestablecerInfo(){
        jtUsuario.setText("Ingrese el número de usuario");
        jpContrasegna.setText("1234");
    }
    
}
