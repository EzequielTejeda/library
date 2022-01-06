package com.sondeos.library.dtos;

public class BookRequestDTO {

    private Long id;
    private String titulo, autor, fechaLanzamiento;
    private double precio;

    public BookRequestDTO() {
    }

    public BookRequestDTO(Long id, String titulo, String autor, String fechaLanzamiento, double precio) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precio = precio;
    }

    public Long getId() {
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
