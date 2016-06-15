/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author astrid
 */
public class DetalleVenta {
    int Bebida_id;
    int Venta_id;
    int Comida_id;
    int cantidad;
    double subtotal;
    int Id;
    boolean activo;
    String cliente;

    public DetalleVenta(int Bebida_id,int Venta_id, int Comida_id, int cantidad, double subtotal, int Id, String cliente) {
        this.Bebida_id = Bebida_id;
        this.Venta_id=Venta_id;
        this.Comida_id = Comida_id;   
        this.cantidad=cantidad;
        this.subtotal = subtotal;
        this.Id = Id;
        this.activo=true;
        this.cliente=cliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public DetalleVenta() {
    }

    public int getVenta_id() {
        return Venta_id;
    }

    public void setVenta_id(int Venta_id) {
        this.Venta_id = Venta_id;
    }

    public int getBebida_id() {
        return Bebida_id;
    }

    public int getComida_id() {
        return Comida_id;
    }

    

    public double getSubtotal() {
        return subtotal;
    }

    public int getId() {
        return Id;
    }
    public void insertarDetalleVenta(Connection conexion, int cantidad, int id, int identificador, double subtotal, String Cliente){
        PreparedStatement ingreso;
        if(identificador==0) //si es bebida
            try {
                ingreso=conexion.prepareStatement("INSERT INTO "+ "Detalle_de_venta " + "(Bebida_ID, Ventas_ID, Comida_ID, Cant, subtotal, Id, Activo, Cliente) VALUES(?,?,?,?,?,?,?,?)");
                ingreso.setInt(1, id);
                ingreso.setInt(2, 0);
                ingreso.setInt(3, 0);
                ingreso.setInt(4, cantidad);  //cantidad
                ingreso.setDouble(5, subtotal);
                ingreso.setInt(6, 0);   //cantidad*precio
                ingreso.setBoolean(7,true);
                ingreso.setString(8, Cliente);
            } catch (SQLException ex) {
                Logger.getLogger(DetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        else if(identificador==1){//si es comida
            try {
                ingreso=conexion.prepareStatement("INSERT INTO "+ "Detalle_de_venta " + "(Bebida_ID, Ventas_ID, Comida_ID, Cant, subtotal, Id, Activo, Cliente) VALUES(?,?,?,?,?,?,?,?,?)");
                ingreso.setInt(1, 0);
                ingreso.setInt(2, id);
                ingreso.setInt(3, 0);
                ingreso.setInt(4, cantidad);//cantidad
                ingreso.setDouble(5, subtotal);
                ingreso.setInt(6, 0);//cantidad*precio
                ingreso.setBoolean(7,true);
                ingreso.setString(8, Cliente);
            } catch (SQLException ex) {
                Logger.getLogger(DetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    public List<DetalleVenta> ObtenerTodas(Connection conexion){
        List<DetalleVenta> detalle=new ArrayList<>();
        try {
            PreparedStatement consulta=conexion.prepareStatement("Select Bebida_Id, Ventas_Id, Comida_Id, Cant, subtotal, Id, Activo FROM Detalle_de_venta ORDER BY Id");
            ResultSet resultado=consulta.executeQuery();
            while(resultado.next()){
                detalle.add(new DetalleVenta(resultado.getInt("Bebida_Id"), resultado.getInt("Ventas_Id"), resultado.getInt("Comida_Id"), resultado.getInt("cantidad"), resultado.getDouble("Subtotal"), resultado.getInt("Id"), resultado.getString("Cliente")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalle;
        
    }
    public void Identificar(Connection co, int i, int id){
        PreparedStatement consulta;
        try {
            consulta=co.prepareStatement("UPDATE "+ "Detalle_de_venta "+"SET Ventas_id=? WHERE id=?");
            consulta.setInt(1, i);
            consulta.setInt(2, id);
            consulta.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}