package com.mmhtoo.demo.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Override
    public void sendEmail(String email, String content) throws Exception {
        Thread.sleep(2000);
        throw new Exception("Failed to send email!");
    }

}
