/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorUsuarios;

import componentesVistaInicial.PanelBusqueda;
import componentesVistaInicial.PanelBusquedaControlador;
import componentesVistaInicial.PanelIngresoControlador;
import componentesVistaInicial.PanelLibro;

/**
 *
 * @author 57301
 */
public class ControladorVistaInicial {
    protected VistaInicial vistaInicial;
    protected PanelIngresoControlador panelIngresoControlador;
    protected PanelBusquedaControlador panelBusquedaControlador;
    protected PanelLibro panelLibro = new PanelLibro();
    
    public ControladorVistaInicial(){
        this.vistaInicial = crearventanaInicial();
        this.panelBusquedaControlador = new PanelBusquedaControlador(this);
        
        this.panelIngresoControlador = new PanelIngresoControlador(this);        
        this.agregarPaneles();
    } 
    
    //un solo punto de acceso 
    public VistaInicial crearventanaInicial(){
        if(vistaInicial==null){
            vistaInicial = new VistaInicial();
        }
        return vistaInicial;
    }
    
    //Limpia los paneles centrales
    public void limpiarPaneles(){
        vistaInicial.jpCentralGeneral.removeAll();
        vistaInicial.jpDerecha.removeAll();
        vistaInicial.jpCentral.removeAll();
    }
    //Actualiza los paneles
    public void actualizarPaneles(){
        vistaInicial.jpCentralGeneral.repaint();
        vistaInicial.jpDerecha.repaint();
        vistaInicial.jpCentral.repaint();
        
    }
    //--------------------------------------------------------------------------
    //Cambiar visibilidad de los paneles
    //--------------------------------------------------------------------------
    public void cambiarVisibilidad(int opcion){
        if(opcion==1){
            vistaInicial.jpCentral.setVisible(true);
            vistaInicial.jpDerecha.setVisible(true);
            vistaInicial.jpCentralGeneral.setVisible(false);
        }else{
            vistaInicial.jpCentral.setVisible(false);
            vistaInicial.jpDerecha.setVisible(false);
            vistaInicial.jpCentralGeneral.setVisible(true);
        }
    }
    
    public void agregarPaneles(){
        //Se limpia el panel
        vistaInicial.jpIzquierda.removeAll();
        this.limpiarPaneles();
        vistaInicial.jpIzquierda.add(panelIngresoControlador.getPanelIngreso());
        vistaInicial.jpDerecha.add(panelBusquedaControlador.getPanelBusqueda());
        vistaInicial.jpCentral.add(panelLibro);
        vistaInicial.jpIzquierda.repaint();
        this.actualizarPaneles();
    }
    
    //Se actualizan los paneles
    public void reestablecerInfo(){
        panelIngresoControlador.getPanelIngreso().reestablecerInfo();
    }

    //--------------------------------------------------------------------------
    //Getters and setters
    //--------------------------------------------------------------------------
    public VistaInicial getVistaInicial() {
        return vistaInicial;
    }

    public PanelBusqueda getPanelBusquedaControlador() {return panelBusquedaControlador.getPanelBusqueda();}

    public PanelLibro getPanelLibro() {return panelLibro;}
    
    
    
}
