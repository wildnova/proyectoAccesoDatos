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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.ObjectNotFoundException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author novad
 */
public class ModeloHibernate implements DAO{

    private Session sesion=null;
    private SessionFactory factory;

    public ModeloHibernate() {
        
        try {
            crearConexionHibernate();
        } catch (DAOConexionExcepcion ex) {
            Logger.getLogger(ModeloHibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private void crearConexionHibernate() throws DAOConexionExcepcion{
        
        factory= new Configuration().configure().buildSessionFactory();
        
        /*factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Factura.class)
                .addAnnotatedClass(Informe.class)
                .addAnnotatedClass(Obd.class)
                .addAnnotatedClass(Repuestos.class)
                .addAnnotatedClass(Trabajador.class)
                .addAnnotatedClass(Vehiculo.class)
                .buildSessionFactory();*/
        
        sesion= factory.openSession();
        
        if(sesion==null)
        {
            throw new DAOConexionExcepcion("No se ha podido conectar a hibernate");
        }
    }
    private void cerrarConexionHibernate() throws DAOConexionExcepcion
    {
        sesion.close();
        
        if(sesion!=null)
            throw new DAOConexionExcepcion("No se ha podido cerrar la conexión con hibernate.");
        
    }
    
    @Override
    public ArrayList<Trabajador> getListadoTrabajadores() throws DAOTrabajadorExcepcion {
        ArrayList<Trabajador> listadoTrabajadores=null;

        String sql="FROM Trabajador";
        Query q = sesion.createQuery(sql);
        listadoTrabajadores =(ArrayList) q.list();

        return listadoTrabajadores;
    }
    @Override
    public Trabajador comprobarTrabajador(String dniTrabajador) throws DAOTrabajadorExcepcion {
        Trabajador trabajador=null;
        
        if (sesion!=null)
        {
            trabajador = sesion.load(Trabajador.class,dniTrabajador);
    
        }
        
        return trabajador;
       
    }
    @Override
    public void insertarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion {
        
        Transaction tx = sesion.beginTransaction();
        
        sesion.save(trabajador);
        
        tx.commit();
        
    }

    @Override
    public void modificarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion {
        
        Transaction tx = sesion.beginTransaction();
        
        sesion.update(trabajador);
        
        tx.commit();
        
    }

    @Override
    public void eliminarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion {
  
        Transaction tx = sesion.beginTransaction();
        
        sesion.delete(trabajador);
        
        tx.commit();
        
        
    }

    @Override
    public ArrayList<Vehiculo> getListadoVehiculos() throws DAOVehiculoExcepcion {
        
        ArrayList<Vehiculo> listadoVehiculos=null;

        
        String sql="FROM Vehiculo";
        Query q = sesion.createQuery(sql);
        listadoVehiculos =(ArrayList) q.list();
        return listadoVehiculos;
       
    }

    @Override
    public void insertarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion {
        Transaction tx = sesion.beginTransaction();
        
        sesion.save(vehiculo);
        
        tx.commit();
    }

    @Override
    public void modificarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion {
        Transaction tx = sesion.beginTransaction();
        
        sesion.update(vehiculo);
        
        tx.commit();
    }

    @Override
    public void eliminarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion {
        Transaction tx = sesion.beginTransaction();
        
        sesion.delete(vehiculo);
        
        tx.commit();
    }
    
    @Override
    public ArrayList<Informe> getListadoInformes() throws DAOInformeExcepcion {
        ArrayList<Informe> listadoInformes=null;

        
        String sql="FROM Informe";
        Query q = sesion.createQuery(sql);
        listadoInformes =(ArrayList) q.list();
        return listadoInformes;
    }

    @Override
    public void insertarInforme(Informe informe) throws DAOInformeExcepcion {
        Transaction tx = sesion.beginTransaction();
        
        sesion.save(informe);
        
        tx.commit();
    }

    @Override
    public void modificarInforme(Informe informe) throws DAOInformeExcepcion {
        Transaction tx = sesion.beginTransaction();
        
        sesion.update(informe);
        
        tx.commit();
    }

    @Override
    public void eliminarInforme(Informe informe) throws DAOInformeExcepcion {
        Transaction tx = sesion.beginTransaction();
        
        sesion.delete(informe);
        
        tx.commit();
    }

    @Override
    public ArrayList<Factura> getListadoFacturas() throws DAOFacturaExcepcion {
        ArrayList<Factura> listadoFacturas=null;

        
        String sql="FROM Factura";
        Query q = sesion.createQuery(sql);
        listadoFacturas =(ArrayList) q.list();
        return listadoFacturas;
    }

    @Override
    public void insertarFactura(Factura factura) throws DAOFacturaExcepcion {
        Transaction tx = sesion.beginTransaction();
        
        sesion.save(factura);
        
        tx.commit();
    }

    @Override
    public void modificarFactura(Factura factura) throws DAOFacturaExcepcion {
       Transaction tx = sesion.beginTransaction();
        
        sesion.update(factura);
        
        tx.commit();
    }

    @Override
    public void eliminarFactura(Factura factura) throws DAOFacturaExcepcion {
        Transaction tx = sesion.beginTransaction();
        
        sesion.delete(factura);
        
        tx.commit();
    }

    @Override
    public ArrayList<Repuestos> getListadoRepuestos() throws DAORepuestoExcepcion {
        ArrayList<Repuestos> listadoRepuestos=null;

        
        String sql="FROM Repuestos";
        Query q = sesion.createQuery(sql);
        listadoRepuestos =(ArrayList) q.list();
        return listadoRepuestos;
    }

    @Override
    public void insertarRepuesto(Repuestos repuestos) throws DAORepuestoExcepcion {
         Transaction tx = sesion.beginTransaction();
        
        sesion.save(repuestos);
        
        tx.commit();
    }

    @Override
    public void modificarRepuesto(Repuestos repuestos) throws DAORepuestoExcepcion {
        Transaction tx = sesion.beginTransaction();
        
        sesion.update(repuestos);
        
        tx.commit();
    }

    @Override
    public void eliminarRepuesto(Repuestos repuestos) throws DAORepuestoExcepcion {
         Transaction tx = sesion.beginTransaction();
        
        sesion.delete(repuestos);
        
        tx.commit();
    }

    @Override
    public ArrayList<Obd> getListadoObd() throws DAOObdExcepcion {
        ArrayList<Obd> listadoObd=null;

        
        String sql="FROM Obd";
        Query q = sesion.createQuery(sql);
        listadoObd =(ArrayList) q.list();
        return listadoObd;
    }

    @Override
    public void insertarObd(Obd obd) throws DAOObdExcepcion {
        Transaction tx = sesion.beginTransaction();
        
        sesion.save(obd);
        
        tx.commit();
    }

    @Override
    public void modificarObd(Obd obd) throws DAOObdExcepcion {
         Transaction tx = sesion.beginTransaction();
        
        sesion.update(obd);
        
        tx.commit();
    }

    @Override
    public void eliminarObd(Obd obd) throws DAOObdExcepcion {
         Transaction tx = sesion.beginTransaction();
        
        sesion.delete(obd);
        
        tx.commit();
    }

}
    /*public List<Vehiculo> getListadoVehiculos() throws DAOConexionExcepcion
    {
        
        List<Vehiculo> listadoVehiculos=null;
        crearConexionHibernate();
        if (sesion!=null)
        {
        
        String sql="FROM Vehiculo";
        Query q = sesion.createQuery(sql);
        listadoVehiculos = q.list();

            
            
            
        }
        return listadoVehiculos;
    }
        /*if(sesion!=null)
            try
            {
             cerrarConexionHibernate();
            }
            catch(DAOConexionExcepcion dce)
            {
                System.out.println("Error al cerrar la conexión con hibernate (getTrabajador)");
            }
        /*
        if (sesion==null)
        {
            try
            {
            crearConexionHibernate();
            trabajador = sesion.load(Trabajador.class,dni);
            }
            catch(ObjectNotFoundException onfe)
            {
                throw new DAOTrabajadorExcepcion("Error al buscar el trabajador");
            }
            catch(DAOConexionExcepcion dce)
            {
                System.out.println("Error al crear la conexión con hibernate");;
            }*/
    
    /*public int getNumTrabajadores(){
        int numTrabajadores=-1;
        if (sesion==null)
        {
            try
            {
            crearConexionHibernate();

            Query q = sesion.createQuery("from Trabajador");
            List<Trabajador> listadoTrabajadores = q.list();
            numTrabajadores=listadoTrabajadores.size();
                
            
            }
            catch(DAOConexionExcepcion dce)
            {
                System.out.println("Error conectando con hibernate");
            }
        }
        if(sesion!=null)
        {
            try
            {
                cerrarConexionHibernate();
            }
            catch(DAOConexionExcepcion dce)
            {
                System.out.println("Error al cerrar la conexión con hibernate");
            }
        }
        
        return numTrabajadores;
    }
    
    public Set<Trabajador> getTodosTrabajadores() {
       Query q = sesion.createQuery("from Trabajador");
         
        List<Trabajador> listadoTrabajadores = q.list();
        return new HashSet<Trabajador>(listadoTrabajadores);
    }*/

