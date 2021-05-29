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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import logica.Libro;

/**
 *
 * @author 57301
 */
public class PanelRegistroLibro extends JPanel implements ActionListener{
    //Atributos
    private ObjDecoradorServicio objetoDecorador;
    private ObjGraficosServicio objetoGrafico;
    private JLabel jlLibro, llEjemplar, jlNombre, jlAutor, jlEditorial, jlCategoria; 
    private JTextField jtxtLibro, jtxtEjemplar, jtxtNombre;
    protected JComboBox jcbxAutores,jcbxEditoriales, jcbxCategorias ;
    
    private JLabel jlN_autor, jlN_categoria, jlN_editoria;
    private JTextField jtxt_n_autor, jtxt_n_categoria, jtxt_n_editorial;
    private JButton jbtn_autor, jbtn_categoria, jbtn_editorial, jbtn_R_libro;
    
    //Listas
    //Conexión a la base de datos.
    protected BDAcceso bdAcceso = BDAcceso.crearAcceso();
    protected ArrayList<String>listaDatos;
    
    public PanelRegistroLibro(){
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
        this.menusDesplegables();
        this.agregarComponentes();
        this.agregarNuevosRegistros();
    }
    
    //---------------------------------------------------------------------------
    public void iniEtiquetas(){
        jlNombre = objetoGrafico.crearJLabel("Ingrese el nombre del libro", 100, 80, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());
        jlNombre.setHorizontalAlignment(SwingConstants.LEFT);        
        this.add(jlNombre);
        
        jtxtNombre = objetoGrafico.crearJTextField("Nombre del libro", 360, 80, 140, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxtNombre);
        
        jlLibro = objetoGrafico.crearJLabel("Ingrese el código del libro", 100, 120, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());;
        jlLibro.setHorizontalAlignment(SwingConstants.LEFT); 
        this.add(jlLibro);
        
        jtxtLibro = objetoGrafico.crearJTextField("Código del libro", 360, 120, 140, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxtLibro);
        
        llEjemplar = objetoGrafico.crearJLabel("Ingrese el número del ejemplar", 100, 160, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());
        llEjemplar.setHorizontalAlignment(SwingConstants.LEFT); 
        this.add(llEjemplar);
        
        
        jtxtEjemplar = objetoGrafico.crearJTextField("Ejemplar", 360, 160, 140, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxtEjemplar);
        
        jlAutor = objetoGrafico.crearJLabel("Seleccione el autor", 100, 200, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());
        jlAutor.setHorizontalAlignment(SwingConstants.LEFT); 
        this.add(jlAutor);
        
        jlCategoria = objetoGrafico.crearJLabel("Seleccione la categoría", 100, 240, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());
        jlCategoria.setHorizontalAlignment(SwingConstants.LEFT); 
        this.add(jlCategoria); 
        
        jlEditorial = objetoGrafico.crearJLabel("Seleccione la editorial", 100, 280, 250, 30, objetoDecorador.getFontTprincipal(), Color.black, objetoDecorador.getcMano());
        jlEditorial.setHorizontalAlignment(SwingConstants.LEFT); 
        this.add(jlEditorial);
        
        jbtn_R_libro = objetoGrafico.crearBoton("Registrar",510,260,70,50, Color.BLACK, new Color(82, 190, 128), objetoDecorador.getcMano(), null);
        jbtn_R_libro.addActionListener(this);
        this.add(jbtn_R_libro);
        
        
    }
    
    
    public void disegnarCombo(){
        //----------------------------------------------------------------------
        jcbxAutores = new JComboBox();
        jcbxAutores.setLocation(360, 200);
        jcbxAutores.setSize(140, 30);
        jcbxAutores.setBorder(null);
        jcbxAutores.setBackground(Color.WHITE);
        //----------------------------------------------------------------------
        jcbxCategorias = new JComboBox();
        jcbxCategorias.setLocation(360, 240);
        jcbxCategorias.setSize(140, 30);
        jcbxCategorias.setBorder(null);
        jcbxCategorias.setBackground(Color.WHITE);
        //----------------------------------------------------------------------
        jcbxEditoriales = new JComboBox();
        jcbxEditoriales.setLocation(360, 280);
        jcbxEditoriales.setSize(140, 30);
        jcbxEditoriales.setBorder(null);
        jcbxEditoriales.setBackground(Color.WHITE);
    }
    
    public void menusDesplegables(){
        int contador=1;
        //----------------------------------------------------------------------
        jcbxAutores.removeAllItems();
        listaDatos = bdAcceso.getBdConsulta().obtenerAutores();
        while(contador<listaDatos.size()){
            jcbxAutores.addItem(listaDatos.get(contador));
            contador++;
        }
        //----------------------------------------------------------------------
        jcbxCategorias.removeAllItems();
        listaDatos.removeAll(listaDatos);
        contador = 1;
        listaDatos = bdAcceso.getBdConsulta().obtenerCategorias();
        while(contador<listaDatos.size()){
            jcbxCategorias.addItem(listaDatos.get(contador));
            contador++;
        }
        //----------------------------------------------------------------------        
        jcbxEditoriales.removeAllItems();
        listaDatos.removeAll(listaDatos);
        contador = 1;
        listaDatos = bdAcceso.getBdConsulta().obtenerEditorial();
        while(contador<listaDatos.size()){
            jcbxEditoriales.addItem(listaDatos.get(contador));
            contador++;
        }
    }
    
    public void agregarComponentes(){
        this.add(jcbxAutores);
        this.add(jcbxCategorias);
        this.add(jcbxEditoriales);
    }
    
    public void agregarNuevosRegistros(){
        jlN_autor = objetoGrafico.crearJLabel("Ingrese el nuevo autor",50, 330, 200, 30, null, Color.black, objetoDecorador.getcMano());
        jlN_autor.setHorizontalAlignment(jlN_autor.LEFT);  
        this.add(jlN_autor);
        
        jtxt_n_autor = objetoGrafico.crearJTextField("Nombre del autor",260, 330, 150, 30, Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxt_n_autor);
        
        jbtn_autor = objetoGrafico.crearBoton("Enviar autor",420, 330, 130, 30, Color.BLACK, new Color(82, 190, 128), objetoDecorador.getcMano(), null);
        jbtn_autor.addActionListener(this);
        this.add(jbtn_autor);
        
        jlN_categoria = objetoGrafico.crearJLabel("Ingrese la nueva categoría",50, 370, 200, 30, null, Color.black, objetoDecorador.getcMano());
        jlN_categoria.setHorizontalAlignment(jlN_autor.LEFT); 
        this.add(jlN_categoria);
        
        jtxt_n_categoria = objetoGrafico.crearJTextField("Nombre de la categoría",260, 370, 150, 30,  Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxt_n_categoria);
        
        jbtn_categoria = objetoGrafico.crearBoton("Enviar categoria",420, 370, 130, 30, Color.BLACK, new Color(82, 190, 128), objetoDecorador.getcMano(), null);
        jbtn_categoria.addActionListener(this);
        this.add(jbtn_categoria); 
        
        jlN_editoria = objetoGrafico.crearJLabel("Ingrese la editorial", 50, 410, 200, 30, null, Color.black, objetoDecorador.getcMano());
        jlN_editoria.setHorizontalAlignment(jlN_autor.LEFT); 
        this.add(jlN_editoria);
        
        jtxt_n_editorial = objetoGrafico.crearJTextField("Nombre la editorial", 260, 410, 150, 30,  Color.WHITE, Color.BLACK, Color.BLACK);
        this.add(jtxt_n_editorial);
        
        jbtn_editorial = objetoGrafico.crearBoton("Enviar editorial",420, 410, 130, 30, Color.BLACK, new Color(82, 190, 128), objetoDecorador.getcMano(), null);
        jbtn_editorial.addActionListener(this);
        this.add(jbtn_editorial); 
        
       
        
    }
    

    
    //Oyente
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==this.jbtn_autor){
            String valor=this.jtxt_n_autor.getText();
            
            if(valor.equals("Nombre del autor") || valor.equals("")){
                JOptionPane.showMessageDialog(null, "Ingresar algun autor");
            }else{
                
                if(this.bdAcceso.getBdConsulta().consultaAutor(valor)==true){
                    JOptionPane.showMessageDialog(null, "Ya existe un registro con el mismo nombre");
                }else{
                    
                    this.bdAcceso.getBdRegistro().registrarAutor(valor);
                    JOptionPane.showMessageDialog(null, "Exito en el ingreso");
                    this.menusDesplegables();
                }
                
            }
            
        }
        
        if(e.getSource()==this.jbtn_categoria){
            String valor=this.jtxt_n_categoria.getText();
            
            if(valor.equals("Ingrese la nueva categoría") || valor.equals("") ){
                JOptionPane.showMessageDialog(null, "Ingresar alguna categoria");
            }else{
                
                if(this.bdAcceso.getBdConsulta().consultaCategoria(valor)==true){
                    JOptionPane.showMessageDialog(null, "Ya existe un registro con el mismo nombre");
                }else{
                    this.bdAcceso.getBdRegistro().registrarCategoria(valor);
                    JOptionPane.showMessageDialog(null, "Exito en el ingreso");
                    this.menusDesplegables();
                }
                
            }
            
            
        }
        if(e.getSource()==this.jbtn_editorial){
            String valor=this.jtxt_n_editorial.getText();
            
            if(valor.equals("Nombre la editorial") || valor.equals("") ){
                JOptionPane.showMessageDialog(null, "Ingresar alguna editorial");
            }else{
                
                if(this.bdAcceso.getBdConsulta().consultaEditorias(valor)==true){
                    JOptionPane.showMessageDialog(null, "Ya existe un registro con el mismo nombre");
                }else{
                    this.bdAcceso.getBdRegistro().registrarEditorial(valor);
                    JOptionPane.showMessageDialog(null, "Exito en el ingreso");
                    this.menusDesplegables();
                }
                
            }
            
            
        }
        
        //ingreso libro
        if(e.getSource()==this.jbtn_R_libro){
            
            
            try{
                Libro libro= new Libro((String)this.jcbxAutores.getSelectedItem() ,(String) this.jcbxCategorias.getSelectedItem(),
                       (String)this.jcbxEditoriales.getSelectedItem(), true, Integer.parseInt(this.jtxtEjemplar.getText()),
                        this.jtxtLibro.getText(), this.jtxtNombre.getText());
                        
                
                if(this.bdAcceso.getBdConsulta().consultarID_Libro(libro)==false){
                    
                    int ejemplares=Integer.parseInt(this.jtxtEjemplar.getText());
                        for(int i=1;i<=ejemplares;i++){
                            
                            libro.setId_ejemplar(i);
                            
                            if(this.bdAcceso.getBdRegistro().registrarLibro(libro)==false)
                                JOptionPane.showMessageDialog(null, "Falla");
                            
                            
                        }
                    JOptionPane.showMessageDialog(null, "Ingreso Exitoso");
                    
                }else{
                    int result=this.bdAcceso.getBdConsulta().consultarNombreLibro(libro);
                    System.out.println(result);
                    if(result>0){
                        int ejemplares=Integer.parseInt(this.jtxtEjemplar.getText());
                        for(int i=result+1;i<=result+ejemplares;i++){
                            
                            libro.setId_ejemplar(i);
                            
                             if(this.bdAcceso.getBdRegistro().registrarLibro(libro)==false)
                                JOptionPane.showMessageDialog(null, "Falla");
                            
                            
                        }
                        JOptionPane.showMessageDialog(null, "Ingreso Exitoso");
                        
                    }else{
                         JOptionPane.showMessageDialog(null, "No coinside el nombre con su ID");
                    }
                    
                }
                
                
            }catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "Entrada no valida en el ingreso de ejemplares");
            }
            
        }
        
        
    }
    
}
