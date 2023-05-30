/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package interfaz;

import funciones.ValidarDatos;
import gestortarjetas.TarjetaCredito;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Esta clase permite crear un JDialog con el que podremos añadir tarjetas de
 * credito a nuestra base de datos y a nuestro modelo.
 *
 * @author Heriberto Amezcua
 * @version 3.0
 * @since JDK 11.0.17
 */
public class JDAñadir extends javax.swing.JDialog {

    private TarjetaCredito tarjeta, nuevaTarjeta;
    private boolean selecModificar;
    private boolean haModificado;
    private boolean haAñadido;

    /**
     * Permite crear un JDialog con el que podremos añadir tarjetas de credito.
     */
    public JDAñadir(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializar();

        //Establezco los valores para el caso de añadir
        this.labelTitulo.setText("Crear Nueva Tarjeta");
        this.selecModificar = false;
        this.haAñadido = false;

        labelCaducidad.setVisible(false);
        labelCvv.setVisible(false);
        textCaducidad.setVisible(false);
        textCvv.setVisible(false);

    }

    /**
     * Permite crear un JDialog con el que podremos modificar tarjetas de
     * credito.
     *
     * @param parent
     * @param modal
     * @param tarjeta la tarjeta de credito que queremos modificar
     */
    public JDAñadir(java.awt.Frame parent, boolean modal, TarjetaCredito tarjeta) {
        super(parent, modal);
        initComponents();
        inicializar();

        //Establezco los valores para el caso de modificar
        this.selecModificar = true;
        this.haModificado = false;
        this.tarjeta = new TarjetaCredito(tarjeta);

        labelTitulo.setText("Modificando Tarjeta");
        textNombre.setText(tarjeta.getTitular());
        textNombre.setBackground(Color.gray);
        textNombre.setEditable(false);
        textNombre.setFocusable(false);
        textNif.setFocusable(false);
        textNif.setBackground(Color.gray);
        textNif.setText(tarjeta.getNif());
        textNif.setEditable(false);
        textNumero.setText(tarjeta.getNumeroTarjeta());
        textNumero.setEditable(false);
        textNumero.setBackground(Color.gray);
        textNumero.setFocusable(false);

        String mesCaducidad = (tarjeta.getMesCaducidad().length() < 2) ? ("0" + tarjeta.getMesCaducidad()) : tarjeta.getMesCaducidad();
        textCaducidad.setText(tarjeta.getAñoCaducidad() + "/" + mesCaducidad);
        textCaducidad.setEditable(false);
        textCaducidad.setBackground(Color.gray);
        textCaducidad.setFocusable(false);
        textCvv.setText(tarjeta.getCVV());
        textCvv.setEditable(false);
        textCvv.setBackground(Color.gray);
        textCvv.setFocusable(false);

        textPin.setText(tarjeta.getPin());
        textLimite.setText(tarjeta.getLimite() + "");

        btnAñadir.setText("MODIFICAR");
    }

    /**
     *
     * @return true si se ha creado y añadido una nueva tarjeta
     */
    public boolean getHaAñadido() {
        return this.haAñadido;
    }

    /**
     *
     * @return true si se ha modificado la tarjeta
     */
    public boolean getHaModificado() {
        return this.haModificado;
    }

    /**
     * Permite obtener la nueva tarjeta de credito creada.
     *
     * @return un objeto TarjetaCredito
     */
    public TarjetaCredito getNuevaTarjeta() {
        return new TarjetaCredito(this.nuevaTarjeta);
    }

    /**
     * Permite obtener la tarjeta de credito a modificar con los cambios
     * realizados.
     *
     * @return la tarjeta de credito a modificar con los cambios realizados
     */
    public TarjetaCredito getTarjeta() {
        return new TarjetaCredito(this.tarjeta);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNombre = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAñadir = new javax.swing.JButton();
        labelNif = new javax.swing.JLabel();
        labelNumero = new javax.swing.JLabel();
        labelPin = new javax.swing.JLabel();
        labelLimite = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        textNif = new javax.swing.JTextField();
        textNumero = new javax.swing.JTextField();
        textPin = new javax.swing.JTextField();
        labelTitulo = new javax.swing.JLabel();
        textLimite = new javax.swing.JTextField();
        labelCaducidad = new javax.swing.JLabel();
        labelCvv = new javax.swing.JLabel();
        textCaducidad = new javax.swing.JTextField();
        textCvv = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelNombre.setText("Nombre Completo:");

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAñadir.setText("AÑADIR");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        labelNif.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelNif.setText("NIF/CIF/NIE:");

        labelNumero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelNumero.setText("Número de Tarjeta:");

        labelPin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelPin.setText("PIN:");

        labelLimite.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelLimite.setText("Límite:");

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelTitulo.setText("jLabel1");

        labelCaducidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelCaducidad.setText("Caducidad:");

        labelCvv.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelCvv.setText("CVV:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNombre)
                                    .addComponent(labelNif))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textNif, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelCaducidad)
                                        .addComponent(labelNumero))
                                    .addComponent(labelCvv, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelPin, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelLimite, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textPin, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textCvv, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(labelTitulo)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelTitulo)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(labelNif))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(textNif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNumero)
                    .addComponent(textNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCaducidad)
                    .addComponent(textCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCvv)
                    .addComponent(textCvv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPin)
                    .addComponent(textPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLimite)
                    .addComponent(textLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * JButton que cierra el JDialog cuando hacemos click izquierdo sobre el.
     *
     * @param evt click izquierdo
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * JButton que nos permite crear o modificar una tarjeta de credito
     * dependiendo del caso.
     *
     * @param evt click izquierdo
     */
    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed

        String nombre = textNombre.getText(),
                nif = textNif.getText(),
                pin = textPin.getText(),
                limite = textLimite.getText(),
                numeroTarjeta = textNumero.getText();

        if (selecModificar) {
            if (esNumerico(limite)) {
                if (ValidarDatos.validarPin(pin) && ValidarDatos.validarLimite(Integer.parseInt(limite))) {
                    this.tarjeta.setLimite(Integer.parseInt(limite));
                    this.tarjeta.setPin(pin);
                    this.haModificado = true;
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Error al introducir los datos, no se ha podido modificar la tarjeta", "ERROR", JOptionPane.ERROR_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Error al introducir los datos, no se ha podido modificar la tarjeta", "ERROR", JOptionPane.ERROR_MESSAGE);

            }

        } else {
            if (esNumerico(limite)) {
                if (validarAtributos(nombre, nif, pin, Integer.parseInt(limite), numeroTarjeta)) {
                    this.nuevaTarjeta = new TarjetaCredito(nombre, nif, pin, Integer.parseInt(limite), numeroTarjeta);
                    this.haAñadido = true;
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Error al introducir los datos, no se ha podido añadir la nueva tarjeta", "ERROR", JOptionPane.ERROR_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Error al introducir los datos, no se ha podido añadir la nueva tarjeta", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        this.setVisible(false);
    }//GEN-LAST:event_btnAñadirActionPerformed

    /**
     * Metodo que nos permite validar los atributos de la tarjeta de credito
     * introducidos a través del JDialog para comprobar si se puede crear una
     * nueva tarjeta con ellos.
     *
     * @param nombre el nombre del titular
     * @param nif el nif/cif/nie del titular
     * @param pin el pin de la tarjeta
     * @param limite el limite de gasto de la tarjeta
     * @param numeroTarjeta el numero de la tarjeta de credito que sigue el
     * algoritmo de Luhn
     * @return true si el nombre del titular no es menor de 5 o mayor de 80
     * caracteres o esta compuesto de caracteres alfanumericos y si el
     * NIF/CIF/NIE es un documento que sigue un algoritmo que lo valida y si el
     * pin es mayor o igual a 4 caracteres y no tiene algun caracter que no es
     * un digito y si el limite de gasto es mayor o igual a 500 y menor a 5000 y
     * si el numero de la tarjeta de credito introducido sigue el algoritmo de
     * Luhn.
     */
    public boolean validarAtributos(String nombre, String nif, String pin, int limite, String numeroTarjeta) {
        return ValidarDatos.validarLimite(limite) && ValidarDatos.validarTitular(nombre) && ValidarDatos.validarDocumento(nif)
                && ValidarDatos.validarPin(pin) && ValidarDatos.comprobarNumeroTarjeta(numeroTarjeta);
    }

    /**
     * Permite comprobar si un string podria ser numerico entero
     *
     * @param s el string a comprobar
     * @return true si todos los caracteres del string a comprobar son digitos.
     */
    private static boolean esNumerico(String s) {
        if (s == null || s.equals("")) {
            return false;
        }

        return s.chars().allMatch(Character::isDigit);
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
            java.util.logging.Logger.getLogger(JDAñadir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDAñadir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDAñadir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDAñadir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDAñadir dialog = new JDAñadir(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel labelCaducidad;
    private javax.swing.JLabel labelCvv;
    private javax.swing.JLabel labelLimite;
    private javax.swing.JLabel labelNif;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelPin;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTextField textCaducidad;
    private javax.swing.JTextField textCvv;
    private javax.swing.JTextField textLimite;
    private javax.swing.JTextField textNif;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textNumero;
    private javax.swing.JTextField textPin;
    // End of variables declaration//GEN-END:variables

    /**
     * Metodo que nos permite alterar el estado del JDialog cuando queramos
     * inicializarlo con estos valores.
     */
    private void inicializar() {
        setLocationRelativeTo(null);
        ImageIcon mainIcon = new ImageIcon("iconos/icono.png");
        setIconImage(mainIcon.getImage());
        setTitle("Gestor de Tarjetas");
    }

}
