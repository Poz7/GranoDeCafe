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
public class Venta {
    int Id;
    String Fecha;
    double Monto;
    int Encargado_id;
    int Cliente_Id;
    boolean anulado;
    boolean credito;

    public Venta() {
    }

    public Venta(int Id, String Fecha, double Monto, int Encargado_id, int Cliente_Id, boolean anulado, boolean credito) {
        this.Id = Id;
        this.Fecha = Fecha;
        this.Monto = Monto;
        this.Encargado_id = Encargado_id;
        this.Cliente_Id = Cliente_Id;
        this.anulado = anulado;
        this.credito = credito;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double Monto) {
        this.Monto = Monto;
    }

    public int getEncargado_id() {
        return Encargado_id;
    }

    public void setEncargado_id(int Encargado_id) {
        this.Encargado_id = Encargado_id;
    }

    public int getCliente_Id() {
        return Cliente_Id;
    }

    public void setCliente_Id(int Cliente_Id) {
        this.Cliente_Id = Cliente_Id;
    }

    public boolean isAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public boolean isCredito() {
        return credito;
    }

    public void setCredito(boolean credito) {
        this.credito = credito;
    }
    public void InsertarVenta(Connection conexion, Double Monto, String Encargado, String Cliente, boolean credito){
        if(credito==false){
         try {
            PreparedStatement ingreso;                     
            ingreso=conexion.prepareStatement("INSERT INTO "+ "Ventas " + "(Id, Fecha, Monto, Encargado, Cliente, Anulado, Credito) VALUES(?,?,?,?,?,?,?)");
            ingreso.setString(1, null);
            ingreso.setString(2, null);
            ingreso.setDouble(3, Monto);
            ingreso.setString(4, Encargado);
            ingreso.setString(5, Cliente);
            ingreso.setBoolean(6, false);
            ingreso.setBoolean(7, credito);
            ingreso.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Comida.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else if(credito==true){
             try {
            PreparedStatement ingreso;                     
            ingreso=conexion.prepareStatement("INSERT INTO "+ "Ventas " + "(Id, Fecha, Monto, Encargado, Cliente, Anulado, Credito) VALUES(?,?,?,?,?,?,?)");
            ingreso.setString(1, null);
            ingreso.setString(2, null);
            ingreso.setDouble(3, Monto);
            ingreso.setString(4, Encargado);
            ingreso.setString(5, Cliente);
            ingreso.setBoolean(6, false);
            ingreso.setBoolean(7, credito);
            ingreso.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Comida.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        }
    }
    public List<Venta> obtenerTodas(Connection conexion){
        List<Venta> ven=new ArrayList<>();
        try {
            PreparedStatement consulta =conexion.prepareStatement("Select Id, Fecha, Monto, Encargado, Cliente, Anulado, Credito FROM Ventas ORDER BY Id");
            ResultSet resultado=consulta.executeQuery();
            while(resultado.next()){
                ven.add((new Venta(resultado.getInt("Id"),resultado.getString("Fecha"), resultado.getDouble("Monto"), resultado.getInt("Encargado"),resultado.getInt("Cliente"),resultado.getBoolean("Anulado"), resultado.getBoolean("Credito"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ven;
    }
    public Venta ObtenerEspecifica(Connection conexion, int id){
        Venta vent=new Venta();
        try {
            PreparedStatement consulta=conexion.prepareStatement("Select Id, Fecha, Monto, Encargado, Cliente, Anulado, Credito FROM Ventas WHERE id=?");
            consulta.setInt(1, id);
            ResultSet resultado=consulta.executeQuery();
            vent.setId(resultado.getInt("Id"));
            vent.setFecha(resultado.getString("Fecha"));
            vent.setMonto(resultado.getDouble("Monto"));
            vent.setEncargado_id(resultado.getInt("Encargado"));
            vent.setCliente_Id(resultado.getInt("Cliente"));
            vent.setAnulado(resultado.getBoolean("Anulado"));
            vent.setCredito(resultado.getBoolean("credito"));
       } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vent;
    }
    /*
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
    */
    
}
