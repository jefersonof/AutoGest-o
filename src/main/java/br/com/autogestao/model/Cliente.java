package br.com.autogestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String nome;
    @Column(name="cpf_cnpj") private String cpfCnpj;
    @NotBlank private String telefone;
    private String email;
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    @OneToMany(mappedBy="cliente", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Veiculo> veiculos = new ArrayList<>();

    public void adicionarVeiculo(Veiculo veiculo) { veiculos.add(veiculo); veiculo.setCliente(this); }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getNome(){return nome;} public void setNome(String v){nome=v;}
    public String getCpfCnpj(){return cpfCnpj;} public void setCpfCnpj(String v){cpfCnpj=v;}
    public String getTelefone(){return telefone;} public void setTelefone(String v){telefone=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getCep(){return cep;} public void setCep(String v){cep=v;}
    public String getRua(){return rua;} public void setRua(String v){rua=v;}
    public String getNumero(){return numero;} public void setNumero(String v){numero=v;}
    public String getBairro(){return bairro;} public void setBairro(String v){bairro=v;}
    public String getCidade(){return cidade;} public void setCidade(String v){cidade=v;}
    public String getEstado(){return estado;} public void setEstado(String v){estado=v;}
    public List<Veiculo> getVeiculos(){return veiculos;} public void setVeiculos(List<Veiculo> v){veiculos=v;}
}
