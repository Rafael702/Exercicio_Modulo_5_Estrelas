package br.com.zup.leaddesafio.lead.dto;

public class ProdutoDTO {
    private String nomeDoProduto;
    private double valor;

    public ProdutoDTO() {
    }

    public ProdutoDTO(String nomeDoProduto, double valor) {
        this.nomeDoProduto = nomeDoProduto;
        this.valor = valor;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public void setNomeDoProduto(String nomeDoProduto) {
        this.nomeDoProduto = nomeDoProduto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
