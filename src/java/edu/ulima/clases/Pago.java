
package edu.ulima.clases;


public class Pago {
    
    private int idPago;
    private Usuario vendedor;
    private Subasta subasta;
    private String fecha;
    private float monto;

    public Pago(int idPago, Usuario vendedor, Subasta subasta, String fecha, float monto) {
        this.idPago = idPago;
        this.vendedor = vendedor;
        this.subasta = subasta;
        this.fecha = fecha;
        this.monto = monto;
    }

    public Pago() {
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
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
