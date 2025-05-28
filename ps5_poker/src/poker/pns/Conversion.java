package poker.pns;

import java.util.List;

public class Conversion {

    public static int convertFaceCardValue(String faceCard) {
        switch (faceCard) {
            case "A":
                return FaceCard.A.getValueFC();
            case "V":
                return FaceCard.V.getValueFC();
            case "D":
                return FaceCard.D.getValueFC();
            case "R":
                return FaceCard.R.getValueFC();
            default:
                return 0;
        }
        // à faire : gérer les entrées utilisateur absurdes
    }

    /**
     *
     * @param cardName
     * @return la valeur de la carte
     */
    public static int convertToValue(String cardName) {
        String[] faceCards = new String[] {"A", "V", "D", "R"};

        //si la carte est une tête (ex : APi, DCa)
        for (String faceCard : faceCards) {
            if (cardName.contains(faceCard)) {
                return convertFaceCardValue(faceCard);
            }
        }

        //si la carte n'est pas une tête (ex : 2Tr, 10Co)
        return Integer.parseInt(cardName.replaceAll("\\D+", "")); // enlève les caractères dans cardName pour ne garder que les chiffres
    }

    /**
     *
     * @param cardName
     * @return la couleur de la carte
     */
    public static String convertToColor(String cardName) {
        String colorName = cardName.replaceAll("\\d+", "");  // enlève les chiffres dans cardName

        for (String faceCard : new String[] {"A", "V", "D", "R"}) {
            colorName = colorName.replace(faceCard, ""); //remplace les têtes par rien
        }

        return colorName;
    }

    /**
     *
     * @param cardName
     * @return la carte
     */
    public static Card convertToCard(String cardName) {
        return new Card(convertToValue(cardName), convertToColor(cardName));
    }


    /**
     *
     * @param cardsName
     * @param handSize
     * @return une main avec handSize cartes
     */
    public static Hand createHand(List<String> cardsName, int handSize) { //Enzo
        Hand hand = new Hand(handSize);

        // ajoute les cartes dans hand
        for (int i=0; i<handSize; i++) {
            hand.addCard(Conversion.convertToCard(cardsName.get(i)));
        }
        hand.initializeOccurrences();
        hand.calculateValue();
        return hand;
    }
}
