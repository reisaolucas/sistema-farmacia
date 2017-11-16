/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Fornecedor;

/**
 *
 * @author brunodepaulo
 */
public class FornecedorDAO {
   private ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
   private File fileFornecedor = new File("fornecedores.txt");
   private File fileFornecedorBin = new File("fornecedorBin.obj");
   private ObjectOutputStream oos = CriaEscritorBinario(fileFornecedorBin, true);
   private ObjectInputStream ois = CriaLeitorBinario(fileFornecedorBin);
   private Connection conexao = null;
   private PreparedStatement pstdados = null;
   
   public void create(Fornecedor fornecedor) throws IOException{ //correto é colocar try-catch e a função ser void
       for(int i=0; i<getFornecedores().size(); i++){
           if(getFornecedores().get(i).getCnpj() == fornecedor.getCnpj()){
               throw new IOException();
           }
       }
        getFornecedores().add(fornecedor);
       gravar(getFornecedores(), true); //pois cria um novo e adiciona na lista
   }
   
   public void read() throws FileNotFoundException, IOException{
        File arquivo = new File("fornecedores.txt");
        FileReader leitor = new FileReader(arquivo);
        BufferedReader leitorBuff = new BufferedReader(leitor);
        String line;
        while((line = leitorBuff.readLine())!=null){
            String [] fornecedores = line.split(",");
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(fornecedores[0]);
            fornecedor.setCnpj(fornecedores[1]);
            fornecedor.setTel(fornecedores[2]);
            fornecedor.setEmail(fornecedores[3]);
            this.getFornecedores().add(fornecedor);   
        }
        leitorBuff.close();
        for(int i=0; i<this.getFornecedores().size(); i++){
            System.out.println("Nome: "+this.getFornecedores().get(i).getNome()+
                    ", CPF: "+this.getFornecedores().get(i).getCnpj()+
                    ", Email: "+this.getFornecedores().get(i).getTel()+
                    ", Telefone: "+this.getFornecedores().get(i).getEmail());
        }
   }
   
   public void update(Fornecedor fornecedor, String Cnpj){
       for(int i=0; i<this.getFornecedores().size(); i++){
           
       }
       
   }
   
   public void escolheAtualizar(){
   try {
           read();//para carregar do txt para o arrya e mostrar no console.
           String searchCnpj = JOptionPane.showInputDialog("Qual cnpj deseja editar?");
           for(int i=0; i < this.getFornecedores().size(); i++){
               if(this.getFornecedores().get(i).getCnpj()==searchCnpj){
                  // AtualizaFornecedor atualiza = new AtualizaFornecedor(this.fornecedores.get(i));
               }
                
           }
       } catch (IOException ex) {
           Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   public void delete(){
        String searchCnpj = JOptionPane.showInputDialog("Qual cnpj deseja apagar?");
        for(int i=0; i < this.getFornecedores().size(); i++){
            if(this.getFornecedores().get(i).getCnpj()==searchCnpj){
                getFornecedores().remove(i);
            }    
        }
       try {
           gravar(getFornecedores(), false);
       } catch (IOException ex) {
           Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   public void gravar(ArrayList<Fornecedor> fornecedores, boolean append) throws IOException{
       FileWriter escritor = new FileWriter(fileFornecedor,append);
       BufferedWriter escritorBuff = new BufferedWriter(escritor);
       for(int i=0; i<fornecedores.size(); i++){ // caminha no array list
            escritorBuff.write(fornecedores.get(i).getNome()+","+
                fornecedores.get(i).getCnpj()+","+
                fornecedores.get(i).getTel()+","+
                fornecedores.get(i).getEmail()+"\n");
       }
        escritorBuff.flush();
        escritorBuff.close();
    }
   
   public void gravaBin(ArrayList<Fornecedor> fornecedores, boolean flush) throws IOException{
       oos.writeObject(fornecedores);
       if(flush){
           oos.flush();
       }
   }
  
   public ArrayList<Fornecedor> leBinario(ObjectInputStream ois){ //editar o array da classe
       ArrayList<Fornecedor> clientes = null;
        try {
            clientes = (ArrayList<Fornecedor>) ois.readObject();
            for (int i=0;i<clientes.size(); i++){
                System.out.println("Nome: "+this.getFornecedores().get(i).getNome()+
                    ", CNPJ: "+this.getFornecedores().get(i).getCnpj()+
                    ", Email: "+this.getFornecedores().get(i).getEmail()+
                    ", Telefone: "+this.getFornecedores().get(i).getTel());
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

    /**
     * @return the fornecedores
     */
    public ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
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

    
    public void inserirBD(Fornecedor fornecedor){
        try {
            
            bdConnect();
            
            String sql = "INSERT INTO fornecedores (nome, cnpj, tel, email) VALUES (?,?,?,?)";
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = conexao.prepareStatement(sql, tipo, concorrencia);   
            
            pstdados.setString(1, fornecedor.getNome());
            pstdados.setString(2, fornecedor.getCnpj());
            pstdados.setString(3, fornecedor.getTel());
            pstdados.setString(4, fornecedor.getEmail());
            
            
            int rowsInserted = pstdados.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Novo fornecedor foi inserido com sucesso.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletarBD(String id){
        try{
            bdConnect();
            
            String sql = "DELETE FROM fornecedores WHERE cnpj=?";
            
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("O fornecedor foi deletado com sucesso.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Fornecedor> lerBD(){
        
        bdConnect();
                
        PreparedStatement pst = null;
        ResultSet rs = null;
        int cont = 0;
        ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
                
        try{
            pst = conexao.prepareStatement("SELECT * FROM fornecedores");
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNome(rs.getString(1));
                fornecedor.setCnpj(rs.getString(2));
                fornecedor.setTel(rs.getString(3));
                fornecedor.setEmail(rs.getString(4));
                fornecedores.add(fornecedor);
                cont++;
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Fornecedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fornecedores;
    }
    
    public void atualizarBD(Fornecedor fornecedor){
        try {
            bdConnect();
            
            String sql = "UPDATE fornecedores SET nome=?, cnpj=?, tel=?, email=? WHERE cnpj=?";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, fornecedor.getNome());
            statement.setString(2, fornecedor.getCnpj());
            statement.setString(3, fornecedor.getTel());
            statement.setString(4, fornecedor.getEmail());
            statement.setString(5, fornecedor.getCnpj());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("O fornecedor foi atualizado com sucesso!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
