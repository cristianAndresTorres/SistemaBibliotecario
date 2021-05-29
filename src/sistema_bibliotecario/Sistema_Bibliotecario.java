/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_bibliotecario;

import conexionBD.ConsultaBD;
import controladorUsuarios.VistaInicial;
import controladorUsuarios.ControladorVistaInicial;

/**
 *
 * @author 57301
 */
public class Sistema_Bibliotecario {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControladorVistaInicial vistaInicialControlador = new ControladorVistaInicial();
        /*ConsultaBD consultaBD = new ConsultaBD();
        consultaBD.obtenerAutores();
        consultaBD.obtenerCategorias();
        consultaBD.obtenerEditorial();*/
        //ConsultaBD consultaBD = new ConsultaBD();
        //consultaBD.consultarPrestamo("1013686160");
    }
    
}
