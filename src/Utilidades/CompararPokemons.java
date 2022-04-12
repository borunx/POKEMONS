/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import Objetos.Pokemon;
import java.util.Comparator;

/**
 *
 * @author 34697
 */
public class CompararPokemons implements Comparator<Pokemon>{
    
    //fase 8s
    @Override
    public int compare(Pokemon o1, Pokemon o2) {
        int resultado = o1.getNombre().compareTo(o2.getNombre());
        
        if (resultado !=0) {
            return resultado;
        }
        else if (resultado == 0) {
            return 0;
        }
        else
            return -1;
    }
    
}
