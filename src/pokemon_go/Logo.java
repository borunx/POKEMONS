/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon_go;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author 34697
 */
public class Logo {
    
    public static void mostrarLogo() throws FileNotFoundException{
        File logo_pokemon = new File("PokeImagenes/logo.pok.txt");
        Scanner lectura = new Scanner (logo_pokemon);

        while(lectura.hasNextLine()) {
            System.out.println(lectura.nextLine());
        }
        System.out.println("");
        lectura.close();
    }
}
