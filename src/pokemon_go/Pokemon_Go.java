/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokemon_go;

import Objetos.Pokemon;
import Persistencia.PersistenciaPokemon;
import Utilidades.Aleatorios;
import Utilidades.CompararPokemons;
import Utilidades.ValidarUsuarios;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Random;
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
    
    //correr el programa
    private void lanzarApp(){
        poke_operaciones = new DAOPokemon();
        Scanner sc = new Scanner (System.in);
        
        //login usuario, pedir datos
        System.out.println("Nombre de usuario: ");
        String nombre = sc.nextLine();
        
        System.out.println("Contraseña: ");
        String contrasenya = sc.nextLine();
        
        Identificacion(nombre, contrasenya,sc);
        
        int opcion;
        
        do {
            mostrarMenu();
            opcion=sc.nextInt();
            switch (opcion){
            case 1:
                cazar_Pokemon(sc);
                break;
            case 2:
                VerPokemons();
                //System.out.println("No implementada");
                break; 
            case 3:
                System.out.println("No implementada");
                break; 
            case 4:
                System.out.println("No implementada");
                break; 
            case 5:
                BorrarUsuario(nombre);
                //System.out.println("No implementada");
                break; 
            case 0:
                System.out.println("Fin de partida, hasta la proxima.");
                break; 
            
            }
        } while (opcion!=0);
    }
    
    //fase2y3
    public void Identificacion(String nombre, String contrasenya, Scanner sc){
        
        String ruta_usuario = ValidarUsuarios.ruta_Usuario(nombre);//En Windows si que importan las 
        //extensiones de los ficheros, por eso hay que añadir .txt
        
        try {
            String password = ValidarUsuarios.LecturaFichero(ruta_usuario); 
            //lee la linea del fichero y lo almacena en la variable password
            
            if (ValidarUsuarios.ValidarContrasenya(contrasenya, password)) {
                //compara la linea del fichero con la contraseña introducida por el usuario
                System.out.println("Login correcto, Hola " + nombre + "!");
            }
            else
                System.out.println("Login incorrecto");
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el usuario");
            System.out.println("Desea crearlo?");
            String creacion = sc.nextLine();
            if (creacion.equalsIgnoreCase("si")) {
                try {
                    ValidarUsuarios.escribirContrasena(contrasenya, ruta_usuario);
                    //si el usuario no existe, crea su fichero y inserta la contraseña dentro
                    System.out.println("Usuario " + nombre + " creado correctamente");
                } catch (IOException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
    }
    //fase 5
    public void cazar_Pokemon(Scanner sc){
        
        ArrayList<String> poke_nombres = new ArrayList<>();
        
        try {
            Scanner lectura = new Scanner(new File("nombres.pok.txt"));
            while(lectura.hasNextLine()) {
                String frase = lectura.nextLine();
                poke_nombres.add(frase); //cargar nombres en la arraylist
        }
        lectura.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        }
        
        int eleccion_pokemon = Aleatorios.aleatorioArray(poke_nombres);
        
        String nombre_pokemon_azar = poke_nombres.get(eleccion_pokemon); //nombre al azar
        String ruta_pokemon = "PokeImagenes/" +nombre_pokemon_azar + ".pok.txt";
        
        //System.out.println(ruta_pokemon);
        Pokemon anyadir = new Pokemon(nombre_pokemon_azar); //crear pokemon
        
        try {
            PersistenciaPokemon.visualizarPokemon(ruta_pokemon); //mostrar ASCII-Pokemon
            System.out.println(nombre_pokemon_azar + " tiene un CP de " + anyadir.getCP());
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el fichero");
        }
        
        int num_adivinar = Aleatorios.generarNumAleatorio(anyadir.getCP());
        //System.out.println(num_adivinar);
        
        System.out.println("Adivina el numero entre 1 y " + anyadir.getCP()/10);
        int num_secreto = sc.nextInt();
        
        if (num_secreto==num_adivinar) {
            System.out.println("Muy bien, has capturado a " + anyadir.getNombre());
            poke_operaciones.cazar_pokemon(anyadir);//añadirlo a la mochila
        }
        else
            System.out.println(anyadir.getNombre() + " se ha escapado");
        
    }
    
    public void VerPokemons() {
        //fase 8
        Collections.sort(poke_operaciones.getMochila(), new CompararPokemons());
        
        //fase 6
        
    }
    
    //fase 11 - Jonatan
    public boolean BorrarUsuario(String nombre) {
        Scanner sc = new Scanner (System.in);
        boolean borrar_usuario;
        System.out.println("IMPORTANTE! Se borrará este usuario y su mochila");
        System.out.println("Desea continuar?");
        String confirmacion= sc.nextLine();
        
        if (confirmacion.equalsIgnoreCase("si")) {
            boolean borrado = PersistenciaPokemon.Borrar_Usuario(nombre);

            if (borrado) {
                System.out.println("Se ha borrado el usuario correctamente");
                System.out.println("Cerrando sesión...");
                borrar_usuario = true;
                System.exit(0);
            }
            else
                System.out.println("Error, el usuario no puede ser borrado");
                borrar_usuario = false;
        }
        else{
            System.out.println("Cancelando...");
            borrar_usuario = false;
        }
        return borrar_usuario;
    }
    
    private void mostrarMenu() {
        System.out.println("1. Cazar Pokemon");
        System.out.println("2. Ver Pokemons");
        System.out.println("3. Transferir Pokemon");
        System.out.println("4. Recibir Pokemon");
        System.out.println("5. Borrar Usuario");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
    }
    
}


