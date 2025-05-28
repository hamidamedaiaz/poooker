package poker.pns;

import java.util.*;

public class Hand {
    // Attributes:
    private final List<Card> cards;
    private final int nbCards;
    private final int[] occurrences = new int[15]; //Ce tableau contient le nombre d'occurrences de chaque carte. Ex : Si nous avons deux 2 et un 3, la liste contiendra [0,0,2,1,...,0]
    private int value;
    private Display display;

    // Constructor:
    public Hand(int nbCards) {
        this.cards = new ArrayList<>();
        this.nbCards = nbCards;
        this.display = new Display();
    }

    // Getter and setter methods:


    public int getNbCards() {
        return nbCards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int getValue() {
        return this.value;
    }

    /**
     * Initialise la table  occurrences qui contient le nombre d'occurrences de chaque carte
     */
    public void initializeOccurrences() {
        for (Card card : this.cards) {
            int value = card.getValue();
            occurrences[value]++;
        }
    }

    public int[] getOccurrences() {
        return this.occurrences;
    }

    @Override
    public String toString() {
        StringBuilder handString = new StringBuilder("(");
        for (Card card : this.cards) {
            handString.append(card).append(" ");
        }
        handString.setCharAt(handString.length() - 1, ')');
        return handString.toString().trim();
    }

    public void calculateValue() {
        if (this.containsQuinteFlushRoyale()) this.value = 9;
        else if (this.containsQuinteFlush()) this.value = 8;
        else if (this.containsCarre()) this.value = 7;
        else if (this.containsFull()) this.value = 6;
        else if (this.containsCouleur()) this.value = 5;
        else if (this.containsSuite()) this.value = 4;
        else if (this.containsBrelan()) this.value = 3;
        else if (this.containsDoublePaire()) this.value = 2;
        else if (this.containsPaire()) this.value = 1;
        else this.value = 0; //aucune combinaison, carte haute
    }

    /**
     * verifier  des doublon dans Hand !!!!!
     * @return
     */
    public boolean validateCardHand() {
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (!this.cards.get(i).validateCard(this.cards.get(j))) return false;
            }
        }
        return true;
    }




    /**
     *  methode qui permet d'eviter d'entrer les memes cartes dans les deux hand
     *  ex : Main 1:  2Tr 6Ca 7Ca 8Tr APi   et Main 2 : Main 1:  2Tr 6Ca 7Ca 8Tr APi   ===> impossible !!!!!!!!
     * @param hand
     * @return
     */


    public boolean confirmHand(Hand hand ) {
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!this.cards.get(i).validateCard(hand.getCards().get(j))) {
                    return false;
                }
            }
        }
        return true;
    }






    /**la valeur de la carte la plus forte dans un  Hand  *****/
    public int getMaxValue() {
        for (int i = occurrences.length - 1; i >= 0; i--) {
            if (occurrences[i] > 0) {

                return i;
            }
        }
        return -1;
    }

    /************************* Méthodes containsCombinaison  et valueOfCombinaison *************************/


    /*************************************** PAIRE ***************************************/
    public boolean containsPaire() {
        for (int occurence : this.occurrences) {
            if (occurence == 2) {
                return true;
            }
        }
        return false;
    }

    /**  va retourner la valeur de la carte formant la paire si Hand est une paire, -1 sino */
    public  int valueOfPaire() {
        for (int value = 2; value <= 14; value++) {
            if (this.occurrences[value] == 2) {
                return value;
            }
        }
        return -1;
    }


    /*************************************** DOUBLE PAIRE ***************************************/
    public boolean containsDoublePaire() { // va retourner true si la main est une double paire, false sinon c'est à dire elle a deux paires de cartes de même valeur
        int compteur = 0;
        for (int occurence : this.occurrences) {
            if (occurence == 2) {
                compteur++;
            }
        }
        return compteur == 2;
    }

    /*****  va retourner la valeur de la carte formant la plus grande paire si la main est une double paire, -1 sinon ****/
    public int valueOfDoublePaire() {
        if (this.containsDoublePaire()) {
            int pair1 = -1;
            int pair2 = -1;
            for (int i = 2; i <= 14; i++) {
                if (occurrences[i] == 2) {
                    if (pair1 == -1) {
                        pair1 = i;
                    } else {
                        pair2 = i;
                    }
                }
            }
            return Math.max(pair1, pair2);
        }
        return -1;
    }

    /******  va retourner la valeur de la carte formant la plus petite paire si Hand  est une double paire, -1 sinon ****/
    public int lowerPairValue() {
        int value = -1;
        for (int i = 2; i <= 14; i++) {
            if (this.occurrences[i] == 2 && (value == -1 || i < value)) {
                value = i;
            }
        }
        return value;
    }


    /*************************************** BRELAN ***************************************/
    public boolean containsBrelan(){
        for (int occurrence : this.occurrences) {
            if (occurrence == 3) {
                return true;
            }
        }
        return false;
    }

    /**** va returner la valeur de la carte formant le brelan si la main est un brelan, -1 sinon ********/
    public int valueOfBrelan() {
        for (int value = 2; value <= 14; value++) {
            if (this.occurrences[value] == 3) {
                return value;
            }
        }
        return -1;
    }


    /*************************************** SUITE ***************************************/
    public boolean containsSuite() {
        int firstValue = 0; //pour récupérer la valeur de la carte la plus basse de la main
        for (int value=2; value<=14; value++) {
            if (this.occurrences[value] >= 1) {
                firstValue = value;
                break;
            }
        }
        return occurrences[firstValue] == 1 && occurrences[firstValue+1] == 1
                && occurrences[firstValue+2] == 1 && occurrences[firstValue+3] == 1 && occurrences[firstValue+4] == 1;
    }


    /*************************************** COULEUR ***************************************/
    public boolean containsCouleur() {
        String color = cards.getFirst().getColor();
        for (Card card : this.cards) {
            if (!color.equals(card.getColor())) {
                return false;
            }
        }
        return true;
    }

    /******  va returner la valeur de la carte la plus forte de la couleur si Hand  est une couleur, -1 sinon */
    public int valueOfCouleur() {
        if (containsCouleur()) {
            return getMaxValue();
        }
        return -1;
    }
    /*************************************** FULL ***************************************/
    public boolean containsFull() {
        return this.containsBrelan() && this.containsPaire();  // La paire n'est pas inclus dans le Brelan
    }

    /**va return la valeur de la carte formant le brelan si la main est un full, -1 sinon*/
    public int valueOfContainFull() {
        if (this.containsFull()) {
            return this.valueOfBrelan();
        }
        return -1;
    }


    /*************************************** CARRE ***************************************/
    public boolean containsCarre() {
        for (int value = 2; value <= 14; value++) {
            if (this.occurrences[value] == 4) {

                return true;
            }
        }
        return false;
    }

    /********* va retourner la valeur de la carte formant le carré si Hand est un carré, -1 sinon  *************************/
    public int valueOfCarre() {
        for (int i = 2; i <= 14; i++) {
            if (this.occurrences[i] == 4) {
                return i;
            }
        }
        return -1;
    }


    /*************************************** QUINTE FLUSH ***************************************/
    public boolean containsQuinteFlush() {
        return this.containsCouleur() && this.containsSuite();
    }

    /** va retourner la valeur de la carte la plus forte de la quinte flush si la main est une quinte flush, -1 sinon *****/
    public int valueQuinteFlush() {
        if (containsQuinteFlush()) {
            return getMaxValue();
        }
        return -1;
    }

    /***************************************** QUINTE FLUSH ROYALE  ***************************************/
    public boolean containsQuinteFlushRoyale() {
        return this.containsQuinteFlush() && this.getMaxValue() == 14;
    }



    /***************************************** FONCTIONS POUR COMPARER  ***************************************/
    public String compareMaxValues(Hand hand){ // à changer
        for (int value=14; value>=2; value--) {
            if (this.occurrences[value] > hand.occurrences[value])  {
                return "Main 1 " + this + " a gagné contre Main 2 " + hand + " avec carte la plus élevée : " + display.getCardValue(value);
            }
            if (this.occurrences[value] < hand.occurrences[value]) {
                return "Main 2 " + hand + " a gagné contre Main 1 " + this + " avec carte la plus élevée : " + display.getCardValue(value);
            }
        }
        return "Égalité";
    }

    public String compareHand(Hand hand) {

        int value1 = this.getValue();
        int value2 = hand.getValue();

        if (value1 > value2) return "Main 1 " + this + " a gagné contre Main 2 " + hand + " avec " + display.valueToCombinaison(this);
        else if (value1 < value2) return "Main 2 " + hand + " a gagné contre Main 1 " + this + " avec " + display.valueToCombinaison(hand);
        else  { //cas d'égalité des valeurs des mains, effectuer les comparaisons nécessaires

            switch(this.value){
                case 0,4,5,8: //Cas carte haute, suite, couleur ou quinteflush
                    return this.compareMaxValues(hand);
                case 1: //Cas Paire
                    if (this.valueOfPaire() > hand.valueOfPaire()) {
                        return "Main 1 " + this + " a gagné contre Main 2 " + hand + " avec paire de " + display.getCardValue(this.valueOfPaire());
                    }
                    else if (this.valueOfPaire() < hand.valueOfPaire()) {
                        return "Main 2 " + hand + " a gagné contre Main 1 " + this + " avec paire de " + display.getCardValue(hand.valueOfPaire());
                    }
                    else {
                        return this.compareMaxValues(hand);
                    }
                case 2: //Cas Deux Paires
                    if (this.valueOfDoublePaire() > hand.valueOfDoublePaire()) {
                        return "Main 1 " + this + " a gagné contre Main 2 " + hand + " avec paire de " + display.getCardValue(this.valueOfDoublePaire());
                    }
                    else if (this.valueOfDoublePaire() < hand.valueOfDoublePaire()) {
                        return "Main 2 " + hand + " a gagné contre Main 1  " + this + " avec paire de " + display.getCardValue(hand.valueOfDoublePaire());
                    }
                    else {
                        if (this.lowerPairValue() > hand.lowerPairValue()) {
                            return "Main 1 " + this + " a gagné contre Main 2 " + hand + " avec la paire la plus basse de " + display.getCardValue(this.lowerPairValue());
                        } else if(this.lowerPairValue() < hand.lowerPairValue()) {
                            return "Main 2 " + hand + " a gagné contre Main 1 " + this + " avec la paire la plus basse de " + display.getCardValue(hand.lowerPairValue());
                        } else {
                            return this.compareMaxValues(hand);
                        }
                    }
                case 3: //Cas Brelan
                    if (this.valueOfBrelan() > hand.valueOfBrelan()) {
                        return "Main 1 " + this + " a gagné contre Main 2 " + hand + " avec brelan de " + display.getCardValue(this.valueOfBrelan());
                    } else {
                        return "Main 2 " + hand + " a gagné contre Main 1 " + this + " avec brelan de " + display.getCardValue(hand.valueOfBrelan());
                    }
                case 6: //cas du full
                    if (this.valueOfContainFull() > hand.valueOfContainFull()) {
                        return "Main 1 " + this + " a gagné contre Main 2 " + hand + " avec full de " + display.getCardValue(this.valueOfContainFull());
                    } else {
                        return "Main 2 " + hand + " a gagné contre Main 1 " + this + " avec full de " + display.getCardValue(hand.valueOfContainFull());
                    }
                case 7 : // cas de carré
                    if (this.valueOfCarre() > hand.valueOfCarre()) {
                        return "Main 1 " + this + " a gagné contre Main 2 " + hand + " avec carré de " + display.getCardValue(this.valueOfCarre());
                    } else {
                        return "Main 2 " + hand + " a gagné contre Main 1 " + this + " avec carré de " + display.getCardValue(hand.valueOfCarre());
                    }
                case 9: // containsQuinteFlushRoyale
                    return "Égalité : Quinte Flush Royale ";

                default:
                    return this.compareMaxValues(hand);
            }

        }
    }

}