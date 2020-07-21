package com.desafio.selecaojava.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "historico_preco")
public class HistoricoPreco {

    @Id
    @GeneratedValue(generator = "s_historico_preco")
    @SequenceGenerator(name = "s_historico_preco", sequenceName = "s_historico_preco", allocationSize = 1)
    private Long id;

    private Double valorVenda;

    private Double valorCompra;

    @OneToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    @OneToOne
    @JoinColumn(nullable = false)
    private Revendedor revendedor;

    private LocalDate dataColeta;

}
