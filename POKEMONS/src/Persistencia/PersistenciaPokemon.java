/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Objetos.Pokemon;
import Utilidades.Rutas;
import Utilidades.ValidarUsuarios;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 34697
 */
public class PersistenciaPokemon {
    
    public static void visualizarPokemon(String ruta) throws FileNotFoundException{
        
        Scanner lectura = new Scanner(new File(ruta));

        while(lectura.hasNextLine()){
            System.out.println(lectura.nextLine());
        }
        lectura.close();
    }
    
    public static void GuardarPokemons(String nombreUsuario, ArrayList<Pokemon> mochila) throws FileNotFoundException, IOException{
        
        String rutaMochila = Rutas.rutaMochila(nombreUsuario);
        
        FileOutputStream escribirPokemons = new FileOutputStream(rutaMochila);
        
        ObjectOutputStream pokeDatos = new ObjectOutputStream(escribirPokemons);
        
        pokeDatos.writeObject(mochila);
        
        pokeDatos.close();
    }
    
    public static ArrayList<Pokemon> RecuperarPokemons(String nombre_usuario) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        String rutaMochila = Rutas.rutaMochila(nombre_usuario);
        
        ArrayList<Pokemon> cargarMochila = new ArrayList<Pokemon>();
        
        FileInputStream ficheroPokemons = new FileInputStream(rutaMochila);
        
        ObjectInputStream lectura = new ObjectInputStream(ficheroPokemons);
        
        cargarMochila = (ArrayList<Pokemon>) lectura.readObject();
        
        lectura.close();
        
        return cargarMochila;
    }
    
    public static void GuardarTransferencia(Pokemon pokemon, String receptor_transferencia) throws FileNotFoundException, IOException {
        
        String ruta_transferencia = "Transferencias/transfer_" + receptor_transferencia;
        
        FileOutputStream escribir_transferencia = new FileOutputStream(ruta_transferencia);
        
        ObjectOutputStream pokeTransfer = new ObjectOutputStream(escribir_transferencia);
        
        pokeTransfer.writeObject(pokemon);
        
        pokeTransfer.close();
    }
    
    public static Pokemon RecibirTransferencia(String nombre_usuario) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        String rutaTransferencia = Rutas.rutaTransferencia(nombre_usuario);
        
        Pokemon pokemon;
        
        FileInputStream fichero_transferencia = new FileInputStream(rutaTransferencia);
        
        ObjectInputStream lectura_transferencia = new ObjectInputStream(fichero_transferencia);
        
        pokemon = (Pokemon) lectura_transferencia.readObject();
        
        fichero_transferencia.close();
        lectura_transferencia.close();
        
        return pokemon;
    }
    
    public static void GuardarPokemonsJSON(String nombre_usuario, ArrayList<Pokemon> mochila) throws IOException{
        String ruta_jmochila = Rutas.rutaMochilaJson(nombre_usuario);
        
        FileWriter escribir_json = new FileWriter(ruta_jmochila);
        
        Gson gson = new Gson();
        
        String mochila_json = gson.toJson(mochila);
        
        escribir_json.write("" + mochila_json);
        
        escribir_json.close();
        //System.out.println(mochila_json);
    }
    
    public static Pokemon RecuperarPokemonsJSON(String nombre_usuario, ArrayList<Pokemon> mochila) throws FileNotFoundException,JsonSyntaxException{
        String ruta_jmochila = Rutas.rutaMochilaJson(nombre_usuario);
        Scanner leerJSON = new Scanner(new File(ruta_jmochila));
        String json = "";
        
        while(leerJSON.hasNext()) {
            json = leerJSON.nextLine();
        }
        leerJSON.close();
        
        json = json.replace("[", "");
        json = json.replace("]", "");
        
        Gson gson = new Gson();
        
        Pokemon pokemon = gson.fromJson(json, Pokemon.class);
        
        Pokemon anyadir = new Pokemon(pokemon.getNombre(), pokemon.getCP());
        
        mochila.clear();
        
        return anyadir;
    }
}
