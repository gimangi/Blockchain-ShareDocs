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
            if (!chain.get(i - 1).getHash().equals(chain.get(i).getPreviousHash()))
                return false;
            if (!chain.get(i).getHash().equals(chain.get(i).calculateHash()))
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

    public int totalLines() {
        int lines = 0;
        for (Block block : chain) {
            for (Transaction t : block.getTransactions()) {
                lines += getCountLine(t);
            }
        }
        for (Transaction t : pendingTransactions)
            lines += getCountLine(t);

        return lines;
    }

    private static int getCountLine(Transaction transaction) {
        if (transaction instanceof InsertTransaction)
            return 1;
        if (transaction instanceof DeleteTransaction)
            return -1;
        return 0;
    }

    public int pendingTransactionSize() {
        return pendingTransactions.size();
    }

    private Block createGenesisBlock() {
        return new Block(System.currentTimeMillis());
    }
}
