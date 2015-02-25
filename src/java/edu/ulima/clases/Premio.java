
package edu.ulima.clases;


public class Premio {

    private int idpremio;
    private Usuario usuario;
    private String tipo;
    private int cantidad;
    private Subasta subasta;

    public Premio(int idpremio, Usuario usuario, String tipo, int cantidad, Subasta subasta) {
        this.idpremio = idpremio;
        this.usuario = usuario;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.subasta = subasta;
    }

    public Premio() {
    }

    public int getIdpremio() {
        return idpremio;
    }

    public void setIdpremio(int idpremio) {
        this.idpremio = idpremio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }
    
    
    
    
}
