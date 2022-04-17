/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author 34697
 */
public class ValidarUsuarios {
    
    public static String LecturaPassword(String ruta) throws FileNotFoundException{
        
        Scanner lectura = new Scanner(new File(ruta));
        
        String password = lectura.nextLine();
        
        lectura.close();
        
        return password;
    }
    
    public static boolean ValidarPassword(String contrasenya, String password_fichero){
        if (contrasenya.equals(password_fichero)) {
            return true;
        }
        else
            return false;
    }
    
    public static void EscribirPassword(String entrada, String ruta) throws IOException{
        FileWriter nueva_entrada = new FileWriter(ruta);
        
        nueva_entrada.write("" + entrada);
        
        nueva_entrada.close();
        
    }
}
