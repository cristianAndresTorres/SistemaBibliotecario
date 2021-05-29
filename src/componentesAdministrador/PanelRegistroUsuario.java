/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentesAdministrador;

import componentesVistaInicial.PanelIngresoControlador;
import conexionBD.BDAcceso;
import disegnoComponentes.ObjDecoradorServicio;
import disegnoComponentes.ObjGraficosServicio;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import logica.Persona;

/**
 *
 * @author 57301
 */
public class PanelRegistroUsuario extends JPanel implements ActionListener{
    
    //Atributos
    private ObjDecoradorServicio objetoDecorador;
    private ObjGraficosServicio objetoGrafico;
    private JLabel jl_Id, jl_tipo_usu, jl_pNombre, jl_pApellido, jl_contacto; 
    private JTextField jtxt_Id, jtxt_pNombre, jtxt_sNombre, jtxt_pApellido, jtxt_sApellido, jtxt_contacto;
    private JButton jbtnCrear;
    private JComboBox jcbxTipoUsu, jcbxTipoDoc;
    protected BDAcceso bdAcceso = BDAcceso.crearAcceso();
    
    //Constructo
    public PanelRegistroUsuario(){
    //Diseño del panel
        objetoDecorador = ObjDecoradorServicio.obtenerServicio();
        objetoGrafico = ObjGraficosServicio.getServicioObjeto();
        //Diseño de ventana
        this.setSize(600, 450);
        this.setBackground(objetoDecorador.getColorGrisClaro());
        this.setLayout(null);
	this.setVisible(true);
        this.iniEtiquetas();
        this.disegnarCombo();
    }
    
    
    //---------------------------------------------------------------------------
    public void iniEtiquetas(){
        jl_Id = objetoGrafico.crearJLabel("Ingrese el número del documento : ",50,80,300, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());
        jl_Id.setHorizontalAlignment(SwingConstants.LEFT);        
        this.add(jl_Id);

        jtxt_Id = objetoGrafico.crearJTextField("Número documento",340, 80, 140, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxt_Id);
        
        
        jl_tipo_usu = objetoGrafico.crearJLabel("Seleccioné el tipo de usuario ",50, 120, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());;
        jl_tipo_usu.setHorizontalAlignment(SwingConstants.LEFT); 
        this.add(jl_tipo_usu);
        
        jl_pNombre = objetoGrafico.crearJLabel("Ingrese los nombres :",50, 160, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());
        jl_pNombre.setHorizontalAlignment(SwingConstants.LEFT); 
        this.add(jl_pNombre);
        
        jtxt_pNombre = objetoGrafico.crearJTextField("Primer nombre", 310, 160, 120, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxt_pNombre);
        
        jtxt_sNombre = objetoGrafico.crearJTextField("Segundo nombre",440, 160, 120, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxt_sNombre);
        
        
        jl_pApellido = objetoGrafico.crearJLabel("Ingrese los apellidos :",50, 200, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());
        jl_pApellido.setHorizontalAlignment(SwingConstants.LEFT); 
        this.add(jl_pApellido);
        
        jtxt_pApellido = objetoGrafico.crearJTextField("Primer apellido", 310, 200, 120, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxt_pApellido);
        
        jtxt_sApellido = objetoGrafico.crearJTextField("Segundo apellido",440, 200, 120, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxt_sApellido);
        
        
        jl_contacto = objetoGrafico.crearJLabel("Ingrese el número de contacto :",50, 240, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());
        jl_contacto.setHorizontalAlignment(SwingConstants.LEFT); 
        this.add(jl_contacto); 
        
        jtxt_contacto= objetoGrafico.crearJTextField("Número telefónico",310, 240, 140, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxt_contacto);
        
        jbtnCrear = objetoGrafico.crearBoton("Registrar usuario",210,280,120,50, Color.BLACK, new Color(82, 190, 128), objetoDecorador.getcMano(), null);
        jbtnCrear.addActionListener(this);
        this.add(jbtnCrear);
        
    }
    
    public void disegnarCombo(){
        //----------------------------------------------------------------------
        jcbxTipoUsu= new JComboBox();
        jcbxTipoUsu.setLocation(340, 120);
        jcbxTipoUsu.setSize(140, 30);
        jcbxTipoUsu.setBorder(null);
        jcbxTipoUsu.setBackground(Color.WHITE);
        
        jcbxTipoUsu.addItem("Docente");
        jcbxTipoUsu.addItem("Estudiante");
        jcbxTipoUsu.addItem("Personal Administrativo");
        
        jcbxTipoDoc = new JComboBox();
        
        jcbxTipoDoc.setLocation(490, 80);
        jcbxTipoDoc.setSize(70, 30);
        jcbxTipoDoc.setBorder(null);
        jcbxTipoDoc.setBackground(Color.WHITE);
        
        jcbxTipoDoc.addItem("CC");
        jcbxTipoDoc.addItem("TI");
        jcbxTipoDoc.addItem("CE");
        
        
        this.add(jcbxTipoUsu);
        this.add(jcbxTipoDoc);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==this.jbtnCrear){
            int a=2+this.jcbxTipoUsu.getSelectedIndex();
            
            Persona per= new Persona(this.jtxt_Id.getText(),this.jtxt_pNombre.getText(),this.jtxt_sNombre.getText(),
            this.jtxt_pApellido.getText(),this.jtxt_sApellido.getText(),this.jtxt_contacto.getText(),(String)this.jcbxTipoDoc.getSelectedItem(),""+a);
            
            if(!this.bdAcceso.getBdRegistro().registrarPersona(per)){
                this.bdAcceso.getBdRegistro().registrarUsuario(per);
                JOptionPane.showMessageDialog(null, "Ingreso Exitoso");
            }else{
                JOptionPane.showMessageDialog(null, "Ya existe un Usuario con ese ID");
            }
            
        }
        
        
    }
    
    
}
