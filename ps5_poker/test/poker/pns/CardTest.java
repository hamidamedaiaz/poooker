package poker.pns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void cardInitialisationTest() {
        Card card1 = new Card(3, "Tr");
        Card card2 = new Card(6, "Pi");
        Card card3 = new Card(10, "Co");
        Card card4 = new Card(13, "Ca");
        Card card5 = new Card(13, "Ca");


        assert(3 == card1.getValue());
        assert(6 == card2.getValue());
        assert(10 == card3.getValue());
        assert(13 == card4.getValue());

        assertEquals("Tr", card1.getColor());
        assertEquals("Pi", card2.getColor());
        assertEquals("Co", card3.getColor());
        assertEquals("Ca", card4.getColor());
    }
    @Test
    void testValidateCard(){
        Card card1 = new Card(3, "Tr");
        Card card2 = new Card(3, "Tr");
        Card card3 = new Card(10, "Co");
        Card card4 = new Card(10, "Ca");

        assertFalse(card1.validateCard(card2));
        assertTrue(card3.validateCard(card4));

    }
}