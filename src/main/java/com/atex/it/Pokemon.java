package com.atex.it;

public class Pokemon implements AccionesBase,Estadisticas, Ataque{
    private int id;
    private String imagenURL;
    private Entrenador entrenador;
    private double peso;
    private double medidas;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImagenURL() {
        return imagenURL;
    }
    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }
    public Entrenador getEntrenador() {
        return entrenador;
    }
    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public double getMedidas() {
        return medidas;
    }
    public void setMedidas(double medidas) {
        this.medidas = medidas;
    }
    @Override
    public void dormir() {
 
    }
    @Override
    public void comer() {
System.out.println("Este pokemon puede comer");
    }
    @Override
    public void caminar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'caminar'");
    }
    @Override
    public void ataquePrincipal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ataquePrincipal'");
    }
    @Override
    public double calcularPeso() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularPeso'");
    }
    @Override
    public double calcularVolumen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularVolumen'");
    }
    @Override
    public double calcularEnergia() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularEnergia'");
    }


}
