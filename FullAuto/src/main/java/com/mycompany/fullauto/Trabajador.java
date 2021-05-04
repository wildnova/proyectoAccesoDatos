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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author novad
 */
@Entity
@Table (name="trabajador")
public class Trabajador {
    @Id
    @Column(name="dni")
    private String dni;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido1")
    private String apellido1;
    @Column(name="apellido2")
    private String apellido2;
    @Column(name="funcion")
    private String funcion;
    
    @OneToMany(targetEntity=com.mycompany.fullauto.Vehiculo.class,mappedBy="trabajador")
    private Set<Vehiculo> vehiculo;
    
    @OneToMany(targetEntity=com.mycompany.fullauto.Informe.class,mappedBy="trabajador")
    private Set<Informe> informe;
    
    @ManyToMany(targetEntity=com.mycompany.fullauto.Repuestos.class)
    private Set<Repuestos> repuestos;
 
    public Trabajador() {
    }

    public Trabajador(String dni, String nombre, String apellido1, String apellido2, String funcion, Set<Vehiculo> vehiculo, Set<Informe> informe, Set<Repuestos> repuestos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.funcion = funcion;
        this.vehiculo = vehiculo;
        this.informe = informe;
        this.repuestos = repuestos;
    }
    public Trabajador(String dni, String nombre, String apellido1, String apellido2, String funcion)
    {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.funcion = funcion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public Set<Vehiculo> getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Set<Vehiculo> vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Set<Informe> getInforme() {
        return informe;
    }

    public void setInforme(Set<Informe> informe) {
        this.informe = informe;
    }

    public Set<Repuestos> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(Set<Repuestos> repuestos) {
        this.repuestos = repuestos;
    }

    

    

}