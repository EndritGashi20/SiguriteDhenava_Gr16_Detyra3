
public abstract class Ciphers implements CipherControl {

  @Override
  public StringBuilder encrypt(String plainString, int offset, String key) {
    return null;
  }

  @Override
  public StringBuilder decrypt(String encryptedString, int offset, String key) {
    return null;
  }
}



class Caesar extends Ciphers implements CipherControl {


  @Override
  public StringBuilder encrypt(String plainString, int offset, String key) {
    StringBuilder encryptedString = new StringBuilder();

    for (int i = 0; i < plainString.length(); i++) {
      if (Character.isUpperCase(plainString.charAt(i))) {
        char ch = (char) (((int) plainString.charAt(i) +
            offset - 65) % 26 + 65);
        encryptedString.append(ch);
      } else if (plainString.charAt(i) == ' ') {
        encryptedString.append(" ");

      } else {
        char ch = (char) (((int) plainString.charAt(i) +
            offset - 97) % 26 + 97);
        encryptedString.append(ch);
      }
    }
    return encryptedString;
  }


  @Override
  public StringBuilder decrypt(String encryptedString, int offset, String key) {

    // StringBuilder to hold encrypted String
    StringBuilder decryptedString = new StringBuilder();

    // loop adding each ASCII character by the offset provided
    for (int i = 0; i < encryptedString.length(); i++) {
      char letter = encryptedString.charAt(i);

      int letterInt = letter - offset;

      if (letter <= 32) {
        decryptedString.append(" ");
      } else if (letterInt < 65 && letterInt > 32) {
        letterInt = 122 - (64 - letterInt);
        decryptedString.append((char) letterInt);
      } else if (letterInt < 97 && letterInt > 90) {
        letterInt = 90 - (96 - letterInt);
        decryptedString.append((char) letterInt);
      } else {
        decryptedString.append((char) letterInt);
      }
    }

    return decryptedString;

  }
}


class Vigenere extends Ciphers implements CipherControl {


  @Override
  public StringBuilder encrypt(String plainString, int offset, String key) {

    plainString = plainString.toLowerCase(); // convert to lowercase for compatibility
    key = key.toLowerCase(); // for compatibility
    StringBuilder newString = new StringBuilder();                //Final encrypted string
    int passChar;                         //Pass index value
    int passIndex = -1;                    //Pass array value
    int divisibility = 1;                 //How many times the alphabet repeats

    //log.setTextLog("Encryption length = " + key.length());

    for (int i = 0; i < plainString.length(); i++) {
      char tempChar = (char) (plainString.charAt(i) - 96);   //phrase index character
      char newChar;                       //Temp character

      if (passIndex >= key.length() - 1) //Does encrypted password index need to be looped?
      {
        passIndex = 0;
      } else {
        passIndex++;
      }

      System.out.println("Encryption index = " + passIndex);
      passChar =
          (int) key.charAt(passIndex) - 96;               //Encrypted char = Encrypted char at index

      if (plainString.charAt(i)
          != ' ') {                                            //If phrase character is not a space,
        System.out.println(
            (char) (tempChar + 96) + "," + (int) tempChar + " + " + (char) ((int) passChar + 96)
                + "," + passChar + " = " + (char) ((int) tempChar + passChar + 96) + "," + (
                (int) tempChar + passChar));
        if ((tempChar + passChar)
            > 25) {                              //If phrase char and encryption char are over alphabet length,
          divisibility = ((tempChar + passChar)
              / 26);                  //How many times does the alphabet repeats
          System.out.println(
              divisibility + " = divisible");              //How many times the alphabet is repeated
          newChar = (char) Math.abs((tempChar + passChar) - (25 * divisibility)
              + 96);     //(Phrase char + encryption char) - (alphabet * alphabet iterations)
        } else {                                                         //Else if phrase is within alphabet range,
          newChar = (char) (tempChar + passChar + 96);
        }
      } else {
        newChar = ' ';
      }

      newString.append(newChar);

      //System.out.println(newChar);
    }
    return newString;
  }


  @Override
  public StringBuilder decrypt(String encryptedString, int offset, String key) {

    encryptedString = encryptedString.toLowerCase(); // for compatibility
    key = key.toLowerCase(); // for compatibility
    StringBuilder newString = new StringBuilder();   //Final encrypted string
    int passChar;                         //Pass index value
    int passIndex = -1;                    //Pass array value
    int divisibility = 1;                 //How many times the alphabet repeats

    //System.out.println("Encryption length = " + phraseEncrypter.length());

    for (int i = 0; i < encryptedString.length(); i++) {
      char tempChar = (char) (encryptedString.charAt(i) - 96);   //phrase index character
      char newChar;                       //Temp character

      if (passIndex >= key.length() - 1) //Does encrypted password index need to be looped?
      {
        passIndex = 0;
      } else {
        passIndex++;
      }

      System.out.println("Encryption index = " + passIndex);
      passChar =
          (int) key.charAt(passIndex) - 96;               //Encrypted char = Encrypted char at index

      if (encryptedString.charAt(i)
          != ' ') {                                            //If phrase character is not a space,
        System.out.println(
            (char) (tempChar + 96) + "," + (int) tempChar + " - " + (char) ((int) passChar + 96)
                + "," + passChar + " = " + (char) ((int) tempChar - passChar + 96) + "," + (
                (int) tempChar - passChar));
        if ((tempChar - passChar) > 0 && (tempChar - passChar)
            < 25) {                              //If phrase char and encryption char are over alphabet length,
          newChar = (char) (tempChar - passChar + 96);

        } else {                                                         //Else if phrase is within alphabet range,
          if ((tempChar - passChar) < 0 && (tempChar - passChar) > -25) {
            newChar = (char) Math.abs(25 + (tempChar - passChar)
                + 96);     //(Phrase char + encryption char) - (alphabet * alphabet iterations)
          } else if ((tempChar - passChar) < -26) {
            divisibility = ((tempChar - passChar)
                / -26);                  //How many times does the alphabet repeats
            System.out.println(divisibility
                + " = divisible");              //How many times the alphabet is repeated
            newChar = (char) Math.abs((tempChar - passChar) - (25 * divisibility)
                + 96);     //(Phrase char + encryption char) - (alphabet * alphabet iterations)
          } else {
            newChar = '*';
          }
        }
      } else {
        newChar = ' ';
      }

      newString.append(newChar);

      //System.out.println(newChar);
    }
    return newString;
  }

}