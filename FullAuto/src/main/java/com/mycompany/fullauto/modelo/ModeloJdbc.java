/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.modelo;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author novad
 */
public class ModeloJdbc {

    /**
     * @param args the command line arguments
     */
    
    public ModeloJdbc(){}

    public Connection conectar()
    {
        Connection conn=null;
        String url = "jdbc:mysql://192.168.100.233/fullAutoDB";
        
        try
        {
            conn = DriverManager.getConnection(url,"cristian","alumno");
            System.out.println("Conexión realizada con éxito");
        }
        catch(SQLException se)
        {
            System.err.println(se.getClass().getName()+"--> "+ se.getMessage());
            System.out.println("Error en la conexión a la base de datos");
        }
        
        return conn;
    }
    
    public ArrayList getDatosTrabajador()
    {
        
        Connection conn=conectar();
        ResultSet rs=null;
        String[] datosTrabajador=null;
        ArrayList listaTrabajadores = new ArrayList();
     try{
             
            String sql = "SELECT * FROM trabajador";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs= pstmt.executeQuery();
            
            while(rs.next())
            {
            datosTrabajador= new String[5];
            datosTrabajador[0]=rs.getString(1);
            datosTrabajador[1]=rs.getString(2);
            datosTrabajador[2]=rs.getString(3);
            datosTrabajador[3]=rs.getString(4);
            datosTrabajador[4]=rs.getString(5);
            listaTrabajadores.add(datosTrabajador);
            }
            
            
        }
        catch(SQLException sqle)
        {
            System.out.println("Error en la consulta");
            sqle.printStackTrace();
            
        }
        finally
        {
            try
            {
                if( conn!=null)
                {
                   conn.close();
                }
            }
            catch(SQLException sqle)
            {
                System.out.println("Error al cerrar la base de datos");
            }
        }
     return listaTrabajadores;
    }
    
}
