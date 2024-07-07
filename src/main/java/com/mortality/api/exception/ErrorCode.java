package com.mortality.api.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("Erro interno na aplicação"),
    RESOURCE_NOT_FOUND("Recurso não encontrado"),
    DUPLICATED_VALUE("Ja existe registro cadastrado para esse Ano e IsoCode ");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
