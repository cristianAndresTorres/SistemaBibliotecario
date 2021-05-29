/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesVistaInicial;

import conexionBD.BDAcceso;
import disegnoComponentes.ObjDecoradorServicio;
import disegnoComponentes.ObjGraficosServicio;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author 57301
 */
public class PanelBusqueda extends JPanel{
    //componentes de la ventana
    protected JLabel jlTitulo, jlLibro, jlAutor, jlCategoria, jlEditorial;
    protected JTextField jtxtNombre, jtxtAutor, jtxtCategoria, jtxtEditorial;
    protected JButton jbtnLimpiar, jbtnBuscar;
    
    protected JComboBox jcbxAutores,jcbxEditoriales, jcbxCategorias ;
    
    //Diseño de la ventana
    private ObjGraficosServicio objetoGrafico;
    private ObjDecoradorServicio objetoDecorador;
    
    private PanelBusquedaControlador oyentePBControlador;
    
    //Conexión a la base de datos.
    protected BDAcceso bdAcceso;
    protected ArrayList<String>listaDatos;
    
    
    //Constructor
    public PanelBusqueda(PanelBusquedaControlador oyentePBControlador){
        //Diseño del panel
        objetoDecorador = ObjDecoradorServicio.obtenerServicio();
        objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        //conexion a la base de datos
        bdAcceso = BDAcceso.crearAcceso();
        
        this.oyentePBControlador = oyentePBControlador;
        
        this.setSize(250, 450);
        this.setBackground(objetoDecorador.getColorVerdeClaro());
        this.setLayout(null);
	this.setVisible(true);
        
        this.componentePanel();
        this.disegnarCombo();
        this.menusDesplegables();
        
        
    }
    //Metodos
    public void componentePanel(){
        //Etiquetas
        jlTitulo = objetoGrafico.crearJLabel("Busqueda catálogo libros", 25, 25, 200, 50, objetoDecorador.getFontTprincipal(), objetoDecorador.getColorGrisOscuro(), objetoDecorador.getcMano());
        this.add(jlTitulo);
        
        jlLibro = objetoGrafico.crearJLabel("Ingrese el nombre del libro", 0, 75, 250, 50, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        this.add(jlLibro);
        
        jtxtNombre = objetoGrafico.crearJTextField("Nombre del libro",50, 115, 150, 35, Color.WHITE, objetoDecorador.getColorAzulOscuro(), Color.BLUE);
        this.add(jtxtNombre);
        
        jlAutor = objetoGrafico.crearJLabel("Ingrese el nombre del autor", 0, 145, 250, 50, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        this.add(jlAutor);
        
        jlCategoria = objetoGrafico.crearJLabel("Ingrese la categoría", 0, 215, 250, 50, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        this.add(jlCategoria);
        
        jlEditorial = objetoGrafico.crearJLabel("Ingrese la editorial", 0, 280, 250, 50, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        this.add(jlEditorial);
        
        jbtnBuscar = objetoGrafico.crearBoton("Buscar", 25, 380, 100, 50, Color.BLACK, new Color(82, 190, 128), objetoDecorador.getcMano(), null);
        jbtnBuscar.addActionListener(oyentePBControlador);
        this.add(jbtnBuscar);
        
        jbtnLimpiar = objetoGrafico.crearBoton("Limpiar", 130, 380, 100, 50, Color.BLACK, new Color(244, 208, 63), objetoDecorador.getcMano(), null);
        jbtnLimpiar.addActionListener(oyentePBControlador);
        this.add(jbtnLimpiar);
    }
    public void menusDesplegables(){
        int contador=0;
        //----------------------------------------------------------------------
        jcbxAutores.removeAllItems();
        listaDatos = bdAcceso.getBdConsulta().obtenerAutores();
        while(contador<listaDatos.size()){
            jcbxAutores.addItem(listaDatos.get(contador));
            contador++;
        }
        //----------------------------------------------------------------------
        jcbxCategorias.removeAllItems();
        listaDatos.removeAll(listaDatos);
        contador = 0;
        listaDatos = bdAcceso.getBdConsulta().obtenerCategorias();
        while(contador<listaDatos.size()){
            jcbxCategorias.addItem(listaDatos.get(contador));
            contador++;
        }
        //----------------------------------------------------------------------        
        jcbxEditoriales.removeAllItems();
        listaDatos.removeAll(listaDatos);
        contador = 0;
        listaDatos = bdAcceso.getBdConsulta().obtenerEditorial();
        while(contador<listaDatos.size()){
            jcbxEditoriales.addItem(listaDatos.get(contador));
            contador++;
        }
        this.add(jcbxAutores);
        this.add(jcbxCategorias);
        this.add(jcbxEditoriales);
    }
    
    public void disegnarCombo(){
        //----------------------------------------------------------------------
        jcbxAutores = new JComboBox();
        jcbxAutores.setLocation(50, 185);
        jcbxAutores.setSize(150, 35);
        jcbxAutores.setBorder(null);
        jcbxAutores.setBackground(Color.WHITE);
        //----------------------------------------------------------------------
        jcbxCategorias = new JComboBox();
        jcbxCategorias.setLocation(50, 255);
        jcbxCategorias.setSize(150, 35);
        jcbxCategorias.setBorder(null);
        jcbxCategorias.setBackground(Color.WHITE);
        //----------------------------------------------------------------------
        jcbxEditoriales = new JComboBox();
        jcbxEditoriales.setLocation(50, 325);
        jcbxEditoriales.setSize(150, 35);
        jcbxEditoriales.setBorder(null);
        jcbxEditoriales.setBackground(Color.WHITE);
    }
    
}
