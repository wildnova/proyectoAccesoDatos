/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author novad
 */
@Entity
@Table(name="repuestos")
public class Repuestos {
    @Id
    @Column(name="num_serie")
    private String numSerie;
    @Column(name="referencia")
    private String referencia;
    @Column(name="nombre")
    private String nombre;
    @Column(name="marca")
    private String marca;
    @Column(name="uso")
    private String uso;
    @Column(name="tienda")
    private String tienda;
    @Column(name="fecha_compra")
    private Date fechaCompra;

    @Column(name="bastidor_vehiculo_repuestos", updatable = false)
    private String BastidorVehiculoRepuestos;
    @Column(name="num_factura_repuestos", updatable = false)
    private String NumFacturaRepuestos;
    
    @ManyToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="bastidor")
    private Vehiculo vehiculo;
    @ManyToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="num_factura")
    private Factura factura;
    
    @ManyToMany(targetEntity=com.mycompany.fullauto.Trabajador.class,mappedBy = "repuestos")
    private Set<Trabajador> trabajador;
    
    public Repuestos() {
    }

    public Repuestos(String numSerie, String referencia, String nombre, String marca, String uso, String tienda, Date fechaCompra, Vehiculo vehiculo, Factura factura, Set<Trabajador> trabajador) {
        this.numSerie = numSerie;
        this.referencia = referencia;
        this.nombre = nombre;
        this.marca = marca;
        this.uso = uso;
        this.tienda = tienda;
        this.fechaCompra = fechaCompra;
        this.vehiculo = vehiculo;
        this.factura = factura;
        this.trabajador = trabajador;
    }
    public Repuestos(String numSerie, String referencia, String nombre, String marca, String uso, String tienda, Date fechaCompra, String bastidorVehiculoRepuestos,String numFacturaRepuestos) {
        this.numSerie = numSerie;
        this.referencia = referencia;
        this.nombre = nombre;
        this.marca = marca;
        this.uso = uso;
        this.tienda = tienda;
        this.fechaCompra = fechaCompra;
        this.BastidorVehiculoRepuestos = bastidorVehiculoRepuestos;
        this.NumFacturaRepuestos = numFacturaRepuestos;
        
    }
    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    public String getBastidorVehiculoRepuestos() {
        return BastidorVehiculoRepuestos;
    }

    public void setBastidorVehiculoRepuestos(String BastidorVehiculoRepuestos) {
        this.BastidorVehiculoRepuestos = BastidorVehiculoRepuestos;
    }

    public String getNumFacturaRepuestos() {
        return NumFacturaRepuestos;
    }

    public void setNumFacturaRepuestos(String NumFacturaRepuestos) {
        this.NumFacturaRepuestos = NumFacturaRepuestos;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Set<Trabajador> getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Set<Trabajador> trabajador) {
        this.trabajador = trabajador;
    }

    

   
}
