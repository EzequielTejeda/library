package com.sondeos.library.dtos;

import com.sondeos.library.models.Book;

public class BookDTO {
    private long id;
    private String titulo, autor, fechaLanzamiento;
    private double precio;

    public BookDTO(Book book){
        this.id = book.getId();
        this.titulo = book.getTitulo();
        this.autor = book.getAutor();
        this.fechaLanzamiento = book.getFechaLanzamiento();
        this.precio = book.getPrecio();
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
