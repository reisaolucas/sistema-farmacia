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
import model.Cliente;
import view.GerenciaCliente;

/**
 *
 * @author brunodepaulo
 */
public class ClienteDAO {
   private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
   private File fileCliente = new File("clientes.txt");
   private File fileClienteBin = new File("clientesBin.obj");
   private ObjectOutputStream oos = CriaEscritorBinario(fileClienteBin, true);
   private ObjectInputStream ois = CriaLeitorBinario(fileClienteBin);
   
   public void create(Cliente cliente) throws IOException{ //correto é colocar try-catch e a função ser void
       for(int i=0; i<getClientes().size(); i++){
           if(getClientes().get(i).getCpf() == cliente.getCpf()){
               throw new IOException();
           }
       }
        getClientes().add(cliente);
       gravar(getClientes(), true);
   }
   
   public void read() throws FileNotFoundException, IOException{
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
   
   public void update(Cliente cliente, String CPF){ //só recebe um novo objeto cliente, atualiza no array e grava
             
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
           gravar(getClientes(), false);
       } catch (IOException ex) {
           Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   public void gravar(ArrayList<Cliente> clientes, boolean append) throws IOException{
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

    /**
     * @return the clientes
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
}
