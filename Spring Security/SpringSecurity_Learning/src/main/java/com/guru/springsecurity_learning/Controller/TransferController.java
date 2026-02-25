package com.guru.springsecurity_learning.Controller;

import com.guru.springsecurity_learning.Service.TransferTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/user/transaction")
public class TransferController {

    @Autowired
    private TransferTransactionService transferTransactionService;
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam("fromAccountId") Long fromAccountId, @RequestParam("toAccountId") Long toAccountId, @RequestParam("amount") BigDecimal amount , @RequestHeader("X-IdempotencyKey")  String idempotencyKey){
        transferTransactionService.transfer(fromAccountId,toAccountId,amount,idempotencyKey);
        return new ResponseEntity<>("Transaction Successful..!",HttpStatus.OK);
    }
}
