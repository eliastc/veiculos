package com.mpinfo.seucarro.domain.dto;

import com.mpinfo.seucarro.domain.Veiculo;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class VeiculoDTO {

    private Long id;
    private String nome;
    private String tipo;

    public static VeiculoDTO create(Veiculo v) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(v, VeiculoDTO.class);
    }
}
