/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 34697
 */
public class PersistenciaPokemon {
    
    public static void visualizarPokemon(String ruta) throws FileNotFoundException{
        
        File visual_pokemon = new File(ruta);
        
        Scanner lectura = new Scanner(visual_pokemon);

        while(lectura.hasNextLine()){
            String frase = lectura.nextLine();
            System.out.println(frase);
        }
        lectura.close();
    }
}
