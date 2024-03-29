package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShelterTest {

    private Shelter testShelter;
    private Clothes cl1;
    private Clothes cl2;
    private Clothes cl3;
    private Clothes cl4;

    @BeforeEach
    void runBefore() {
        testShelter = new Shelter();
        cl1 = new Clothes("coat", "black", 42);
        cl2 = new Clothes("shirt", "white", 52);
        cl3 = new Clothes("coat", "black", 42);
        cl4 = null;
    }

    @Test
    void testConstructor() {
        assertEquals(0, testShelter.getAmountFounded());

    }

    @Test
    void testClothes() {
        assertEquals("coat", cl1.getName());
        assertEquals("black", cl1.getColor());
        assertEquals(42, cl1.getSize());
    }

    @Test
    void testClothesEquals() {
        Clothes cl5 = new Clothes("coat", "white", 42);
        Clothes cl6 = new Clothes("skirt", "black", 42);
        assertTrue(cl1.equals(cl3) && cl3.equals(cl1));
        assertTrue(cl1.hashCode() == cl3.hashCode());
        assertFalse(cl1.equals(cl4));
        assertFalse(cl1.equals(testShelter));
        assertFalse(cl1.equals(cl5));
        assertFalse(cl1.equals(cl6));


    }

    @Test
    void testFund() {
        testShelter.fund(500);
        assertEquals(500, testShelter.getAmountFounded());
        testShelter.fund(1);
        assertEquals(501, testShelter.getAmountFounded());
    }

    @Test
    void testUseDonations() {
        testShelter.fund(501);
        testShelter.useDonations(350);
        assertEquals(151, testShelter.getAmountFounded());
        testShelter.useDonations(1);
        assertEquals(150, testShelter.getAmountFounded());
        testShelter.useDonations(150);
        assertEquals(0, testShelter.getAmountFounded());
    }

    @Test
    void testAddLists() {
        assertEquals(0, testShelter.getClothes().size());
        testShelter.addClothes(cl1);
        assertEquals(1, testShelter.getClothes().size());
        testShelter.addClothes(cl2);
        assertEquals(2, testShelter.getClothes().size());
        assertEquals(0, testShelter.getFurnitures().size());
        testShelter.addFurniture("Table");
        testShelter.addFurniture("Bed");
        assertEquals(2, testShelter.getFurnitures().size());
        assertEquals(0, testShelter.getRequests().size());
        testShelter.addRequest("coat");
        testShelter.addRequest("Chair");
        assertEquals(2, testShelter.getRequests().size());
    }

    @Test
    void testDecreasingLists() {
        testShelter.addClothes(cl1);
        testShelter.addClothes(cl2);
        testShelter.takeClothes(cl2);
        testShelter.takeClothes(new Clothes("blouse", "white", 35));
        assertEquals(1, testShelter.getClothes().size());
        testShelter.takeClothes(cl3);
        assertEquals(0, testShelter.getClothes().size());
        testShelter.addFurniture("Table");
        testShelter.addFurniture("Bed");
        testShelter.takeFurniture("Table");
        testShelter.takeFurniture("fridge");
        assertEquals(1, testShelter.getFurnitures().size());
        testShelter.addRequest("coat");
        testShelter.addRequest("Chair");
        assertEquals(2, testShelter.getRequests().size());
        testShelter.addFurniture("Chair");
        assertEquals(1, testShelter.getRequests().size());
        testShelter.addClothes(cl1);
        testShelter.addClothes(cl2);
        assertEquals(0, testShelter.getRequests().size());

    }


}