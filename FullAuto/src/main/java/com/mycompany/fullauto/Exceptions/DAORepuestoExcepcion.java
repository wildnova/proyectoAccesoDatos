/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.Exceptions;

/**
 *
 * @author novad
 */
public class DAORepuestoExcepcion extends Exception{
    public DAORepuestoExcepcion(String mensaje)
    {
        super("Se ha producido un error en repuesto "+ mensaje);
    }
    
}
