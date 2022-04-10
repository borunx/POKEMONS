/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokemon_go;

import Persistencia.PersistenciaPokemon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        
        Scanner sc = new Scanner (System.in);
        //login usuario, pedir datos
        System.out.println("Nombre de usuario: ");
        String nombre = sc.nextLine();
        
        System.out.println("Contraseña: ");
        String contraseña = sc.nextLine();
        
        Identificacion(nombre, contraseña,sc);
        
    }
    
    public void Identificacion(String nombre, String contraseña, Scanner sc){
        
        String ruta = "Usuarios/user_" + nombre + ".dat.txt";//En Windows si que importan las 
        //extensiones de los ficheros, por eso hay que añadir .txt
        
        File fichero = new File(ruta); 
        try {
            Scanner lectura = new Scanner(fichero);
            
            if (fichero.exists()) {
                System.out.println("Verifica la contraseña: ");
                contraseña = sc.nextLine();
                String password = lectura.nextLine();
                if (contraseña.equals(password)) {
                    System.out.println("Login correcto, Hola " + nombre + "!");
                }
                else
                    System.out.println("Login incorrecto");
            }
            lectura.close();
        
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el usuario");
            System.out.println("Desea crearlo?");
            String creacion = sc.nextLine();
            if (creacion.equalsIgnoreCase("si")) {
                try {
                    FileWriter nuevo_usuario = new FileWriter(ruta);
                    
                    nuevo_usuario.write("" + contraseña);
                    
                    nuevo_usuario.close();
                    
                } catch (IOException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
    }
    
    
    
}
