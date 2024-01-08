package org.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Block {
    public String hash;
    public String previousHash;
    private String data; //our data will be a simple message.
    private int nonce;
    private long timeStamp; //as number of milliseconds since 1/1/1970.
    //Block Constructor.
    public Block(String data,String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }


    public String calculateHash() {
        String hash = StringUtil.applySha256(
                previousHash +
                        timeStamp+
                        nonce +
                        data
        );
        return hash;
    }
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}