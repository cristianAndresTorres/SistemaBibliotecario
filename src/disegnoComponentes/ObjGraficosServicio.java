/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disegnoComponentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javafx.scene.layout.Border;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author 57301
 */
public class ObjGraficosServicio {
    //Atributos
    private static ObjGraficosServicio servicioObjeto;
    
    //Componentes graficos
    private JPanel jpGeneral;
    private JButton jBtnGeneral;
    private JTextField jtfCajaGeneral;
    private JPasswordField jtfContrasegna;
    private JLabel txtGeneral;
    
    //------------------------------------------------
    //Constructor
    private ObjGraficosServicio(){
    }
    //------------------------------------------------
    //Unico punto de acceso patron singleton
    public static ObjGraficosServicio getServicioObjeto() {
        if(servicioObjeto == null) 
                servicioObjeto = new ObjGraficosServicio();
        return servicioObjeto;
    }
    
    //Permite crear un panel general
    public JPanel crearPanel(int posicionX, int posicionY, int ancho, int alto, Color colorPanel){
        jpGeneral = new JPanel();
        jpGeneral.setLocation(posicionX,posicionY);
        jpGeneral.setSize(ancho, alto);
        jpGeneral.setBackground(colorPanel);
        jpGeneral.setLayout(null);

        return jpGeneral;
    }
    
    
    public JButton crearBoton(String nombre, int posicionX, int posicionY, int ancho, int alto, Color colorLetra,
				Color colorFondo, Cursor Tipocursor, ImageIcon iconoImg) 
	{
		
            jBtnGeneral = new JButton(nombre);
            jBtnGeneral.setLocation(posicionX, posicionY);
            jBtnGeneral.setSize(ancho, alto);

            jBtnGeneral.setBackground(colorFondo);
            jBtnGeneral.setForeground(colorLetra);

            jBtnGeneral.setCursor(Tipocursor);

            jBtnGeneral.setIcon(iconoImg);
            jBtnGeneral.setFocusable(false);
            jBtnGeneral.setBorder(null);

            return jBtnGeneral;
    }
    
    
    public JTextField crearJTextField(String texto, int posX, int posY,int ancho, int alto, 
										Color colorFondo, Color colorLetra, Color cursor) {
		
        jtfCajaGeneral = new JTextField(texto);

        jtfCajaGeneral.setLocation(posX, posY);
        jtfCajaGeneral.setSize(ancho, alto);

        jtfCajaGeneral.setForeground(colorLetra);
        jtfCajaGeneral.setBackground(colorFondo);
        jtfCajaGeneral.setCaretColor(cursor);

        jtfCajaGeneral.setHorizontalAlignment(SwingConstants.CENTER);

        return jtfCajaGeneral;
		
	}
	
	public JPasswordField crearJPasswordField(String texto, int posX, int posY,int ancho, int alto, 
			Color colorFondo, Color colorLetra, Color cursor){

            jtfContrasegna = new JPasswordField(texto);

            jtfContrasegna.setLocation(posX, posY);
            jtfContrasegna.setSize(ancho, alto);

            jtfContrasegna.setForeground(colorLetra);
            jtfContrasegna.setBackground(colorFondo);
            jtfContrasegna.setCaretColor(cursor);

            jtfContrasegna.setHorizontalAlignment(SwingConstants.CENTER);

	return jtfContrasegna;
}
        
        public JLabel crearJLabel(String texto, int posX, int posY, int ancho, int alto, Font fuenteLetra, Color colorLetra, Cursor cursor) {
		txtGeneral = new JLabel();
		
		txtGeneral = new JLabel(texto);
		txtGeneral.setLocation(posX, posY);
		txtGeneral.setSize(ancho, alto);
		
		
		txtGeneral.setFont(fuenteLetra);
		txtGeneral.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtGeneral.setForeground(colorLetra);
		
		txtGeneral.setCursor(cursor);
		
		return txtGeneral;
	}
    
}
