/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesVistaInicial;

import disegnoComponentes.ObjDecoradorServicio;
import disegnoComponentes.ObjGraficosServicio;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author 57301
 */
public class PanelLibro extends JPanel{
    //Atributos
    private JLabel jetqTitulo;
    //Diseño de la ventana
    
    private ObjGraficosServicio objetoGrafico;
    private ObjDecoradorServicio objetoDecorador;
    
    //Declaración de objetos necesarios para una tabla
    private JScrollPane scrollTabla;
    protected JTable tablaLibros;
    private JTableHeader tablaCabecera;
    protected DefaultTableModel modeloTabla;
    private String [] cabecera = {"Nombre del libro", "Autor", "Categoria", "Editorial", "Número ejemplar", "Estado"};
    
    
    public PanelLibro(){    
        //Diseño del panel
        objetoDecorador = ObjDecoradorServicio.obtenerServicio();
        objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        
        //Diseño de ventana
        this.setSize(600, 450);
        this.setBackground(objetoDecorador.getColorGrisClaro());
        this.setLayout(null);
	this.setVisible(true);
        
        this.etiquetas();
        this.crearTabla();
    }
    
    public void etiquetas(){
        jetqTitulo = objetoGrafico.crearJLabel("Catálogo de libros", 180, 25, 200, 50, objetoDecorador.getFontTprincipal(), objetoDecorador.getColorGrisOscuro(), objetoDecorador.getcMano());
        this.add(jetqTitulo);
    }
    
    public void crearTabla(){
        //Interactuar con la tabla
        modeloTabla = new DefaultTableModel();
        //Cabecera de la tabla
        modeloTabla.setColumnIdentifiers(cabecera);
        
        //creación de la tabla
        tablaLibros = new JTable();
        //Se le pasa el modelo a la tabla
        tablaLibros.setModel(modeloTabla);
        
        //Se pasa la cabecera de la tabla
        tablaCabecera = tablaLibros.getTableHeader();
        
        //Scroll de la tabla
        scrollTabla = new JScrollPane(tablaLibros);
        scrollTabla.setLocation(25, 80);
        scrollTabla.setSize(550, 340);
        scrollTabla.setBorder(null);
        
        this.add(scrollTabla);
    }
    
    
}
