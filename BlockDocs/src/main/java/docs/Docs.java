package docs;

import blockchain.BlockChain;

public class Docs {
    private final int difficulty;
    private final BlockChain blockChain;
    private final String author;
    private final DocsEditor docsEditor;
    private final DocsViewer docsViewer;

    public Docs(int difficulty, String author) {
        this.difficulty = difficulty;
        this.author = author;
        this.blockChain = new BlockChain(difficulty);
        this.docsEditor = new DocsEditor(blockChain, author);
        this.docsViewer = new DocsViewer(blockChain);
    }

    public void appendLast(String input) {
        docsEditor.appendLine(blockChain.totalTransactions(), input);
    }

    public void append(int line, String input) {
        validateLine(line);
        docsEditor.appendLine(line, input);
    }

    public void update(int line, String input) {
        validateLine(line);
        docsEditor.updateLine(line, input);
    }

    public void delete(int line) {
        validateLine(line);
        docsEditor.deleteLine(line);
    }

    /**
     * 문서를 읽을 사람이 채굴 해야함
     * @return 문서 열람 결과
     */
    public String view() {
        if (blockChain.pendingTransactionSize() > 0)
            blockChain.minePendingTransactions(author);
        return docsViewer.getView();
    }

    public BlockChain getBlockChain() {
        return this.blockChain;
    }

    private void validateLine(int line) throws IllegalArgumentException {
        if (line < 0 || line > blockChain.totalTransactions())
            throw new IllegalArgumentException("불가능한 라인 위치입니다.");
    }
}
