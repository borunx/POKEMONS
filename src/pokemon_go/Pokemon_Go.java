/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokemon_go;

import Objetos.Pokemon;
import Persistencia.PersistenciaPokemon;
import Utilidades.Aleatorios;
import Utilidades.OrdenarPokemons;
import Utilidades.Logo;
import Utilidades.Rutas;
import Utilidades.ValidarUsuarios;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumne
 */
public class Pokemon_Go {
    DAOPokemon poke_operaciones;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pokemon_Go app = new Pokemon_Go();
        
        app.lanzarApp();
    }
    
    //fase 2 y 3
    public boolean Identificacion(String nombre, String contrasenya, Scanner sc){
        
        boolean accesoUsuario=false;
        String rutaUsuario = Rutas.rutaUsuario(nombre);
        
        try {
            String passwordUsuario = ValidarUsuarios.LecturaPassword(rutaUsuario);
            
            if (ValidarUsuarios.ValidarPassword(contrasenya, passwordUsuario)) {
                System.out.println("Login correcto, Hola " + nombre + "!");
                accesoUsuario = true;
            }
            else{
                System.out.println("Login incorrecto");
                accesoUsuario = false;
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el usuario");
            System.out.println("Desea crearlo?");
            String creacion = sc.nextLine();
            
            if (creacion.equalsIgnoreCase("si")) {
                try {
                    crearUsuario(nombre, contrasenya, rutaUsuario);
                    accesoUsuario = true;
                    
                } catch (IOException ex1) {
                    ex1.printStackTrace();
                }
            }
            else{
                System.out.println("Saliendo...");
                accesoUsuario = false;
            }
        }
        return accesoUsuario;
    }
    
    public void crearUsuario(String nombre, String contrasenya, String rutaUsuario) throws IOException{
        
        ValidarUsuarios.EscribirPassword(contrasenya, rutaUsuario);
        System.out.println("Usuario " + nombre + " creado correctamente");
        
    }
    
    //fase 5
    public void cazarPokemon(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> pokeNombres = new ArrayList<>();
        
        try {
            Scanner leerNombres = new Scanner(new File("nombres.pok.txt"));
            while(leerNombres.hasNextLine()) {
                String frase = leerNombres.nextLine();
                pokeNombres.add(frase);
        }
        leerNombres.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        }
        
        int eleccion_pokemon = Aleatorios.aleatorioArray(pokeNombres);
        
        String pokemonAleatorio = pokeNombres.get(eleccion_pokemon);
        String rutaPokemon = Rutas.rutaPokemon(pokemonAleatorio);
        
        Pokemon anyadir = new Pokemon(pokemonAleatorio);
        
        try {
            PersistenciaPokemon.visualizarPokemon(rutaPokemon);
            System.out.println(pokemonAleatorio + " tiene un CP de " + anyadir.getCP());
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el fichero del pokemon");
        }
        
        if(poke_operaciones.existenciaPokemon(anyadir)){
            
            System.out.println("Ya tienes a " +anyadir.getNombre());
            System.out.println("Deseas volver a cazarlo? ");
            String cazar_de_nuevo =sc.nextLine();
            
            if(cazar_de_nuevo.equalsIgnoreCase("si")){
            
                int numOCulto = Aleatorios.generarNumAleatorio(anyadir.getCP());
                System.out.println(numOCulto);
        
                System.out.println("Adivina el numero entre 1 y " + anyadir.getCP()/10);
                int intento = sc.nextInt();
        
                if (intento==numOCulto) {
                
                    System.out.println("Muy bien, has capturado a " + anyadir.getNombre());
                    poke_operaciones.cazarPokemon(anyadir);
                }
                else
                    System.out.println(anyadir.getNombre() + " se ha escapado");
            }
            else{
                System.out.println("Cancelar caza ");
            }
        }
        else{
            int numOCulto = Aleatorios.generarNumAleatorio(anyadir.getCP());
            System.out.println(numOCulto);
        
            System.out.println("Adivina el numero entre 1 y " + anyadir.getCP()/10);
            int intento = sc.nextInt();
        
            if (intento==numOCulto) {
                
                System.out.println("Muy bien, has capturado a " + anyadir.getNombre());
                poke_operaciones.cazarPokemon(anyadir);
            }
            else
                System.out.println(anyadir.getNombre() + " se ha escapado");
        }
        
        
    }
    
    public void recuperarPokemons(String nombre){
        try {
            poke_operaciones.cargarMochila(nombre);
            System.out.println("Se han cargado " + poke_operaciones.ItemsMochila() +" pokemons a tu mochila");
            
        } catch (IOException ex) {
            System.out.println(nombre.toUpperCase() + " de momento no tiene mochila");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: No se pudo cargar la mochila");
        }
    }
    
    public void Salir(String nombre){
        try {
            if (!(poke_operaciones.getMochila().isEmpty())) {
                PersistenciaPokemon.GuardarPokemons(nombre,poke_operaciones.getMochila());
                System.out.println("Guardando pokemons...");
            }
            else
                System.out.println("No guardado, la mochila esta vacia");
            
            System.out.println("Fin de partida, hasta la proxima!");
            
        } catch (IOException ex) {
            System.out.println("Ha ocurrido un error al guardar los pokemons.");
        }
    }
    
    public void VerPokemons() {
        //fase 8
        Collections.sort(poke_operaciones.getMochila(), new OrdenarPokemons());
        
        //fase 6
        if (poke_operaciones.ItemsMochila()==0) {
            System.out.println("No tienes Pokemons en la mochila");
        }
        else {
            for (Pokemon pokemon : poke_operaciones.getMochila()) {
                System.out.println(pokemon.toString());
            }
            System.out.println("Tienes " + poke_operaciones.ItemsMochila() + " pokemons en la mochila");
        }
    }
    
    //fase 9
    public void TransferirPokemon() {
        Scanner sc = new Scanner (System.in);
        System.out.println("Que pokemon quiere transferir: ");
        String transferir = sc.nextLine();
        
        String pokemon_transferido = transferir.toUpperCase().charAt(0) 
                + transferir.substring(1, transferir.length()).toLowerCase();
        
        Pokemon comprobar_existencia = new Pokemon(pokemon_transferido);
        
        if (poke_operaciones.getMochila().indexOf(comprobar_existencia)!=-1) {
            int posicion_pokemon = poke_operaciones.getMochila().indexOf(comprobar_existencia);
            System.out.println("A que usuario quiere transferir el pokemon:");
            String receptor_transferencia = sc.nextLine();
            
            if (ValidarUsuarios.existenciaUsuario(receptor_transferencia)) {
                crearTransferencia(receptor_transferencia, posicion_pokemon);
            }
            else{
                System.out.println("El usuario no existe, transferir de todos modos?");
                String aceptarTransferencia = sc.nextLine();
                
                if (aceptarTransferencia.equalsIgnoreCase("si")) {
                    crearTransferencia(receptor_transferencia, posicion_pokemon);
                }
                else
                    System.out.println("Cancelando transferencia...");
            }
        }
        else
            System.out.println("No tienes el pokemon");
    }
    
    public void crearTransferencia(String receptor_transferencia, int posicion_pokemon){
        try {
            PersistenciaPokemon.GuardarTransferencia(poke_operaciones.getMochila().get(posicion_pokemon), receptor_transferencia);
            poke_operaciones.getMochila().remove(posicion_pokemon);
            System.out.println("Pokemon transferido a " + receptor_transferencia + " con exito");
            
        } catch (IOException ex) {
            System.out.println("No se ha podido transferir el pokemon");
        }
    }
    
    public void RecibirPokemon(String nombre) {
        try {
            Pokemon pokemon_recibido = PersistenciaPokemon.RecibirTransferencia(nombre);
            poke_operaciones.getMochila().add(pokemon_recibido);
            System.out.println("Pokemon transferido a la mochila con exito");
            
            File transferencia = new File(Rutas.rutaTransferencia(nombre));
            if (transferencia.exists()) {
                System.out.println("Existe");
            }
            transferencia.delete();
            
        } catch (IOException ex) {
            System.out.println("No tienes ninguna transferencia");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error");
        }
    }
    
    //fase 4
    public void Menu() {
        System.out.println("1. Cazar Pokemon");
        System.out.println("2. Ver Pokemons");
        System.out.println("3. Transferir Pokemon");
        System.out.println("4. Recibir Pokemon");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //lanzarApp
    private void lanzarApp(){
        
        //variables
        String nombreUsuario, contrasenya;
        boolean runApp;
        
        poke_operaciones = new DAOPokemon();
        Scanner sc = new Scanner (System.in);
        
        Logo.mostrarLogo();
        
        System.out.print("Nombre de usuario: ");
        nombreUsuario = sc.nextLine();
        
        System.out.print("Contraseña: ");
        contrasenya = sc.nextLine();
        
        runApp = Identificacion(nombreUsuario, contrasenya,sc);
        
        if (runApp) {
            recuperarPokemons(nombreUsuario);
            int opcion;
        
            do {
                Menu();
                opcion=sc.nextInt();
                switch (opcion){
                case 1:
                    cazarPokemon();
                    break;
                case 2:
                    VerPokemons();
                    break; 
                case 3:
                    TransferirPokemon();
                    break; 
                case 4:
                    RecibirPokemon(nombreUsuario);
                    break; 
                case 0:
                    Salir(nombreUsuario);
                    break;
                default:
                    System.out.println("Opcion fuera de rango");
                }
                
            } while (opcion!=0);
        }
        
    }
}


