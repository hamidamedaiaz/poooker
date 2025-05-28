package poker.pns;

public enum FaceCard {
    // Attributes:
    V(11),
    D(12),
    R(13),
    A(14);

    private final int fCValue;

    // Constructor:
    FaceCard(int fCValue) {
        this.fCValue = fCValue;
    }

    // Methods:
    public int getValueFC() {
        return this.fCValue;
    }

}
