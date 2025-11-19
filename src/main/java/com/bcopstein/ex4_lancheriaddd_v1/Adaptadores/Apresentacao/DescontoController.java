package com.bcopstein.ex4_lancheriaddd_v1.Adaptadores.Apresentacao;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcopstein.ex4_lancheriaddd_v1.Dominio.Servicos.DescontosServiceManager;

@RestController
@RequestMapping("/desconto")
public class DescontoController {

    private final DescontosServiceManager manager;

    public DescontoController(DescontosServiceManager manager) {
        this.manager = manager;
    }

    @GetMapping("/active")
    @CrossOrigin("*")
    public String active() {
        return manager.getActiveOption();
    }

    @PostMapping("/active/{code}")
    @CrossOrigin("*")
    public String setActive(@PathVariable String code) {
        // NOTE: No authentication implemented here. It's the caller's responsibility
        // to ensure only 'master' users invoke this endpoint.
        manager.setActiveOption(code);
        return manager.getActiveOption();
    }
}
