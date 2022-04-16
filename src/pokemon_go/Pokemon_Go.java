/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokemon_go;

import Objetos.Pokemon;
import Persistencia.PersistenciaPokemon;
import Utilidades.Aleatorios;
import Utilidades.CompararPokemons;
import Utilidades.Logo;
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
    
    //correr el programa
    private void lanzarApp(){
        poke_operaciones = new DAOPokemon();
        Scanner sc = new Scanner (System.in);
        
        //mostrarLogo
        Logo.mostrarLogo();
        
        //login usuario, pedir datos
        System.out.println("Nombre de usuario: ");
        String nombre = sc.nextLine();
        
        System.out.println("Contraseña: ");
        String contrasenya = sc.nextLine();
        
        boolean lanzar_programa=Identificacion(nombre, contrasenya,sc);
        
        //cargar mochila
        recuperarPokemons(nombre);
        
        if (lanzar_programa) {
            //poke_operaciones.cargarMochila(); //(fase 5c) falta poner los pokemons de user_mochila.dat en la mochila
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
                    break; 
                case 3:
                    TransferirPokemon();
                    System.out.println("No implementada");
                    break; 
                case 4:
                    RecibirPokemon(nombre);
                    System.out.println("No implementada");
                    break; 
                case 0:
                    Salir(nombre);
                    break; 
                }
            } while (opcion!=0);
        }
        
    }
    
    //fase2y3
    public boolean Identificacion(String nombre, String contrasenya, Scanner sc){
        boolean acceso_usuario=false;
        String ruta_usuario = ValidarUsuarios.ruta_Usuario(nombre);//En Windows si que importan las 
        //extensiones de los ficheros, por eso hay que añadir .txt
        
        try {
            String password = ValidarUsuarios.LecturaFichero(ruta_usuario); 
            //lee la linea del fichero y lo almacena en la variable password
            
            if (ValidarUsuarios.ValidarContrasenya(contrasenya, password)) {
                //compara la linea del fichero con la contraseña introducida por el usuario
                System.out.println("Login correcto, Hola " + nombre + "!");
                acceso_usuario=true;
            }
            else{
                System.out.println("Login incorrecto");
                acceso_usuario=false;
            }
                
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el usuario");
            System.out.println("Desea crearlo?");
            String creacion = sc.nextLine();
            if (creacion.equalsIgnoreCase("si")) {
                try {
                    ValidarUsuarios.escribirFichero(contrasenya, ruta_usuario);
                    //si el usuario no existe, crea su fichero y inserta la contraseña dentro
                    System.out.println("Usuario " + nombre + " creado correctamente");
                    acceso_usuario=true;
                } catch (IOException ex1) {
                    ex1.printStackTrace();
                }
            }
            else{
                System.out.println("Saliendo...");
                acceso_usuario=false;
            }
        }
        return acceso_usuario;
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
        System.out.println(num_adivinar);
        
        System.out.println("Adivina el numero entre 1 y " + anyadir.getCP()/10);
        int num_secreto = sc.nextInt();
        
        if (num_secreto==num_adivinar) {
            System.out.println("Muy bien, has capturado a " + anyadir.getNombre());
            poke_operaciones.cazar_pokemon(anyadir);//añadirlo a la mochila
        }
        else
            System.out.println(anyadir.getNombre() + " se ha escapado");
        
    }
    
    //fase 5c - recuperar mochila
    public void recuperarPokemons(String nombre){
        try {
            poke_operaciones.cargarMochila(nombre);
            
        } catch (IOException ex) {
            System.out.println(nombre.toUpperCase() + " de momento no tiene mochila");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: No se pudo cargar la mochila");
        }
    }
    
    //fase 5c - guardar mochila
    public void Salir(String nombre){
        try {
            if (!(poke_operaciones.getMochila().isEmpty())) {
                PersistenciaPokemon.Guardar_Pokemons(nombre,poke_operaciones.getMochila());
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
        Collections.sort(poke_operaciones.getMochila(), new CompararPokemons());
        
        //fase 6
        if (poke_operaciones.ItemsMochila()==0) {
            System.out.println("No tienes Pokemons en la mochila");
        }
        else {
            for (int i = 0; i < poke_operaciones.ItemsMochila(); i++) {
                System.out.println(poke_operaciones.getMochila().get(i).toString());
            }
        }
    }
    
    //fase 9
    public void TransferirPokemon() {
        Scanner sc = new Scanner (System.in);
        System.out.println("Que pokemon quiere transferir: ");
        String transferir = sc.nextLine();
        //Transformar el texto en el formato correcto para ser leido
        String pokemon_transferido = transferir.toUpperCase().charAt(0) 
                + transferir.substring(1, transferir.length()).toLowerCase();
        
        Pokemon comprobar_existencia = new Pokemon(pokemon_transferido);
        
        if (poke_operaciones.getMochila().indexOf(comprobar_existencia)!=-1) {
            //System.out.println("el pokemon existe");
            //System.out.println(poke_operaciones.getMochila().indexOf(comprobar_existencia));
            int posicion_pokemon = poke_operaciones.getMochila().indexOf(comprobar_existencia);
            System.out.println("A que usuario quiere transferir el pokemon:");
            String receptor_transferencia = sc.nextLine();
            try {
                PersistenciaPokemon.Guardar_Transferencia(poke_operaciones.getMochila().get(posicion_pokemon), receptor_transferencia);
                poke_operaciones.getMochila().remove(posicion_pokemon);
            } catch (IOException ex) {
                System.out.println("No se ha podido guardar el pokemon");
            }
        }
        else
            System.out.println("No tienes el pokemon");
    }
    
    public void RecibirPokemon(String nombre) {
        try {
            Pokemon pokemon_recibido = PersistenciaPokemon.Recibir_Transferencia(nombre);
            poke_operaciones.getMochila().add(pokemon_recibido);
            
            File transferencia = new File("Transferencias/transfer_" + nombre);
            transferencia.delete();
            
        } catch (IOException ex) {
            System.out.println("Error al recibir pokemon");
        } catch (ClassNotFoundException ex) {
            System.out.println("hey");
        }
    }
    
    private void mostrarMenu() {
        System.out.println("1. Cazar Pokemon");
        System.out.println("2. Ver Pokemons");
        System.out.println("3. Transferir Pokemon");
        System.out.println("4. Recibir Pokemon");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
    }
}


