package docs;

import blockchain.BlockChain;

public class Docs {
    private final int difficulty;
    private final BlockChain blockChain;
    private final String author;
    private final DocsEditor docsEditor;

    public Docs(int difficulty, String author) {
        this.difficulty = difficulty;
        this.author = author;
        this.blockChain = new BlockChain(difficulty);
        this.docsEditor = new DocsEditor(blockChain, author);
    }

    public void appendLast(String input) {
        docsEditor.appendLine(blockChain.numOfTransactions(), input);
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
        blockChain.minePendingTransactions(author);
        DocsViewer viewer = new DocsViewer(blockChain);
        return viewer.getView();
    }

    public BlockChain getBlockChain() {
        return this.blockChain;
    }

    private void validateLine(int line) throws IllegalArgumentException {
        if (line < 0 || line > blockChain.numOfTransactions())
            throw new IllegalArgumentException("불가능한 라인 위치입니다.");
    }
}
