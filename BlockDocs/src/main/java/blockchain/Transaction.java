package blockchain;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Transaction {

    protected final String author;

    protected final long timestamp = System.currentTimeMillis();

    protected String getDefaultTransactionStr() {
        return author + timestamp;
    }

    public abstract String getTransactionStr();
}
