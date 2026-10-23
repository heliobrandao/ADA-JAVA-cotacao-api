package br.com.ada.cotacao_service.services;

import br.com.ada.cotacao_service.clients.AwesomeApiClient;
import br.com.ada.cotacao_service.dtos.CotacaoAwesomeApiResponse;
import br.com.ada.cotacao_service.dtos.CotacaoResponse;
import br.com.ada.cotacao_service.exceptions.MoedaNaoSuportadaException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CotacaoService {

    private final AwesomeApiClient awesomeApiClient;

    public CotacaoService(AwesomeApiClient awesomeApiClient) {
        this.awesomeApiClient = awesomeApiClient;
    }

    public CotacaoResponse calcular(String moeda, BigDecimal valor) {

        String moedaNormalizada = moeda.toUpperCase();

        if (!moedaNormalizada.equals("USD") &&
                !moedaNormalizada.equals("EUR")) {

            throw new MoedaNaoSuportadaException(moeda);
        }

        CotacaoAwesomeApiResponse cotacaoApi =
                awesomeApiClient.buscarCotacao(moedaNormalizada);

        BigDecimal cotacao =
                new BigDecimal(cotacaoApi.bid());

        BigDecimal valorConvertido =
                valor.multiply(cotacao)
                        .setScale(2, RoundingMode.HALF_UP);

        return new CotacaoResponse(
                moedaNormalizada,
                valor,
                cotacao,
                valorConvertido
        );
    }
}