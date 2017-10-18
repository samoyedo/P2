package is2_practica1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumno
 */
public class Cesion {
    
    private Miembro actual, futuro;
    private Moto moto;
    private int idcesion;
    
    public Cesion(Miembro a, Miembro f, Moto m, int id)
    {
        this.actual = a;
        this.futuro = f;
        this.moto = m;
        this.idcesion = id;
    }
    
    public String toString()
    {
        return "\nCesion Nº: "+idcesion+"\nPropietario:\n\t"+ actual+"\nDestinatario:\n\t"+futuro+"\nMoto cedida:\n\t"+moto;
    }
}
