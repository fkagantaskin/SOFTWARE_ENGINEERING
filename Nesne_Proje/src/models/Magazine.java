// src/models/Magazine.java

package models;

/**
 * Dergi sınıfı, Ürün sınıfından kalıtım alır.
 */
public class Magazine extends Product {
    private int sayi;

    public Magazine(String id, String isim, double fiyat, int stok, int sayi) {
        super(id, isim, fiyat, stok);
        this.sayi = sayi;
    }

    @Override
    public void bilgiGöster() {
        System.out.println("ID: " + getId() +
                           ", Dergi: " + getIsim() +
                           ", Sayı: " + sayi +
                           ", Fiyat: " + getFiyat() + " TL" +
                           ", Stok: " + getStok());
    }

    // Getter ve Setter metodları
    public int getSayi() {
        return sayi;
    }

    public void setSayi(int sayi) {
        this.sayi = sayi;
    }

    // Fazladan yükleme (Overloading) örneği
    public void bilgiGöster(boolean detayli) {
        if (detayli) {
            bilgiGöster();
            System.out.println("Detaylı bilgi: İçerik listesi vs.");
        } else {
            bilgiGöster();
        }
    }
}
