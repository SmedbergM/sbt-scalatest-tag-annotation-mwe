package smedbergm.mwe.service1;

import org.scalatestplus.testng.TestNGSuite;
import org.testng.annotations.Test;
import smedbergm.mwe.e2e.E2ETest;

import static org.testng.Assert.fail;

@E2ETest
public class Service1E2ETest extends TestNGSuite {
    @Test
    public void testService1E2E() {
        fail("No Service 1 endpoint provided!");
    }
}
