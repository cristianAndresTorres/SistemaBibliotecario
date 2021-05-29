/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorUsuarios;

import componentesVistaInicial.PanelBusqueda;
import componentesVistaInicial.PanelSuperior;
import disegnoComponentes.ObjDecoradorServicio;
import disegnoComponentes.ObjGraficosServicio;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author 57301
 */
public class VistaInicial extends JFrame{
    //Atributos
    private ObjGraficosServicio objetoGrafico;
    private ObjDecoradorServicio objetoDecorador;
    protected JPanel jpIzquierda, jpCentral, jpSuperior, jpDerecha, jpInferior, jpGeneral, jpCentralGeneral;
    private ImageIcon imgAux, imgEscudo;
    private JButton jbtnIMG;
    
    private PanelSuperior panelSuperior = new PanelSuperior();

    

    //Constructor
    public VistaInicial(){
        //Caracteristicas de la ventana
        //Dimensiones de la pantalla
        this.setSize(1200,650);
        this.setLocationRelativeTo(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLayout(null);
        this.setVisible(true);
        //Objetos decoradores        
        this.objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        this.objetoDecorador = ObjDecoradorServicio.obtenerServicio();
                
        this.crearPaneles();
    }
    
    public void crearPaneles(){
        jpSuperior = objetoGrafico.crearPanel(0, 0, 1200, 100, objetoDecorador.getColorRojOscuro());
        jpSuperior.add(panelSuperior);
        this.add(jpSuperior);
        
        jpIzquierda = objetoGrafico.crearPanel(25, 125, 250, 450, objetoDecorador.getColorVerdeClaro());
        this.add(jpIzquierda);
        
        jpInferior = objetoGrafico.crearPanel(0, 600, 1200, 50, objetoDecorador.getColorRojOscuro());
        this.add(jpInferior);
        
        jpCentralGeneral = objetoGrafico.crearPanel(300, 125, 875, 450, Color.white);
        jpCentralGeneral.setVisible(false);
        this.add(jpCentralGeneral);
        
        jpCentral = objetoGrafico.crearPanel(300, 125, 600, 450, objetoDecorador.getColorGrisClaro());
        this.add(jpCentral);
        
        jpDerecha = objetoGrafico.crearPanel(925, 125, 250, 450, objetoDecorador.getColorVerdeClaro());
        this.add(jpDerecha);
        
        jpGeneral = objetoGrafico.crearPanel(0, 0, 1200, 650, Color.WHITE);
        this.add(jpGeneral); 
    }

    //--------------------------------------------------------------------------
    //Getters and setters
    //--------------------------------------------------------------------------

    public JPanel getJpCentral() {
        return jpCentral;
    }

    public JPanel getJpDerecha() {
        return jpDerecha;
    }

    public JPanel getJpCentralGeneral() {
        return jpCentralGeneral;
    }
    
    
}
