package org.domain;

import com.alibaba.fastjson.JSON;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class NoobChain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = new Random().nextInt();
    public static void main(String[] args) {
        //add our blocks to the blockchain ArrayList:
        List<Transaction> transactionList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction("0x11","0x00",new BigInteger("100"),new Date());
            transactionList.add(transaction);
        }
        blockchain.add(new Block(JSON.toJSONString(transactionList), "0"));
        System.out.println("Trying to Mine block 1... ");
        blockchain.get(0).mineBlock(difficulty);
        blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
                System.out.println("Trying to Mine block 2... ");
        blockchain.get(1).mineBlock(difficulty);
        blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
                System.out.println("Trying to Mine block 3... ");
        blockchain.get(2).mineBlock(difficulty);
        System.out.println("Blockchain is Valid: " + isChainValid());
        String blockchainJson = JSON.toJSONString(blockchain);
//        String blockchainJson = JSON.toJSONString(blockchain)new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("The block chain: ");
        System.out.println(blockchainJson);
    }
    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }

            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}
