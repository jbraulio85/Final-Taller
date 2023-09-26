package org.braulioecheverria.model;

/**
 *
 * @author Brau
 */
public class Enunciado {
    private int idEnunciado;
    private String nombreEnunciado;
    private String descripcionEnunciado;

    public Enunciado() {
    }

    public Enunciado(int idEnunciado, String nombreEnunciado, String descripcionEnunciado) {
        this.idEnunciado = idEnunciado;
        this.nombreEnunciado = nombreEnunciado;
        this.descripcionEnunciado = descripcionEnunciado;
    }

    public String getDescripcionEnunciado() {
        return descripcionEnunciado;
    }

    public void setDescripcionEnunciado(String descripcionEnunciado) {
        this.descripcionEnunciado = descripcionEnunciado;
    }

    public int getIdEnunciado() {
        return idEnunciado;
    }

    public void setIdEnunciado(int idEnunciado) {
        this.idEnunciado = idEnunciado;
    }

    public String getNombreEnunciado() {
        return nombreEnunciado;
    }

    public void setNombreEnunciado(String nombreEnunciado) {
        this.nombreEnunciado = nombreEnunciado;
    }

    @Override
    public String toString() {
        return "Enunciado{" + "idEnunciado=" + idEnunciado + ", nombreEnunciado=" + nombreEnunciado + ", descripcionEnunciado=" + descripcionEnunciado + '}';
    }
}
