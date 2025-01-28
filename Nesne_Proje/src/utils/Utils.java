// src/utils/Utils.java

package utils;

/**
 * Utility sınıfı, çeşitli yardımcı metodları içerir.
 */
public class Utils {
    private static int idCounter = 1; // Ürün ID sayacı
    private static int orderIdCounter = 1; // Sipariş ID sayacı

    /**
     * Basit ve artan sayısal ID oluşturur.
     *
     * @return Benzersiz bir sayısal ID.
     */
    public static String generateSimpleId() {
        return String.valueOf(idCounter++);
    }

    /**
     * Belirtilen prefix ile basit sayısal ID oluşturur.
     *
     * @param prefix ID için kullanılacak ön ek.
     * @return Benzersiz bir ID.
     */
    public static String generateIdWithPrefix(String prefix) {
        return prefix + "-" + generateSimpleId();
    }

    /**
     * Sipariş için basit sayısal ID oluşturur.
     *
     * @return Benzersiz bir sipariş ID'si.
     */
    public static String generateOrderId() {
        return String.valueOf(orderIdCounter++);
    }
}
