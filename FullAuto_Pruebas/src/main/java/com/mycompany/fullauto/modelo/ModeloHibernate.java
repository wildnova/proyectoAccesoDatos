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
        try {
            crearConexionHibernate();
        } catch (DAOConexionExcepcion ex) {
            
        }
        if (sesion!=null)
        {
        
        String sql="FROM Trabajador";
        Query q = sesion.createQuery(sql);
        listadoTrabajadores =(ArrayList) q.list();
        }
        return listadoTrabajadores;
    }

    @Override
    public void insertarTrabajador(Trabajador tr) throws DAOTrabajadorExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarTrabajador(Trabajador tr, String dniOriginal) throws DAOTrabajadorExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarTrabajador(Trabajador tr) throws DAOTrabajadorExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Vehiculo> getListadoVehiculos() throws DAOVehiculoExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarVehiculo(Vehiculo vh) throws DAOVehiculoExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarVehiculo(Vehiculo vh, String bastidorOriginal) throws DAOVehiculoExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarVehiculo(Vehiculo vh) throws DAOVehiculoExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void deleteFactura(Factura fac, int numFacturaOriginal) throws DAOFacturaExcepcion {
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
    public void modificarRepuesto(Repuestos rep, int numSerieOriginal) throws DAORepuestoExcepcion {
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
    }*/
    /*
    public Set<Trabajador> getTodosTrabajadores() {
       Query q = sesion.createQuery("from Trabajador");
         
        List<Trabajador> listadoTrabajadores = q.list();
        return new HashSet<Trabajador>(listadoTrabajadores);
    }


   
    public void setTrabajador(Trabajador tr) throws DAOTrabajadorExcepcion {
        Transaction tx = sesion.beginTransaction();
        
        sesion.save(tr);
        
        tx.commit();
    }

   
    public void deleteTrabajador(Trabajador tr) throws DAOTrabajadorExcepcion {
         Transaction tx = sesion.beginTransaction();
        
        sesion.delete(tr);
        
        tx.commit();
    }

   
    public void updateTrabajador(Trabajador tr) throws DAOTrabajadorExcepcion {
        Transaction tx = sesion.beginTransaction();
        
        sesion.update(tr);
        
        tx.commit();
    }*/

    
    
}
