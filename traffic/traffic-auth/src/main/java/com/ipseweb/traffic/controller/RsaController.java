package com.ipseweb.traffic.controller;

import com.ipseweb.traffic.dto.RsaResponse;
import com.ipseweb.traffic.service.RsaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rsa")
@AllArgsConstructor
public class RsaController {
    RsaService rsaService;

    @GetMapping("")
    public RsaResponse getRsa(){
        return rsaService.getRsa();
    }
}
