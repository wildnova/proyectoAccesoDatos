/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.modelo;

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
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author novad
 */
public class ModeloObjectDB implements DAO{
    EntityManagerFactory emf=null;
    EntityManager em=null;

    public ModeloObjectDB() {
        crearConexionObjectDB();
        File f = new File("FullAutoObjectDB.odb");
        
        if(!f.exists())
        {
            EntityManager em = emf.createEntityManager();

            //Conversor de fechas para los datos por defecto
            String date1= "2020-11-30";
            String date2= "2021-01-06";
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha=null;
            Date fecha2=null;
            try {
                fecha = formater.parse(date1);
                fecha2= formater.parse(date2);
            } catch (ParseException ex) {
                Logger.getLogger(ModeloObjectDB.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Inserción de datos por defecto para el inicio de la aplicación.
            em.getTransaction().begin();

            Trabajador trabajador = new Trabajador("11111111A","Juan","Bautista","Conejero","cambios");
            Vehiculo vehiculo = new Vehiculo("111111111111111", "4470KJZ","Kia","Cerato","1600cc","130","diesel","-10-40","5.8l/100km",fecha,"60 minutos","11111111A");
            Informe informe = new Informe(1111,"Sustituir pastillas de freno antiguas por las nuevas","11111111A");
            Factura factura = new Factura(111, 35.45,"Pastillas freno brembo delanteras y traseras","1111");
            Repuestos repuestos = new Repuestos("25","11111","pastilla de freno","brembo","sistema de frenos","Serca",fecha2,"111111111111111","111");
            Obd obd = new Obd(11111111,2500,63.2,89.45,"122","Frenos","111111111111111");

            em.persist(trabajador);
            em.persist(vehiculo);
            em.persist(informe);
            em.persist(factura);
            em.persist(repuestos);
            em.persist(obd);

            em.getTransaction().commit();
        }
    }
    
    
    public void crearConexionObjectDB()
    {
        emf = Persistence.createEntityManagerFactory("FullAutoObjectDB.odb");
    }
    @Override
    public ArrayList<Trabajador> getListadoTrabajadores() throws DAOTrabajadorExcepcion {
       
        em = emf.createEntityManager();

        // Store 1000 Point objects in the database:
        /*em.getTransaction().begin();
        
            Trabajador trabajador = new Trabajador();
            em.persist(trabajador);
        
        em.getTransaction().commit();
*/
        // Find the number of Point objects in the database:
        //Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        //System.out.println("Total Points: " + q1.getSingleResult());

        
        // Retrieve all the Point objects from the database:
        
        TypedQuery<Trabajador> query =
            em.createQuery("SELECT t FROM Trabajador t", Trabajador.class);
        ArrayList<Trabajador> listaTrabajadores = (ArrayList)query.getResultList();
        

        // Close the database connection:
        em.close();
        
        return listaTrabajadores;
    
    }

    @Override
    public Trabajador comprobarTrabajador(String dniTrabajador) throws DAOTrabajadorExcepcion {
        em = emf.createEntityManager();
        Trabajador trabajador= em.find(Trabajador.class, dniTrabajador);
        System.out.println("/////////////////////////////////////////   " + trabajador.getDni());
       em.close();
        return trabajador;
    }

    @Override
    public void insertarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(trabajador);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void modificarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion {
        em = emf.createEntityManager();
        Trabajador trabajadorUpdate = em.find(Trabajador.class, trabajador.getDni());
        em.getTransaction().begin();
        
        trabajadorUpdate.setNombre(trabajador.getNombre());
        trabajadorUpdate.setApellido1(trabajador.getApellido1());
        trabajadorUpdate.setApellido2(trabajador.getApellido2());
        trabajadorUpdate.setFuncion(trabajador.getFuncion());
        
        em.getTransaction().commit();
        em.close();
    
    }

    @Override
    public void eliminarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion {
        em = emf.createEntityManager();
        Trabajador trabajadorDelete = em.find(Trabajador.class, trabajador.getDni());
        em.getTransaction().begin();
        
        em.remove(trabajadorDelete);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public ArrayList<Vehiculo> getListadoVehiculos() throws DAOVehiculoExcepcion {
        em = emf.createEntityManager();
        TypedQuery<Vehiculo> query =
            em.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class);
        ArrayList<Vehiculo> listaVehiculos = (ArrayList)query.getResultList();
        

        // Close the database connection:
        em.close();
        
        return listaVehiculos;
    }

    @Override
    public void insertarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(vehiculo);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void modificarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion {
        em = emf.createEntityManager();
        Vehiculo vehiculoUpdate = em.find(Vehiculo.class, vehiculo.getBastidor());
        em.getTransaction().begin();
        
        vehiculoUpdate.setMatricula(vehiculo.getMatricula());
        vehiculoUpdate.setMarca(vehiculo.getMarca());
        vehiculoUpdate.setModelo(vehiculo.getModelo());
        vehiculoUpdate.setMotor(vehiculo.getMotor());
        vehiculoUpdate.setPotenciaCv(vehiculo.getPotenciaCv());
        vehiculoUpdate.setCarburante(vehiculo.getCarburante());
        vehiculoUpdate.setAceite(vehiculo.getAceite());
        vehiculoUpdate.setConsumo(vehiculo.getConsumo());
        vehiculoUpdate.setFechaEntrada(vehiculo.getFechaEntrada());
        vehiculoUpdate.setTiempoDedicado(vehiculo.getTiempoDedicado());
        vehiculoUpdate.setDniTrabajador(vehiculo.getDniTrabajador());
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void eliminarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion {
        em = emf.createEntityManager();
        Vehiculo vehiculoDelete = em.find(Vehiculo.class, vehiculo.getBastidor());
        em.getTransaction().begin();
        
        em.remove(vehiculoDelete);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public ArrayList<Informe> getListadoInformes() throws DAOInformeExcepcion {
        em = emf.createEntityManager();
        TypedQuery<Informe> query =
            em.createQuery("SELECT i FROM Informe i", Informe.class);
        ArrayList<Informe> listaInformes = (ArrayList)query.getResultList();
        

        // Close the database connection:
        em.close();
       
        return listaInformes;
    }

    @Override
    public void insertarInforme(Informe informe) throws DAOInformeExcepcion {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(informe);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void modificarInforme(Informe informe) throws DAOInformeExcepcion {
        em = emf.createEntityManager();
        Informe informeUpdate = em.find(Informe.class, informe.getNumExpedicion());
        em.getTransaction().begin();
        
        informeUpdate.setTareas(informe.getTareas());
        informeUpdate.setDniTrabajadorInforme(informe.getDniTrabajadorInforme());
        
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void eliminarInforme(Informe informe) throws DAOInformeExcepcion {
        em = emf.createEntityManager();
        Informe informeDelete = em.find(Informe.class, informe.getNumExpedicion());
        em.getTransaction().begin();
        
        em.remove(informeDelete);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public ArrayList<Factura> getListadoFacturas() throws DAOFacturaExcepcion {
        em = emf.createEntityManager();
        TypedQuery<Factura> query =
            em.createQuery("SELECT f FROM Factura f", Factura.class);
        ArrayList<Factura> listaFacturas = (ArrayList)query.getResultList();
        

        // Close the database connection:
        em.close();
        
        return listaFacturas;
    }

    @Override
    public void insertarFactura(Factura factura) throws DAOFacturaExcepcion {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(factura);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void modificarFactura(Factura factura) throws DAOFacturaExcepcion {
        em = emf.createEntityManager();
        Factura facturaUpdate = em.find(Factura.class, factura.getNumFactura());
        em.getTransaction().begin();
        
        facturaUpdate.setPrecio(factura.getPrecio());
        facturaUpdate.setLineaFactura(factura.getLineaFactura());
        
        facturaUpdate.setNumExpedicionInforme(factura.getNumExpedicionInforme()); 
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void eliminarFactura(Factura factura) throws DAOFacturaExcepcion {
        em = emf.createEntityManager();
        Factura facturaDelete = em.find(Factura.class, factura.getNumFactura());
        em.getTransaction().begin();
        
        em.remove(facturaDelete);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public ArrayList<Repuestos> getListadoRepuestos() throws DAORepuestoExcepcion {
        em = emf.createEntityManager();
        TypedQuery<Repuestos> query =
            em.createQuery("SELECT r FROM Repuestos r", Repuestos.class);
        ArrayList<Repuestos> listaRepuestos = (ArrayList)query.getResultList();
        

        // Close the database connection:
        em.close();
        
        
        return listaRepuestos;
    }

    @Override
    public void insertarRepuesto(Repuestos repuestos) throws DAORepuestoExcepcion {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(repuestos);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void modificarRepuesto(Repuestos repuesto) throws DAORepuestoExcepcion {
        em = emf.createEntityManager();
        Repuestos repuestoUpdate = em.find(Repuestos.class, repuesto.getNumSerie());
        em.getTransaction().begin();
        
        repuestoUpdate.setReferencia(repuesto.getReferencia());
        repuestoUpdate.setNombre(repuesto.getNombre());
        repuestoUpdate.setMarca(repuesto.getMarca());
        repuestoUpdate.setUso(repuesto.getUso());
        repuestoUpdate.setTienda(repuesto.getTienda());
        repuestoUpdate.setFechaCompra(repuesto.getFechaCompra());
        
        repuestoUpdate.setBastidorVehiculoRepuestos(repuesto.getBastidorVehiculoRepuestos());
        repuestoUpdate.setNumFacturaRepuestos(repuesto.getNumFacturaRepuestos());
         
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void eliminarRepuesto(Repuestos repuesto) throws DAORepuestoExcepcion {
        em = emf.createEntityManager();
        Repuestos repuestoDelete = em.find(Repuestos.class, repuesto.getNumSerie());
        em.getTransaction().begin();
        
        em.remove(repuestoDelete);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public ArrayList<Obd> getListadoObd() throws DAOObdExcepcion {
      em = emf.createEntityManager();
        TypedQuery<Obd> query =
            em.createQuery("SELECT o FROM Obd o", Obd.class);
        ArrayList<Obd> listaObd = (ArrayList)query.getResultList();
        

        // Close the database connection:
        em.close();
        
        return listaObd;
    }

    @Override
    public void insertarObd(Obd obd) throws DAOObdExcepcion {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        
        em.persist(obd);
        
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void modificarObd(Obd obd) throws DAOObdExcepcion {
        em = emf.createEntityManager();
        Obd obdUpdate = em.find(Obd.class, obd.getId());
        em.getTransaction().begin();
        
        obdUpdate.setRpm_inst(obd.getRpm_inst());
        obdUpdate.setTemp_aceite(obd.getTemp_aceite());
        obdUpdate.setTemp_agua(obd.getTemp_agua());
        obdUpdate.setCod_salida(obd.getCod_salida());
        obdUpdate.setSensores(obd.getSensores());
        obdUpdate.setBastidorVehiculoObd(obd.getBastidorVehiculoObd());
        
     
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void eliminarObd(Obd obd) throws DAOObdExcepcion {
        em = emf.createEntityManager();
        Obd obdDelete = em.find(Obd.class, obd.getId());
        em.getTransaction().begin();
        
        em.remove(obdDelete);
        
        em.getTransaction().commit();
        em.close();
    }

    

    
}
