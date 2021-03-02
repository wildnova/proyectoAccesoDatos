/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author novad
 */
public class PruebaConexion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Uso con los archivos de mapeo en XML
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        
        //Uso con anotaciones dentro de código
       /* SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Factura.class)
                .addAnnotatedClass(Informe.class)
                .addAnnotatedClass(Obd.class)
                .addAnnotatedClass(Repuestos.class)
                .addAnnotatedClass(Trabajador.class)
                .addAnnotatedClass(Vehiculo.class)
                .buildSessionFactory();*/
        
        Session sesion = factory.openSession();
        if(sesion!=null)
        {
            System.out.println("Conectado!!!!!!!!!!!!!!!!!!!!");
            
        }
        else
            System.out.println("puta madre");
    }
    
}
