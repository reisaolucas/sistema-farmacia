
package model;

public class Cliente {
   private String nome;
   private String cpf;
   private String endereco;
   private String tel;
   private String email;
   
   public Cliente(String nome, String cpf, String endereco, String tel, String email){
       this.nome = nome;
       this.cpf = cpf;
       this.endereco = endereco;
       this.tel = tel;
       this.email = email;
   }
   public Cliente (){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
   
}
