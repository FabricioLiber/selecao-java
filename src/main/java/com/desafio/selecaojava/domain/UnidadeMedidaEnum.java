package com.desafio.selecaojava.domain;

import lombok.Getter;

@Getter
public enum UnidadeMedidaEnum {

    REAL_LITRO("R$ / litro");

    private final String value;

    UnidadeMedidaEnum (String value) {
        this.value = value;
    }

    public static UnidadeMedidaEnum get(String label) {
        for (UnidadeMedidaEnum unidadeMedida : values()) {
            if (unidadeMedida.value.equals(label)) {
                return unidadeMedida;
            }
        }
        return null;
    }

}
