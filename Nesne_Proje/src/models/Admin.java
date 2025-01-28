package models;

import exceptions.GeçersizGirişException;
import java.util.List;


public class Admin extends User {

    public Admin(String kullanıcıAdı, String şifre) {
        super(kullanıcıAdı, şifre);
    }

    @Override
    public void girişYap() throws GeçersizGirişException {
        // Basit giriş kontrolü
        if (getKullanıcıAdı() == null || getKullanıcıAdı().isEmpty() ||
            getŞifre() == null || getŞifre().isEmpty()) {
            throw new GeçersizGirişException("Yönetici giriş bilgileri geçersiz.");
        }
        System.out.println("Yönetici olarak giriş yapıldı.");
    }

    // (Overloading)

    /**
     * Tek ürün ekleme 
     */
    public void ekle(Product product) {
        System.out.println(product.getIsim() + " ürünü eklendi.");
    }

    /**
     * Çoklu ürün ekleme 
     */
    public void ekle(Product... products) {
        for (Product product : products) {
            System.out.println(product.getIsim() + " ürünü eklendi.");
        }
    }

    /**
     * Parametrelerle ürün ekleme 
     */
    public void ekle(String id, String isim, double fiyat, int stok) {
        // Ürün tipi için kontrol
        Product yeniUrun;
        if (isim.toLowerCase().contains("kitap")) {
            yeniUrun = new Book(id, isim, fiyat, stok, "Bilinmeyen Yazar", 100);
        } else if (isim.toLowerCase().contains("dergi")) {
            yeniUrun = new Magazine(id, isim, fiyat, stok, 1);
        } else {
            // Diğer ürün tipleri için anonim sınıf kullanımı
            yeniUrun = new Product(id, isim, fiyat, stok) {
                @Override
                public void bilgiGöster() {
                    System.out.println("Ürün: " + getIsim() + ", Fiyat: " + getFiyat() + " TL, Stok: " + getStok());
                }
            };
        }
        System.out.println(yeniUrun.getIsim() + " ürünü parametrelerle eklendi.");
    }

    /**
     * Ürün silme 
     */
    public void sil(Product product) {
        System.out.println(product.getIsim() + " ürünü silindi.");
    }

    /**
     * Ürün güncelleme 
     */
    public void güncelle(Product product, double yeniFiyat) {
        product.setFiyat(yeniFiyat);
        System.out.println(product.getIsim() + " ürünü güncellendi. Yeni fiyat: " + yeniFiyat + " TL");
    }

    /**
     * Tüm ürünleri listeleme 
     */
    public void listeleTümÜrünler(List<Product> ürünler) {
        System.out.println("Tüm Ürünler:");
        for (Product product : ürünler) {
            product.bilgiGöster();
        }
    }

    /**
     * Satış raporu oluşturma 
     */
    public void raporOluştur() {
        System.out.println("Satış raporu oluşturuldu.");
    }

    /**
     * Kullanıcı ekleme 
     */
    public void kullanıcıEkle(User user) {
        System.out.println(user.getKullanıcıAdı() + " kullanıcısı eklendi.");
    }

    /**
     * Kullanıcı silme 
     */
    public void kullanıcıSil(User user) {
        System.out.println(user.getKullanıcıAdı() + " kullanıcısı silindi.");
    }
}
