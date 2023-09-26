package org.braulioecheverria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    private static Conexion instancia;
    private Connection conexion;
    private Statement enunciado;

    private Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection
                    ("jdbc:mysql://containers-us-west-117.railway.app:5527/railway?useSSL=false","root","fIOWZ1C8S6BA3CVbtLzG");
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
            e.printStackTrace();
        }
    }

    public static Conexion getInstancia(){
        if(instancia == null){
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConexion(){
        return this.conexion;
    }
}
