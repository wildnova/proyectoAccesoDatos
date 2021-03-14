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
import java.sql.*;
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
    
    
    private int contadorTrabajador;
    private int contadorVehiculo;
    private int contadorInforme;
    private int contadorRepuestos;
    private int contadorObd;
    private int contadorFactura;
    
    public Vista(){
        initComponents();
        controlador = new Controlador(this);
        
        
        jbSiguienteTrabajador.addActionListener(this);
        jbAnteriorTrabajador.addActionListener(this);
        jbInsertarTrabajador.addActionListener(this);
        jbModificarTrabajador.addActionListener(this);
        jbEliminarTrabajador.addActionListener(this);
        
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
        mostrarDatosTrabajador();
    }
    
    public void actionPerformed(ActionEvent ae) {
        System.out.println("evento Activado");
 
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
    }
    public void mostrarDatosTrabajador()
    {
        listaTrabajadores= controlador.getListadoTrabajadores();
        Trabajador trabajador = listaTrabajadores.get(contadorTrabajador);
        cargarCamposTrabajador(trabajador);

    }
    public void mostrarSiguienteTrabajador()
    {
        contadorTrabajador++;
        Trabajador trabajador=listaTrabajadores.get(contadorTrabajador);
        cargarCamposTrabajador(trabajador);
    }
    public void mostrarAnteriorTrabajador()
    {
        contadorTrabajador--;
        Trabajador trabajador=listaTrabajadores.get(contadorTrabajador);
        cargarCamposTrabajador(trabajador);
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
        //Esta variable se usa para guardar el dni que corresponde al trabajador que se va a modificar cuando se haga la búsqueda en la base de datos en el modelo.
        String dniOriginal;
        
        Trabajador trabajador = listaTrabajadores.get(contadorTrabajador);
        dniOriginal= trabajador.getDni();
        FormularioTrabajador ft = new FormularioTrabajador(this, true);
        ft.setTextoTitulo("Modificar trabajador");
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
            controlador.modificarTrabajador(trabajador,dniOriginal);
            
            mostrarDatosTrabajador();
        }
        
    }
    
    public void eliminarTrabajador()
    {
        boolean confirmarEliminar;
        String dni;
        
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
    public void cargarCamposTrabajador(Trabajador trabajador)
    {
        jtfDniTrabajador.setText(trabajador.getDni());
        jtfNombreTrabajador.setText(trabajador.getNombre());
        jtfApellido1Trabajador.setText(trabajador.getApellido1());
        jtfApellido2Trabajador.setText(trabajador.getApellido2());
        jtfFuncionTrabajador.setText(trabajador.getFuncion());
    }
    public void cargarCamposVehiculo(String[] vehiculo)
    {
        jtfBastidorVehiculo.setText(vehiculo[0]);
        jtfMatriculaVehiculo.setText(vehiculo[1]);
        jtfMarcaVehiculo.setText(vehiculo[2]);
        jtfModeloVehiculo.setText(vehiculo[3]);
        jtfMotorVehiculo.setText(vehiculo[4]);
        jtfPotenciaVehiculo.setText(vehiculo[5]);
        jtfCarburanteVehiculo.setText(vehiculo[6]);
        jtfAceiteVehiculo.setText(vehiculo[7]);
        jtfConsumoVehiculo.setText(vehiculo[8]);
        jtfFechaEntradaVehiculo.setText(vehiculo[9]);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jtfnumeroFacturaRepuestos = new javax.swing.JTextField();
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
        jlRpmAvgObd = new javax.swing.JLabel();
        jtfRpmAvgObd = new javax.swing.JTextField();
        jlConsumoInstObd = new javax.swing.JLabel();
        jtfConsumoInstObd = new javax.swing.JTextField();
        jlConsumoMedioObd = new javax.swing.JLabel();
        jtfConsumoMedioOBD = new javax.swing.JTextField();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setBackground(new java.awt.Color(0, 153, 255));
        lblTitle.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        lblTitle.setText("FullAuto");
        lblTitle.setToolTipText("");

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
                .addContainerGap(513, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jtfFuncionTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(288, 288, 288))
        );

        jtpTablaPrincipal.addTab("Trabajadores", pTrabajadores);

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

        javax.swing.GroupLayout pTrabajadores1Layout = new javax.swing.GroupLayout(pTrabajadores1);
        pTrabajadores1.setLayout(pTrabajadores1Layout);
        pTrabajadores1Layout.setHorizontalGroup(
            pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlMotorVehiculo)
                            .addComponent(jlMatriculaVehiculo))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlBastidorVehiculo)
                                    .addComponent(jlMarcaVehiculo))
                                .addGap(38, 38, 38)
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfMatriculaVehiculo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jtfCarburanteVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                                        .addComponent(jtfAceiteVehiculo)
                                        .addComponent(jtfConsumoVehiculo)
                                        .addComponent(jtfMarcaVehiculo)
                                        .addComponent(jtfMotorVehiculo)
                                        .addComponent(jtfPotenciaVehiculo))
                                    .addComponent(jtfBastidorVehiculo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTrabajadores1Layout.createSequentialGroup()
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlCarburanteVehiculo)
                                            .addComponent(jlAceiteVehiculo)
                                            .addComponent(jlConsumoVehiculo)
                                            .addComponent(jlPotenciaVehiculo)
                                            .addComponent(jlModeloVehiculo))
                                        .addGap(17, 17, 17))
                                    .addComponent(jlFechaEntradaVehiculo))
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtfFechaEntradaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jtfModeloVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(45, 45, 45)
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTrabajadores1Layout.createSequentialGroup()
                                .addComponent(jbSiguienteVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbModificarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbInsertarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbEliminarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(142, 142, 142))
                            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                .addComponent(jbAnteriorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(535, Short.MAX_VALUE))))))
        );
        pTrabajadores1Layout.setVerticalGroup(
            pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                        .addComponent(jbInsertarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))
                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfBastidorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlBastidorVehiculo))
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlMatriculaVehiculo)
                                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                        .addComponent(jtfMatriculaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21)
                                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jtfMarcaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jlMarcaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jbModificarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(pTrabajadores1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jbSiguienteVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jbAnteriorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)))
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfModeloVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlModeloVehiculo))
                        .addGap(23, 23, 23)
                        .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlMotorVehiculo)
                            .addComponent(jtfMotorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pTrabajadores1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
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
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfConsumoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlConsumoVehiculo))
                .addGap(18, 18, 18)
                .addGroup(pTrabajadores1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfFechaEntradaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlFechaEntradaVehiculo))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pVehiculosLayout = new javax.swing.GroupLayout(pVehiculos);
        pVehiculos.setLayout(pVehiculosLayout);
        pVehiculosLayout.setHorizontalGroup(
            pVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pVehiculosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pTrabajadores1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        pVehiculosLayout.setVerticalGroup(
            pVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pVehiculosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pTrabajadores1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jtpTablaPrincipal.addTab("Vehículos", pVehiculos);

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
                .addContainerGap(513, Short.MAX_VALUE))
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
                .addContainerGap(377, Short.MAX_VALUE))
        );

        jtpTablaPrincipal.addTab("Informes", jPanel1);

        jlNumFactura.setText("Número de factura");

        jlPrecioFactura.setText("Precio");

        jlLineaFactura.setText("Línea Factura");

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
                .addContainerGap(513, Short.MAX_VALUE))
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
                .addContainerGap(324, Short.MAX_VALUE))
        );

        jtpTablaPrincipal.addTab("Facturas", jPanel2);

        jlNumSerieRepuesto.setText("número de serie");

        jlReferenciaRepuesto.setText("referencia");

        jlNombreRepuesto.setText("nombre");

        jlMarcaRepuesto.setText("marca");

        jtfMarcaRepuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfMarcaRepuestoActionPerformed(evt);
            }
        });

        jlUsoRepuesto.setText("uso");

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

        jtfnumeroFacturaRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfnumeroFacturaRepuestosActionPerformed(evt);
            }
        });

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
                    .addGroup(pTrabajadores2Layout.createSequentialGroup()
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlUsoRepuesto)
                            .addComponent(jlReferenciaRepuesto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 762, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbSiguienteRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbAnteriorRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)))
                .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbModificarRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbEliminarRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbInsertarRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(142, 142, 142))
            .addGroup(pTrabajadores2Layout.createSequentialGroup()
                .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadores2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfBastidorVehiculoRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlNumeroFacturaRepuestos)
                            .addComponent(jtfnumeroFacturaRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGap(31, 31, 31)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfNombreRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlNombreRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbModificarRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfMarcaRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlMarcaRepuesto))
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pTrabajadores2Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlUsoRepuesto)
                                    .addComponent(jtfUsoRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pTrabajadores2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jbEliminarRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlTiendaRepuesto)
                            .addComponent(jtfTiendaRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlFechaCompraRepuesto)
                            .addComponent(jtfFechaCompraRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pTrabajadores2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pTrabajadores2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfNumSerieRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlNumSerieRepuesto)
                            .addComponent(jbInsertarRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jlReferenciaRepuesto)
                        .addGap(26, 26, 26)
                        .addComponent(jbSiguienteRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jbAnteriorRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jlBastidorVehiculoRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jtfBastidorVehiculoRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlNumeroFacturaRepuestos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtfnumeroFacturaRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pTrabajadores2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pTrabajadores2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jtpTablaPrincipal.addTab("Repuestos", jPanel3);

        jlIdObd.setText("identificador");

        jlRpmInstObd.setText("rpm_inst");

        jlRpmAvgObd.setText("rpm_avg");

        jlConsumoInstObd.setText("consumo instantáneo");

        jtfConsumoInstObd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfConsumoInstObdActionPerformed(evt);
            }
        });

        jlConsumoMedioObd.setText("consumo medio");

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

        jtfBastidorVehiculoObd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfBastidorVehiculoObdActionPerformed(evt);
            }
        });

        jlTemperaturaAceiteObd.setText("temperatura aceite");

        jlTemperaturaAguaObd.setText("temperatura agua");

        jlCodigoSalidaObd.setText("código de salida");

        jlBastidorVehiculoObd.setText("bastidor del vehículo de esta lectura ");

        javax.swing.GroupLayout pTrabajadores3Layout = new javax.swing.GroupLayout(pTrabajadores3);
        pTrabajadores3.setLayout(pTrabajadores3Layout);
        pTrabajadores3Layout.setHorizontalGroup(
            pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrabajadores3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadores3Layout.createSequentialGroup()
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlConsumoMedioObd)
                            .addComponent(jlRpmInstObd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 759, Short.MAX_VALUE))
                    .addGroup(pTrabajadores3Layout.createSequentialGroup()
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTrabajadores3Layout.createSequentialGroup()
                                .addComponent(jlTemperaturaAceiteObd)
                                .addGap(90, 90, 90))
                            .addGroup(pTrabajadores3Layout.createSequentialGroup()
                                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlIdObd)
                                    .addComponent(jlRpmAvgObd)
                                    .addComponent(jlConsumoInstObd))
                                .addGap(38, 38, 38)))
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfConsumoInstObd, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfRpmInstObd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfRpmAvgObd, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                                    .addComponent(jtfConsumoMedioOBD)
                                    .addComponent(jtfTemperaturaAceiteObd))
                                .addComponent(jtfIdObd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfTemperaturaAguaObd, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfCodigoSalidaObd, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbSiguienteObd, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbAnteriorObd, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)))
                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbModificarObd, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbEliminarObd, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbInsertarObd, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(142, 142, 142))
            .addGroup(pTrabajadores3Layout.createSequentialGroup()
                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadores3Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfBastidorVehiculoObd, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlBastidorVehiculoObd)))
                    .addComponent(jlTemperaturaAguaObd)
                    .addComponent(jlCodigoSalidaObd))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pTrabajadores3Layout.setVerticalGroup(
            pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTrabajadores3Layout.createSequentialGroup()
                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pTrabajadores3Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jtfRpmInstObd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfRpmAvgObd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlRpmAvgObd)
                            .addComponent(jbModificarObd, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfConsumoInstObd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlConsumoInstObd))
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pTrabajadores3Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlConsumoMedioObd)
                                    .addComponent(jtfConsumoMedioOBD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pTrabajadores3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jbEliminarObd, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlTemperaturaAceiteObd)
                            .addComponent(jtfTemperaturaAceiteObd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlTemperaturaAguaObd)
                            .addComponent(jtfTemperaturaAguaObd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pTrabajadores3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfIdObd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlIdObd)
                            .addComponent(jbInsertarObd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jlRpmInstObd)
                        .addGap(26, 26, 26)
                        .addComponent(jbSiguienteObd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jbAnteriorObd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pTrabajadores3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlCodigoSalidaObd, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCodigoSalidaObd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jlBastidorVehiculoObd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtfBastidorVehiculoObd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pTrabajadores3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(pTrabajadores3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jtpTablaPrincipal.addTab("OBD", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(499, 499, 499)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpTablaPrincipal)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtpTablaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSiguienteTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteTrabajadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSiguienteTrabajadorActionPerformed

    private void jbSiguienteVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSiguienteVehiculoActionPerformed

    private void jtfModeloVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfModeloVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfModeloVehiculoActionPerformed

    private void jtfConsumoVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfConsumoVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfConsumoVehiculoActionPerformed

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

    private void jtfnumeroFacturaRepuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfnumeroFacturaRepuestosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfnumeroFacturaRepuestosActionPerformed

    private void jbInsertarRepuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInsertarRepuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbInsertarRepuestoActionPerformed

    private void jtfConsumoInstObdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfConsumoInstObdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfConsumoInstObdActionPerformed

    private void jbSiguienteObdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteObdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSiguienteObdActionPerformed

    private void jbInsertarObdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInsertarObdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbInsertarObdActionPerformed

    private void jtfBastidorVehiculoObdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfBastidorVehiculoObdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfBastidorVehiculoObdActionPerformed

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
    public javax.swing.JLabel jlConsumoInstObd;
    public javax.swing.JLabel jlConsumoMedioObd;
    public javax.swing.JLabel jlConsumoVehiculo;
    public javax.swing.JLabel jlDniTrabajador;
    public javax.swing.JLabel jlDniTrabajadorInforme;
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
    public javax.swing.JLabel jlNombreRepuesto;
    public javax.swing.JLabel jlNombreTrabajador;
    public javax.swing.JLabel jlNumExpedicionInforme;
    public javax.swing.JLabel jlNumFactura;
    public javax.swing.JLabel jlNumSerieRepuesto;
    public javax.swing.JLabel jlNumeroFacturaRepuestos;
    public javax.swing.JLabel jlPotenciaVehiculo;
    public javax.swing.JLabel jlPrecioFactura;
    public javax.swing.JLabel jlReferenciaRepuesto;
    public javax.swing.JLabel jlRpmAvgObd;
    public javax.swing.JLabel jlRpmInstObd;
    public javax.swing.JLabel jlTareasInforme;
    public javax.swing.JLabel jlTemperaturaAceiteObd;
    public javax.swing.JLabel jlTemperaturaAguaObd;
    public javax.swing.JLabel jlTiendaRepuesto;
    public javax.swing.JLabel jlUsoRepuesto;
    public javax.swing.JLabel jlnumExpedicionInformeFactura;
    public javax.swing.JTextField jtfAceiteVehiculo;
    public javax.swing.JTextField jtfApellido1Trabajador;
    public javax.swing.JTextField jtfApellido2Trabajador;
    public javax.swing.JTextField jtfBastidorVehiculo;
    public javax.swing.JTextField jtfBastidorVehiculoObd;
    public javax.swing.JTextField jtfBastidorVehiculoRepuestos;
    public javax.swing.JTextField jtfCarburanteVehiculo;
    public javax.swing.JTextField jtfCodigoSalidaObd;
    public javax.swing.JTextField jtfConsumoInstObd;
    public javax.swing.JTextField jtfConsumoMedioOBD;
    public javax.swing.JTextField jtfConsumoVehiculo;
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
    public javax.swing.JTextField jtfNombreRepuesto;
    public javax.swing.JTextField jtfNombreTrabajador;
    public javax.swing.JTextField jtfNumExpedicionInforme;
    public javax.swing.JTextField jtfNumFactura;
    public javax.swing.JTextField jtfNumSerieRepuesto;
    public javax.swing.JTextField jtfPotenciaVehiculo;
    public javax.swing.JTextField jtfPrecioFactura;
    public javax.swing.JTextField jtfReferenciaRepuesto;
    public javax.swing.JTextField jtfRpmAvgObd;
    public javax.swing.JTextField jtfRpmInstObd;
    public javax.swing.JTextField jtfTareasInforme;
    public javax.swing.JTextField jtfTemperaturaAceiteObd;
    public javax.swing.JTextField jtfTemperaturaAguaObd;
    public javax.swing.JTextField jtfTiendaRepuesto;
    public javax.swing.JTextField jtfUsoRepuesto;
    public javax.swing.JTextField jtfnumExpedicionInformeFactura;
    public javax.swing.JTextField jtfnumeroFacturaRepuestos;
    public javax.swing.JTabbedPane jtpTablaPrincipal;
    public javax.swing.JLabel lblTitle;
    public javax.swing.JPanel pTrabajadores;
    public javax.swing.JPanel pTrabajadores1;
    public javax.swing.JPanel pTrabajadores2;
    public javax.swing.JPanel pTrabajadores3;
    public javax.swing.JPanel pVehiculos;
    // End of variables declaration//GEN-END:variables
}
