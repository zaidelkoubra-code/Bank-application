package com.bank.Hiberus_bank;

public class FailedTransactionException extends RuntimeException{
    public FailedTransactionException(String s){
        super("Transaction failed: "+s);
    }
    }

