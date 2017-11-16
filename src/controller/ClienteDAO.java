/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {
   private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
   private File fileCliente = new File("clientes.txt");
   private File fileClienteBin = new File("clientesBin.obj");
   private ObjectOutputStream oos = CriaEscritorBinario(fileClienteBin, true);
   private ObjectInputStream ois = CriaLeitorBinario(fileClienteBin);
   private Connection conexao = null;
   private PreparedStatement pstdados = null;
   
   public void create(Cliente cliente) throws IOException{ //correto é colocar try-catch e a função ser void
       for(int i=0; i<getClientes().size(); i++){
           if(getClientes().get(i).getCpf() == cliente.getCpf()){
               throw new IOException();
           }
       }
        getClientes().add(cliente);
       gravarTxt(getClientes(), true);
   }
   
   public void readTxt() throws FileNotFoundException, IOException{
        FileReader leitor = new FileReader(fileCliente);
        BufferedReader leitorBuff = new BufferedReader(leitor);
        String line;
        while((line = leitorBuff.readLine())!=null){
            String [] clientes = line.split(",");
            Cliente cliente = new Cliente();
            cliente.setNome(clientes[0]);
            cliente.setCpf(clientes[1]);
            cliente.setEmail(clientes[2]);
            cliente.setEndereco(clientes[3]);
            cliente.setTel(clientes[4]);
            this.getClientes().add(cliente);   
        }
        leitorBuff.close();
        for(int i=0; i<this.getClientes().size(); i++){
            System.out.println("Nome: "+this.getClientes().get(i).getNome()+
                    ", CPF: "+this.getClientes().get(i).getCpf()+
                    ", Email: "+this.getClientes().get(i).getEmail()+
                    ", Endereço: "+this.getClientes().get(i).getEndereco()+
                    ", Telefone: "+this.getClientes().get(i).getTel());
        }
   }
 
   
   public Cliente escolheAtualizar(){
       this.clientes = leBinario(ois);
       String searchCPF = JOptionPane.showInputDialog("Qual cpf deseja editar?");
       for(int i=0; i < this.getClientes().size(); i++){
           if(this.getClientes().get(i).getCpf()==searchCPF){
               return getClientes().get(i);
           }
       }
       return null;
   }
   
   public void delete(){
        String searchCPF = JOptionPane.showInputDialog("Qual cpf deseja apagar?");
        for(int i=0; i < this.getClientes().size(); i++){
            if(this.getClientes().get(i).getCpf().equals(searchCPF)){
                getClientes().remove(i);
            }    
        }
       try {
           gravarTxt(getClientes(), false);
       } catch (IOException ex) {
           Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   public void gravarTxt(ArrayList<Cliente> clientes, boolean append) throws IOException{
       FileWriter escritor = new FileWriter(fileCliente, append);
       BufferedWriter escritorBuff = new BufferedWriter(escritor);
       for(int i=0; i<clientes.size(); i++){ // caminha no array list
            escritorBuff.write(clientes.get(i).getNome()+","+
                clientes.get(i).getCpf()+","+
                clientes.get(i).getEmail()+","+
                clientes.get(i).getEndereco()+","+
                clientes.get(i).getTel()+"\n");
       }
        escritorBuff.flush();
        escritorBuff.close();
    }
   
   public void gravaBin(ArrayList<Cliente> clientes, boolean flush) throws IOException{
       oos.writeObject(clientes);
       if(flush){
           oos.flush();
       }
   }
  
   public ArrayList<Cliente> leBinario(ObjectInputStream ois){ //editar o array da classe
       ArrayList<Cliente> clientes = null;
        try {
            clientes = (ArrayList<Cliente>) ois.readObject();
            for (int i=0;i<clientes.size(); i++){
                System.out.println("Nome: "+this.getClientes().get(i).getNome()+
                    ", CPF: "+this.getClientes().get(i).getCpf()+
                    ", Email: "+this.getClientes().get(i).getEmail()+
                    ", Endereço: "+this.getClientes().get(i).getEndereco()+
                    ", Telefone: "+this.getClientes().get(i).getTel());
            }
        } catch (ClassNotFoundException erro) {
            System.out.println("Classe nao encontrada. " + erro);
        } catch (java.io.EOFException erro) {
            System.out.println("Final de arquivo. " + erro);
        } catch (IOException erro) {
            System.out.println("Erro na leitura do objeto. " + erro);
        } finally {
            return clientes;
        }
   }
   
    
   public static ObjectOutputStream CriaEscritorBinario(File arquivo, boolean append) {
        ObjectOutputStream out = null;
        try {
            FileOutputStream fos = new FileOutputStream(arquivo, append);
            out = new ObjectOutputStream(fos);
        } catch (IOException erro) {
            System.out.println("Erro ao criar arquivo. " + erro);
        }
        return out;
    }
   
   public static ObjectInputStream CriaLeitorBinario(File arquivo) {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(arquivo);
            ois = new ObjectInputStream(fis);
        } catch (IOException erro) {
            System.out.println("Erro ao ler arquivo. " + erro);
        }
        return ois;
    }

   
   public boolean verifica(File arquivo){
       if(arquivo.isFile()){
           return true;
       }
       return false;
   }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    
    //--- Conexão com BD ---//
    
    public boolean bdConnect(){
        try{
            String usuario = "postgres";
            String senha = "postgres";
            
            Class.forName("org.postgresql.Driver");
            String urlconexao = "jdbc:postgresql://localhost:5433/bdfarmacia";
            
            conexao = DriverManager.getConnection(urlconexao, usuario, senha);

        } catch(ClassNotFoundException erro){
            System.out.println("Erro no carregamento do driver " + erro);
            return false;            
        } catch(SQLException erro){
            System.out.println("Erro no SQL " + erro);
            return false;
        }
        return true;
    }

    
    public void inserirBD(Cliente cliente){
        try {
            
            bdConnect();
            
            String sql = "INSERT INTO clientes (nome, cpf, endereco, tel, email) VALUES (?,?,?,?,?)";
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = conexao.prepareStatement(sql, tipo, concorrencia);   
            
            pstdados.setString(1, cliente.getNome());
            pstdados.setString(2, cliente.getCpf());
            pstdados.setString(3, cliente.getEndereco());
            pstdados.setString(4, cliente.getTel());
            pstdados.setString(5, cliente.getEmail());
            
            
            int rowsInserted = pstdados.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Novo cliente foi inserido com sucesso.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletarBD(String id){
        try{
            bdConnect();
            
            String sql = "DELETE FROM clientes WHERE cpf=?";
            
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("O cliente foi deletado com sucesso.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Cliente> lerBD(){
        
        bdConnect();
                
        PreparedStatement pst = null;
        ResultSet rs = null;
        int cont = 0;
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
                
        try{
            pst = conexao.prepareStatement("SELECT * FROM clientes");
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString(1));
                cliente.setCpf(rs.getString(2));
                cliente.setEndereco(rs.getString(3));
                cliente.setTel(rs.getString(4));
                cliente.setEmail(rs.getString(5));
                clientes.add(cliente);
                cont++;
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conexao != null) {
                    conexao.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return clientes;
    }

    
    public void atualizarBD(Cliente cliente){
        try {
            bdConnect();
            
            String sql = "UPDATE clientes SET nome=?, cpf=?, endereco=?, tel=?, email=? WHERE cpf=?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, cliente.getEndereco());
            statement.setString(4, cliente.getTel());
            statement.setString(5, cliente.getEmail());
            statement.setString(6, cliente.getCpf());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("O cliente foi atualizado com sucesso");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}