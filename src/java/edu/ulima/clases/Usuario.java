
package edu.ulima.clases;


public class Usuario {
    
    private long DNI;
    private String nombre;
    private String usuario;
    private String password;
    private int creditos;
    private String tipo;

    public Usuario(long DNI, String nombre, String usuario, String password, int creditos, String tipo) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.creditos = 0;
        this.tipo=tipo;
    }

    public Usuario() {
    }

    public long getDNI() {
        return DNI;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDNI(long DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Usuario{" + "DNI=" + DNI + ", nombre=" + nombre + ", usuario=" + usuario + ", password=" + password + ", creditos=" + creditos + '}';
    }
        
    
    
}
