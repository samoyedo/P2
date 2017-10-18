package is2_practica1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Salvador Andres y Julio Ayala
 */
public class Moto {
    
    private String modelo;
    private int id_moto;
    
    private int coste;
    
    
    public Moto(String m,int id, int c)
    {
        this.modelo = m;
        this.id_moto = id;
        this.coste = c;
    }

    public String getModelo() {
        return modelo;
    }

    public int getId_moto() {
        return id_moto;
    }

    public int getCoste() {
        return coste;
    }
    
    public String toString()
    {  
        return String.format("%03d",id_moto)+"\t"+modelo+"\t\t"+coste+"€";
    }  
}
