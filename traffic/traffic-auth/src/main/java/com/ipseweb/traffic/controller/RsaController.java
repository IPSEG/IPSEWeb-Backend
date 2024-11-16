package com.ipseweb.traffic.controller;

import com.ipseweb.traffic.dto.RsaResponse;
import com.ipseweb.traffic.service.RsaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rsa")
public class RsaController {
    RsaService rsaService;

    @GetMapping("/create")
    public RsaResponse getRsa(){
        return rsaService.getRsa();
    }
}
