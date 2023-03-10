/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador;

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
 * @author ADMIN
 */
public class tabla_categoria extends javax.swing.JFrame {
conexion cone =new conexion();
PreparedStatement pt;
    /**
     * Creates new form frmproveedores
     */
           private void cargarcategorias(){
     DefaultTableModel modelo = (DefaultTableModel) tablacategorias.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone=null;
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_categoría`, `nombre` FROM `categoría` ");
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
           
           private void cargarcategoriasindividual(){
     DefaultTableModel modelo = (DefaultTableModel) tablacategorias.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone=null;
    String dato = txtbuscarproveedor.getText();
         try {
             
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_categoría`, `nombre` FROM `categoría`  where id_categoría ='"+dato+"' ");
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
           
           

    public tabla_categoria() {
        initComponents();
        this. setLocationRelativeTo(null);
        this.setSize(450,450);
       this.setResizable(false);
        
        cargarcategorias();
        
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

        txtcategoria = new javax.swing.JTextField();
        txtidcategoria = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablacategorias = new javax.swing.JTable();
        btnbuscarproveedor = new javax.swing.JButton();
        txtbuscarproveedor = new javax.swing.JTextField();
        btnEliminarproveedor = new javax.swing.JButton();
        Btnlistar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de categorias");
        setIconImage(getIconImage());
        setIconImages(getIconImages());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablacategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id Categoria", "Nombre"
            }
        ));
        tablacategorias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablacategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablacategoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablacategorias);

        btnbuscarproveedor.setText("Buscar");
        btnbuscarproveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbuscarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarproveedorActionPerformed(evt);
            }
        });

        txtbuscarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarproveedorActionPerformed(evt);
            }
        });

        btnEliminarproveedor.setBackground(new java.awt.Color(255, 0, 51));
        btnEliminarproveedor.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnEliminarproveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarproveedor.setText("Eliminar");
        btnEliminarproveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEliminarproveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarproveedorActionPerformed(evt);
            }
        });

        Btnlistar.setBackground(new java.awt.Color(255, 255, 0));
        Btnlistar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btnlistar.setText("Lista completa");
        Btnlistar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnlistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnlistarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Btnlistar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnbuscarproveedor)
                        .addGap(18, 18, 18)
                        .addComponent(txtbuscarproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(162, Short.MAX_VALUE)
                .addComponent(btnEliminarproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnbuscarproveedor)
                    .addComponent(txtbuscarproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btnlistar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarproveedor)
                .addGap(43, 43, 43))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Listado de Categorias");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1)
                        .addGap(0, 128, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarproveedorActionPerformed
        // TODO add your handling code here:
        
         int confirmacion = JOptionPane.showConfirmDialog(jPanel1, "Desea eliminar esta categoria", "confirmar eliminación", JOptionPane.YES_NO_OPTION);

    if (confirmacion ==JOptionPane.YES_OPTION){

  
        Connection con=null;
        
         try {
            con=getConnection();
            pt=con.prepareStatement("DELETE FROM categoría WHERE id_categoría = ?");
            
            pt.setString(1,txtidcategoria.getText());
               int enter=pt.executeUpdate();
               if (enter > 0) {
                   JOptionPane.showMessageDialog(null, "Eliminacion existosa");
                cargarcategorias();
                txtbuscarproveedor.setText(null);
                
            } else {
               JOptionPane.showMessageDialog(null, "Debe seleccionar el dato para eliminar");
              
              
            }
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btpGuardarActionPerformed()"+e);
             JOptionPane.showMessageDialog(rootPane ,"Esta categoria no se puede borrar, esta asociado en un producto ");
             cargarcategorias();
              txtbuscarproveedor.setText(null);
              
        
        } 
    }
      
    }//GEN-LAST:event_btnEliminarproveedorActionPerformed

    private void tablacategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablacategoriasMouseClicked
        // TODO add your handling code here:
     Connection con=null;
        
        try {
            tablacategorias.setSelectionBackground(Color.BLUE);
            tablacategorias.setSelectionForeground(Color.WHITE);
            int fila = tablacategorias.getSelectedRow();
            int id =Integer.parseInt(tablacategorias.getValueAt(fila, 0).toString());
            con=getConnection();
              pt=con.prepareStatement("SELECT `id_categoría`, `nombre` FROM `categoría` WHERE id_categoría=?");
             pt.setInt(1, id);
         ResultSet rs = pt.executeQuery();
              while(rs.next()){
              txtidcategoria.setText(String.valueOf(id));
            txtcategoria.setText(rs.getString("nombre"));
    
                
              }
        } catch (NumberFormatException | SQLException e) {
            
            
        }  
    }//GEN-LAST:event_tablacategoriasMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
         this.hide();
        categorias categoria = new categorias();
        categoria.setVisible(true);
    }//GEN-LAST:event_jButton2MouseClicked

    private void txtbuscarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarproveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarproveedorActionPerformed

    private void btnbuscarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarproveedorActionPerformed
        // TODO add your handling code here:
        
        
         Connection cone = null;
        try {
            cone = getConnection();
             pt=cone.prepareStatement("Select * from categoría where id_categoría=?");
            pt.setString(1, txtbuscarproveedor.getText());
             ResultSet rs = pt.executeQuery();
            
            if (rs.next()) {
                
               /* Tabla Proveedor con uno Solo */
            
              cargarcategoriasindividual();
                              
           
            } else {
                
                JOptionPane.showMessageDialog(null,"Esta categoria no esta registrada");
            }
            
            
        } catch (Exception e) {
            System.out.println("monterosoft.inicio.btnbuscarActionPerformed()"+e);
        }
        
        
        
        
        
    }//GEN-LAST:event_btnbuscarproveedorActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
      
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BtnlistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnlistarActionPerformed
        // TODO add your handling code here:
        cargarcategorias();
        txtbuscarproveedor.setText("");

    }//GEN-LAST:event_BtnlistarActionPerformed

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
            java.util.logging.Logger.getLogger(tabla_categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tabla_categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tabla_categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tabla_categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tabla_categoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btnlistar;
    public javax.swing.JButton btnEliminarproveedor;
    private javax.swing.JButton btnbuscarproveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablacategorias;
    private javax.swing.JTextField txtbuscarproveedor;
    public javax.swing.JTextField txtcategoria;
    public javax.swing.JTextField txtidcategoria;
    // End of variables declaration//GEN-END:variables
}
