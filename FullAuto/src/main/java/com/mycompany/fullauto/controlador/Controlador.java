/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.controlador;

import com.mycompany.fullauto.modelo.ModeloHibernate;
import com.mycompany.fullauto.modelo.ModeloJdbc;
import com.mycompany.fullauto.vista.InsertarTrabajador;
import com.mycompany.fullauto.vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



/**
 *
 * @author novad
 */
public class Controlador implements ActionListener
{
    private ModeloJdbc modeloJdbc;
    private ModeloHibernate modeloHibernate;
    private Vista vista;
    private ArrayList listaTrabajadores;
    private int contador;
    //Connection conn;
    
    public Controlador(Vista vista, ModeloJdbc modelo)
    {
        this.vista=vista;
        this.modeloJdbc=modelo;
        contador=0;
        listaTrabajadores=null;
        
        mostrarDatosBBDD();
        
        vista.jbSiguienteTrabajador.addActionListener(this);
        vista.jbAnteriorTrabajador.addActionListener(this);
        
        vista.jbInsertarTrabajador.addActionListener(this);
    }
    public Controlador(Vista vista, ModeloHibernate modelo)
    {
        this.vista=vista;
        this.modeloHibernate=modelo;
        
      
        vista.jbSiguienteTrabajador.addActionListener(this);
        
    }
    public void mostrarDatosBBDD()
    {

        listaTrabajadores= modeloJdbc.getDatosTrabajador();
        String datosTrabajador[] = new String[5];
        datosTrabajador=(String[])listaTrabajadores.get(contador);
        vista.cargarCampos(datosTrabajador);

    }
    public void mostrarSiguienteTrabajador()
    {
        contador++;
        String datosTrabajador[] = new String[5];
        datosTrabajador = (String[])listaTrabajadores.get(contador);
        vista.cargarCampos(datosTrabajador);
    }
    public void mostrarAnteriorTrabajador()
    {
        contador--;
        String datosTrabajador[] = new String[5];
        datosTrabajador = (String[])listaTrabajadores.get(contador);
        vista.cargarCampos(datosTrabajador);
    }
    public void insertarTrabajador()
    {
        InsertarTrabajador it = new InsertarTrabajador(vista, true);
        it.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("evento Activado");
 
        if(ae.getSource()==vista.jbSiguienteTrabajador)
        {
            System.out.println("jbSiguienteTrabajador pulsado");
            mostrarSiguienteTrabajador();
        }
        if(ae.getSource()==vista.jbAnteriorTrabajador)
        {
            System.out.println("jbAnteriorTrabajador pulsado");
            mostrarAnteriorTrabajador();
        }
        if(ae.getSource()==vista.jbInsertarTrabajador)
        {
            System.out.println("jbInsertarTrabajador pulsado");
           insertarTrabajador();
        }
    }
    
}
