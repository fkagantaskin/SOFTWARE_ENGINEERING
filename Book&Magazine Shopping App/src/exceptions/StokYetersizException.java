package exceptions;

/**
 * Özel bir istisna sınıfı. Stokta yeterli ürün bulunmadığında kullanılır.
 */
public class StokYetersizException extends Exception {
    public StokYetersizException(String mesaj) {
        super(mesaj);
    }
}
