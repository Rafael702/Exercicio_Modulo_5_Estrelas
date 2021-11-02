package br.com.zup.leaddesafio.lead;

import br.com.zup.leaddesafio.lead.dto.LeadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarCliente(@RequestBody LeadDTO leadDTO) {
        leadService.salvarCliente(leadDTO);
    }

    @GetMapping
    public List<LeadDTO> exibirClientesCadastrados() {
        return leadService.exibirClientesCadastrados();
    }


}
