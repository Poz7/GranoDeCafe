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
public class Bebida {
    int Id;
    String Nombre;
    Double Precio;
    int identificador;

    public Bebida() {
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public Bebida(int Id, String Nombre, Double Precio) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.identificador=0;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double Precio) {
        this.Precio = Precio;
    }
    public Bebida VerificarExistenciaDeBebida(Connection conexion, String Nombre){
        Bebida bebida=null;
        try {
            PreparedStatement consulta= conexion.prepareStatement("SELECT ID, nombre, Precio FROM "+"Bebida "+ "WHERE nombre= ?");
            consulta.setString(1, Nombre);
            ResultSet resultado=consulta.executeQuery();
            while(resultado.next()){
                bebida=new Bebida(resultado.getInt("Id"), Nombre,resultado.getDouble("Precio"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bebida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bebida;
    }
    
    public Bebida InsertarBebida(Connection conexion,String nombre, Double precio){
        
        try {
            PreparedStatement ingreso;
            ingreso=conexion.prepareStatement("INSERT INTO "+ "Bebida "+ "(Id, Nombre, Precio) VALUES(?,?,?)");
            ingreso.setString(1, null);
            ingreso.setString(2, nombre);
            ingreso.setDouble(3, precio);
            ingreso.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bebida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
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
     public void ModificarBebida(Connection conexion, String Nombre, Double Precio, int id){
         PreparedStatement consulta;
        try {
            consulta=conexion.prepareStatement("UPDATE "+"Bebida "+" SET nombre=?, Precio=? WHERE id=?");
            consulta.setString(1, Nombre);
            consulta.setDouble(2, Precio);
            consulta.setInt(3, id);
            System.out.println(id);
            consulta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bebida.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
