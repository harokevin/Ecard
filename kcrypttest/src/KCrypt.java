public class KCrypt{

   private static int offsetValue = 2;
   
   public static String encrypt(String input){
   
      // Create the array to hold the string
      char[] stringArray = stringToArray(input);
      String result = "";
      
      // Encrypt string using ceasar value equal to offsetValue
      for(int i=0; i<input.length(); i++){
         stringArray[i] += offsetValue;
         result += stringArray[i];
      }
      
      // Return the string
      return result;
      
   }
   
   public static String decrypt(String input){
   
      // Create the array to hold the string
      char[] stringArray = stringToArray(input);
      String result = "";
      
      // Decrypt string using ceasar value equal to offsetValue
      for(int i=0; i<input.length(); i++){
         stringArray[i] -= offsetValue;
         result += stringArray[i];
      }
      
      // Return the string
      return result;
   
   }
   
   private static char[] stringToArray(String input){
   
      // Create the array to hold the string
      char[] stringArray = new char[input.length()];
      
      // Initilialze the array
      for(int i=0; i<input.length(); i++){
         stringArray[i] = input.charAt(i);
      }
      
      // Return the array
      return stringArray;
      
   }
   
   private static String arrayToString(char[] input, int inputLength){
      
      // Declare the string that will contain the result
      String result = "";
      
      // Initialize the string
      for(int i=0; i<inputLength; i++){
         result += input[i];      
      }
      
      // Ouput the string
       return result;
   }
}