/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.clasesXml;


import com.mycompany.fullauto.Informe;
import com.mycompany.fullauto.Trabajador;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author novad
 */
@XmlRootElement(name = "Informes")
@XmlAccessorType (XmlAccessType.FIELD)
public class InformesXml {
    private List<Informe> informes = null;
 
    public List<Informe> getInformes() {
        return informes;
    }
 
    public void setInformes(List<Informe> informes) {
        this.informes = informes;
    }
}
