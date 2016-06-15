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
public class Comida {
    int Id;
    String Nombre;
    Double Precio;
    int existencia;
    int identificador;
    public Comida() {
    }

    public Comida(int Id, String Nombre, Double Precio, int existencia) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.existencia = existencia;
        this.identificador=1;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
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

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
    
    public void InsertarComida(Connection conexion, String nombre, Double precio, int existencia){
         try {
            PreparedStatement ingreso;                     
            ingreso=conexion.prepareStatement("INSERT INTO "+ "Comida " + "(Id, Nombre, Precio, Existencia) VALUES(?,?,?,?)");
            ingreso.setString(1, null);
            ingreso.setString(2, nombre);
            ingreso.setDouble(3, precio);
            ingreso.setDouble(4, existencia);
            ingreso.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Comida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Comida VerificarExistenciaDeComida(Connection conexion, String Nombre){
        Comida comida=null;
        try {
            PreparedStatement consulta = conexion.prepareStatement("SELECT ID, Nombre, Precio, Existencia FROM " + "Comida "+ "WHERE Nombre = ?");
            consulta.setString(1, Nombre);
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
               comida=new Comida(resultado.getInt("Id"), Nombre, resultado.getDouble("Precio"), resultado.getInt("Existencia"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Comida.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return comida;
    }
    public List<Comida> obtenerTodas (Connection conexio){
        List<Comida> comidas =new ArrayList<>();
        try {
            PreparedStatement consulta=conexio.prepareStatement("Select Id, Nombre, Existencia, Precio FROM "+"Comida "+"ORDER BY Nombre");
            ResultSet resultado=consulta.executeQuery();
            while(resultado.next()){
                comidas.add(new Comida(resultado.getInt("Id"),resultado.getString("Nombre"), resultado.getDouble("Precio"),resultado.getInt("Existencia")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Comida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comidas;
        
    }
    public void ModificarComida(Connection conexion, String Nombre, Double Precio, int Existencia, int id){
         PreparedStatement consulta;
        try {
            consulta=conexion.prepareStatement("UPDATE "+"Comida "+" SET nombre=?, Precio=?, Existencia=? WHERE id=?");
            consulta.setString(1, Nombre);
            consulta.setDouble(2, Precio);
            consulta.setInt(3, Existencia);
            consulta.setInt(4, id);
            consulta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bebida.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    public List<Comida> obtenerTodasExistentes(Connection conexion){
        List<Comida> comidas=new ArrayList<>();
        try {
            PreparedStatement consulta=conexion.prepareStatement("SELECT Id, nombre, Precio, Existencia FROM "+"Comida "+ " WHERE existencia>=1");
            ResultSet resultado=consulta.executeQuery();
            while(resultado.next()){
                comidas.add(new Comida(resultado.getInt("Id"), resultado.getString("Nombre"), resultado.getDouble("Precio"), resultado.getInt("existencia")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Comida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comidas;
    }
}
            
    

