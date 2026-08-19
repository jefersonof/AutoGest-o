package br.com.autogestao.service;

import br.com.autogestao.dto.ClienteCadastroForm;
import br.com.autogestao.model.Cliente;
import br.com.autogestao.model.Veiculo;
import br.com.autogestao.repository.ClienteRepository;
import br.com.autogestao.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clientes; private final VeiculoRepository veiculos;
    public ClienteService(ClienteRepository clientes, VeiculoRepository veiculos){this.clientes=clientes;this.veiculos=veiculos;}
    public List<Cliente> listar(){return clientes.listarComVeiculos();}
    public Cliente buscar(Long id){return clientes.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));}
    @Transactional public Cliente cadastrar(ClienteCadastroForm f){
        validarPlaca(f.getPlaca());
        Cliente c=new Cliente(); c.setNome(f.getNome()); c.setCpfCnpj(f.getCpfCnpj()); c.setTelefone(f.getTelefone()); c.setEmail(f.getEmail());
        c.setCep(f.getCep()); c.setRua(f.getRua()); c.setNumero(f.getNumero()); c.setBairro(f.getBairro()); c.setCidade(f.getCidade()); c.setEstado(f.getEstado());
        Veiculo v=novoVeiculo(f.getPlaca(),f.getMarca(),f.getModelo(),f.getAno()); c.adicionarVeiculo(v); return clientes.save(c);
    }
   @Transactional
    public void adicionarVeiculo(Long clienteId, Veiculo v) {
        validarPlaca(v.getPlaca());

        Cliente cliente = buscar(clienteId);

        v.setId(null);
        v.setCliente(cliente);

        veiculos.save(v);
    }
    private void validarPlaca(String placa){String p=placa==null?"":placa.replaceAll("[^A-Za-z0-9]",""); if(veiculos.existsByPlacaIgnoreCase(p)) throw new IllegalArgumentException("Já existe um veículo com esta placa.");}
    private Veiculo novoVeiculo(String placa,String marca,String modelo,String ano){Veiculo v=new Veiculo();v.setPlaca(placa);v.setMarca(marca);v.setModelo(modelo);v.setAno(ano);return v;}
}
