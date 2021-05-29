/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorUsuarios;

import componentesAdministrador.PanelOpcionesUsuario;
import logica.Persona;

/**
 *
 * @author 57301
 */
public class ControladorAdministrador {
    //Atributos
    protected ControladorVistaInicial controladorVistaInicial;
    protected PanelOpcionesUsuario panelOpcionesUsuario;
    private Persona miAdministrador;
    
    
    public ControladorAdministrador(ControladorVistaInicial controladorVistaInicial){
        this.controladorVistaInicial = controladorVistaInicial;
        this.panelOpcionesUsuario = new PanelOpcionesUsuario(this);
    }
    
    //Se establecen los paneles generales
    public void actualizarPanelesUsuario(){
        controladorVistaInicial.vistaInicial.jpIzquierda.removeAll();
        controladorVistaInicial.vistaInicial.jpIzquierda.add(panelOpcionesUsuario);
        controladorVistaInicial.vistaInicial.jpIzquierda.repaint();
    }
    
    
    //--------------------------------------------------------------------------

    public PanelOpcionesUsuario getPanelOpcionesUsuario() {
        return panelOpcionesUsuario;
    }    

    public Persona getMiAdministrador() {
        return miAdministrador;
    }
    
    public void inicializarAdministrador(Persona miAdministrador){
        this.miAdministrador = miAdministrador;
    }

    public ControladorVistaInicial getControladorVistaInicial() {
        return controladorVistaInicial;
    }
    
    
}
