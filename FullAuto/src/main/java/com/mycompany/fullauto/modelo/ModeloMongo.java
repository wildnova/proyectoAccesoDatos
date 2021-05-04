/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.modelo;


import com.mongodb.BasicDBObject;
import com.mongodb.ClientSessionOptions;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.ClientSession;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.connection.ClusterDescription;
import com.mycompany.fullauto.Exceptions.DAOFacturaExcepcion;
import com.mycompany.fullauto.Exceptions.DAOInformeExcepcion;
import com.mycompany.fullauto.Exceptions.DAOObdExcepcion;
import com.mycompany.fullauto.Exceptions.DAORepuestoExcepcion;
import com.mycompany.fullauto.Exceptions.DAOTrabajadorExcepcion;
import com.mycompany.fullauto.Exceptions.DAOVehiculoExcepcion;
import com.mycompany.fullauto.Factura;
import com.mycompany.fullauto.Informe;
import com.mycompany.fullauto.Obd;
import com.mycompany.fullauto.Repuestos;
import com.mycompany.fullauto.Trabajador;
import com.mycompany.fullauto.Vehiculo;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Convert;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author novad
 */
public class ModeloMongo implements DAO{
    MongoClient mongo = null;
    MongoDatabase database=null;
    public ModeloMongo() {
        mongo = crearConexion();
        database= mongo.getDatabase("fullAuto");
        
        if(database.getCollection("trabajador").countDocuments()==0)
        {
            MongoCollection<Document> trabajadores = database.getCollection("trabajador");
            Document trabajador = new Document("_id", new ObjectId());
            trabajador.append("dni", "11111111A")
                      .append("nombre", "Juan")
                      .append("apellido1", "Bautista")
                      .append("apellido2", "Conejero")
                      .append("funcion", "cambios");
            trabajadores.insertOne(trabajador);
        }
        
        if(database.getCollection("vehiculo").countDocuments()==0)
        {
        MongoCollection<Document> vehiculos = database.getCollection("vehiculo");
        Document vehiculo = new Document("_id", new ObjectId());
        vehiculo.append("bastidor", "111111111111111")
                  .append("matricula", "4470kjz")
                  .append("marca", "Kia")
                  .append("modelo", "Cerato")
                  .append("motor", "1600cc")
                  .append("potenciaCv", "130")
                  .append("carburante", "diesel")
                  .append("aceite", "-10-40")
                  .append("consumo", "5.8l/100km")
                  .append("fecha_entrada", "2020-11-30")
                  .append("tiempo_dedicado", "60 minutos")
                  .append("dni_trabajador", "11111111A");
                  
        vehiculos.insertOne(vehiculo);
        }
        
        if(database.getCollection("informe").countDocuments()==0)
        {
        MongoCollection<Document> informes = database.getCollection("informe");
        Document informe = new Document("_id", new ObjectId());
        informe.append("num_expedicion", 1111)
                  .append("tareas", "Sustituir pastillas de freno antiguas por las nuevas")
                  .append("dni_trabajador", "11111111A");
        informes.insertOne(informe);
        }
        
        if(database.getCollection("factura").countDocuments()==0)
        {
        MongoCollection<Document> facturas = database.getCollection("factura");
        Document factura = new Document("_id", new ObjectId());
        factura.append("num_factura", 111)
                  .append("precio", 35.45)
                  .append("linea_factura", "Pastillas freno brembo delanteras y traseras")
                  .append("num_expedicion_informe", 1111);
                  
        facturas.insertOne(factura);
        }
        if(database.getCollection("repuesto").countDocuments()==0)
        {
        MongoCollection<Document> repuestos = database.getCollection("repuesto");
        Document repuesto = new Document("_id", new ObjectId());
        repuesto.append("num_serie", "25")
                  .append("referencia", "11111")
                  .append("nombre", "Pastilla de freno")
                  .append("marca", "Brembo")
                  .append("uso", "Sistema de frenos")
                  .append("tienda", "Serca")
                  .append("fecha_compra", "2021-01-06")
                  .append("bastidor_vehiculo", "111111111111111")
                  .append("num_factura", 111);
        repuestos.insertOne(repuesto);
        }
        
        if(database.getCollection("obd").countDocuments()==0)
        {
        MongoCollection<Document> obds = database.getCollection("obd");
        Document obd = new Document("_id", new ObjectId());
        obd.append("id", 11111111)
                  .append("rpm_inst", 2500)
                  .append("temp_aceite", 63.2)
                  .append("temp_agua", 89.45)
                  .append("cod_salida", "122")
                  .append("sensores","Frenos") 
                  .append("bastidor_vehiculo", "111111111111111");
                  
        obds.insertOne(obd);
        }
         
    }
    public MongoClient crearConexion()
    {
        MongoClient mongo = null;
        mongo = MongoClients.create("mongodb://localhost:27017");
        if(mongo != null)
        {
            System.out.println("Conexión establecida con MongoDB en docker");
        }
        else
        {
            System.out.println("Error de conexión con mongoDB");
        }
        return mongo;
    }

    
    @Override
    public ArrayList<Trabajador> getListadoTrabajadores() throws DAOTrabajadorExcepcion {
        MongoCollection<Document> trabajadores = database.getCollection("trabajador");
        ArrayList<Document> trabajadoresDocList= trabajadores.find().into(new ArrayList<>());
        ArrayList<Trabajador> listaTrabajadores=new ArrayList();
        for(Document trabajadorDoc:trabajadoresDocList)
        {
            String dni = trabajadorDoc.get("dni").toString();
            String nombre = trabajadorDoc.get("nombre").toString();
            String apellido1 = trabajadorDoc.get("apellido1").toString();
            String apellido2 = trabajadorDoc.get("apellido2").toString();
            String funcion = trabajadorDoc.get("funcion").toString();
            
            System.out.println(dni+"  "+nombre+"  "+apellido1+"   "+apellido2+"  " +funcion);
            
            Trabajador trabajador = new Trabajador(dni,nombre,apellido1,apellido2,funcion);
            listaTrabajadores.add(trabajador);
        }
        
        return listaTrabajadores;
    }

    @Override
    public Trabajador comprobarTrabajador(String dniTrabajador) throws DAOTrabajadorExcepcion {
        MongoCollection<Document> trabajadores = database.getCollection("trabajador");
        Document trabajadorDoc = trabajadores.find(new Document("dni",dniTrabajador)).first();
        String dni = trabajadorDoc.get("dni").toString();
        String nombre = trabajadorDoc.get("nombre").toString();
        String apellido1 = trabajadorDoc.get("apellido1").toString();
        String apellido2 = trabajadorDoc.get("apellido2").toString();
        String funcion = trabajadorDoc.get("funcion").toString();
        Trabajador trabajador= new Trabajador(dni,nombre,apellido1,apellido2,funcion);
        return trabajador;
    }

    @Override
    public void insertarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion {
        MongoCollection<Document> trabajadores = database.getCollection("trabajador");
        
        Document trabajadorDoc = new Document("_id", new ObjectId());
        trabajadorDoc.append("dni", trabajador.getDni())
                  .append("nombre", trabajador.getNombre())
                  .append("apellido1", trabajador.getApellido1())
                  .append("apellido2", trabajador.getApellido2())
                  .append("funcion", trabajador.getFuncion());
        trabajadores.insertOne(trabajadorDoc);
    }

    @Override
    public void modificarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion {
       MongoCollection<Document> trabajadores = database.getCollection("trabajador");
        Bson filter = eq("dni", trabajador.getDni());
            Bson update1 = set("nombre", trabajador.getNombre());
            Bson update2 = set("apellido1", trabajador.getApellido1());
            Bson update3 = set("apellido2", trabajador.getApellido2());
            Bson update4 = set("funcion", trabajador.getFuncion());
            Bson updates = combine(update1,update2,update3,update4);
            Document updateDB = trabajadores.findOneAndUpdate(filter, updates);
    }

    @Override
    public void eliminarTrabajador(Trabajador trabajador) throws DAOTrabajadorExcepcion {
         MongoCollection<Document> trabajadores = database.getCollection("trabajador");
         Bson filter = eq("dni", trabajador.getDni());
         DeleteResult result = trabajadores.deleteOne(filter);
    }

    @Override
    public ArrayList<Vehiculo> getListadoVehiculos() throws DAOVehiculoExcepcion {
       MongoCollection<Document> vehiculos = database.getCollection("vehiculo");
        ArrayList<Document> vehiculosDocList= vehiculos.find().into(new ArrayList<>());
        ArrayList<Vehiculo> listaVehiculos=new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for(Document vehiculoDoc:vehiculosDocList)
        {
            String bastidor = vehiculoDoc.get("bastidor").toString();
            String matricula = vehiculoDoc.get("matricula").toString();
            String marca = vehiculoDoc.get("marca").toString();
            String modelo = vehiculoDoc.get("modelo").toString();
            String motor = vehiculoDoc.get("motor").toString();
            String potenciaCv = vehiculoDoc.get("potenciaCv").toString();
            String carburante = vehiculoDoc.get("carburante").toString();
            String aceite = vehiculoDoc.get("aceite").toString();
            String consumo = vehiculoDoc.get("consumo").toString();
            
            Date fechaEntrada=null;
            try {
               fechaEntrada = formatter.parse(String.valueOf(vehiculoDoc.get("fecha_entrada")));
            } catch (ParseException ex) {
               ex.printStackTrace();
                System.err.println("Error al convertir la fecha de entrada de vehículo.");
            }
            String tiempoDedicado = vehiculoDoc.get("tiempo_dedicado").toString();
            String dniTrabajador = vehiculoDoc.get("dni_trabajador").toString();
            
            
            
            System.out.println(bastidor+"  "+matricula+"  "+marca+"   "+modelo+"  " +motor+ "  "+ potenciaCv+"  "+carburante+"  "+aceite+"   "+consumo+"  " +fechaEntrada+"   "+tiempoDedicado+"  " +dniTrabajador);
            
            Vehiculo vehiculo = new Vehiculo(bastidor,matricula,marca,modelo,motor,potenciaCv,carburante,aceite,consumo,fechaEntrada,tiempoDedicado,dniTrabajador);
            listaVehiculos.add(vehiculo);
        }
        
        return listaVehiculos;
    }

    @Override
    public void insertarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion {
        MongoCollection<Document> vehiculos = database.getCollection("vehiculo");
        
        Document vehiculoDoc = new Document("_id", new ObjectId());
        vehiculoDoc.append("bastidor", vehiculo.getBastidor())
                  .append("matricula", vehiculo.getMatricula())
                  .append("marca", vehiculo.getMarca())
                  .append("modelo", vehiculo.getModelo())
                  .append("motor", vehiculo.getMotor())
                  .append("potenciaCv", vehiculo.getPotenciaCv())
                  .append("carburante", vehiculo.getCarburante())
                  .append("aceite", vehiculo.getAceite())
                  .append("consumo", vehiculo.getConsumo())
                  .append("fecha_entrada", vehiculo.getFechaEntrada().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate().toString())
                  .append("tiempo_dedicado", vehiculo.getTiempoDedicado())
                  .append("dni_trabajador", vehiculo.getDniTrabajador());
                  
        vehiculos.insertOne(vehiculoDoc);
    }

    @Override
    public void modificarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion {
        MongoCollection<Document> vehiculos = database.getCollection("vehiculo");
        Bson filter = eq("bastidor", vehiculo.getBastidor());
            Bson update1 = set("matricula",  vehiculo.getMatricula());
            Bson update2 = set("marca",  vehiculo.getMarca());
            Bson update3 = set("modelo",  vehiculo.getModelo());
            Bson update4 = set("motor",  vehiculo.getMotor());
            Bson update5 = set("potenciaCv",  vehiculo.getPotenciaCv());
            Bson update6 = set("carburante",  vehiculo.getCarburante());
            Bson update7 = set("aceite",  vehiculo.getAceite());
            Bson update8 = set("consumo",  vehiculo.getConsumo());
            Bson update9 = set("fecha_entrada", vehiculo.getFechaEntrada().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate().toString());
            Bson update10 = set("tiempo_dedicado",  vehiculo.getTiempoDedicado());
            Bson update11 = set("dni_trabajador",  vehiculo.getDniTrabajador());
            Bson updates = combine(update1,update2,update3,update4,update5,update6,update7,update8,update9,update10,update11);
            Document updateDB = vehiculos.findOneAndUpdate(filter, updates);
    }

    @Override
    public void eliminarVehiculo(Vehiculo vehiculo) throws DAOVehiculoExcepcion {
        MongoCollection<Document> vehiculos = database.getCollection("vehiculo");
         Bson filter = eq("bastidor", vehiculo.getBastidor());
         DeleteResult result = vehiculos.deleteOne(filter);
    }

    @Override
    public ArrayList<Informe> getListadoInformes() throws DAOInformeExcepcion {
        MongoCollection<Document> informes = database.getCollection("informe");
        ArrayList<Document> informesDocList= informes.find().into(new ArrayList<>());
        ArrayList<Informe> listaInformes=new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for(Document informeDoc:informesDocList)
        {
            int numExpedicion = Integer.parseInt(informeDoc.get("num_expedicion").toString());
            String tareas = informeDoc.get("tareas").toString();
            String dniTrabajador = informeDoc.get("dni_trabajador").toString();

            System.out.println(numExpedicion+"  "+tareas+"  "+dniTrabajador);
            
            Informe informe = new Informe(numExpedicion,tareas,dniTrabajador);
            listaInformes.add(informe);
        }
        
        return listaInformes;
    }

    @Override
    public void insertarInforme(Informe informe) throws DAOInformeExcepcion {
        MongoCollection<Document> informes = database.getCollection("informe");
        Document informeDoc = new Document("_id", new ObjectId());
        informeDoc.append("num_expedicion", informe.getNumExpedicion())
                  .append("tareas", informe.getTareas())
                  .append("dni_trabajador", informe.getDniTrabajadorInforme());
        informes.insertOne(informeDoc);
        
        
    }

    @Override
    public void modificarInforme(Informe informe) throws DAOInformeExcepcion {
        MongoCollection<Document> informes = database.getCollection("informe");
        Bson filter = eq("num_expedicion", informe.getNumExpedicion());
            Bson update1 = set("tareas", informe.getTareas());
            Bson update2 = set("dni_trabajador", informe.getDniTrabajadorInforme());
            Bson updates = combine(update1,update2);
            Document updateDB = informes.findOneAndUpdate(filter, updates);
    }

    @Override
    public void eliminarInforme(Informe informe) throws DAOInformeExcepcion {
       MongoCollection<Document> informes = database.getCollection("informe");
         Bson filter = eq("num_expedicion", informe.getNumExpedicion());
         DeleteResult result = informes.deleteOne(filter);
    }

    @Override
    public ArrayList<Factura> getListadoFacturas() throws DAOFacturaExcepcion {
        MongoCollection<Document> facturas = database.getCollection("factura");
        ArrayList<Document> facturasDocList= facturas.find().into(new ArrayList<>());
        ArrayList<Factura> listaFacturas=new ArrayList();
        
        for(Document facturaDoc:facturasDocList)
        {
            int numFactura = Integer.parseInt(facturaDoc.get("num_factura").toString());
            double precio = Double.parseDouble(facturaDoc.get("precio").toString());
            String lineaFactura = facturaDoc.get("linea_factura").toString();
            String numExpedicionInforme= facturaDoc.get("num_expedicion_informe").toString();
            
            System.out.println(numFactura+"  "+precio+"  "+lineaFactura+ "   "+ numExpedicionInforme);
            
            Factura factura = new Factura(numFactura,precio,lineaFactura,numExpedicionInforme);
            listaFacturas.add(factura);
        }
        
        return listaFacturas;
    }

    @Override
    public void insertarFactura(Factura factura) throws DAOFacturaExcepcion {
        MongoCollection<Document> facturas = database.getCollection("factura");
        Document facturaDoc = new Document("_id", new ObjectId());
        facturaDoc.append("num_factura", factura.getNumFactura())
                  .append("precio", factura.getPrecio())
                  .append("linea_factura", factura.getLineaFactura())
                  .append("num_expedicion_informe", factura.getNumExpedicionInforme());
                  
        facturas.insertOne(facturaDoc);
    }

    @Override
    public void modificarFactura(Factura factura) throws DAOFacturaExcepcion {
        MongoCollection<Document> facturas = database.getCollection("factura");
        Bson filter = eq("num_factura", factura.getNumFactura());
            Bson update1 = set("precio", factura.getPrecio());
            Bson update2 = set("lineaFactura", factura.getLineaFactura());
            Bson update3 = set("num_expedicion_informe", factura.getNumExpedicionInforme());
            
            Bson updates = combine(update1,update2,update3);
            Document updateDB = facturas.findOneAndUpdate(filter, updates);
    }

    @Override
    public void eliminarFactura(Factura factura) throws DAOFacturaExcepcion {
        MongoCollection<Document> facturas = database.getCollection("factura");
         Bson filter = eq("num_factura", factura.getNumFactura());
         DeleteResult result = facturas.deleteOne(filter);
    }

    @Override
    public ArrayList<Repuestos> getListadoRepuestos() throws DAORepuestoExcepcion {
        MongoCollection<Document> repuestos = database.getCollection("repuesto");
        ArrayList<Document> repuestosDocList= repuestos.find().into(new ArrayList<>());
        ArrayList<Repuestos> listaRepuestos=new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for(Document repuestoDoc:repuestosDocList)
        {
            String numSerie = repuestoDoc.get("num_serie").toString();
            String referencia = repuestoDoc.get("referencia").toString();
            String nombre = repuestoDoc.get("nombre").toString();
            String marca = repuestoDoc.get("marca").toString();
            String uso= repuestoDoc.get("uso").toString();
            String tienda= repuestoDoc.get("tienda").toString();
            Date fechaCompra= null;
            try {
               fechaCompra = formatter.parse(String.valueOf(repuestoDoc.get("fecha_compra").toString()));
            } catch (ParseException ex) {
               ex.printStackTrace();
                System.err.println("Error al convertir la fecha de compra de repuesto.");
            }
            String bastidorVehiculo= repuestoDoc.get("bastidor_vehiculo").toString();
            String numFactura= repuestoDoc.get("num_factura").toString();
                    
            System.out.println(numSerie+"  "+referencia+"  "+nombre+ "   "+ marca+"  "+uso+"  "+tienda+ "   "+ fechaCompra+"  "+bastidorVehiculo+"   "+ numFactura);
            
            Repuestos repuesto= new Repuestos(numSerie,referencia,nombre,marca,uso,tienda,fechaCompra,bastidorVehiculo,numFactura);
            listaRepuestos.add(repuesto);
        }
        
        return listaRepuestos;
    }

    @Override
    public void insertarRepuesto(Repuestos repuesto) throws DAORepuestoExcepcion {
        MongoCollection<Document> repuestos = database.getCollection("repuesto");
        Document repuestoDoc = new Document("_id", new ObjectId());
        repuestoDoc.append("num_serie", repuesto.getNumSerie())
                  .append("referencia", repuesto.getReferencia())
                  .append("nombre", repuesto.getNombre())
                  .append("marca", repuesto.getMarca())
                  .append("uso", repuesto.getUso())
                  .append("tienda", repuesto.getTienda())
                  .append("fecha_compra", repuesto.getFechaCompra().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate().toString())
                  .append("bastidor_vehiculo", repuesto.getBastidorVehiculoRepuestos())
                  .append("num_factura", repuesto.getNumFacturaRepuestos());
        
        repuestos.insertOne(repuestoDoc);
        
        
    }

    @Override
    public void modificarRepuesto(Repuestos repuesto) throws DAORepuestoExcepcion {
        MongoCollection<Document> repuestos = database.getCollection("repuesto");
        Bson filter = eq("num_serie", repuesto.getNumSerie());
            Bson update1 = set("referencia",  repuesto.getReferencia());
            Bson update2 = set("nombre",  repuesto.getNombre());
            Bson update3 = set("marca",  repuesto.getMarca());
            Bson update4 = set("uso",  repuesto.getUso());
            Bson update5 = set("tienda",  repuesto.getTienda());
            Bson update6 = set("fecha_compra",  repuesto.getFechaCompra().toInstant().atZone(ZoneId.of("Europe/Madrid")).toLocalDate().toString());
            Bson update7 = set("bastidor_vehiculo",  repuesto.getBastidorVehiculoRepuestos());
            Bson update8 = set("num_factura",  repuesto.getNumFacturaRepuestos());
            
            Bson updates = combine(update1,update2,update3,update4,update5,update6,update7,update8);
            Document updateDB = repuestos.findOneAndUpdate(filter, updates);
    }

    @Override
    public void eliminarRepuesto(Repuestos repuesto) throws DAORepuestoExcepcion {
        MongoCollection<Document> repuestos = database.getCollection("repuesto");
         Bson filter = eq("num_serie", repuesto.getNumSerie());
         DeleteResult result = repuestos.deleteOne(filter);
    }

    @Override
    public ArrayList<Obd> getListadoObd() throws DAOObdExcepcion {
        MongoCollection<Document> obds = database.getCollection("obd");
        ArrayList<Document> obdsDocList= obds.find().into(new ArrayList<>());
        ArrayList<Obd> listaObds=new ArrayList();
        for(Document obdDoc:obdsDocList)
        {
            int id = Integer.parseInt(obdDoc.get("id").toString());
            int rpmInst = Integer.parseInt(obdDoc.get("rpm_inst").toString());
            double tempAceite = Double.parseDouble(obdDoc.get("temp_aceite").toString());
            double tempAgua = Double.parseDouble(obdDoc.get("temp_agua").toString());
            String codSalida= obdDoc.get("cod_salida").toString();
            String sensores= obdDoc.get("sensores").toString();
            String bastidorVehiculo= obdDoc.get("bastidor_vehiculo").toString();
           
                    
            System.out.println(id+"  "+rpmInst+"  "+tempAceite+ "   "+ tempAgua+"  "+codSalida+"  "+sensores+ "   "+ bastidorVehiculo);
            
            Obd obd= new Obd(id,rpmInst,tempAceite,tempAgua,codSalida,sensores,bastidorVehiculo);
            listaObds.add(obd);
        }
        
        return listaObds;
    }

    @Override
    public void insertarObd(Obd obd) throws DAOObdExcepcion {
        MongoCollection<Document> obds = database.getCollection("obd");
        Document obdDoc = new Document("_id", new ObjectId());
        obdDoc.append("id", obd.getId())
                  .append("rpm_inst", obd.getRpm_inst())
                  .append("temp_aceite", obd.getTemp_aceite())
                  .append("temp_agua", obd.getTemp_agua())
                  .append("cod_salida", obd.getCod_salida())
                  .append("sensores",obd.getSensores()) 
                  .append("bastidor_vehiculo", obd.getBastidorVehiculoObd());
                  
        obds.insertOne(obdDoc);
    }

    @Override
    public void modificarObd(Obd obd) throws DAOObdExcepcion {
        MongoCollection<Document> obds = database.getCollection("obd");
        Bson filter = eq("id", obd.getId());
            Bson update1 = set("rpm_inst",  obd.getRpm_inst());
            Bson update2 = set("temp_aceite",  obd.getTemp_aceite());
            Bson update3 = set("temp_agua",  obd.getTemp_agua());
            Bson update4 = set("cod_salida",  obd.getCod_salida());
            Bson update5 = set("sensores",  obd.getSensores());
            Bson update6 = set("bastidor_vehiculo",  obd.getBastidorVehiculoObd());
            
            Bson updates = combine(update1,update2,update3,update4,update5,update6);
            Document updateDB = obds.findOneAndUpdate(filter, updates);
    }

    @Override
    public void eliminarObd(Obd obd) throws DAOObdExcepcion {
        MongoCollection<Document> obds = database.getCollection("obd");
         Bson filter = eq("id", obd.getId());
         DeleteResult result = obds.deleteOne(filter);
    }
}
