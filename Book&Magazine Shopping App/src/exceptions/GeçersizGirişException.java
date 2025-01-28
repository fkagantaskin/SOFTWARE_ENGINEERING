package exceptions;

/**
 * Özel bir istisna sınıfı. Geçersiz giriş durumlarında kullanılır.
 */
public class GeçersizGirişException extends Exception {
    public GeçersizGirişException(String mesaj) {
        super(mesaj);
    }
}
