package blockchain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class InsertTransaction extends Transaction {
    private InsertCommands command;
    private int line;


    @Override
    public String getTransactionStr() {
        return command.name() + ": " + line + getDefaultTransactionStr();
    }

    public enum InsertCommands {
        APPEND, UPDATE
    }

    @Builder
    public InsertTransaction(String author, InsertCommands command, int line) {
        super(author);
        this.command = command;
        this.line = line;
    }
}
