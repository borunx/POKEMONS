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
public class OrdenarPokemons implements Comparator<Pokemon>{
    
    //fase 8
    @Override
    public int compare(Pokemon pok1, Pokemon pok2) {
        int resultado = pok1.getNombre().compareTo(pok2.getNombre());
        
        if (resultado > 0) {
            return 1;
        }
        else if (resultado == 0) {
            if (pok1.getCP() > pok2.getCP()) {
                return 1;
            }
            else if (pok1.getCP() < pok2.getCP()) {
                return -1;
            }
            else{
                return 0;
            }
        }
        else
            return -1;
    }
    
}
