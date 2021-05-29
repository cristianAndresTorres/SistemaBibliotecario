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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import logica.Libro;
import logica.Persona;

/**
 *
 * @author 57301
 */
public class ActualizaUsuario extends JPanel implements ActionListener, MouseListener{
    //Atributos
    //Diseño de la ventana
    private ObjGraficosServicio objetoGrafico;
    private ObjDecoradorServicio objetoDecorador;
    private JLabel jlid;
    private JTextField jtxtID;
    private JButton jbtnBuscar;
    private Tabla disegnoTabla = new Tabla();
    
     //Declaración de objetos necesarios para una tabla
    private JScrollPane scrollTabla;
    protected JTable tablaPersona;
    private JTableHeader tablaCabecera;
    protected DefaultTableModel modeloTabla;
    private String [] cabecera = {"Documento","Tipo_usuario","Pri_nombre","Seg_nombre", "Pri_apellido", "Seg_apillo", "Teléfono","Estado_usuario","Modificar"};
    private JLabel jl_titulo;
    protected JButton jtbtn;
    protected PanelOpcionesUsuario panelOpcionesUsuario;
    protected BDAcceso bdAcceso; 
    
    
    public ActualizaUsuario(){
    //Diseño del panel
        objetoDecorador = ObjDecoradorServicio.obtenerServicio();
        objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        bdAcceso = BDAcceso.crearAcceso();
        this.panelOpcionesUsuario = panelOpcionesUsuario;
        
        //Diseño de ventana
        this.setSize(875, 450);
        this.setBackground(objetoDecorador.getColorGrisClaro());
        this.setLayout(null);
	this.setVisible(true);
        
        jl_titulo = objetoGrafico.crearJLabel("Actualizar información usuario",200, 20, 400, 50, objetoDecorador.getFontTprincipal(), Color.BLACK, objetoDecorador.getcMano());
        jtbtn = objetoGrafico.crearBoton("Solicitar", 0, 0, 40, 30, Color.BLACK, new Color(82, 190, 128), objetoDecorador.getcMano(), null);
        jtbtn.addActionListener(this);
        this.add(jl_titulo);
        
        this.crearTabla();
        
}

  
    
    public void crearTabla(){
        //Interactuar con la tabla
        modeloTabla = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                if( column==2 || column==3 ||column==4 ||column==5 ||column==6||column==7){
                    return true;
                }
                return false;
            }
        };
        //Cabecera de la tabla
        modeloTabla.setColumnIdentifiers(cabecera);
        //creación de la tabla
        tablaPersona = new JTable();
        //Se le pasa el modelo a la tabla
        tablaPersona.setModel(modeloTabla);
        
        //Se pasa la cabecera de la tabla
        tablaCabecera = tablaPersona.getTableHeader();
        
        //Scroll de la tabla
        scrollTabla = new JScrollPane(tablaPersona);
        scrollTabla.setLocation(25, 80);
        scrollTabla.setSize(825, 300);
        scrollTabla.setBorder(null);
        //Diseño
        tablaPersona.setBackground(new Color(213, 245, 227));
        tablaPersona.setRowHeight(40);
        
        tablaPersona.addMouseListener(this);
        
        this.add(scrollTabla);
        
        jlid = objetoGrafico.crearJLabel("Ingrese El numero de identificacion",25, 390, 300, 20, objetoDecorador.getFontOpc(), Color.BLACK, objetoDecorador.getcMano());
        this.add(jlid);
        
        jtxtID = objetoGrafico.crearJTextField("",80, 415, 170, 30, Color.WHITE, objetoDecorador.getColorAzulOscuro(), Color.BLUE);
        this.add(jtxtID);
        
        jbtnBuscar = objetoGrafico.crearBoton("Buscar", 680, 400, 150, 50, Color.BLACK, new Color(82, 190, 128), objetoDecorador.getcMano(), null);
        jbtnBuscar.addActionListener(this);
        this.add(jbtnBuscar);
        
    }    
    //--------------------------------------------------------------------------
    //Getters and getters
    //--------------------------------------------------------------------------
    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    
    
    
    
    
    //--------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==this.jbtnBuscar){
            
            try{
                int valor=Integer.parseInt(this.jtxtID.getText());
                
                Persona p= this.bdAcceso.getBdConsulta().obtenerUsuGeneral(""+valor);
                if(p==null){
                     JOptionPane.showMessageDialog(null, "El usuario no existe");
                }else{
                   
                    int filas2 = modeloTabla.getRowCount();
                    int acumulador = 0;
            
                    while(filas2>acumulador){
                        modeloTabla.removeRow(0);
                        acumulador++;
                    }
                    //Se renderiza la tabla para poder agregar botones
                    tablaPersona.setDefaultRenderer(Object.class, disegnoTabla);
                         
                    //Se actualiza la información de la tabla
            
                        //Se agregan los elementos a la fila
                
                     modeloTabla.addRow(new Object[]{p.getID_Documento(),p.getTipoUsuario(),p.getPri_nombre(),p.getSeg_nombre(),
                                                                            p.getPri_apellido(),p.getSeg_apellido(),p.getTel_contacto(),p.getTipo_doc(),jtbtn});
                           
                            
                }
           
            }catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "Ingreso no valido");
            }
            
        }
        
    }
    
    //-------------------------------------------------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {
        //Posicion x
        int columna = tablaPersona.getColumnModel().getColumnIndexAtX(e.getX());
        //Posicion y
        int fila = e.getY()/tablaPersona.getRowHeight();
        
        //Dimensione de la tabla
        if(fila<tablaPersona.getRowCount()&&fila>=0 && columna>=0 && columna<tablaPersona.getColumnCount()){
             //Objeto de la tabla
              //filas horizontales
             // columna 0-6
            Object miObjeto = tablaPersona.getValueAt(fila, columna);
            //Si el objeto es un boton
            if(miObjeto instanceof JButton){
                System.out.println("/nx"+columna+"Y"+fila);
                Persona p= new Persona((String)tablaPersona.getValueAt(fila, 0),(String)tablaPersona.getValueAt(fila, 2),(String)tablaPersona.getValueAt(fila, 3),
                        (String)tablaPersona.getValueAt(fila, 4),(String)tablaPersona.getValueAt(fila, 5),(String)tablaPersona.getValueAt(fila, 6),
                       (String)tablaPersona.getValueAt(fila, 7),(String)tablaPersona.getValueAt(fila, 1));  
                
                if(this.bdAcceso.getBdModifica().modificar_Persona(p)){
                    JOptionPane.showMessageDialog(null, "Ingreso Exitoso");
                }else{
                    
                    JOptionPane.showMessageDialog(null, "Problemas en la actualizacion");
                }
                
                
            }
        }
        
        
    
        
        
    
    }

    
    
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
