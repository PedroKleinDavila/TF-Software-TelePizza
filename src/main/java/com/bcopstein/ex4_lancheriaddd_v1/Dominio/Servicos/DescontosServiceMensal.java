package com.bcopstein.ex4_lancheriaddd_v1.Dominio.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Dados.PedidoRepository;
import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Entidades.Cliente;


@Service
public class DescontosServiceMensal implements DescontosService {

    private PedidoRepository pedidoRepository;

    @Autowired
    public DescontosServiceMensal(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public double getPercentualDesconto(Cliente cliente) {
        double gastoUltimos30dias = pedidoRepository.gastoTotalClienteUltimosDias(cliente.getCpf(), 30);
        if (gastoUltimos30dias > 500.0) {
            return 0.15;
        }
        return 0.0;
    }
}