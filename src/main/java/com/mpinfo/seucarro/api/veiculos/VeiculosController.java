package com.mpinfo.seucarro.api.veiculos;

import com.mpinfo.seucarro.domain.Veiculo;
import com.mpinfo.seucarro.domain.VeiculoService;
import com.mpinfo.seucarro.domain.dto.VeiculoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carros")
public class VeiculosController {
    @Autowired
    private VeiculoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<VeiculoDTO> veiculos = service.getVeiculos();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        VeiculoDTO veiculo = service.getVeiculoById(id);
        return ResponseEntity.ok(veiculo);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getVeiculosByTipo(@PathVariable("tipo") String tipo) {
        List<VeiculoDTO> veiculos = service.getVeiculosByTipo(tipo);
        return veiculos.isEmpty() ?
            ResponseEntity.noContent().build() :
            ResponseEntity.ok(veiculos);
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity post(@RequestBody Veiculo veiculo) {
           VeiculoDTO v = service.insert(veiculo);
           URI location = getUri(v.getId());
           return ResponseEntity.created(location).build();
    }

    private  URI getUri(long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public  ResponseEntity put(@PathVariable("id") Long id, @RequestBody Veiculo veiculo) {
        veiculo.setId(id);
        VeiculoDTO v = service.update(veiculo, id);
        return  v != null ?
                ResponseEntity.ok(v) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}



