package kz.dorm.util;

import kz.dorm.utils.ControlWrite;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestControlWrite {

    @Test
    public void testCheckPhone(){
        assertTrue(ControlWrite.isCheckPhone("87770452918"));   // true
        assertTrue(!ControlWrite.isCheckPhone("877770452918")); // false
        assertTrue(!ControlWrite.isCheckPhone("877704529180")); // false

        assertTrue(ControlWrite.isCheckPhone("+77770452918"));  // true
        assertTrue(!ControlWrite.isCheckPhone("+7777045291"));  // false
        assertTrue(!ControlWrite.isCheckPhone("+7770452918"));  // false

        assertTrue(ControlWrite.isCheckPhone("99-88-77"));    // true
        assertTrue(!ControlWrite.isCheckPhone("99-98-877"));  // false
        assertTrue(!ControlWrite.isCheckPhone("+99-88-77"));  // false
        assertTrue(!ControlWrite.isCheckPhone("+799-88-77")); // false
    }

    @Test
    public void testCheckNames(){
        assertTrue(ControlWrite.isCheckNames("Влад", "Влад"));    // true
        assertTrue(!ControlWrite.isCheckNames("1Влад", "Влад"));  // false
        assertTrue(!ControlWrite.isCheckNames("влад", "Влад"));   // false
        assertTrue(!ControlWrite.isCheckNames("Vlad", "Влад"));   // false
    }

    @Test
    public void testCheckAddress(){
        assertTrue(ControlWrite
                .isCheckAddress("ул.Малинова, г.Караганда, ёё Казахстан 23-23-23"));                 // true
        assertTrue(!ControlWrite
                .isCheckAddress("Malinova 23-23-23 ;"));                                             // false
        assertTrue(!ControlWrite
                .isCheckAddress("123456789123456789123456789123456789123456789123456789123456789")); // false
    }

    @Test
    public void testCheckGroup(){
        assertTrue(ControlWrite.isCheckGroup("ВТ-13С"));                   // true
        assertTrue(!ControlWrite.isCheckGroup("VT-13"));                   // false
        assertTrue(!ControlWrite.isCheckGroup("VVVVVVVVVVVVVVVVVVVVVVV")); // false
    }
}