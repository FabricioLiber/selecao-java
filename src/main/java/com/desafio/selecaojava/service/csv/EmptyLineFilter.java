package com.desafio.selecaojava.service.csv;

import com.opencsv.bean.CsvToBeanFilter;

public class EmptyLineFilter implements CsvToBeanFilter {

    @Override
    public boolean allowLine(String[] strings) {
        return strings.length > 1;
    }
}
