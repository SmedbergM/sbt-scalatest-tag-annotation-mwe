package smedbergm.mwe.service1;

import org.scalatestplus.testng.TestNGSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Service1UnitTest extends TestNGSuite {
    @Test
    public void concatenateStrings() {
        String part1 = "foo";
        String part2 = "bar";
        assertEquals(part1 + part2, "foobar");
    }
}
