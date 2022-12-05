package blockchain;

import java.util.*;

public class BlockChain {

    private List<Block> chain = new ArrayList<>();
    private List<Transaction> pendingTransactions = new ArrayList<>();
    private final int difficulty;
    private final String uuid = UUID.randomUUID().toString();

    public BlockChain(int difficulty) {
        this.chain.add(createGenesisBlock());
        this.difficulty = difficulty;
    }

    public Block getLastBlock() throws NoSuchElementException {
        if (chain.isEmpty()) throw new NoSuchElementException();
        return chain.get(chain.size() - 1);
    }

    public void minePendingTransactions(String author) {
        RewardTransaction rewardTx = RewardTransaction
                .builder()
                .author(author)
                .build();

        pendingTransactions.add(rewardTx);

        Block block = new Block(System.currentTimeMillis(), new ArrayList<>(pendingTransactions), getLastBlock().getHash());
        block.mineBlock(difficulty);
        chain.add(block);

        pendingTransactions.clear();
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            if (chain.get(i - 1).getHash() != chain.get(i).getPreviousHash())
                return false;
            if (chain.get(i).getHash() != chain.get(i).calculateHash())
                return false;
        }
        return true;
    }

    public void addTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
    }

    public List<Block> getChain() {
        return Collections.unmodifiableList(this.chain);
    }

    public int totalTransactions() {
        int size = 0;
        for (Block block : chain)
            size += block.getTransactions().size();
        return size;
    }

    public int pendingTransactionSize() {
        return pendingTransactions.size();
    }

    private Block createGenesisBlock() {
        return new Block(System.currentTimeMillis());
    }
}
