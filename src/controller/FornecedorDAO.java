/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
       FileWriter escritor = new FileWriter(fileFornecedor,false);
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
}
