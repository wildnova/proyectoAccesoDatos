/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.vista;

import com.mycompany.fullauto.Exceptions.DAOConexionExcepcion;
import com.mycompany.fullauto.Factura;
import com.mycompany.fullauto.Informe;
import com.mycompany.fullauto.Obd;
import com.mycompany.fullauto.Repuestos;
import com.mycompany.fullauto.Trabajador;
import com.mycompany.fullauto.Vehiculo;
import com.mycompany.fullauto.controlador.Controlador;
import com.mycompany.fullauto.modelo.ModeloJdbc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 *
 * @author novad
 */
public class Vista extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form Vista
     */
    private Controlador controlador;
    private ArrayList<Trabajador> listaTrabajadores;
    private ArrayList<Vehiculo> listaVehiculos;
    private ArrayList<Informe> listaInformes;
    private ArrayList<Factura> listaFacturas;
    private ArrayList<Repuestos> listaRepuestos;
    private ArrayList<Obd> listaObd;
    
    private SelectorConexion selector;
    private int seleccionModelo;
    
    private int contadorTrabajador;
    private int contadorVehiculo;
    private int contadorInforme;
    private int contadorRepuestos;
    private int contadorObd;
    private int contadorFactura;
    
    
    public Vista(){
        initComponents();
        //Ventana inicial de selección de sistema de base de datos.
        selector= new SelectorConexion(this, true);
        selector.setVisible(true);
        seleccionModelo= selector.getSeleccion();
        
        
        //si no selecciona ninguna opción en la ventana inicial o si cierra la ventana, se corta la ejecución del programa.
        if(seleccionModelo==0)
        {
            System.exit(0);
        }
        
        controlador = new Controlador(this,seleccionModelo);
        
        //Listeners de los botones
        jmiExportarXml.addActionListener(this);
        
        jbSiguienteTrabajador.addActionListener(this);
        jbAnteriorTrabajador.addActionListener(this);
        jbInsertarTrabajador.addActionListener(this);
        jbModificarTrabajador.addActionListener(this);
        jbEliminarTrabajador.addActionListener(this);
        
        jbSiguienteVehiculo.addActionListener(this);
        jbAnteriorVehiculo.addActionListener(this);
        jbInsertarVehiculo.addActionListener(this);
        jbModificarVehiculo.addActionListener(this);
        jbEliminarVehiculo.addActionListener(this);
        
        jbSiguienteInforme.addActionListener(this);
        jbAnteriorInforme.addActionListener(this);
        jbInsertarInforme.addActionListener(this);
        jbModificarInforme.addActionListener(this);
        jbEliminarInforme.addActionListener(this);
        
        
        jbSiguienteFactura.addActionListener(this);
        jbAnteriorFactura.addActionListener(this);
        jbInsertarFactura.addActionListener(this);
        jbModificarFactura.addActionListener(this);
        jbEliminarFactura.addActionListener(this);
        
        jbSiguienteRepuesto.addActionListener(this);
        jbAnteriorRepuesto.addActionListener(this);
        jbInsertarRepuesto.addActionListener(this);
        jbModificarRepuesto.addActionListener(this);
        jbEliminarRepuesto.addActionListener(this);
        
        jbSiguienteObd.addActionListener(this);
        jbAnteriorObd.addActionListener(this);
        jbInsertarObd.addActionListener(this);
        jbModificarObd.addActionListener(this);
        jbEliminarObd.addActionListener(this);
        
       
        listaTrabajadores=null;
        listaVehiculos=null;
        listaInformes=null;
        listaFacturas =null;
        listaRepuestos=null;
        listaObd=null;
        
        contadorTrabajador=0;
        contadorVehiculo=0;
        contadorInforme=0;
        contadorRepuestos=0;
        contadorObd=0;
        contadorFactura=0;
        
        //Cargar los datos de la base de datos en los distintos campos
        mostrarDatosTrabajador();
        mostrarDatosVehiculo();
        mostrarDatosInforme();
        mostrarDatosFactura();
        mostrarDatosRepuesto();
        mostrarDatosObd();
        
        jbAnteriorTrabajador.setEnabled(false);
        jbAnteriorVehiculo.setEnabled(false);
        jbAnteriorInforme.setEnabled(false);
        jbAnteriorFactura.setEnabled(false);
        jbAnteriorRepuesto.setEnabled(false);
        jbAnteriorObd.setEnabled(false);
    }
    
    //LLamadas a cada uno de los distintos botones de la interfaz
    public void actionPerformed(ActionEvent ae) {
        System.out.println("evento Activado");
        
        if(ae.getSource()==jmiExportarXml)
        {
            System.out.println("ExportarXml pulsado");
            controlador.exportarXml();
            ConfirmacionExportacionXml confirmacion = new ConfirmacionExportacionXml(this, true);
            confirmacion.setVisible(true);
        }
        if(ae.getSource()==jbSiguienteTrabajador)
        {
            System.out.println("jbSiguienteTrabajador pulsado");
            mostrarSiguienteTrabajador();
        }
        if(ae.getSource()==jbAnteriorTrabajador)
        {
            System.out.println("jbAnteriorTrabajador pulsado");
            mostrarAnteriorTrabajador();
        }
        if(ae.getSource()==jbInsertarTrabajador)
        {
            System.out.println("jbInsertarTrabajador pulsado");
           insertarTrabajador();
        }
        
        if(ae.getSource()==jbModificarTrabajador)
        {
           System.out.println("jbModificarTrabajador pulsado");
           modificarTrabajador();
        }
        
        if(ae.getSource()==jbEliminarTrabajador)
        {
            System.out.println("jbEliminarTrabajador pulsado");
           eliminarTrabajador();
        }
        
        
          if(ae.getSource()==jbSiguienteVehiculo)
        {
            System.out.println("jbSiguienteVehiculo pulsado");
            mostrarSiguienteVehiculo();
        }
        if(ae.getSource()==jbAnteriorVehiculo)
        {
            System.out.println("jbAnteriorVehiculo pulsado");
            mostrarAnteriorVehiculo();
        }
        if(ae.getSource()==jbInsertarVehiculo)
        {
            System.out.println("jbInsertarVehiculo pulsado");
           insertarVehiculo();
        }
        
        if(ae.getSource()==jbModificarVehiculo)
        {
           System.out.println("jbModificarVehiculo pulsado");
           modificarVehiculo();
        }
        
        if(ae.getSource()==jbEliminarVehiculo)
        {
            System.out.println("jbEliminarVehiculo pulsado");
           eliminarVehiculo();
        }
        
        
        if(ae.getSource()==jbSiguienteInforme)
        {
            System.out.println("jbSiguienteInforme pulsado");
            mostrarSiguienteInforme();
        }
        if(ae.getSource()==jbAnteriorInforme)
        {
            System.out.println("jbAnteriorInforme pulsado");
            mostrarAnteriorInforme();
        }
        if(ae.getSource()==jbInsertarInforme)
        {
            System.out.println("jbInsertarInforme pulsado");
           insertarInforme();
        }
        
        if(ae.getSource()==jbModificarInforme)
        {
           System.out.println("jbModificarInforme pulsado");
           modificarInforme();
        }
        
        if(ae.getSource()==jbEliminarInforme)
        {
            System.out.println("jbEliminarInforme pulsado");
           eliminarInforme();
        }
        
          if(ae.getSource()==jbSiguienteFactura)
        {
            System.out.println("jbSiguienteFactura pulsado");
            mostrarSiguienteFactura();
        }
        if(ae.getSource()==jbAnteriorFactura)
        {
            System.out.println("jbAnteriorFactura pulsado");
            mostrarAnteriorFactura();
        }
        if(ae.getSource()==jbInsertarFactura)
        {
            System.out.println("jbInsertarFactura pulsado");
           insertarFactura();
        }
        
        if(ae.getSource()==jbModificarFactura)
        {
           System.out.println("jbModificarFactura pulsado");
           modificarFactura();
        }
        
        if(ae.getSource()==jbEliminarFactura)
        {
            System.out.println("jbEliminarFactura pulsado");
           eliminarFactura();
        }
        
          if(ae.getSource()==jbSiguienteRepuesto)
        {
            System.out.println("jbSiguienteRepuesto pulsado");
            mostrarSiguienteRepuesto();
        }
        if(ae.getSource()==jbAnteriorRepuesto)
        {
            System.out.println("jbAnteriorRepuesto pulsado");
            mostrarAnteriorRepuesto();
        }
        if(ae.getSource()==jbInsertarRepuesto)
        {
            System.out.println("jbInsertarRepuesto pulsado");
           insertarRepuesto();
        }
        
        if(ae.getSource()==jbModificarRepuesto)
        {
           System.out.println("jbModificarRepuesto pulsado");
           modificarRepuesto();
        }
        
        if(ae.getSource()==jbEliminarRepuesto)
        {
            System.out.println("jbEliminarRepuesto pulsado");
           eliminarRepuesto();
        }
        
          if(ae.getSource()==jbSiguienteObd)
        {
            System.out.println("jbSiguienteObd pulsado");
            mostrarSiguienteObd();
        }
        if(ae.getSource()==jbAnteriorObd)
        {
            System.out.println("jbAnteriorObd pulsado");
            mostrarAnteriorObd();
        }
        if(ae.getSource()==jbInsertarObd)
        {
            System.out.println("jbInsertarObd pulsado");
           insertarObd();
        }
        
        if(ae.getSource()==jbModificarObd)
        {
           System.out.println("jbModificarObd pulsado");
           modificarObd();
        }
        
        if(ae.getSource()==jbEliminarObd)
        {
            System.out.println("jbEliminarObd pulsado");
           eliminarObd();
        }
        
    }
    
    //Funciones de TRABAJADOR
    public void mostrarDatosTrabajador()
    {
        listaTrabajadores= controlador.getListadoTrabajadores();
        if(listaTrabajadores.size() == contadorTrabajador)
        {
            contadorTrabajador--;
            jbSiguienteTrabajador.setEnabled(false);
        }
        Trabajador trabajador = listaTrabajadores.get(contadorTrabajador);
        cargarCamposTrabajador(trabajador);
        if(listaTrabajadores.size()==1)
        {
            jbSiguienteTrabajador.setEnabled(false);
            jbAnteriorTrabajador.setEnabled(false);
        }
        if(listaTrabajadores.size()-1 > contadorTrabajador)
        {
            jbSiguienteTrabajador.setEnabled(true);
        }

    }
    public void mostrarSiguienteTrabajador()
    {
        
            jbAnteriorTrabajador.setEnabled(true);
            contadorTrabajador++;
            Trabajador trabajador=listaTrabajadores.get(contadorTrabajador);
            cargarCamposTrabajador(trabajador);
        if(contadorTrabajador==listaTrabajadores.size()-1)
        {
            jbSiguienteTrabajador.setEnabled(false);
        }
        
    }
    public void mostrarAnteriorTrabajador()
    {
            jbSiguienteTrabajador.setEnabled(true);
            contadorTrabajador--;
            Trabajador trabajador=listaTrabajadores.get(contadorTrabajador);
            cargarCamposTrabajador(trabajador);
        if(contadorTrabajador==0)
        {
            jbAnteriorTrabajador.setEnabled(false);
        }
    }
    public void insertarTrabajador()
    {
        boolean confirmarInsertar;
        FormularioTrabajador ft = new FormularioTrabajador(this, true);
        ft.setTextoTitulo("Insertar trabajador");
        ft.setVisible(true);
        confirmarInsertar= ft.getConfirmacion();
        if(confirmarInsertar)
        {
            Trabajador trabajador=new Trabajador(ft.getDni(),ft.getNombre(),ft.getApellido1(),ft.getApellido2(),ft.getFuncion());
            controlador.insertarTrabajador(trabajador);
            
            mostrarDatosTrabajador();
        }
     
    }
    public void modificarTrabajador()
    {
        boolean confirmarModificar;
        
        Trabajador trabajador = listaTrabajadores.get(contadorTrabajador);
        
        FormularioTrabajador ft = new FormularioTrabajador(this, true);
        ft.setTextoTitulo("Modificar trabajador");
        ft.desactivarDni();
        ft.setTextoDni(trabajador.getDni());
        ft.setTextoNombre(trabajador.getNombre());
        ft.setTextoApellido1(trabajador.getApellido1());
        ft.setTextoApellido2(trabajador.getApellido2());
        ft.setTextoFuncion(trabajador.getFuncion());
        
        ft.setVisible(true);
        confirmarModificar=ft.getConfirmacion();
        
        if(confirmarModificar)
        {
            trabajador.setDni(ft.getDni());
            trabajador.setNombre(ft.getNombre());
            trabajador.setApellido1(ft.getApellido1());
            trabajador.setApellido2(ft.getApellido2());
            trabajador.setFuncion(ft.getFuncion());
            controlador.modificarTrabajador(trabajador);
            
            mostrarDatosTrabajador();
        }
        
    }
    
    public void eliminarTrabajador()
    {
        boolean confirmarEliminar;
        
        Trabajador trabajador = listaTrabajadores.get(contadorTrabajador);
        
        EliminarTrabajador et = new EliminarTrabajador(this, true);
        et.setTextoDni(trabajador.getDni());
        et.setTextoNombre(trabajador.getNombre());
        et.setTextoApellido1(trabajador.getApellido1());
        et.setTextoApellido2(trabajador.getApellido2());
        et.setTextoFuncion(trabajador.getFuncion());
        et.setVisible(true);
        
        confirmarEliminar=et.getConfirmacion();
        if(confirmarEliminar)
        {
            controlador.eliminarTrabajador(trabajador);
            mostrarDatosTrabajador();
        }
    }
    
    //Funciones de VEHICULO
    
    public void mostrarDatosVehiculo()
    {
        listaVehiculos= controlador.getListadoVehiculos();
        if(listaVehiculos.size()==contadorVehiculo)
        {
            contadorVehiculo--;
            jbSiguienteVehiculo.setEnabled(false);
        }
        Vehiculo vehiculo = listaVehiculos.get(contadorVehiculo);
        
        String nombreCompleto=controlador.comprobarTrabajadorAsignado(vehiculo);
        cargarCamposVehiculo(vehiculo,nombreCompleto);
        
        if(listaVehiculos.size()==1)
        {
            jbSiguienteVehiculo.setEnabled(false);
            jbAnteriorVehiculo.setEnabled(false);
        }
        if(listaVehiculos.size()-1 > contadorVehiculo)
        {
            jbSiguienteVehiculo.setEnabled(true);
        }
    }
    
    public void mostrarSiguienteVehiculo()
    {
        jbAnteriorVehiculo.setEnabled(true);
        contadorVehiculo++;
        Vehiculo vehiculo=listaVehiculos.get(contadorVehiculo);
        String nombreCompleto=controlador.comprobarTrabajadorAsignado(vehiculo);
        cargarCamposVehiculo(vehiculo,nombreCompleto);
        if(contadorVehiculo==listaVehiculos.size()-1)
        {
            jbSiguienteVehiculo.setEnabled(false);
        }
    }
    public void mostrarAnteriorVehiculo()
    {
        jbSiguienteVehiculo.setEnabled(true);
        contadorVehiculo--;
        Vehiculo vehiculo=listaVehiculos.get(contadorVehiculo);
        String nombreCompleto=controlador.comprobarTrabajadorAsignado(vehiculo);
        cargarCamposVehiculo(vehiculo,nombreCompleto);
        if(contadorVehiculo==0)
        {
            jbAnteriorVehiculo.setEnabled(false);
        }
    }
    public void insertarVehiculo()
    {
        boolean confirmarInsertar;
        FormularioVehiculo fv = new FormularioVehiculo(this, true,controlador);
        fv.setTextoTitulo("Insertar vehículo");
        fv.setVisible(true);
        confirmarInsertar= fv.getConfirmacion();
        if(confirmarInsertar)
        {
            Vehiculo vehiculo = new Vehiculo(fv.getBastidor(),fv.getMatricula(),fv.getMarca(),fv.getModelo(),fv.getMotor(),fv.getPotencia(),fv.getCarburante(),fv.getAceite(),fv.getConsumo(),fv.getFechaEntrada(),fv.getTiempoDedicado(),fv.getDniTrabajadorAsignado());
            controlador.insertarVehiculo(vehiculo);
            
            mostrarDatosVehiculo();
        }
    }
    public void modificarVehiculo()
    {
         boolean confirmarModificar;
     
        Vehiculo vehiculo = listaVehiculos.get(contadorVehiculo);
        
        FormularioVehiculo fv = new FormularioVehiculo(this, true,controlador);
        fv.setTextoTitulo("Modificar vehículo");
        fv.desactivarBastidor();
        fv.setTextoBastidor(vehiculo.getBastidor());
        fv.setTextoMatricula(vehiculo.getMatricula());
        fv.setTextoMarca(vehiculo.getMarca());
        fv.setTextoModelo(vehiculo.getModelo());
        fv.setTextoMotor(vehiculo.getMotor());
        fv.setTextoPotencia(vehiculo.getPotenciaCv());
        fv.setTextoCarburante(vehiculo.getCarburante());
        fv.setTextoAceite(vehiculo.getAceite());
        fv.setTextoConsumo(vehiculo.getConsumo());
        fv.setTextoFechaEntrada(vehiculo.getFechaEntrada());
        fv.setTextoTiempoDedicado(vehiculo.getTiempoDedicado());
        fv.setTextoDniTrabajadorAsignado(vehiculo.getDniTrabajador());
        
        fv.setVisible(true);
        confirmarModificar=fv.getConfirmacion();
        
        if(confirmarModificar)
        {
            vehiculo.setBastidor(fv.getBastidor());
            vehiculo.setMatricula(fv.getMatricula());
            vehiculo.setMarca(fv.getMarca());
            vehiculo.setModelo(fv.getModelo());
            vehiculo.setMotor(fv.getMotor());
            vehiculo.setPotenciaCv(fv.getPotencia());
            vehiculo.setCarburante(fv.getCarburante());
            vehiculo.setAceite(fv.getAceite());
            vehiculo.setConsumo(fv.getConsumo());
            vehiculo.setFechaEntrada(fv.getFechaEntrada());
            vehiculo.setTiempoDedicado(fv.getTiempoDedicado());
            vehiculo.setDniTrabajador(fv.getDniTrabajadorAsignado());
           
           
            controlador.modificarVehiculo(vehiculo);
            
            mostrarDatosVehiculo();
        }
    }
    public void eliminarVehiculo()
    {
        boolean confirmarEliminar;
        
        Vehiculo vehiculo = listaVehiculos.get(contadorVehiculo);
        
        EliminarVehiculo ev = new EliminarVehiculo(this, true);
       
        ev.setTextoBastidor(vehiculo.getBastidor());
        ev.setTextoMatricula(vehiculo.getMatricula());
        ev.setTextoMarca(vehiculo.getMarca());
        ev.setTextoModelo(vehiculo.getModelo());
        ev.setTextoMotor(vehiculo.getMotor());
        ev.setTextoPotencia(vehiculo.getPotenciaCv());
        ev.setTextoCarburante(vehiculo.getCarburante());
        ev.setTextoAceite(vehiculo.getAceite());
        ev.setTextoConsumo(vehiculo.getConsumo());
        ev.setTextoFechaEntrada(vehiculo.getFechaEntrada());
        ev.setTextoTiempoDedicado(vehiculo.getTiempoDedicado());
        ev.setTextoDniTrabajadorAsignado(vehiculo.getDniTrabajador());
        ev.setVisible(true);
        
        confirmarEliminar=ev.getConfirmacion();
        if(confirmarEliminar)
        {
            controlador.eliminarVehiculo(vehiculo);
            mostrarDatosVehiculo();
        }
    }
    
    //Funciones de INFORME
    
    public void mostrarDatosInforme()
    {
        listaInformes= controlador.getListadoInformes();
        if(listaInformes.size()==contadorInforme)
        {
            contadorInforme--;
            jbSiguienteInforme.setEnabled(false);
        }
        Informe informe = listaInformes.get(contadorInforme);
        cargarCamposInforme(informe);
        if(listaInformes.size()==1)
        {
            jbSiguienteInforme.setEnabled(false);
            jbAnteriorInforme.setEnabled(false);
        }
        if(listaInformes.size()-1 > contadorInforme)
        {
            jbSiguienteInforme.setEnabled(true);
        }
    }
    public void mostrarSiguienteInforme()
    {
        jbAnteriorInforme.setEnabled(true);
        contadorInforme++;
        Informe informe=listaInformes.get(contadorInforme);
        cargarCamposInforme(informe);
        if(listaInformes.size()-1==contadorInforme)
        {
            jbSiguienteInforme.setEnabled(false);
        }
    }
    public void mostrarAnteriorInforme()
    {
        jbSiguienteInforme.setEnabled(true);
        contadorInforme--;
        Informe informe=listaInformes.get(contadorInforme);
        cargarCamposInforme(informe);
        if(contadorInforme==0)
        {
            jbAnteriorInforme.setEnabled(false);
        }
    }
    public void insertarInforme()
    {
        boolean confirmarInsertar;
        FormularioInforme fi = new FormularioInforme(this, true,controlador);
        fi.setTextoTitulo("Insertar informe");
        fi.setVisible(true);
        confirmarInsertar= fi.getConfirmacion();
        if(confirmarInsertar)
        {
            Informe informe=new Informe(Integer.parseInt(fi.getNumExpedicion()),fi.getTareas(),fi.getDniTrabajadorInforme());
            controlador.insertarInforme(informe);
            
            mostrarDatosInforme();
        }
    }
    public void modificarInforme()
    {
        boolean confirmarModificar;
        
        Informe informe = listaInformes.get(contadorInforme);
        
        FormularioInforme fi = new FormularioInforme(this, true,controlador);
        fi.setTextoTitulo("Modificar informe");
        fi.desactivarNumExpedicion();
        fi.setTextoNumExpedicion(String.valueOf(informe.getNumExpedicion()));
        fi.setTextoTareas(informe.getTareas());
        fi.setTextoDniTrabajadorInforme(informe.getDniTrabajadorInforme());
        
        
        fi.setVisible(true);
        confirmarModificar=fi.getConfirmacion();
        
        if(confirmarModificar)
        {
            informe.setNumExpedicion(Integer.parseInt(fi.getNumExpedicion()));
            informe.setTareas(fi.getTareas());
            informe.setDniTrabajadorInforme(fi.getDniTrabajadorInforme());
            
            controlador.modificarInforme(informe);
            
            mostrarDatosInforme();
        }
    }
    public void eliminarInforme()
    {
        
        boolean confirmarEliminar;
        
        Informe informe = listaInformes.get(contadorInforme);
        
        EliminarInforme ei = new EliminarInforme(this, true);
       
        ei.setTextoNumExpedicion(String.valueOf(informe.getNumExpedicion()));
        ei.setTextoTareas(informe.getTareas());
        ei.setTextoDniTrabajadorInforme(informe.getDniTrabajadorInforme());
        
        ei.setVisible(true);
        
        confirmarEliminar=ei.getConfirmacion();
        if(confirmarEliminar)
        {
            controlador.eliminarInforme(informe);
            mostrarDatosInforme();
        }
    }
    
    //Funciones de FACTURA
    
    public void mostrarDatosFactura()
    {
        listaFacturas= controlador.getListadoFacturas();
        if(listaFacturas.size()==contadorFactura)
        {
            contadorFactura--;
            jbSiguienteFactura.setEnabled(false);
        }
        
        Factura factura = listaFacturas.get(contadorFactura);
        cargarCamposFactura(factura);
        if(listaFacturas.size()==1)
        {
            jbSiguienteFactura.setEnabled(false);
            jbAnteriorFactura.setEnabled(false);
        }
        if(listaFacturas.size()-1 > contadorFactura)
        {
            jbSiguienteFactura.setEnabled(true);
        }
        
    }
    public void mostrarSiguienteFactura()
    {
        jbAnteriorFactura.setEnabled(true);
        contadorFactura++;
        Factura factura=listaFacturas.get(contadorFactura);
        cargarCamposFactura(factura);
        if(listaFacturas.size()-1==contadorFactura)
        {
            jbSiguienteFactura.setEnabled(false);
        }
        
    }
    public void mostrarAnteriorFactura()
    {
        jbSiguienteFactura.setEnabled(true);
        contadorFactura--;
        Factura factura=listaFacturas.get(contadorFactura);
        cargarCamposFactura(factura);
        if(contadorFactura==0)
        {
            jbAnteriorFactura.setEnabled(false);
        }
    }
    public void insertarFactura()
    {
        boolean confirmarInsertar;
        FormularioFactura ff = new FormularioFactura(this, true,controlador);
        ff.setTextoTitulo("Insertar factura");
        ff.setVisible(true);
        confirmarInsertar= ff.getConfirmacion();
        if(confirmarInsertar)
        {
            Factura factura=new Factura(Integer.parseInt(ff.getNumFactura()),Double.parseDouble(ff.getPrecio()),ff.getLineaFactura(),ff.getNumExpedicionInforme());
            controlador.insertarFactura(factura);
            
            mostrarDatosFactura();
        }
    }
    public void modificarFactura()
    {
        boolean confirmarModificar;
        
        Factura factura = listaFacturas.get(contadorFactura);
        
        FormularioFactura ff = new FormularioFactura(this, true,controlador);
        ff.setTextoTitulo("Modificar factura");
        ff.desactivarNumFactura();
        ff.setTextoNumFactura(String.valueOf(factura.getNumFactura()));
        ff.setTextoPrecio(String.valueOf(factura.getPrecio()));
        ff.setTextoLineaFactura(factura.getLineaFactura());
        ff.setTextoNumExpedicionInforme(factura.getNumExpedicionInforme());
        
        ff.setVisible(true);
        confirmarModificar=ff.getConfirmacion();
        
        if(confirmarModificar)
        {
            factura.setNumFactura(Integer.parseInt(ff.getNumFactura()));
            factura.setPrecio(Double.parseDouble(ff.getPrecio()));
            factura.setLineaFactura(ff.getLineaFactura());
            factura.setNumExpedicionInforme(ff.getNumExpedicionInforme());
            
            controlador.modificarFactura(factura);
            
            mostrarDatosFactura();
        }
    }
    public void eliminarFactura()
    {
        boolean confirmarEliminar;
        
        Factura factura = listaFacturas.get(contadorFactura);
        
        EliminarFactura ef = new EliminarFactura(this, true);
       
        ef.setTextoNumFactura(String.valueOf(factura.getNumFactura()));
        ef.setTextoPrecio(String.valueOf(factura.getPrecio()));
        ef.setTextoLineaFactura(factura.getLineaFactura());
        ef.setTextoNumExpedicionInforme(factura.getNumExpedicionInforme());
        
        ef.setVisible(true);
        
        confirmarEliminar=ef.getConfirmacion();
        if(confirmarEliminar)
        {
            controlador.eliminarFactura(factura);
            mostrarDatosFactura();
        }
    }
    
    //Funciones de REPUESTOS
    
    public void mostrarDatosRepuesto()
    {
        listaRepuestos= controlador.getListadoRepuestos();
        if(listaRepuestos.size()==contadorRepuestos)
        {
            contadorRepuestos--;
            jbSiguienteRepuesto.setEnabled(false);
        }
        
        Repuestos repuesto = listaRepuestos.get(contadorRepuestos);
        cargarCamposRepuestos(repuesto);
        if(listaRepuestos.size()==1)
        {
            jbSiguienteRepuesto.setEnabled(false);
            jbAnteriorRepuesto.setEnabled(false);
        }
        if(listaRepuestos.size()-1 > contadorRepuestos)
        {
            jbSiguienteRepuesto.setEnabled(true);
        }
    }
    public void mostrarSiguienteRepuesto()
    {
        jbAnteriorRepuesto.setEnabled(true);
        contadorRepuestos++;
        Repuestos repuesto=listaRepuestos.get(contadorRepuestos);
        cargarCamposRepuestos(repuesto);
        if(listaRepuestos.size()-1==contadorRepuestos)
        {
            jbSiguienteRepuesto.setEnabled(false);
        }
    
    }
    public void mostrarAnteriorRepuesto()
    {
        jbSiguienteRepuesto.setEnabled(true);
        contadorRepuestos--;
        Repuestos repuesto=listaRepuestos.get(contadorRepuestos);
        cargarCamposRepuestos(repuesto);
        if(contadorRepuestos==0)
        {
            jbAnteriorRepuesto.setEnabled(false);
        }
    }
    public void insertarRepuesto()
    {
        boolean confirmarInsertar;
        FormularioRepuestos fr = new FormularioRepuestos(this, true,controlador);
        fr.setTextoTitulo("Insertar repuesto");
        fr.setVisible(true);
        confirmarInsertar= fr.getConfirmacion();
        if(confirmarInsertar)
        {
            Repuestos repuesto=new Repuestos(fr.getNumSerie(),fr.getReferencia(),fr.getNombre(),fr.getMarca(),fr.getUso(),fr.getTienda(),fr.getFechaCompra(),fr.getBastidorVehiculoRepuestos(),fr.getNumFacturaRepuestos());
            controlador.insertarRepuesto(repuesto);
            
            mostrarDatosRepuesto();
        }
    }
    public void modificarRepuesto()
    {
      boolean confirmarModificar;
        
        Repuestos repuesto = listaRepuestos.get(contadorRepuestos);
        
        FormularioRepuestos fr = new FormularioRepuestos(this, true,controlador);
        fr.setTextoTitulo("Modificar repuesto");
        fr.desactivarNumSerie();
        fr.setTextoNumSerie(repuesto.getNumSerie());
        fr.setTextoReferencia(repuesto.getReferencia());
        fr.setTextoNombre(repuesto.getNombre());
        fr.setTextoMarca(repuesto.getMarca());
        fr.setTextoUso(repuesto.getUso());
        fr.setTextoTienda(repuesto.getTienda());
        fr.setTextoFechaCompra(repuesto.getFechaCompra());
        fr.setTextoBastidorRepuestos(repuesto.getBastidorVehiculoRepuestos());
        fr.setTextoNumFacturaRepuestos(repuesto.getNumFacturaRepuestos());
        
        fr.setVisible(true);
        confirmarModificar=fr.getConfirmacion();
        
        if(confirmarModificar)
        {
            repuesto.setNumSerie(fr.getNumSerie());
            repuesto.setReferencia(fr.getReferencia());
            repuesto.setNombre(fr.getNombre());
            repuesto.setMarca(fr.getMarca());
            repuesto.setUso(fr.getUso());
            repuesto.setTienda(fr.getTienda());
            repuesto.setTienda(fr.getTienda());
            repuesto.setFechaCompra(fr.getFechaCompra());
            repuesto.setBastidorVehiculoRepuestos(fr.getBastidorVehiculoRepuestos());
            repuesto.setNumFacturaRepuestos(fr.getNumFacturaRepuestos());
            controlador.modificarRepuesto(repuesto);
            
            mostrarDatosRepuesto();
        }  
    }
    public void eliminarRepuesto()
    {
        boolean confirmarEliminar;
        
        Repuestos repuesto = listaRepuestos.get(contadorRepuestos);
        
        EliminarRepuestos er = new EliminarRepuestos(this, true);
       
        er.setTextoNumSerie(repuesto.getNumSerie());
        er.setTextoReferencia(repuesto.getReferencia());
        er.setTextoNombre(repuesto.getNombre());
        er.setTextoMarca(repuesto.getMarca());
        er.setTextoUso(repuesto.getUso());
        er.setTextoTienda(repuesto.getTienda());
        er.setTextoFechaCompra(repuesto.getFechaCompra().toString());
        er.setTextoBastidorRepuestos(repuesto.getBastidorVehiculoRepuestos());
        er.setTextoNumFacturaRepuestos(repuesto.getNumFacturaRepuestos());
        er.setVisible(true);
        
        confirmarEliminar=er.getConfirmacion();
        if(confirmarEliminar)
        {
            controlador.eliminarRepuesto(repuesto);
            mostrarDatosRepuesto();
        }
    }
    
    //Funciones de OBD
    
    public void mostrarDatosObd()
    {
        listaObd= controlador.getListadoObd();
        if(listaObd.size()==contadorObd)
        {
            contadorObd--;
            jbSiguienteObd.setEnabled(false);
        }
        Obd obd = listaObd.get(contadorObd);
        cargarCamposObd(obd);
        
        if(listaObd.size()==1)
        {
            jbSiguienteObd.setEnabled(false);
            jbAnteriorObd.setEnabled(false);
        }
        if(listaObd.size()-1 > contadorObd)
        {
            jbSiguienteObd.setEnabled(true);
        }
    }
    public void mostrarSiguienteObd()
    {
        jbAnteriorObd.setEnabled(true);
        contadorObd++;
        Obd obd=listaObd.get(contadorObd);
        cargarCamposObd(obd);
        if(listaObd.size()-1==contadorObd)
        {
            jbSiguienteObd.setEnabled(false);
        }
    }
    public void mostrarAnteriorObd()
    {
        jbSiguienteObd.setEnabled(true);
        contadorObd--;
        Obd obd=listaObd.get(contadorObd);
        cargarCamposObd(obd);
        if(contadorObd==0)
        {
            jbAnteriorObd.setEnabled(false);
        }

    }
    public void insertarObd()
    {
        boolean confirmarInsertar;
        FormularioObd fo = new FormularioObd(this, true,controlador);
        fo.setTextoTitulo("Insertar Obd");
        fo.setVisible(true);
        confirmarInsertar= fo.getConfirmacion();
        if(confirmarInsertar)
        {
            Obd obd=new Obd(Integer.parseInt(fo.getId()),Integer.parseInt(fo.getRpmInst()),Double.parseDouble(fo.getTempAceite()),Double.parseDouble(fo.getTempAgua()),fo.getCodSalida(),fo.getSensores(),fo.getBastidorObd());
            controlador.insertarObd(obd);
            
            mostrarDatosObd();
        }
    }
    public void modificarObd()
    {
        boolean confirmarModificar;
        
        Obd obd = listaObd.get(contadorObd);
        
        FormularioObd fo = new FormularioObd(this, true,controlador);
        fo.setTextoTitulo("Modificar Obd");
        fo.desactivarIdObd();
        fo.setTextoId(String.valueOf(obd.getId()));
        fo.setTextoRpmInst(String.valueOf(obd.getRpm_inst()));
        fo.setTextoTempAceite(String.valueOf(obd.getTemp_aceite()));
        fo.setTextoTempAgua(String.valueOf(obd.getTemp_agua()));
        fo.setTextoCodSalida(obd.getCod_salida());
        fo.setTextoSensores(obd.getSensores());
        fo.setTextoBastidorObd(obd.getBastidorVehiculoObd());
        
        fo.setVisible(true);
        confirmarModificar=fo.getConfirmacion();
        
        if(confirmarModificar)
        {
            obd.setId(Integer.parseInt(fo.getId()));
            obd.setRpm_inst(Integer.parseInt(fo.getRpmInst()));
            obd.setTemp_aceite(Double.parseDouble(fo.getTempAceite()));
            obd.setTemp_agua(Double.parseDouble(fo.getTempAgua()));
            obd.setCod_salida(obd.getCod_salida());
            obd.setSensores(obd.getSensores());
            obd.setBastidorVehiculoObd(obd.getBastidorVehiculoObd());
                   
            controlador.modificarObd(obd);
            
            mostrarDatosObd();
        }
    }
    public void eliminarObd()
    {
        boolean confirmarEliminar;
        
        Obd obd = listaObd.get(contadorObd);
        
        EliminarObd eo = new EliminarObd(this, true);
       
        eo.setTextoId(String.valueOf(obd.getId()));
        eo.setTextoRpmInst(String.valueOf(obd.getRpm_inst()));
        eo.setTextoTempAceite(String.valueOf(obd.getTemp_aceite()));
        eo.setTextoTempAgua(String.valueOf(obd.getTemp_agua()));
        eo.setTextoCodSalida(obd.getCod_salida());
        eo.setTextoSensores(obd.getSensores());
        eo.setTextoBastidorObd(obd.getBastidorVehiculoObd());
        eo.setVisible(true);
        
        confirmarEliminar=eo.getConfirmacion();
        if(confirmarEliminar)
        {
            controlador.eliminarObd(obd);
            mostrarDatosObd();
        }
    }
    
    //Funciones para cargar los campos trabajando con lo que reciba del controlador
    
    public void cargarCamposTrabajador(Trabajador trabajador)
    {
        jtfDniTrabajador.setText(trabajador.getDni());
        jtfNombreTrabajador.setText(trabajador.getNombre());
        jtfApellido1Trabajador.setText(trabajador.getApellido1());
        jtfApellido2Trabajador.setText(trabajador.getApellido2());
        jtfFuncionTrabajador.setText(trabajador.getFuncion());
    }
    public void cargarCamposVehiculo(Vehiculo vehiculo, String nombreCompleto)
    {
        jtfBastidorVehiculo.setText(vehiculo.getBastidor());
        jtfMatriculaVehiculo.setText(vehiculo.getMatricula());
        jtfMarcaVehiculo.setText(vehiculo.getMarca());
        jtfModeloVehiculo.setText(vehiculo.getModelo());
        jtfMotorVehiculo.setText(vehiculo.getMotor());
        jtfPotenciaVehiculo.setText(vehiculo.getPotenciaCv());
        jtfCarburanteVehiculo.setText(vehiculo.getCarburante());
        jtfAceiteVehiculo.setText(vehiculo.getAceite());
        jtfConsumoVehiculo.setText(vehiculo.getConsumo());
        jtfFechaEntradaVehiculo.setText(vehiculo.getFechaEntrada().toString());
        jtfTiempoDedicado.setText(vehiculo.getTiempoDedicado().toString());
        jtfDNITrabajadorVehiculo.setText(vehiculo.getDniTrabajador());
        jtfNombreCompletoTrabajadorVehiculo.setText(nombreCompleto);
        
    }
    public void cargarCamposInforme(Informe informe)
    {
        jtfNumExpedicionInforme.setText(String.valueOf(informe.getNumExpedicion()));
        jtfTareasInforme.setText(informe.getTareas());
        jtfDniTrabajadorInforme.setText(informe.getDniTrabajadorInforme());
    
    }
    public void cargarCamposFactura(Factura factura)
    {
        jtfNumFactura.setText(String.valueOf(factura.getNumFactura()));
        jtfPrecioFactura.setText(String.valueOf(factura.getPrecio()));
        jtfLineaFactura.setText(factura.getLineaFactura());
        jtfnumExpedicionInformeFactura.setText(factura.getNumExpedicionInforme());
    }
    public void cargarCamposRepuestos(Repuestos repuestos)
    {
        jtfNumSerieRepuesto.setText(repuestos.getNumSerie());
        jtfReferenciaRepuesto.setText(repuestos.getReferencia());
        jtfNombreRepuesto.setText(repuestos.getNombre());
        jtfMarcaRepuesto.setText(repuestos.getMarca());
        jtfUsoRepuesto.setText(repuestos.getUso());
        jtfTiendaRepuesto.setText(repuestos.getTienda());
        jtfFechaCompraRepuesto.setText(String.valueOf(repuestos.getFechaCompra()));
        jtfBastidorVehiculoRepuestos.setText(repuestos.getBastidorVehiculoRepuestos());
        jtfNumFacturaRepuestos.setText(repuestos.getNumFacturaRepuestos());
        
                
    }
    public void cargarCamposObd(Obd obd)
    {
        jtfIdObd.setText(String.valueOf(obd.getId()));
        jtfRpmInstObd.setText(String.valueOf(obd.getRpm_inst()));
        jtfTemperaturaAceiteObd.setText(String.valueOf(obd.getTemp_aceite()));
        jtfTemperaturaAguaObd.setText(String.valueOf(obd.getTemp_aceite()));
        jtfCodigoSalidaObd.setText(String.valueOf(obd.getTemp_aceite()));
        jtfSensores.setText(obd.getSensores());
        jtfBastidorVehiculoObd.setText(obd.getBastidorVehiculoObd());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondoMain = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jtpTablaPrincipal = new javax.swing.JTabbedPane();
        pTrabajadores = new javax.swing.JPanel();
        jlDniTrabajador = new javax.swing.JLabel();
        jtfDniTrabajador = new javax.swing.JTextField();
        jlNombreTrabajador = new javax.swing.JLabel();
        jtfNombreTrabajador = new javax.swing.JTextField();
        jlApellido1Trabajador = new javax.swing.JLabel();
        jtfApellido1Trabajador = new javax.swing.JTextField();
        jlApellido2Trabajador = new javax.swing.JLabel();
        jtfApellido2Trabajador = new javax.swing.JTextField();
        jlFuncionTrabajador = new javax.swing.JLabel();
        jtfFuncionTrabajador = new javax.swing.JTextField();
        jbSiguienteTrabajador = new javax.swing.JButton();
        jbAnteriorTrabajador = new javax.swing.JButton();
        jbInsertarTrabajador = new javax.swing.JButton();
        jbModificarTrabajador = new javax.swing.JButton();
        jbEliminarTrabajador = new javax.swing.JButton();
        pVehiculos = new javax.swing.JPanel();
        pTrabajadores1 = new javax.swing.JPanel();
        jlBastidorVehiculo = new javax.swing.JLabel();
        jtfBastidorVehiculo = new javax.swing.JTextField();
        jlMatriculaVehiculo = new javax.swing.JLabel();
        jtfMatriculaVehiculo = new javax.swing.JTextField();
        jlMarcaVehiculo = new javax.swing.JLabel();
        jtfMarcaVehiculo = new javax.swing.JTextField();
        jlModeloVehiculo = new javax.swing.JLabel();
        jtfModeloVehiculo = new javax.swing.JTextField();
        jlMotorVehiculo = new javax.swing.JLabel();
        jtfMotorVehiculo = new javax.swing.JTextField();
        jbSiguienteVehiculo = new javax.swing.JButton();
        jbAnteriorVehiculo = new javax.swing.JButton();
        jbInsertarVehiculo = new javax.swing.JButton();
        jbModificarVehiculo = new javax.swing.JButton();
        jbEliminarVehiculo = new javax.swing.JButton();
        jtfFechaEntradaVehiculo = new javax.swing.JTextField();
        jtfConsumoVehiculo = new javax.swing.JTextField();
        jtfAceiteVehiculo = new javax.swing.JTextField();
        jtfCarburanteVehiculo = new javax.swing.JTextField();
        jtfPotenciaVehiculo = new javax.swing.JTextField();
        jlPotenciaVehiculo = new javax.swing.JLabel();
        jlCarburanteVehiculo = new javax.swing.JLabel();
        jlAceiteVehiculo = new javax.swing.JLabel();
        jlConsumoVehiculo = new javax.swing.JLabel();
        jlFechaEntradaVehiculo = new javax.swing.JLabel();
        jtfTiempoDedicado = new javax.swing.JTextField();
        jlTiempoDedicado = new javax.swing.JLabel();
        jtfDNITrabajadorVehiculo = new javax.swing.JTextField();
        jlTrabajadorAsignado = new javax.swing.JLabel();
        jlDniTrabajadorVehiculo = new javax.swing.JLabel();
        jtfNombreCompletoTrabajadorVehiculo = new javax.swing.JTextField();
        jlNombreCompletoTrabajadorVehiculo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jtfNumExpedicionInforme = new javax.swing.JTextField();
        jlNumExpedicionInforme = new javax.swing.JLabel();
        jtfTareasInforme = new javax.swing.JTextField();
        jlTareasInforme = new javax.swing.JLabel();
        jlDniTrabajadorInforme = new javax.swing.JLabel();
        jtfDniTrabajadorInforme = new javax.swing.JTextField();
        jbSiguienteInforme = new javax.swing.JButton();
        jbAnteriorInforme = new javax.swing.JButton();
        jbInsertarInforme = new javax.swing.JButton();
        jbModificarInforme = new javax.swing.JButton();
        jbEliminarInforme = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jlNumFactura = new javax.swing.JLabel();
        jtfNumFactura = new javax.swing.JTextField();
        jtfPrecioFactura = new javax.swing.JTextField();
        jlPrecioFactura = new javax.swing.JLabel();
        jlLineaFactura = new javax.swing.JLabel();
        jtfLineaFactura = new javax.swing.JTextField();
        jbSiguienteFactura = new javax.swing.JButton();
        jbAnteriorFactura = new javax.swing.JButton();
        jbInsertarFactura = new javax.swing.JButton();
        jbModificarFactura = new javax.swing.JButton();
        jbEliminarFactura = new javax.swing.JButton();
        jtfnumExpedicionInformeFactura = new javax.swing.JTextField();
        jlnumExpedicionInformeFactura = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pTrabajadores2 = new javax.swing.JPanel();
        jlNumSerieRepuesto = new javax.swing.JLabel();
        jtfNumSerieRepuesto = new javax.swing.JTextField();
        jlReferenciaRepuesto = new javax.swing.JLabel();
        jtfReferenciaRepuesto = new javax.swing.JTextField();
        jlNombreRepuesto = new javax.swing.JLabel();
        jtfNombreRepuesto = new javax.swing.JTextField();
        jlMarcaRepuesto = new javax.swing.JLabel();
        jtfMarcaRepuesto = new javax.swing.JTextField();
        jlUsoRepuesto = new javax.swing.JLabel();
        jtfUsoRepuesto = new javax.swing.JTextField();
        jbSiguienteRepuesto = new javax.swing.JButton();
        jbAnteriorRepuesto = new javax.swing.JButton();
        jbInsertarRepuesto = new javax.swing.JButton();
        jbModificarRepuesto = new javax.swing.JButton();
        jbEliminarRepuesto = new javax.swing.JButton();
        jtfNumFacturaRepuestos = new javax.swing.JTextField();
        jtfBastidorVehiculoRepuestos = new javax.swing.JTextField();
        jtfFechaCompraRepuesto = new javax.swing.JTextField();
        jtfTiendaRepuesto = new javax.swing.JTextField();
        jlTiendaRepuesto = new javax.swing.JLabel();
        jlFechaCompraRepuesto = new javax.swing.JLabel();
        jlBastidorVehiculoRepuestos = new javax.swing.JLabel();
        jlNumeroFacturaRepuestos = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pTrabajadores3 = new javax.swing.JPanel();
        jlIdObd = new javax.swing.JLabel();
        jtfIdObd = new javax.swing.JTextField();
        jlRpmInstObd = new javax.swing.JLabel();
        jtfRpmInstObd = new javax.swing.JTextField();
        jbSiguienteObd = new javax.swing.JButton();
        jbAnteriorObd = new javax.swing.JButton();
        jbInsertarObd = new javax.swing.JButton();
        jbModificarObd = new javax.swing.JButton();
        jbEliminarObd = new javax.swing.JButton();
        jtfBastidorVehiculoObd = new javax.swing.JTextField();
        jtfCodigoSalidaObd = new javax.swing.JTextField();
        jtfTemperaturaAguaObd = new javax.swing.JTextField();
        jtfTemperaturaAceiteObd = new javax.swing.JTextField();
        jlTemperaturaAceiteObd = new javax.swing.JLabel();
        jlTemperaturaAguaObd = new javax.swing.JLabel();
        jlCodigoSalidaObd = new javax.swing.JLabel();
        jlBastidorVehiculoObd = new javax.swing.JLabel();
        jlSensores = new javax.swing.JLabel();
        jtfSensores = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jmbBarraHerramientas = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jmiExportarXml = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        fondoMain.setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setBackground(new java.awt.Color(0, 153, 255));
        lblTitle.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        lblTitle.setText("FullAuto");
        lblTitle.setToolTipText("");

        jtpTablaPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jtpTablaPrincipal.setForeground(new java.awt.Color(0, 102, 204));

        pTrabajadores.setBackground(new java.awt.Color(255, 255, 255));

        jlDniTrabajador.setText("DNI");

        jtfDniTrabajador.setEditable(false);

        jlNombreTrabajador.setText("Nombre");

        jtfNombreTrabajador.setEditable(false);

        jlApellido1Trabajador.setText("Apellido 1");

        jtfApellido1Trabajador.setEditable(false);

        jlApellido2Trabajador.setText("Apellido 2");

        jtfApellido2Trabajador.setEditable(false);

        jlFuncionTrabajador.setText("Función");

        jtfFuncionTrabajador.setEditable(false);

        jbSiguienteTrabajador.setText("Siguiente");
        jbSiguienteTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSiguienteTrabajadorActionPerformed(evt);
            }
        });

        jbAnteriorTrabajador.setText("Anterior");

        jbInsertarTrabajador.setText("Insertar nuevo trabajador");

        jbModificarTrabajador.setText("Modificar trabajador");

        jbEliminarTrabajador.setText("Eliminar trabajador");

        javax.swing.GroupLayout pTrabajadoresLayout = new javax.swing.GroupLayout(pTrabajadores);
        pTrabajadores.setLayout(pTrabajadoresLayout);
        pTrabajadoresLayout.setHorizontalGroup(
            pTrabajadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrabajadoresLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pTrabajadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfDniTrabajador)
                    .addComponent(jtfNombreTrabajador)
                    .addComponent(jtfApellido1Trabajador)
                    .addComponent(jtfApellido2Trabajador)
                    .addComponent(jtfFuncionTrabajador)
                    .addComponent(jlDniTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlNombreTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlApellido1Trabajador, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .addComponent(jlApellido2Trabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlFuncionTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(63, 63, 63)
                .addGroup(pTrabajadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbSiguienteTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbAnteriorTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(98, 98, 98)
                .addGroup(pTrabajadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbEliminarTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jbInsertarTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jbModificarTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(555, Short.MAX_VALUE))
        );
        pTrabajadoresLayout.setVerticalGroup(
            pTrabajadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrabajadoresLayout.createSequentialGroup()
                .addGroup(pTrabajadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pTrabajadoresLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jbInsertarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jbModificarTrabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pTrabajadoresLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(pTrabajadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pTrabajadoresLayout.createSequentialGroup()
                                .addComponent(jlDniTrabajador)
                                .addGap(10, 10, 10)
                                .addComponent(jtfDniTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlNombreTrabajador)
                                .addGap(19, 19, 19)
                                .addComponent(jtfNombreTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlApellido1Trabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jbSiguienteTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(pTrabajadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadoresLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfApellido1Trabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEliminarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pTrabajadoresLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pTrabajadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlApellido2Trabajador)
                            .addComponent(jbAnteriorTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jtfApellido2Trabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addComponent(jlFuncionTrabajador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtfFuncionTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(288, 288, 288))
        );

        jtpTablaPrincipal.addTab("Trabajadores", pTrabajadores);

        pTrabajadores1.setBackground(new java.awt.Color(255, 255, 255));

        jlBastidorVehiculo.setText("Bastidor");

        jtfBastidorVehiculo.setEditable(false);

        jlMatriculaVehiculo.setText("Matrícula");

        jtfMatriculaVehiculo.setEditable(false);

        jlMarcaVehiculo.setText("Marca");

        jtfMarcaVehiculo.setEditable(false);

        jlModeloVehiculo.setText("Modelo");

        jtfModeloVehiculo.setEditable(false);
        jtfModeloVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfModeloVehiculoActionPerformed(evt);
            }
        });

        jlMotorVehiculo.setText("Motor");

        jtfMotorVehiculo.setEditable(false);

        jbSiguienteVehiculo.setText("Siguiente");
        jbSiguienteVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSiguienteVehiculoActionPerformed(evt);
            }
        });

        jbAnteriorVehiculo.setText("Anterior");
        jbAnteriorVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnteriorVehiculoActionPerformed(evt);
            }
        });

        jbInsertarVehiculo.setText("Insertar nuevo vehículo");

        jbModificarVehiculo.setText("Modificar vehículo");

        jbEliminarVehiculo.setText("Eliminar vehículo");

        jtfFechaEntradaVehiculo.setEditable(false);

        jtfConsumoVehiculo.setEditable(false);
        jtfConsumoVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfConsumoVehiculoActionPerformed(evt);
            }
        });

        jtfAceiteVehiculo.setEditable(false);

        jtfCarburanteVehiculo.setEditable(false);

        jtfPotenciaVehiculo.setEditable(false);

        jlPotenciaVehiculo.setText("Potencia");

        jlCarburanteVehiculo.setText("Carburante");

        jlAceiteVehiculo.setText("Aceite");

        jlConsumoVehiculo.setText("Consumo");

        jlFechaEntradaVehiculo.setText("Fecha entrada");

        jtfTiempoDedicado.setEditable(false);

        jlTiempoDedicado.setText("Tiempo dedicado");

        jtfDNITrabajadorVehiculo.setEditable(false);

        jlTrabajadorAsignado.setText("Trabajador asignado:");

        jlDniTrabajadorVehiculo.setText("DNI ");

        jtfNombreCompletoTrabajadorVehiculo.setEditable(false);

        jlNombreCompletoTrabajadorVehiculo.setText("Nombre completo");

        javax.swing.GroupLayout pTrabajadores1Layout = new javax.swing.GroupLayout(pTrabajadores1);
        pTrabajadores1.setLayout(pTrabajadores1Layout);
        pTrabajadores1Layout.setHorizontalGroup(
            pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                        .addComponent(jlTiempoDedicado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfTiempoDedicado, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlBastidorVehiculo)
                                    .addComponent(jlMarcaVehiculo)
                                    .addComponent(jlConsumoVehiculo)
                                    .addComponent(jlMatriculaVehiculo))
                                .addGap(61, 61, 61)
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfMatriculaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfMarcaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfBastidorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jlMotorVehiculo)
                            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlCarburanteVehiculo)
                                            .addComponent(jlAceiteVehiculo)
                                            .addComponent(jlPotenciaVehiculo)
                                            .addComponent(jlModeloVehiculo))
                                        .addGap(17, 17, 17))
                                    .addComponent(jlFechaEntradaVehiculo))
                                .addGap(34, 34, 34)
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfConsumoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfFechaEntradaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfAceiteVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfCarburanteVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfPotenciaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfMotorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfModeloVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(108, 108, 108)
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbAnteriorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbSiguienteVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbEliminarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbModificarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbInsertarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(362, 362, 362))))
            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jlTrabajadorAsignado)
                .addGap(58, 58, 58)
                .addComponent(jlDniTrabajadorVehiculo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfDNITrabajadorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jlNombreCompletoTrabajadorVehiculo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfNombreCompletoTrabajadorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pTrabajadores1Layout.setVerticalGroup(
            pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfBastidorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlBastidorVehiculo))
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jbSiguienteVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jbAnteriorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbModificarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jtfMatriculaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jbInsertarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jlMatriculaVehiculo)))
                                .addGap(39, 39, 39)
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtfMarcaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlMarcaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfModeloVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlModeloVehiculo))
                        .addGap(23, 23, 23)
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlMotorVehiculo)
                            .addComponent(jtfMotorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jbEliminarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlPotenciaVehiculo)
                    .addComponent(jtfPotenciaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlCarburanteVehiculo)
                    .addComponent(jtfCarburanteVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlAceiteVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfAceiteVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfConsumoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlConsumoVehiculo))
                .addGap(18, 18, 18)
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfFechaEntradaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlFechaEntradaVehiculo))
                .addGap(18, 18, 18)
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfTiempoDedicado, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTiempoDedicado))
                .addGap(18, 18, 18)
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfNombreCompletoTrabajadorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlNombreCompletoTrabajadorVehiculo))
                    .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfDNITrabajadorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlTrabajadorAsignado)
                        .addComponent(jlDniTrabajadorVehiculo)))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pVehiculosLayout = new javax.swing.GroupLayout(pVehiculos);
        pVehiculos.setLayout(pVehiculosLayout);
        pVehiculosLayout.setHorizontalGroup(
            pVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pTrabajadores1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pVehiculosLayout.setVerticalGroup(
            pVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pTrabajadores1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jtpTablaPrincipal.addTab("Vehículos", pVehiculos);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jtfNumExpedicionInforme.setEditable(false);

        jlNumExpedicionInforme.setText("Número de expedición");

        jtfTareasInforme.setEditable(false);

        jlTareasInforme.setText("Tareas");

        jlDniTrabajadorInforme.setText("Dni trabajador");

        jtfDniTrabajadorInforme.setEditable(false);

        jbSiguienteInforme.setText("Siguiente");
        jbSiguienteInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSiguienteInformeActionPerformed(evt);
            }
        });

        jbAnteriorInforme.setText("Anterior");

        jbInsertarInforme.setText("Insertar nuevo informe");

        jbModificarInforme.setText("Modificar informe");

        jbEliminarInforme.setText("Eliminar informe");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfNumExpedicionInforme)
                    .addComponent(jtfTareasInforme)
                    .addComponent(jtfDniTrabajadorInforme)
                    .addComponent(jlNumExpedicionInforme, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .addComponent(jlTareasInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlDniTrabajadorInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbSiguienteInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbAnteriorInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbEliminarInforme, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jbInsertarInforme, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jbModificarInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(555, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jbInsertarInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jbModificarInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jlNumExpedicionInforme)
                                .addGap(10, 10, 10)
                                .addComponent(jtfNumExpedicionInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlTareasInforme)
                                .addGap(19, 19, 19)
                                .addComponent(jtfTareasInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addComponent(jbSiguienteInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jbEliminarInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jlDniTrabajadorInforme)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jbAnteriorInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jtfDniTrabajadorInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(399, Short.MAX_VALUE))
        );

        jtpTablaPrincipal.addTab("Informes", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jlNumFactura.setText("Número de factura");

        jtfNumFactura.setEditable(false);

        jtfPrecioFactura.setEditable(false);

        jlPrecioFactura.setText("Precio");

        jlLineaFactura.setText("Línea Factura");

        jtfLineaFactura.setEditable(false);

        jbSiguienteFactura.setText("Siguiente");
        jbSiguienteFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSiguienteFacturaActionPerformed(evt);
            }
        });

        jbAnteriorFactura.setText("Anterior");

        jbInsertarFactura.setText("Insertar nueva factura");

        jbModificarFactura.setText("Modificar factura");

        jbEliminarFactura.setText("Eliminar factura");

        jtfnumExpedicionInformeFactura.setEditable(false);

        jlnumExpedicionInformeFactura.setText("Informe origen");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlnumExpedicionInformeFactura)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtfnumExpedicionInformeFactura, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfNumFactura, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfPrecioFactura, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfLineaFactura, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlNumFactura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                            .addComponent(jlPrecioFactura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlLineaFactura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbSiguienteFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbAnteriorFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(98, 98, 98)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbEliminarFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jbInsertarFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jbModificarFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(555, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jbInsertarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jbModificarFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jlNumFactura)
                                .addGap(10, 10, 10)
                                .addComponent(jtfNumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlPrecioFactura)
                                .addGap(19, 19, 19)
                                .addComponent(jtfPrecioFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addComponent(jbSiguienteFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jbEliminarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jlLineaFactura)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jbAnteriorFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jtfLineaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlnumExpedicionInformeFactura)))
                .addGap(18, 18, 18)
                .addComponent(jtfnumExpedicionInformeFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(346, Short.MAX_VALUE))
        );

        jtpTablaPrincipal.addTab("Facturas", jPanel2);

        pTrabajadores2.setBackground(new java.awt.Color(255, 255, 255));

        jlNumSerieRepuesto.setText("número de serie");

        jtfNumSerieRepuesto.setEditable(false);

        jlReferenciaRepuesto.setText("referencia");

        jtfReferenciaRepuesto.setEditable(false);

        jlNombreRepuesto.setText("nombre");

        jtfNombreRepuesto.setEditable(false);

        jlMarcaRepuesto.setText("marca");

        jtfMarcaRepuesto.setEditable(false);
        jtfMarcaRepuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfMarcaRepuestoActionPerformed(evt);
            }
        });

        jlUsoRepuesto.setText("uso");

        jtfUsoRepuesto.setEditable(false);

        jbSiguienteRepuesto.setText("Siguiente");
        jbSiguienteRepuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSiguienteRepuestoActionPerformed(evt);
            }
        });

        jbAnteriorRepuesto.setText("Anterior");

        jbInsertarRepuesto.setText("Insertar nuevo repuesto");
        jbInsertarRepuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInsertarRepuestoActionPerformed(evt);
            }
        });

        jbModificarRepuesto.setText("Modificar repuesto");

        jbEliminarRepuesto.setText("Eliminar repuesto");

        jtfNumFacturaRepuestos.setEditable(false);
        jtfNumFacturaRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNumFacturaRepuestosActionPerformed(evt);
            }
        });

        jtfBastidorVehiculoRepuestos.setEditable(false);

        jtfFechaCompraRepuesto.setEditable(false);

        jtfTiendaRepuesto.setEditable(false);

        jlTiendaRepuesto.setText("tienda");

        jlFechaCompraRepuesto.setText("fecha de compra");

        jlBastidorVehiculoRepuestos.setText("bastidor vehículo destino");

        jlNumeroFacturaRepuestos.setText("factura a que pertenece");

        javax.swing.GroupLayout pTrabajadores2Layout = new javax.swing.GroupLayout(pTrabajadores2);
        pTrabajadores2.setLayout(pTrabajadores2Layout);
        pTrabajadores2Layout.setHorizontalGroup(
            pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrabajadores2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlUsoRepuesto)
                    .addComponent(jlReferenciaRepuesto)
                    .addGroup(pTrabajadores2Layout.createSequentialGroup()
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTrabajadores2Layout.createSequentialGroup()
                                .addComponent(jlTiendaRepuesto)
                                .addGap(90, 90, 90))
                            .addGroup(pTrabajadores2Layout.createSequentialGroup()
                                .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlNumSerieRepuesto)
                                    .addComponent(jlNombreRepuesto)
                                    .addComponent(jlMarcaRepuesto))
                                .addGap(38, 38, 38)))
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfMarcaRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfReferenciaRepuesto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfNombreRepuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                                    .addComponent(jtfUsoRepuesto)
                                    .addComponent(jtfTiendaRepuesto))
                                .addComponent(jtfNumSerieRepuesto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfFechaCompraRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbSiguienteRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbAnteriorRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(161, 161, 161)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbModificarRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbEliminarRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbInsertarRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(213, 213, 213))
            .addGroup(pTrabajadores2Layout.createSequentialGroup()
                .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadores2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfBastidorVehiculoRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlNumeroFacturaRepuestos)
                            .addComponent(jtfNumFacturaRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pTrabajadores2Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jlBastidorVehiculoRepuestos))))
                    .addComponent(jlFechaCompraRepuesto))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pTrabajadores2Layout.setVerticalGroup(
            pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrabajadores2Layout.createSequentialGroup()
                .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadores2Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jtfReferenciaRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfNombreRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlNombreRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfMarcaRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlMarcaRepuesto))
                        .addGap(46, 46, 46)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlUsoRepuesto)
                            .addComponent(jtfUsoRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlTiendaRepuesto)
                            .addComponent(jtfTiendaRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlFechaCompraRepuesto)
                            .addComponent(jtfFechaCompraRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pTrabajadores2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pTrabajadores2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pTrabajadores2Layout.createSequentialGroup()
                                        .addGap(110, 110, 110)
                                        .addComponent(jbModificarRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(73, 73, 73)
                                        .addComponent(jbEliminarRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jbInsertarRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pTrabajadores2Layout.createSequentialGroup()
                                .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtfNumSerieRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlNumSerieRepuesto))
                                .addGap(9, 9, 9)
                                .addComponent(jlReferenciaRepuesto)
                                .addGap(26, 26, 26)
                                .addComponent(jbSiguienteRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jbAnteriorRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jlBastidorVehiculoRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jtfBastidorVehiculoRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlNumeroFacturaRepuestos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jtfNumFacturaRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pTrabajadores2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pTrabajadores2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jtpTablaPrincipal.addTab("Repuestos", jPanel3);

        pTrabajadores3.setBackground(new java.awt.Color(255, 255, 255));

        jlIdObd.setText("identificador");

        jtfIdObd.setEditable(false);

        jlRpmInstObd.setText("rpm_inst");

        jtfRpmInstObd.setEditable(false);

        jbSiguienteObd.setText("Siguiente");
        jbSiguienteObd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSiguienteObdActionPerformed(evt);
            }
        });

        jbAnteriorObd.setText("Anterior");

        jbInsertarObd.setText("Insertar nuevo registro OBD");
        jbInsertarObd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInsertarObdActionPerformed(evt);
            }
        });

        jbModificarObd.setText("Modificar registro OBD");

        jbEliminarObd.setText("Eliminar registro OBD");

        jtfBastidorVehiculoObd.setEditable(false);
        jtfBastidorVehiculoObd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfBastidorVehiculoObdActionPerformed(evt);
            }
        });

        jtfCodigoSalidaObd.setEditable(false);

        jtfTemperaturaAguaObd.setEditable(false);

        jtfTemperaturaAceiteObd.setEditable(false);

        jlTemperaturaAceiteObd.setText("temperatura aceite");

        jlTemperaturaAguaObd.setText("temperatura agua");

        jlCodigoSalidaObd.setText("código de salida");

        jlBastidorVehiculoObd.setText("bastidor del vehículo de esta lectura ");

        jlSensores.setText("sensores");

        jtfSensores.setEditable(false);

        javax.swing.GroupLayout pTrabajadores3Layout = new javax.swing.GroupLayout(pTrabajadores3);
        pTrabajadores3.setLayout(pTrabajadores3Layout);
        pTrabajadores3Layout.setHorizontalGroup(
            pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrabajadores3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlRpmInstObd)
                    .addGroup(pTrabajadores3Layout.createSequentialGroup()
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pTrabajadores3Layout.createSequentialGroup()
                                .addComponent(jlIdObd)
                                .addGap(128, 128, 128)
                                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfRpmInstObd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfIdObd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pTrabajadores3Layout.createSequentialGroup()
                                    .addComponent(jlTemperaturaAceiteObd)
                                    .addGap(90, 90, 90)
                                    .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jtfTemperaturaAceiteObd, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtfTemperaturaAguaObd, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtfCodigoSalidaObd, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jlTemperaturaAguaObd)
                                .addComponent(jlCodigoSalidaObd)))
                        .addGap(106, 106, 106)
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbSiguienteObd, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbAnteriorObd, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pTrabajadores3Layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jtfSensores, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlSensores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTrabajadores3Layout.createSequentialGroup()
                        .addComponent(jbInsertarObd, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(237, 237, 237))
                    .addGroup(pTrabajadores3Layout.createSequentialGroup()
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbModificarObd, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbEliminarObd, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(pTrabajadores3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfBastidorVehiculoObd, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlBastidorVehiculoObd))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pTrabajadores3Layout.setVerticalGroup(
            pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrabajadores3Layout.createSequentialGroup()
                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadores3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfIdObd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlIdObd)
                            .addComponent(jbInsertarObd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jlRpmInstObd)
                        .addGap(29, 29, 29)
                        .addComponent(jbSiguienteObd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jbAnteriorObd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jbEliminarObd, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pTrabajadores3Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jtfRpmInstObd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pTrabajadores3Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jbModificarObd, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pTrabajadores3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlTemperaturaAceiteObd)
                                    .addComponent(jtfTemperaturaAceiteObd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlTemperaturaAguaObd)
                                    .addComponent(jtfTemperaturaAguaObd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlCodigoSalidaObd, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfCodigoSalidaObd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlSensores, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfSensores, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jlBastidorVehiculoObd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfBastidorVehiculoObd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(252, 252, 252))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pTrabajadores3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pTrabajadores3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jtpTablaPrincipal.addTab("OBD", jPanel4);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\novad\\Documents\\sincEstudios\\estudios\\DAM2\\AD\\proyecto\\FullAuto\\FullAutoLogoMini.png")); // NOI18N

        javax.swing.GroupLayout fondoMainLayout = new javax.swing.GroupLayout(fondoMain);
        fondoMain.setLayout(fondoMainLayout);
        fondoMainLayout.setHorizontalGroup(
            fondoMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondoMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtpTablaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 1352, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(fondoMainLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2)
                        .addGap(107, 107, 107)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fondoMainLayout.setVerticalGroup(
            fondoMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoMainLayout.createSequentialGroup()
                .addGroup(fondoMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fondoMainLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(lblTitle))
                    .addGroup(fondoMainLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtpTablaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        jMenu2.setText("Archivo");

        jmiExportarXml.setText("Exportar a XML");
        jMenu2.add(jmiExportarXml);

        jmbBarraHerramientas.add(jMenu2);

        setJMenuBar(jmbBarraHerramientas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoMain, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSiguienteTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteTrabajadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSiguienteTrabajadorActionPerformed

    private void jbSiguienteInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteInformeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSiguienteInformeActionPerformed

    private void jbSiguienteFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSiguienteFacturaActionPerformed

    private void jtfMarcaRepuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfMarcaRepuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfMarcaRepuestoActionPerformed

    private void jbSiguienteRepuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteRepuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSiguienteRepuestoActionPerformed

    private void jtfNumFacturaRepuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNumFacturaRepuestosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNumFacturaRepuestosActionPerformed

    private void jbInsertarRepuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInsertarRepuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbInsertarRepuestoActionPerformed

    private void jbSiguienteObdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteObdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSiguienteObdActionPerformed

    private void jbInsertarObdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInsertarObdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbInsertarObdActionPerformed

    private void jtfBastidorVehiculoObdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfBastidorVehiculoObdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfBastidorVehiculoObdActionPerformed

    private void jtfConsumoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfConsumoVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfConsumoVehiculoActionPerformed

    private void jbSiguienteVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSiguienteVehiculoActionPerformed

    private void jtfModeloVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfModeloVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfModeloVehiculoActionPerformed

    private void jbAnteriorVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnteriorVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbAnteriorVehiculoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel fondoMain;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JButton jbAnteriorFactura;
    public javax.swing.JButton jbAnteriorInforme;
    public javax.swing.JButton jbAnteriorObd;
    public javax.swing.JButton jbAnteriorRepuesto;
    public javax.swing.JButton jbAnteriorTrabajador;
    public javax.swing.JButton jbAnteriorVehiculo;
    public javax.swing.JButton jbEliminarFactura;
    public javax.swing.JButton jbEliminarInforme;
    public javax.swing.JButton jbEliminarObd;
    public javax.swing.JButton jbEliminarRepuesto;
    public javax.swing.JButton jbEliminarTrabajador;
    public javax.swing.JButton jbEliminarVehiculo;
    public javax.swing.JButton jbInsertarFactura;
    public javax.swing.JButton jbInsertarInforme;
    public javax.swing.JButton jbInsertarObd;
    public javax.swing.JButton jbInsertarRepuesto;
    public javax.swing.JButton jbInsertarTrabajador;
    public javax.swing.JButton jbInsertarVehiculo;
    public javax.swing.JButton jbModificarFactura;
    public javax.swing.JButton jbModificarInforme;
    public javax.swing.JButton jbModificarObd;
    public javax.swing.JButton jbModificarRepuesto;
    public javax.swing.JButton jbModificarTrabajador;
    public javax.swing.JButton jbModificarVehiculo;
    public javax.swing.JButton jbSiguienteFactura;
    public javax.swing.JButton jbSiguienteInforme;
    public javax.swing.JButton jbSiguienteObd;
    public javax.swing.JButton jbSiguienteRepuesto;
    public javax.swing.JButton jbSiguienteTrabajador;
    public javax.swing.JButton jbSiguienteVehiculo;
    public javax.swing.JLabel jlAceiteVehiculo;
    public javax.swing.JLabel jlApellido1Trabajador;
    public javax.swing.JLabel jlApellido2Trabajador;
    public javax.swing.JLabel jlBastidorVehiculo;
    public javax.swing.JLabel jlBastidorVehiculoObd;
    public javax.swing.JLabel jlBastidorVehiculoRepuestos;
    public javax.swing.JLabel jlCarburanteVehiculo;
    public javax.swing.JLabel jlCodigoSalidaObd;
    public javax.swing.JLabel jlConsumoVehiculo;
    public javax.swing.JLabel jlDniTrabajador;
    public javax.swing.JLabel jlDniTrabajadorInforme;
    public javax.swing.JLabel jlDniTrabajadorVehiculo;
    public javax.swing.JLabel jlFechaCompraRepuesto;
    public javax.swing.JLabel jlFechaEntradaVehiculo;
    public javax.swing.JLabel jlFuncionTrabajador;
    public javax.swing.JLabel jlIdObd;
    public javax.swing.JLabel jlLineaFactura;
    public javax.swing.JLabel jlMarcaRepuesto;
    public javax.swing.JLabel jlMarcaVehiculo;
    public javax.swing.JLabel jlMatriculaVehiculo;
    public javax.swing.JLabel jlModeloVehiculo;
    public javax.swing.JLabel jlMotorVehiculo;
    public javax.swing.JLabel jlNombreCompletoTrabajadorVehiculo;
    public javax.swing.JLabel jlNombreRepuesto;
    public javax.swing.JLabel jlNombreTrabajador;
    public javax.swing.JLabel jlNumExpedicionInforme;
    public javax.swing.JLabel jlNumFactura;
    public javax.swing.JLabel jlNumSerieRepuesto;
    public javax.swing.JLabel jlNumeroFacturaRepuestos;
    public javax.swing.JLabel jlPotenciaVehiculo;
    public javax.swing.JLabel jlPrecioFactura;
    public javax.swing.JLabel jlReferenciaRepuesto;
    public javax.swing.JLabel jlRpmInstObd;
    public javax.swing.JLabel jlSensores;
    public javax.swing.JLabel jlTareasInforme;
    public javax.swing.JLabel jlTemperaturaAceiteObd;
    public javax.swing.JLabel jlTemperaturaAguaObd;
    public javax.swing.JLabel jlTiempoDedicado;
    public javax.swing.JLabel jlTiendaRepuesto;
    public javax.swing.JLabel jlTrabajadorAsignado;
    public javax.swing.JLabel jlUsoRepuesto;
    public javax.swing.JLabel jlnumExpedicionInformeFactura;
    private javax.swing.JMenuBar jmbBarraHerramientas;
    private javax.swing.JMenuItem jmiExportarXml;
    public javax.swing.JTextField jtfAceiteVehiculo;
    public javax.swing.JTextField jtfApellido1Trabajador;
    public javax.swing.JTextField jtfApellido2Trabajador;
    public javax.swing.JTextField jtfBastidorVehiculo;
    public javax.swing.JTextField jtfBastidorVehiculoObd;
    public javax.swing.JTextField jtfBastidorVehiculoRepuestos;
    public javax.swing.JTextField jtfCarburanteVehiculo;
    public javax.swing.JTextField jtfCodigoSalidaObd;
    public javax.swing.JTextField jtfConsumoVehiculo;
    public javax.swing.JTextField jtfDNITrabajadorVehiculo;
    public javax.swing.JTextField jtfDniTrabajador;
    public javax.swing.JTextField jtfDniTrabajadorInforme;
    public javax.swing.JTextField jtfFechaCompraRepuesto;
    public javax.swing.JTextField jtfFechaEntradaVehiculo;
    public javax.swing.JTextField jtfFuncionTrabajador;
    public javax.swing.JTextField jtfIdObd;
    public javax.swing.JTextField jtfLineaFactura;
    public javax.swing.JTextField jtfMarcaRepuesto;
    public javax.swing.JTextField jtfMarcaVehiculo;
    public javax.swing.JTextField jtfMatriculaVehiculo;
    public javax.swing.JTextField jtfModeloVehiculo;
    public javax.swing.JTextField jtfMotorVehiculo;
    public javax.swing.JTextField jtfNombreCompletoTrabajadorVehiculo;
    public javax.swing.JTextField jtfNombreRepuesto;
    public javax.swing.JTextField jtfNombreTrabajador;
    public javax.swing.JTextField jtfNumExpedicionInforme;
    public javax.swing.JTextField jtfNumFactura;
    public javax.swing.JTextField jtfNumFacturaRepuestos;
    public javax.swing.JTextField jtfNumSerieRepuesto;
    public javax.swing.JTextField jtfPotenciaVehiculo;
    public javax.swing.JTextField jtfPrecioFactura;
    public javax.swing.JTextField jtfReferenciaRepuesto;
    public javax.swing.JTextField jtfRpmInstObd;
    public javax.swing.JTextField jtfSensores;
    public javax.swing.JTextField jtfTareasInforme;
    public javax.swing.JTextField jtfTemperaturaAceiteObd;
    public javax.swing.JTextField jtfTemperaturaAguaObd;
    public javax.swing.JTextField jtfTiempoDedicado;
    public javax.swing.JTextField jtfTiendaRepuesto;
    public javax.swing.JTextField jtfUsoRepuesto;
    public javax.swing.JTextField jtfnumExpedicionInformeFactura;
    public javax.swing.JTabbedPane jtpTablaPrincipal;
    public javax.swing.JLabel lblTitle;
    public javax.swing.JPanel pTrabajadores;
    public javax.swing.JPanel pTrabajadores1;
    public javax.swing.JPanel pTrabajadores2;
    public javax.swing.JPanel pTrabajadores3;
    public javax.swing.JPanel pVehiculos;
    // End of variables declaration//GEN-END:variables
}
