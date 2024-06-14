import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class FileWriterTest {

    @Test
    public void testVulnerabilityTriggered() {
        // Test input data
        String input = "\\\\foo\\\\.\\bar";

        try {
            // Intercept the method call to FilenameUtils.normalize
            MethodCallInterceptor.interceptor(
                org.apache.commons.io.FilenameUtils.class, "normalize",
                new Object[]{input}
            );

            // Create an instance of FileWriter using the vulnerable code
            new FileWriter(input);

            // Assert that the vulnerability was successfully triggered
            assertTrue(MethodCallInterceptor.isTriggered());
            assertTrue(MethodCallInterceptor.isConditionMet());
        } catch (Exception e) {
            // Exception handling, if needed
        }
    }
}
