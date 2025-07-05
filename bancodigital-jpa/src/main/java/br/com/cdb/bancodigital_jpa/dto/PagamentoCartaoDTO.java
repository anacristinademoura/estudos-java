package br.com.cdb.bancodigital_jpa.dto;

public class PagamentoCartaoDTO {
    private Double valor;
    private String senha;

    public Double getValor() {return valor;}
    public void setValor(Double valor) {this.valor = valor;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}
}
