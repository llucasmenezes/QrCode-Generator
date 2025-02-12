package com.qrcode.Generator.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaginaController {

    @GetMapping("/pagina")
    public String paginaPersonalizada(
            @RequestParam String mensagem,
            @RequestParam String data,
            @RequestParam String imagem,
            @RequestParam String musica,
            Model model) {

        model.addAttribute("mensagem", mensagem);
        model.addAttribute("data", data);
        model.addAttribute("imagem", imagem);
        model.addAttribute("musica", musica);

        return "pagina.html"; // Renderiza pagina.html.html em templates/
    }
}
