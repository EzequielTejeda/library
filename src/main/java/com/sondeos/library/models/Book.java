package com.sondeos.library.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String titulo, autor, fechaLanzamiento;
    private double precio;

    public Book(){
    }

    public Book(String titulo, String autor, String fechaLanzamiento, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
