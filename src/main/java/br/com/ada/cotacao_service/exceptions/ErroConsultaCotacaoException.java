package br.com.ada.cotacao_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErroConsultaCotacaoException extends RuntimeException {

    public ErroConsultaCotacaoException() {
        super("Erro ao consultar o serviço de cotação");
    }
}