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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import logica.Libro;
import logica.Prestamo;
import logica.Usuario;

/**
 *
 * @author 57301
 */
public class PanelDevolucionLibro extends JPanel implements ActionListener{
    private ObjDecoradorServicio objetoDecorador;
    private ObjGraficosServicio objetoGrafico;
    private JLabel jlLibro, llEjemplar, jlNombre; 
    private JTextField jtxtLibro, jtxtEjemplar, jtxtNombre;
    private JButton jbtn_R_Prestamo;
     private JLabel jl_titulo;
    
    
    protected BDAcceso bdAcceso = BDAcceso.crearAcceso();
    
    public PanelDevolucionLibro(){
        
         //Diseño del panel
        objetoDecorador = ObjDecoradorServicio.obtenerServicio();
        objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        //Diseño de ventana
        this.setSize(600, 450);
        this.setBackground(objetoDecorador.getColorGrisClaro());
        this.setLayout(null);
	this.setVisible(true);
        this.iniEtiquetas();
    }
    
     public void iniEtiquetas(){
         
        jl_titulo = objetoGrafico.crearJLabel("Realizar Devolucion",200, 20, 400, 50, objetoDecorador.getFontTprincipal(), Color.BLACK, objetoDecorador.getcMano());
        this.add(jl_titulo);
         
        jlNombre = objetoGrafico.crearJLabel("Ingrese el ID del usuario", 100, 80, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());
        jlNombre.setHorizontalAlignment(SwingConstants.LEFT);        
        this.add(jlNombre);
        
        jtxtNombre = objetoGrafico.crearJTextField("", 360, 80, 140, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxtNombre);
        
        jlLibro = objetoGrafico.crearJLabel("Igrese el ID del libro", 100, 120, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());;
        jlLibro.setHorizontalAlignment(SwingConstants.LEFT); 
        this.add(jlLibro);
        
        jtxtLibro = objetoGrafico.crearJTextField("", 360, 120, 140, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxtLibro);
        
        llEjemplar = objetoGrafico.crearJLabel("Ingrese el número del ejemplar", 100, 160, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());
        llEjemplar.setHorizontalAlignment(SwingConstants.LEFT); 
        this.add(llEjemplar);
        
        jtxtEjemplar = objetoGrafico.crearJTextField("", 360, 160, 140, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxtEjemplar);
        
        jbtn_R_Prestamo = objetoGrafico.crearBoton("Registrar",510,260,70,50, Color.BLACK, new Color(82, 190, 128), objetoDecorador.getcMano(), null);
        jbtn_R_Prestamo.addActionListener(this);
        this.add(jbtn_R_Prestamo);
        
     }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        if(e.getSource()==this.jbtn_R_Prestamo){
            
            
            
            try{
                String Id_usu= this.jtxtNombre.getText();
                String Id_libro= this.jtxtLibro.getText();
                int ejemplar= Integer.parseInt(this.jtxtEjemplar.getText());
                
                if(Id_usu.equals("")==false){
                    if(Id_libro.equals("")==false){
                        
                        Usuario p=this.bdAcceso.getBdConsulta().obtenerUsuario_general(Id_usu);
                        if(p!=null){
                            Prestamo pres= new Prestamo("",""+Id_libro,""+ejemplar,""+Id_usu,
                                    "","",true,true);
                            if(this.bdAcceso.getBdModifica().modificar_Estado_Prestamo(pres)){
                                
                                p.setStock_libros(p.getStock_libros()-1);
                                p.setEstado_prestamo(true);
                                
                                this.bdAcceso.getBdModifica().modificar_Usuario_cantidad(p);
                                Libro libro= new Libro(ejemplar,Id_libro,true);
                                this.bdAcceso.getBdModifica().modificar_Libro_Estado(libro);
                                
                                JOptionPane.showMessageDialog(null, "Acción realizada con exito");
                                
                            }else{
                            JOptionPane.showMessageDialog(null, "El prestamo no esta registrado");
                            }
                            
                            
                        }else{
                            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                        }
                        
                        
                        
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Valor de ID_libro no valido");
                    }
                        
                }else{
                    JOptionPane.showMessageDialog(null, "Valor de ID usuario no valido");
                }
                
            }catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "Valor de ejemplar no valido");
            }
            
        }
    }
    
}
