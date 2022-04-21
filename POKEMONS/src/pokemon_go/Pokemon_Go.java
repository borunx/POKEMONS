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
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
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
    
    //fase 2 y 3
    public boolean Identificacion(String nombre, String contrasenya, Scanner sc){
        
        boolean accesoUsuario=false;
        String rutaUsuario = Rutas.rutaUsuario(nombre);
        
        try {
            String passwordUsuario = ValidarUsuarios.LecturaPassword(rutaUsuario);
            
            if (ValidarUsuarios.ValidarPassword(contrasenya, passwordUsuario)) {
                System.out.println("Login correcto, Hola " + nombre.toUpperCase() + "!");
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
                    System.out.println("Error No se puedo crear el usuario");
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
        System.out.println("Usuario " + nombre.toUpperCase() + " creado correctamente");
        
    }
    
    //fase 5
    public void cazarPokemon(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> pokeNombres = new ArrayList<>();
        
        File nombrePokemons = new File("nombres.pok");
        pokeNombres = cargarNombres(pokeNombres, nombrePokemons);
        
        int eleccion_pokemon = Aleatorios.aleatorioArray(pokeNombres);
        
        String pokemonAleatorio = pokeNombres.get(eleccion_pokemon);
        String rutaPokemon = Rutas.rutaPokemon(pokemonAleatorio);
        
        Pokemon anyadir = new Pokemon(pokemonAleatorio);
        
        try {
            PersistenciaPokemon.visualizarPokemon(rutaPokemon);
            System.out.println(pokemonAleatorio.toUpperCase() + " tiene un CP de " + anyadir.getCP());
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el fichero del pokemon");
        }
        
        //fase 10
        if(poke_operaciones.existenciaPokemon(anyadir)){
            
            System.out.println("Ya tienes a " + anyadir.getNombre().toUpperCase());
            System.out.println("Deseas volver a cazarlo? ");
            String cazar_de_nuevo =sc.nextLine();
            
            if(cazar_de_nuevo.equalsIgnoreCase("si")){
                AdivinarNumPokemon(anyadir, sc);
            }
            else
                System.out.println("Caza cancelada");
        }
        else{
            AdivinarNumPokemon(anyadir, sc);
        }
        
    }
    
    public static ArrayList<String> cargarNombres(ArrayList<String> nombres, File ruta){
        try {
            Scanner leerNombres = new Scanner(ruta);
            while(leerNombres.hasNextLine()) {
                String frase = leerNombres.nextLine();
                nombres.add(frase);
        }
        leerNombres.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        }
        return nombres;
    }
    
    //fase 7
    public void AdivinarNumPokemon(Pokemon anyadir, Scanner sc){
        int CP = anyadir.getCP();
        int numOCulto = Aleatorios.generarNumAleatorio(CP);
        //System.out.println(numOCulto);

        if (CP<10) {
            CP = 10;
        }
        
        if (CP/10==1) {
            System.out.println(anyadir.getNombre().toUpperCase() + " es debil, capturado correctamente");
            poke_operaciones.cazarPokemon(anyadir);
        }
        else{
            try{
                System.out.println("Adivina el numero entre 1 y " + CP/10);
                int intento = sc.nextInt();

                if (intento==numOCulto) {

                    System.out.println("Muy bien, has capturado a " + anyadir.getNombre().toUpperCase());
                    poke_operaciones.cazarPokemon(anyadir);
                }
                else
                    System.out.println(anyadir.getNombre().toUpperCase() + " se ha escapado");
                
            } catch(InputMismatchException e){
                System.out.println("Solo se aceptan numeros");
            }
            
        }
    }
    
    //fase 5c
    public void recuperarPokemons(String nombre){
        try {
            poke_operaciones.cargarMochila(nombre);
            if (poke_operaciones.ItemsMochila()!=0) {
                System.out.println("Se han cargado " + poke_operaciones.ItemsMochila() +" pokemons a tu mochila");
            }
            else
                System.out.println("La mochila esta vacia");
            
            
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
            else{
                PersistenciaPokemon.GuardarPokemons(nombre,poke_operaciones.getMochila());
                System.out.println("Se guardara la mochila vacia...");
            }
                
            System.out.println("Fin de partida, hasta la proxima!");
            
        } catch (IOException ex) {
            System.out.println("Ha ocurrido un error al guardar los pokemons.");
        }
    }
    
    public void VerMochila() {
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
        if (poke_operaciones.getMochila().isEmpty()) {
            System.out.println("No tienes pokemon para transferir");
        }
        else{
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
        
    }
    
    public void crearTransferencia(String receptor_transferencia, int posicion_pokemon){
        try {
            PersistenciaPokemon.GuardarTransferencia(poke_operaciones.getMochila().get(posicion_pokemon), receptor_transferencia);
            poke_operaciones.transferirPokemon(posicion_pokemon);
            System.out.println("Pokemon transferido a " + receptor_transferencia.toUpperCase() + " con exito");
            
        } catch (IOException ex) {
            System.out.println("No se ha podido transferir el pokemon");
        }
    }
    
    public void RecibirPokemon(String nombre) {
        System.out.println("IMPORTANTE: En Windows aveces no borra el fichero de transferencia al recibirlo, en Linux si");
        try {
            Pokemon pokemon_recibido = PersistenciaPokemon.RecibirTransferencia(nombre);
            poke_operaciones.recibirPokemon(pokemon_recibido);
            System.out.println("Pokemon transferido a la mochila con exito");
            
            File transferencia = new File(Rutas.rutaTransferencia(nombre));
            transferencia.delete(); //En Windows no borra el fichero al recibirlo
            //En linux si que borra el fichero al recibirlo
        } catch (IOException ex) {
            System.out.println("No tienes ninguna transferencia");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error");
        }
    }
    
    //fase 4
    public void Menu() {
        System.out.println("1. Cazar Pokemon");
        System.out.println("2. Ver Mochila");
        System.out.println("3. Transferir Pokemon");
        System.out.println("4. Recibir Pokemon");
        System.out.println("5. Ruleta Pokemon");
        System.out.println("6. Historial de Jugadores");
        System.out.println("7. Guardar Pokemons en JSON");
        System.out.println("8. Recuperar Pokemons en JSON");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
    }
    
    //fase 11
    public void ruletaPokemon(){
        if (poke_operaciones.ItemsMochila()<3) {
            System.out.println("Necesitas un minimo de 3 Pokemon para tirar de la ruleta");
        }
        else{
            Random r = new Random();
            int aleatorio = r.nextInt(5)+1;
            menuRuleta();
            System.out.println("Te ha tocado el numero " + aleatorio);
            switch (aleatorio){
                case 1:
                    OpcionesRuleta.BorrarPokemons(1, poke_operaciones.getMochila());
                    System.out.println("Se te ha borrado 1 Pokemon");
                    break;
                case 2:
                    PokemonLegendario(poke_operaciones.getMochila());
                    break;
                case 3:
                    OpcionesRuleta.BorrarPokemons(2, poke_operaciones.getMochila());
                    System.out.println("Se te han borrado 2 Pokemon");
                    break;
                case 4:
                    PokemonComun(poke_operaciones.getMochila());
                    break;
                case 5:
                    OpcionesRuleta.VaciarMochila(poke_operaciones.getMochila());
                    System.out.println("Se te ha vaciado la mochila");
                    break;
            }
        }
    }
    
    public void menuRuleta(){
        System.out.println("####Recompensas/Castigos");
        System.out.println("  1 - Borrar 1 Pokemon ");
        System.out.println("  2 - Pokemon Legendario");
        System.out.println("  3 - Borrar 2 Pokemon ");
        System.out.println("  4 - Pokemon comun");
        System.out.println("  5 - Vaciar mochila ");
    }
    
    public void PokemonLegendario(ArrayList<Pokemon> mochila){
        try {
            File rutaLegendaria = new File("pokemonsLegendarios.pok");
            String pokeLegendario = OpcionesRuleta.ObtenerPokemon(mochila,rutaLegendaria);
            System.out.println("Felicidades! Has conseguido el pokemon legendario " + pokeLegendario);
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontra el fichero");
        }
    }
    
    public void PokemonComun(ArrayList<Pokemon> mochila){
        try {
            File rutaLegendaria = new File("nombres.pok");
            String pokeLegendario = OpcionesRuleta.ObtenerPokemon(mochila,rutaLegendaria);
            System.out.println("Has conseguido un " + pokeLegendario);
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontra el fichero");
        }
    }
    
    //fase 13
    public void historialJugadores(){
        File mochila = new File("Mochilas");
        System.out.println("Historial de Jugadores:");
        for (File file : mochila.listFiles()) {
            if (!file.isDirectory()) {
                String jugador = file.getName().replace("_mochila.dat", "").toUpperCase();
                System.out.println(" " + jugador);
            }
        }
    }
    
    //fase 14
    public void GuardarJSON(String nombre){
        if (poke_operaciones.ItemsMochila()==0) {
            System.out.println("No hay pokemons en la mochila para guardar.");
        }
        else{
            try {
                PersistenciaPokemon.GuardarPokemonsJSON(nombre, poke_operaciones.getMochila());
                System.out.println("Guardando Pokemons en mochila JSON...");
                
            } catch (IOException ex) {
                System.out.println("Error: No se ha podido crear el fichero .json");
            }
        }
    }
    
    public void RecuperarMochilaJSON(String nombre){
        System.out.println("IMPORTANTE: Esta opción solo funciona con un solo Pokemon en la mochila JSON");
        try {
            Pokemon anyadir = PersistenciaPokemon.RecuperarPokemonsJSON(nombre,poke_operaciones.getMochila());
            poke_operaciones.recibirPokemon(anyadir);
            
            System.out.println("Se ha cargado " + poke_operaciones.ItemsMochila() + " Pokemon de tu mochila JSON");
            
        } catch (FileNotFoundException ex) {
            System.out.println("No tienes mochila JSON");
        } catch (JsonSyntaxException ex){
            System.out.println("No se puede cargar mas de un pokemon");
        }
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
            try{
                do {
                    Menu();
                    opcion=sc.nextInt();
                    switch (opcion){
                        case 1:
                            cazarPokemon();
                            break;
                        case 2:
                            VerMochila();
                            break; 
                        case 3:
                            TransferirPokemon();
                            break; 
                        case 4:
                            RecibirPokemon(nombreUsuario);
                            break; 
                        case 5:
                            ruletaPokemon();
                            break;
                        case 6:
                            historialJugadores();
                            break;
                        case 7:
                            GuardarJSON(nombreUsuario);
                            break;
                        case 8:
                            RecuperarMochilaJSON(nombreUsuario);
                            break;
                        case 0:
                            Salir(nombreUsuario);
                            break;
                        default:
                            System.out.println("Opcion fuera de rango");
                    }

                } while (opcion!=0);
            } catch(InputMismatchException e){
                System.out.println("Error: solo se aceptan numeros. \nSe guardar lo hecho hasta ahora.");
                Salir(nombreUsuario);
            }
            
        }
        
    }
}


