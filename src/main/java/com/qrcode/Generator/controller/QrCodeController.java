package com.qrcode.Generator.controller;

import com.qrcode.Generator.model.QrCode;
import com.qrcode.Generator.service.QrCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeService qrCodeService;

    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @PostMapping(value = "/gerar", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<QrCode> gerarQrCode(@RequestParam String mensagem,
                                              @RequestParam String data,
                                              @RequestParam("imagem") MultipartFile imagem,
                                              @RequestParam String musica) throws Exception {

        System.out.println("Requisição POST recebida para /qrcode/gerar");

        // Salve a imagem no diretório desejado
        String uploadDir = System.getProperty("user.home") + "/uploads/";
        File imageFile = new File(uploadDir + imagem.getOriginalFilename());

        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            fos.write(imagem.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        // Cria a URL com os parâmetros
        String url = "http://localhost:8080/pagina?" +
                "mensagem=" + URLEncoder.encode(mensagem, StandardCharsets.UTF_8) +
                "&data=" + URLEncoder.encode(data, StandardCharsets.UTF_8) +
                "&imagem=" + URLEncoder.encode(imageFile.getAbsolutePath(), StandardCharsets.UTF_8) +
                "&musica=" + URLEncoder.encode(musica, StandardCharsets.UTF_8);

        // Gera o QR Code em bytes
        QrCode qrCodeBytes = qrCodeService.GerarQrCode(url);

        // Retorna o QR Code gerado como uma imagem PNG
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrCodeBytes);
    }

}

