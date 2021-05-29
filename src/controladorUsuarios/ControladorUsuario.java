/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorUsuarios;

import componentesUsuarioGeneral.PanelMenusControlador;
import componentesVistaInicial.PanelBusqueda;
import componentesVistaInicial.PanelLibro;
import logica.Persona;
import logica.Usuario;

/**
 *
 * @author 57301
 */
public class ControladorUsuario {
    private ControladorVistaInicial controladorVistaInicial;
    private PanelMenusControlador panelMenusControlador;
    private Usuario miUsuario;
    
    //constructor
    public ControladorUsuario(ControladorVistaInicial controladorVistaInicial){
        this.controladorVistaInicial = controladorVistaInicial;
        this.panelMenusControlador = new PanelMenusControlador(this);
    }
    //--------------------------------------------------------------------------
    //Inicializa al usuario del sistema
    //--------------------------------------------------------------------------
    public void inicializarUsuario(Usuario miUsuario){
        this.miUsuario = miUsuario;
    }
    
    
    
    
    //Se establecen los paneles generales
    public void actualizarPanelesUsuario(){
        controladorVistaInicial.vistaInicial.jpIzquierda.removeAll();
        controladorVistaInicial.vistaInicial.jpIzquierda.add(panelMenusControlador.getPanelMenus());
        controladorVistaInicial.vistaInicial.jpIzquierda.repaint();
    }
    
    
    //Metodos getters and setters
    public ControladorVistaInicial getControladorVistaInicial() {
        return controladorVistaInicial;
    }

    public Usuario getMiUsuario() {return miUsuario;}

    public PanelLibro getPanelLibro() {return controladorVistaInicial.getPanelLibro();}
    public PanelBusqueda getPanelBusqueda() {return controladorVistaInicial.getPanelBusquedaControlador();}
}
