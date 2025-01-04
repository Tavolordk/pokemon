package com.atex.it;


public class Entrenador implements AccionesBase,EstadisticasByEntrenador{
    private String nombre;
    private int edad;
    private String apellido;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    @Override
    public void dormir() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dormir'");
    }
    @Override
    public void comer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'comer'");
    }
    @Override
    public void caminar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'caminar'");
    }
    @Override
    public void calcularPeso() {
 System.out.println("Tu peso está calculado");
    }

    
}
