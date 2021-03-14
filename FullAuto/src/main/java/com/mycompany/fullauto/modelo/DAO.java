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
    void insertarTrabajador(Trabajador tr) throws DAOTrabajadorExcepcion;
    void modificarTrabajador(Trabajador tr, String dniOriginal) throws DAOTrabajadorExcepcion;
    void eliminarTrabajador(Trabajador tr) throws DAOTrabajadorExcepcion;
    

    //Interfaz para vehículo
    ArrayList<Vehiculo> getListadoVehiculos() throws DAOVehiculoExcepcion;
    void insertarVehiculo(Vehiculo vh) throws DAOVehiculoExcepcion;
    void modificarVehiculo(Vehiculo vh, String bastidorOriginal) throws DAOVehiculoExcepcion;
    void eliminarVehiculo(Vehiculo vh) throws DAOVehiculoExcepcion;
   
    //Interfaz para Informe
    ArrayList<Informe> getListadoInformes() throws DAOInformeExcepcion;
    void insertarInforme (Informe inf) throws DAOInformeExcepcion;
    void modificarInforme(Informe inf,int numExpedicionOriginal) throws DAOInformeExcepcion;
    void eliminarInforme(Informe inf) throws DAOInformeExcepcion;
    
    //Interfaz para Factura
    ArrayList<Factura> getListadoFacturas() throws DAOFacturaExcepcion;
    void insertFactura (Factura fac) throws DAOFacturaExcepcion;
    void deleteFactura(Factura fac, int numFacturaOriginal) throws DAOFacturaExcepcion;
    void updateFactura(Factura fac) throws DAOFacturaExcepcion;
    
    //Interfaz para Repuestos
    ArrayList<Repuestos> getListadoRepuestos() throws DAORepuestoExcepcion;
    void insertarRepuesto (Repuestos rep)  throws DAORepuestoExcepcion;
    void modificarRepuesto(Repuestos rep, int numSerieOriginal) throws DAORepuestoExcepcion;
    void eliminarRepuesto(Repuestos rep) throws DAORepuestoExcepcion;
    
    
    //Interfaz para OBD
    ArrayList<Obd> getListadoObd() throws DAOObdExcepcion;
    void insertarObd (Obd obd) throws DAOObdExcepcion;
    void modificarObd(Obd obd, int idOriginal) throws DAOObdExcepcion;
    void eliminarObd(Obd obd) throws DAOObdExcepcion;
    
    
   
    
    
}
