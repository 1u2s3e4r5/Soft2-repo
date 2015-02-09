
package edu.ulima.clases;

import java.util.ArrayList;
import java.util.List;


public class Articulo {
    
    private int idarticulo;
    private Usuario vendedor;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String fechaInicio;
    private String fechaFin;
    private byte[] foto;
    private float precioBase;

    public Articulo() {
    }

    public Articulo(int idarticulo, Usuario vendedor, String nombre, String descripcion, String tipo, String fechaInicio, String fechaFin, byte[] foto, float precioBase) {
        this.idarticulo = idarticulo;
        this.vendedor = vendedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.foto = foto;
        this.precioBase = precioBase;
    }

    

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public float getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(float precioBase) {
        this.precioBase = precioBase;
    }
    

}
