package com.bcopstein.ex4_lancheriaddd_v1.Dominio.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Cliente;

@Service
@Primary
public class DescontosServiceManager implements DescontosService {

    public static final String CLIENTE_FREQUENTE = "ClienteFrequente";
    public static final String CLIENTE_GASTADOR = "ClienteGastador";

    private final DescontosServicePadrao frequenteService;
    private final DescontosServiceMensal gastadorService;

    private String activeOption = CLIENTE_FREQUENTE; // default

    @Autowired
    public DescontosServiceManager(DescontosServicePadrao frequenteService,
                                   DescontosServiceMensal gastadorService) {
        this.frequenteService = frequenteService;
        this.gastadorService = gastadorService;
    }

    @Override
    public double getPercentualDesconto(Cliente cliente) {
        if (CLIENTE_GASTADOR.equals(activeOption)) {
            return gastadorService.getPercentualDesconto(cliente);
        }
        // default to frequent client discount
        return frequenteService.getPercentualDesconto(cliente);
    }

    public void setActiveOption(String code) {
        if (CLIENTE_GASTADOR.equals(code) || CLIENTE_FREQUENTE.equals(code)) {
            this.activeOption = code;
        } else {
            throw new IllegalArgumentException("Código de desconto desconhecido: " + code);
        }
    }

    public String getActiveOption() {
        return this.activeOption;
    }
}
