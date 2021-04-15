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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author novad
 */

@Entity
@Table(name="vehiculo")
public class Vehiculo{
    @Id
    @Column(name="bastidor")
    private String bastidor;
    @Column(name="matricula")
    private String matricula;
    @Column(name="marca")
    private String marca;
    @Column(name="modelo")
    private String modelo;
    @Column(name="motor")
    private String motor;
    @Column(name="potenciaCv")
    private String potenciaCv;
    @Column(name="carburante")
    private String carburante;
    @Column(name="aceite")
    private String aceite;
    @Column(name="consumo")
    private String consumo;
    @Column(name="fecha_entrada")
    private Date fechaEntrada;
    @Column(name="tiempo_dedicado")
    private String tiempoDedicado;
    @Column(name="dni_trabajador")
    private String dniTrabajador;
    
    @ManyToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="dni")
    private Trabajador trabajador;
    
    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="id")
    private Obd obd;
    
    @OneToMany(mappedBy="vehiculo",cascade=CascadeType.DETACH)
    private Set<Repuestos> repuestos;
    
    public Vehiculo() {
    }
    
    public Vehiculo(String bastidor, String matricula, String marca, String modelo, String motor, String potenciaCv, String carburante, String aceite, String consumo, Date fechaEntrada, String tiempoDedicado,String dniTrabajador) {
        this.bastidor = bastidor;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.potenciaCv = potenciaCv;
        this.carburante = carburante;
        this.aceite = aceite;
        this.consumo = consumo;
        this.fechaEntrada = fechaEntrada;
        this.tiempoDedicado = tiempoDedicado;
        this.dniTrabajador = dniTrabajador;
    }
    public Vehiculo(String bastidor, String matricula, String marca, String modelo, String motor, String potenciaCv, String carburante, String aceite, String consumo, Date fechaEntrada, String tiempoDedicado,String dniTrabajador, Trabajador trabajador, Obd obd, Set<Repuestos> repuestos) {
        this.bastidor = bastidor;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.motor = motor;
        this.potenciaCv = potenciaCv;
        this.carburante = carburante;
        this.aceite = aceite;
        this.consumo = consumo;
        this.fechaEntrada = fechaEntrada;
        this.tiempoDedicado = tiempoDedicado;
        this.dniTrabajador = dniTrabajador;
        this.trabajador = trabajador;
        this.obd = obd;
        this.repuestos = repuestos;
    }

    public String getBastidor() {
        return bastidor;
    }

    public void setBastidor(String bastidor) {
        this.bastidor = bastidor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getPotenciaCv() {
        return potenciaCv;
    }

    public void setPotenciaCv(String potenciaCv) {
        this.potenciaCv = potenciaCv;
    }

    public String getCarburante() {
        return carburante;
    }

    public void setCarburante(String carburante) {
        this.carburante = carburante;
    }

    public String getAceite() {
        return aceite;
    }

    public void setAceite(String aceite) {
        this.aceite = aceite;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getTiempoDedicado() {
        return tiempoDedicado;
    }

    public void setTiempoDedicado(String tiempoDedicado) {
        this.tiempoDedicado = tiempoDedicado;
    }

    public String getDniTrabajador() {
        return dniTrabajador;
    }

    public void setDniTrabajador(String dniTrabajador) {
        this.dniTrabajador = dniTrabajador;
    }
    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Obd getObd() {
        return obd;
    }

    public void setObd(Obd obd) {
        this.obd = obd;
    }

    public Set<Repuestos> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(Set<Repuestos> repuestos) {
        this.repuestos = repuestos;
    }

    
    
    
}
