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

    public void salvarCliente(LeadDTO leadDTO) {
        if (validarLead(leadDTO)) {
            clientes.add(leadDTO);
        }
    }

    public String validarProduto(LeadDTO leadDTO) {
        String nomeDoProduto = "";

        for (LeadDTO referenciaCliente : clientes) {
            if (referenciaCliente.getProdutos().isEmpty()) {
                for (ProdutoDTO referenciaProduto : referenciaCliente.getProdutos()) {
                    nomeDoProduto = referenciaProduto.getNomeDoProduto();
                }
            } else {
                for (ProdutoDTO verificaProduto : referenciaCliente.getProdutos()) {
                    if (!verificaProduto.getNomeDoProduto().contains(nomeDoProduto)) {
                        nomeDoProduto = verificaProduto.getNomeDoProduto();
                    }
                }
            }
        }
        return nomeDoProduto;
    }

    public boolean validarLead(LeadDTO leadDTO) {
        boolean validar = true;

        String novoEmail = leadDTO.getEmail();
        String nomeProduto = validarProduto(leadDTO);

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
