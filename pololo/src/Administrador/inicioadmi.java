/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador;

import com.itextpdf.text.BaseColor;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAW
 */
public class inicioadmi extends javax.swing.JFrame {
   
    public static final String url="jdbc:mysql://localhost:3306/muebles";
    public static final String usuario="root";
    public static final String clave="";
    private static final long serialVersionUID = 1L;
    PreparedStatement pt;
    ResultSet rs;
    public static Connection getConnection(){
    Connection cone=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cone=DriverManager.getConnection(url,usuario,clave);
            System.out.println("conecion exitosa");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(" error en la conexion "+e);
            JOptionPane.showMessageDialog(null, "error en la conexion");
        }
    return cone;
    
    }
    private void limpliarCaja(){//metodo para limpiar las cajas de texto
txtCedula.setText(null);
 txtNombre.setText(null); 
 txtApellido.setText(null);
  txtEmail.setText(null);
  txtTelefono.setText(null);
 txtDireccion.setText(null);
 textbuscar.setText(null);
    
    }
    
    
    private void limpiarcompra(){//metodo para limpiar las cajas de texto
        
         txtrut.setText(null);
                txtProductoC.setText(null);
                txtproveedor.setText(null);
                txtFechaC.setText(null);
              txtcosto.setText(null);
              txtcantidadcomp.setText(null);
              txtBuscarcompra.setText(null);
        
    }
   
    private void limpiarventas(){//metodo para limpiar las cajas de texto
    
    txtVenta.setText(null);
       txtFecha.setText(null);
      txtValor.setText(null);
       txtId_producto.setText(null);
       txtCliente.setText(null);
       txtUsuario.setText(null);
       txtbuscarventas.setText(null);
           
    }
    
     private void limpiarproductos(){//metodo para limpiar las cajas de texto
         
               txtIdproducto.setText(null);                
               txtnombre.setText(null);
               txtcategoria.setText(null);
               txtcantidad.setText(null);
               txtbuscarproducto.setText(null);
                
    
     }
    
       private void cargartabla(){//metodo para cargar la tabla
     DefaultTableModel modelo = (DefaultTableModel) tablacliente.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone=null;
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT cedula,nombre,apellidos,telefono,direccion,email FROM cliente");
              rs=pt.executeQuery();
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
       
   /************************************************************************************************************/
       
       private void cargartablaindividual(){//metodo para cargar solo la celda selecionda en la tabla
     DefaultTableModel modelo = (DefaultTableModel) tablacliente.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone=null;
         String buscar = textbuscar.getText();
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT cedula,nombre,apellidos,telefono,direccion,email FROM cliente where cedula ='"+buscar+"'");
              rs=pt.executeQuery();
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
   /*****************************************************FIN PRUEBA*****************************************************/
          
       
       
       
      private void cargartablaCompra(){//metodo para cargar la tabla
     DefaultTableModel modelo = (DefaultTableModel) tablaCompra.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone=null;
         
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `idCompra`, `nonbre`, `idProveedor`, `fecha`, `costo`, `cantidad`, `total` FROM `compra` ");
              rs=pt.executeQuery();
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
         
          private void cargartablaCompraindividual(){//metodo para cargar solo la celda selecionda en la tabla
            DefaultTableModel modelo = (DefaultTableModel) tablaCompra.getModel();
           modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone=null;
        String buscarcompra = txtBuscarcompra.getText();
         
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `idCompra`, `nonbre`, `idProveedor`, `fecha`, `costo`, `cantidad`, `total` FROM `compra` where `idCompra` = '"+buscarcompra+"' ");
              rs=pt.executeQuery();
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
     
    //***********************************************************************************************************************************************************
    }
          
          //***************************************************************************
        private void cargartablaventa(){//metodo para cargar la tabla
     DefaultTableModel modelo = (DefaultTableModel) tablaventas.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone=null;
         
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_venta`, `fecha`, `monto`, `id_producto`, `cedula_cliente`, `id_usuario` FROM `venta`");
              rs=pt.executeQuery();
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
          private void cargartablaventaindividual(){//metodo para cargar solo la celda selecionda en la tabla
     DefaultTableModel modelo = (DefaultTableModel) tablaventas.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone;
         String buscarventas = txtbuscarventas.getText();
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_venta`, `fecha`, `monto`, `id_producto`, `cedula_cliente`, `id_usuario` FROM `venta` where `id_venta` = '"+buscarventas+"'");
              rs=pt.executeQuery();
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
              
        
        
        // *************************************************************************************************************
        private void cargartablaproducto(){//metodo para cargar la tabla
     DefaultTableModel modelo = (DefaultTableModel) tablaproducto.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone=null;
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_producto`, `nombre`, `id_categorias`, `cantida` FROM `producto`");
              rs=pt.executeQuery();
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
        
         private void cargartablaproductoindividual(){//metodo para cargar solo la fila selecionda en la tabla
                     DefaultTableModel modelo = (DefaultTableModel) tablaproducto.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;//trae los meta datos de la tabla de las columna
         int columna;//guarda un el numero de las columnas en la base de datos
         Connection cone=null;
       String buscarproducto = txtbuscarproducto.getText();
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_producto`, `nombre`, `id_categorias`, `cantida` FROM `producto` where `id_producto` ='"+buscarproducto+"' ");
              rs=pt.executeQuery();
              rsmd=rs.getMetaData();
              columna=rsmd.getColumnCount();
              
              while(rs.next()){// repetir una acción en un bucle siempre y cuando traiga un objeto dando incio a un 'for' con los valores de los datos en la BD
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
         //************************************************************************************************************************
         
         
                    private void cargarcategorias(){
     DefaultTableModel modelo = (DefaultTableModel) tablacategorias.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone=null;
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_categoría`, `nombre` FROM `categoría` ");
         ResultSet rss = pt.executeQuery();
              rsmd=rss.getMetaData();
              columna=rsmd.getColumnCount();
              
              while(rss.next()){
                Object[] fila= new Object[columna];
                for(int indice=0;indice<columna;indice++){
                    fila[indice]=rss.getObject(indice+1);
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
         Connection cone;
    String dato = txtbuscarproveedor.getText();
         try {
             
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_categoría`, `nombre` FROM `categoría`  where id_categoría ='"+dato+"' ");
         ResultSet rsl = pt.executeQuery();
              rsmd=rsl.getMetaData();
              columna=rsmd.getColumnCount();
              
              while(rsl.next()){
                Object[] fila= new Object[columna];
                for(int indice=0;indice<columna;indice++){
                    fila[indice]=rsl.getObject(indice+1);
                }
                modelo.addRow(fila);
              }
         } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, e.toString());
         }
     
    
    }
         
         
         //**************************************************************************************************************************
        
             
        
                
        //**************************************************************************************************************
         
        private void totalizar (){//metodo para mostrar la suma de una columna de una tabla
            
            int t= 0;
            double p = 0;
            if (tablaCompra.getRowCount() > 0){
              for (int i=0; i<tablaCompra.getRowCount();i++){
                  
                  p = Double.parseDouble(tablaCompra.getValueAt(i,6).toString());
                  t +=p;
                  
              }
                jLabel32.setText("El total es : $" + t);
            }
        
               
        }
        
         
         
         public inicioadmi() {//constructor 
        initComponents();
       
       // this.setLocationRelativeTo(null);*/
       
        this.setSize(1300,750);
        this.cargartablaventa();
        this.cargartablaproducto();
        this.cargartablaCompra();
        cargartabla();
        //this.setSize(1272,720);
      this.infor.setVisible(false);
      this.setResizable(false);
      this.setLocationRelativeTo(null);
      this.totalizar();
         
       
      
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

        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        infor = new javax.swing.JTextField();
        txtidcategoria = new javax.swing.JTextField();
        PanelPrincipal = new javax.swing.JTabbedPane();
        PanelCliente = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textbuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnbuscarcliente = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnModificarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnGuardarCiente = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablacliente = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        Btnlistar = new javax.swing.JButton();
        PanelVentas = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtVenta = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        txtId_producto = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        btnEliminarventa = new javax.swing.JButton();
        btnModificarventa = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnGuardaventa = new javax.swing.JButton();
        txtbuscarventas = new javax.swing.JTextField();
        buscarventa = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaventas = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        listar = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        producto = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtbuscarproducto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtIdproducto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        btpGuardarproducto = new javax.swing.JButton();
        btnModificarproducto = new javax.swing.JButton();
        btnEliminarproducto = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtcategoria = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaproducto = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablacategorias = new javax.swing.JTable();
        btnbuscarproveedor = new javax.swing.JButton();
        txtbuscarproveedor = new javax.swing.JTextField();
        btnEliminarproveedor = new javax.swing.JButton();
        Btnlistar1 = new javax.swing.JButton();
        Buscarproducto = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        listar1 = new javax.swing.JButton();
        PanelProductos = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txtrut = new javax.swing.JTextField();
        txtProductoC = new javax.swing.JTextField();
        txtproveedor = new javax.swing.JTextField();
        txtFechaC = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtcosto = new javax.swing.JTextField();
        btnGuadarCompra = new javax.swing.JButton();
        btnActualizarCompra = new javax.swing.JButton();
        btnEliminarCompra = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtcantidadcomp = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        btnBuscarCompra = new javax.swing.JButton();
        txtBuscarcompra = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCompra = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        listar2 = new javax.swing.JButton();
        textocargo = new javax.swing.JLabel();
        textoadmin = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu4.setText("jMenu4");

        jMenu5.setText("jMenu5");

        jMenuItem3.setText("jMenuItem3");

        jMenu6.setText("jMenu6");

        jMenu8.setText("jMenu8");

        jMenuItem8.setText("jMenuItem8");

        infor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inforActionPerformed(evt);
            }
        });

        txtidcategoria.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(null);

        PanelPrincipal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanelPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        PanelCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        jLabel1.setText("Clientes");

        textbuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnbuscarcliente.setBackground(new java.awt.Color(255, 255, 255));
        btnbuscarcliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnbuscarcliente.setText("Buscar");
        btnbuscarcliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbuscarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarclienteActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("Cedula");

        txtCedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setText("Nombre");

        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel5.setText("E-mail");

        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setText("Telefono");

        txtTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnModificarCliente.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnModificarCliente.setText("Actualizar");
        btnModificarCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnModificarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnEliminarCliente.setText("Eliminar");
        btnEliminarCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnGuardarCiente.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnGuardarCiente.setText("Guardar");
        btnGuardarCiente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGuardarCiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCienteActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel13.setText("Apellidos");

        txtApellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtDireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel7.setText("Direccion");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(8, 8, 8)))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre)
                            .addComponent(txtTelefono)
                            .addComponent(txtCedula)
                            .addComponent(txtApellido)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnGuardarCiente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(btnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarCiente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablacliente.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tablacliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "cedula", "nombre", "apellidos", "telefono", "direccion", "email"
            }
        ));
        tablacliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablacliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaclienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablacliente);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 255));
        jLabel28.setText("Listado de Clientes");

        Btnlistar.setBackground(new java.awt.Color(255, 255, 0));
        Btnlistar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btnlistar.setText("Lista completa");
        Btnlistar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnlistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnlistarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(65, 65, 65)
                        .addComponent(textbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbuscarcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(Btnlistar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Btnlistar)
                        .addComponent(textbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnbuscarcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel28)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelClienteLayout = new javax.swing.GroupLayout(PanelCliente);
        PanelCliente.setLayout(PanelClienteLayout);
        PanelClienteLayout.setHorizontalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClienteLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        PanelClienteLayout.setVerticalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelPrincipal.addTab("Cliente", PanelCliente);

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel21.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel21.setText("N° venta");

        jLabel22.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel22.setText("Fecha");

        jLabel23.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel23.setText("N° producto");

        jLabel24.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel24.setText("valor");

        jLabel25.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel25.setText("Cliente");

        txtVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtFecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtValor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });

        txtId_producto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnEliminarventa.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnEliminarventa.setText("Eliminar");
        btnEliminarventa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEliminarventa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarventaActionPerformed(evt);
            }
        });

        btnModificarventa.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnModificarventa.setText("Actualizar");
        btnModificarventa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnModificarventa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarventaActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel18.setText("N° Empleado");

        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnGuardaventa.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnGuardaventa.setText("Guardar");
        btnGuardaventa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGuardaventa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardaventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardaventaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel18)
                    .addComponent(jLabel21))
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnModificarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btnGuardaventa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnEliminarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(28, 28, 28)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(txtId_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardaventa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        buscarventa.setBackground(new java.awt.Color(255, 255, 255));
        buscarventa.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        buscarventa.setText("Buscar");
        buscarventa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buscarventa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buscarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarventaActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablaventas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tablaventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "N° venta", "fecha", "valor", "Producto", "Cedula cliente", "Cedula empleado"
            }
        ));
        tablaventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaventasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaventas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel26.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel26.setText("N° venta");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 255));
        jLabel29.setText("Listado de Ventas");

        listar.setBackground(new java.awt.Color(255, 255, 0));
        listar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listar.setText("Lista completa");
        listar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarActionPerformed(evt);
            }
        });

        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Exportacion a pdf");

        jButton2.setText("Individual");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("General");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addGap(29, 29, 29))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26)
                        .addGap(17, 17, 17)
                        .addComponent(txtbuscarventas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(listar)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtbuscarventas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buscarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(listar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29))))
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 71, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelVentasLayout = new javax.swing.GroupLayout(PanelVentas);
        PanelVentas.setLayout(PanelVentasLayout);
        PanelVentasLayout.setHorizontalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        PanelVentasLayout.setVerticalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        PanelPrincipal.addTab("Ventas", PanelVentas);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtbuscarproducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel11.setText("N° producto");

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setText("N° producto");

        txtIdproducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel10.setText("Nombre");

        txtnombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel12.setText("Cantidad");

        txtcantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btpGuardarproducto.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btpGuardarproducto.setText("Guardar");
        btpGuardarproducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btpGuardarproducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btpGuardarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btpGuardarproductoActionPerformed(evt);
            }
        });

        btnModificarproducto.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnModificarproducto.setText("Actualizar");
        btnModificarproducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnModificarproducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarproductoActionPerformed(evt);
            }
        });

        btnEliminarproducto.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnEliminarproducto.setText("Eliminar");
        btnEliminarproducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEliminarproducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarproductoActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel20.setText("Categotiria");

        txtcategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(btnModificarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel20)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btpGuardarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnEliminarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtIdproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(66, 66, 66)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btpGuardarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablaproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N° Producto", "Nombre", "categoria", "cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaproducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaproducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaproductoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaproducto);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setText("Listado de Categorias");

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
        jScrollPane5.setViewportView(tablacategorias);

        btnbuscarproveedor.setBackground(new java.awt.Color(255, 255, 255));
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

        Btnlistar1.setBackground(new java.awt.Color(255, 255, 0));
        Btnlistar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btnlistar1.setText("Lista completa");
        Btnlistar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnlistar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btnlistar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminarproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Btnlistar1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbuscarproveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(txtbuscarproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btnlistar1)
                    .addComponent(btnbuscarproveedor)
                    .addComponent(txtbuscarproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(btnEliminarproveedor)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(218, 218, 218))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(261, 261, 261))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Buscarproducto.setBackground(new java.awt.Color(255, 255, 255));
        Buscarproducto.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        Buscarproducto.setText("Buscar");
        Buscarproducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Buscarproducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Buscarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarproductoActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 255));
        jLabel30.setText("Listado de Productos");

        listar1.setBackground(new java.awt.Color(255, 255, 0));
        listar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listar1.setText("Lista completa");
        listar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(txtbuscarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Buscarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel30)
                        .addGap(167, 167, 167)
                        .addComponent(listar1)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Buscarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(listar1)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout productoLayout = new javax.swing.GroupLayout(producto);
        producto.setLayout(productoLayout);
        productoLayout.setHorizontalGroup(
            productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        productoLayout.setVerticalGroup(
            productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );

        PanelPrincipal.addTab("Productos", producto);

        PanelProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null));
        PanelProductos.setLayout(null);

        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setMinimumSize(new java.awt.Dimension(1400, 530));

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtrut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtProductoC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtproveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproveedorActionPerformed(evt);
            }
        });

        txtFechaC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel15.setText("Nombre");

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel16.setText("N° proveedor");

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel17.setText("Fecha");

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel19.setText("Precio");

        txtcosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtcosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcostoActionPerformed(evt);
            }
        });

        btnGuadarCompra.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnGuadarCompra.setText("Guadar");
        btnGuadarCompra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGuadarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuadarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuadarCompraActionPerformed(evt);
            }
        });

        btnActualizarCompra.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnActualizarCompra.setText("Actualizar");
        btnActualizarCompra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnActualizarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCompraActionPerformed(evt);
            }
        });

        btnEliminarCompra.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnEliminarCompra.setText("Eliminar");
        btnEliminarCompra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEliminarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCompraActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Cantidad");

        txtcantidadcomp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtcantidadcomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidadcompActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel27.setText("N° Compra");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnActualizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(btnGuadarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnEliminarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel15)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaC)
                            .addComponent(txtproveedor)
                            .addComponent(txtProductoC, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtrut)
                            .addComponent(txtcosto)
                            .addComponent(txtcantidadcomp))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtrut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtProductoC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFechaC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtcosto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtcantidadcomp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuadarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        btnBuscarCompra.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarCompra.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnBuscarCompra.setText("Buscar");
        btnBuscarCompra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnBuscarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCompraActionPerformed(evt);
            }
        });

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablaCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N° compra", "Nombre", "N° proveedor", "Fecha", "Valor unitario", "Cantidad", "Subtotal"
            }
        ));
        tablaCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCompraMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaCompra);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setText("Total");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel32)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addGap(69, 69, 69))
        );

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel14.setText("N° Compra");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 255));
        jLabel31.setText("Listado de Compras");

        listar2.setBackground(new java.awt.Color(255, 255, 0));
        listar2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listar2.setText("Lista completa");
        listar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(330, 330, 330)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(listar2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 203, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBuscarcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(btnBuscarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel31)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(listar2)))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        PanelProductos.add(jPanel12);
        jPanel12.setBounds(16, 10, 1220, 550);

        PanelPrincipal.addTab("Compras", PanelProductos);

        getContentPane().add(PanelPrincipal);
        PanelPrincipal.setBounds(10, 50, 1260, 600);

        textocargo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        textocargo.setText("Nombre del administrador:");
        getContentPane().add(textocargo);
        textocargo.setBounds(360, 0, 260, 40);

        textoadmin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        getContentPane().add(textoadmin);
        textoadmin.setBounds(630, 10, 330, 40);

        btnSalir.setBackground(new java.awt.Color(255, 0, 51));
        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Cerrar Sesion");
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir);
        btnSalir.setBounds(1140, 10, 120, 30);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Monterosoft Copyright © 2022 todos los derechos reservados");
        getContentPane().add(jLabel35);
        jLabel35.setBounds(510, 670, 390, 15);

        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu3.setText("Proveedores  ");
        jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem4.setText("Registrar");
        jMenuItem4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem10.setText("Tabla");
        jMenuItem10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuBar1.add(jMenu3);

        jMenu7.setText("Usuario");
        jMenu7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem5.setText("Registrar");
        jMenuItem5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem5);

        jMenuItem9.setText("Tabla");
        jMenuItem9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem9);

        jMenuBar1.add(jMenu7);

        jMenu9.setText(" Empleado   ");
        jMenu9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem6.setText("Registrar");
        jMenuItem6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem6);

        jMenuItem7.setText("Tabla");
        jMenuItem7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem7MouseClicked(evt);
            }
        });
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem7);

        jMenuBar1.add(jMenu9);

        jMenu2.setText("Categoria    ");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem1.setText("Registrar");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Tabla");
        jMenuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btpGuardarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btpGuardarproductoActionPerformed
        Connection con=null;
        try {
            con=getConnection();
            pt=con.prepareStatement("INSERT INTO `producto`(`id_producto`, `nombre`, `id_categorias`, `cantida`) VALUES(?,?,?,?)");
            pt.setString(1,txtIdproducto.getText());
            pt.setString(2,txtnombre.getText());
             pt.setString(3,txtcategoria.getText());
             
               pt.setString(4,txtcantidad.getText());
               
               int enter=pt.executeUpdate();
               if (enter > 0) {
                   JOptionPane.showMessageDialog(null, "Producto guardado");
               cargartablaproducto();
               limpiarproductos();
            } else {
               JOptionPane.showMessageDialog(null, "error en guardar");
             
            }
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btpGuardarActionPerformed()"+e);
            JOptionPane.showMessageDialog(null, "Registro no guardado verifique sus datos ");
        }
    }//GEN-LAST:event_btpGuardarproductoActionPerformed

    private void btnActualizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCompraActionPerformed
        
         Connection con=null;
         String RUT=txtrut.getText();
       String Nombre=txtProductoC.getText();
       String proveedor= txtproveedor.getText();
       String fecha=txtFechaC.getText() ;
       
          double costo= Double.parseDouble(txtcosto.getText());
       int cantidad=Integer.parseInt(txtcantidadcomp.getText());
       double total= costo * cantidad;
       String valor=String.valueOf(total);
       infor.setText(valor);
       
       
        try {
            con=getConnection();
            pt=con.prepareStatement("UPDATE `compra` SET `nonbre` = ?, `idProveedor` = ?, `fecha` = ?, `costo` =? , `cantidad` = ?, total = ? WHERE `idCompra` = ?;");
             
           pt.setString(7, RUT);
            pt.setString(1, Nombre);
            pt.setString(2, proveedor);
            pt.setString(3,  fecha);           
            pt.setString(4,txtcosto.getText());
            pt.setString(5,  txtcantidadcomp.getText());
           pt.setString(6, infor.getText());
            
            
               int enter=pt.executeUpdate();
               if (enter > 0) {
                   JOptionPane.showMessageDialog(null, "Actualizacion existosa");
                 cargartablaCompra();
               limpiarcompra();
               totalizar();
            } else {
               JOptionPane.showMessageDialog(null, "Error...Verifique sus datos");
              
            
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error...Verifique sus datos");
            System.out.println("monterosoft.inicio.btpGuardarActionPerformed()"+e);
        }
        
    }//GEN-LAST:event_btnActualizarCompraActionPerformed

    private void btnEliminarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCompraActionPerformed
// TODO add your handling code here:eliminar


    int confirmacion = JOptionPane.showConfirmDialog(PanelCliente, "Desea eliminar esta compra", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);

    if (confirmacion ==JOptionPane.YES_OPTION){
         Connection con=null;
        try {
            con=getConnection();
            pt=con.prepareStatement( "DELETE FROM `compra` WHERE `idCompra` = ?");
            
            pt.setString(1,txtrut.getText());
               int enter=pt.executeUpdate();
               if (enter > 0) {
                   JOptionPane.showMessageDialog(null, "Compra eliminada");
                cargartablaCompra();
                limpiarcompra();
                totalizar();
            } else {
               JOptionPane.showMessageDialog(null, "No hay datos seleccionado");
              
              limpiarcompra();
            }
        } catch (SQLException | HeadlessException e) {
            System.out.println("Error en eliminar compra");
            System.out.println("monterosoft.inicio.btpGuardarActionPerformed()"+e);
        }
        
        
        
   
    }
                // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarCompraActionPerformed

    private void btnEliminarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarventaActionPerformed
        // TODO add your handling code here:
        int confirmacion = JOptionPane.showConfirmDialog(PanelCliente, "Desea eliminar esta venta", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);

    if (confirmacion ==JOptionPane.YES_OPTION){

        
        Connection con=null;
        try {
            con=getConnection();
            pt=con.prepareStatement("DELETE  FROM venta WHERE id_venta=?");
            pt.setString(1,txtVenta.getText());
               int enter=pt.executeUpdate();
               if (enter > 0) {
                   JOptionPane.showMessageDialog(null, "Venta Eliminada ");
                cargartablaventa();
                limpiarventas();
                
            } else {
               JOptionPane.showMessageDialog(null, "error en  Eliminar");
              
              
            }
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btpGuardarActionPerformed()"+e);
        }
        
    }
        
                // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarventaActionPerformed

    private void btnModificarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarventaActionPerformed
        // TODO add your handling code here:
        Connection con =null;
        String id=txtVenta.getText();
       String fecha=txtFecha.getText();
       String productos= txtId_producto.getText();
       String cliente=txtCliente.getText() ;
       String usuarios=txtUsuario.getText() ;
        String valor=txtValor.getText() ;
       
        try {
            con=getConnection();
            pt=con.prepareStatement("UPDATE `venta` SET `fecha` = ?, `monto` = ?, `id_producto` = ?,cedula_cliente = ?, `id_usuario` = ? WHERE `id_venta` = ?");
             
          
             pt.setString(1,fecha);
             pt.setString(2,valor);
             pt.setString(3,productos);
             pt.setString(4,cliente);
             pt.setString(5, usuarios);
             pt.setString(6,id);
            
               int enter=pt.executeUpdate();
               if (enter > 0) {
                   JOptionPane.showMessageDialog(null, "Actualizacion exitosa");
                cargartablaventa();
                limpiarventas();
               
            } else {
               JOptionPane.showMessageDialog(null, "Error...Verifique sus datos");
              
              
            }
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btpGuardarActionPerformed()"+e);
        }
    }//GEN-LAST:event_btnModificarventaActionPerformed

    private void btnModificarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarproductoActionPerformed
        // TODO add your handling code here:
         Connection con=null;
        try {
            con=getConnection();
            pt=con.prepareStatement("UPDATE `producto` SET `nombre`=?,`id_categorias`=?,`cantida`=? WHERE `id_producto`=?");
            pt.setString(4,txtIdproducto.getText());
            pt.setString(1,txtnombre.getText());
             pt.setString(2,txtcategoria.getText());
             
               pt.setString(3,txtcantidad.getText());
               int enter=pt.executeUpdate();
               if (enter > 0) {
                   JOptionPane.showMessageDialog(null, "Actualizacion exitosa");
                cargartablaproducto();
                limpiarproductos();
            } else {
               JOptionPane.showMessageDialog(null, "Error en actualizar");
              
              
            }
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btpGuardarActionPerformed()"+e);
            JOptionPane.showMessageDialog(null, "Datos no actualizados... Verifique los datos");
        }
        
    }//GEN-LAST:event_btnModificarproductoActionPerformed

    private void btnEliminarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarproductoActionPerformed
      int confirmacion = JOptionPane.showConfirmDialog(PanelCliente, "Desea eliminar este producto", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

    if (confirmacion ==JOptionPane.YES_OPTION){


        
        Connection con=null;
        try {
            con=getConnection();
            pt=con.prepareStatement("DELETE  FROM producto WHERE id_producto=?");
            pt.setString(1,txtIdproducto.getText());
               int enter=pt.executeUpdate();
               if (enter > 0) {
                   JOptionPane.showMessageDialog(null, "Producto Eliminado ");
               cargartablaproducto();
               limpiarproductos();
            } else {
               JOptionPane.showMessageDialog(null, "No hay datos seleccionados");
             
            }
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btpGuardarActionPerformed()"+e);
            JOptionPane.showMessageDialog(PanelCliente,"Este producto no se puede borrar, esta asociado en una venta ");
            
        }
        

    }
        
    }//GEN-LAST:event_btnEliminarproductoActionPerformed

    private void btnGuadarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuadarCompraActionPerformed
       Connection cone=null; 
       String RUT=txtrut.getText();
       String Nombre=txtProductoC.getText();
       String direccion= txtproveedor.getText();
       String fecha=txtFechaC.getText() ;
          String costo=txtcosto.getText();
       String cantidad=txtcantidadcomp.getText();
       double precio= Double.parseDouble(txtcosto.getText());
       int cantid=Integer.parseInt(txtcantidadcomp.getText());
       double total= precio * cantid;
       String gota=String.valueOf(total);
       infor.setText(gota);
        try {
            cone=getConnection();
            pt=cone.prepareStatement("INSERT INTO `compra` (`idCompra`, `nonbre`, `idProveedor`, `fecha`, `costo`, `cantidad`, `total`) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pt.setString(1, RUT);
            pt.setString(2, Nombre);
            pt.setString(3, direccion);
            pt.setString(4,  fecha);
            pt.setString(5,costo);
            pt.setString(6,  cantidad);
           pt.setString(7, infor.getText());
            int enter=pt.executeUpdate();
             if(enter>0){
                 cargartablaCompra();
                 JOptionPane.showMessageDialog(null, "guardado exitoso");
                 limpiarcompra();
                
        totalizar();
                }else{
            
               JOptionPane.showMessageDialog(null, "Registro no guardado verifique sus datos ");
            } 
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btnGuadarCompraActionPerformed()"+e);  
            JOptionPane.showMessageDialog(null, "Registro no guardado verifique sus datos ");
        }
        //****************************************************************************************************
           
    }//GEN-LAST:event_btnGuadarCompraActionPerformed

    private void txtproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproveedorActionPerformed

    private void btnBuscarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCompraActionPerformed
       Connection cone = null;
        try {
            cone = getConnection();
            pt=cone.prepareStatement("Select * from compra where idCompra=?");
            pt.setString(1, txtBuscarcompra.getText());
            rs=pt.executeQuery();
            
            if (rs.next()) {
                txtrut.setText(rs.getString("idCompra"));
                txtProductoC.setText(rs.getString("nonbre"));
                txtproveedor.setText(rs.getString("idProveedor"));
                txtFechaC.setText(rs.getString("fecha"));
              txtcosto.setText(rs.getString("costo"));
              txtcantidadcomp.setText(rs.getString("cantidad"));
              cargartablaCompraindividual();
               
            } else {
                 JOptionPane.showMessageDialog(null, "Esta compra NO esta Registrada ");
            }
            
            
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btnbuscarActionPerformed()"+e);
        }
        
        
    }//GEN-LAST:event_btnBuscarCompraActionPerformed

    private void btnGuardaventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardaventaActionPerformed
        // TODO add your handling code here:
         Connection cone; 
       String id=txtVenta.getText();
       String fecha=txtFecha.getText();
        String valor=txtValor.getText() ;
       String Productos= txtId_producto.getText();
       String cliente=txtCliente.getText() ;
       String Usuarios=txtUsuario.getText() ;
       
       
       
        try {
            cone=getConnection();
            pt=cone.prepareStatement("INSERT INTO `venta`(`id_venta`, `fecha`, `monto`, `id_producto`, `cedula_cliente`, `id_usuario`) VALUES (?, ?, ?, ?, ?, ?)");
            
            pt.setString(1,id);
             pt.setString(2,fecha);
             pt.setString(3,valor);
             pt.setString(4,Productos);
             pt.setString(5,cliente);
             pt.setString(6, Usuarios);
             int enter=pt.executeUpdate();
             System.out.println(enter);
             if(enter>0){
             JOptionPane.showMessageDialog(null, "Registro exitoso");
             
             cargartablaventa();
             limpiarventas();
             }
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.jButton4ActionPerformed()"+e);
             JOptionPane.showMessageDialog(null, "Registro no guardado verifique sus datos ");
        }
    }//GEN-LAST:event_btnGuardaventaActionPerformed

    private void buscarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarventaActionPerformed
 Connection cone = null;
        try {
            cone = getConnection();
            pt=cone.prepareStatement("Select * from venta where id_venta=?");
            pt.setString(1, txtbuscarventas.getText());
            rs=pt.executeQuery();
            
            if (rs.next()) {
                
                txtVenta.setText(rs.getString("id_venta"));
      txtFecha.setText(rs.getString("fecha"));
       txtValor.setText(rs.getString("monto")) ;
       txtId_producto.setText(rs.getString("id_producto"));
      txtCliente.setText(rs.getString("cedula_cliente")) ;
       txtUsuario.setText(rs.getString("id_usuario")) ;
       cargartablaventaindividual();
               
       
               
            } else {
                JOptionPane.showMessageDialog(null, "Esta venta NO esta registrada ");
            }
            
            
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btnbuscarActionPerformed()"+e);
           
        
        
        }    }//GEN-LAST:event_buscarventaActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void BuscarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarproductoActionPerformed
        // TODO add your handling code here:
        
                 Connection cone ;
        try {
            cone = getConnection();
            pt=cone.prepareStatement("SELECT * FROM `producto` WHERE id_producto= ?");
            pt.setString(1, txtbuscarproducto.getText());
            rs=pt.executeQuery();
            
            if (rs.next()) {
                
            txtIdproducto.setText(rs.getString("id_producto"));
            txtnombre.setText(rs.getString("nombre"));
            txtcategoria.setText(rs.getString("id_categorias"));
            txtcantidad.setText(rs.getString("cantida"));
            cargartablaproductoindividual();
               
            } else {
                 JOptionPane.showMessageDialog(null, "Este producto NO esta registrado ");
                 
            }
            
            
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btnbuscarActionPerformed()"+e);
        }
        
        
    }//GEN-LAST:event_BuscarproductoActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        usuarios user = new usuarios();
        user.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void tablaCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCompraMouseClicked
        // TODO add your handling code here:
         Connection con;
        tablaCompra.setSelectionBackground(Color.BLUE);
        tablaCompra.setSelectionForeground(Color.WHITE);
         
        try {
            int fila = tablaCompra.getSelectedRow();
            int id =Integer.parseInt(tablaCompra.getValueAt(fila, 0).toString());
            con=getConnection();
              pt=con.prepareStatement("SELECT `idCompra`, `nonbre`, `idProveedor`, `fecha`, `costo`, `cantidad` FROM `compra` WHERE `idCompra`=?");
             pt.setInt(1, id);
              rs=pt.executeQuery();
              while(rs.next()){
             txtrut.setText(String.valueOf(id));
                
                txtProductoC.setText(rs.getString("nonbre"));
                txtproveedor.setText(rs.getString("idProveedor"));
                txtFechaC.setText(rs.getString("fecha"));
              txtcosto.setText(rs.getString("costo"));
             txtcantidadcomp.setText(rs.getString("cantidad"));
              
              }
        } catch (NumberFormatException | SQLException e) {
        }
    }//GEN-LAST:event_tablaCompraMouseClicked

    private void tablaventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaventasMouseClicked
        // TODO add your handling code here:
        
          Connection cone; 
           tablaventas.setSelectionBackground(Color.BLUE);
            tablaventas.setSelectionForeground(Color.WHITE);
        try {
            int fila = tablaventas.getSelectedRow();
            int id =Integer.parseInt(tablaventas.getValueAt(fila, 0).toString());
            cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_venta`, `fecha`, `monto`, `id_producto`, `cedula_cliente`, `id_usuario` FROM `venta` WHERE `id_venta`=?");
             pt.setInt(1, id);
              rs=pt.executeQuery();
              while(rs.next()){
             txtVenta.setText(String.valueOf(id));
                
               txtFecha.setText(rs.getString("fecha"));
               txtValor.setText(rs.getString("monto"));
                txtId_producto.setText(rs.getString("id_producto"));
                txtCliente.setText(rs.getString("cedula_cliente"));
              txtUsuario.setText(rs.getString("id_usuario"));
            
              
              }
        } catch (NumberFormatException | SQLException e) {
        }
        
        
               
        
        
    }//GEN-LAST:event_tablaventasMouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        
         empleado empleo = new empleado();
        empleo.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
         proveedores provee = new proveedores();
        provee.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        tabla tab= new tabla();
        tab.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void txtcantidadcompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidadcompActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadcompActionPerformed

    private void txtcostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcostoActionPerformed

    private void tablaproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaproductoMouseClicked
        // TODO add your handling code here:
        
        
          Connection cone; 
          
          tablaproducto.setSelectionBackground(Color.BLUE);
           tablaproducto.setSelectionForeground(Color.WHITE);
        try {
            int fila = tablaproducto.getSelectedRow();
            int id =Integer.parseInt(tablaproducto.getValueAt(fila, 0).toString());
            cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_producto`, `nombre`, `id_categorias`, `cantida` FROM `producto` WHERE `id_producto`=?");
             pt.setInt(1, id);
              rs=pt.executeQuery();
              while(rs.next()){
             txtIdproducto.setText(String.valueOf(id));
                
               txtnombre.setText(rs.getString("nombre"));
               txtcategoria.setText(rs.getString("id_categorias"));
               txtcantidad.setText(rs.getString("cantida"));
                
              }
        } catch (NumberFormatException | SQLException e) {
        }
        
        
        
        
        
        
    }//GEN-LAST:event_tablaproductoMouseClicked

    private void jMenuItem7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem7MouseClicked
        // TODO add your handling code here:
        tabla_empleado table = new tabla_empleado();
        table.setVisible(true);
    }//GEN-LAST:event_jMenuItem7MouseClicked

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
         tabla_empleado table = new tabla_empleado();
        table.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void listarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarActionPerformed
        // TODO add your handling code here:
        
        cargartablaventa();
        limpiarventas();
      
        
        
    }//GEN-LAST:event_listarActionPerformed

    private void listar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listar1ActionPerformed
        // TODO add your handling code here:
        cargartablaproducto();
        limpiarproductos();
         
    }//GEN-LAST:event_listar1ActionPerformed

    private void listar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listar2ActionPerformed
        // TODO add your handling code here:
       cargartablaCompra();
     limpiarcompra();
        
                
    }//GEN-LAST:event_listar2ActionPerformed

    private void BtnlistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnlistarActionPerformed
        // TODO add your handling code here:
        cargartabla();
        limpliarCaja();
        
    }//GEN-LAST:event_BtnlistarActionPerformed

    private void tablaclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaclienteMouseClicked
        Connection con;

       tablacliente.setSelectionBackground(Color.BLUE);
            tablacliente.setSelectionForeground(Color.WHITE);
        try {
            int fila = tablacliente.getSelectedRow();
            int id =Integer.parseInt(tablacliente.getValueAt(fila, 0).toString());
            con=getConnection();
            pt=con.prepareStatement("SELECT cedula,nombre,apellidos,telefono,direccion,email FROM cliente where cedula=?");
            pt.setInt(1, id);
            rs=pt.executeQuery();
            while(rs.next()){
                txtCedula.setText(String.valueOf(id));
                txtNombre.setText(rs.getString("nombre"));
                txtApellido.setText(rs.getString("apellidos"));
                txtTelefono.setText(rs.getString("telefono"));
                txtDireccion.setText(rs.getString("direccion"));
                txtEmail.setText(rs.getString("email"));

            }
        } catch (NumberFormatException | SQLException e) {
        
        }
    }//GEN-LAST:event_tablaclienteMouseClicked

    private void btnGuardarCienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCienteActionPerformed
        Connection con;
        try {
            con=getConnection();
            pt=con.prepareStatement("INSERT INTO `cliente` (`cedula`, `nombre`, `apellidos`,`telefono`, `direccion`, `email`) VALUES (?,?,?,?,?,?)");

            pt.setString(1,txtCedula.getText());
            pt.setString(2,txtNombre.getText());
            pt.setString(3,txtApellido.getText());
            pt.setString(4,txtTelefono.getText());
            pt.setString(5, txtDireccion.getText());
            pt.setString(6,txtEmail.getText());

            int enter=pt.executeUpdate();
            if (enter > 0) {

                JOptionPane.showMessageDialog(null, "guardado exitoso");
                cargartabla();
                limpliarCaja();
            } else {
                JOptionPane.showMessageDialog(null, "error en guardar");

                limpliarCaja();
            }
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btnGuardarActionPerformed()"+e);
            JOptionPane.showMessageDialog(null, "Registro no guardado verifique sus datos ");
        }
    }//GEN-LAST:event_btnGuardarCienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        // TODO add your handling code here:eliminar
        int confirmacion = JOptionPane.showConfirmDialog(PanelCliente, "Desea eliminar este cliente", "confirmar eliminación", JOptionPane.YES_NO_OPTION);

    if (confirmacion ==JOptionPane.YES_OPTION){

  
        Connection con;
        try {
            con=getConnection();
            pt=con.prepareStatement("DELETE FROM `cliente` WHERE `cedula` = ?");
            pt.setString(1,txtCedula.getText());
            int enter=pt.executeUpdate();
            if (enter > 0) {
                JOptionPane.showMessageDialog(null, "Eliminacion existosa");
                cargartabla();
                limpliarCaja();
            } else {
                JOptionPane.showMessageDialog(null, "No hay campo seleccionado para borrar");

                limpliarCaja();
            }
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btpGuardarActionPerformed()"+e);
            JOptionPane.showMessageDialog(null, "Este cliente no se puede eliminar porque tiene una venta registrada  ");
        }
        
        
    }

    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarClienteActionPerformed
        // TODO add your handling code here:
        Connection con;
        try {
            con=getConnection();
            pt=con.prepareStatement("UPDATE `cliente` SET `nombre` = ?, `apellidos` = ?,`telefono` = ?, `direccion` = ?, `email` = ? WHERE `cedula` = ?");

            pt.setString(1,txtNombre.getText());
            pt.setString(2,txtApellido.getText());
            pt.setString(3,txtTelefono.getText());
            pt.setString(4, txtDireccion.getText());
            pt.setString(5,txtEmail.getText());
            pt.setString(6,txtCedula.getText());
            int enter=pt.executeUpdate();
            if (enter > 0) {
                JOptionPane.showMessageDialog(null, "Datos actualizados");
                cargartabla();
                limpliarCaja();
            } else {
                JOptionPane.showMessageDialog(null, "Error en actualizar");

            }
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btpGuardarActionPerformed()"+e);
        }

    }//GEN-LAST:event_btnModificarClienteActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnbuscarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarclienteActionPerformed
        // TODO add your handling code here:
        Connection cone ;
        try {
            cone = getConnection();
            pt=cone.prepareStatement("Select * from cliente where cedula=?");
            pt.setString(1, textbuscar.getText());
            rs=pt.executeQuery();

            if (rs.next()) {
                txtCedula.setText(rs.getString("cedula"));
                txtNombre.setText(rs.getString("nombre"));
                txtApellido.setText(rs.getString("apellidos"));
                txtTelefono.setText(rs.getString("telefono"));
                txtDireccion.setText(rs.getString("direccion"));
                txtEmail.setText(rs.getString("email"));

                cargartablaindividual();

            } else {
                JOptionPane.showMessageDialog(null,"Este cliente NO esta registrado ");
                txtCedula.setText(null);
                txtNombre.setText(null); 
                txtApellido.setText(null);
                txtEmail.setText(null);
                txtTelefono.setText(null);
                txtDireccion.setText(null);
                 
            }

        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btnbuscarActionPerformed()"+e);
        }

    }//GEN-LAST:event_btnbuscarclienteActionPerformed

    private void inforActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inforActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inforActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        RegistroUsuario usu = new RegistroUsuario();
        usu.setVisible(true);
        
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        categorias categoria = new categorias();
        categoria.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        tabla_categoria tb_categoria = new tabla_categoria();
        tb_categoria.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void tablacategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablacategoriasMouseClicked
        // TODO add your handling code here:
        Connection con;

        try {
            tablacategorias.setSelectionBackground(Color.BLUE);
            tablacategorias.setSelectionForeground(Color.WHITE);
            int fila = tablacategorias.getSelectedRow();
            int id =Integer.parseInt(tablacategorias.getValueAt(fila, 0).toString());
            con=getConnection();
            pt=con.prepareStatement("SELECT `id_categoría` FROM `categoría` WHERE id_categoría=?");
            pt.setInt(1, id);
            ResultSet rst = pt.executeQuery();
            while(rst.next()){
                txtcategoria.setText(String.valueOf(id));
                

            }
        } catch (NumberFormatException | SQLException e) {

        }
    }//GEN-LAST:event_tablacategoriasMouseClicked

    private void btnbuscarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarproveedorActionPerformed
        // TODO add your handling code here:

        Connection cone ;
        try {
            cone = getConnection();
            pt=cone.prepareStatement("Select * from categoría where id_categoría=?");
            pt.setString(1, txtbuscarproveedor.getText());
            ResultSet RS = pt.executeQuery();

            if (RS.next()) {

                /* Tabla Proveedor con uno Solo */

                cargarcategoriasindividual();

            } else {

                JOptionPane.showMessageDialog(null,"Esta categoria no esta registrada");
            }

        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btnbuscarActionPerformed()"+e);
        }

    }//GEN-LAST:event_btnbuscarproveedorActionPerformed

    private void txtbuscarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarproveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarproveedorActionPerformed

    private void btnEliminarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarproveedorActionPerformed
        // TODO add your handling code here:

        int confirmacion = JOptionPane.showConfirmDialog(jPanel1, "Desea eliminar esta categoria", "confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion ==JOptionPane.YES_OPTION){

            Connection con;

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
                JOptionPane.showMessageDialog(null, "La categoria esta registrada en compras NO se puede eliminar ");
                cargarcategorias();
                txtbuscarproveedor.setText(null);

            }
        }

    }//GEN-LAST:event_btnEliminarproveedorActionPerformed

    private void Btnlistar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btnlistar1ActionPerformed
        // TODO add your handling code here:
        cargarcategorias();
        txtbuscarproveedor.setText("");
    }//GEN-LAST:event_Btnlistar1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Document documento = new Document();
        LocalDate date = LocalDate.now();

        try {
            String venta = txtVenta.getText();
            String empleado = txtUsuario.getText();
            String clientes = txtCliente.getText();

            String ruta=System.getProperty("user.home");
            String poder =  JOptionPane.showInputDialog("¿Como desea guardar su archivo? ");

            while (poder.length()== 0) {

                String nom =  JOptionPane.showInputDialog("¿Como desea guardar su archivo? ");

                poder = nom;

            }

            if (venta.length()==0 ) {
                JOptionPane.showMessageDialog(PanelCliente, "No seleccionó la venta a exportar ");
            }else{

                JOptionPane.showMessageDialog(rootPane,"El PDF exportado se llama  ''"+ poder+" "+date+"'', revise su escritorio");
                PdfWriter.getInstance(documento,new FileOutputStream(ruta+"/Desktop/"+poder+" "+date+".pdf") );

                com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/Iconos/productos.png");
                header.scaleToFit(95, 95);
                header.setAlignment(Chunk.ALIGN_LEFT);

                Paragraph Parf1 = new Paragraph();

                Parf1.add("N° venta: "+venta+"      "+"Empleado: "+empleado+"       "+"Cliente: "+clientes+" "
                    + "       "+"Fecha: "+date+"\n\n\n\n");
                Parf1.setAlignment(Paragraph.ALIGN_LEFT);

                
                Paragraph Parf5 = new Paragraph();
                Parf5.add("_______________________________________________________");
                Parf5.setAlignment(Paragraph.ALIGN_LEFT);

                Paragraph parrafo = new Paragraph();
                parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo.add("Información del cliente. \n \n");
                parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));
                parrafo.setSpacingBefore(10);

                Font Titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                Titulo.setColor(BaseColor.BLUE);
                Titulo.setSize(20);

                Paragraph titulo = new Paragraph("Factura",Titulo);
                titulo.setAlignment(Paragraph.ALIGN_CENTER);

                Font sub = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                sub.setColor(BaseColor.BLUE);
                sub.setSize(11);

                Paragraph footer = new Paragraph("Monterosoft Copyright © 2022 todos los derechos reservados",sub);
                footer.setAlignment(Paragraph.ALIGN_RIGHT);

                PdfPTable tabla = new PdfPTable(6);
                tabla.setWidthPercentage(100f);
                tabla.setWidths(new float [] {1.5f,2.5f,2.0f,2.0f,2.0f,2.0f});
                tabla.setSpacingBefore(10);

                Font encabezado = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                encabezado.setColor(BaseColor.WHITE);
                encabezado.setSize(13);

                PdfPCell id = new PdfPCell(new Phrase("ID",encabezado));
                id.setBackgroundColor(BaseColor.BLUE);

                PdfPCell fecha = new PdfPCell(new Phrase("Fecha",encabezado));
                fecha.setBackgroundColor(BaseColor.BLUE);

                PdfPCell monto = new PdfPCell(new Phrase("Monto",encabezado));
                monto.setBackgroundColor(BaseColor.BLUE);

                PdfPCell produto = new PdfPCell(new Phrase("Id producto",encabezado));
                produto.setBackgroundColor(BaseColor.BLUE);

                PdfPCell cliente = new PdfPCell(new Phrase("Cc cliente",encabezado));
                cliente.setBackgroundColor(BaseColor.BLUE);

                PdfPCell suario = new PdfPCell(new Phrase("Id usuario",encabezado));
                suario.setBackgroundColor(BaseColor.BLUE);

                tabla.addCell(id);
                tabla.addCell(fecha);
                tabla.addCell(monto);
                tabla.addCell(produto);
                tabla.addCell(cliente);
                tabla.addCell(suario);

                /********************************** ORDEN DEL PDF *************************************/
                documento.open();
                documento.add(footer);
                documento.add(header);//#1

                documento.add(Parf5);//#
                documento.add(parrafo);//#
                documento.add(Parf1);
                /*  documento.add(Parf2);
                documento.add(Parf3);     */
                documento.add(titulo);

                /**********************************  *************************************/

                try {

                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/muebles","root","");
                    PreparedStatement ps=con.prepareStatement("SELECT * FROM venta where id_venta='"+venta+"'");

                    ResultSet pol=ps.executeQuery();

                    if (pol.next()) {
                        do {
                            tabla.addCell(pol.getString(1));
                            tabla.addCell(pol.getString(2));
                            tabla.addCell(pol.getString(3));
                            tabla.addCell(pol.getString(4));
                            tabla.addCell(pol.getString(5));
                            tabla.addCell(pol.getString(6));

                        } while (pol.next());
                        documento.add(tabla);

                    }

                } catch (SQLException e) {
                    System.err.print("Error al obtener datos del clientes. " + e);
                }

                documento.close();

                /*****************SOBRA****************************

            } catch (DocumentException | IOException e) {
                System.err.println("Error en PDF o ruta de imagen"  );
                JOptionPane.showMessageDialog(null, "Error al generar PDF, contacte al administrador");

                /**********************************************/

            }

        } catch (DocumentException | IOException ex) {
            Logger.getLogger(inicioadmi.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Cierra el else//Cierra el else
        //Cierra el else//Cierra el else
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
         Document documento = new Document();
        LocalDate date = LocalDate.now();

        try {
            String venta = "Todos";
            String empleado = "Todos";
            String clientes = "Todos";

            String ruta=System.getProperty("user.home");
            String poder =  JOptionPane.showInputDialog("¿Como desea guardar su archivo? ");

            while (poder.length()== 0) {

                String nom =  JOptionPane.showInputDialog("¿Como desea guardar su archivo? ");

                poder = nom;

            }

            
                JOptionPane.showMessageDialog(rootPane,"El PDF exportado se llama  ''"+ poder+" "+date+"'', revise su escritorio");
                PdfWriter.getInstance(documento,new FileOutputStream(ruta+"/Desktop/"+poder+" "+date+".pdf") );

                com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/Iconos/productos.png");
                header.scaleToFit(95, 95);
                header.setAlignment(Chunk.ALIGN_LEFT);

                Paragraph Parf1 = new Paragraph();

                Parf1.add("N° venta: "+venta+"      "+"Empleado: "+empleado+"       "+"Cliente: "+clientes+" "
                    + "       "+"Fecha: "+date+"\n\n\n\n");
                Parf1.setAlignment(Paragraph.ALIGN_LEFT);

                /*  Paragraph Parf2 = new Paragraph();

                Parf2.add("Empleado: "+empleado+"\n\n");
                Parf2.setAlignment(Paragraph.ALIGN_LEFT);

                Paragraph Parf3 = new Paragraph();

                Parf3.add("Cliente: "+clientes+"\n\n");
                Parf3.setAlignment(Paragraph.ALIGN_LEFT);

                Paragraph Parf4 = new Paragraph();
                Parf4.add("Fecha: "+date+"\n\n");
                Parf4.setAlignment(Paragraph.ALIGN_LEFT);
                */
                Paragraph Parf5 = new Paragraph();
                Parf5.add("_______________________________________________________");
                Parf5.setAlignment(Paragraph.ALIGN_LEFT);

                Paragraph parrafo = new Paragraph();
                parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo.add("Información del cliente. \n \n");
                parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));
                parrafo.setSpacingBefore(10);

                Font Titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                Titulo.setColor(BaseColor.BLUE);
                Titulo.setSize(20);

                Paragraph titulo = new Paragraph("Factura",Titulo);
                titulo.setAlignment(Paragraph.ALIGN_CENTER);

                Font sub = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                sub.setColor(BaseColor.BLUE);
                sub.setSize(11);

                Paragraph footer = new Paragraph("Monterosoft Copyright &copy; 2022 todos los derechos reservados",sub);
                footer.setAlignment(Paragraph.ALIGN_RIGHT);

                PdfPTable tabla = new PdfPTable(6);
                tabla.setWidthPercentage(100f);
                tabla.setWidths(new float [] {1.5f,2.5f,2.0f,2.0f,2.0f,2.0f});
                tabla.setSpacingBefore(10);

                Font encabezado = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                encabezado.setColor(BaseColor.WHITE);
                encabezado.setSize(13);

                PdfPCell id = new PdfPCell(new Phrase("ID",encabezado));
                id.setBackgroundColor(BaseColor.BLUE);

                PdfPCell fecha = new PdfPCell(new Phrase("Fecha",encabezado));
                fecha.setBackgroundColor(BaseColor.BLUE);

                PdfPCell monto = new PdfPCell(new Phrase("Monto",encabezado));
                monto.setBackgroundColor(BaseColor.BLUE);

                PdfPCell product = new PdfPCell(new Phrase("Id producto",encabezado));
                product.setBackgroundColor(BaseColor.BLUE);

                PdfPCell cliente = new PdfPCell(new Phrase("Cc cliente",encabezado));
                cliente.setBackgroundColor(BaseColor.BLUE);

                PdfPCell usuar = new PdfPCell(new Phrase("Id usuario",encabezado));
                usuar.setBackgroundColor(BaseColor.BLUE);

                tabla.addCell(id);
                tabla.addCell(fecha);
                tabla.addCell(monto);
                tabla.addCell(product);
                tabla.addCell(cliente);
                tabla.addCell(usuar);

                /********************************** ORDEN DEL PDF *************************************/
                documento.open();
                documento.add(footer);
                documento.add(header);//#1

                documento.add(Parf5);//#
                documento.add(parrafo);//#
                documento.add(Parf1);
                /*  documento.add(Parf2);
                documento.add(Parf3);     */
                documento.add(titulo);

                /**********************************  *************************************/

                try {

                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/muebles","root","");
                    PreparedStatement ps=con.prepareStatement("SELECT * FROM venta ");

                    ResultSet pol=ps.executeQuery();

                    if (pol.next()) {
                        do {
                            tabla.addCell(pol.getString(1));
                            tabla.addCell(pol.getString(2));
                            tabla.addCell(pol.getString(3));
                            tabla.addCell(pol.getString(4));
                            tabla.addCell(pol.getString(5));
                            tabla.addCell(pol.getString(6));

                        } while (pol.next());
                        documento.add(tabla);

                    }

                } catch (SQLException e) {
                    System.err.print("Error al obtener datos del clientes. " + e);
                }

                documento.close();

                /*****************SOBRA****************************/

            } catch (DocumentException | IOException e) {
                System.err.println("Error en PDF o ruta de imagen"  );
                JOptionPane.showMessageDialog(null, "Error al generar PDF, contacte al administrador");

                /**********************************************/

            }

        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        
       int confirmar =  JOptionPane.showConfirmDialog(PanelCliente, "Desea Cerrar Sesión?","confirmar decisión",JOptionPane.YES_NO_OPTION);
        
        if (confirmar ==JOptionPane.YES_OPTION){  
            JOptionPane.showMessageDialog(PanelCliente,"Sesión cerrada! ");
           login salir = new login();
          salir.setVisible(true);
           this.hide(); 
           
        } 
        
    }//GEN-LAST:event_btnSalirActionPerformed

    
    
    
    
    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicioadmi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new inicioadmi().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btnlistar;
    private javax.swing.JButton Btnlistar1;
    private javax.swing.JButton Buscarproducto;
    private javax.swing.JPanel PanelCliente;
    private javax.swing.JTabbedPane PanelPrincipal;
    private javax.swing.JPanel PanelProductos;
    private javax.swing.JPanel PanelVentas;
    private javax.swing.JButton btnActualizarCompra;
    private javax.swing.JButton btnBuscarCompra;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarCompra;
    private javax.swing.JButton btnEliminarproducto;
    public javax.swing.JButton btnEliminarproveedor;
    private javax.swing.JButton btnEliminarventa;
    private javax.swing.JButton btnGuadarCompra;
    private javax.swing.JButton btnGuardarCiente;
    private javax.swing.JButton btnGuardaventa;
    private javax.swing.JButton btnModificarCliente;
    private javax.swing.JButton btnModificarproducto;
    private javax.swing.JButton btnModificarventa;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnbuscarcliente;
    private javax.swing.JButton btnbuscarproveedor;
    private javax.swing.JButton btpGuardarproducto;
    private javax.swing.JButton buscarventa;
    private javax.swing.JTextField infor;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton listar;
    private javax.swing.JButton listar1;
    private javax.swing.JButton listar2;
    private javax.swing.JPanel producto;
    private javax.swing.JTable tablaCompra;
    private javax.swing.JTable tablacategorias;
    private javax.swing.JTable tablacliente;
    private javax.swing.JTable tablaproducto;
    private javax.swing.JTable tablaventas;
    private javax.swing.JTextField textbuscar;
    public static javax.swing.JLabel textoadmin;
    public static javax.swing.JLabel textocargo;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscarcompra;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFechaC;
    private javax.swing.JTextField txtId_producto;
    private javax.swing.JTextField txtIdproducto;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtProductoC;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtVenta;
    private javax.swing.JTextField txtbuscarproducto;
    private javax.swing.JTextField txtbuscarproveedor;
    private javax.swing.JTextField txtbuscarventas;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtcantidadcomp;
    private javax.swing.JTextField txtcategoria;
    private javax.swing.JTextField txtcosto;
    private javax.swing.JTextField txtidcategoria;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtproveedor;
    private javax.swing.JTextField txtrut;
    // End of variables declaration//GEN-END:variables
}
