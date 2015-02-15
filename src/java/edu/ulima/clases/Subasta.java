
package edu.ulima.clases;

import edu.ulima.bd.ConexionDAO;
import java.util.ArrayList;
import java.util.List;


public class Subasta {
    
    private int idsubasta;
    private Articulo articulo;
    private String estado;
    private String fechaInicio;
    private String fechaFin;
    private float precioActual;
    private List<IOferta> ofertas;

    public Subasta(int idsubasta, Articulo articulo, String estado, String fechaInicio, String fechaFin, float precioActual) {
        this.idsubasta = idsubasta;
        this.articulo = articulo;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioActual = precioActual;
        ofertas = new ArrayList<>();
    }

    public Subasta() {
    }

    public boolean  agregarOferta(IOferta oferta){
    this.setPrecioActual(oferta.getMonto());
    ConexionDAO dao = new ConexionDAO();
    //System.out.println(this.getPrecioActual() + " 1");
    dao.actualizarPrecio(this);
    //System.out.println(this.getPrecioActual() + " 2");
    for (IOferta o : ofertas){
        if (o.getMayor().equalsIgnoreCase("true")){
        o.nuevaOferta();
        }
    }
    
    ofertas.add(oferta);
    return oferta.registrar();
    
    }
    
    public List<IOferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<IOferta> ofertas) {
        this.ofertas = ofertas;
    }

    
    public int getIdsubasta() {
        return idsubasta;
    }

    public void setIdsubasta(int idsubasta) {
        this.idsubasta = idsubasta;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public float getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(float precioActual) {
        this.precioActual = precioActual;
    }
    
    
    
    
}
