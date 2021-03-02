/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto;

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
public class Obd {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="rpm_inst")
    private int rpm_inst;
    @Column(name="rpm_avg")
    private int rpm_avg;
    @Column(name="consumo_inst")
    private float consumo_inst;
    @Column(name="consumo_avg")
    private float consumo_avg;
    @Column(name="temp_aceite")
    private float temp_aceite;
    @Column(name="temp_agua")
    private float temp_agua;
    @Column(name="cod_salida")
    private String cod_salida;
    
    @OneToOne(mappedBy="obd")
    private Vehiculo vehiculo;

    
    public Obd(){
    }

    public Obd(int id, int rpm_inst, int rpm_avg, float consumo_inst, float consumo_avg, float temp_aceite, float temp_agua, String cod_salida, Vehiculo vehiculo) {
        this.id = id;
        this.rpm_inst = rpm_inst;
        this.rpm_avg = rpm_avg;
        this.consumo_inst = consumo_inst;
        this.consumo_avg = consumo_avg;
        this.temp_aceite = temp_aceite;
        this.temp_agua = temp_agua;
        this.cod_salida = cod_salida;
        this.vehiculo = vehiculo;
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

    public int getRpm_avg() {
        return rpm_avg;
    }

    public void setRpm_avg(int rpm_avg) {
        this.rpm_avg = rpm_avg;
    }

    public float getConsumo_inst() {
        return consumo_inst;
    }

    public void setConsumo_inst(float consumo_inst) {
        this.consumo_inst = consumo_inst;
    }

    public float getConsumo_avg() {
        return consumo_avg;
    }

    public void setConsumo_avg(float consumo_avg) {
        this.consumo_avg = consumo_avg;
    }

    public float getTemp_aceite() {
        return temp_aceite;
    }

    public void setTemp_aceite(float temp_aceite) {
        this.temp_aceite = temp_aceite;
    }

    public float getTemp_agua() {
        return temp_agua;
    }

    public void setTemp_agua(float temp_agua) {
        this.temp_agua = temp_agua;
    }

    public String getCod_salida() {
        return cod_salida;
    }

    public void setCod_salida(String cod_salida) {
        this.cod_salida = cod_salida;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    

    
    
    
}
