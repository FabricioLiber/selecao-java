package com.desafio.selecaojava.domain;

import lombok.Getter;

@Getter
public enum RegiaoEnum {

    CO("Centro-Oeste"),
    N("Norte"),
    NE("Nordeste"),
    S("Sul"),
    SE("Sudeste");

    private final String value;

    RegiaoEnum(String value) {
        this.value = value;
    }

    public static RegiaoEnum get(String label) {
        for (RegiaoEnum regiao : values()) {
            if (regiao.value.equals(label)) {
                return regiao;
            }
        }
        return null;
    }

}
