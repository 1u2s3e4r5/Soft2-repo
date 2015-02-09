
package edu.ulima.clases;

import java.util.ArrayList;
import java.util.List;


public class Subasta {
    
    private int Subasta;
    private Usuario vendedor;
    private String nombre;
    private String descripcion;
    private float precioBase;
    private float precioActual;
    private Oferta mayorOferta;
    private byte[] imagen;
    private boolean disponible;
    private String tipoSubasta;
    private List<Oferta> ofertas;

    public Subasta(int Subasta, Usuario vendedor, String descbreve, String descripcion, float precioBase, float precioActual, Oferta mayorOferta, byte[] imagen, boolean disponible, String tipoSubasta, List<Oferta> ofertas) {
        this.Subasta = Subasta;
        this.vendedor = vendedor;
        this.nombre = descbreve;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.precioActual = precioActual;
        this.mayorOferta = mayorOferta;
        this.imagen = imagen;
        this.disponible = disponible;
        this.tipoSubasta = tipoSubasta;
        this.ofertas = ofertas;
    }

    public Subasta() {
       ofertas = new ArrayList<Oferta>();
    }

    public int getSubasta() {
        return Subasta;
    }

    public void setSubasta(int Subasta) {
        this.Subasta = Subasta;
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

    public float getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(float precioBase) {
        this.precioBase = precioBase;
    }

    public float getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(float precioActual) {
        this.precioActual = precioActual;
    }

    public Oferta getMayorOferta() {
        return mayorOferta;
    }

    public void setMayorOferta(Oferta mayorOferta) {
        this.mayorOferta = mayorOferta;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getTipoSubasta() {
        return tipoSubasta;
    }

    public void setTipoSubasta(String tipoSubasta) {
        this.tipoSubasta = tipoSubasta;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    
    
    
    
}
