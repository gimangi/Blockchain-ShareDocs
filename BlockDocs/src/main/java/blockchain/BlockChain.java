package blockchain;

import lombok.Getter;

import java.util.List;
import java.util.NoSuchElementException;

@Getter
public class BlockChain {
    public static final String AUTHOR_ROOT = "root";

    private List<Block> chain;
    private List<Transaction> pendingTransactions;
    private final int difficulty;
    private final int miningReward;

    public BlockChain(int difficulty, int miningReward) {
        this.chain.add(createGenesisBlock());
        this.difficulty = difficulty;
        this.miningReward = miningReward;
    }

    public Block getLastBlock() throws NoSuchElementException {
        if (chain.isEmpty()) throw new NoSuchElementException();
        return chain.get(chain.size() - 1);
    }

    public void minePendingTransactions(String author) {
        Transaction rewardTx = RewardTransaction.builder()
                .author(author)
                .build();

        pendingTransactions.add(rewardTx);

        Block block = new Block(System.currentTimeMillis(), pendingTransactions, getLastBlock().getHash());
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

    private Block createGenesisBlock() {
        return new Block(System.currentTimeMillis());
    }
}
