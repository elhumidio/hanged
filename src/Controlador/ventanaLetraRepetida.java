/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ventanaLetraRepetida.java
 *
 * Created on 18/06/2010, 22:32:53
 */

package Controlador;

/**
 *
 * @author Damian
 */
public class ventanaLetraRepetida extends javax.swing.JFrame {

    /** Creates new form ventanaLetraRepetida */
    public ventanaLetraRepetida() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNuevaLetraRepetida = new javax.swing.JTextField();
        btnNuevaLetraII = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("Letra repetida, intentar con otra...");

        txtNuevaLetraRepetida.setMinimumSize(new java.awt.Dimension(40, 20));

        btnNuevaLetraII.setText("Cargar");
        btnNuevaLetraII.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaLetraIIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(41, 41, 41))
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(txtNuevaLetraRepetida, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnNuevaLetraII)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNuevaLetraRepetida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevaLetraII))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevaLetraIIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaLetraIIActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaLetraIIActionPerformed

    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ventanaLetraRepetida().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevaLetraII;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JTextField txtNuevaLetraRepetida;
    // End of variables declaration//GEN-END:variables
  
    // End of variables declaration

    /**
     * @return the txtNuevaLetraRepetida
     */
    public javax.swing.JTextField getTxtNuevaLetraRepetida() {
        return txtNuevaLetraRepetida;
    }

    /**
     * @param txtNuevaLetraRepetida the txtNuevaLetraRepetida to set
     */
    public void setTxtNuevaLetraRepetida(javax.swing.JTextField txtNuevaLetraRepetida) {
        this.txtNuevaLetraRepetida = txtNuevaLetraRepetida;
    }

}
