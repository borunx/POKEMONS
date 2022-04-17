/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Objetos.Pokemon;
import Utilidades.Rutas;
import Utilidades.ValidarUsuarios;
import com.google.gson.Gson;
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
    
    public static ArrayList<Pokemon> Recuperar_Pokemons(String nombre_usuario, ArrayList<Pokemon> mochila) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        String rutaMochila = Rutas.rutaMochila(nombre_usuario);
        
        ArrayList<Pokemon> cargarMochila = new ArrayList<Pokemon>();
        
        FileInputStream ficheroPokemons = new FileInputStream(rutaMochila);
        
        ObjectInputStream lectura = new ObjectInputStream(ficheroPokemons);
        
        cargarMochila = (ArrayList<Pokemon>) lectura.readObject();
        
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
        
        return pokemon;
    }
}
