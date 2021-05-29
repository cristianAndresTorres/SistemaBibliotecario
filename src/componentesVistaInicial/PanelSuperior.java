/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesVistaInicial;

import disegnoComponentes.ObjDecoradorServicio;
import disegnoComponentes.ObjGraficosServicio;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 57301
 */
public class PanelSuperior extends JPanel{
    //Atributos
    private ObjGraficosServicio objetoGrafico;
    private ObjDecoradorServicio objetoDecorador;
    private ImageIcon imgAux, imgEscudo;
    private JButton jbtnIMG;
    private JLabel jlTitulo;
    
    public PanelSuperior(){
        
        
        //Objetos decoradores        
        this.objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        this.objetoDecorador = ObjDecoradorServicio.obtenerServicio();
        
        //Caracteristicas de la ventana
	this.setSize(1200, 100);
	this.setBackground(objetoDecorador.getColorRojOscuro());
	this.setLayout(null);
	this.setVisible(true);
        
        //elementos de la ventana
        this.inicializarComponentes();
    }
    
    public void inicializarComponentes(){
        imgAux = new ImageIcon("src/img/escudo.jpg");
        jbtnIMG = objetoGrafico.crearBoton("", 10, 10, 80, 80, null, null, objetoDecorador.getcMano(), imgAux);
        this.add(jbtnIMG);
        
        Font tamagno =  new Font("Arial", 3, 40);
        jlTitulo = objetoGrafico.crearJLabel("Sistema Bibliotecario Juana Escobar I.E.D.", 150, 10, 900, 75, tamagno, Color.BLACK, objetoDecorador.getcMano());
        
        this.add(jlTitulo);
    }
    
}
