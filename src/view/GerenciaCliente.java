/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ClienteDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cliente;

/**
 *
 * @author brunodepaulo
 */
public class GerenciaCliente extends javax.swing.JFrame {

    
    ClienteDAO clienteDao;
    
    public GerenciaCliente() throws SQLException, ClassNotFoundException {
        this.clienteDao = new ClienteDAO();
        initComponents();
        this.setVisible(true);
        ArrayList<Cliente> clientes = clienteDao.lerBD();
        
        DefaultTableModel dtmClientes = (DefaultTableModel) jTableClientes.getModel();
        int i =0;
        while(clientes.get(i)!=null){
            Object[] dados = {clientes.get(i).getNome(), clientes.get(i).getCpf(), clientes.get(i).getEmail(), clientes.get(i).getTel(), clientes.get(i).getEndereco()};
            dtmClientes.addRow(dados);
            i++;
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
        jTableClientes = new javax.swing.JTable();
        jButtonSalvar = new javax.swing.JButton();
        jButtonDeletar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF", "Email", "Telefone", "Endereço"
            }
        ));
        jScrollPane1.setViewportView(jTableClientes);

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSalvarMouseClicked(evt);
            }
        });

        jButtonDeletar.setText("Deletar");
        jButtonDeletar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonDeletarMouseClicked(evt);
            }
        });

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("Gerenciamento de Clientes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonDeletar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSalvar)
                        .addGap(243, 243, 243)
                        .addComponent(jButtonVoltar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonDeletar)
                    .addComponent(jButtonVoltar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSalvarMouseClicked
        int i;
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        for(i=0;i<jTableClientes.getModel().getRowCount(); i++){
            if (!jTableClientes.getModel().getValueAt(i, 0).equals("") );//verifica se a celula esta vazia
                //Objeto para ser adicionado ao arraylist
                Cliente cliente = new Cliente();
                cliente.setNome((String) jTableClientes.getModel().getValueAt(i, 0));
                cliente.setCpf((String) jTableClientes.getModel().getValueAt(i, 1));
                cliente.setEmail((String) jTableClientes.getModel().getValueAt(i, 2));
                cliente.setTel((String) jTableClientes.getModel().getValueAt(i, 3));
                cliente.setEndereco((String) jTableClientes.getModel().getValueAt(i, 4));
                
                
                //adiciona no arraylist
                clientes.add(cliente);
                clienteDao.atualizarBD(cliente);
        }
        this.dispose();
    }//GEN-LAST:event_jButtonSalvarMouseClicked

    private void jButtonDeletarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDeletarMouseClicked
//      clienteDao.delete();
//        try {
//            clienteDao.read();
//            DefaultTableModel dtmClientes = (DefaultTableModel) jTableClientes.getModel();
//            while (dtmClientes.getRowCount()>0) dtmClientes.removeRow(0);
//            int i =0;
//            while(clientes.get(i)!=null){
//                Object[] dados = {clientes.get(i).getNome(), clientes.get(i).getCpf(), clientes.get(i).getEmail(), clientes.get(i).getTel(), clientes.get(i).getEndereco()};
//                dtmClientes.addRow(dados);
//                i++;            
//        }   
//        } catch (IOException ex) {
//            Logger.getLogger(AtualizaCliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String searchCPF = JOptionPane.showInputDialog("Digite o CPF do cliente que deseja apagar:");

        clienteDao.deletarBD(searchCPF);
        this.dispose();
    }//GEN-LAST:event_jButtonDeletarMouseClicked

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

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
            java.util.logging.Logger.getLogger(GerenciaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GerenciaCliente().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(GerenciaCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GerenciaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDeletar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    // End of variables declaration//GEN-END:variables
}
