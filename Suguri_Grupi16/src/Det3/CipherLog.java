package Det3;

public class CipherLog {

  protected String message = "";
  protected String cipherType = "";

  public CipherLog(String message, String cipherType) {
    this.message = message;
    this.cipherType = cipherType;
  }


  @Override
  public String toString() {
    String record =
        "Log --- " + "Message Ciphered: " + getMessage() + " --- " + " Cipher Type: "
            + getCipherType();
    return record;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public String getCipherType() {
    return cipherType;
  }

 
  public void setCipherType(String cipherType) {
    this.cipherType = cipherType;
  }
}