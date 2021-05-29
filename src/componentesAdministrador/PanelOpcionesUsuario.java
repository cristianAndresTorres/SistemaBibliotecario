/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesAdministrador;

import conexionBD.BDAcceso;
import controladorUsuarios.ControladorAdministrador;
import disegnoComponentes.ObjDecoradorServicio;
import disegnoComponentes.ObjGraficosServicio;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import logica.Libro;
import logica.Persona;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author 57301
 */
public class PanelOpcionesUsuario extends JPanel implements ActionListener{
    //Atributos
    //Diseño de los componentes
    private ObjDecoradorServicio objetoDecorador;
    private ObjGraficosServicio objetoGrafico;
    
    //Controlador del administrador
    private ControladorAdministrador controladorAdministrador;
    
    //botones de la ventana
    private JButton jbtn_Crear_libro,jbtn_Crear_usuario, jbtn_re_prestamo, jbtn_dev_libro, 
                        jbtn_ac_libro, jbtn_ac_usu, jbtn_con_usu, jbtn_con_libros, jbtn_salir;
    
    //Paneles 
    private PanelRegistroLibro panelRegistroLibro = new PanelRegistroLibro();
    private PanelRegistroUsuario panelRegistroUsuario = new PanelRegistroUsuario();
    private ActulizaLibros actulizaLibros;
    private ActualizaUsuario actualizaUsuario = new ActualizaUsuario(); 
    private PanelRealizaPrestamo realizaPrestamo = new PanelRealizaPrestamo();
    private PanelDevolucionLibro devolucionLibro = new PanelDevolucionLibro();
    
    private BDAcceso bdAcceso = BDAcceso.crearAcceso();
    private ArrayList<Libro>listaLibros;
    private ArrayList<Persona>listaUsuarios;
    private Libro libro;
    private Persona p;
    private Tabla disegnoTabla = new Tabla();

    public PanelOpcionesUsuario(ControladorAdministrador controladorAdministrador){
        this.controladorAdministrador = controladorAdministrador;
        actulizaLibros = new ActulizaLibros(this);
        //Propiedades del panel
         //Diseño del panel
        this.objetoDecorador = ObjDecoradorServicio.obtenerServicio();
        this.objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        
        //Caracteristicas de la ventana
	this.setSize(250, 450);
	this.setBackground(objetoDecorador.getColorVerdeClaro());
	this.setLayout(null);
	this.setVisible(true);
        
        this.crearLibros();
    }
    
    public void crearLibros(){
        jbtn_Crear_libro = objetoGrafico.crearBoton("Nuevo libro", 25, 100, 200, 40, Color.BLACK, objetoDecorador.getColorAzulOscuro(), objetoDecorador.getcMano(), null);
        jbtn_Crear_libro.addActionListener(this);
        this.add(jbtn_Crear_libro);
        
        jbtn_Crear_usuario = objetoGrafico.crearBoton("Nuevo usuario", 25, 150, 200, 40, Color.BLACK, objetoDecorador.getColorAzulOscuro(), objetoDecorador.getcMano(), null);
        jbtn_Crear_usuario.addActionListener(this);
        this.add(jbtn_Crear_usuario);
        
        jbtn_ac_libro = objetoGrafico.crearBoton("Usuarios del Sistema", 25, 200, 200, 40, Color.BLACK, objetoDecorador.getColorAzulOscuro(), objetoDecorador.getcMano(), null);
        jbtn_ac_libro.addActionListener(this);
        this.add(jbtn_ac_libro);
        
        jbtn_ac_usu = objetoGrafico.crearBoton("Catálogo bibliotecario", 25, 250, 200, 40, Color.BLACK, objetoDecorador.getColorAzulOscuro(), objetoDecorador.getcMano(), null);
        jbtn_ac_usu.addActionListener(this);
        this.add(jbtn_ac_usu);
        
        
        jbtn_re_prestamo = objetoGrafico.crearBoton("Realizar prestamo", 25, 300, 200, 40, Color.BLACK, objetoDecorador.getColorAzulOscuro(), objetoDecorador.getcMano(), null);
        jbtn_re_prestamo.addActionListener(this);
        this.add(jbtn_re_prestamo);
        
        jbtn_dev_libro = objetoGrafico.crearBoton("Devolver prestamo", 25, 350, 200, 40, Color.BLACK, objetoDecorador.getColorAzulOscuro(), objetoDecorador.getcMano(), null);
        jbtn_dev_libro.addActionListener(this);
        this.add(jbtn_dev_libro);
        
        jbtn_salir = objetoGrafico.crearBoton("Finalizar sesión", 25, 400, 200, 40, Color.BLACK, objetoDecorador.getColorAzulOscuro(), objetoDecorador.getcMano(), null);
        jbtn_salir.addActionListener(this);
        this.add(jbtn_salir);
    }
    
    
    
    
    
    //Oyente de la clase
    @Override
    public void actionPerformed(ActionEvent e) {
        //Crear libro------------------------------------------------------------------------------------------------------
        if(e.getSource()==jbtn_Crear_libro){
            controladorAdministrador.getControladorVistaInicial().limpiarPaneles();
            controladorAdministrador.getControladorVistaInicial().cambiarVisibilidad(1);
            
            controladorAdministrador.getControladorVistaInicial().getVistaInicial().getJpCentral().add(panelRegistroLibro);
            controladorAdministrador.getControladorVistaInicial().actualizarPaneles();
            //Se agrega el nuevo panel
        //Crear usuario---------------------------------------------------------------------------------------------------
        }else if(e.getSource()==jbtn_Crear_usuario){
            controladorAdministrador.getControladorVistaInicial().limpiarPaneles();
            controladorAdministrador.getControladorVistaInicial().cambiarVisibilidad(1);
            
            controladorAdministrador.getControladorVistaInicial().getVistaInicial().getJpCentral().add(panelRegistroUsuario);
            controladorAdministrador.getControladorVistaInicial().actualizarPaneles();
        //Actualiza libro--------------------------------------------------------------------------------------------------
        }else if(e.getSource()==jbtn_ac_usu){
            controladorAdministrador.getControladorVistaInicial().limpiarPaneles();
            controladorAdministrador.getControladorVistaInicial().cambiarVisibilidad(2);
            
            
            //------------------------------------------------------------------
            //Se obtiene toda la lista de prestamos con base en la consulta sql
            listaLibros = bdAcceso.getBdConsulta().obtenerTodosLibros("%", "%", "%", "%");
            
           //Se limpia las filas de la tabla
            acumulador = 0;
             filas1 = actulizaLibros.getModeloTabla().getRowCount();
            
            while(filas1>acumulador){
                actulizaLibros.getModeloTabla().removeRow(0);
                acumulador++;
            }
            //Se renderiza la tabla para poder agregar botones
            actulizaLibros.tablaLibros.setDefaultRenderer(Object.class, disegnoTabla);
            
            acumulador=0;                
            //Se actualiza la información de la tabla
            while(acumulador<listaLibros.size()){
                libro = listaLibros.get(acumulador);
                //Se agregan los elementos a la fila
                if(libro.isEstadoLibro()==true){  
                    actulizaLibros.getModeloTabla().addRow(new Object[]{libro.getId_libro(),libro.getId_ejemplar(),libro.getNombreLibro(),libro.isEstadoLibro(),
                                                                            libro.getAutor(),libro.getCategoria(),libro.getEditorial(),actulizaLibros.jtbtn});
                }else{
                    actulizaLibros.getModeloTabla().addRow(new Object[]{libro.getId_libro(),libro.getId_ejemplar(),libro.getNombreLibro(),libro.isEstadoLibro(),
                                                                            libro.getAutor(),libro.getCategoria(),libro.getEditorial()});
                }
                acumulador++;
           }
           
            
            
            //--------------------------------------------------------------------------------------
            controladorAdministrador.getControladorVistaInicial().getVistaInicial().getJpCentralGeneral().add(actulizaLibros);
            controladorAdministrador.getControladorVistaInicial().actualizarPaneles();
        //Actualiza usuario    
        }else if(e.getSource()==jbtn_ac_libro){
            controladorAdministrador.getControladorVistaInicial().limpiarPaneles();
            controladorAdministrador.getControladorVistaInicial().cambiarVisibilidad(2);
            
            //---------------------------------------------------------------------------
            
            //Se obtiene toda la lista de usuarios con base en la consulta sql
            listaUsuarios = bdAcceso.getBdConsulta().obtenerUsuarios();
            
           //Se limpia las filas de la tabla
            acumulador = 0;
            filas2 = actualizaUsuario.getModeloTabla().getRowCount();
            
            while(filas2>acumulador){
                actualizaUsuario.getModeloTabla().removeRow(0);
                acumulador++;
            }
            //Se renderiza la tabla para poder agregar botones
            actualizaUsuario.tablaPersona.setDefaultRenderer(Object.class, disegnoTabla);
            
            acumulador=0;                
            //Se actualiza la información de la tabla
            
            while(acumulador<listaUsuarios.size()){
                p = listaUsuarios.get(acumulador);
                //Se agregan los elementos a la fila
                
                    actualizaUsuario.getModeloTabla().addRow(new Object[]{p.getID_Documento(),p.getTipoUsuario(),p.getPri_nombre(),p.getSeg_nombre(),
                                                                            p.getPri_apellido(),p.getSeg_apellido(),p.getTel_contacto(),p.getTipo_doc(),actualizaUsuario.jtbtn});
                    System.out.println(""+p.getPri_nombre());
                acumulador++;
           }
            
            
            //----------------------------------------------------------------------------
            controladorAdministrador.getControladorVistaInicial().getVistaInicial().getJpCentralGeneral().add(actualizaUsuario);
            controladorAdministrador.getControladorVistaInicial().actualizarPaneles();
        
        }else if(e.getSource()==jbtn_re_prestamo){
            
            controladorAdministrador.getControladorVistaInicial().limpiarPaneles();
            controladorAdministrador.getControladorVistaInicial().cambiarVisibilidad(1);
            
            controladorAdministrador.getControladorVistaInicial().getVistaInicial().getJpCentral().add(realizaPrestamo);
            controladorAdministrador.getControladorVistaInicial().actualizarPaneles();
            //Se agrega el nuevo panel
            
            
        }else if(e.getSource()==jbtn_dev_libro){
            
            controladorAdministrador.getControladorVistaInicial().limpiarPaneles();
            controladorAdministrador.getControladorVistaInicial().cambiarVisibilidad(1);
            
            controladorAdministrador.getControladorVistaInicial().getVistaInicial().getJpCentral().add(devolucionLibro);
            controladorAdministrador.getControladorVistaInicial().actualizarPaneles();
            //Se agrega el nuevo panel
            
            
        }else if(e.getSource()==jbtn_salir){
            
            controladorAdministrador.getControladorVistaInicial().cambiarVisibilidad(1);
            controladorAdministrador.getControladorVistaInicial().reestablecerInfo();
            controladorAdministrador.getControladorVistaInicial().agregarPaneles();
            
        }
        
        
    }

    

    
    
    
    
    
    
    
    
    
    
   
    
    private int acumulador, filas1=0,filas2=0;
    
}
