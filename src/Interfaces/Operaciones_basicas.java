/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import Objetos.Pokemon;
import java.util.ArrayList;

/**
 *
 * @author 34697
 */
public interface Operaciones_basicas {
    
    public boolean cazar_pokemon(Pokemon pokemon); //metodo para agregar pokemons a la mochila
    public ArrayList<Pokemon> listar_pokemons(); // devuelve el arraylist para utilizarlo en la app
    public int transferir_pokemon();
    public void recibir_pokemon();
}
