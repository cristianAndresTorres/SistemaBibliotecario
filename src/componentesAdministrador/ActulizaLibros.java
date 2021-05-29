/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesAdministrador;

import conexionBD.BDAcceso;
import disegnoComponentes.ObjDecoradorServicio;
import disegnoComponentes.ObjGraficosServicio;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import logica.Libro;

/**
 *
 * @author 57301
 */
public class ActulizaLibros extends JPanel implements MouseListener, ActionListener{
    //Atributos
    //Diseño de la ventana
    private ObjGraficosServicio objetoGrafico;
    private ObjDecoradorServicio objetoDecorador;
    
    
    //Declaración de objetos necesarios para una tabla
    private JScrollPane scrollTabla;
    protected JTable tablaLibros;
    private JTableHeader tablaCabecera;
    protected DefaultTableModel modeloTabla;
    private String [] cabecera = {"ID_Libro","ID_Ejemplar","Nombre del libro", "Estado del libro", "Autor", "Categoría", "Editorial","Modificar"};
    private JLabel jl_titulo;
    protected JButton jtbtn;
    protected PanelOpcionesUsuario panelOpcionesUsuario;
    
    
    
    //componentes de la ventana
    protected JLabel jlLibro, jlAutor, jlCategoria, jlEditorial;
    protected JTextField jtxtNombre, jtxtAutor, jtxtCategoria, jtxtEditorial;
    protected JButton jbtnBuscar;
    protected JComboBox jcbxAutores,jcbxEditoriales, jcbxCategorias;
    
    //Conexión a la base de datos.
    protected BDAcceso bdAcceso;
    protected ArrayList<String>listaDatos;
    
    
    public ActulizaLibros(PanelOpcionesUsuario panelOpcionesUsuario){
        //Diseño del panel
        objetoDecorador = ObjDecoradorServicio.obtenerServicio();
        objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        
        //conexion a la base de datos
        bdAcceso = BDAcceso.crearAcceso();
        
        this.panelOpcionesUsuario = panelOpcionesUsuario;
        
        //Diseño de ventana
        this.setSize(875, 450);
        this.setBackground(objetoDecorador.getColorGrisClaro());
        this.setLayout(null);
	this.setVisible(true);
        
        jl_titulo = objetoGrafico.crearJLabel("Actualizar libros",200, 20, 400, 50, objetoDecorador.getFontTprincipal(), Color.BLACK, objetoDecorador.getcMano());
        jtbtn = objetoGrafico.crearBoton("Solicitar", 0, 0, 40, 30, Color.BLACK, new Color(82, 190, 128), objetoDecorador.getcMano(), null);
        jtbtn.addActionListener(this);
        this.add(jl_titulo);
        
        this.crearTabla();
        
        this.componentePanel();
        this.disegnarCombo();
        this.menusDesplegables();
    }
    
    public void crearTabla(){
        //Interactuar con la tabla
        modeloTabla = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                if( column==4 || column==5 ||column==6){
                    return true;
                }
                return false;
            }
        };
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
        scrollTabla.setSize(825, 300);
        scrollTabla.setBorder(null);
        //Diseño
        tablaLibros.setBackground(new Color(213, 245, 227));
        tablaLibros.setRowHeight(40);
        
        tablaLibros.addMouseListener(this);
        
        this.add(scrollTabla);
    }    
    //--------------------------------------------------------------------------
    //Getters and getters
    //--------------------------------------------------------------------------
    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Posicion x
        int columna = tablaLibros.getColumnModel().getColumnIndexAtX(e.getX());
        //Posicion y
        int fila = e.getY()/tablaLibros.getRowHeight();
        
        //Dimensione de la tabla
        if(fila<tablaLibros.getRowCount()&&fila>=0 && columna>=0 && columna<tablaLibros.getColumnCount()){
             //Objeto de la tabla
              //filas horizontales
             // columna 0-6
            Object miObjeto = tablaLibros.getValueAt(fila, columna);
            //Si el objeto es un boton
            if(miObjeto instanceof JButton){
                System.out.println("/nx"+columna+"Y"+fila);
                Libro libro;
                
              //  (String autor 4, String categoria 5, String editorial 6, boolean estadoLibro  3, 
               //     int id_ejemplar  1, String id_libro  0 , String nombreLibro 2)
                libro = new Libro((String)tablaLibros.getValueAt(fila, 4),(String)tablaLibros.getValueAt(fila, 5),
                        (String)tablaLibros.getValueAt(fila, 6),(boolean)tablaLibros.getValueAt(fila, 3),
                        (int)tablaLibros.getValueAt(fila, 1),(String)tablaLibros.getValueAt(fila, 0),
                        (String)tablaLibros.getValueAt(fila, 2));
                System.out.println("ID: "+libro.getId_libro()+" Nombre: "+libro.getNombreLibro()+" Ejemplar: "+libro.getId_ejemplar()+" Autor: "+libro.getAutor()+
                        " Categoria:"+ libro.getCategoria()+" Editoria:"+libro.getEditorial());
                
                if(this.bdAcceso.getBdModifica().modificar_Libro(libro)){
                    JOptionPane.showMessageDialog(null, "Ingreso Exitoso");
                }else{
                    
                    JOptionPane.showMessageDialog(null, "Problemas en la actualizacion - Datos no validos");
                }
                
                
            }
        }
        
        
        
    }
    
    //Metodos
    public void componentePanel(){
        
        jlLibro = objetoGrafico.crearJLabel("Ingrese el nombre del libro",25, 390, 170, 20, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        this.add(jlLibro);
        
        jtxtNombre = objetoGrafico.crearJTextField("Nombre del libro",30, 415, 170, 30, Color.WHITE, objetoDecorador.getColorAzulOscuro(), Color.BLUE);
        this.add(jtxtNombre);
        
        jlAutor = objetoGrafico.crearJLabel("Ingrese el nombre del autor",215, 390, 170, 20, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        this.add(jlAutor);
        
        jlCategoria = objetoGrafico.crearJLabel("Ingrese la categoría",400, 390, 120, 20, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        this.add(jlCategoria);
        
        jlEditorial = objetoGrafico.crearJLabel("Ingrese la editorial",540, 390, 120, 20, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        this.add(jlEditorial);
        
        jbtnBuscar = objetoGrafico.crearBoton("Filtrar información", 680, 400, 150, 50, Color.BLACK, new Color(82, 190, 128), objetoDecorador.getcMano(), null);
        jbtnBuscar.addActionListener(this);
        this.add(jbtnBuscar);
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
        jcbxAutores.setLocation(215, 415);
        jcbxAutores.setSize(170, 30);
        jcbxAutores.setBorder(null);
        jcbxAutores.setBackground(Color.WHITE);
        //----------------------------------------------------------------------
        jcbxCategorias = new JComboBox();
        jcbxCategorias.setLocation(400, 415);
        jcbxCategorias.setSize(120, 30);
        jcbxCategorias.setBorder(null);
        jcbxCategorias.setBackground(Color.WHITE);
        //----------------------------------------------------------------------
        jcbxEditoriales = new JComboBox();
        jcbxEditoriales.setLocation(540, 415);
        jcbxEditoriales.setSize(120, 30);
        jcbxEditoriales.setBorder(null);
        jcbxEditoriales.setBackground(Color.WHITE);
    }
    

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==this.jbtnBuscar){
            
            String nombre=""+this.jtxtNombre.getText();
            String autor=(String) this.jcbxAutores.getSelectedItem();
            String categoria=(String) this.jcbxCategorias.getSelectedItem();
            String editorial=(String) this.jcbxEditoriales.getSelectedItem();
            if(nombre.equals("Nombre del libro")||nombre.equals("")){
                nombre="%";
            }
            if(autor.equals("1 No aplica")){
                autor="%";
            }
            if(categoria.equals("1 No aplica")){
               categoria="%"; 
            }
            if(editorial.equals("1 No aplica")){
                editorial="%";
            }  
            ArrayList<Libro> listaLibros = bdAcceso.getBdConsulta().obtenerTodosLibros(nombre, autor, categoria, editorial);
            int acumulador = 0;
     
            //Se limpia las filas de la tabla  
            int filas=this.modeloTabla.getRowCount();
            while(filas>acumulador){
                modeloTabla.removeRow(0);
                acumulador++;
            }
            acumulador = 0;
            while(listaLibros.size()>acumulador){
                modeloTabla.addRow(new Object[]{listaLibros.get(acumulador).getId_libro(),listaLibros.get(acumulador).getId_ejemplar(),listaLibros.get(acumulador).getNombreLibro(),listaLibros.get(acumulador).isEstadoLibro(),
                                                                            listaLibros.get(acumulador).getAutor(),listaLibros.get(acumulador).getCategoria(),listaLibros.get(acumulador).getEditorial(),jtbtn});                                                            
                acumulador++;
            }
            filas = listaLibros.size();
        }
        
        
    }

    
}
