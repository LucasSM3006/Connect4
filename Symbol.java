public enum Symbols {
    VAZIO(' ', -1),
    Y('Y', 1),
    B('B', 2);

    public final char label;
    public final int value;

    private Symbols(char label, int value) {
        this.label = label;
        this.value = value;
    } // fim do construtor(char, int)
}
