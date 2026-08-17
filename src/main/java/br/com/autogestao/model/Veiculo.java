package br.com.autogestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="veiculos", uniqueConstraints=@UniqueConstraint(name="uk_veiculo_placa", columnNames="placa"))
public class Veiculo {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String placa;
    @NotBlank private String marca;
    private String modelo;
    private String ano;
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="cliente_id", nullable=false)
    private Cliente cliente;
    @PrePersist @PreUpdate public void normalizar(){ if(placa!=null) placa=placa.replaceAll("[^A-Za-z0-9]","").toUpperCase(); }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getPlaca(){return placa;} public void setPlaca(String v){placa=v;}
    public String getMarca(){return marca;} public void setMarca(String v){marca=v;}
    public String getModelo(){return modelo;} public void setModelo(String v){modelo=v;}
    public String getAno(){return ano;} public void setAno(String v){ano=v;}
    public Cliente getCliente(){return cliente;} public void setCliente(Cliente v){cliente=v;}
}
