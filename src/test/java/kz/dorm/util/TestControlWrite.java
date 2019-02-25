package kz.dorm.util;

import com.google.gson.Gson;
import kz.dorm.api.dorm.util.gson.Parent;
import kz.dorm.utils.ControlWrite;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
        assertTrue(ControlWrite.isCheckNames("Влад", "Влад", "Влад"));   // true
        assertTrue(!ControlWrite.isCheckNames("1Влад", "Влад", "Влад"));  // false
        assertTrue(!ControlWrite.isCheckNames("влад", "Влад", "Влад"));   // false
        assertTrue(!ControlWrite.isCheckNames("Vlad", "Влад", "Влад"));   // false
    }
}
