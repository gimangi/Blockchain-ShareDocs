package blockchain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteTransaction extends Transaction {
    private static final String COMMAND = "DELETE: ";
    private final int line;

    @Override
    public String getTransactionStr() {
        return COMMAND + line + getDefaultTransactionStr();
    }

    @Builder
    public DeleteTransaction(String author, int line) {
        super(author);
        this.line = line;
    }
}
