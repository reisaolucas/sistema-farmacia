
package model;

public class Fornecedor {
    private String nome;
    private String cnpj;
    private String tel;
    private String email;
    //private Produto produto <list>;
    
    public Fornecedor(String nome, String cnpj, String tel, String email /*,Produto produto*/){
        this.nome = nome;
        this.cnpj = cnpj;
        this.tel = tel;
        this.email = email;
        //this.produto = produto;
        
    }
    
    public Fornecedor(){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String telefone) {
        this.tel = telefone;
    } 

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
