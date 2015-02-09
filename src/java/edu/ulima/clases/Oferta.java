
package edu.ulima.clases;

import edu.ulima.bd.ConexionDAO;


public class Oferta implements IOferta {
    
    private int idoferta;
    private Usuario comprador;
    private Subasta subasta;
    private float monto;
    private String mayor;
    private String fecha;

    public Oferta(int idoferta, Usuario comprador, Subasta subasta, float monto, String mayor, String fecha) {
        this.idoferta = idoferta;
        this.comprador = comprador;
        this.subasta = subasta;
        this.monto = monto;
        this.mayor = mayor;
        this.fecha=fecha;
    }

    public Oferta() {
    }

    public int getIdoferta() {
        return idoferta;
    }

    public void setIdoferta(int idoferta) {
        this.idoferta = idoferta;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
    @Override
    public String getMayor() {
        return mayor;
    }

    public void setMayor(String mayor) {
        this.mayor = mayor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public void nuevaOferta() {
        ConexionDAO g = new ConexionDAO();
        g.actualizarOferta(this.idoferta);
        this.subasta.setPrecioActual(this.getMonto());
        g.actualizarPrecio(this.subasta);
    }
    
    
    
}
