package com.mmhtoo.demo.dto.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
public class SendEmailDto {

    @NotNull( message = "To email is required!")
    @NotEmpty( message = "To email is required!")
    @Email( message = "Invalid email format!")
    private String toEmail;

    @NotNull( message = "Content is required!")
    @NotEmpty( message = "Content is required!")
    @Size( min = 5 , max = 1024 , message = "Minimum is 5 and maximum is 1024 characters!")
    private String content;

}
