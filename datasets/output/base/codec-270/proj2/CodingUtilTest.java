import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class CodingUtilTest {

    @Test
    public void testVulnerabilityTriggered() {
        // Test input data
        String input = "AB==";

        // Intercept the vulnerable method call
        MethodCallInterceptor.interceptor(
            org.apache.commons.codec.binary.Base64.class,
            "decodeBase64",
            new Object[]{input}
        );

        try {
            // Call the function under test
            CodingUtil.aesDecrypt(input, "secretKey", "iv"); // Replace "secretKey" and "iv" with actual values
        } catch (Exception e) {
            // Exception caught, do nothing
        }

        // Assert that the vulnerability was successfully triggered
        assertTrue(MethodCallInterceptor.isTriggered());
        assertTrue(MethodCallInterceptor.isConditionMet());
    }
}
