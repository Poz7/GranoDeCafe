/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

/**
 *
 * @author astrid
 */
public class Compras {
    public int Id;
    public int Id_comida;
    public String fecha;
    public int cantidad;

    public Compras(int Id, int Id_comida, String fecha, int cantidad) {
        this.Id = Id;
        this.Id_comida = Id_comida;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public Compras() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getId_comida() {
        return Id_comida;
    }

    public void setId_comida(int Id_comida) {
        this.Id_comida = Id_comida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
