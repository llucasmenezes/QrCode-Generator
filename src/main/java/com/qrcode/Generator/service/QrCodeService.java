package com.qrcode.Generator.service;

import com.qrcode.Generator.model.QrCode;
import com.qrcode.Generator.repository.QrCodeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class QrCodeService {

    @Autowired
    private QrCodeRepository qrCodeRepository;


    public QrCode GerarQrCode(String url){
        try{
            String caminhoDoArquivo = System.getProperty("user.home") + "\\Downloads\\qr_romantico.png";
            File file = new File(caminhoDoArquivo);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(QRCode.from(url).withSize(300, 300).stream().toByteArray());
            fos.close();

            QrCode qrCode = new QrCode(url, "/qr_romantico.png");
            return qrCodeRepository.save(qrCode);

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
