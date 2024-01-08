package org.domain;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class Transaction {
    private String from;
    private String to;
    private BigInteger amount;
    private String txHash;
    private Date timestamp;

    public Transaction(String from,String to,BigInteger amount,Date timestamp) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.timestamp = timestamp;
        this.txHash = calculateHash();
    }

    public String calculateHash() {
        String txHash = StringUtil.applySha256(
                from +
                        to +
                        amount +
                        timestamp
        );
        return txHash;
    }
}
