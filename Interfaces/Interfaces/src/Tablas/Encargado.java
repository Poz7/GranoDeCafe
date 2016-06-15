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
public class Encargado {
    int Id;
    String Nombre;
    String Password;
    String Cargo;

    public Encargado() {
    }

    public Encargado(int Id, String Nombre, String Password, String Cargo) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Password = Password;
        this.Cargo = Cargo;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }
    
    
    public void InsertarEncargado(Connection conexion, String Nombre, String Cargo, String Pasword){
        try {
            PreparedStatement ingreso;                     
            ingreso=conexion.prepareStatement("INSERT INTO "+ "Encargado" + "(ID, Nombre, Cargo, Pasword) VALUES(?,?,?,?)");
            ingreso.setString(1, null);
            ingreso.setString(2, Nombre);
            ingreso.setString(3, Cargo);
            ingreso.setString(4, Pasword);
            ingreso.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Encargado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Encargado VerificarExistenciaDeEncargado(Connection conexion, String Nombre){
        Encargado encargado = null;
        try {
            PreparedStatement consulta= conexion.prepareStatement("SELECT Id, Nombre, Pasword, Cargo FROM " + "Encargado "+ "WHERE Nombre = ?");
            consulta.setString(1, Nombre);
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
               encargado=new Encargado(resultado.getInt("Id"), Nombre, resultado.getNString("pasword"), resultado.getString("cargo"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Encargado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return encargado;
    }
    
    
    
    public List<Encargado> obtenerTodas (Connection conexio){
        List<Encargado> encargad =new ArrayList<>();
        try {
            PreparedStatement consulta=conexio.prepareStatement("Select Id, Nombre, Pasword, Cargo FROM "+"Encargado "+"ORDER BY Nombre");
            ResultSet resultado=consulta.executeQuery();
            while(resultado.next()){
                encargad.add(new Encargado(resultado.getInt("Id"),resultado.getString("Nombre"), resultado.getString("Cargo"),resultado.getString("Pasword")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Comida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encargad;
        
    }
}
