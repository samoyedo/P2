package is2_practica1.P2;

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
    
    private String modelo, matricula;
    private int id_moto, coste, gastos;
    
    
    public Moto(String m,int id, int c, int g, String mt)
    {
        this.modelo = m;
        this.id_moto = id;
        this.coste = c;
        this.matricula = mt;
        this.gastos = g;
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
    
    public int getCosteTotal() {
        return coste+gastos;
    }
    
    public String getMatricula() {
        return matricula;
    }
    
    public int getGastos() {
        return gastos;
    }
    
    public void setGastos(int g) {
        gastos += g;
    }
    
    public String toString()
    {  
        return String.format("%03d",id_moto)+"\t"+modelo+"\t\t"+coste+"€";
    }  
}
