/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.controlador;

import com.mycompany.fullauto.Exceptions.DAOConexionExcepcion;
import com.mycompany.fullauto.Exceptions.DAOTrabajadorExcepcion;
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
    private ModeloJdbc modeloJdbc;
    private ModeloHibernate modeloHibernate;
    private ModeloObjectDB modeloObjectDB;
    private ModeloMongo modeloMongo;
    private Vista vista;
    
    /*private ArrayList<Trabajador> listaTrabajadores;
    private ArrayList<Vehiculo> listaVehiculos;
    private ArrayList<Obd> listaObd;
    private ArrayList<Repuestos> listaRepuestos;
    private ArrayList<Informe> listaInformes;
    private ArrayList<Factura> listaFacturas;
    
    private int contadorTrabajador;
    private int contadorVehiculo;*/
   
    
    public Controlador(Vista vista)
    {
        this.vista=vista;
        this.modeloJdbc= new ModeloJdbc();
        this.modeloHibernate = new ModeloHibernate();
        this.modeloObjectDB = new ModeloObjectDB();
        this.modeloMongo = new ModeloMongo();

    }
   
    public ArrayList<Trabajador> getListadoTrabajadores()
    {
        ArrayList<Trabajador> listaTrabajadores = null;
        try 
        {
            listaTrabajadores = modeloJdbc.getListadoTrabajadores();
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
            modeloJdbc.insertarTrabajador(trabajador);
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
            modeloJdbc.modificarTrabajador(trabajador,dniOriginal);
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
            modeloJdbc.eliminarTrabajador(trabajador);
        }
        catch (DAOTrabajadorExcepcion ex) 
        {
            System.out.println("Error al eliminar el trabajador");
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
