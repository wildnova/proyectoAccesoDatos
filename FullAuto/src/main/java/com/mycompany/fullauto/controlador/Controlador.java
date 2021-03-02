/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.controlador;

import com.mycompany.fullauto.modelo.ModeloHibernate;
import com.mycompany.fullauto.modelo.ModeloJdbc;
import com.mycompany.fullauto.vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;


/**
 *
 * @author novad
 */
public class Controlador implements ActionListener
{
    private ModeloJdbc modeloJdbc;
    private ModeloHibernate modeloHibernate;
    private Vista vista;
    private ResultSet rs;
    //Connection conn;
    
    public Controlador(Vista vista, ModeloJdbc modelo)
    {
        this.vista=vista;
        this.modeloJdbc=modelo;
        rs=null;
        
        mostrarDatosBBDD();
        vista.jbSiguienteTrabajador.addActionListener(this);
        vista.jbObtenerDatosTrabajador.addActionListener(this);
    }
    public Controlador(Vista vista, ModeloHibernate modelo)
    {
        this.vista=vista;
        this.modeloHibernate=modelo;
        
      
        vista.jbSiguienteTrabajador.addActionListener(this);
        vista.jbObtenerDatosTrabajador.addActionListener(this);
    }
    public void mostrarDatosBBDD()
    {

        ArrayList listaTrabajadores= modeloJdbc.getDatosTrabajador();
        String datosTrabajador[] = new String[5];
        datosTrabajador=(String[])listaTrabajadores.get(0);
        vista.cargarCampos(datosTrabajador);

    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("evento Activado");
        
        if(ae.getSource()==vista.jbObtenerDatosTrabajador)
        {
            System.out.println("jbObtenerDatos pulsado");
            mostrarDatosBBDD();
           
        }
        
        
        if(ae.getSource()==vista.jbSiguienteTrabajador)
        {
            System.out.println("jbSiguienteTrabajador pulsado");
            
            String[] datosTrabajador= new String[5];
            
            
            
            vista.cargarCampos(datosTrabajador);
        }
    }
    
}
