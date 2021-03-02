/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.modelo;

import com.mycompany.fullauto.Exceptions.DAOTrabajadorExcepcion;
import com.mycompany.fullauto.Factura;
import com.mycompany.fullauto.Informe;
import com.mycompany.fullauto.Obd;
import com.mycompany.fullauto.Repuestos;
import com.mycompany.fullauto.Trabajador;
import com.mycompany.fullauto.Vehiculo;
import java.util.Set;

/**
 *
 * @author novad
 */
public interface DAO {
    
    //Interfaz para trabajador
    void insertTrabajador(Trabajador tr) throws DAOTrabajadorExcepcion;
    void deleteTrabajador(Trabajador tr) throws DAOTrabajadorExcepcion;
    void updateTrabajador(Trabajador tr) throws DAOTrabajadorExcepcion;
    Trabajador getTrabajador(String dni) throws DAOTrabajadorExcepcion;
    int getNumTrabajadores();
    
    
    
    //Interfaz para vehículo
    void insertVehiculo(Vehiculo vh);
    void deleteVehiculo(Vehiculo vh);
    void updateVehiculo(Vehiculo vh);
    Vehiculo getVehiculo(String bastidor);
    int getNumVehiculos();
    
    //Interfaz para Repuestos
    void insertRepuesto (Repuestos rep);
    void deleteRepuesto(Repuestos rep);
    void updateVehiculo(Repuestos rep);
    Repuestos getRepuesto (String numSerie);
    int getNumRepuestos();
    
    //Interfaz para OBD
    void insertObd (Obd obd);
    void deleteObd(Obd obd);
    void updateObd(Obd obd);
    Obd getObd (int id);
    
    //Interfaz para Informe
    void insertInforme (Informe inf);
    void deleteInforme(Informe inf);
    void updateInforme(Informe inf);
    Informe getInforme (int numExpedicion);
    
    //Interfaz para Factura
    void insertFactura (Factura fac);
    void deleteFactura(Factura fac);
    void updateFactura(Factura fac);
    Factura getFactura (int numFactura);
    
}
