package com.mmhtoo.demo.controller;

import com.mmhtoo.demo.annotation.CheckValidation;
import com.mmhtoo.demo.dto.request.OwnTransferDto;
import com.mmhtoo.demo.dto.request.Response;
import com.mmhtoo.demo.dto.request.SendEmailDto;
import com.mmhtoo.demo.service.IEmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping( value = "/demo" )
@AllArgsConstructor
public class DemoController {

    private final IEmailService emailService;

    @GetMapping
    public String sayHello(
            @RequestParam( value = "message" , required = false ) String message
    ){
        return message == null ? "Hello!" : message;
    }

    @PostMapping( value = "/transfer" )
    public Response transfer(
            @RequestBody OwnTransferDto body
    ){
        // call transfer logic
        return Response.builder()
                .description(String.format("Successfully transfer %s from %s to %s.",body.getAmount(),body.getFromAccount(),body
                        .getToAccount()))
                .status(HttpStatus.OK)
                .build();
    }

    @CheckValidation
    @PostMapping( value = "/send-email" )
    public Response sendEmail(
           @Valid @RequestBody SendEmailDto dto,
           BindingResult bindingResult
    ) throws Exception {
        emailService.sendEmail(dto.getToEmail(), dto.getContent());
        return Response.builder()
                .description(String.format("Successfully send email to %s ",dto.getToEmail()))
                .status(HttpStatus.OK)
                .build();
    }

}
