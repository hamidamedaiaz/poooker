package poker.pns;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final int HAND_SIZE = 5;

    public static void main(String[] args) {
        Display display = new Display();
        boolean reponseRejouer; // Déclarer un boolean pour la boucle While

        do {
            reponseRejouer = false; // Réinitialisation à chaque début de partie

            Scanner scanner = new Scanner(System.in);
            Hand hand1, hand2;
            List<String> cardsName1;
            List<String> cardsName2;

            // Entrée des cartes pour la Main 1
            System.out.print("Entrez 5 cartes pour la Main 1 (ex: 2Tr 10Pi ACo RCa DTr): ");
            String cardNameHand1 = scanner.nextLine();
            cardsName1 = Arrays.asList(cardNameHand1.split(" "));
            hand1 = Conversion.createHand(cardsName1, HAND_SIZE); // Création de la main 1

            // exception
            display.validateHand(hand1);


            // Entrée des cartes pour la Main 2
            System.out.print("Entrez 5 cartes pour la Main 2 (ex: 2Pi 3Pi ACa RCo 4Tr): ");
            String cardNameHand2 = scanner.nextLine();
            cardsName2 = Arrays.asList(cardNameHand2.split(" "));
            hand2 = Conversion.createHand(cardsName2, HAND_SIZE); // Création de la main 2


            //verifier l'entrée des deux hand

            while (!hand1.confirmHand(hand2)){
                System.out.print("Vous avez rentré deux fois  même carte(s). Veuillez rentrer la main à nouveau \nla Main 2 : ");
                cardNameHand2 = scanner.nextLine();

                cardsName2 = Arrays.asList(cardNameHand2.split(" "));
                hand2 = Conversion.createHand(cardsName2, HAND_SIZE); // Création de la main 2
                display.validateHand(hand2);
            }
            // exception
            display.validateHand(hand2);

            // Comparaison des mains
            System.out.println("\n" + hand1.compareHand(hand2) + "\n");

            System.out.print("Vous voulez encore jouer ? (y/n) : ");// Demander si l'utilisateur veut rejouer
            String reponse = scanner.nextLine();
            if (reponse.contains("y")) {
                reponseRejouer = true;
            }
            else {
                reponseRejouer = false;
            }

        } while (reponseRejouer);

        System.out.println("Merci d'avoir joué !");
    }

    /*
    exempleq de comparaison :
        sans combinaison :
        Main 1 : 2Tr 3Co 4Tr 8Co 10Tr
        Main 2 : 4Pi 5Co 6Tr 8Ca 10Pi

        Paire :
        Main 1 : 3Tr 8Co 4Tr 7Co 7Tr
        Main 2 : 3Pi 5Co DTr 7Pi 7Ca

        Main 1 : 3Tr 8Co 4Tr 7Co 7Tr
        Main 2 : 3Pi 8Ca 4Pi RCo RTr

        Double paire:
        Main 1 : 8Tr 3Co 3Pi 7Ca 7Co
        Main 2 : 2Tr 5Co 5Pi 7Pi 7Tr

        Full:
        Main 1 : ACo ACa APi 5Tr 5Ca
        Main 2 : RCo RCa RPi DTr DCa

        Double Paire et Carré:
        Main 1 : 5Tr 5Co RPi RTr VCo
        Main 2 : 2Tr 2Co 2Pi 2Ca 3Co


        Couleur et Quinte Flush:
        Main 1: 10Pi 5Pi APi 3Pi VPi
        Main 2: 3Ca 4Ca 5Ca 6Ca 7Ca

     */
}
