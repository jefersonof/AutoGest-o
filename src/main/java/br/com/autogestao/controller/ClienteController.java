package br.com.autogestao.controller;

import br.com.autogestao.dto.ClienteCadastroForm;
import br.com.autogestao.model.Veiculo;
import br.com.autogestao.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClienteController {
    private final ClienteService service;
    public ClienteController(ClienteService service){this.service=service;}
    @GetMapping("/") public String inicio(){return "redirect:/clientes";}
    @GetMapping("/clientes") public String listar(Model model){model.addAttribute("clientes",service.listar());return "clientes/lista";}
    @GetMapping("/clientes/novo") public String novo(Model model){if(!model.containsAttribute("form"))model.addAttribute("form",new ClienteCadastroForm());return "clientes/form";}
    @PostMapping("/clientes") public String salvar(@Valid @ModelAttribute("form") ClienteCadastroForm form, BindingResult erros, RedirectAttributes ra){
        if(erros.hasErrors())return "clientes/form";
        try{service.cadastrar(form);ra.addFlashAttribute("sucesso","Cliente e veículo cadastrados com sucesso!");return "redirect:/clientes";}
        catch(IllegalArgumentException e){erros.reject("cadastro",e.getMessage());return "clientes/form";}
    }
    @GetMapping("/clientes/{id}/veiculos/novo") public String novoVeiculo(@PathVariable Long id,Model model){model.addAttribute("cliente",service.buscar(id));model.addAttribute("veiculo",new Veiculo());return "clientes/veiculo-form";}
    @PostMapping("/clientes/{id}/veiculos") public String adicionarVeiculo(@PathVariable Long id,@Valid @ModelAttribute Veiculo veiculo,BindingResult erros,Model model,RedirectAttributes ra){
        if(erros.hasErrors()){model.addAttribute("cliente",service.buscar(id));return "clientes/veiculo-form";}
        try{service.adicionarVeiculo(id,veiculo);ra.addFlashAttribute("sucesso","Veículo adicionado ao cliente!");return "redirect:/clientes";}
        catch(IllegalArgumentException e){model.addAttribute("cliente",service.buscar(id));model.addAttribute("erro",e.getMessage());return "clientes/veiculo-form";}
    }
}
