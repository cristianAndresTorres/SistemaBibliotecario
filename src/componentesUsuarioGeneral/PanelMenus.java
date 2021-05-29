/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesUsuarioGeneral;

import disegnoComponentes.ObjDecoradorServicio;
import disegnoComponentes.ObjGraficosServicio;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author 57301
 */
public class PanelMenus extends JPanel{
    //Atributos
    //Diseño de la ventana
    private ObjGraficosServicio objetoGrafico;
    private ObjDecoradorServicio objetoDecorador;
    
    //Oyente
    private PanelMenusControlador panelMenusControlador;
    
    //Botones
    protected JButton jbtnInfoPer, jbtnPrestamos, jbtnCatalogoLibros, jbtnSalir;
    
    
    //Constructor
    public PanelMenus(PanelMenusControlador panelMenusControlador){
        
        //Diseño del panel
        this.objetoDecorador = ObjDecoradorServicio.obtenerServicio();
        this.objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        
        //Oyente
        this.panelMenusControlador = panelMenusControlador;
        
        //Caracteristicas de la ventana
	this.setSize(250, 450);
	this.setBackground(objetoDecorador.getColorVerdeClaro());
	this.setLayout(null);
	this.setVisible(true);
        
        this.crearBotones();
    }
    
    public void crearBotones(){
        jbtnInfoPer = objetoGrafico.crearBoton("Información personal", 25, 150, 200, 50, Color.white, objetoDecorador.getColorAzulOscuro(), objetoDecorador.getcMano(), null);
        jbtnInfoPer.addActionListener(panelMenusControlador);
        this.add(jbtnInfoPer);
        
        jbtnPrestamos = objetoGrafico.crearBoton("Prestamos", 25, 225, 200, 50, Color.white, objetoDecorador.getColorAzulOscuro(), objetoDecorador.getcMano(), null);
        jbtnPrestamos.addActionListener(panelMenusControlador);
        this.add(jbtnPrestamos);
        
        jbtnCatalogoLibros = objetoGrafico.crearBoton("Catálogo libros", 25, 300, 200, 50, Color.white, objetoDecorador.getColorAzulOscuro(), objetoDecorador.getcMano(), null);
        jbtnCatalogoLibros.addActionListener(panelMenusControlador);
        this.add(jbtnCatalogoLibros);
        
        jbtnSalir = objetoGrafico.crearBoton("Cerrar sesión", 25, 375, 200, 50, Color.white, objetoDecorador.getColorAzulOscuro(), objetoDecorador.getcMano(), null);
        jbtnSalir.addActionListener(panelMenusControlador);
        this.add(jbtnSalir);   
    }
}
