/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.controlador;

import com.mycompany.fullauto.Exceptions.DAOConexionExcepcion;
import com.mycompany.fullauto.Exceptions.DAOFacturaExcepcion;
import com.mycompany.fullauto.Exceptions.DAOInformeExcepcion;
import com.mycompany.fullauto.Exceptions.DAOObdExcepcion;
import com.mycompany.fullauto.Exceptions.DAORepuestoExcepcion;
import com.mycompany.fullauto.Exceptions.DAOTrabajadorExcepcion;
import com.mycompany.fullauto.Exceptions.DAOVehiculoExcepcion;
import com.mycompany.fullauto.Factura;
import com.mycompany.fullauto.Informe;
import com.mycompany.fullauto.clasesXml.TrabajadoresXml;
import com.mycompany.fullauto.Obd;
import com.mycompany.fullauto.Repuestos;
import com.mycompany.fullauto.Trabajador;
import com.mycompany.fullauto.Vehiculo;
import com.mycompany.fullauto.clasesXml.FacturasXml;
import com.mycompany.fullauto.clasesXml.InformesXml;
import com.mycompany.fullauto.clasesXml.ObdsXml;
import com.mycompany.fullauto.clasesXml.RepuestosXml;
import com.mycompany.fullauto.clasesXml.VehiculosXml;
import com.mycompany.fullauto.modelo.DAO;
import com.mycompany.fullauto.modelo.ModeloHibernate;
import com.mycompany.fullauto.modelo.ModeloJdbc;
import com.mycompany.fullauto.modelo.ModeloMongo;
import com.mycompany.fullauto.modelo.ModeloObjectDB;
import com.mycompany.fullauto.vista.EliminarTrabajador;
import com.mycompany.fullauto.vista.FormularioTrabajador;
import com.mycompany.fullauto.vista.Vista;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;




/**
 *
 * @author novad
 */
public class Controlador 
{
    private DAO modelo;
    private Vista vista;

    public Controlador(Vista vista, int seleccionModelo)
    {
        this.vista=vista;

        switch(seleccionModelo)
        {
            case 1:
                this.modelo= new ModeloJdbc();
                break;

            case 2:
                this.modelo = new ModeloHibernate();
                break;

            case 3:
                this.modelo = new ModeloObjectDB();
                break;

            case 4:
                this.modelo = new ModeloMongo();
                break;
        }
    }
    
   //Funciones de trabajador
    public ArrayList<Trabajador> getListadoTrabajadores()
    {
        ArrayList<Trabajador> listaTrabajadores = null;
        try 
        {
            listaTrabajadores = modelo.getListadoTrabajadores();
        }
        catch (DAOTrabajadorExcepcion ex) 
        {
            System.out.println("Error obteniendo la lista de trabajadores");
            ex.getMessage();
        }
        return listaTrabajadores;
        
    }
    public void insertarTrabajador(Trabajador trabajador)
    {
        try 
        {
            modelo.insertarTrabajador(trabajador);
            //mostrarDatosTrabajador();
        } 
        catch (DAOTrabajadorExcepcion ex) 
        {
            System.out.println("Error insertando el trabajador");
            ex.getMessage();
        } 
    }
    
    public void modificarTrabajador(Trabajador trabajador)
    {
   
        try {
            modelo.modificarTrabajador(trabajador);
        } 
        catch (DAOTrabajadorExcepcion ex) 
        {
            System.out.println("Error al modificar el trabajador");
            ex.getMessage();
        }
        
    }
    
    public void eliminarTrabajador(Trabajador trabajador)
    {
  
        try 
        {
            modelo.eliminarTrabajador(trabajador);
        }
        catch (DAOTrabajadorExcepcion ex) 
        {
            System.out.println("Error al eliminar el trabajador");
            ex.getMessage();
        }
 
    }
    
    //Funciones de vehículo
    public ArrayList<Vehiculo> getListadoVehiculos()
    {
        ArrayList<Vehiculo> listaVehiculos = null;
        try 
        {
            listaVehiculos = modelo.getListadoVehiculos();
        }
        catch (DAOVehiculoExcepcion ex) 
        {
            System.out.println("Error obteniendo la lista de vehículos");
            ex.getMessage();
        }
        return listaVehiculos;
        
    }
    
    public void insertarVehiculo(Vehiculo vehiculo)
    {
        try 
        {
            modelo.insertarVehiculo(vehiculo);
            
        } 
        catch (DAOVehiculoExcepcion ex) 
        {
            System.out.println("Error insertando el vehículo");
            ex.getMessage();
        } 
    }
    
    public void modificarVehiculo(Vehiculo vehiculo)
    {
   
        try {
            modelo.modificarVehiculo(vehiculo);
        } 
        catch (DAOVehiculoExcepcion ex) 
        {
            System.out.println("Error al modificar el vehículo");
            ex.getMessage();
        }
        
    }
    
    public void eliminarVehiculo(Vehiculo vehiculo)
    {
        try 
        {
            modelo.eliminarVehiculo(vehiculo);
        }
        catch (DAOVehiculoExcepcion ex) 
        {
            System.out.println("Error al eliminar el vehículo");
            ex.getMessage();
        }
 
    }
    
    //Funciones de informe
    
    public ArrayList<Informe> getListadoInformes()
    {
        ArrayList<Informe> listaInformes = null;
        try 
        {
            listaInformes = modelo.getListadoInformes();
        }
        catch (DAOInformeExcepcion ex) 
        {
            System.out.println("Error obteniendo la lista de informes");
            ex.getMessage();
        }
        return listaInformes;
        
    }
     public void insertarInforme(Informe informe)
    {
        try 
        {
            modelo.insertarInforme(informe);
            
        } 
        catch (DAOInformeExcepcion ex) 
        {
            System.out.println("Error insertando el informe");
            ex.getMessage();
        } 
    }
     public void modificarInforme(Informe informe)
     {
         try {
            modelo.modificarInforme(informe);
        } 
        catch (DAOInformeExcepcion ex) 
        {
            System.out.println("Error al modificar el informe");
            ex.getMessage();
        }
     }
     public void eliminarInforme(Informe informe)
    {
        try 
        {
            modelo.eliminarInforme(informe);
        }
        catch (DAOInformeExcepcion ex) 
        {
            System.out.println("Error al eliminar el informe");
            ex.getMessage();
        }
 
    }
     
     //Funciones de Factura
      public ArrayList<Factura> getListadoFacturas()
    {
        ArrayList<Factura> listaFacturas = null;
        try 
        {
            listaFacturas = modelo.getListadoFacturas();
        }
        catch (DAOFacturaExcepcion ex) 
        {
            System.out.println("Error obteniendo la lista de facturas");
            ex.getMessage();
        }
        return listaFacturas;
        
    }
      public void insertarFactura(Factura factura)
      {
      try 
        {
            modelo.insertarFactura(factura);
            
        } 
        catch (DAOFacturaExcepcion ex) 
        {
            System.out.println("Error insertando la factura");
            ex.getMessage();
        } 
      }
      public void modificarFactura(Factura factura)
     {
         try {
            modelo.modificarFactura(factura);
        } 
        catch (DAOFacturaExcepcion ex) 
        {
            System.out.println("Error al modificar la factura");
            ex.getMessage();
        }
     }
      public void eliminarFactura(Factura factura)
    {
        try 
        {
            modelo.eliminarFactura(factura);
        }
        catch (DAOFacturaExcepcion ex) 
        {
            System.out.println("Error al eliminar la factura");
            ex.getMessage();
        }
 
    }
    
      //Funciones de repuestos
    public ArrayList<Repuestos> getListadoRepuestos()
    {
        ArrayList<Repuestos> listaRepuestos = null;
        try 
        {
            listaRepuestos = modelo.getListadoRepuestos();
        }
        catch (DAORepuestoExcepcion ex) 
        {
            System.out.println("Error obteniendo la lista de repuestos");
            ex.getMessage();
        }
        return listaRepuestos;
        
    }
      public void insertarRepuesto(Repuestos repuesto)
      {
      try 
        {
            modelo.insertarRepuesto(repuesto);
            
        } 
        catch (DAORepuestoExcepcion ex) 
        {
            System.out.println("Error insertando el repuesto");
            ex.getMessage();
        } 
      }
      public void modificarRepuesto(Repuestos repuesto)
     {
         try {
            modelo.modificarRepuesto(repuesto);
        } 
        catch (DAORepuestoExcepcion ex) 
        {
            System.out.println("Error al modificar el repuesto");
            ex.getMessage();
        }
     }
      public void eliminarRepuesto(Repuestos repuesto)
    {
        try 
        {
            modelo.eliminarRepuesto(repuesto);
        }
        catch (DAORepuestoExcepcion ex) 
        {
            System.out.println("Error al eliminar el repuesto");
            ex.getMessage();
        }
 
    }
    
      //Funciones de Obd
      
      public ArrayList<Obd> getListadoObd()
    {
        ArrayList<Obd> listaObd = null;
        try 
        {
            listaObd = modelo.getListadoObd();
        }
        catch (DAOObdExcepcion ex) 
        {
            System.out.println("Error obteniendo la lista Obd");
            ex.getMessage();
        }
        return listaObd;
        
    }
      public void insertarObd(Obd obd)
      {
      try 
        {
            modelo.insertarObd(obd);
            
        } 
        catch (DAOObdExcepcion ex) 
        {
            System.out.println("Error insertando este registro Obd");
            ex.getMessage();
        } 
      }
      public void modificarObd(Obd obd)
     {
         try {
            modelo.modificarObd(obd);
        } 
        catch (DAOObdExcepcion ex) 
        {
            System.out.println("Error al modificar el registro Obd");
            ex.getMessage();
        }
     }
    public void eliminarObd(Obd obd)
    {
        try 
        {
            modelo.eliminarObd(obd);
        }
        catch (DAOObdExcepcion ex) 
        {
            System.out.println("Error al eliminar el registro Obd");
            ex.getMessage();
        }
 
    }
    
    //Funciones especiales
    
    public String comprobarTrabajadorAsignado(Vehiculo vehiculo)
    {
        
        String dniTrabajadorAsignado=vehiculo.getDniTrabajador();
        
        Trabajador trabajador=null;
        try 
        {
            trabajador = modelo.comprobarTrabajador(dniTrabajadorAsignado);
            System.out.println("dniTrabajador asignado en controlador: ------------------------------------------------"+ dniTrabajadorAsignado);
        } 
        catch (DAOTrabajadorExcepcion ex) 
        {
            System.err.println("Error al comprobar el trabajador");
            ex.getMessage();
            ex.printStackTrace();
        }
        String nombreCompleto=trabajador.getNombre()+" "+trabajador.getApellido1()+" "+trabajador.getApellido2();
        return nombreCompleto;
    }
    public ArrayList getListaDni()
    {
      ArrayList<String> listaDni=new ArrayList();
      ArrayList<Trabajador> listaTrabajadores=null;
        try {
            listaTrabajadores = modelo.getListadoTrabajadores();
            
        } catch (DAOTrabajadorExcepcion ex) {
            ex.printStackTrace();
            System.err.println("Error comprobando la lista de trabajadores");
        }
        
      for(Trabajador trabajador:listaTrabajadores)
      {
          listaDni.add(trabajador.getDni());
      }
      return listaDni;
    }
    public ArrayList getListaNumsExpedicion()
    {
        ArrayList<String> listaNumsExpedicion=new ArrayList();
        ArrayList<Informe> listaInformes=null;
        try {
            listaInformes = modelo.getListadoInformes();
            
        } catch (DAOInformeExcepcion ex) {
            ex.printStackTrace();
            System.err.println("Error comprobando la lista de informes");
        }
        
      for(Informe informe:listaInformes)
      {
          listaNumsExpedicion.add(String.valueOf(informe.getNumExpedicion()));
      }
      return listaNumsExpedicion;
    }
    public ArrayList getListaBastidores()
    {
        ArrayList<String> listaBastidores=new ArrayList();
        ArrayList<Vehiculo> listaVehiculos=null;
        try {
            listaVehiculos = modelo.getListadoVehiculos();
            
        } catch (DAOVehiculoExcepcion ex) {
            ex.printStackTrace();
            System.err.println("Error comprobando la lista de vehículos");
        }
        
      for(Vehiculo vehiculo:listaVehiculos)
      {
          listaBastidores.add(String.valueOf(vehiculo.getBastidor()));
      }
      return listaBastidores;
    }
    public ArrayList getListaNumsFactura()
    {
        ArrayList<String> listaNumsFactura=new ArrayList();
        ArrayList<Factura> listaFacturas=null;
        try {
            listaFacturas = modelo.getListadoFacturas();
            
        } catch (DAOFacturaExcepcion ex) {
            ex.printStackTrace();
            System.err.println("Error comprobando la lista de facturas");
        }
        
      for(Factura factura:listaFacturas)
      {
          listaNumsFactura.add(String.valueOf(factura.getNumFactura()));
      }
      return listaNumsFactura;
    }
    
    public void exportarXml()
    {
        JAXBContext context=null;
        Marshaller mar=null;
        TrabajadoresXml listaTrabajadores = new TrabajadoresXml();
        VehiculosXml listaVehiculos = new VehiculosXml();
        InformesXml listaInformes = new InformesXml();
        FacturasXml listaFacturas = new FacturasXml();
        RepuestosXml listaRepuestos = new RepuestosXml();
        ObdsXml listaObds = new ObdsXml();
        
        try 
        {
            listaTrabajadores.setTrabajadores(modelo.getListadoTrabajadores());
            listaVehiculos.setVehiculos(modelo.getListadoVehiculos());
            listaInformes.setInformes(modelo.getListadoInformes());
            listaFacturas.setFacturas(modelo.getListadoFacturas());
            listaRepuestos.setRepuestos(modelo.getListadoRepuestos());
            listaObds.setObds(modelo.getListadoObd());
        } 
        catch (DAOTrabajadorExcepcion ex){
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }catch (DAOVehiculoExcepcion ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOInformeExcepcion ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOFacturaExcepcion ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAORepuestoExcepcion ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOObdExcepcion ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        File file = new File(".\\exportacionesXML\\trabajadores.xml");
        
        try 
        {
            context = JAXBContext.newInstance(TrabajadoresXml.class);
            mar=context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(listaTrabajadores, file);
        } 
        catch (JAXBException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        file = new File(".\\exportacionesXML\\vehiculos.xml");
        try 
        {
            context = JAXBContext.newInstance(VehiculosXml.class);
            mar=context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(listaVehiculos, file);
        } 
        catch (JAXBException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        file = new File(".\\exportacionesXML\\informes.xml");
        try 
        {
            context = JAXBContext.newInstance(InformesXml.class);
            mar=context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(listaInformes, file);
        } 
        catch (JAXBException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        file = new File(".\\exportacionesXML\\facturas.xml");
        try 
        {
            context = JAXBContext.newInstance(FacturasXml.class);
            mar=context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(listaFacturas, file);
        } 
        catch (JAXBException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        file = new File(".\\exportacionesXML\\repuestos.xml");
        try 
        {
            context = JAXBContext.newInstance(RepuestosXml.class);
            mar=context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(listaRepuestos, file);
        } 
        catch (JAXBException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        file = new File(".\\exportacionesXML\\obds.xml");
        try 
        {
            context = JAXBContext.newInstance(ObdsXml.class);
            mar=context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(listaObds, file);
        } 
        catch (JAXBException ex) 
        {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
