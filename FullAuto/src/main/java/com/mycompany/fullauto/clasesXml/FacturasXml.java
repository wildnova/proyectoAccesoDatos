/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.clasesXml;


import com.mycompany.fullauto.Factura;
import com.mycompany.fullauto.Trabajador;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author novad
 */
@XmlRootElement(name = "Facturas")
@XmlAccessorType (XmlAccessType.FIELD)
public class FacturasXml {
    private List<Factura> facturas = null;
 
    public List<Factura> getFacturas() {
        return facturas;
    }
 
    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
}
