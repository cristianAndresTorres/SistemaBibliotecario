/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disegnoComponentes;

import componentesVistaInicial.PanelSuperior;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;

/**
 *
 * @author 57301
 */
public class ObjDecoradorServicio {
    //Atributos
    private static ObjDecoradorServicio servicioDercorador;
    private Cursor cMano;
    private Font fontTprincipal,fontOpc,fontSecion;
    private Color colorRojoOscuro, colorGrisOscuro, colorGrisClaro, colorVerde,colorRojoClaro, colorGrisMedio, colorAzulOscuro;
    
    //Constructor
    private ObjDecoradorServicio() {
        cMano =new Cursor(Cursor.HAND_CURSOR);  
        colorRojoOscuro = new Color(236, 112, 99);
        colorGrisOscuro = new Color(95, 106, 106);
        colorAzulOscuro =new Color(1,117,245);
        colorGrisClaro = new Color(242, 243, 244);
        colorVerde = new Color(212, 230, 241);
        colorRojoClaro = new Color(250, 219, 216);
        colorGrisMedio = new Color(153, 163, 164);
        
        fontTprincipal = new Font("Engravers MT",Font.BOLD,17);
        fontOpc = new Font("Daytona Pro Condensed",Font.BOLD,12);
        fontSecion = new Font("Daytona Pro Condensed",Font.BOLD,13);
    }
    //Patron creacional-Singleton
    public static ObjDecoradorServicio obtenerServicio() {
        if(servicioDercorador == null)
                servicioDercorador = new ObjDecoradorServicio();

        return servicioDercorador;
    }
    
    //Metodos getters and setters
    public Cursor getcMano() {return cMano;}
    public Color getColorRojOscuro(){return  colorRojoOscuro;}
    public Color getColorRojClaro(){return  colorRojoClaro;}
    public Color getColorGrisOscuro(){return  colorGrisOscuro;}
    public Color getColorGrisClaro(){return  colorGrisClaro;}
    public Color getColorVerdeClaro(){return  colorVerde;}  
    public Color getColorGrisMedio(){return  colorGrisMedio;} 
    public Color getColorAzulOscuro() {return colorAzulOscuro;}
    
    public Font getFontTprincipal() {return fontTprincipal;}
    public Font getFontOpc() {return fontOpc;}
    public Font getFontSecion() {return fontSecion;}
    
}
