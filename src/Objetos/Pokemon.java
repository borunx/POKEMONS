/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author 34697
 */
public class Pokemon implements Serializable{
    private String nombre;
    private int CP;

    public Pokemon(String nombre) {
        this.nombre = nombre;
        Random r = new Random();
        this.CP = r.nextInt(100)+1;
    }
    

    public String getNombre() {
        return nombre;
    }

    public int getCP() {
        return CP;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pokemon other = (Pokemon) obj;
        return Objects.equals(this.nombre, other.nombre);
    }

    @Override
    public String toString() {
        return "Pokemon: \n Nombre: " + this.nombre + "\n Puntos de combate: " + this.CP;
    }
    
}
