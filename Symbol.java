public enum Symbol {
    EMPTY(' ', -1),
    X('Y', 0),
    O('B', 1);

    public final char label;
    public final int value;

    private Symbol(char label, int value) {
        this.label = label;
        this.value = value;
    }
}
