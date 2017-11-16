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
import model.Produto;

/**
 *
 * @author brunodepaulo
 */
public class ProdutoDAO {
   private ArrayList<Produto> produtos = new ArrayList<Produto>();
   private File fileProduto = new File("produtos.txt");
   private File fileProdutoBin = new File("produtoBin.obj");
   private ObjectOutputStream oos = CriaEscritorBinario(fileProdutoBin, true);
   private ObjectInputStream ois = CriaLeitorBinario(fileProdutoBin);
   private Connection conexao = null;
   private PreparedStatement pstdados = null;
   
   public void create(Produto produto) throws IOException{ //correto é colocar try-catch e a função ser void
       for(int i=0; i<getProdutos().size(); i++){
           if(getProdutos().get(i).getCodProduto()== getProdutos().get(i).getCodProduto()){
               throw new IOException();
           }
       }
        getProdutos().add(produto);
       gravar(getProdutos(), true);
   }
   
   public void read() throws FileNotFoundException, IOException{
        //File arquivo = new File("produtos.txt");
        FileReader leitor = new FileReader(fileProduto);
        BufferedReader leitorBuff = new BufferedReader(leitor);
        String line;
        while((line = leitorBuff.readLine())!=null){
            String [] produtos = line.split(",");
            Produto produto = new Produto();
            produto.setNome(produtos[0]);
            produto.setCodProduto(Integer.parseInt(produtos[1]));
            produto.setTipo(produtos[2]);
            produto.setMarca(produtos[3]);
            produto.setFornecedor(produtos[4]);
            this.getProdutos().add(produto);   
        }
        leitorBuff.close();
        for(int i=0; i<this.getProdutos().size(); i++){
            System.out.println("Nome: "+this.getProdutos().get(i).getNome()+
                    ", Codigo: "+this.getProdutos().get(i).getCodProduto()+
                    ", Tipo: "+this.getProdutos().get(i).getTipo()+
                    ", Marca: "+this.getProdutos().get(i).getMarca()+
                    ", Preço: "+this.getProdutos().get(i).getPreco()+
                    ", Fornecedor: "+this.getProdutos().get(i).getFornecedor());
        }
   }
   
   public void update(Produto produto, String CPF){
       for(int i=0; i<this.getProdutos().size(); i++){
           
       }
       
   }

   public void delete(){
        int searchCodigo = Integer.parseInt(JOptionPane.showInputDialog("Qual o código deseja apagar?"));
        for(int i=0; i < this.getProdutos().size(); i++){
            if(this.getProdutos().get(i).getCodProduto()==searchCodigo){
                getProdutos().remove(i);
            }    
        }
       try {
           gravar(getProdutos(), false);
       } catch (IOException ex) {
           Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   public void gravar(ArrayList<Produto> produtos, boolean append) throws IOException{
       FileWriter escritor = new FileWriter(fileProduto, append);
       BufferedWriter escritorBuff = new BufferedWriter(escritor);
       for(int i=0; i<produtos.size(); i++){ // caminha no array list
            escritorBuff.write(produtos.get(i).getNome()+","+
                produtos.get(i).getCodProduto()+","+
                produtos.get(i).getTipo()+","+
                produtos.get(i).getMarca()+","+
                produtos.get(i).getFornecedor()+"\n");
       }
        escritorBuff.flush();
        escritorBuff.close();
    }
   
   public void gravaBin(ArrayList<Produto> produtos, boolean flush) throws IOException{
       oos.writeObject(produtos);
       if(flush){
           oos.flush();
       }
   }
  
   public ArrayList<Produto> leBinario(ObjectInputStream ois){ //editar o array da classe
       ArrayList<Produto> clientes = null;
        try {
            clientes = (ArrayList<Produto>) ois.readObject();
            for (int i=0;i<clientes.size(); i++){
                System.out.println("Nome: "+this.getProdutos().get(i).getNome()+
                    ", Fornecedor: "+this.getProdutos().get(i).getFornecedor()+
                    ", Marca: "+this.getProdutos().get(i).getMarca()+
                    ", Tipo: "+this.getProdutos().get(i).getTipo());
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
     * @return the produtos
     */
    public ArrayList<Produto> getProdutos() {
        return produtos;
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

    
    public void inserirBD(Produto produto){
        try {
            
            bdConnect();
            
            String sql = "INSERT INTO produtos (codproduto, nome, preco, marca, fornecedor, tipo, qtd) VALUES (?,?,?,?,?,?,?)";
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = conexao.prepareStatement(sql, tipo, concorrencia);   
            
            pstdados.setInt(1, produto.getCodProduto());
            pstdados.setString(2, produto.getNome());
            pstdados.setFloat(3, produto.getPreco());
            pstdados.setString(4, produto.getMarca());
            pstdados.setString(5, produto.getFornecedor());
            pstdados.setString(6, produto.getTipo());
            pstdados.setInt(7, produto.getQtd());
            
            
            
            int rowsInserted = pstdados.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Novo produto foi inserido com sucesso.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletarBD(String id){
        try{
            bdConnect();
            
            String sql = "DELETE FROM produtos WHERE codProduto=?";
            
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("O produto foi deletado com sucesso.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
