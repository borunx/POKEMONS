/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokemon_go;

import Persistencia.PersistenciaPokemon;
import java.io.File;
import java.io.FileNotFoundException;
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
    
    
    public void mostrarMenu(){
        //falta implementar
    }
    
    //correr el programa
    private void lanzarApp(){
        poke_operaciones = new DAOPokemon();
        
        //login usuario
        Scanner sc = new Scanner (System.in);
        System.out.println("Nombre de usuario: ");
        String nombre = sc.nextLine();
        String ruta = "user_" + nombre + ".dat";
        
        System.out.println("Contraseña: ");
        String contraseña = sc.nextLine();
        
        File fichero = new File(ruta + ".txt"); //En Windows si que importan las 
        //extensiones de los ficheros, por eso hay que añadir .txt
        try {
            Scanner lectura = new Scanner(fichero);
            
            if (fichero.exists()) {
            while(lectura.hasNextLine()){
                String password = lectura.nextLine();
                if (contraseña.equals(password)) {
                    System.out.println("Login correcto");
                }
                else
                    System.out.println("Login incorrecto");
            }
            lectura.close();
        }
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("Usuario no encontrado");
        }
        
        //System.out.println(ruta);
        
        
        /*
                
        //opciones del programa
        int opcion = sc.nextInt();
        
        do {
            switch(opcion){
                case 1:
                    System.out.println("No implementada");
                    break;
                case 2:
                    System.out.println("No implementada");
                    break;
                case 3:
                    System.out.println("No implementada");
                    break;
                case 4:
                    System.out.println("No implementada");
                    break;
                case 0:
                    System.out.println("No implementada");
                    break;
            }
        } while (opcion!=0);*/
    }
    
    
    
}
