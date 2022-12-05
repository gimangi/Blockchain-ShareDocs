import blockchain.BlockChain;
import blockchain.InsertTransaction;
import docs.DocsViewer;

public class Main {

    public static void main(String[] args) {
        BlockChain blockChain = new BlockChain(2);
        blockChain.addTransaction(InsertTransaction.builder()
                .author("김형기")
                .command(InsertTransaction.InsertCommands.APPEND)
                .input("가나다라마바사아")
                .line(0)
                .build());
        System.out.println(blockChain.isChainValid());

        blockChain.minePendingTransactions("김형기");

        DocsViewer viewer = new DocsViewer(blockChain);
        System.out.println(viewer.getView());
        System.out.println("ㄴㅇㄹㄴㅇㄹ");
    }
}
