package br.com.ada.cotacao_service.controllers;

import br.com.ada.cotacao_service.dtos.CotacaoResponse;
import br.com.ada.cotacao_service.services.CotacaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cotacao")
public class CotacaoController {

    private final CotacaoService cotacaoService;

    public CotacaoController(CotacaoService cotacaoService) {

        this.cotacaoService = cotacaoService;
    }

    @GetMapping("/{moeda}/{valor}")
    public CotacaoResponse consultar(
            @PathVariable String moeda,
            @PathVariable BigDecimal valor) {

        return cotacaoService.calcular(moeda, valor);
    }
}