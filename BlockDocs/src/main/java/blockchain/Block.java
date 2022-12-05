package blockchain;

import lombok.Getter;
import util.StringUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Block {
    private final long timestamp;
    private String hash;
    private final String previousHash;
    private final List<Transaction> transactions;
    private int nonce = 0;

    public Block(long timestamp) {
        this(timestamp, new ArrayList<>());
    }

    public Block(long timestamp, List<Transaction> transactions) {
        this(timestamp, transactions, "");
    }

    public Block(long timestamp, List<Transaction> transactions, String previousHash) {
        this.timestamp = timestamp;
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.getSHA256(
                previousHash +
                        nonce +
                        "" +
                        timestamp +
                        transactionsStr()
        );
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');

        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("[채굴 성공] hash :" + hash);
    }

    private String transactionsStr() {
        StringBuilder sb = new StringBuilder();
        for (Transaction t : transactions) {
            sb.append(t.getTransactionStr());
        }
        return sb.toString();
    }
}
