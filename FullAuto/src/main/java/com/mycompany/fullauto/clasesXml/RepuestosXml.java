/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.clasesXml;


import com.mycompany.fullauto.Repuestos;
import com.mycompany.fullauto.Trabajador;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author novad
 */
@XmlRootElement(name = "Repuestos")
@XmlAccessorType (XmlAccessType.FIELD)
public class RepuestosXml {
    private List<Repuestos> repuestos = null;
 
    public List<Repuestos> getRepuestos() {
        return repuestos;
    }
 
    public void setRepuestos(List<Repuestos> repuestos) {
        this.repuestos = repuestos;
    }
}
