
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

    public Oferta(Usuario comprador, Subasta subasta, float monto) {
        this.comprador = comprador;
        this.subasta = subasta;
        this.monto = monto;
    }
      
    

    public Oferta() {
    }

    public int getIdoferta() {
        return idoferta;
    }

    public void setIdoferta(int idoferta) {
        this.idoferta = idoferta;
    }
    @Override
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
    @Override
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
        
//        g.actualizarPrecio(this.subasta);
    }
    
    @Override
    public boolean registrar(){
    ConexionDAO g = new ConexionDAO();
    return g.registrarOferta(this);
    }
    
    
}
