/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon_go;

import Interfaces.Operaciones_basicas;
import Objetos.Pokemon;
import Persistencia.PersistenciaPokemon;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 34697
 */
public class DAOPokemon implements Operaciones_basicas{
    private ArrayList<Pokemon> mochila;
    
    public DAOPokemon() {
        mochila = new ArrayList<Pokemon>();
    }

    @Override
    public boolean cazarPokemon(Pokemon pokemon) {
        return mochila.add(pokemon);
    }

    public ArrayList<Pokemon> getMochila() {
        return mochila;
    }

    @Override
    public ArrayList<Pokemon> listarPokemons() {
        return getMochila();
    }
    
    @Override
    public int ItemsMochila(){
        return mochila.size();
    }
    
    @Override
    public void cargarMochila(String nombre) throws IOException, FileNotFoundException, ClassNotFoundException {
        mochila = PersistenciaPokemon.RecuperarPokemons(nombre);
    }
    
    @Override
    public boolean existenciaPokemon(Pokemon pokemon){
        if(mochila.contains(pokemon)){
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public void transferirPokemon(int posicion){
        mochila.remove(posicion);
    }
    
    @Override
    public boolean recibirPokemon(Pokemon pokemon){
        return mochila.add(pokemon);
    }
    
    
}
