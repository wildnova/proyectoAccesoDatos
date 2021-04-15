/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.controlador;

import com.mycompany.fullauto.Exceptions.DAOConexionExcepcion;
import com.mycompany.fullauto.Exceptions.DAOTrabajadorExcepcion;
import com.mycompany.fullauto.Exceptions.DAOVehiculoExcepcion;
import com.mycompany.fullauto.Factura;
import com.mycompany.fullauto.Informe;
import com.mycompany.fullauto.Obd;
import com.mycompany.fullauto.Repuestos;
import com.mycompany.fullauto.Trabajador;
import com.mycompany.fullauto.Vehiculo;
import com.mycompany.fullauto.modelo.DAO;
import com.mycompany.fullauto.modelo.ModeloHibernate;
import com.mycompany.fullauto.modelo.ModeloJdbc;
import com.mycompany.fullauto.modelo.ModeloMongo;
import com.mycompany.fullauto.modelo.ModeloObjectDB;
import com.mycompany.fullauto.vista.EliminarTrabajador;
import com.mycompany.fullauto.vista.FormularioTrabajador;
import com.mycompany.fullauto.vista.Vista;
import java.util.ArrayList;
import java.util.List;




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
    
    public void modificarTrabajador(Trabajador trabajador, String dniOriginal)
    {
   
        try {
            modelo.modificarTrabajador(trabajador,dniOriginal);
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
            System.out.println("Error obteniendo la lista de trabajadores");
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
    
    public void modificarVehiculo(Vehiculo vehiculo, String bastidorOriginal)
    {
   
        try {
            modelo.modificarVehiculo(vehiculo,bastidorOriginal);
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
    /*
    
    //Funciones para la pestaña vehículo que utiliza hibernate
    public void mostrarDatosVehiculo() throws DAOConexionExcepcion
    {
  
        listaVehiculos = modeloHibernate.getListadoVehiculos();
        /*String[] datosVehiculo = new String[10];
        System.out.println(listaVehiculos.get(contadorVehiculo).getMarca());
        vista.cargarCamposVehiculo(datosVehiculo);
    }*/
    
}
