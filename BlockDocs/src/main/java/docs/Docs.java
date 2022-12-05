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

    public void append(int line, String input) {
        docsEditor.appendLine(line, input);
    }

    public void update(int line, String input) {
        docsEditor.updateLine(line, input);
    }

    public void delete(int line) {
        docsEditor.deleteLine(line);
    }

    public String view() {
        return docsViewer.getView();
    }

    public BlockChain getBlockChain() {
        return this.blockChain;
    }

}
