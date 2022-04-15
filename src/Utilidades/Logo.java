/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author 34697
 */
public class Logo {
    
    
    public static void mostrarLogo(){
        File logo = new File("PokeImagenes/logo.pok.txt"); 
        try {
            Scanner sc_fitxer = new Scanner(logo);
            while (sc_fitxer.hasNext()) {
                 System.out.println(sc_fitxer.nextLine());
            }
            sc_fitxer.close();
            System.out.println("");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
