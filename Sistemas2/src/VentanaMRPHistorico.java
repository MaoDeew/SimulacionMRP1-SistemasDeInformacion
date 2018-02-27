
import Controlador.Consultas;
import Controlador.MateriaPrimaProducto;
import Controlador.MateriaPrimaTotal;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mabet
 */
public class VentanaMRPHistorico extends javax.swing.JFrame {

    DefaultTableModel modeloTablaMateriaPrimaPorProducto;
    DefaultTableModel modeloTablaMateriaPrimaProductoA;
    DefaultTableModel modeloTablaMateriaPrimaProductoB;
    DefaultTableModel modeloTablaMateriaPrimaProductoC;
    DefaultTableModel modeloTablaMateriaPrimaProductoD;

    DefaultTableModel modeloTablaMateriasPrimasProductoA;
    DefaultTableModel modeloTablaMateriasPrimasProductoB;
    DefaultTableModel modeloTablaMateriasPrimasProductoC;
    DefaultTableModel modeloTablaMateriasPrimasProductoD;

    Consultas c = new Consultas();
    ArrayList<MateriaPrimaTotal> listadoMateriaPrimasPorProducto = c.listadoMateriaPrima();
    ArrayList<MateriaPrimaProducto> listadoMPProductoA = c.listadoMateriaPrimaPorProducto(1);
    ArrayList<MateriaPrimaProducto> listadoMPProductoB = c.listadoMateriaPrimaPorProducto(2);
    ArrayList<MateriaPrimaProducto> listadoMPProductoC = c.listadoMateriaPrimaPorProducto(3);
    ArrayList<MateriaPrimaProducto> listadoMPProductoD = c.listadoMateriaPrimaPorProducto(4);

    float[] valoresMostrarA = new float[12];
    float[] valoresMostrarB = new float[12];
    float[] valoresMostrarC = new float[12];
    float[] valoresMostrarD = new float[12];
    
        float totalPapaTodos = 0;
        float totalSalTodos = 0;
        float totalSaborizanteTodos = 0;
        float totalAceiteTodos = 0;
        float totalInosinatoTodos = 0;
        
        int totalMRP = 0;

    public VentanaMRPHistorico(String[] mostrarA, String[] mostrarB, String[] mostrarC, String[] mostrarD) {
        initComponents();
        String cabeceraPrimeraTabla[] = {"Producto", "Total Materias Primas"};
        String cabeceraTablaMPProductos[] = {"Nombre MP", "Cantidad", "MP Unidad/Mes"};
        String datos[][] = {};
        modeloTablaMateriaPrimaPorProducto = new DefaultTableModel(datos, cabeceraPrimeraTabla);

        modeloTablaMateriaPrimaProductoA = new DefaultTableModel(datos, cabeceraTablaMPProductos);
        modeloTablaMateriaPrimaProductoB = new DefaultTableModel(datos, cabeceraTablaMPProductos);
        modeloTablaMateriaPrimaProductoC = new DefaultTableModel(datos, cabeceraTablaMPProductos);
        modeloTablaMateriaPrimaProductoD = new DefaultTableModel(datos, cabeceraTablaMPProductos);

        modeloTablaMateriasPrimasProductoA = new DefaultTableModel(datos, 0);
        modeloTablaMateriasPrimasProductoB = new DefaultTableModel(datos, 0);
        modeloTablaMateriasPrimasProductoC = new DefaultTableModel(datos, 0);
        modeloTablaMateriasPrimasProductoD = new DefaultTableModel(datos, 0);

        tablaMateriaPrimaPorProducto.setModel(modeloTablaMateriaPrimaPorProducto);

        tablaMPProductoA.setModel(modeloTablaMateriaPrimaProductoA);
        tablaMPProductoB.setModel(modeloTablaMateriaPrimaProductoB);
        tablaMPProductoC.setModel(modeloTablaMateriaPrimaProductoC);
        tablaMPProductoD.setModel(modeloTablaMateriaPrimaProductoD);

        tablaMateriasPrimasProductoA.setModel(modeloTablaMateriasPrimasProductoA);
        tablaMateriasPrimasProductoB.setModel(modeloTablaMateriasPrimasProductoB);
        tablaMateriasPrimasProductoC.setModel(modeloTablaMateriasPrimasProductoC);
        tablaMateriasPrimasProductoD.setModel(modeloTablaMateriasPrimasProductoD);

        cargarTablas();
        cargarValoresTablaHistorico(mostrarA, mostrarB, mostrarC, mostrarD);

    }

    private void cargarValoresTablaHistorico(String[] mostrarA, String[] mostrarB, String[] mostrarC, String[] mostrarD) {

        for (int i = 0; i < 12; i++) {
            this.valoresMostrarA[i] = Float.parseFloat(mostrarA[i]);
            this.valoresMostrarB[i] = Float.parseFloat(mostrarB[i]);
            this.valoresMostrarC[i] = Float.parseFloat(mostrarC[i]);
            this.valoresMostrarD[i] = Float.parseFloat(mostrarD[i]);

        }

    }

    private void cargarTablas() {

        for (int i = 0; i < listadoMateriaPrimasPorProducto.size(); i++) {
            MateriaPrimaTotal mp = listadoMateriaPrimasPorProducto.get(i);
            String nombre = mp.getNombreProducto();
            String total = String.valueOf(mp.getTotalMateriaPrimaEnProducto());
            String[] mostrarTabla = {nombre, total};
            modeloTablaMateriaPrimaPorProducto.addRow(mostrarTabla);

        }

        float totalMPProductoA = Float.parseFloat(String.valueOf(tablaMateriaPrimaPorProducto.getValueAt(0, 1)));
        float totalMPProductoB = Float.parseFloat(String.valueOf(tablaMateriaPrimaPorProducto.getValueAt(1, 1)));
        float totalMPProductoC = Float.parseFloat(String.valueOf(tablaMateriaPrimaPorProducto.getValueAt(2, 1)));
        float totalMPProductoD = Float.parseFloat(String.valueOf(tablaMateriaPrimaPorProducto.getValueAt(3, 1)));

        //MP Producto A
        for (int i = 0; i < listadoMPProductoA.size(); i++) {
            MateriaPrimaProducto mpp = listadoMPProductoA.get(i);
            String nombre = mpp.getNombreMateriaPrima();
            String cantidad = String.valueOf(mpp.getCantidad());
            String mpPorMes = String.valueOf(mpp.getCantidad() / totalMPProductoA);
            String[] mostrarTabla = {nombre, cantidad, mpPorMes};
            modeloTablaMateriaPrimaProductoA.addRow(mostrarTabla);

        }

        //MP Producto B
        for (int i = 0; i < listadoMPProductoB.size(); i++) {
            MateriaPrimaProducto mpp = listadoMPProductoB.get(i);
            String nombre = mpp.getNombreMateriaPrima();
            String cantidad = String.valueOf(mpp.getCantidad());
            String mpPorMes = String.valueOf(mpp.getCantidad() / totalMPProductoB);
            String[] mostrarTabla = {nombre, cantidad, mpPorMes};
            modeloTablaMateriaPrimaProductoB.addRow(mostrarTabla);

        }

        //MP Producto C
        for (int i = 0; i < listadoMPProductoC.size(); i++) {
            MateriaPrimaProducto mpp = listadoMPProductoC.get(i);
            String nombre = mpp.getNombreMateriaPrima();
            String cantidad = String.valueOf(mpp.getCantidad());
            String mpPorMes = String.valueOf(mpp.getCantidad() / totalMPProductoC);
            String[] mostrarTabla = {nombre, cantidad, mpPorMes};
            modeloTablaMateriaPrimaProductoC.addRow(mostrarTabla);

        }

        //MP Producto A
        for (int i = 0; i < listadoMPProductoD.size(); i++) {
            MateriaPrimaProducto mpp = listadoMPProductoD.get(i);
            String nombre = mpp.getNombreMateriaPrima();
            String cantidad = String.valueOf(mpp.getCantidad());
            String mpPorMes = String.valueOf(mpp.getCantidad() / totalMPProductoD);
            String[] mostrarTabla = {nombre, cantidad, mpPorMes};
            modeloTablaMateriaPrimaProductoD.addRow(mostrarTabla);

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

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMateriaPrimaPorProducto = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaMPProductoA = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaMPProductoB = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaMPProductoC = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaMPProductoD = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaMateriasPrimasProductoA = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaMateriasPrimasProductoB = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaMateriasPrimasProductoC = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaMateriasPrimasProductoD = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        botonCargarValores = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        labelTotalPapa = new javax.swing.JLabel();
        labelTotalSal = new javax.swing.JLabel();
        labelTotalSaborizante = new javax.swing.JLabel();
        labelTotalAceite = new javax.swing.JLabel();
        labelTotalInosinato = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        labelTotalMRP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaMateriaPrimaPorProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaMateriaPrimaPorProducto);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("MRP HISTORICO");

        tablaMPProductoA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaMPProductoA);

        jLabel2.setText("Total Materia Prima por Producto:");

        jLabel3.setText("Materia Prima por Producto A:");

        tablaMPProductoB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tablaMPProductoB);

        jLabel4.setText("Materia Prima por Producto B:");

        jLabel5.setText("Materia Prima por Producto C:");

        tablaMPProductoC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tablaMPProductoC);

        jLabel6.setText("Materia Prima por Producto D:");

        tablaMPProductoD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tablaMPProductoD);

        tablaMateriasPrimasProductoA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tablaMateriasPrimasProductoA);

        tablaMateriasPrimasProductoB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tablaMateriasPrimasProductoB);

        tablaMateriasPrimasProductoC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tablaMateriasPrimasProductoC);

        tablaMateriasPrimasProductoD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(tablaMateriasPrimasProductoD);

        jLabel7.setText("Producto A");

        jLabel8.setText("Producto B");

        jLabel9.setText("Producto C");

        jLabel10.setText("Producto D");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane8)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jScrollPane10.setViewportView(jPanel1);

        botonCargarValores.setText("Cargar Valores");
        botonCargarValores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarValoresActionPerformed(evt);
            }
        });

        jLabel11.setText("Total Papa:");

        jLabel12.setText("Total Sal: ");

        jLabel13.setText("Total Saborizante: ");

        jLabel14.setText("Total Aceite: ");

        jLabel15.setText("Total Inosinato: ");

        labelTotalPapa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotalPapa.setText("0");

        labelTotalSal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotalSal.setText("0");

        labelTotalSaborizante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotalSaborizante.setText("0");

        labelTotalAceite.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotalAceite.setText("0");

        labelTotalInosinato.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotalInosinato.setText("0");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel16.setText("Σ MRP");

        labelTotalMRP.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        labelTotalMRP.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botonCargarValores, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelTotalPapa))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelTotalSal, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelTotalSaborizante, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelTotalAceite, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelTotalInosinato, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addGap(50, 50, 50))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelTotalMRP)
                                .addGap(41, 41, 41))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 38, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonCargarValores)
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(labelTotalPapa))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(labelTotalSal))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(labelTotalSaborizante))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(labelTotalAceite))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(labelTotalInosinato))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(labelTotalMRP))
                        .addGap(30, 30, 30))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCargarValoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarValoresActionPerformed
        botonCargarValores.setEnabled(false);
        llenarMateriasPrimasProductos(tablaMPProductoA, valoresMostrarA, modeloTablaMateriasPrimasProductoA);
        llenarMateriasPrimasProductos(tablaMPProductoB, valoresMostrarB, modeloTablaMateriasPrimasProductoB);
        llenarMateriasPrimasProductos(tablaMPProductoC, valoresMostrarC, modeloTablaMateriasPrimasProductoC);
        llenarMateriasPrimasProductos(tablaMPProductoD, valoresMostrarD, modeloTablaMateriasPrimasProductoD);
        
        totalPapaTodos = Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoA.getValueAt(0, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoB.getValueAt(0, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoC.getValueAt(0, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoD.getValueAt(0, 12)));
        
        totalSalTodos = Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoA.getValueAt(1, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoB.getValueAt(1, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoC.getValueAt(1, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoD.getValueAt(1, 12)));
        
        totalSaborizanteTodos = Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoA.getValueAt(2, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoB.getValueAt(2, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoC.getValueAt(2, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoD.getValueAt(2, 12)));
        
        totalAceiteTodos = Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoA.getValueAt(3, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoB.getValueAt(3, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoC.getValueAt(3, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoD.getValueAt(3, 12)));
        
        totalInosinatoTodos = Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoA.getValueAt(4, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoB.getValueAt(4, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoC.getValueAt(4, 12)))+
                Float.parseFloat(String.valueOf(tablaMateriasPrimasProductoD.getValueAt(4, 12)));
        
        
        labelTotalPapa.setText(String.valueOf(totalPapaTodos));
        labelTotalSal.setText(String.valueOf(totalSalTodos));
        labelTotalSaborizante.setText(String.valueOf(totalSaborizanteTodos));
        labelTotalAceite.setText(String.valueOf(totalAceiteTodos));
        labelTotalInosinato.setText(String.valueOf(totalInosinatoTodos));
        
        totalMRP = Math.round(totalPapaTodos+totalSalTodos+totalSaborizanteTodos+totalAceiteTodos+totalInosinatoTodos);
        labelTotalMRP.setText(String.valueOf(totalMRP));
    }//GEN-LAST:event_botonCargarValoresActionPerformed

    /**
     * @param args the command line arguments
     */
    private void llenarMateriasPrimasProductos(JTable tablaProducto, float[] valoresMostrar, DefaultTableModel modelo) {

        float papaUnidadPorMesProducto = Float.parseFloat(String.valueOf(tablaProducto.getValueAt(0, 2)));
        float salUnidadPorMesProducto = Float.parseFloat(String.valueOf(tablaProducto.getValueAt(1, 2)));
        float saborizanteUnidadPorMesProducto = Float.parseFloat(String.valueOf(tablaProducto.getValueAt(2, 2)));
        float aceiteUnidadPorMesProducto = Float.parseFloat(String.valueOf(tablaProducto.getValueAt(3, 2)));
        float inosinatoUnidadPorMesProducto = Float.parseFloat(String.valueOf(tablaProducto.getValueAt(4, 2)));

        Vector<Float> cantidadEnero = new Vector<>();
        Vector<Float> cantidadFebrero = new Vector<>();
        Vector<Float> cantidadMarzo = new Vector<>();
        Vector<Float> cantidadAbril = new Vector<>();
        Vector<Float> cantidadMayo = new Vector<>();
        Vector<Float> cantidadJunio = new Vector<>();
        Vector<Float> cantidadJulio = new Vector<>();
        Vector<Float> cantidadAgosto = new Vector<>();
        Vector<Float> cantidadSeptiembre = new Vector<>();
        Vector<Float> cantidadOctubre = new Vector<>();
        Vector<Float> cantidadNoviembre = new Vector<>();
        Vector<Float> cantidadDiciembre = new Vector<>();
        Vector<Float> cantidadTotal = new Vector<>();

        cantidadEnero.add(valoresMostrar[0] * papaUnidadPorMesProducto);
        cantidadEnero.add(valoresMostrar[0] * salUnidadPorMesProducto);
        cantidadEnero.add(valoresMostrar[0] * saborizanteUnidadPorMesProducto);
        cantidadEnero.add(valoresMostrar[0] * aceiteUnidadPorMesProducto);
        cantidadEnero.add(valoresMostrar[0] * inosinatoUnidadPorMesProducto);

        cantidadFebrero.add(valoresMostrar[1] * papaUnidadPorMesProducto);
        cantidadFebrero.add(valoresMostrar[1] * salUnidadPorMesProducto);
        cantidadFebrero.add(valoresMostrar[1] * saborizanteUnidadPorMesProducto);
        cantidadFebrero.add(valoresMostrar[1] * aceiteUnidadPorMesProducto);
        cantidadFebrero.add(valoresMostrar[1] * inosinatoUnidadPorMesProducto);
        
        cantidadMarzo.add(valoresMostrar[2] * papaUnidadPorMesProducto);
        cantidadMarzo.add(valoresMostrar[2] * salUnidadPorMesProducto);
        cantidadMarzo.add(valoresMostrar[2] * saborizanteUnidadPorMesProducto);
        cantidadMarzo.add(valoresMostrar[2] * aceiteUnidadPorMesProducto);
        cantidadMarzo.add(valoresMostrar[2] * inosinatoUnidadPorMesProducto);
        
        cantidadAbril.add(valoresMostrar[3] * papaUnidadPorMesProducto);
        cantidadAbril.add(valoresMostrar[3] * salUnidadPorMesProducto);
        cantidadAbril.add(valoresMostrar[3] * saborizanteUnidadPorMesProducto);
        cantidadAbril.add(valoresMostrar[3] * aceiteUnidadPorMesProducto);
        cantidadAbril.add(valoresMostrar[3] * inosinatoUnidadPorMesProducto);
        
        cantidadMayo.add(valoresMostrar[4] * papaUnidadPorMesProducto);
        cantidadMayo.add(valoresMostrar[4] * salUnidadPorMesProducto);
        cantidadMayo.add(valoresMostrar[4] * saborizanteUnidadPorMesProducto);
        cantidadMayo.add(valoresMostrar[4] * aceiteUnidadPorMesProducto);
        cantidadMayo.add(valoresMostrar[4] * inosinatoUnidadPorMesProducto);
        
        cantidadJunio.add(valoresMostrar[5] * papaUnidadPorMesProducto);
        cantidadJunio.add(valoresMostrar[5] * salUnidadPorMesProducto);
        cantidadJunio.add(valoresMostrar[5] * saborizanteUnidadPorMesProducto);
        cantidadJunio.add(valoresMostrar[5] * aceiteUnidadPorMesProducto);
        cantidadJunio.add(valoresMostrar[5] * inosinatoUnidadPorMesProducto);
        
        cantidadJulio.add(valoresMostrar[6] * papaUnidadPorMesProducto);
        cantidadJulio.add(valoresMostrar[6] * salUnidadPorMesProducto);
        cantidadJulio.add(valoresMostrar[6] * saborizanteUnidadPorMesProducto);
        cantidadJulio.add(valoresMostrar[6] * aceiteUnidadPorMesProducto);
        cantidadJulio.add(valoresMostrar[6] * inosinatoUnidadPorMesProducto);
        
        cantidadAgosto.add(valoresMostrar[7] * papaUnidadPorMesProducto);
        cantidadAgosto.add(valoresMostrar[7] * salUnidadPorMesProducto);
        cantidadAgosto.add(valoresMostrar[7] * saborizanteUnidadPorMesProducto);
        cantidadAgosto.add(valoresMostrar[7] * aceiteUnidadPorMesProducto);
        cantidadAgosto.add(valoresMostrar[7] * inosinatoUnidadPorMesProducto);
        
        cantidadSeptiembre.add(valoresMostrar[8] * papaUnidadPorMesProducto);
        cantidadSeptiembre.add(valoresMostrar[8] * salUnidadPorMesProducto);
        cantidadSeptiembre.add(valoresMostrar[8] * saborizanteUnidadPorMesProducto);
        cantidadSeptiembre.add(valoresMostrar[8] * aceiteUnidadPorMesProducto);
        cantidadSeptiembre.add(valoresMostrar[8] * inosinatoUnidadPorMesProducto);

        cantidadOctubre.add(valoresMostrar[9] * papaUnidadPorMesProducto);
        cantidadOctubre.add(valoresMostrar[9] * salUnidadPorMesProducto);
        cantidadOctubre.add(valoresMostrar[9] * saborizanteUnidadPorMesProducto);
        cantidadOctubre.add(valoresMostrar[9] * aceiteUnidadPorMesProducto);
        cantidadOctubre.add(valoresMostrar[9] * inosinatoUnidadPorMesProducto);
        
        cantidadNoviembre.add(valoresMostrar[10] * papaUnidadPorMesProducto);
        cantidadNoviembre.add(valoresMostrar[10] * salUnidadPorMesProducto);
        cantidadNoviembre.add(valoresMostrar[10] * saborizanteUnidadPorMesProducto);
        cantidadNoviembre.add(valoresMostrar[10] * aceiteUnidadPorMesProducto);
        cantidadNoviembre.add(valoresMostrar[10] * inosinatoUnidadPorMesProducto);
        
        cantidadDiciembre.add(valoresMostrar[11] * papaUnidadPorMesProducto);
        cantidadDiciembre.add(valoresMostrar[11] * salUnidadPorMesProducto);
        cantidadDiciembre.add(valoresMostrar[11] * saborizanteUnidadPorMesProducto);
        cantidadDiciembre.add(valoresMostrar[11] * aceiteUnidadPorMesProducto);
        cantidadDiciembre.add(valoresMostrar[11] * inosinatoUnidadPorMesProducto);
        
        float totalPapa = 0;
        float totalSal = 0;
        float totalSaborizante = 0;
        float totalAceite = 0;
        float totalInosinato = 0;
        
        for (int i = 0; i < 12; i++) {
            totalPapa = (valoresMostrar[i]*papaUnidadPorMesProducto)+totalPapa;
            totalSal = (valoresMostrar[i]*salUnidadPorMesProducto)+totalSal;
            totalSaborizante = (valoresMostrar[i]*saborizanteUnidadPorMesProducto)+totalSaborizante;
            totalAceite = (valoresMostrar[i]*aceiteUnidadPorMesProducto)+totalAceite;
            totalInosinato = (valoresMostrar[i]*inosinatoUnidadPorMesProducto)+totalInosinato;
        }
        
        cantidadTotal.add(totalPapa);
        cantidadTotal.add(totalSal);
        cantidadTotal.add(totalSaborizante);
        cantidadTotal.add(totalAceite);
        cantidadTotal.add(totalInosinato);
        
        
        

        modelo.addColumn("Enero", cantidadEnero);
        modelo.addColumn("Febrero", cantidadFebrero);
        modelo.addColumn("Marzo", cantidadMarzo);
        modelo.addColumn("Abril", cantidadAbril);
        modelo.addColumn("Mayo", cantidadMayo);
        modelo.addColumn("Junio", cantidadJunio);
        modelo.addColumn("Julio", cantidadJulio);
        modelo.addColumn("Agosto", cantidadAgosto);
        modelo.addColumn("Septiembre", cantidadSeptiembre);
        modelo.addColumn("Octubre", cantidadOctubre);
        modelo.addColumn("Noviembre", cantidadNoviembre);
        modelo.addColumn("Diciembre", cantidadDiciembre);
        modelo.addColumn("TOTAL", cantidadTotal);
        
        
    }

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
            java.util.logging.Logger.getLogger(VentanaMRPHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaMRPHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaMRPHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaMRPHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaMRPHistorico(null, null, null, null).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCargarValores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel labelTotalAceite;
    private javax.swing.JLabel labelTotalInosinato;
    private javax.swing.JLabel labelTotalMRP;
    private javax.swing.JLabel labelTotalPapa;
    private javax.swing.JLabel labelTotalSaborizante;
    private javax.swing.JLabel labelTotalSal;
    private javax.swing.JTable tablaMPProductoA;
    private javax.swing.JTable tablaMPProductoB;
    private javax.swing.JTable tablaMPProductoC;
    private javax.swing.JTable tablaMPProductoD;
    private javax.swing.JTable tablaMateriaPrimaPorProducto;
    private javax.swing.JTable tablaMateriasPrimasProductoA;
    private javax.swing.JTable tablaMateriasPrimasProductoB;
    private javax.swing.JTable tablaMateriasPrimasProductoC;
    private javax.swing.JTable tablaMateriasPrimasProductoD;
    // End of variables declaration//GEN-END:variables
}
