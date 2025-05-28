package poker.pns;

import static poker.pns.Main.HAND_SIZE;

public class Display {



    public void validateHand(Hand hand ){
        if (hand.getNbCards()!=HAND_SIZE) {
            throw new IllegalArgumentException("Une main de poker doit contenir exactement 5 cartes.");
        }
        if (!hand.validateCardHand()) {
            throw new IllegalArgumentException("Vous avez rentré deux fois la même carte");
        }
    }


    public String ValueToFaceCard(int value) {
        switch (value) {
            case 11 :
                return "V";
            case 12 :
                return "D";
            case 13 :
                return "R";
            case 14 :
                return "A";
            default :
                return "Error";
        }
    }

    public String valueToCombinaison(Hand hand) { // à mettre dans une classe pour l'affichage des résultats
        switch (hand.getValue()) {
            case 9 : return "Quinte Flush Royale";
            case 8 : return "Quinte Flush";
            case 7 : return "Carre";
            case 6 : return "Full";
            case 5 : return "Couleur";
            case 4 : return "Suite";
            case 3 : return "Brelan";
            case 2 : return "Double Paire";
            case 1 : return "Paire";
            case 0 : return "Plus haute carte";
            default : return "erreur";
        }
    }

    public String getCardValue(int value){ // à mettre dans une classe pour l'affichage des résultats
        return switch(value) {
            case 11 -> "Valet";
            case 12 -> "Dame";
            case 13 -> "Roi";
            case 14 -> "As";
            default -> Integer.toString(value);
        };
    }
}
