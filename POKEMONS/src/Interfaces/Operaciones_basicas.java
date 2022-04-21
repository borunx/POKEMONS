/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import Objetos.Pokemon;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 34697
 */
public interface Operaciones_basicas {
    //fase 4
    public boolean cazarPokemon(Pokemon pokemon);
    public ArrayList<Pokemon> listarPokemons();
    public int ItemsMochila();
    public void cargarMochila(String nombre) throws IOException, FileNotFoundException, ClassNotFoundException;
    public boolean existenciaPokemon(Pokemon pokemon);
    public void transferirPokemon(int posicion);
    public boolean recibirPokemon(Pokemon pokemon);
}
