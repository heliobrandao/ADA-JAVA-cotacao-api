package br.com.ada.cotacao_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MoedaNaoSuportadaException extends RuntimeException {

    public MoedaNaoSuportadaException(String moeda) {
        super("Moeda não suportada: " + moeda);
    }
}