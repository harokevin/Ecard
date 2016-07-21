import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class KCryptTest {

	
	private String testString1 = "Kevin";
	private String testString2 = "JOEjackson";
	private String testString3 = "";
	private String testString4 = "####////....12345";
	//TODO: Conduct boundary testing

	
	@Test
	public void encryptTestSingleUpper(){
		String expectedResult = "Mgxkp";
		String actualResult =  KCrypt.encrypt(testString1);
		assertTrue(expectedResult.equals(actualResult));
		
	}
	
	@Test
	public void decryptTestSingleUpper(){
		String expectedResult = "Kevin";
		String actualResult = KCrypt.decrypt(KCrypt.encrypt(testString1));
		assertTrue(expectedResult.equals(actualResult));
	}
	
	@Test
	public void encryptTestEmptyString(){
		String expectedResult = "";
		String actualResult = KCrypt.encrypt(testString3);
		assertTrue(expectedResult.equals(actualResult));
	}
	
	@Test
	public void decryptTestEmptyString(){
		String expectedResult = "";
		String actualResult = KCrypt.decrypt(KCrypt.encrypt(testString3));
		assertTrue(expectedResult.equals(actualResult));
	}
	
	@Test
	public void encryptTestSpecialChars(){
		String expectedResult = "%%%%1111000034567";
		String actualResult = KCrypt.encrypt(testString4);
		assertTrue(expectedResult.equals(actualResult));
	}
	
}
