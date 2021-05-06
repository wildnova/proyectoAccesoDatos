/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.clasesXml;


import com.mycompany.fullauto.Obd;
import com.mycompany.fullauto.Trabajador;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author novad
 */
@XmlRootElement(name = "Obds")
@XmlAccessorType (XmlAccessType.FIELD)
public class ObdsXml {
    private List<Obd> obds = null;
 
    public List<Obd> getObds() {
        return obds;
    }
 
    public void setObds(List<Obd> obds) {
        this.obds = obds;
    }
}
