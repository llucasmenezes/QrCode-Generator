package com.qrcode.Generator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
public class QrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    private String caminhoDoArquivo;

    public QrCode() {}

    public QrCode(String url, String caminhoDoArquivo) {
        this.url = url;
        this.caminhoDoArquivo = caminhoDoArquivo;
    }


}
