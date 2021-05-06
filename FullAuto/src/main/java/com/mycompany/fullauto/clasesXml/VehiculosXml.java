/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.clasesXml;


import com.mycompany.fullauto.Trabajador;
import com.mycompany.fullauto.Vehiculo;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author novad
 */
@XmlRootElement(name = "Vehiculos")
@XmlAccessorType (XmlAccessType.FIELD)
public class VehiculosXml {
    private List<Vehiculo> vehiculos = null;
 
    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }
 
    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
}
