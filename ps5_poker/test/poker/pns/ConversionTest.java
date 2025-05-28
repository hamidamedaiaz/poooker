package poker.pns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static poker.pns.Conversion.*;

class ConversionTest {

    @Test
    void testConvertToValue() {
        assertEquals(2, convertToValue("2Tr"));
        assertEquals(5, convertToValue("5Co"));
        assertEquals(10, convertToValue("10Pi"));
        assertEquals(11, convertToValue("VCo"));
        assertEquals(12, convertToValue("DTr"));
        assertEquals(13, convertToValue("RCo"));
        assertEquals(14, convertToValue("ACa"));
    }

    @Test
    void testConvertToColor() {
        assertEquals("Tr", convertToColor("2Tr"));
        assertEquals("Co", convertToColor("5Co"));
        assertEquals("Pi", convertToColor("10Pi"));
        assertEquals("Co", convertToColor("VCo"));
        assertEquals("Tr", convertToColor("DTr"));
        assertEquals("Co", convertToColor("RCo"));
        assertEquals("Ca", convertToColor("ACa"));
    }

    @Test
    void testConvertToCard() {
        assertEquals(new Card(2, "Tr"), convertToCard("2Tr"));
        assertEquals(new Card(5, "Co"), convertToCard("5Co"));
        assertEquals(new Card(10, "Pi"), convertToCard("10Pi"));
        assertEquals(new Card(11, "Co"), convertToCard("VCo"));
        assertEquals(new Card(12, "Tr"), convertToCard("DTr"));
        assertEquals(new Card(13, "Co"), convertToCard("RCo"));
        assertEquals(new Card(14, "Ca"), convertToCard("ACa"));
    }
}