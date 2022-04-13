/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Objetos.Pokemon;
import Utilidades.ValidarUsuarios;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    
    public static ArrayList<Pokemon> Guardar_Pokemons(String nombre_usuario, ArrayList<Pokemon> mochila) throws FileNotFoundException, IOException{
        
        String ruta_mochila = ValidarUsuarios.ruta_Mochila(nombre_usuario);
        
        FileOutputStream escribir = new FileOutputStream(ruta_mochila);
        
        ObjectOutputStream pokeDatos = new ObjectOutputStream(escribir);
        
        pokeDatos.writeObject(mochila);
        
        return mochila;
        
    }
    
    public static boolean Borrar_Usuario(String nombre) {
        
        Scanner sc = new Scanner (System.in);
        
        String ruta_usuario = ValidarUsuarios.ruta_Usuario(nombre);
        String ruta_mochila = ValidarUsuarios.ruta_Mochila(nombre);
        
        File usuario = new File(ruta_usuario);
        File mochila = new File(ruta_mochila);
        
        
        if (usuario.exists() && mochila.exists()) {
            usuario.delete();
            mochila.delete();
            return true;
        }
        else if (usuario.exists() && !mochila.exists()) {
            usuario.delete();
            return true;
        }
        else
            return false;        
    }
}
