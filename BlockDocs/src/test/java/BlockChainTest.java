import blockchain.BlockChain;
import blockchain.InsertTransaction;
import org.junit.jupiter.api.Test;

public class BlockChainTest {

    @Test
    public void testInsert() {
        BlockChain blockChain = new BlockChain(1);
        InsertTransaction transaction = InsertTransaction
                .builder()
                .command(InsertTransaction.InsertCommands.APPEND)
                .line(0)
                .input("test")
                .author("author")
                .build();

        blockChain.addTransaction(transaction);
        System.out.println(blockChain.totalTransactions());
        blockChain.minePendingTransactions("miner");

        System.out.println(blockChain.getChain());
        System.out.println(blockChain.totalTransactions());
    }
}
