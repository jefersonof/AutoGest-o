package br.com.autogestao.dto;

import jakarta.validation.constraints.NotBlank;

public class ClienteCadastroForm {
    @NotBlank private String nome; private String cpfCnpj; @NotBlank private String telefone; private String email;
    private String cep; private String rua; private String numero; private String bairro; private String cidade; private String estado;
    @NotBlank private String placa; @NotBlank private String marca; private String modelo; private String ano;
    public String getNome(){return nome;} public void setNome(String v){nome=v;} public String getCpfCnpj(){return cpfCnpj;} public void setCpfCnpj(String v){cpfCnpj=v;}
    public String getTelefone(){return telefone;} public void setTelefone(String v){telefone=v;} public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getCep(){return cep;} public void setCep(String v){cep=v;} public String getRua(){return rua;} public void setRua(String v){rua=v;}
    public String getNumero(){return numero;} public void setNumero(String v){numero=v;} public String getBairro(){return bairro;} public void setBairro(String v){bairro=v;}
    public String getCidade(){return cidade;} public void setCidade(String v){cidade=v;} public String getEstado(){return estado;} public void setEstado(String v){estado=v;}
    public String getPlaca(){return placa;} public void setPlaca(String v){placa=v;} public String getMarca(){return marca;} public void setMarca(String v){marca=v;}
    public String getModelo(){return modelo;} public void setModelo(String v){modelo=v;} public String getAno(){return ano;} public void setAno(String v){ano=v;}
}
