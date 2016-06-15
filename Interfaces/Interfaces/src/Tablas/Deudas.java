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
public class Deudas {
    int Id;
    double saldo;
    int Venta_id;
    boolean pendiente;

    public Deudas() {
    }

    public Deudas(int Id, double saldo, int Venta_id, boolean pendiente) {
        this.Id = Id;
        this.saldo = saldo;
        this.Venta_id = Venta_id;
        this.pendiente = pendiente;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getVenta_id() {
        return Venta_id;
    }

    public void setVenta_id(int Venta_id) {
        this.Venta_id = Venta_id;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }
    
    public void Insertar_deuda(Connection conexion, Double monto, String fecha, Double saldo, int Id_venta, boolean pendiente){
        try {
            PreparedStatement ingreso;
            ingreso=conexion.prepareStatement("INSERT INTO "+ "Deudas "+"(Id, Fecha_pago, Saldo, Ventas_ID, pendiente)");
            ingreso.setString(1, null);
            ingreso.setString(2, fecha);
            ingreso.setDouble(3, saldo);
            ingreso.setInt(4, Id_venta);
            ingreso.setBoolean(Id, pendiente);
        } catch (SQLException ex) {
            Logger.getLogger(Deudas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Deudas> obtenerTodas(Connection conexion){
        List<Deudas> deud=new ArrayList<>();
        try {
            PreparedStatement consulta=conexion.prepareStatement("SELECT Id, Saldo, Ventas_Id,Pendiente FROM Deudas ORDER BY Id");
            ResultSet resultado=consulta.executeQuery();
            while(resultado.next()){
                deud.add(new Deudas(resultado.getInt("Id"),resultado.getDouble("Saldo"), resultado.getInt("Ventas_Id"), resultado.getBoolean("Pendiente")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Deudas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deud;
        
    }
    /*
     public List<Bebida> obtenerTodas (Connection conexion){
         List<Bebida> bebidas=new ArrayList<>();
        try {
            PreparedStatement consulta=conexion.prepareStatement("SELECT Id, Nombre, Precio FROM " + "Bebida "+ "ORDER BY Nombre");
            ResultSet resultado=consulta.executeQuery();
            while(resultado.next()){
                bebidas.add(new Bebida(resultado.getInt("Id"),resultado.getString("Nombre"), resultado.getDouble("Precio")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bebida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bebidas;
     }
    */
}
