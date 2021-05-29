/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionBD;

/**
 *
 * @author 57301
 */
public class BDAcceso {
    private static BDAcceso acceso;
    //Atributos
    private ConsultaBD bdConsulta = new ConsultaBD();
    private RegistroBD bdRegistro = new RegistroBD();
    private ModificaBD bdModifica = new ModificaBD();
    
    //Constructor
    private BDAcceso(){}
    
    public static BDAcceso crearAcceso(){
        if(acceso == null){
            acceso = new BDAcceso();
        }
        return acceso;
    }
    
    //--------------------------------------------------------------------------
    //Metodos getters and setters
    //--------------------------------------------------------------------------
    public ConsultaBD getBdConsulta() {return bdConsulta;}
    public RegistroBD getBdRegistro() {return bdRegistro;}
    public ModificaBD getBdModifica() {return bdModifica;}
}
