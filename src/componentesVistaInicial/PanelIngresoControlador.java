/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesVistaInicial;

import conexionBD.BDAcceso;
import controladorUsuarios.ControladorAdministrador;
import controladorUsuarios.ControladorUsuario;
import controladorUsuarios.ControladorVistaInicial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author 57301
 */
public class PanelIngresoControlador implements ActionListener{
    //Atributos
    private PanelIngreso panelIngreso;
    
    private ControladorVistaInicial vistaInicialControlador;
    
    //Para el de ingreso[Usuario general]
    private ControladorUsuario controladorUsuario;
    
    private ControladorAdministrador controladorAdministrador;
    
    //Acceso a la base de datos
    private BDAcceso bdAcceso;
    
    //ingreso
    public PanelIngresoControlador(ControladorVistaInicial vistaInicialControlador){
        panelIngreso = new PanelIngreso(this);
        //controladorInicial
        this.vistaInicialControlador = vistaInicialControlador; 
        controladorUsuario= new ControladorUsuario(vistaInicialControlador);
        controladorAdministrador = new ControladorAdministrador(vistaInicialControlador);
        //Se crea el acceso a la base de datos
        bdAcceso = BDAcceso.crearAcceso();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        //Se hace la validación del ingreso[ID_usuario,Contraseña]
        acceso = bdAcceso.getBdConsulta().consultarUsuario(panelIngreso.jtUsuario.getText(), panelIngreso.jpContrasegna.getText());
        switch(acceso){
            //Administrador
            case 1:
                JOptionPane.showMessageDialog(null, "Bienvenido al Sistema Bibliotecario");
                //Se inicializa al administrador
                controladorAdministrador.inicializarAdministrador(bdAcceso.getBdConsulta().obtenerAdministrador(panelIngreso.jtUsuario.getText()));
                //Se actualizan los paneles
                System.out.println(controladorAdministrador.getMiAdministrador().getPri_nombre());
                System.out.println(controladorAdministrador.getMiAdministrador().getSeg_nombre());
                
                //Se actualiza el panel izquierdo
                controladorAdministrador.actualizarPanelesUsuario();
                
                
                
                break;
            //Planta docente, Estudiantado, personalAdministrativo    
            case 2:
            case 3:
            case 4:
                //Se obtiene el usuario
                JOptionPane.showMessageDialog(null, "Bienvenido al Sistema Bibliotecario");
                //Se obtiene el usuario del sistema
                controladorUsuario.inicializarUsuario(bdAcceso.getBdConsulta().obtenerUsuario(panelIngreso.jtUsuario.getText()));
                //Se actualizan los paneles
                controladorUsuario.actualizarPanelesUsuario();
                break;
            case 0:
                JOptionPane.showMessageDialog(null, "Usuario Bloqueado");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Datos incorrectos");
                break;
        }
    }
    
    //Getters and setters
    public PanelIngreso getPanelIngreso() {return panelIngreso;}
    
    
    private int acceso;
}
