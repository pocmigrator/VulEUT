package demo.test.demo1test;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XmlUtilTest {
    @Test
    public void testXml2ObjCallsVulnerabilityMethod() {
        File file = new File("/dataset/poc/cve/poc.xml");
        String xmlData = "<void>";
        // Set up an interceptor to detect calls to the XStream.fromXML method
        MethodCallInterceptor.interceptor(
                com.thoughtworks.xstream.XStream.class, "fromXML", new Object[]{file}
        );
        try {
            // Call the xml2Obj method with XML data containing malicious content
//            XmlUtil.xml2Obj(file, Object.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Verify if a call to the XStream.fromXML method was triggered
assertTrue(MethodCallInterceptor.isTriggered());
assertTrue(MethodCallInterceptor.isConditionMet());
    }

    @Test
    public void testVulnerabilityTriggered2() {
        // Test input data
        String inputFilePath = "/IdeaProjects/LLMPocMigration/dataset/poc/cve/poc.xml";

        // Convert inputFilePath to a Java File object
        File inputFile = new File(inputFilePath);

        // Intercept the vulnerable method call
        MethodCallInterceptor.interceptor(
                com.thoughtworks.xstream.XStream.class,
                "fromXML",
                new Object[]{inputFile}
        );

        try {
            // Call the function under test
//            XmlUtil.xml2Obj(inputFile, Object.class); // Replace SomeClass with the appropriate class
        } catch (Exception e) {
            // Exception caught, do nothing
        }

        // Assert that the vulnerability was successfully triggered
assertTrue(MethodCallInterceptor.isTriggered());
assertTrue(MethodCallInterceptor.isConditionMet());
    }
}


