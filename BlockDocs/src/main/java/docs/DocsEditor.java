package docs;

import blockchain.BlockChain;
import blockchain.DeleteTransaction;
import blockchain.InsertTransaction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DocsEditor {
    private final BlockChain blockChain;
    private final String author;

    public void deleteLine(int line) {
        DeleteTransaction transaction = DeleteTransaction.builder()
                .author(author)
                .line(line)
                .build();
        blockChain.addTransaction(transaction);
    }

    public void updateLine(int line, String input) {
        InsertTransaction transaction = InsertTransaction.builder()
                .command(InsertTransaction.InsertCommands.UPDATE)
                .line(line)
                .input(input)
                .author(author)
                .build();
        blockChain.addTransaction(transaction);
    }

    public void appendLine(int line, String input) {
        InsertTransaction transaction = InsertTransaction.builder()
                .command(InsertTransaction.InsertCommands.APPEND)
                .line(line)
                .input(input)
                .author(author)
                .build();
        blockChain.addTransaction(transaction);
    }
}
