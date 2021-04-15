/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 *
 * @author novad
 */
@Entity
@Table(name="informe")
public class Informe {
    @Id
    @Column(name="num_expedicion")
    private int numExpedicion;
    @Column(name="tareas")
    private String tareas;
    
    
    @OneToOne(mappedBy="informe")
    private Factura factura;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="informe")
    private Trabajador trabajador;
    
    
    public Informe() {
    }

    public Informe(int numExpedicion, String tareas, Factura factura, Trabajador trabajador) {
        this.numExpedicion = numExpedicion;
        this.tareas = tareas;
        this.factura = factura;
        this.trabajador = trabajador;
    }

    public int getNumExpedicion() {
        return numExpedicion;
    }

    public void setNumExpedicion(int numExpedicion) {
        this.numExpedicion = numExpedicion;
    }

    public String getTareas() {
        return tareas;
    }

    public void setTareas(String tareas) {
        this.tareas = tareas;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    
}
