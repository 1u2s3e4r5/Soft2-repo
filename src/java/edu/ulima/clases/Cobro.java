
package edu.ulima.clases;


public class Cobro {
    
    private int idCobro;
    private Usuario comprador;
    private Subasta subasta;
    private String fecha;
    private float monto;

    public Cobro(int idCobro, Usuario comprador, Subasta subasta, String fecha, float monto) {
        this.idCobro = idCobro;
        this.comprador = comprador;
        this.subasta = subasta;
        this.fecha = fecha;
        this.monto = monto;
    }

    public Cobro() {
    }

    public int getIdCobro() {
        return idCobro;
    }

    public void setIdCobro(int idCobro) {
        this.idCobro = idCobro;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
}
