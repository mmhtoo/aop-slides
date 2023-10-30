package com.mmhtoo.demo.dto.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OwnTransferDto {
    private String toAccount;
    private String fromAccount;
    private Double amount;
}
