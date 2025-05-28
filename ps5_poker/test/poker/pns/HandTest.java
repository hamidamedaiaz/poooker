package poker.pns;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void testTostring(){
        Hand main = Conversion.createHand(Arrays.asList("10Tr", "ACo", "APi", "DCa", "RCo"), 5);
        assertEquals("(10Tr ACo APi DCa RCo)", main.toString());
    }

    @Test
    void testContainsPaire(){
        Hand hand1 = Conversion.createHand(Arrays.asList("3Tr", "3Co"), 2);
        Hand hand2 = Conversion.createHand(Arrays.asList("3Tr", "7Co"), 2);
        Hand hand3 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "3Tr", "8Co"), 4);
        Hand hand4 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "9Tr", "5Co"), 4);
        Hand hand5 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "4Tr", "8Co", "7Tr"), 5);
        Hand hand6 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "9Tr", "5Co", "6Tr"), 5);
        assert(hand1.containsPaire());
        assert(!hand2.containsPaire());
        assert(hand3.containsPaire());
        assert(!hand4.containsPaire());
        assert(hand5.containsPaire());
        assert(!hand6.containsPaire());
        assertEquals(hand1.containsPaire(),hand3.containsPaire() );
    }

    @Test
    void containsDoublePaireTest() {
        Hand hand1 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "3Pi", "7Tr"), 4);
        Hand hand2 = Conversion.createHand(Arrays.asList("3Tr", "3Co", "3Ca", "3Pi"), 4);
        Hand hand3 = Conversion.createHand(Arrays.asList("ACa", "ACo", "APi", "ATr"), 4);

        assertTrue(hand1.containsDoublePaire());
        assertFalse(hand2.containsDoublePaire());
        assertFalse(hand3.containsDoublePaire());

        Hand hand7 = Conversion.createHand(Arrays.asList("ATr", "APi", "RCo", "5Pi", "RCa"), 5);
        Hand hand8 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "3Pi", "DPi", "7Tr"), 5);
        Hand hand9 = Conversion.createHand(Arrays.asList("3Ca", "3Co", "3Pi", "3Tr", "4Ca"), 5);
        Hand hand10 = Conversion.createHand(Arrays.asList("ACa", "ACo", "APi", "RPi", "ATr"), 5);

        assertTrue(hand7.containsDoublePaire());
        assertTrue(hand8.containsDoublePaire());
        assertFalse(hand9.containsDoublePaire());
        assertFalse(hand10.containsDoublePaire());


        Hand hand12 = Conversion.createHand(Arrays.asList("3Tr", "3Co", "7Co", "4Ca"), 4);
        Hand hand13 = Conversion.createHand(Arrays.asList("3Tr", "3Ca", "3Co", "4Ca"), 4);
        Hand hand14 = Conversion.createHand(Arrays.asList("RTr", "2Co", "3Tr", "VCa"), 4);

        assertFalse(hand12.containsDoublePaire());
        assertFalse(hand13.containsDoublePaire());
        assertFalse(hand14.containsDoublePaire());

        Hand hand15 = Conversion.createHand(Arrays.asList("3Tr", "Vco", "3Ca", "7Co", "4Ca"), 5);
        Hand hand17 = Conversion.createHand(Arrays.asList("3Tr", "3Ca", "3Co", "7Co", "4Ca"), 5);
        Hand hand18 = Conversion.createHand(Arrays.asList("RTr", "2Co", "3Tr", "7Co", "VCa"), 5);

        assertFalse(hand15.containsDoublePaire());
        assertFalse(hand17.containsDoublePaire());
        assertFalse(hand18.containsDoublePaire());
    }

    @Test
    void testcontainsBrelan(){
        Hand hand1 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "2Pi"), 3);
        Hand hand2 = Conversion.createHand(Arrays.asList("3Tr", "3Co", "5Pi"), 3);
        Hand hand3 = Conversion.createHand(Arrays.asList("3Tr", "3Co", "3Pi"), 3);
        Hand hand4 = Conversion.createHand(Arrays.asList("ACa", "APi", "ATr"), 3);

        assertFalse(hand1.containsBrelan());
        assertFalse(hand2.containsBrelan());
        assertTrue(hand3.containsBrelan());
        assertTrue(hand4.containsBrelan());

        Hand hand5 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "2Pi", "8Ca"), 4);
        Hand hand6 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "3Pi", "8Ca"), 4);
        Hand hand7 = Conversion.createHand(Arrays.asList("8Tr", "8Co", "2Pi", "8Ca"), 4);
        Hand hand8 = Conversion.createHand(Arrays.asList("7Tr", "7Co", "7Pi", "7Ca"), 4);

        assertFalse(hand5.containsBrelan());
        assertFalse(hand6.containsBrelan());
        assertTrue(hand7.containsBrelan());
        assertFalse(hand8.containsBrelan());



        Hand hand9 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "2Pi", "6Ca", "5Co"), 5);
        Hand hand10 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "3Pi", "6Ca", "5Co"), 5);
        Hand hand11= Conversion.createHand(Arrays.asList("2Tr", "2Co", "2Pi", "6Ca", "5Co"), 5);
        Hand hand12 = Conversion.createHand(Arrays.asList("6Tr", "7Co", "6Pi", "6Ca", "6Co"), 5);
        Hand hand13 = Conversion.createHand(Arrays.asList("7Tr", "7Co", "7Pi", "7Ca", "7Co"), 5);
        Hand hand14 = Conversion.createHand(Arrays.asList("7Tr", "7Co", "ACa", "APi", "ATr"), 5);

        assertFalse(hand9.containsBrelan());
        assertFalse(hand10.containsBrelan());
        assertTrue(hand11.containsBrelan());
        assertFalse(hand12.containsBrelan());
        assertFalse(hand13.containsBrelan());
        assertTrue(hand14.containsBrelan());
    }

    @Test
    void testcontainsSuite(){
        Hand hand1 = Conversion.createHand(Arrays.asList("10Tr", "ACo", "APi", "DCa", "RCo"), 5);
        Hand hand2 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "3Pi", "6Ca", "5Co"), 5);
        Hand hand3= Conversion.createHand(Arrays.asList("2Tr", "3Co", "2Pi", "4Ca", "5Co"), 5);
        Hand hand4 = Conversion.createHand(Arrays.asList("5Tr", "6Co", "7Pi", "8Ca", "9Co"), 5);
        Hand hand5 = Conversion.createHand(Arrays.asList("3Tr", "4Co", "5Pi", "6Ca", "7Co"), 5);
        Hand hand6 = Conversion.createHand(Arrays.asList("10Tr", "VCo", "DCa", "RPi", "ATr"), 5);

        assertFalse(hand1.containsSuite());
        assertFalse(hand2.containsSuite());
        assertFalse(hand3.containsSuite());
        assertTrue(hand4.containsSuite());
        assertTrue(hand5.containsSuite());
        assertTrue(hand6.containsSuite());
    }

    @Test
    void testcontainsCouleur(){

        Hand hand1 = Conversion.createHand(Arrays.asList("10Tr", "5Co", "APi", "3Ca", "RCo"), 5);
        Hand hand2 = Conversion.createHand(Arrays.asList("3Tr", "7Tr", "3Pi", "6Tr", "5Co"), 5);
        Hand hand3= Conversion.createHand(Arrays.asList("8Pi", "3Co", "2Pi", "4Pi", "9Pi"), 5);
        Hand hand4 = Conversion.createHand(Arrays.asList("5Tr", "ATr", "6Tr", "8Tr", "9Tr"), 5);
        Hand hand5 = Conversion.createHand(Arrays.asList("3Ca", "4Ca", "VCa", "DCa", "7Ca"), 5);

        assertFalse(hand1.containsCouleur());
        assertFalse(hand2.containsCouleur());
        assertFalse(hand3.containsCouleur());
        assertTrue(hand4.containsCouleur());
        assertTrue(hand5.containsCouleur());
    }

    @Test
    void testContainsFull(){
        Hand hand1 = Conversion.createHand(Arrays.asList("10Tr", "5Co", "APi", "3Ca", "RCo"), 5);
        Hand hand2 = Conversion.createHand(Arrays.asList("10Tr", "5Co", "APi", "RCa", "RCo"), 5);
        Hand hand3 = Conversion.createHand(Arrays.asList("10Tr", "10Co", "10Pi", "3Ca", "RCo"), 5);
        Hand hand4 = Conversion.createHand(Arrays.asList("10Tr", "10Co", "10Pi", "VCa", "VCo"), 5);

        assertFalse(hand1.containsFull());
        assertFalse(hand2.containsFull());
        assertFalse(hand3.containsFull());
        assertTrue(hand4.containsFull());
    }

    @Test
    void testContainsCarre(){
        Hand hand1 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "2Pi", "8Ca"), 4);
        Hand hand2 = Conversion.createHand(Arrays.asList("3Tr", "3Co", "2Pi", "8Ca"), 4);
        Hand hand3 = Conversion.createHand(Arrays.asList("3Tr", "8Co", "8Pi", "8Ca"), 4);
        Hand hand4 = Conversion.createHand(Arrays.asList("7Tr", "7Co", "7Pi", "7Ca"), 4);

        assertFalse(hand1.containsCarre());
        assertFalse(hand2.containsCarre());
        assertFalse(hand3.containsCarre());
        assertTrue(hand4.containsCarre());



        Hand hand5= Conversion.createHand(Arrays.asList("2Tr", "8Co", "7Pi", "6Ca", "5Co"), 5);
        Hand hand6= Conversion.createHand(Arrays.asList("2Tr", "2Co", "8Pi", "6Ca", "5Co"), 5);
        Hand hand7= Conversion.createHand(Arrays.asList("2Tr", "2Co", "2Pi", "6Ca", "5Co"), 5);
        Hand hand8= Conversion.createHand(Arrays.asList("2Tr", "2Co", "2Pi", "2Ca", "5Co"), 5);

        assert(!hand5.containsCarre());
        assert(!hand6.containsCarre());
        assert(!hand7.containsCarre());
        assert(hand8.containsCarre());
    }

    @Test
    void testcontainsQuinteFlush(){
        Hand hand1 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "2Pi", "6Ca", "5Co"), 5);
        Hand hand2 = Conversion.createHand(Arrays.asList("3Ca", "4Ca", "5Ca", "6Ca", "7Ca"), 5);
        Hand hand3 = Conversion.createHand(Arrays.asList("5Tr", "ATr", "3Tr", "8Tr", "2Tr"), 5);
        Hand hand4 = Conversion.createHand(Arrays.asList("3Tr", "4Co", "5Tr", "6Tr", "7Ca"), 5);


        assertFalse(hand1.containsQuinteFlush());
        assertTrue(hand2.containsQuinteFlush());
        assertFalse(hand3.containsQuinteFlush());
        assertFalse(hand4.containsQuinteFlush());
    }

    @Test
    void testcontainsQuinteFlushRoyale(){

        Hand hand1 = Conversion.createHand(Arrays.asList("10Tr", "VTr", "DTr", "RTr", "ATr"), 5);
        Hand hand2 = Conversion.createHand(Arrays.asList("RPi", "10Pi", "DPi",  "APi" , "VPi"), 5);
        Hand hand3 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "2Pi", "6Ca", "5Co"), 5);
        Hand hand4 = Conversion.createHand(Arrays.asList("RTr", "10Pi", "DCo",  "APi" , "VPi"), 5);

        assert(hand1.containsQuinteFlushRoyale());
        assert(hand2.containsQuinteFlushRoyale());
        assert(!hand3.containsQuinteFlushRoyale());
        assert(!hand4.containsQuinteFlushRoyale());
    }

    @Test
    void testCompareHand(){
        Hand hand1 = Conversion.createHand(Arrays.asList("2Tr", "3Co", "4Tr", "8Co", "10Tr"), 5);///  value = 0    plus haute carte
        Hand hand2 = Conversion.createHand(Arrays.asList("4Pi", "5Co", "6Tr", "8Ca", "10Pi"), 5);///  value = 0    plus haute carte
        Hand hand3 = Conversion.createHand(Arrays.asList("2Tr", "3Co", "5Tr", "8Co", "RTr"), 5);///  value = 0    plus haute carte
        Hand handPaire1 = Conversion.createHand(Arrays.asList("3Tr", "8Co", "4Tr", "7Co", "7Tr"), 5);///  value = 1    paire
        Hand handPaire2 = Conversion.createHand(Arrays.asList("3Tr", "5Co", "DTr", "7Pi", "7Ca"), 5);///  value = 1    paire
        Hand handPaire3 = Conversion.createHand(Arrays.asList("3Tr", "8Co", "4Tr", "RCo", "RTr"), 5);///  value = 1    paire
        Hand handDoublePaire1 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "3Pi", "7Tr","8Co"), 5);///   value = 2    double Paire
        Hand handDoublePaire2 = Conversion.createHand(Arrays.asList("2Ca", "7Pi", "2Co", "VCa","VCo"), 5);///   value = 2    double Paire
        Hand handDoublePaire3 = Conversion.createHand(Arrays.asList("5Tr", "7Co", "5Pi", "7Tr","2Co"), 5);///   value = 2    double Paire
        Hand handBrelan1 = Conversion.createHand(Arrays.asList("7Tr", "9Co", "ACa", "APi", "ATr"), 5);/// value = 3     Brelan
        Hand handBrelan2 = Conversion.createHand(Arrays.asList("3Tr", "9Co", "7Ca", "7Pi", "7Tr"), 5);/// value = 3     Brelan
        Hand handSuite1 = Conversion.createHand(Arrays.asList("10Tr", "VCo", "DPi", "RCa", "ACo"), 5);/// value = 4    suite
        Hand handSuite2 = Conversion.createHand(Arrays.asList("4Tr", "5Co", "6Pi", "7Ca", "8Co"), 5);/// value = 4    suite
        Hand handCouleur1 = Conversion.createHand(Arrays.asList("10Pi", "5Pi", "APi", "3Pi", "VPi"), 5); /// value = 5   couleur
        Hand handCouleur2 = Conversion.createHand(Arrays.asList("10Tr", "5Tr", "DTr", "3Tr", "VTr"), 5); /// value = 5   couleur
        Hand handFull1 = Conversion.createHand(Arrays.asList("ACo", "ACa", "APi", "5Tr", "5Ca"), 5);/// value = 6    full
        Hand handFull2 = Conversion.createHand(Arrays.asList("RCo", "RCa", "RPi", "7Tr", "7Ca"), 5);/// value = 6    full
        Hand handCarre1 = Conversion.createHand(Arrays.asList("2Tr", "2Co", "2Pi", "2Ca", "5Co"), 5); /// value = 7    carre
        Hand handCarre2 = Conversion.createHand(Arrays.asList("3Tr", "3Co", "3Pi", "3Ca", "5Co"), 5); /// value = 7    carre
        Hand handQuinteFlush1 = Conversion.createHand(Arrays.asList("3Ca", "4Ca", "5Ca", "6Ca", "7Ca"), 5);/// value = 8     Quinte Flush
        Hand handQuinteFlush2 = Conversion.createHand(Arrays.asList("3Pi", "4Pi", "5Pi", "6Pi", "7Pi"), 5);/// value = 8     Quinte Flush
        Hand handQuinteFlush3 = Conversion.createHand(Arrays.asList("6Co", "7Co", "8Co", "9Co", "10Co"), 5);/// value = 8     Quinte Flush
        Hand handQuinteFlushRoyale1 = Conversion.createHand(Arrays.asList("10Tr", "VTr", "DTr", "RTr", "ATr"), 5);/// value =9    Quinte Flush Royale
        Hand handQuinteFlushRoyale2 = Conversion.createHand(Arrays.asList("10Co", "VCo", "DCo", "RCo", "ACo"), 5);/// value =9    Quinte Flush Royale

        assertEquals(0,hand1.getValue());
        assertEquals(1,handPaire1.getValue());
        assertEquals(2,handDoublePaire1.getValue());
        assertEquals(3,handBrelan1.getValue());
        assertEquals(4,handSuite1.getValue());
        assertEquals(5,handCouleur1.getValue());
        assertEquals(6,handFull1.getValue());
        assertEquals(7,handCarre1.getValue());
        assertEquals(8,handQuinteFlush1.getValue());
        assertEquals(9,handQuinteFlushRoyale1.getValue());

        System.out.println(hand1.compareHand(hand2));
        System.out.println(hand1.compareHand(hand3));
        System.out.println(handPaire1.compareHand(handPaire2));
        System.out.println(handPaire1.compareHand(handPaire3));
        System.out.println(handDoublePaire1.compareHand(handDoublePaire2));
        System.out.println(handDoublePaire1.compareHand(handDoublePaire3));
        System.out.println(handBrelan1.compareHand(handBrelan2));
        System.out.println(handSuite1.compareHand(handSuite2));
        System.out.println(handCouleur1.compareHand(handCouleur2));
        System.out.println(handFull1.compareHand(handFull2));
        System.out.println(handCarre1.compareHand(handCarre2));
        System.out.println(handQuinteFlush1.compareHand(handQuinteFlush2));
        System.out.println(handQuinteFlush1.compareHand(handQuinteFlush3));
        System.out.println(handQuinteFlushRoyale1.compareHand(handQuinteFlushRoyale2));

        System.out.println(hand1.compareHand(handPaire1));
        System.out.println(handPaire1.compareHand(handDoublePaire1));
        System.out.println(handDoublePaire1.compareHand(handBrelan1));
        System.out.println(handBrelan1.compareHand(handSuite1));
        System.out.println(handSuite1.compareHand(handCouleur1));
        System.out.println(handCouleur1.compareHand(handFull1));
        System.out.println(handFull1.compareHand(handCarre1));
        System.out.println(handCarre1.compareHand(handQuinteFlush1));
        System.out.println(handQuinteFlush1.compareHand(handQuinteFlushRoyale1));
    }

    /**
     * teste methode verifyHand pour eviter les doublon dans hand  ex: 3Tr", "3Tr", "9Tr", "5Co", "6Tr ==> 3Tr et 3Tr  c'est imposiible !!!!!!
     */
    @Test
    void testValidateHand(){
        Hand hand0 = Conversion.createHand(Arrays.asList("3Tr", "3Tr", "9Tr", "5Co", "6Tr"), 5);
        Hand hand1 = Conversion.createHand(Arrays.asList("3Tr", "7Co", "4Tr", "8Co", "7Tr"), 5);
        assertTrue(hand1.validateCardHand());
        assertFalse(hand0.validateCardHand());
    }


    @Test
    void testConfirmHand(){
        Hand hand0 = Conversion.createHand(Arrays.asList("3Tr", "3Tr", "9Tr", "5Co", "6Tr"), 5);// 3Tr ...
        Hand hand1 = Conversion.createHand(Arrays.asList("3Tr", "3Tr", "9Tr", "5Co", "6Tr"), 5);// 3Tr ...   impossible
        assertFalse(hand1.confirmHand(hand0));

        Hand hand2 = Conversion.createHand(Arrays.asList("3Tr", "3Tr", "9Tr", "5Co", "6Tr"), 5);  // 9Tr ...
        Hand hand3 = Conversion.createHand(Arrays.asList("3Co", "3Tr", "9Tr", "5Co", "6Tr"), 5);// 9Tr...  impossible

        assertFalse(hand2.confirmHand(hand3));

        Hand hand4= Conversion.createHand(Arrays.asList("10Tr", "VTr", "DTr", "RTr", "ATr"), 5);// valide
        Hand hand5 = Conversion.createHand(Arrays.asList("10Co", "VCo", "DCo", "RCo", "ACo"), 5);// valide

        assertTrue(hand4.confirmHand(hand5));

    }

}