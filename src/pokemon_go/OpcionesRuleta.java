/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemon_go;

import Objetos.Pokemon;
import Persistencia.PersistenciaPokemon;
import Utilidades.Aleatorios;
import Utilidades.Rutas;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author 34697
 */
public class OpcionesRuleta {
    
    public static void BorrarPokemons(int cantidad_borrar, ArrayList<Pokemon> mochila){
        for (int i = 0; i < cantidad_borrar; i++) {
            mochila.remove(i);
        }
    }
    
    public static void VaciarMochila(ArrayList<Pokemon> mochila){
        mochila.clear();
    }
    
    public static String ObtenerPokemon(ArrayList<Pokemon> mochila, File ruta) throws FileNotFoundException{
        ArrayList<String> nombresLegendarios = new ArrayList<String>();
        
        nombresLegendarios = Pokemon_Go.cargarNombres(nombresLegendarios, ruta);
        
        int eleccion_pokemon = Aleatorios.aleatorioArray(nombresLegendarios);
        
        String pokemonAleatorio = nombresLegendarios.get(eleccion_pokemon);
        String rutaPokemon = Rutas.rutaPokemon(pokemonAleatorio);
        
        Pokemon anyadir = new Pokemon(pokemonAleatorio);
        mochila.add(anyadir);
        PersistenciaPokemon.visualizarPokemon(rutaPokemon);
        
        return pokemonAleatorio;
    }
}
