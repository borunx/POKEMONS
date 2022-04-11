/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon_go;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author 34697
 */
public class ValidarUsuario {
    
    public static String LecturaFichero(String ruta) throws FileNotFoundException{
        
        Scanner lectura = new Scanner(new File(ruta));
        
        String password = lectura.nextLine();
        
        lectura.close();
        
        return password;
    }
    
    public static boolean ValidarContrasenya(String contrasenya, String password_fichero){
        if (contrasenya.equals(password_fichero)) {
            return true;
        }
        else
            return false;
    }
    
    public static void escribirContrasena(String contrasenya, String ruta) throws IOException{
        FileWriter nuevo_usuario = new FileWriter(ruta);
        
        nuevo_usuario.write("" + contrasenya);
        
        nuevo_usuario.close();
        
    }
}
