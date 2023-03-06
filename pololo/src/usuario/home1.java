




package usuario;
/* Importa las librerias de java */

import Administrador.inicioadmi;
import Administrador.login;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*************************************************************************************************/



/*
 * @nadie
 */




public class home1 extends javax.swing.JFrame { //** Clase de inicio***
    
    
  
    //*************************** Conexion a la base de datos ***************************************************************************  
    
public static final String url="jdbc:mysql://localhost:3306/muebles";
public static final String usuario="root";
public static final String clave="";
PreparedStatement pt;  // RECIBE SECUENCIA MSQL
ResultSet rs; //RETORNA EL RESULTADO DE UNA CONSULTA
    public static Connection getConnection(){
    Connection cone=null;
    // La construcción try catch permite manejar errores de tiempo de ejecución. 
    //Literalmente permite “intentar (try)” ejecutar el código y “atrapar (catch)”
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cone=DriverManager.getConnection(url,usuario,clave);
            System.out.println("conecion exitosa");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(" error en la conexion "+e);
            JOptionPane.showMessageDialog(null, "error en la conexion");
        }
    return cone; // La sentencia return se emplea para salir de la secuencia de ejecución 
                // de las sentencias de un método y, opcionalmente, devolver un valor.
    
    }
    
 //********************************** fin Conexion a la base de datos *******************************************************************   
    
    
 //*******************************************************************************************************************************************
    
    
    //*****************************************  Metodos para limpiar las cajas de texto*************************************************************
 private void limpliarCaja(){ // modificador de acceso utilizado para atributos, métodos y constructores, lo que los hace accesibles solo dentro de la clase declarada.
txtCedula.setText(null);
 txtNombre.setText(null); 
 txtApellido.setText(null);
 txtEmail.setText(null);
 txtTelefono.setText(null);
 txtDireccion.setText(null);
 textbuscar.setText(null);
    
    }
    
    private void limpiarcompra(){
         txtrut.setText(null);
         txtProd.setText(null);
         txtprovee.setText(null);
         txtFechaC.setText(null);
         txtcantidadcomp.setText(null);
         txtcosto.setText(null);
         txtBuscarcompra.setText(null);    
}
  

    
    private void limpiarproducto(){
             txtIdproducto.setText(null);
            txtnombre.setText(null);
            txtcategoria.setText(null);
            txtcantidad.setText(null);
    
    }
    private void limpiarventa(){
     txtVenta.setText(null);
      txtFecha.setText(null);
       txtValor.setText(null) ;
       txtId_producto.setText(null);
      txtCliente.setText(null) ;
       txtUsuario.setText(null) ;
       txtbuscarventa.setText(null);
    
    }
    
    //***************************************** Fin  Metodos ******************************************************************************* 
    //*******************************************************************************************************************************************
    
    
    
    
    
       private void cargartabla(){ //CLIENTE   METODOS PARA CARGAR TABLAS |||     
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
              
              while(rs.next()){ // repetir una acción en un bucle siempre y cuando traiga un objeto dando incio a un 'for'
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
       
       
           private void cargartablaindividual(){
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
              
              while(rs.next()){     // repetir una acción en un bucle siempre y cuando traiga un objeto dando incio a un 'for'
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
       
       
       
       
       //************************************************************************************************************************************************ 
      private void cargartablaCompra(){
     DefaultTableModel modelo = (DefaultTableModel) TBComra.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;
         int columna;
         Connection cone=null;
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `idCompra`, `nonbre`, `idProveedor`, `fecha`, `costo`, `cantidad`, `total` FROM `compra`");
              rs=pt.executeQuery();
              rsmd=rs.getMetaData();
              columna=rsmd.getColumnCount();
              
              while(rs.next()){ // repetir una acción en un bucle siempre y cuando traiga un objeto dando incio a un 'for'
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
      
       
          private void cargartablaCompraindividual(){
            DefaultTableModel modelo = (DefaultTableModel) TBComra.getModel();
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
              
              while(rs.next()){ // repetir una acción en un bucle siempre y cuando traiga un objeto dando incio a un 'for' con los valores de los datos en la BD
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
    //***********************************************************************************************************************************************************
    
      
       //********************************************************************************************************************************************************* 
        private void cargartablaventa(){//metodo para carga la tabla con los datos de la BD
     DefaultTableModel modelo = (DefaultTableModel) tablaVenta.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;//trae los meta datos de la tabla de las columna
         int columna;//guarda un el numero de las columnas en la base de datos
         Connection cone=null;
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_venta`, `fecha`, `monto`, `id_producto`, `cedula_cliente`, `id_usuario` FROM `venta`");
              rs=pt.executeQuery();
              rsmd=rs.getMetaData();
              columna=rsmd.getColumnCount();
              
              while(rs.next()){ // repetir una acción en un bucle siempre y cuando traiga un objeto dando incio a un 'for' con los valores de los datos en la BD
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
        
        
        private void cargartablaventaindividual(){
     DefaultTableModel modelo = (DefaultTableModel) tablaVenta.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;//trae los meta datos de la tabla de las columna
         int columna;//guarda un el numero de las columnas en la base de datos
         Connection cone=null;
         String buscarventas = txtbuscarventa.getText();
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_venta`, `fecha`, `monto`, `id_producto`, `cedula_cliente`, `id_usuario` FROM `venta` where `id_venta` = '"+buscarventas+"'");
              rs=pt.executeQuery();
              rsmd=rs.getMetaData();
              columna=rsmd.getColumnCount();
              
              while(rs.next()){ // repetir una acción en un bucle siempre y cuando traiga un objeto dando incio a un 'for' con los valores de los datos en la BD
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
              
        
      //************************************************************************************************************************************************
        private void cargartablaproducto(){//metodo para carga la tabla con los datos de la BD
     DefaultTableModel modelo = (DefaultTableModel) tablaproducto.getModel();
    modelo.setRowCount(0);
         ResultSetMetaData rsmd;//trae los meta datos de la tabla de las columna
         int columna;//guarda un el numero de las columnas en la base de datos
         Connection cone=null;
         try {
              cone=getConnection();
              pt=cone.prepareStatement("SELECT `id_producto`, `nombre`, `id_categorias`, `cantida` FROM `producto`");
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
        
          private void cargartablaproductoindividual(){
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
         ResultSet res = pt.executeQuery();
              rsmd=res.getMetaData();
              columna=rsmd.getColumnCount();
              
              while(res.next()){
                Object[] fila= new Object[columna];
                for(int indice=0;indice<columna;indice++){
                    fila[indice]=res.getObject(indice+1);
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
         ResultSet rssl = pt.executeQuery();
              rsmd=rssl.getMetaData();
              columna=rsmd.getColumnCount();
              
              while(rssl.next()){
                Object[] fila= new Object[columna];
                for(int indice=0;indice<columna;indice++){
                    fila[indice]=rssl.getObject(indice+1);
                }
                modelo.addRow(fila);
              }
         } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, e.toString());
         }
     
    
    }
         
         
         //**************************************************************************************************************************
        
       


      //***************************************************** FIN METODO DE CARGAR TABLAS*******************************************************************************************   
    /******************************************************
     * Creates new form inicio
     */
       
          
          
          
      //******************** CONSTRUCTOR DEL PROGRAMA ************************************************************ 
    public home1() {
      initComponents();
      this.setLocationRelativeTo(null);
      this.cargartablaventa();
      this.cargartablaproducto();
      this.cargartablaCompra();
      cargartabla();
      this.infor.setVisible(false);
      this.setSize(1300,750);
      this.setResizable(false);
      this. setLocationRelativeTo(null);
      this.total();
        
      
    
    }
  //******************** FIN DEL CONSTRUCTOR ************************************************************ 
    
    @Override
public Image getIconImage(){
    Image ver = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Iconos/productos.png"));
return ver;
}



    
       private void total (){//metodo para calcular el tota de de una columna en espesifico que subtotal
            
            int t= 0;
            double p = 0;
            if (TBComra.getRowCount() > 0){
              for (int i=0; i<TBComra.getRowCount();i++){
                  
                  p = Double.parseDouble(TBComra.getValueAt(i,6).toString());
                  t +=p;
                  
              }
                jLabel33.setText("El total es : $" + t);
            }
        
        
        
        
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
        btnGuardarCiente = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablacliente = new javax.swing.JTable();
        Btnlistar = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
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
        jLabel18 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnGuardaventa = new javax.swing.JButton();
        txtbuscarventa = new javax.swing.JTextField();
        buscarventa = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaVenta = new javax.swing.JTable();
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
        jLabel20 = new javax.swing.JLabel();
        txtcategoria = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaproducto = new javax.swing.JTable();
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
        txtProd = new javax.swing.JTextField();
        txtprovee = new javax.swing.JTextField();
        txtFechaC = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtcosto = new javax.swing.JTextField();
        btnGuadarCompra = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtcantidadcomp = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        btnBuscarCompra = new javax.swing.JButton();
        txtBuscarcompra = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TBComra = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        listar2 = new javax.swing.JButton();
        textousuario = new javax.swing.JLabel();
        textocargo = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        jMenu4.setText("jMenu4");

        jMenu5.setText("jMenu5");

        jMenuItem3.setText("jMenuItem3");

        jMenu6.setText("jMenu6");

        jMenu8.setText("jMenu8");

        jMenuItem8.setText("jMenuItem8");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MonteroSoft/Empleado");
        setBackground(new java.awt.Color(51, 204, 255));
        setIconImage(getIconImage());
        getContentPane().setLayout(null);

        PanelPrincipal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel1.setText("Clientes");

        textbuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnbuscarcliente.setBackground(new java.awt.Color(255, 255, 255));
        btnbuscarcliente.setText("Buscar");
        btnbuscarcliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbuscarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarclienteActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("Cedula");

        txtCedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Nombre");

        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("E-mail");

        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Telefono");

        txtTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnGuardarCiente.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnGuardarCiente.setText("Guardar");
        btnGuardarCiente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGuardarCiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCienteActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel13.setText("Apellido");

        txtApellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setText("Direccion");

        txtDireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(8, 8, 8)))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7)))
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarCiente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addComponent(txtCedula)
                        .addComponent(txtApellido))
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnGuardarCiente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                "Cedula", "Nombre", "Apellido", "Telefono", "Direccion", "Email"
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        Btnlistar.setBackground(new java.awt.Color(255, 255, 0));
        Btnlistar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btnlistar.setText("Lista completa");
        Btnlistar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btnlistar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnlistarActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 255));
        jLabel32.setText("Listado de Clientes");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(342, 342, 342)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(22, 22, 22)
                        .addComponent(textbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnbuscarcliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel32)
                        .addGap(189, 189, 189)
                        .addComponent(Btnlistar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarcliente)
                    .addComponent(Btnlistar)
                    .addComponent(jLabel32))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelClienteLayout = new javax.swing.GroupLayout(PanelCliente);
        PanelCliente.setLayout(PanelClienteLayout);
        PanelClienteLayout.setHorizontalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClienteLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
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

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel18.setText("N° Usuario");

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
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel18))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtFecha)
                                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtId_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(64, 64, 64)
                                .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(btnGuardaventa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtId_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(35, 35, 35)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(20, 20, 20)
                .addComponent(btnGuardaventa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        buscarventa.setBackground(new java.awt.Color(255, 255, 255));
        buscarventa.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        buscarventa.setText("Buscar");
        buscarventa.setContentAreaFilled(false);
        buscarventa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buscarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarventaActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Venta", "Fecha", "Valor", "ID Producto", "ID Cliente", "ID Usuario"
            }
        ));
        tablaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVentaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaVenta);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
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
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtbuscarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscarventa))
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel29)
                        .addGap(176, 176, 176)
                        .addComponent(listar))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(listar)
                            .addComponent(jLabel29)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtbuscarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buscarventa)
                                    .addComponent(jLabel26)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(78, 78, 78))
        );

        javax.swing.GroupLayout PanelVentasLayout = new javax.swing.GroupLayout(PanelVentas);
        PanelVentas.setLayout(PanelVentasLayout);
        PanelVentasLayout.setHorizontalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        PanelVentasLayout.setVerticalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelVentasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
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
        jLabel12.setText("Cantidad ");

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

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel20.setText("Categotiria");

        txtcategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btpGuardarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtIdproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addComponent(btpGuardarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                "N° Producto", "Nombre", "Categoria", "Cantidad"
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(139, 139, 139))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 775, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Buscarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(listar1)))
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addGap(314, 314, 314))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbuscarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Buscarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(listar1)))
                .addGap(8, 8, 8)
                .addComponent(jLabel30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout productoLayout = new javax.swing.GroupLayout(producto);
        producto.setLayout(productoLayout);
        productoLayout.setHorizontalGroup(
            productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        productoLayout.setVerticalGroup(
            productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelPrincipal.addTab("Productos", producto);

        PanelProductos.setLayout(null);

        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setMinimumSize(new java.awt.Dimension(1400, 530));

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtrut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtprovee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtprovee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproveeActionPerformed(evt);
            }
        });

        txtFechaC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel15.setText("nombre");

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel16.setText("N° proveedor");

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel17.setText("Fecha");

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel19.setText("Costo");

        txtcosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnGuadarCompra.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnGuadarCompra.setText("Guadar");
        btnGuadarCompra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnGuadarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuadarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuadarCompraActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Cantidad");

        txtcantidadcomp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel27.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel27.setText("N° Compra");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcosto)
                    .addComponent(txtFechaC)
                    .addComponent(txtprovee)
                    .addComponent(txtProd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtrut)
                    .addComponent(txtcantidadcomp))
                .addGap(34, 34, 34))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(btnGuadarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtrut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtprovee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFechaC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(42, 42, 42)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(txtcosto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcantidadcomp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(27, 27, 27)
                .addComponent(btnGuadarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        btnBuscarCompra.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarCompra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBuscarCompra.setText("Buscar");
        btnBuscarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCompraActionPerformed(evt);
            }
        });

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        TBComra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Compra", "nombre", "N° proveedor", "Fecha", "Precio", "Cantidad", "Subtotal"
            }
        ));
        TBComra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TBComra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBComraMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TBComra);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setText("Total");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addGap(39, 39, 39))
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
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscarcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnBuscarCompra))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(listar2)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGap(325, 325, 325)
                                    .addComponent(jLabel31))))))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscarCompra)
                            .addComponent(txtBuscarcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(listar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        PanelProductos.add(jPanel12);
        jPanel12.setBounds(16, 10, 1200, 560);

        PanelPrincipal.addTab("Compras", PanelProductos);

        getContentPane().add(PanelPrincipal);
        PanelPrincipal.setBounds(20, 60, 1240, 610);

        textousuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        textousuario.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                textousuarioAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(textousuario);
        textousuario.setBounds(740, 10, 160, 40);

        textocargo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        textocargo.setText("Nombre del Empleado:");
        getContentPane().add(textocargo);
        textocargo.setBounds(510, 10, 210, 40);

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
        btnSalir.setBounds(1100, 20, 120, 30);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Monterosoft Copyright © 2022 todos los derechos reservados");
        getContentPane().add(jLabel35);
        jLabel35.setBounds(480, 680, 390, 15);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("proveedores");
        jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenuItem10.setText("tabla");
        jMenuItem10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuBar1.add(jMenu3);

        jMenu9.setText("empleado");
        jMenu9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu9ActionPerformed(evt);
            }
        });

        jMenuItem7.setText("Tabla");
        jMenuItem7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem7);

        jMenuBar1.add(jMenu9);

        jMenu1.setText("                                                                                                                                 Pagina de los Empleados                                                                                                                                                                                                           ");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    
    
      //******************** SE PROGRAMA EL BOTON DE GUARDAR CLIENTE ************************************************************ 
    private void btnGuardarCienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCienteActionPerformed
       Connection con=null;
        try {
            con=getConnection();
            pt=con.prepareStatement("INSERT INTO `cliente` (`cedula`, `nombre`, `apellidos`,telefono, `direccion`, `email`) VALUES (?,?,?,?,?,?)");
            
            pt.setString(1,txtCedula.getText());
            pt.setString(2,txtNombre.getText());
             pt.setString(3,txtApellido.getText());
             pt.setString(4,txtTelefono.getText());
              pt.setString(5,txtEmail.getText());
              pt.setString(6,txtDireccion.getText());
               int enter=pt.executeUpdate();
               if (enter > 0) {
                  cargartabla();
                  limpliarCaja();
                   JOptionPane.showMessageDialog(null, "guardado exitoso");
                
            } else {
               JOptionPane.showMessageDialog(null, "error en guardar");
              
              limpliarCaja();
            }
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btnGuardarActionPerformed()"+e);
            JOptionPane.showMessageDialog(null,"Error...Verifique sus datos");
        }
    }//GEN-LAST:event_btnGuardarCienteActionPerformed
//******************** FIN DEL BOTON DE GUARDAR CLIENTE ************************************************************ 
    
    
    
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
                   JOptionPane.showMessageDialog(null, "Producto exitoso");
               cargartablaproducto();
               limpiarproducto();
            } else {
               JOptionPane.showMessageDialog(null, "error en guardar");
              
              limpliarCaja();
            }
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btpGuardarActionPerformed()"+e);
            JOptionPane.showMessageDialog(null,"Error...Verifique sus datos");
        }
    }//GEN-LAST:event_btpGuardarproductoActionPerformed

    private void btnbuscarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarclienteActionPerformed
        // TODO add your handling code here:
        Connection cone = null;
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
               JOptionPane.showMessageDialog(null,"Este cliente no esta registrad@"); 
            }
            
            
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btnbuscarActionPerformed()"+e);
        }
        
        
        
        
        
        
    }//GEN-LAST:event_btnbuscarclienteActionPerformed

    private void btnGuadarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuadarCompraActionPerformed
       Connection cone=null; 
       String RUT=txtrut.getText();
       String Nombre=txtProd.getText();
       String direccion= txtprovee.getText();
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
         limpiarcompra();
         total();
         JOptionPane.showMessageDialog(null, "Compra exitoso");
                
        
        }    
            
        } catch (SQLException e) {
            System.out.println("monterosoft.inicio.btnGuadarCompraActionPerformed()"+e);  
            JOptionPane.showMessageDialog(null,"Error...Verifique sus datos");
        }
        
    }//GEN-LAST:event_btnGuadarCompraActionPerformed

    private void txtproveeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproveeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproveeActionPerformed

    private void btnGuardaventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardaventaActionPerformed
        // TODO add your handling code here:
         Connection cone=null; 
       String id=txtVenta.getText();
       String fecha=txtFecha.getText();
       String product= txtId_producto.getText();
       String cliente=txtCliente.getText() ;
       String usua=txtUsuario.getText() ;
        String valor=txtValor.getText() ;
      
       
        try {
            cone=getConnection();
            pt=cone.prepareStatement("INSERT INTO `venta` (`id_venta`, `fecha`, `monto`, `id_producto`, `cedula_cliente`, `id_usuario`) VALUES (?, ?, ?, ?, ?, ?)");
            
            pt.setString(1,id);
             pt.setString(2,fecha);
             pt.setString(3,valor);
             pt.setString(4,product);
             pt.setString(5,cliente);
             pt.setString(6, usua);
             int enter=pt.executeUpdate();
             if(enter>0){
             JOptionPane.showMessageDialog(null, "Venta existosa");
             cargartablaventa();
             limpiarventa();
             }
        } catch (SQLException e) {
            System.out.println("monterosoft.inicio.jButton4ActionPerformed()"+e);
            JOptionPane.showMessageDialog(null,"Error...Verifique sus datos");
        }
    }//GEN-LAST:event_btnGuardaventaActionPerformed

    private void buscarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarventaActionPerformed
 Connection cone = null;
        try {
            cone = getConnection();
            pt=cone.prepareStatement("Select * from venta where id_venta=?");
            pt.setString(1, txtbuscarventa.getText());
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
                JOptionPane.showMessageDialog(null,"Esta venta no esta registrada");
            }
            
            
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btnbuscarActionPerformed()"+e);
        
        
        }    }//GEN-LAST:event_buscarventaActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void BuscarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarproductoActionPerformed
        // TODO add your handling code here:
        
                 Connection cone = null;
        try {
            cone = getConnection();
            pt=cone.prepareStatement("SELECT * FROM `producto` WHERE id_producto =?");
            pt.setString(1, txtbuscarproducto.getText());
            rs=pt.executeQuery();
            
            if (rs.next()) {
                
            txtIdproducto.setText(rs.getString("id_producto"));
            txtnombre.setText(rs.getString("nombre"));
            txtcategoria.setText(rs.getString("id_categorias"));
            txtcantidad.setText(rs.getString("cantida"));
            cargartablaproductoindividual();
               
            } else {
                JOptionPane.showMessageDialog(null,"Este producto no esta registrado");
            }
            
            
        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btnbuscarActionPerformed()"+e);
        }
        
        
    }//GEN-LAST:event_BuscarproductoActionPerformed

    private void tablaclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaclienteMouseClicked
        // TODO add your handling code here:
         Connection con=null;
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

    private void tablaVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentaMouseClicked
        // TODO add your handling code here:
        Connection con=null;
         tablaVenta.setSelectionBackground(Color.BLUE);
         tablaVenta.setSelectionForeground(Color.WHITE);
        
        try {
            int fila = tablaVenta.getSelectedRow();
            int id =Integer.parseInt(tablaVenta.getValueAt(fila, 0).toString());
            con=getConnection();
              pt=con.prepareStatement("SELECT `id_venta`, `fecha`, `monto`, `id_producto`, `cedula_cliente`, `id_usuario` FROM venta where id_venta=?");
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
      
        
    }//GEN-LAST:event_tablaVentaMouseClicked

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void tablaproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaproductoMouseClicked
        // TODO add your handling code here:
        
         Connection con=null;    
         tablaproducto.setSelectionBackground(Color.BLUE);
         tablaproducto.setSelectionForeground(Color.WHITE);
       
        
        try {
            int fila = tablaproducto.getSelectedRow();
            int id =Integer.parseInt(tablaproducto.getValueAt(fila, 0).toString());
            con=getConnection();
              pt=con.prepareStatement("SELECT `id_producto`, `nombre`, `id_categorias`, `cantida` FROM `producto` WHERE id_producto=?");
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

    private void TBComraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBComraMouseClicked
        // TODO add your handling code here:
        
          Connection con=null;
         try {
             TBComra.setSelectionBackground(Color.BLUE);
        TBComra.setSelectionForeground(Color.WHITE);
            int fila = TBComra.getSelectedRow();
            int id =Integer.parseInt(TBComra.getValueAt(fila, 0).toString());
            con=getConnection();
              pt=con.prepareStatement("SELECT `idCompra`, `nonbre`, `idProveedor`, `fecha`, `costo`, `cantidad`, `total` FROM `compra` WHERE idCompra=?");
             pt.setInt(1, id);
              rs=pt.executeQuery();
              while(rs.next()){
                txtrut.setText(rs.getString("idCompra"));
                txtProd.setText(rs.getString("nonbre"));
                txtprovee.setText(rs.getString("idProveedor"));
                txtFechaC.setText(rs.getString("fecha"));
                txtcantidadcomp.setText(rs.getString("cantidad"));
              txtcosto.setText(rs.getString("costo"));
                 
                              
              }
        } catch (NumberFormatException | SQLException e) {
            
        }
        
        
        
    }//GEN-LAST:event_TBComraMouseClicked

    private void jMenu9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        tabla tab= new tabla();
        tab.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        tabla_empleado table =new tabla_empleado();
        table.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void btnBuscarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCompraActionPerformed
        Connection cone = null;
        try {
            cone = getConnection();
            pt=cone.prepareStatement("Select * from compra where idCompra=?");
            pt.setString(1, txtBuscarcompra.getText());
            rs=pt.executeQuery();

            if (rs.next()) {
                txtrut.setText(rs.getString("idCompra"));
                txtProd.setText(rs.getString("nonbre"));
                txtprovee.setText(rs.getString("idProveedor"));
                txtFechaC.setText(rs.getString("fecha"));
                txtcantidadcomp.setText(rs.getString("cantidad"));
                txtcosto.setText(rs.getString("costo"));
                cargartablaCompraindividual();

            } else {
                JOptionPane.showMessageDialog(null,"Esta compra no esta registrada");
            }

        } catch (SQLException | HeadlessException e) {
            System.out.println("monterosoft.inicio.btnbuscarActionPerformed()"+e);
        }

    }//GEN-LAST:event_btnBuscarCompraActionPerformed

    private void BtnlistarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnlistarActionPerformed
        // TODO add your handling code here:
        cargartabla();
        limpliarCaja();
    }//GEN-LAST:event_BtnlistarActionPerformed

    private void listarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarActionPerformed
        // TODO add your handling code here:

        cargartablaventa();
        limpiarventa();

    }//GEN-LAST:event_listarActionPerformed

    private void listar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listar1ActionPerformed
        // TODO add your handling code here:
        cargartablaproducto();
        limpiarproducto();

    }//GEN-LAST:event_listar1ActionPerformed

    private void listar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listar2ActionPerformed
        // TODO add your handling code here:
        cargartablaCompra();
        limpiarcompra();

    }//GEN-LAST:event_listar2ActionPerformed

    private void textousuarioAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_textousuarioAncestorAdded
        // TODO add your handling code here:
        
       
        
    }//GEN-LAST:event_textousuarioAncestorAdded

    private void tablacategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablacategoriasMouseClicked
        // TODO add your handling code here:
        Connection con=null;
        tablacategorias.setSelectionBackground(Color.BLUE);
        tablacategorias.setSelectionForeground(Color.WHITE);
        try {
            tablacategorias.setSelectionBackground(Color.red);
            int fila = tablacategorias.getSelectedRow();
            int id =Integer.parseInt(tablacategorias.getValueAt(fila, 0).toString());
            con=getConnection();
            pt=con.prepareStatement("SELECT `id_categoría` FROM `categoría` WHERE id_categoría=?");
            pt.setInt(1, id);
            ResultSet rss = pt.executeQuery();
            while(rss.next()){
                txtcategoria.setText(String.valueOf(id));

            }
        } catch (NumberFormatException | SQLException e) {

        }
    }//GEN-LAST:event_tablacategoriasMouseClicked

    private void btnbuscarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarproveedorActionPerformed
        // TODO add your handling code here:

        Connection cone = null;
        try {
            cone = getConnection();
            pt=cone.prepareStatement("Select * from categoría where id_categoría=?");
            pt.setString(1, txtbuscarproveedor.getText());
            ResultSet rsl = pt.executeQuery();

            if (rsl.next()) {

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

            Connection con=null;

            try {
                con=getConnection();
                pt=con.prepareStatement("DELETE FROM categoría WHERE id_categoría = ?");

                pt.setString(1,txtcategoria.getText());
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
    }//GEN-LAST:event_Btnlistar1ActionPerformed

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

                com.itextpdf.text.Font Titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                Titulo.setColor(BaseColor.BLUE);
                Titulo.setSize(20);

                Paragraph titulo = new Paragraph("Factura",Titulo);
                titulo.setAlignment(Paragraph.ALIGN_CENTER);

                com.itextpdf.text.Font sub = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                sub.setColor(BaseColor.BLUE);
                sub.setSize(11);

                Paragraph footer = new Paragraph("Monterosoft Copyright © 2022 todos los derechos reservados",sub);
                footer.setAlignment(Paragraph.ALIGN_RIGHT);

                PdfPTable tabla = new PdfPTable(6);
                tabla.setWidthPercentage(100f);
                tabla.setWidths(new float [] {1.5f,2.5f,2.0f,2.0f,2.0f,2.0f});
                tabla.setSpacingBefore(10);

                com.itextpdf.text.Font encabezado = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
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

            
            Paragraph Parf5 = new Paragraph();
            Parf5.add("_______________________________________________________");
            Parf5.setAlignment(Paragraph.ALIGN_LEFT);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Información del cliente. \n \n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.setSpacingBefore(10);

            com.itextpdf.text.Font Titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            Titulo.setColor(BaseColor.BLUE);
            Titulo.setSize(20);

            Paragraph titulo = new Paragraph("Factura",Titulo);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);

            com.itextpdf.text.Font sub = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            sub.setColor(BaseColor.BLUE);
            sub.setSize(11);

            Paragraph footer = new Paragraph("Monterosoft Copyright &copy; 2022 todos los derechos reservados",sub);
            footer.setAlignment(Paragraph.ALIGN_RIGHT);

            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100f);
            tabla.setWidths(new float [] {1.5f,2.5f,2.0f,2.0f,2.0f,2.0f});
            tabla.setSpacingBefore(10);

            com.itextpdf.text.Font encabezado = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
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
            java.util.logging.Logger.getLogger(home1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new home1().setVisible(true);
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
    private javax.swing.JTable TBComra;
    private javax.swing.JButton btnBuscarCompra;
    public javax.swing.JButton btnEliminarproveedor;
    private javax.swing.JButton btnGuadarCompra;
    private javax.swing.JButton btnGuardarCiente;
    private javax.swing.JButton btnGuardaventa;
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
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
    private javax.swing.JTable tablaVenta;
    private javax.swing.JTable tablacategorias;
    private javax.swing.JTable tablacliente;
    private javax.swing.JTable tablaproducto;
    private javax.swing.JTextField textbuscar;
    public static javax.swing.JLabel textocargo;
    public static javax.swing.JLabel textousuario;
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
    private javax.swing.JTextField txtProd;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtVenta;
    private javax.swing.JTextField txtbuscarproducto;
    private javax.swing.JTextField txtbuscarproveedor;
    private javax.swing.JTextField txtbuscarventa;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtcantidadcomp;
    private javax.swing.JTextField txtcategoria;
    private javax.swing.JTextField txtcosto;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtprovee;
    private javax.swing.JTextField txtrut;
    // End of variables declaration//GEN-END:variables
}
