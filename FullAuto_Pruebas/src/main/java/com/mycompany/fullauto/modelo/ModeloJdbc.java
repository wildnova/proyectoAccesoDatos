/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.modelo;
import com.mycompany.fullauto.Exceptions.DAOConexionExcepcion;
import com.mycompany.fullauto.Exceptions.DAOFacturaExcepcion;
import com.mycompany.fullauto.Exceptions.DAOInformeExcepcion;
import com.mycompany.fullauto.Exceptions.DAOObdExcepcion;
import com.mycompany.fullauto.Exceptions.DAORepuestoExcepcion;
import com.mycompany.fullauto.Exceptions.DAOTrabajadorExcepcion;
import com.mycompany.fullauto.Exceptions.DAOVehiculoExcepcion;
import com.mycompany.fullauto.Factura;
import com.mycompany.fullauto.Informe;
import com.mycompany.fullauto.Obd;
import com.mycompany.fullauto.Repuestos;
import com.mycompany.fullauto.Trabajador;
import com.mycompany.fullauto.Vehiculo;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author novad
 */
public class ModeloJdbc implements DAO{

    /**
     * @param args the command line arguments
     */
    
    public ModeloJdbc(){}

    public Connection conectar() throws DAOConexionExcepcion
    {
        Connection conn=null;
        String url2 = "jdbc:mysql://192.168.100.233/fullAutoDB";
        String url= "jdbc:mysql://192.168.100.233/fullAutoDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        
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
    
    @Override
    public ArrayList<Trabajador> getListadoTrabajadores() throws DAOTrabajadorExcepcion
    {
        Connection conn=null;
        try {
            conn = conectar();
        } catch (DAOConexionExcepcion ex) {
            System.out.println("Error al realizar la conexión");
            ex.getMessage();
        }
        ResultSet rs=null;
        String[] datosTrabajador=null;
        ArrayList<Trabajador> listaTrabajadores = new ArrayList();
        
     try{
             
            String sql = "SELECT * FROM trabajador";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs= pstmt.executeQuery();
            
            while(rs.next())
            {
            Trabajador trabajador= new Trabajador(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            /*datosTrabajador= new String[5];
            datosTrabajador[0]=rs.getString(1);
            datosTrabajador[1]=rs.getString(2);
            datosTrabajador[2]=rs.getString(3);
            datosTrabajador[3]=rs.getString(4);
            datosTrabajador[4]=rs.getString(5);*/
            listaTrabajadores.add(trabajador);
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
    
    @Override
    public void insertarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion
    {
        Connection conn=null;
        
        try {
            
            conn = conectar();
        } 
        catch (DAOConexionExcepcion ex) 
        {
           System.out.println("Error al realizar la conexión");
           ex.getMessage();
        }
        
        PreparedStatement pstmt=null;
        
     try{
             
        String sql = "INSERT INTO trabajador VALUES (?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, trabajador.getDni());
        pstmt.setString(2, trabajador.getNombre());
        pstmt.setString(3, trabajador.getApellido1());
        pstmt.setString(4, trabajador.getApellido2());
        pstmt.setString(5, trabajador.getFuncion());
        
        pstmt.executeUpdate();
         System.out.println("Trabajador añadido correctamente");
   
        }
        catch(SQLException sqle)
        {
            System.out.println("Error en la inserción");
            sqle.printStackTrace();
            
        }
        finally
        {
            try
            {
                if( pstmt!=null)
                {
                   pstmt.close();
                }
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
    }
    
     public void modificarTrabajador(Trabajador trabajador, String dniOriginal) throws DAOTrabajadorExcepcion
    {
        Connection conn=null;
        try {
            conn = conectar();
        } 
        catch (DAOConexionExcepcion ex) 
        {
           System.out.println("Error al realizar la conexión");
            ex.getMessage();
        }
        PreparedStatement pstmt=null;
     try{
             
        String sql = "UPDATE trabajador SET dni=?,nombre=?,apellido1=?,apellido2=?,funcion=? WHERE dni=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, trabajador.getDni());
        pstmt.setString(2, trabajador.getNombre());
        pstmt.setString(3, trabajador.getApellido1());
        pstmt.setString(4, trabajador.getApellido2());
        pstmt.setString(5, trabajador.getFuncion());
        pstmt.setString(6, dniOriginal);
        
        pstmt.executeUpdate();
         System.out.println("Trabajador modificado correctamente");
   
        }
        catch(SQLException sqle)
        {
            System.out.println("Error en la modificación");
            sqle.printStackTrace();
            
        }
        finally
        {
            try
            {
                if( pstmt!=null)
                {
                   pstmt.close();
                }
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
    }
    
    public void eliminarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion
    {
        Connection conn=null;
        try {
            
            conn = conectar();
        } 
        catch (DAOConexionExcepcion ex) 
        {
            System.out.println("Error al realizar la conexión");
            ex.getMessage();
        }
        PreparedStatement pstmt=null;
     try{
             
        String sql = "DELETE FROM trabajador WHERE dni=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, trabajador.getDni());
        pstmt.executeUpdate();
        
        System.out.println("Trabajador eliminado correctamente");
   
        }
        catch(SQLException sqle)
        {
            System.out.println("Error al eliminar");
            sqle.printStackTrace();
            
        }
        finally
        {
            try
            {
                if( pstmt!=null)
                {
                   pstmt.close();
                }
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
    }
    
    @Override
    public ArrayList<Vehiculo> getListadoVehiculos() throws DAOVehiculoExcepcion {
          Connection conn=null;
        try {
            conn = conectar();
        } catch (DAOConexionExcepcion ex) {
            System.out.println("Error al realizar la conexión");
            ex.getMessage();
        }
        ResultSet rs=null;
        String[] datosVehiculo=null;
        ArrayList<Vehiculo> listaVehiculos = new ArrayList();
        
     try{
             
            String sql = "SELECT * FROM vehiculo";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs= pstmt.executeQuery();
            
            while(rs.next())
            {
            Vehiculo vehiculo= new Vehiculo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDate(10),rs.getString(11));
            listaVehiculos.add(vehiculo);
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
     return listaVehiculos;
    }
    

    @Override
    public void insertarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion {
         Connection conn=null;
        
        try {
            
            conn = conectar();
        } 
        catch (DAOConexionExcepcion ex) 
        {
           System.out.println("Error al realizar la conexión");
           ex.getMessage();
        }
        
        PreparedStatement pstmt=null;
        
     try{
             
        String sql = "INSERT INTO vehiculo VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, vehiculo.getBastidor());
        pstmt.setString(2, vehiculo.getMatricula());
        pstmt.setString(3, vehiculo.getMarca());
        pstmt.setString(4, vehiculo.getModelo());
        pstmt.setString(5, vehiculo.getMotor());
        pstmt.setString(6, vehiculo.getPotenciaCv());
        pstmt.setString(7, vehiculo.getCarburante());
        pstmt.setString(8, vehiculo.getAceite());
        pstmt.setString(9, vehiculo.getConsumo());
        pstmt.setDate(10, (Date) vehiculo.getFechaEntrada());
        pstmt.setString(11, vehiculo.getTiempoDedicado());
        
        pstmt.executeUpdate();
         System.out.println("Vehículo añadido correctamente");
   
        }
        catch(SQLException sqle)
        {
            System.out.println("Error en la inserción");
            sqle.printStackTrace();
            
        }
        finally
        {
            try
            {
                if( pstmt!=null)
                {
                   pstmt.close();
                }
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
    }

    @Override
    public void modificarVehiculo(Vehiculo vehiculo, String bastidorOriginal) throws DAOVehiculoExcepcion {
        Connection conn=null;
        try {
            conn = conectar();
        } 
        catch (DAOConexionExcepcion ex) 
        {
           System.out.println("Error al realizar la conexión");
            ex.getMessage();
        }
        PreparedStatement pstmt=null;
     try{
             
        String sql = "UPDATE vehiculo SET bastidor=?,matricula=?,marca=?,modelo=?,motor=?,potenciaCv=?,carburante=?,aceite=?,consumo=?,fecha_entrada=?,tiempo_dedicado=? WHERE bastidor=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, vehiculo.getBastidor());
        pstmt.setString(2, vehiculo.getMatricula());
        pstmt.setString(3, vehiculo.getMarca());
        pstmt.setString(4, vehiculo.getModelo());
        pstmt.setString(5, vehiculo.getMotor());
        pstmt.setString(6, vehiculo.getPotenciaCv());
        pstmt.setString(7, vehiculo.getCarburante());
        pstmt.setString(8, vehiculo.getAceite());
        pstmt.setString(9, vehiculo.getConsumo());
        pstmt.setDate(10, (Date) vehiculo.getFechaEntrada());
        pstmt.setString(11, vehiculo.getTiempoDedicado());
        pstmt.setString(12, bastidorOriginal);
        
        pstmt.executeUpdate();
         System.out.println("Vehiculo modificado correctamente");
   
        }
        catch(SQLException sqle)
        {
            System.out.println("Error en la modificación");
            sqle.printStackTrace();
            
        }
        finally
        {
            try
            {
                if( pstmt!=null)
                {
                   pstmt.close();
                }
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
    }

    @Override
    public void eliminarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion {
        Connection conn=null;
        try {
            
            conn = conectar();
        } 
        catch (DAOConexionExcepcion ex) 
        {
            System.out.println("Error al realizar la conexión");
            ex.getMessage();
        }
        PreparedStatement pstmt=null;
     try{
             
        String sql = "DELETE FROM vehiculo WHERE bastidor=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, vehiculo.getBastidor());
        pstmt.executeUpdate();
        
        System.out.println("Vehículo eliminado correctamente");
   
        }
        catch(SQLException sqle)
        {
            System.out.println("Error al eliminar");
            sqle.printStackTrace();
            
        }
        finally
        {
            try
            {
                if( pstmt!=null)
                {
                   pstmt.close();
                }
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
    }
    @Override
    public ArrayList<Informe> getListadoInformes() throws DAOInformeExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarInforme(Informe inf) throws DAOInformeExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarInforme(Informe inf, int numExpedicionOriginal) throws DAOInformeExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarInforme(Informe inf) throws DAOInformeExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Factura> getListadoFacturas() throws DAOFacturaExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertFactura(Factura fac) throws DAOFacturaExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteFactura(Factura fac,int numFacturaOriginal) throws DAOFacturaExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateFactura(Factura fac) throws DAOFacturaExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public ArrayList<Repuestos> getListadoRepuestos() throws DAORepuestoExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarRepuesto(Repuestos rep) throws DAORepuestoExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarRepuesto(Repuestos rep,int numSerieOriginal) throws DAORepuestoExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarRepuesto(Repuestos rep) throws DAORepuestoExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Obd> getListadoObd() throws DAOObdExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarObd(Obd obd) throws DAOObdExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarObd(Obd obd, int idOriginal) throws DAOObdExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarObd(Obd obd) throws DAOObdExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
}
