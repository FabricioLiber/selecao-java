package com.desafio.selecaojava.resource;

import com.desafio.selecaojava.exception.ExtracaoNaoFinalizadaException;
import com.desafio.selecaojava.exception.GravacaoDeDadosNaoFinalizadaException;
import com.desafio.selecaojava.service.ImportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/importacoes")
public class ImportResource {

    private final ImportService importService;

    public ImportResource(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping(value = "/csv")
    public void importarCSV(MultipartFile file) throws ExtracaoNaoFinalizadaException,
            GravacaoDeDadosNaoFinalizadaException {
        this.importService.importarCSV(file);
    }

}
