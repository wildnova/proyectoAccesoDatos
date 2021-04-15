/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fullauto.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author novad
 */
public class EliminarFactura extends javax.swing.JDialog implements ActionListener{

    /**
     * Creates new form EliminarTrabajador
     */
    boolean confirmarEliminar;
    public EliminarFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        confirmarEliminar=false;
        
        jbAceptar.addActionListener(this);
        jbCancelar.addActionListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlTituloEliminarFactura = new javax.swing.JLabel();
        jbCancelar = new javax.swing.JButton();
        jbAceptar = new javax.swing.JButton();
        jlNumFactura = new javax.swing.JLabel();
        jtfNumFactura = new javax.swing.JTextField();
        jlPrecioFactura = new javax.swing.JLabel();
        jtfPrecio = new javax.swing.JTextField();
        jlLineaFactura = new javax.swing.JLabel();
        jtfLineaFactura = new javax.swing.JTextField();
        jlNumExpedicionInforme = new javax.swing.JLabel();
        jtfNumExpedicionInforme = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jlTituloEliminarFactura.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jlTituloEliminarFactura.setForeground(new java.awt.Color(255, 0, 51));
        jlTituloEliminarFactura.setText("¿Eliminar la siguiente factura de la base de datos?");

        jbCancelar.setText("Cancelar");

        jbAceptar.setText("Aceptar");

        jlNumFactura.setBackground(new java.awt.Color(255, 255, 255));
        jlNumFactura.setForeground(new java.awt.Color(0, 102, 204));
        jlNumFactura.setText("Número de factura");

        jlPrecioFactura.setForeground(new java.awt.Color(0, 102, 204));
        jlPrecioFactura.setText("Precio");

        jlLineaFactura.setForeground(new java.awt.Color(0, 102, 204));
        jlLineaFactura.setText("Líneas de factura");

        jlNumExpedicionInforme.setForeground(new java.awt.Color(0, 102, 204));
        jlNumExpedicionInforme.setText("Número de expedición del informe asociado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlTituloEliminarFactura)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlNumExpedicionInforme)
                                    .addComponent(jtfLineaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfNumExpedicionInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlNumFactura)
                                    .addComponent(jlLineaFactura)
                                    .addComponent(jlPrecioFactura)
                                    .addComponent(jtfPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfNumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlTituloEliminarFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlNumFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfNumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlPrecioFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlLineaFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfLineaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlNumExpedicionInforme)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfNumExpedicionInforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    @Override
    public void actionPerformed(ActionEvent ae) {
         if(ae.getSource()==jbAceptar)
        {
            confirmarEliminar=true;
            this.dispose();
        }
        if(ae.getSource()==jbCancelar)
        {
            confirmarEliminar=false;
            this.dispose();
        }
    }
    
    //Para extraer si el usuario ha pulsado en aceptar o no.
    public boolean getConfirmacion() {
        return confirmarEliminar;
    }
    //Para utilizar el formulario con la función modificar y colocarle los datos que ya existen.
    public void setTextoDni(String texto){
        jtfDni.setText(texto);
    }
    public void setTextoNombre(String texto){
        jtfNombre.setText(texto);
    }
    public void setTextoApellido1(String texto){
        jtfApellido1.setText(texto);
    }
    public void setTextoApellido2(String texto){
        jtfApellido2.setText(texto);
    }
    public void setTextoFuncion(String texto){
        jtfFuncion.setText(texto);
    }
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
            java.util.logging.Logger.getLogger(EliminarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EliminarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EliminarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EliminarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EliminarFactura dialog = new EliminarFactura(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JLabel jlLineaFactura;
    private javax.swing.JLabel jlNumExpedicionInforme;
    private javax.swing.JLabel jlNumFactura;
    private javax.swing.JLabel jlPrecioFactura;
    private javax.swing.JLabel jlTituloEliminarFactura;
    private javax.swing.JTextField jtfLineaFactura;
    private javax.swing.JTextField jtfNumExpedicionInforme;
    private javax.swing.JTextField jtfNumFactura;
    private javax.swing.JTextField jtfPrecio;
    // End of variables declaration//GEN-END:variables

    
}
