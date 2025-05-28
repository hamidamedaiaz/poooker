package poker.pns;

public class Card {
    // Attributes:
    private int value;
    private String color;
    private Display display=new Display();

    //Constructor:
    public Card(int value, String color) {
        this.value = value;
        this.color = color;
    }

    //Accessors:
    public int getValue() {
        return this.value;
    }

    public String getColor() {
        return this.color;
    }

    //Methods:
    @Override
    public String toString() {
        if (this.getValue() <= 10) {
            return this.getValue() + this.getColor();
        } else {
            return display.ValueToFaceCard(this.value) + this.getColor();
        }
    }

    @Override
    public boolean equals(Object obj) { // deux cartes sont égales si elles ont la même valeur
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Card card = (Card) obj;
        return card.getValue() == this.value;
    }

    public boolean validateCard(Card other) {
        if(this.value == other.value && this.color.equals(other.color)) return false;
        return true;
    }


}
