package com.mpinfo.seucarro.domain;

import com.mpinfo.seucarro.api.exception.ObjectNotFoundException;

import com.mpinfo.seucarro.domain.dto.VeiculoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository rep;

    public List<VeiculoDTO> getVeiculos() {
        List<VeiculoDTO> list = rep.findAll().stream().map(VeiculoDTO::create).
                collect(Collectors.toList());
         return list;
    }

    public VeiculoDTO getVeiculoById(Long id) {
        Optional<Veiculo> veiculo = rep.findById(id);
        return veiculo.map(VeiculoDTO::create)
                .orElseThrow(() -> new ObjectNotFoundException("Veiculo não encontrado!"));
    }

    public List<VeiculoDTO> getVeiculosByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(VeiculoDTO::create)
                  .collect(Collectors.toList());
    }

   /**
    *  public Veiculo save(Veiculo veiculo) {
        return rep.save(veiculo);
    } */


    public VeiculoDTO insert(Veiculo veiculo) {
        Assert.isNull(veiculo.getId(), "Não foi possivel inserir o registro");
        return VeiculoDTO.create(rep.save(veiculo));
    }

    public VeiculoDTO update(Veiculo veiculo, Long id) {
        Assert.notNull(id, "Não foi possivel a atualização");

        Optional<Veiculo> optional = rep.findById(id);
        if (optional.isPresent()) {
            Veiculo db = optional.get();
            db.setNome(veiculo.getNome());
            db.setTipo(veiculo.getTipo());
            System.out.println("Veiculo id " + db.getId());
            rep.save(db);
            return VeiculoDTO.create(db);
        }else {
           return null;
            // throw new RuntimeException("Não foi possível atualizar ");
        }
    }
    public void delete(Long id) {
            rep.deleteById(id);
    }

}
