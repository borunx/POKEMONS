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
    //fase 1
    public static void mostrarLogo(){
        File logo = new File("PokeImagenes/Logo.pok"); 
        try {
            Scanner leer_logo = new Scanner(logo);
            while (leer_logo.hasNext()) {
                 System.out.println(leer_logo.nextLine());
            }
            leer_logo.close();
            System.out.println("");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
