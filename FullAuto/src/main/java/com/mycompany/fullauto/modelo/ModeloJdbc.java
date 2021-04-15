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
import java.time.ZoneId;
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
        String url = "jdbc:mysql://192.168.100.233/fullAutoDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        
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
    
    //Funciones para trabajador
    
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
    public Trabajador comprobarTrabajador(String dniTrabajador)
    {
        Connection conn=null;
        try {
            conn = conectar();
        } catch (DAOConexionExcepcion ex) {
            System.out.println("Error al realizar la conexión");
            ex.getMessage();
        }
        ResultSet rs=null;
        
        Trabajador trabajador=null;
        
     try{
             
            String sql = "SELECT * FROM trabajador WHERE dni=?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dniTrabajador);
            rs= pstmt.executeQuery();
            
            rs.next();
             trabajador= new Trabajador(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            
            
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
     return trabajador;       
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
    
     public void modificarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion
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
             
        String sql = "UPDATE trabajador SET nombre=?,apellido1=?,apellido2=?,funcion=? WHERE dni=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, trabajador.getNombre());
        pstmt.setString(2, trabajador.getApellido1());
        pstmt.setString(3, trabajador.getApellido2());
        pstmt.setString(4, trabajador.getFuncion());
        pstmt.setString(5, trabajador.getDni());
        
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
    
    //Funciones para Vehículo
    
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
            Vehiculo vehiculo= new Vehiculo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDate(10),rs.getString(11),rs.getString(12));
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

        String sql = "INSERT INTO vehiculo VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
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
        //Hay que realizar la conversión de java.util.date al nuevo sistema java.time para que sea compatible con java.sql.date
        pstmt.setObject(10, vehiculo.getFechaEntrada().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate());
        pstmt.setString(11, vehiculo.getTiempoDedicado());
        pstmt.setString(12, vehiculo.getDniTrabajador());
        
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
    public void modificarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion {
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
             
        String sql = "UPDATE vehiculo SET matricula=?,marca=?,modelo=?,motor=?,potenciaCv=?,carburante=?,aceite=?,consumo=?,fecha_entrada=?,tiempo_dedicado=?,dni_trabajador=? WHERE bastidor=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, vehiculo.getMatricula());
        pstmt.setString(2, vehiculo.getMarca());
        pstmt.setString(3, vehiculo.getModelo());
        pstmt.setString(4, vehiculo.getMotor());
        pstmt.setString(5, vehiculo.getPotenciaCv());
        pstmt.setString(6, vehiculo.getCarburante());
        pstmt.setString(7, vehiculo.getAceite());
        pstmt.setString(8, vehiculo.getConsumo());
        pstmt.setObject(9, vehiculo.getFechaEntrada().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate());
        pstmt.setString(10, vehiculo.getTiempoDedicado());
        pstmt.setString(11, vehiculo.getDniTrabajador());
        pstmt.setString(12, vehiculo.getBastidor());
        
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
    
    //Funciones para Informe
    
    @Override
    public ArrayList<Informe> getListadoInformes() throws DAOInformeExcepcion {
        Connection conn=null;
        try {
            conn = conectar();
        } catch (DAOConexionExcepcion ex) {
            System.out.println("Error al realizar la conexión");
            ex.getMessage();
        }
        ResultSet rs=null;
        String[] datosInforme=null;
        ArrayList<Informe> listaInformes = new ArrayList();
        
     try{
             
            String sql = "SELECT * FROM informe";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs= pstmt.executeQuery();
            
            while(rs.next())
            {
            Informe informe= new Informe(rs.getInt(1),rs.getString(2),rs.getString(3));
            listaInformes.add(informe);
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
     return listaInformes;
    }

    @Override
    public void insertarInforme(Informe informe) throws DAOInformeExcepcion {
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
             
        String sql = "INSERT INTO informe VALUES (?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, informe.getNumExpedicion());
        pstmt.setString(2, informe.getTareas());
        pstmt.setString(3, informe.getDniTrabajadorInforme());
        
        
        pstmt.executeUpdate();
        System.out.println("Informe añadido correctamente");
   
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
    public void modificarInforme(Informe informe) throws DAOInformeExcepcion {
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
             
        String sql = "UPDATE informe SET tareas=?,dni_trabajador=? WHERE num_expedicion=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, informe.getTareas());
        pstmt.setString(2, informe.getDniTrabajadorInforme());
        pstmt.setInt(3, informe.getNumExpedicion());
        
        
        pstmt.executeUpdate();
        System.out.println("Informe modificado correctamente");
   
        }
        catch(SQLException sqle)
        {
            System.err.println("Error en la modificación");
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
                System.err.println("Error al cerrar la base de datos");
            }
        }
    }

    @Override
    public void eliminarInforme(Informe informe) throws DAOInformeExcepcion {
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
             
        String sql = "DELETE FROM informe WHERE num_expedicion=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, informe.getNumExpedicion());
        pstmt.executeUpdate();
        
        System.out.println("Informe eliminado correctamente");
   
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
    
    //Funciones de Factura
    
    @Override
    public ArrayList<Factura> getListadoFacturas() throws DAOFacturaExcepcion {
        Connection conn=null;
        try {
            conn = conectar();
        } catch (DAOConexionExcepcion ex) {
            System.out.println("Error al realizar la conexión");
            ex.getMessage();
        }
        ResultSet rs=null;
        String[] datosFactura=null;
        ArrayList<Factura> listaFacturas = new ArrayList();
        
     try{
             
            String sql = "SELECT * FROM factura";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs= pstmt.executeQuery();
            
            while(rs.next())
            {
            Factura factura= new Factura(rs.getInt(1),rs.getDouble(2),rs.getString(3),rs.getString(4));
            listaFacturas.add(factura);
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
     return listaFacturas;
    }

    @Override
    public void insertarFactura(Factura factura) throws DAOFacturaExcepcion {
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
             
        String sql = "INSERT INTO factura VALUES (?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, factura.getNumFactura());
        pstmt.setDouble(2, factura.getPrecio());
        pstmt.setString(3, factura.getLineaFactura());
        pstmt.setString(4, factura.getNumExpedicionInforme());
        
        
        pstmt.executeUpdate();
        System.out.println("Factura añadida correctamente");
   
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
    public void modificarFactura(Factura factura) throws DAOFacturaExcepcion {
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
             
        String sql = "UPDATE factura SET precio=?,lineaFactura=?,num_expedicion_informe=? WHERE num_factura=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setDouble(1, factura.getPrecio());
        pstmt.setString(2, factura.getLineaFactura());
        pstmt.setString(3, factura.getNumExpedicionInforme());
        pstmt.setInt(4, factura.getNumFactura());
        
        
        pstmt.executeUpdate();
        System.out.println("Factura modificada correctamente");
   
        }
        catch(SQLException sqle)
        {
            System.err.println("Error en la modificación");
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
                System.err.println("Error al cerrar la base de datos");
            }
        }
    }
    @Override
    public void eliminarFactura(Factura factura) throws DAOFacturaExcepcion {
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
             
        String sql = "DELETE FROM factura WHERE num_factura=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, factura.getNumFactura());
        pstmt.executeUpdate();
        
        System.out.println("Factura eliminada correctamente");
   
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

    //Funciones de repuestos
    
   
    @Override
    public ArrayList<Repuestos> getListadoRepuestos() throws DAORepuestoExcepcion {
        Connection conn=null;
        try {
            conn = conectar();
        } catch (DAOConexionExcepcion ex) {
            System.out.println("Error al realizar la conexión");
            ex.getMessage();
        }
        ResultSet rs=null;
        String[] datosRepuesto=null;
        ArrayList<Repuestos> listaRepuestos = new ArrayList();
        
     try{
             
            String sql = "SELECT * FROM repuestos";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs= pstmt.executeQuery();
            
            while(rs.next())
            {
            Repuestos repuesto= new Repuestos(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9));
            listaRepuestos.add(repuesto);
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
     return listaRepuestos;
    }

    @Override
    public void insertarRepuesto(Repuestos repuesto) throws DAORepuestoExcepcion {
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

        String sql = "INSERT INTO repuestos VALUES (?,?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, repuesto.getNumSerie());
        pstmt.setString(2, repuesto.getReferencia());
        pstmt.setString(3, repuesto.getNombre());
        pstmt.setString(4, repuesto.getMarca());
        pstmt.setString(5, repuesto.getUso());
        pstmt.setString(6, repuesto.getTienda());
        pstmt.setObject(7, repuesto.getFechaCompra().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate());
        pstmt.setString(8, repuesto.getBastidorVehiculoRepuestos());
        pstmt.setString(9, repuesto.getNumFacturaRepuestos());
        
        pstmt.executeUpdate();
        System.out.println("Repuesto añadido correctamente");
   
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
    public void modificarRepuesto(Repuestos repuesto) throws DAORepuestoExcepcion {
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
             
        String sql = "UPDATE repuestos SET referencia=?,nombre=?,marca=?,uso=?,tienda=?,fecha_compra=?,bastidor_vehiculo=?,num_factura=? WHERE num_serie=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, repuesto.getReferencia());
        pstmt.setString(2, repuesto.getNombre());
        pstmt.setString(3, repuesto.getMarca());
        pstmt.setString(4, repuesto.getUso());
        pstmt.setString(5, repuesto.getTienda());
        pstmt.setObject(6, repuesto.getFechaCompra().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate());
        pstmt.setString(7, repuesto.getBastidorVehiculoRepuestos());
        pstmt.setString(8, repuesto.getNumFacturaRepuestos());
        pstmt.setObject(9, repuesto.getNumSerie());
        
        
        pstmt.executeUpdate();
         System.out.println("Repuesto modificado correctamente");
   
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
    public void eliminarRepuesto(Repuestos repuesto) throws DAORepuestoExcepcion {
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
             
        String sql = "DELETE FROM repuestos WHERE num_serie=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, repuesto.getNumSerie());
        pstmt.executeUpdate();
        
        System.out.println("Repuesto eliminado correctamente");
   
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

    
        //Funciones de Obd
    
    
    @Override
    public ArrayList<Obd> getListadoObd() throws DAOObdExcepcion {
        Connection conn=null;
        try {
            conn = conectar();
        } catch (DAOConexionExcepcion ex) {
            System.out.println("Error al realizar la conexión");
            ex.getMessage();
        }
        ResultSet rs=null;
        String[] datosObd=null;
        ArrayList<Obd> listaObd = new ArrayList();
        
     try{
             
            String sql = "SELECT * FROM obd";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs= pstmt.executeQuery();
            
            while(rs.next())
            {
            Obd obd= new Obd(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getDouble(4),rs.getString(5),rs.getString(6),rs.getString(7));
            listaObd.add(obd);
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
     return listaObd;
    }

    @Override
    public void insertarObd(Obd obd) throws DAOObdExcepcion {
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

        String sql = "INSERT INTO obd VALUES (?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, obd.getId());
        pstmt.setInt(2, obd.getRpm_inst());
        pstmt.setDouble(3, obd.getTemp_aceite());
        pstmt.setDouble(4, obd.getTemp_agua());
        pstmt.setString(5, obd.getCod_salida());
        pstmt.setString(6, obd.getSensores());
        pstmt.setString(7, obd.getBastidorVehiculoObd());
        
        
        pstmt.executeUpdate();
        System.out.println("Registro obd añadido correctamente");
   
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
    public void modificarObd(Obd obd) throws DAOObdExcepcion {
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
             
        String sql = "UPDATE obd SET rpm_inst=?,temp_aceite=?,temp_agua=?,cod_salida=?,sensores=?,bastidor_vehiculo=? WHERE id=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, obd.getRpm_inst());
        pstmt.setDouble(2, obd.getTemp_aceite());
        pstmt.setDouble(3, obd.getTemp_agua());
        pstmt.setString(4, obd.getCod_salida());
        pstmt.setString(5, obd.getSensores());
        pstmt.setString(6, obd.getBastidorVehiculoObd());
        pstmt.setInt(7, obd.getId());
        
        pstmt.executeUpdate();
         System.out.println("Registro obd modificado correctamente");
   
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
    public void eliminarObd(Obd obd) throws DAOObdExcepcion {
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
             
        String sql = "DELETE FROM obd WHERE id=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, obd.getId());
        pstmt.executeUpdate();
        
        System.out.println("Registro obd eliminado correctamente");
   
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
 
}
