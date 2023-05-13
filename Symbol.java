public enum Symbol {
    EMPTY(' ', -1),
    Y('Y', 0),
    B('B', 1);

    public final char label;
    public final int value;

    private Symbol(char label, int value) {
        this.label = label;
        this.value = value;
    }
}
