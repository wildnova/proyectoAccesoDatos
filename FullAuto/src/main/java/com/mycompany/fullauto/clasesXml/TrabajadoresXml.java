/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.clasesXml;


import com.mycompany.fullauto.Trabajador;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author novad
 */
@XmlRootElement(name = "Trabajadores")
@XmlAccessorType (XmlAccessType.FIELD)
public class TrabajadoresXml {
    private List<Trabajador> trabajadores = null;
 
    public List<Trabajador> getTrabajadores() {
        return trabajadores;
    }
 
    public void setTrabajadores(List<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    }
}
