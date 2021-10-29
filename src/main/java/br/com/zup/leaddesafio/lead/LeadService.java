package br.com.zup.leaddesafio.lead;

import br.com.zup.leaddesafio.lead.dto.LeadDTO;
import br.com.zup.leaddesafio.lead.dto.ProdutoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadService {
    List<LeadDTO> clientes = new ArrayList<>();

    public void adicionarCliente(LeadDTO leadDTO) {
        if (validarEmail(leadDTO)) {
            clientes.add(leadDTO);
        }
    }

    public String validarCadastro(LeadDTO leadDTO) {
        String nomeDoProduto = "";
        for (LeadDTO referenciaCliente : clientes) {
            for (ProdutoDTO referenciaProduto : referenciaCliente.getProdutos()) {
                nomeDoProduto = referenciaProduto.getNomeDoProduto();
                return nomeDoProduto;
            }
        }
        return nomeDoProduto;
    }

    public boolean validarEmail(LeadDTO leadDTO) {
        boolean validar = true;

        String novoEmail = leadDTO.getEmail();
        String nomeProduto = validarCadastro(leadDTO);

        if (!clientes.isEmpty()) {
            for (LeadDTO referencia : clientes) {
                if (referencia.getEmail().contains(novoEmail)) {
                    for (ProdutoDTO produtos : leadDTO.getProdutos()) {
                        if (produtos.getNomeDoProduto().contains(nomeProduto)) {
                            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                                    "Email ou Produto Já Cadastrardo");
                        }
                    }
                }
            }
        }
        return validar;
    }


    public List<LeadDTO> exibirClientesCadastrados() {
        return clientes;
    }


}
