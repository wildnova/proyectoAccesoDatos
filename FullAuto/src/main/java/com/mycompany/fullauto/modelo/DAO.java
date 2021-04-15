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
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author novad
 */
public interface DAO {
    
    //Interfaz para trabajador
    ArrayList<Trabajador> getListadoTrabajadores() throws DAOTrabajadorExcepcion;
    Trabajador comprobarTrabajador(String dniTrabajador) throws DAOTrabajadorExcepcion;
    void insertarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion;
    void modificarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion;
    void eliminarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion;
    

    //Interfaz para vehículo
    ArrayList<Vehiculo> getListadoVehiculos() throws DAOVehiculoExcepcion;
    void insertarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion;
    void modificarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion;
    void eliminarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion;
   
    //Interfaz para Informe
    ArrayList<Informe> getListadoInformes() throws DAOInformeExcepcion;
    void insertarInforme (Informe informe) throws DAOInformeExcepcion;
    void modificarInforme(Informe informe) throws DAOInformeExcepcion;
    void eliminarInforme(Informe informe) throws DAOInformeExcepcion;
    
    //Interfaz para Factura
    ArrayList<Factura> getListadoFacturas() throws DAOFacturaExcepcion;
    void insertarFactura(Factura factura) throws DAOFacturaExcepcion;
    void modificarFactura(Factura factura) throws DAOFacturaExcepcion;
    void eliminarFactura(Factura factura) throws DAOFacturaExcepcion;
    
    
    //Interfaz para Repuestos
    ArrayList<Repuestos> getListadoRepuestos() throws DAORepuestoExcepcion;
    void insertarRepuesto(Repuestos repuestos)  throws DAORepuestoExcepcion;
    void modificarRepuesto(Repuestos repuestos) throws DAORepuestoExcepcion;
    void eliminarRepuesto(Repuestos repuestos) throws DAORepuestoExcepcion;
    
    
    //Interfaz para OBD
    ArrayList<Obd> getListadoObd() throws DAOObdExcepcion;
    void insertarObd (Obd obd) throws DAOObdExcepcion;
    void modificarObd(Obd obd)throws DAOObdExcepcion;
    void eliminarObd(Obd obd) throws DAOObdExcepcion;
    
}
