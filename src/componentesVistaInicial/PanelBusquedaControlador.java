/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesVistaInicial;

import conexionBD.BDAcceso;
import controladorUsuarios.ControladorVistaInicial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import logica.Libro;

/**
 *
 * @author 57301
 */
public class PanelBusquedaControlador implements ActionListener{
    private PanelBusqueda panelBusqueda;
    private BDAcceso bdAcceso = BDAcceso.crearAcceso();
    private ArrayList<Libro> listaLibros;
    private ControladorVistaInicial controladorVistaInicial;
    
    public PanelBusquedaControlador(ControladorVistaInicial controladorVistaInicial){
        this.controladorVistaInicial = controladorVistaInicial;
        panelBusqueda = new PanelBusqueda(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        //En el caso del limpiar
        if(e.getSource()==panelBusqueda.jbtnBuscar){
            
            String nombre=""+panelBusqueda.jtxtNombre.getText();
            String autor=(String) panelBusqueda.jcbxAutores.getSelectedItem();
            String categoria=(String) panelBusqueda.jcbxCategorias.getSelectedItem();
            String editorial=(String) panelBusqueda.jcbxEditoriales.getSelectedItem();
            if(nombre.equals("Nombre del libro")||nombre.equals(" ")||nombre.equals("")){
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
            listaLibros = bdAcceso.getBdConsulta().obtenerTodosLibros(nombre, autor, categoria, editorial);
            acumulador = 0;
            
            filas = controladorVistaInicial.getPanelLibro().modeloTabla.getRowCount();
            //Se limpia las filas de la tabla            
            while(filas>acumulador){
                controladorVistaInicial.getPanelLibro().modeloTabla.removeRow(0);
                acumulador++;
            }
            acumulador = 0;
            while(listaLibros.size()>acumulador){
                controladorVistaInicial.getPanelLibro().modeloTabla.addRow(new Object[]{listaLibros.get(acumulador).getNombreLibro(),listaLibros.get(acumulador).getAutor(),listaLibros.get(acumulador).getCategoria(),
                            listaLibros.get(acumulador).getEditorial(),listaLibros.get(acumulador).getId_ejemplar(),listaLibros.get(acumulador).isEstadoLibro()});
                                                                            
                acumulador++;
            }
            
        }
        
        if(e.getSource()==panelBusqueda.jbtnLimpiar){
            panelBusqueda.jtxtNombre.setText(" ");
        }
    }
    
    //Metodos y atributos
    public PanelBusqueda getPanelBusqueda() {
        return panelBusqueda;
    }
    
    private int acumulador,filas=0;
    
}
