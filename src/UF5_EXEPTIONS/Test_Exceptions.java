package UF5_EXEPTIONS;

import junit.framework.TestCase;

import java.io.IOException;

public class Test_Exceptions extends TestCase {
    public  void testDNI(){
        if (OperacionsBanc.verifyDNI("45444333B")){
            assertTrue(true);
        }
    }
    public void testNumeroCompte () throws IOException {
        if (OperacionsBanc.verifyNumeroCompte("SH92-4040001732907")){
            assertTrue(true);
        }
    }

}
