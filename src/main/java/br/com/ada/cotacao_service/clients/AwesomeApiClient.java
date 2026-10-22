package br.com.ada.cotacao_service.clients;

import br.com.ada.cotacao_service.dtos.CotacaoAwesomeApiResponse;
import br.com.ada.cotacao_service.exceptions.ErroConsultaCotacaoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@Component
public class AwesomeApiClient {

    private final RestClient restClient;

    public AwesomeApiClient(
            @Value("${awesomeapi.base-url}") String baseUrl,
            @Value("${awesomeapi.api-key}") String apiKey) {

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("x-api-key", apiKey)
                .build();
    }

    public CotacaoAwesomeApiResponse buscarCotacao(String moeda) {

        String moedaNormalizada = moeda.toUpperCase();

        try {

            Map<String, CotacaoAwesomeApiResponse> resposta =
                    restClient.get()
                            .uri("/json/last/{moeda}-BRL", moedaNormalizada)
                            .retrieve()
                            .body(
                                    new ParameterizedTypeReference<
                                            Map<String, CotacaoAwesomeApiResponse>
                                            >() {}
                            );

            return resposta.get(moedaNormalizada + "BRL");

        } catch (RestClientException exception) {

            throw new ErroConsultaCotacaoException();
        }
    }
}