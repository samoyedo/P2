package is2_practica1.P2;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Salvador Andres y Julio Ayala
 */
public class Miembro {
    
    private String nombre;
    private int id_miembro;
    private Moto moto ;
    private ArrayList<Moto> motos = new ArrayList();
    
    public Miembro(String n, int id)
    {
        this.id_miembro = id;
        this.nombre = n;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCoste_moto() {
        int coste_moto=0;
        for(int i=0; i<motos.size(); i++)
            coste_moto += motos.get(i).getCosteTotal();
        return coste_moto;
    }

    public int getId_miembro() {
        return id_miembro;
    }
    
    public ArrayList getMotos() {
        return motos;
    }
    
    public Boolean AsignarMoto(Moto m)
    {
        return this.motos.add(m);
    }
    
    public String toString()
    {
        return String.format("%03d",id_miembro)+"\t"+nombre+"\t\t"+motos.size()+"\t\t"+getCoste_moto()+"€";
    }
//Devuelve un string con los datos de cada moto que posee el miembro
    public String mostrarMotos() {
        String s="";
    for(int i = 0; i < motos.size(); i++)
        {
            s += motos.get(i).toString()+"\n";
        }
        return s;
    }
//Elimina del listado de mostos del socio la moto indicada
    public Boolean QuitarMoto(int id) {
        for(int i=0; i<motos.size();i++){
            if(id==motos.get(i).getId_moto()){
                motos.remove(i);
                return true;
            }
        }
        return false;
    }
}