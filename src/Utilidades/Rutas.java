/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

/**
 *
 * @author 34697
 */
public class Rutas {
    
    public static String rutaUsuario(String nombre) {
        
        String rutaUsuario = "Usuarios/user_" + nombre + ".dat";
        return rutaUsuario;
    }
    
    public static String rutaMochila(String nombre) {
        
        String rutaMochila = "Mochilas/" + nombre + "_mochila.dat";
        return rutaMochila;
    }
    
    public static String rutaMochilaJson(String nombre) {
        
        String rutaJMochila = "MochilasJSON/" + nombre + "_jmochila.json";
        return rutaJMochila;
    }
    
    public static String rutaPokemon(String nombrePokemon){
        String rutaPokemon = "PokeImagenes/" +nombrePokemon + ".pok";
        return rutaPokemon;
    }
    
    public static String rutaTransferencia(String nombre){
        String receptorTransferencia = "Transferencias/transfer_" + nombre;
        return receptorTransferencia;
    }
}
