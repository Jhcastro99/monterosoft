/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import conexion.conexion;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static Administrador.inicioadmi.getConnection;
import java.awt.Image;
import java.awt.Toolkit;


/**
 *
 * @author Mie
 */
public class tabla_empleado extends javax.swing.JFrame {
conexion cone =new conexion();
PreparedStatement pt;
    /**
     * Creates new form tabla_empleado
     */


    
    
      private void cargarempleados(){
     DefaultTableModel modelo = (DefaultTableModel) tablaempleados.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone=null;
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `cedula`, `nombre`, `apellido`, `direccion`, `cargo` FROM `empleado` ");
         ResultSet rs = pt.executeQuery();
              rsmd=rs.getMetaData();
              columna=rsmd.getColumnCount();
              
              while(rs.next()){
                Object[] fila= new Object[columna];
                for(int indice=0;indice<columna;indice++){
                    fila[indice]=rs.getObject(indice+1);
                }
                modelo.addRow(fila);
              }
         } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, e.toString());
         }
     
    
    }
           
           private void cargarempleadoindividual(){
     DefaultTableModel modelo = (DefaultTableModel) tablaempleados.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone=null;
    String dato = txtbuscarempleo.getText();
         try {
             
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `cedula`, `nombre`, `apellido`, `direccion`, `cargo` FROM `empleado` WHERE cedula ='"+dato+"' ");
         ResultSet rs = pt.executeQuery();
              rsmd=rs.getMetaData();
              columna=rsmd.getColumnCount();
              
              while(rs.next()){
                Object[] fila= new Object[columna];
                for(int indice=0;indice<columna;indice++){
                    fila[indice]=rs.getObject(indice+1);
                }
                modelo.addRow(fila);
              }
         } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, e.toString());
         }
     
    
    }
           
    public tabla_empleado() {
        initComponents();
        this. setLocationRelativeTo(null);
        this.setSize(450,450);
       this.setResizable(false);        
        cargarempleadoindividual();
        cargarempleados();
        
    }       
           
           @Override
public Image getIconImage(){
    Image ver = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Iconos/productos.png"));
return ver;
}
           
           
           
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDireccion = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtCargo = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaempleados = new javax.swing.JTable();
        btnbuscarproveedor = new javax.swing.JButton();
        txtbuscarempleo = new javax.swing.JTextField();
        Btnlistar2 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de empleados");
        setIconImage(getIconImage());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaempleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cedula", "Nombre", "Apellido", "Direccion", "Cargo"
            }
        ));
        tablaempleados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaempleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaempleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaempleados);

        btnbuscarproveedor.setBackground(new java.awt.Color(255, 255, 255));
        btnbuscarproveedor.setText("Buscar");
        btnbuscarproveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbuscarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarproveedorActionPerformed(evt);
            }
        });

        txtbuscarempleo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarempleoActionPerformed(evt);
            }
        });

        Btnlistar2.setBackground(new java.awt.Color(255, 255, 0));
        Btnlistar2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btnlistar2.setText("Lista completa");
        Btnlistar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnlistar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btnlistar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Btnlistar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnbuscarproveedor)
                        .addGap(18, 18, 18)
                        .addComponent(txtbuscarempleo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnbuscarproveedor)
                    .addComponent(txtbuscarempleo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btnlistar2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        jButton2.setBackground(new java.awt.Color(102, 255, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Volver");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Listado de empleados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton2)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel1)
                        .addGap(0, 91, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaempleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaempleadosMouseClicked
        // TODO add your handling code here:
         tablaempleados.setSelectionBackground(Color.BLUE);
      tablaempleados.setSelectionForeground(Color.WHITE);
        Connection con=null;

        try {
            
            int fila = tablaempleados.getSelectedRow();
            int id =Integer.parseInt(tablaempleados.getValueAt(fila, 0).toString());
            con=getConnection();
            pt=con.prepareStatement("SELECT `cedula`, `nombre`, `apellido`, `direccion`, `cargo` FROM `empleado` WHERE cedula=?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                txtCedula.setText(String.valueOf(id));
                txtNombre.setText(rs.getString("nombre"));
                txtApellido.setText(rs.getString("apellido"));
                txtDireccion.setText(rs.getString("direccion"));
                txtCargo.setText(rs.getString("Cargo"));

            }
        } catch (NumberFormatException | SQLException e) {

        }
    }//GEN-LAST:event_tablaempleadosMouseClicked

    private void btnbuscarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarproveedorActionPerformed
        // TODO add your handling code here:

        Connection cone = null;
        try {
            cone = getConnection();
            pt=cone.prepareStatement("Select * from empleado where cedula=?");
            pt.setString(1, txtbuscarempleo.getText());
            ResultSet rs = pt.executeQuery();

            if (rs.next()) {

                /* Tabla Proveedor con uno Solo */

                cargarempleadoindividual();

            } else {

                JOptionPane.showMessageDialog(null,"Este empleado no esta registrado");
            }

        } catch (Exception e) {
            System.out.println("monterosoft.inicio.btnbuscarActionPerformed()"+e);
        }

    }//GEN-LAST:event_btnbuscarproveedorActionPerformed

    private void txtbuscarempleoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarempleoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarempleoActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
       

    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         this.hide();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void Btnlistar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btnlistar2ActionPerformed
        // TODO add your handling code here:
         cargarempleados();
    }//GEN-LAST:event_Btnlistar2ActionPerformed

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
            java.util.logging.Logger.getLogger(tabla_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tabla_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tabla_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tabla_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tabla_empleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btnlistar2;
    private javax.swing.JButton btnbuscarproveedor;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaempleados;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtCargo;
    public javax.swing.JTextField txtCedula;
    public javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtbuscarempleo;
    // End of variables declaration//GEN-END:variables
}
