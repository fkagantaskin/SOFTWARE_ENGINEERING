
package models;

import interfaces.Downloadable;

/**
 * Kitap sınıfı, Ürün sınıfından kalıtım alır ve Downloadable arayüzünü uygular.
 */
public class Book extends Product implements Downloadable {
    private String yazar;
    private int sayfaSayisi;

    public Book(String id, String isim, double fiyat, int stok, String yazar, int sayfaSayisi) {
        super(id, isim, fiyat, stok);
        this.yazar = yazar;
        this.sayfaSayisi = sayfaSayisi;
    }

    @Override
    public void bilgiGöster() {
        System.out.println("ID: " + getId() +
                           ", Kitap: " + getIsim() +
                           ", Yazar: " + yazar +
                           ", Sayfa Sayısı: " + sayfaSayisi +
                           ", Fiyat: " + getFiyat() + " TL" +
                           ", Stok: " + getStok());
    }

    @Override
    public void indir() {
        System.out.println(getIsim() + " indiriliyor...");
    }

    // Getter ve Setter 
    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public int getSayfaSayisi() {
        return sayfaSayisi;
    }

    public void setSayfaSayisi(int sayfaSayisi) {
        this.sayfaSayisi = sayfaSayisi;
    }

    // (Overloading) 
    public void bilgiGöster(boolean detayli) {
        if (detayli) {
            bilgiGöster();
            System.out.println("Detaylı bilgi: Yazarın biyografisi vs.");
        } else {
            bilgiGöster();
        }
    }

    public void bilgiGöster(String format) {
        if (format.equalsIgnoreCase("short")) {
            System.out.println("ID: " + getId() + ", Kitap: " + getIsim() + ", Yazar: " + yazar);
        } else {
            bilgiGöster();
        }
    }
}
