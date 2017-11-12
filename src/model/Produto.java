package model;

public class Produto {
 
    private int codProduto;
    private String nome;
    private float preco;
    private String marca;
    private String fornecedor;
    private String tipo;
    private int qtd;

    public Produto(int codProduto, String nome, String marca, String tipo,  float preco, int qtd, String fornecedor) {
        this.codProduto = codProduto;
        this.nome = nome;
        this.preco = preco;
        this.marca = marca;
        this.tipo = tipo;
        this.qtd = qtd;
        this.fornecedor = fornecedor;
    }

   

    public Produto() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the fornecedor
     */
    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    
    
}
