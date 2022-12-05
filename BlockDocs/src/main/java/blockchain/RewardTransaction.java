package blockchain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RewardTransaction extends Transaction {
    private static final String COMMAND = "REWARD: ";

    @Override
    public String getTransactionStr() {
        return COMMAND + getDefaultTransactionStr();
    }

    @Builder
    public RewardTransaction(String author) {
        super(author);
    }
}
