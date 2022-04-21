/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 34697
 */
public class Aleatorios {
    
    //fase 7
    public static int generarNumAleatorio(int CP){
        Random r = new Random();
        if (CP<10) {
            CP = 10;
        }
        int aleatorio = r.nextInt(CP/10)+1;
        return aleatorio;
    }
    
    //fase 5a Random
    public static int aleatorioArray(ArrayList<String> nombres){
        Random r = new Random();
        int aleatorio = r.nextInt(nombres.size());
        return aleatorio;
    }
}
