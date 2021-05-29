/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesUsuarioGeneral;

import disegnoComponentes.ObjDecoradorServicio;
import disegnoComponentes.ObjGraficosServicio;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author 57301
 */
public class PanelRegistroPrestamo extends JPanel{
    //Atributos
    //Diseño de la ventana
    private ObjGraficosServicio objetoGrafico;
    private ObjDecoradorServicio objetoDecorador;
    
    
    //Declaración de objetos necesarios para una tabla
    private JScrollPane scrollTabla;
    protected JTable tablaPrestamo;
    private JTableHeader tablaCabecera;
    private DefaultTableModel modeloTabla;
    private String [] cabecera = {"ID_Prestamo","ID_Libro","Ejemplar", "Título del libro", "Fecha del prestamo", "Fecha devolución", "Estado","Ampliar prestamo","Ampliar devolución"};
    private JLabel jl_titulo;
    protected JButton jtbtn;
    
    private PanelRegistroPrestamoControlador panelRegistroPrestamoControlador;
    
    
    public PanelRegistroPrestamo(PanelRegistroPrestamoControlador panelRegistroPrestamoControlador){        
        //Diseño del panel
        objetoDecorador = ObjDecoradorServicio.obtenerServicio();
        objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        
        this.panelRegistroPrestamoControlador = panelRegistroPrestamoControlador;
        
        //Diseño de ventana
        this.setSize(875, 450);
        this.setBackground(objetoDecorador.getColorGrisClaro());
        this.setLayout(null);
	this.setVisible(true);
        
        jl_titulo = objetoGrafico.crearJLabel("Prestamos",200, 20, 400, 50, objetoDecorador.getFontTprincipal(), Color.BLACK, objetoDecorador.getcMano());
        jtbtn = objetoGrafico.crearBoton("Solicitar", 0, 0, 40, 30, Color.BLACK, new Color(82, 190, 128), objetoDecorador.getcMano(), null);
        this.add(jl_titulo);
        
        this.crearTabla();
    }
    
    public void crearTabla(){
        //Interactuar con la tabla
        modeloTabla = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        //Cabecera de la tabla
        modeloTabla.setColumnIdentifiers(cabecera);
        //creación de la tabla
        tablaPrestamo = new JTable();
        //Se le pasa el modelo a la tabla
        tablaPrestamo.setModel(modeloTabla);
        
        //Se pasa la cabecera de la tabla
        tablaCabecera = tablaPrestamo.getTableHeader();
        
        //Scroll de la tabla
        scrollTabla = new JScrollPane(tablaPrestamo);
        scrollTabla.setLocation(25, 80);
        scrollTabla.setSize(825, 300);
        scrollTabla.setBorder(null);
        //Diseño
        tablaPrestamo.setBackground(new Color(213, 245, 227));
        tablaPrestamo.setRowHeight(40);
        
        tablaPrestamo.addMouseListener(panelRegistroPrestamoControlador);
        
        this.add(scrollTabla);
    }    
    //--------------------------------------------------------------------------
    //Getters and getters
    //--------------------------------------------------------------------------
    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
    
    
    
}
