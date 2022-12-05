package docs;

import blockchain.*;

import java.util.ArrayList;
import java.util.List;

public class DocsViewer {
    private final BlockChain blockChain;
    private final List<String> lines = new ArrayList<>();

    public DocsViewer(BlockChain blockChain) {
        this.blockChain = blockChain;
    }

    public String getView() {
        init();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            sb.append("[" + i + "]\t" + lines.get(i) + "\n");
        }
        return sb.toString();
    }

    private void init() {
        lines.clear();
        System.out.println(blockChain.getChain().size());

        for (Block block : blockChain.getChain()) {
            for (Transaction t : block.getTransactions()) {
                if (t instanceof InsertTransaction) {
                    insertLine((InsertTransaction) t);
                } else if (t instanceof DeleteTransaction) {
                    deleteLine((DeleteTransaction) t);
                }
            }
        }
    }

    private void insertLine(InsertTransaction transaction) {
        if (transaction.getCommand() == InsertTransaction.InsertCommands.APPEND) {
            lines.add(transaction.getLine(), transaction.getInput());
        }
        else if (transaction.getCommand() == InsertTransaction.InsertCommands.UPDATE) {
            lines.set(transaction.getLine(), transaction.getInput());
        }
    }

    private void deleteLine(DeleteTransaction transaction) {
        lines.remove(transaction.getLine());
    }

}
