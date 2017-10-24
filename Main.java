package is2_practica1.P2;


import java.util.*;
import java.util.Scanner;
import javax.swing.JFrame;
import java.io.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Salvador Andres y Julio Ayala
 */
public class Main extends JFrame{
    private static int id_miembro = 0, id_moto = 0, id_cesion = 0, max_coste_moto;
    private String scan, fich, mat_scann;
    private Date fecha;
    private Miembro miembro;
    private ArrayList <Miembro> miembros = new ArrayList();
    private Moto moto;
    private ArrayList <Moto> motos = new ArrayList();
    private Cesion cesion;
    private ArrayList <Cesion> cesiones = new ArrayList();
    private static Scanner scanner = new Scanner(System.in);
    
    public Main(){
        Menu();
    }
    
    public void Menu(){
        Boolean ok;
            
        System.out.print("Igrese el valor maximo de transacciones: ");
        do{
            scan = scanner.nextLine();
            if(scan.length()<1)
                System.out.println("\n!!!NO valido!!!, debe ingresar un dato");
            else if((!comprobarValor(scan)) || (Integer.parseInt(scan)<0))
                System.out.println("\n!!!El valor introducido no es valido!!!");
            else break;
        }while(true);
        
        max_coste_moto = Integer.parseInt(scan);
        
        do
        { 
            ok = false;
            
            System.out.println("\n1. Registrar nuevo miembro ");
            System.out.println("2. Registrar una nueva motocicleta");
            System.out.println("3. Registrar una cesión ");
            System.out.println("4. Listar en pantalla los miembros con motos en posesión ");
            System.out.println("5. Listar todas las motos ");
            System.out.println("6. Mostrar las cesiones realizadas ");
            System.out.println("7. Salir del programa ");
            System.out.print("\nIngrese el Nº de la opcion que desea: ");
            scan = scanner.nextLine();
            if(!comprobarValor(scan))
                scan = "8";
            switch(Integer.parseInt(scan))
            {
                case 1:
                    registrarMiembro();
                    break;                
                case 2:
                    registrarMoto();    
                    break;                
                case 3:
                    registrarCesion();
                    break;                
                case 4:
                    listarMiembros();
                    break;
                case 5:
                    listarMotos();
                    break;
                case 6:
                    mostrarCesiones();
                    break;
                case 7:
                    ingresarGastosMoto();
                case 8:
                    Salir();
                    ok = true;
                    break;                    
                default:
                    System.out.println("\n***********************************************\n  "
                            + "!!!La opción introducida no es corecta!!!\n***********************************************");
            }
        }while(!ok);
    }
    
    /*
        Permite registrar un nuevo socio, solo pide ingresar el nombre, luego automaticamente le asigna un numero de socio,
        como restriccion ponemos que se evite no ingresar ningun valor 
    */
    public void registrarMiembro(){
        //Opcion ingresada para que el usuario pueda volver al menu en caso de arrepentirse o ingresar por error en esta opcion
        System.out.println("\n---------------------------------\n"
                + "Para regresar al menu ingrese 's'\n---------------------------------");
        do{
        System.out.print("\nIngrese nombre y apellido: ");
        scan = scanner.nextLine();
        if("s".equals(scan)) return;
        if(scan.length()<1)
            System.out.println("\n!!!NO valido!!!, debe ingresar un dato");
        else break;
        }while(true);
        miembro = new Miembro(scan, ++id_miembro);
        miembros.add(miembro);
        System.out.println("\n**********************************************************\n  "
                + "!!!La operacion fue realizada con exito!!! \n  el numero de socio de "+scan+" es : "
                + String.format("%03d", id_miembro)+"\n**********************************************************");
        System.out.flush();
    }

    /*
        Permite registrar una nueva moto, para el mismo el usuario debe indicar el modelo y el coste, una ves obtenido los datos
        se asigna dicha moto al primer miembro que encuentre en el listado de miembros en el que, la suma del coste de la moto actual
        y el coste total de motos que posee el miembro no superen los 6000 euros.
    */
    public void registrarMoto(){
        Boolean ok=false; String cost, matri, gastos;
        //Opcion ingresada para que el usuario pueda volver al menu en caso de arrepentirse o ingresar por error en esta opcion
        System.out.println("\n---------------------------------\n"
                + "Para regresar al menu ingrese 's'\n---------------------------------");
        do{
            System.out.print("\nIngrese modelo de moto: ");
            scan = scanner.nextLine();
            //Evitamos que el usuario deje este campo en blanco
            if(scan.length()<1)
                System.out.println("\n!!!NO valido!!!, debe ingresar un dato");
            else break;
        }while(true);
        if("s".equals(scan)) return;
        
        do{
            System.out.print("\nIngrese la matricula: ");
            mat_scann = scanner.nextLine();
            //Evitamos que el usuario deje este campo en blanco
            if(mat_scann.length()< 0)
                System.out.println("\n!!!NO valido!!!, debe ingresar un dato");
            else if((mat_scann.length()==1)&&("s".equals(mat_scann))) return;
            else if ((mat_scann.length()!= 7)||(!(comprobarMatricula(mat_scann))))
                System.out.println("\n!!!NO valido!!!, los datos ingresados no corresponden a una matricula");
            else break;
        }while(true);
        
        do{
            System.out.print("\nIngrese coste en euros: ");
            cost = scanner.nextLine();
            //Se comprueba que el dato ingresado se un entero y que no este en negativo
            if((!comprobarValor(cost)) || (Integer.parseInt(cost)<0))
                System.out.println("\n!!!El valor introducido no es valido!!!");
            else break;
        }while(true);
        
        do{
            System.out.print("\nIngrese otros gastos en euros: ");
            gastos = scanner.nextLine();
            //Se comprueba que el dato ingresado se un entero y que no este en negativo
            if((!comprobarValor(gastos)) || (Integer.parseInt(gastos)<0))
                System.out.println("\n!!!El valor introducido no es valido!!!");
            else break;
        }while(true);
        
        moto = new Moto(scan ,++id_moto, Integer.parseInt(cost), Integer.parseInt(gastos), mat_scann);
        for (Miembro miembro1 : miembros) {
            if(((Integer.parseInt(cost)+ Integer.parseInt(gastos) + miembro1.getCoste_moto()) <= max_coste_moto)&&(miembro1.AsignarMoto(moto))){
                motos.add(moto);
                cost = miembro1.getNombre();
                ok = true;
                break;
            }
        }
        if(!ok){
            System.out.println("\n**********************************************************\n  "
                    + "!!!No hay ningún miembro disponible para la moto.\n\t La moto NO ha sido dada de ALTA.!!!"
                    + "\n**********************************************************");
            id_moto--;
        }
        else
            System.out.println("\n**********************************************************\n  "
                    + "!!!La operacion fue realizada con exito!!! \n  La moto "+scan+" fue asignado al "
                    + "socio "+cost+"\n**********************************************************");
    }
    
    /*
        Registra ceiones netre socios, para lo cual el usuario debe indicar su numero de socio, se verifica que tenga al menos una moto para la cesion
        luego se pide que indique el socio al cual desea ceder la moto, que debe ser distinto al actual, si la suma del coste de la moto seleccionada
        para la cesion y coste total de motos que posee el socio seleccionado superan los 6000, se cancela la operacion 
    */
    public void registrarCesion(){
        String propietario, idmoto ,destinatario;
        //Primero veririficamos que existan al menos 2 miembros para la cesion
        if(id_miembro<2){
            System.out.println("\n!!!no existen miembros sufiecientes pata realizar una cesion!!!");
            return;
        }
        //Opcion ingresada para que el usuario pueda volver al menu en caso de arrepentirse o ingresar por error en esta opcion
        System.out.println("\n---------------------------------\nPara regresar al menu ingrese 's'"
                + "\n---------------------------------");
        System.out.print("\nIngrese su Nº de socio: ");
        do{
            propietario = scanner.nextLine();
            if("s".equals(propietario)) return;
            //Se comprueba que el dato ingresado se un numero y que se corresponda al id de algunos de los socios que se encuentran en el listado de socio
            if((!comprobarValor(propietario))||(!comprobarSocio(Integer.parseInt(propietario))))
                System.out.print("\n!!!Nº de socio incorrecto!!!, vuelva a ingresar su Nº de socio: ");
            else break;
        }while(true);
        System.out.println("\nMotos en posecion del socio "+miembros.get(Integer.parseInt(propietario)-1).getNombre());
        //Se muestran las motos que posee el socio actual
        for (Miembro miembro1 : miembros) {
            if(miembro1.getId_miembro()==Integer.parseInt(propietario))
            {
                if(miembro1.getMotos().size()>0){
                    System.out.println("\n ID\tModelo\t\t\tCoste\n\n"+miembros.get(Integer.parseInt(propietario)-1).mostrarMotos());
                    break;
                }
                else{
                    System.out.println("\n!!!Actualmente no posee ninguna moto, no es posible realizar una cesion!!!");
                    return;
                }
            }
        }
        System.out.print("\nSeleccionar Nº de ID de moto para la cesion: ");
        do{
            idmoto = scanner.nextLine();
            if("s".equals(idmoto)) return;
            //Se comprueba que el dato introducido sea un numero y que se corresponda con el id de alguna de las motos que posee el socio
            if((!comprobarValor(idmoto))||(comprobarMotoPropietario(Integer.parseInt(propietario)-1, Integer.parseInt(idmoto))))
                System.out.print("\n!!!Nº de ID incorrecto!!!, vuelva a ingresar el Nº de ID de moto: ");
            else break;
           
        }while(true);
        muestraDestinatarios(propietario);
        System.out.print("\nIngrese Nº de socio del destinatario: ");
        do{
            destinatario = scanner.nextLine();
            if("s".equals(destinatario)) return;
            //Se comprueba que el dato introducido sea un numero y que se corresponda con el id de alguno de los socios que se encuentran en el listado de socios
            // y que no se al actual, para evitar autocesiones
            if((!comprobarValor(destinatario))||(!comprobarSocio(Integer.parseInt(destinatario)))||(destinatario.equals(propietario)))
                System.out.print("\n!!!Nº de socio incorrecto!!!, vuelva a ingresar el Nº de socio: ");
            //Se comprueba que la suma del coste de la moto seleccionada para la cesion y coste total de motos que posee el socio seleccionado superan los 6000
            else if(((miembros.get(Integer.parseInt(destinatario)-1).getCoste_moto())+(motos.get(Integer.parseInt(idmoto)-1).getCoste()))>max_coste_moto)
                    System.out.print("\n!!!Ya no puede asignarse mas motos a este socio!!!, ingrese otro Nº de socio o 's' para volver a menu: ");
            else break;
        }while(true);
        if((miembros.get(Integer.parseInt(propietario)-1).QuitarMoto(Integer.parseInt(idmoto)))&&(miembros.get(Integer.parseInt(destinatario)-1).AsignarMoto(motos.get(Integer.parseInt(idmoto)-1)))){
            cesion = new Cesion(miembros.get(Integer.parseInt(propietario)-1),miembros.get(Integer.parseInt(destinatario)-1),motos.get(Integer.parseInt(idmoto)-1),++id_cesion, (fecha = new Date()));
            cesiones.add(cesion);
            System.out.println("\n**********************************************************\n  "
                    + "!!!La operacion fue realizada con exito!!!\n**********************************************************");
        }
        else{
            System.out.println("\n**********************************************************\n  "
                    + "!!!ERROR. La operacion No pudo ser realizada!!!\n**********************************************************");
            id_cesion--;
        }
    }
     
    /*
        Lista todos los socios que se encuentran el el listado de socios exceptuando alquel que se pasa como argumento
    */
    public void muestraDestinatarios(String act){
            System.out.println("\nPosibles destinatarios: \n\n ID\tNombre\t\t\tNº de motos\tCoste total\n");
            for(Miembro miembro1:miembros)
            {
                if(miembro1.getId_miembro()!= Integer.parseInt(act))
                    System.out.println(miembro1);
            }
    }

    /*
        Se comprueba si el socio pasado como argumento se encuentra en el listado de socios
    */
    public Boolean comprobarSocio(int p){
        return miembros.stream().anyMatch((miembro1) -> (p==miembro1.getId_miembro()));
    }
    
    /*
        Se comprueba si la moto 'm' pasada como argumento pertenece al prpietario 'p'
    */
    public Boolean comprobarMotoPropietario(int p,int m){
        ArrayList <Moto> aux = new ArrayList(miembros.get(p).getMotos()); 
        return aux.stream().noneMatch((moto1) -> (moto1.getId_moto()==m));
    }
    
    public Boolean comprobarMotoAsociacion(int m){
        return motos.stream().anyMatch((moto1) -> (m==moto1.getId_moto()));
    }
    
    /*
        Se comprueba que el string pasado como argumento se corresponde a un numero
    */
    public Boolean comprobarValor(String n){
        try{
            Integer.parseInt(n);
            return true;
        }
        catch(NumberFormatException ex){
            return false;
        }
    }
    
    
public Boolean comprobarMatricula(String n){
        String aux1, aux2;
        aux1 = n.substring(0,3);
        aux2 = n.substring(4,7);
        return !(!(comprobarValor(aux1))||!((Character.isLetter(aux2.charAt(0)))&&(Character.isLetter(aux2.charAt(2)))&&(Character.isLetter(aux2.charAt(3)))));
    }

    /*
        Lista todos los miembros del listado de socios
    */
    public void listarMiembros(){
        //En caso de no haber aun ningun miembro se indica al usuario
        if(miembros.size()<1)
            System.out.println("!!!Aun no ha sido registrado ningun socio!!!");
        //En caso de si haber miembros pero ninguno tiene asigando aun una moto se indica al usuario
        else if((motos.size()>0)&&(motos.size()<1))
            System.out.println("!!!Ningun miembros se encuentra en posecion de alguna moto!!!");
        else{
            System.out.println("\n ID\tNombre\t\t\tNº de motos\tCoste total\n");
            miembros.stream().filter((miembro1) -> (miembro1.getMotos().size()>0)).forEach((miembro1) 
                -> {System.out.println(miembro1);});
        }
    }

    /*
        Lista todos los miembros del listado de socios
    */
    public void listarMotos(){
        //En caso de no haber aun ninguna moto registrada se indica al usuario
        if(motos.size()<1)
            System.out.println("!!!Aun no ha sido registrado ninguna moto!!!");
        else{
            System.out.println("\n ID\tModelo\t\t\tCoste\n");
            motos.stream().forEach((moto1) -> {System.out.println(moto1);});
        }
    }
    
    /*
        Lista todas las seciones del listado de cesiones
    */
    public void mostrarCesiones(){
        //En caso de no haber aun ninguna cesion registrada se indica al usuario
        if(cesiones.size()<1)
            System.out.println("!!!Aun no ha sido realizada ninguna cesion!!!");
        else{
            System.out.println();
            cesiones.stream().forEach((cesion1) -> {System.out.println(cesion1);});
        }
    }
    
    /*
        Antes de salir se pasa a un fichero de texto todos los datos contenidos en los listados de miembros, motos, y cesiones
    */
    public void Salir(){
        do{
        System.out.print("\nIdique el nombre del fichero en que se guardaran los datos: ");
        scan = scanner.nextLine();
        //Evitamos que el usuario deje este campo en blanco
        if(scan.length()<1)
            System.out.println("\n!!!NO valido!!!, debe ingresar un dato");
        else break;
        }while(true);
        FileWriter flwriter = null;
	try {
            flwriter = new FileWriter("./src/is2_practica1/"+scan+".txt");
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write("------------------------------\nMiembros actuales de ACAMA"
                    + "\n------------------------------\n\n ID\tNombre\t\t\tNº de motos\tCoste total\n\n");
            
            for (Miembro miembro1 : miembros) {
                //escribe los datos en el archivo
                bfwriter.write(miembro1.toString()+"\n\n\t\tMotos actuales del socio: "
                        + "\n\t\t ID\tModelo\t\t\tCoste\n\t\t"+miembro1.mostrarMotos()+"\n");
            }
            bfwriter.write("------------------------------\nCesiones realizadas\n------------------------------ \n");
            for (Cesion cesion1 : cesiones) {
                    //escribe los datos en el archivo
                    bfwriter.write(cesion1.toString()+"\n");
            }
		//cierra el buffer intermedio
            bfwriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (flwriter != null) {
                    try {//cierra el flujo principal
			flwriter.close();
                    } catch (IOException e) {
			e.printStackTrace();
                    }
		}
	}
    }
    
    public void ingresarGastosMoto(){
        String gastos;
        System.out.println("Listado de motos que pertenecen a la asociacion \n");
        listarMotos();
        
        System.out.print("\nSeleccionar Nº de ID de moto para la operacion: ");
        do{
            scan = scanner.nextLine();
            if("s".equals(scan)) return;
            //Se comprueba que el dato introducido sea un numero y que se corresponda con el id de alguna de las motos que posee el socio
            if((!comprobarValor(scan))||(!comprobarMotoAsociacion(Integer.parseInt(scan))))
                System.out.print("\n!!!Nº de ID incorrecto!!!, vuelva a ingresar el Nº de ID de moto: ");
            else break;
           
        }while(true);
        
        do{
            System.out.print("\nIngrese el importe a incrementar en euros: ");
            gastos = scanner.nextLine();
            if("s".equals(gastos)) return;
            //Se comprueba que el dato ingresado se un entero y que no este en negativo
            if((!comprobarValor(gastos)) || (Integer.parseInt(gastos)<0))
                System.out.println("\n!!!El valor introducido no es valido!!!");
            else break;
        }while(true);
        
        motos.get(Integer.parseInt(scan)-1).setGastos(Integer.parseInt(gastos));
        
        System.out.println("\n**********************************************************\n  "
                    + "!!!La operacion fue realizada con exito!!! \n  La moto "+motos.get(Integer.parseInt(scan)-1).getModelo()+" tiene ahora un "
                    + "gasto total de "+motos.get(Integer.parseInt(scan)-1).getGastos()+"\n**********************************************************");
       
    }
    
    public static void main(String[] args){
        System.out.println("\n//******************************//\n//\t\tACAMA\t\t//\n//******************************//\n");
        Main m =new Main();
    }
}





