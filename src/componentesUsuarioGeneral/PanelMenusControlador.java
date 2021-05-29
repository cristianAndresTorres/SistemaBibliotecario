/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesUsuarioGeneral;

import conexionBD.BDAcceso;
import controladorUsuarios.ControladorUsuario;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import logica.Prestamo;

/**
 *
 * @author 57301
 */
public class PanelMenusControlador extends DefaultTableCellRenderer implements ActionListener{
    //Atributo
    private PanelMenus panelMenus;
    private ControladorUsuario controladorUsuario;
    private PanelDatosPersonal panelDatosPersonal;
    private PanelRegistroPrestamoControlador panelRegistroPrestamoControlador = new PanelRegistroPrestamoControlador();
    //Conexión a la base de datos
    private BDAcceso bdAcceso = BDAcceso.crearAcceso();
    private ArrayList<Prestamo>listaPrestamos;
    private Prestamo prestamo;
    
    //constructor
    public PanelMenusControlador(ControladorUsuario controladorUsuario){
        this.controladorUsuario = controladorUsuario;
        this.panelMenus = new PanelMenus(this);
        panelDatosPersonal = new PanelDatosPersonal();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Se limpia el panel
        controladorUsuario.getControladorVistaInicial().limpiarPaneles();
        
        //Información del usuario
        if(e.getSource() == panelMenus.jbtnInfoPer){
            //CambiarVisibilidad
            controladorUsuario.getControladorVistaInicial().cambiarVisibilidad(1);
            //Se actualiza el panel
            panelDatosPersonal.actualizarInfo(controladorUsuario.getMiUsuario());
            controladorUsuario.getControladorVistaInicial().getVistaInicial().getJpCentral().add(panelDatosPersonal);
            //Se muestran los cambios
            controladorUsuario.getControladorVistaInicial().actualizarPaneles();
            
            //------------------------------------------------------------------
            //Catálogo de ibros
        }else if(e.getSource()==panelMenus.jbtnCatalogoLibros){
            
            //CambiarVisibilidad
            controladorUsuario.getControladorVistaInicial().cambiarVisibilidad(1);
            controladorUsuario.getControladorVistaInicial().getVistaInicial().getJpCentral().add(controladorUsuario.getPanelLibro());
            controladorUsuario.getControladorVistaInicial().getVistaInicial().getJpDerecha().add(controladorUsuario.getPanelBusqueda());
            //Se muestran los cambios
            controladorUsuario.getControladorVistaInicial().actualizarPaneles();
            //------------------------------------------------------------------
            //Salir del aplicativo
        }else if(e.getSource()==panelMenus.jbtnPrestamos){
            //------------------------------------------------------------------
            //Se obtiene toda la lista de prestamos con base en la consulta sql
            listaPrestamos = bdAcceso.getBdConsulta().consultarPrestamo(controladorUsuario.getMiUsuario().getID_Documento());
            
           //Se limpia las filas de la tabla
            acumulador = 0;
            filas = panelRegistroPrestamoControlador.getModeloTabla().getRowCount();
            while(filas>acumulador){
                panelRegistroPrestamoControlador.getModeloTabla().removeRow(0);
                acumulador++;
            }
            //Se renderiza la tabla para poder agregar botones
            panelRegistroPrestamoControlador.panelRegistroPrestamo.tablaPrestamo.setDefaultRenderer(Object.class, this);
            
            acumulador=0;                
            //Se actualiza la información de la tabla
            while(acumulador<listaPrestamos.size()){
                prestamo = listaPrestamos.get(acumulador);
                //Se agregan los elementos a la fila
                if(prestamo.isAmpliarPrestamo()==false){                   
                    panelRegistroPrestamoControlador.getModeloTabla().addRow(new Object[]{prestamo.getId_Prestamo(),prestamo.getId_Libro(),prestamo.getId_Ejemplar(),prestamo.getTitulo_libro(),
                                                                            prestamo.getFecha_prestamo(),prestamo.getFecha_devolucion(),prestamo.isEstado(),prestamo.isAmpliarPrestamo(),panelRegistroPrestamoControlador.retornaJbtn()});
                }else{
                    panelRegistroPrestamoControlador.getModeloTabla().addRow(new Object[]{prestamo.getId_Prestamo(),prestamo.getId_Libro(),prestamo.getId_Ejemplar(),prestamo.getTitulo_libro(),
                                                                            prestamo.getFecha_prestamo(),prestamo.getFecha_devolucion(),prestamo.isEstado(),prestamo.isAmpliarPrestamo()});
                }
                acumulador++;
           }
            filas = acumulador;
            //CambiarVisibilidad
            controladorUsuario.getControladorVistaInicial().cambiarVisibilidad(2);
            //Se agrega el panel
            controladorUsuario.getControladorVistaInicial().getVistaInicial().getJpCentralGeneral().add(panelRegistroPrestamoControlador.panelRegistroPrestamo);
            //Se muestran los cambios
            controladorUsuario.getControladorVistaInicial().actualizarPaneles();
            
        }else if(e.getSource() == panelMenus.jbtnSalir){
            //CambiarVisibilidad
            controladorUsuario.getControladorVistaInicial().cambiarVisibilidad(1);
            controladorUsuario.getControladorVistaInicial().reestablecerInfo();
            controladorUsuario.getControladorVistaInicial().agregarPaneles();
        }
    }
    
               
    @Override
    public Component getTableCellRendererComponent(JTable tabla, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        
        if(value instanceof JButton){
            JButton btn = (JButton)value;
            return btn;
        }
        
        return super.getTableCellRendererComponent(tabla, value, isSelected, hasFocus, row, column);
    
}
    
    //Getters and setters

    public PanelMenus getPanelMenus() {
        return panelMenus;
    }
    
    private int acumulador,filas=0;
}
