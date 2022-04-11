/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokemon_go;

import Objetos.Pokemon;
import Persistencia.PersistenciaPokemon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumne
 */
public class Pokemon_Go {
    DAOPokemon poke_operaciones;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pokemon_Go app = new Pokemon_Go();
        
        app.lanzarApp();
    }
    
    //correr el programa
    private void lanzarApp(){
        poke_operaciones = new DAOPokemon();
        Scanner sc = new Scanner (System.in);
        
        try {
            Logo.mostrarLogo();
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el logo");
        }
        
        //login usuario, pedir datos
        System.out.println("Nombre de usuario: ");
        String nombre = sc.nextLine();
        
        System.out.println("Contraseña: ");
        String contrasenya = sc.nextLine();
        
        Identificacion(nombre, contrasenya,sc);
        
        int opcion=sc.nextInt();
        
        switch (opcion){
            case 1:
                cazar_Pokemon();
                break;                                                                                                                                
            
        }
        
        
        
    }
    
    //fase2y3
    public void Identificacion(String nombre, String contrasenya, Scanner sc){
        
        String ruta = "Usuarios/user_" + nombre + ".dat.txt";//En Windows si que importan las 
        //extensiones de los ficheros, por eso hay que añadir .txt
        
        try {
            String password = ValidarUsuario.LecturaFichero(ruta); 
            //lee la linea del fichero y lo almacena en la variable password
            
            System.out.println("Verifica la contraseña: ");
            contrasenya = sc.nextLine();
            
            if (ValidarUsuario.ValidarContrasenya(contrasenya, password)) {
                //compara la linea del fichero con la contraseña introducida por el usuario
                System.out.println("Login correcto, Hola " + nombre + "!");
            }
            else
                System.out.println("Login incorrecto");
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el usuario");
            System.out.println("Desea crearlo?");
            String creacion = sc.nextLine();
            if (creacion.equalsIgnoreCase("si")) {
                try {
                    ValidarUsuario.escribirContrasena(contrasenya, ruta);
                    //si el usuario no existe, crea su fichero y inserta la contraseña dentro
                } catch (IOException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
    }
    //fase 5
    public void cazar_Pokemon(){
        
        ArrayList<String> poke_nombres = new ArrayList<String>();
        
        try {
            Scanner lectura = new Scanner(new File("nombres.pok.txt"));
            while(lectura.hasNextLine()) {
                String frase = lectura.nextLine();
                poke_nombres.add(frase); //cargar nombres en la arraylist
            }
            lectura.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        }
        
        //System.out.println(poke_nombres.toString());
        Random rd = new Random();
        int eleccion_pokemon = rd.nextInt(poke_nombres.size());
        
        String nombre_pokemon_azar = poke_nombres.get(eleccion_pokemon); //nombre al azar
        String ruta_pokemon = "PokeImagenes/" +nombre_pokemon_azar + ".pok.txt";
        
        //System.out.println(ruta_pokemon);
        Pokemon anyadir = new Pokemon(nombre_pokemon_azar); //crear pokemon
        poke_operaciones.cazar_pokemon(anyadir);//añadirlo a la mochila
        
        try {
            PersistenciaPokemon.visualizarPokemon(ruta_pokemon); //mostrar ASCII-Pokemon
            System.out.println(nombre_pokemon_azar + " tiene un CP de " + anyadir.getCP());
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el fichero");
        }
    }
    
    
    
}
