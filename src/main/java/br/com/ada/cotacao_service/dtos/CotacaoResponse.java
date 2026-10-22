package br.com.ada.cotacao_service.dtos;

import java.math.BigDecimal;

public record CotacaoResponse(
        String moeda,
        BigDecimal valor,
        BigDecimal cotacao,
        BigDecimal valorConvertido
) {
}