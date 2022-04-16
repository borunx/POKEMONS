/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Objetos.Pokemon;
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
    
    public static void Guardar_Pokemons(String nombre_usuario, ArrayList<Pokemon> mochila) throws FileNotFoundException, IOException{
        
        String ruta_mochila = ValidarUsuarios.ruta_Mochila(nombre_usuario);
        
        FileOutputStream escribir = new FileOutputStream(ruta_mochila);
        
        ObjectOutputStream pokeDatos = new ObjectOutputStream(escribir);
        
        pokeDatos.writeObject(mochila);
        
        pokeDatos.close();
    }
    
    public static ArrayList<Pokemon> Recuperar_Pokemons(String nombre_usuario, ArrayList<Pokemon> mochila) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        String ruta_mochila = ValidarUsuarios.ruta_Mochila(nombre_usuario);
        
        ArrayList<Pokemon> cargar_mochila = new ArrayList<Pokemon>();
        
        FileInputStream fichero = new FileInputStream(ruta_mochila);
        
        ObjectInputStream lectura = new ObjectInputStream(fichero);
        
        cargar_mochila = (ArrayList<Pokemon>) lectura.readObject();
        
        return cargar_mochila;
    }
    
    public static boolean Borrar_Usuario(String nombre) {
        
        String ruta_usuario = ValidarUsuarios.ruta_Usuario(nombre);
        
        File usuario = new File(ruta_usuario);
        
        if (usuario.exists()) {
            usuario.delete();
            return true;
        }
        else
            return false;
    }
    
    public static void Guardar_Transferencia(Pokemon pokemon, String receptor_transferencia) throws FileNotFoundException, IOException {
        
        String ruta_transferencia = "Transferencias/transfer_" + receptor_transferencia;
        
        FileOutputStream escribir_transferencia = new FileOutputStream(ruta_transferencia);
        
        ObjectOutputStream pokeTransfer = new ObjectOutputStream(escribir_transferencia);
        
        pokeTransfer.writeObject(pokemon);
        
        pokeTransfer.close();
    }
    
    public static Pokemon Recibir_Transferencia(String nombre_usuario) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        String ruta_transferencia = "Transferencias/transfer_" + nombre_usuario;
        
        Pokemon pokemon;
        
        FileInputStream fichero_transferencia = new FileInputStream(ruta_transferencia);
        
        ObjectInputStream lectura_transferencia = new ObjectInputStream(fichero_transferencia);
        
        pokemon = (Pokemon) lectura_transferencia.readObject();
        
        return pokemon;
    }
    
    
}
