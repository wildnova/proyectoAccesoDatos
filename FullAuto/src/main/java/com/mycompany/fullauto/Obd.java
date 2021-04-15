/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author novad
 */
@Entity
@Table(name="obd")
public class Obd implements Serializable {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="rpm_inst")
    private int rpm_inst;
    @Column(name="temp_aceite")
    private double temp_aceite;
    @Column(name="temp_agua")
    private double temp_agua;
    @Column(name="cod_salida")
    private String cod_salida;
    @Column(name="sensores")
    private String sensores;

    private String bastidorVehiculoObd;

    
    
    @OneToOne(mappedBy="obd")
    private Vehiculo vehiculo;

    
    public Obd(){
    }

    public Obd(int id, int rpm_inst, double temp_aceite, double temp_agua, String cod_salida, String sensores, Vehiculo vehiculo) {
        this.id = id;
        this.rpm_inst = rpm_inst;
        this.temp_aceite = temp_aceite;
        this.temp_agua = temp_agua;
        this.cod_salida = cod_salida;
        this.sensores = sensores;
        this.vehiculo = vehiculo;
    }
    public Obd(int id, int rpm_inst, double temp_aceite, double temp_agua, String cod_salida, String sensores,String bastidorVehiculoObd) {
        this.id = id;
        this.rpm_inst = rpm_inst;
        this.temp_aceite = temp_aceite;
        this.temp_agua = temp_agua;
        this.cod_salida = cod_salida;
        this.sensores = sensores;
        this.bastidorVehiculoObd=bastidorVehiculoObd;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRpm_inst() {
        return rpm_inst;
    }

    public void setRpm_inst(int rpm_inst) {
        this.rpm_inst = rpm_inst;
    }

    public double getTemp_aceite() {
        return temp_aceite;
    }

    public void setTemp_aceite(double temp_aceite) {
        this.temp_aceite = temp_aceite;
    }

    public double getTemp_agua() {
        return temp_agua;
    }

    public void setTemp_agua(double temp_agua) {
        this.temp_agua = temp_agua;
    }

    public String getCod_salida() {
        return cod_salida;
    }

    public void setCod_salida(String cod_salida) {
        this.cod_salida = cod_salida;
    }
    public String getSensores() {
        return sensores;
    }

    public void setSensores(String sensores) {
        this.sensores = sensores;
    }
    public String getBastidorVehiculoObd() {
        return bastidorVehiculoObd;
    }

    public void setBastidorVehiculoObd(String bastidorVehiculoObd) {
        this.bastidorVehiculoObd = bastidorVehiculoObd;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    

    
    
    
}
