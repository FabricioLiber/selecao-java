package com.desafio.selecaojava.service.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArquivoDTO {

    @CsvBindByPosition(position = 0)
    private String regiao;

    @CsvBindByPosition(position = 1)
    private String estado;

    @CsvBindByPosition(position = 2)
    private String municipio;

    @CsvBindByPosition(position = 3)
    private String revenda;

    @CsvBindByPosition(position = 4)
    private String cnpj;

    @CsvBindByPosition(position = 5)
    private String produto;

    @CsvBindByPosition(position = 6)
    private String dataColeta;

    @CsvBindByPosition(position = 7)
    private String valorVenda;

    @CsvBindByPosition(position = 8)
    private String valorCompra;

    @CsvBindByPosition(position = 9)
    private String unidadeMedida;

    @CsvBindByPosition(position = 10)
    private String bandeira;

    public void setRegiao (String regiao) {
        this.regiao = limparString(regiao);
    }

    public void setEstado(String estado) {
        this.estado = limparString(estado);
    }

    public void setMunicipio(String municipio) {
        this.municipio = limparString(municipio);
    }

    public void setRevenda (String revenda) {
        this.revenda = limparString(revenda);
    }

    public void setCnpj(String cnpj) {
        this.cnpj = limparString(cnpj);
    }

    public void setProduto(String produto) {
        this.produto = limparString(produto);
    }

    public void setDataColeta(String dataColeta) {
        this.dataColeta = limparString(dataColeta);
    }

    public void setValorVenda(String valorVenda) {
        this.valorVenda = limparString(valorVenda);
    }

    public void setValorCompra(String valorCompra) {
        this.valorCompra = limparString(valorCompra);
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = limparString(unidadeMedida);
    }

    public void setBandeira(String bandeira) {
        this.bandeira = limparString(bandeira);
    }

    public String limparString (String campo) {
        return campo.replace("\0", "").trim();
    }

}
