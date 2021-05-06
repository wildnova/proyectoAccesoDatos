/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author novad
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="factura")
public class Factura {
    
    @Id
    @Column(name="num_factura")
    private int numFactura;
    @Column(name="precio")
    private double precio;
    @Column(name="lineaFactura")
    private String lineaFactura;

    @Column(name="num_expedicion_informe", updatable = false)
    private String numExpedicionInforme;
    
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="num_expedicion")
    private Informe informe;
    
    @OneToMany(targetEntity=com.mycompany.fullauto.Repuestos.class,mappedBy="factura")
    private Set<Repuestos> repuestos;
    
    public Factura() {
    }

    public Factura(int numFactura, double precio, String lineaFactura, Informe informe, Set<Repuestos> repuestos) {
        this.numFactura = numFactura;
        this.precio = precio;
        this.lineaFactura = lineaFactura;
        this.informe = informe;
        this.repuestos = repuestos;
    }
    public Factura(int numFactura, double precio, String lineaFactura, String numExpedicionInforme){
        this.numFactura = numFactura;
        this.precio = precio;
        this.lineaFactura = lineaFactura;
        this.numExpedicionInforme = numExpedicionInforme;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getLineaFactura() {
        return lineaFactura;
    }

    public void setLineaFactura(String lineaFactura) {
        this.lineaFactura = lineaFactura;
    }

    public Informe getInforme() {
        return informe;
    }

    public void setInforme(Informe informe) {
        this.informe = informe;
    }

    public Set<Repuestos> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(Set<Repuestos> repuestos) {
        this.repuestos = repuestos;
    }

    public String getNumExpedicionInforme() {
        return numExpedicionInforme;
    }

    public void setNumExpedicionInforme(String numExpedicionInforme) {
        this.numExpedicionInforme = numExpedicionInforme;
    }
    
    
    
}
