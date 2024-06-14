import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class FileWriterTest {

    @Test
    public void testVulnerabilityTriggered() {
        // Test input data
        String dir = "\\foo\\";
        String prefix = ".";
        String suffix = "bar";
        boolean overwrite = false;

        try {
            // Intercept the method call to FilenameUtils.normalize
            MethodCallInterceptor.interceptor(
                org.apache.commons.io.FilenameUtils.class, "normalize",
                new Object[]{dir + "/" + prefix + "_" + TimeUtils.getTimestamp() + "." + suffix}
            );

            // Vulnerable code under test
            new FileWriter(dir, prefix, suffix, overwrite);
        } catch (Exception e) {
            // Exception handling, if needed
        }

        // Assert that the vulnerability was successfully triggered
        assertTrue(MethodCallInterceptor.isTriggered());
        assertTrue(MethodCallInterceptor.isConditionMet());
    }
}
