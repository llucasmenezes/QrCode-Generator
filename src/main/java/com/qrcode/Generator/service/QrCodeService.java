package com.qrcode.Generator.service;

import com.qrcode.Generator.model.QrCode;
import com.qrcode.Generator.repository.QrCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.glxn.qrgen.javase.QRCode;

import java.io.ByteArrayOutputStream;
import java.util.List;

@AllArgsConstructor
@Service
public class QrCodeService {

    @Autowired
    private QrCodeRepository qrCodeRepository;

    // Método para gerar QR Code e retornar a imagem como um array de bytes
    public QrCode GerarQrCode(String url) {
        try {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            QRCode.from(url).withSize(300, 300).writeTo(byteArrayOutputStream);
            byte[] qrCodeBytes = byteArrayOutputStream.toByteArray();


            QrCode qrCode = new QrCode(url, "/qrcode_generator.png");
            qrCodeRepository.save(qrCode);


            return qrCode;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para listar todos os QR Codes salvos no banco de dados
    public List<QrCode> listarQrCodes() {
        return qrCodeRepository.findAll();
    }
}
