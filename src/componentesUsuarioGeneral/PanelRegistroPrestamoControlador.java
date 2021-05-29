/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesUsuarioGeneral;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author 57301
 */
public class PanelRegistroPrestamoControlador extends MouseAdapter{
    //Atributos
    protected PanelRegistroPrestamo panelRegistroPrestamo;

    public PanelRegistroPrestamoControlador(){
        //constrolador
        this.panelRegistroPrestamo = new PanelRegistroPrestamo(this);
    }
    
    public DefaultTableModel getModeloTabla() {
        return panelRegistroPrestamo.getModeloTabla();
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        //Posicion x
        int columna = panelRegistroPrestamo.tablaPrestamo.getColumnModel().getColumnIndexAtX(e.getX());
        //Posicion y
        int fila = e.getY()/panelRegistroPrestamo.tablaPrestamo.getRowHeight();
        
        //Dimensione de la tabla
        if(fila<panelRegistroPrestamo.tablaPrestamo.getRowCount()&&fila>=0 && columna>=0 && columna<panelRegistroPrestamo.tablaPrestamo.getColumnCount()){
             //Objeto de la tabla
            Object miObjeto = panelRegistroPrestamo.tablaPrestamo.getValueAt(fila, columna);
            //Si el objeto es un boton
            if(miObjeto instanceof JButton){
                System.out.println("/nx"+columna+"Y"+fila);
                id_libro = (String) panelRegistroPrestamo.tablaPrestamo.getValueAt(fila, 0);
                System.out.println(id_libro);
            }
        }
        
    }
    //Public void retornaBoton
    public JButton retornaJbtn(){
        return panelRegistroPrestamo.jtbtn;
    }
    
    private String id_libro; 
}
