package br.com.ada.cotacao_service.dtos;

public record CotacaoAwesomeApiResponse(
        String code,
        String codein,
        String name,
        String high,
        String low,
        String varBid,
        String pctChange,
        String bid,
        String ask,
        String timestamp,
        String create_date
) {
}