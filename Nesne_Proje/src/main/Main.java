// src/main/Main.java

package main;

import exceptions.GeçersizGirişException;
import exceptions.StokYetersizException;
import interfaces.Downloadable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.*;
import utils.Utils;

/**
 * Ana sınıf, uygulamanın giriş noktasıdır.
 */
public class Main {
    private static final List<Product> ürünler = new ArrayList<>();
    private static final List<Order> siparişler = new ArrayList<>();
    private static final List<User> kullanıcılar = new ArrayList<>();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();
        
        // Geçici olarak indir() metodunu test etme
    Book testKitap = new Book(Utils.generateSimpleId(), "Örnek Kitap", 45.0, 20, "Yazar Adı", 350);
    testKitap.indir();

    
        // Ana döngü
        while (true) {
            System.out.println("\n=== Online Kitap Satış Sistemi ===");
            System.out.println("1. Kullanıcı Girişi");
            System.out.println("2. Admin Girişi");
            System.out.println("3. Çıkış");
            System.out.print("Seçiminiz: ");
            int secim;
            try {
                secim = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş! Lütfen bir sayı girin.");
                continue;
            }

            switch (secim) {
                case 1 -> kullanıcıGirişi();
                case 2 -> adminGirişi();
                case 3 -> {
                    System.out.println("Sistemden çıkılıyor...");
                    System.exit(0);
                }
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }

    /**
     * Örnek verileri başlatma metodu.
     */
    private static void initializeData() {
        // Ürünler ekle
        ürünler.add(new Book(Utils.generateSimpleId(), "Java Programlama", 50.0, 10, "Ahmet Yılmaz", 500));
        ürünler.add(new Book(Utils.generateSimpleId(), "Nesne Yönelimli Programlama", 60.0, 5, "Mehmet Kaya", 600));
        ürünler.add(new Magazine(Utils.generateSimpleId(), "Teknoloji Dergisi", 20.0, 15, 1));

        // Kullanıcılar ekle
        kullanıcılar.add(new Admin("admin", "admin123"));
        kullanıcılar.add(new Customer("customer1", "cust123", "Ali", "Veli"));
    }

    /**
     * Kullanıcı girişi ve menüsü.
     */
    private static void kullanıcıGirişi() {
        System.out.print("Kullanıcı Adı: ");
        String kullanıcıAdı = scanner.nextLine();
        System.out.print("Şifre: ");
        String şifre = scanner.nextLine();

        // Müşteriyi bul
        Customer müşteri = null;
        for (User user : kullanıcılar) {
            if (user instanceof Customer customer) { 
                if (user.getKullanıcıAdı().equals(kullanıcıAdı) && user.getŞifre().equals(şifre)) {
                    müşteri = customer;
                    break;
                }
            }
        }

        if (müşteri == null) {
            System.out.println("Müşteri bulunamadı veya şifre yanlış.");
            return;
        }

        try {
            müşteri.girişYap();
        } catch (GeçersizGirişException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Müşteri menüsü
        while (true) {
            System.out.println("\n--- Müşteri Menüsü ---");
            System.out.println("1. Ürünleri Listele");
            System.out.println("2. Sepeti Göster");
            System.out.println("3. Ürün Satın Al");
            System.out.println("4. İade Et");
            System.out.println("5. Sipariş Ver");
            System.out.println("6. İndir"); // yeni seçenek ekleniyor
            System.out.println("7. Çıkış Yap");


            System.out.print("Seçiminiz: ");
            int secim;
            try {
                secim = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş! Lütfen bir sayı girin.");
                continue;
            }

            switch (secim) {
                case 1 -> ürünleriListele();
                case 2 -> sepetiGöster(müşteri);
                case 3 -> ürünSatınAl(müşteri);
                case 4 -> ürünİadeEt(müşteri);
                case 5 -> siparişVer(müşteri);
                case 6 -> indirimiUygula(müşteri);
                case 7 -> {
                    System.out.println("Müşteri olarak çıkış yapıldı.");
                    return;
                }
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }

    /**
     * Admin girişi ve menüsü.
     */
    private static void adminGirişi() {
        System.out.print("Admin Kullanıcı Adı: ");
        String kullanıcıAdı = scanner.nextLine();
        System.out.print("Admin Şifre: ");
        String şifre = scanner.nextLine();

        // Admini bul
        Admin admin = null;
        for (User user : kullanıcılar) {
            if (user instanceof Admin a) { 
                if (user.getKullanıcıAdı().equals(kullanıcıAdı) && user.getŞifre().equals(şifre)) {
                    admin = a;
                    break;
                }
            }
        }

        if (admin == null) {
            System.out.println("Admin bulunamadı veya şifre yanlış.");
            return;
        }

        try {
            admin.girişYap();
        } catch (GeçersizGirişException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Admin menüsü
        while (true) {
            System.out.println("\n--- Admin Menüsü ---");
            System.out.println("1. Ürün Ekle");
            System.out.println("2. Ürün Sil");
            System.out.println("3. Ürün Güncelle");
            System.out.println("4. Tüm Ürünleri Listele");
            System.out.println("5. Kullanıcı Ekle");
            System.out.println("6. Kullanıcı Sil");
            System.out.println("7. Satış Raporu Oluştur");
            System.out.println("8. Çıkış Yap");
            System.out.print("Seçiminiz: ");
            int secim;
            try {
                secim = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş! Lütfen bir sayı girin.");
                continue;
            }

            switch (secim) {
                case 1 -> ürünEkle(admin);
                case 2 -> ürünSil(admin);
                case 3 -> ürünGüncelle(admin);
                case 4 -> listeleTümÜrünler(admin);
                case 5 -> kullanıcıEkle(admin);
                case 6 -> kullanıcıSil(admin);
                case 7 -> satışRaporuOluştur();
                case 8 -> {
                    System.out.println("Admin olarak çıkış yapıldı.");
                    return;
                }
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }

    /**
     * Ürünleri listeleme metodu.
     */
    private static void ürünleriListele() {
        System.out.println("\n--- Ürünler ---");
        for (Product product : ürünler) {
            product.bilgiGöster();
        }
    }

    /**
     * Sepeti gösterme metodu.
     */
    private static void sepetiGöster(Customer müşteri) {
        müşteri.sepetiGöster();
        System.out.println("Toplam Tutar: " + müşteri.getSepet().toplamTutar() + " TL");
    }

    /**
     * Ürün satın alma metodu.
     */
    private static void ürünSatınAl(Customer müşteri) {
        System.out.print("Satın almak istediğiniz ürünün ID'sini girin: ");
        String id = scanner.nextLine();
        Product seçiliÜrün = null;
        for (Product product : ürünler) {
            if (product.getId().equals(id)) {
                seçiliÜrün = product;
                break;
            }
        }

        if (seçiliÜrün == null) {
            System.out.println("Ürün bulunamadı.");
            return;
        }

        System.out.print("Miktar: ");
        int miktar;
        try {
            miktar = Integer.parseInt(scanner.nextLine());
            if (miktar <= 0) {
                System.out.println("Miktar sıfır veya negatif olamaz.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz miktar girdiniz.");
            return;
        }

        try {
            müşteri.satınAl(seçiliÜrün, miktar);
        } catch (StokYetersizException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Ürün iade etme metodu.
     */
    private static void ürünİadeEt(Customer müşteri) {
        System.out.print("İade etmek istediğiniz ürünün ID'sini girin: ");
        String id = scanner.nextLine();
        Product seçiliÜrün = null;
        for (Product product : ürünler) {
            if (product.getId().equals(id)) {
                seçiliÜrün = product;
                break;
            }
        }

        if (seçiliÜrün == null) {
            System.out.println("Ürün bulunamadı.");
            return;
        }

        müşteri.iadeEt(seçiliÜrün);
    }

    /**
     * Sipariş verme metodu.
     */
    private static void siparişVer(Customer müşteri) {
        double toplam = müşteri.getSepet().toplamTutar();
        if (toplam <= 0) {
            System.out.println("Sepetinizde ürün yok.");
            return;
        }

        String siparişId = Utils.generateOrderId();
        List<Product> siparişÜrünleri = new ArrayList<>();
        for (Customer.Cart.CartItem item : müşteri.getSepet().getItems()) {
            siparişÜrünleri.add(item.getProduct());
        }
        Order sipariş = new Order(siparişId, müşteri, siparişÜrünleri, toplam);
        siparişler.add(sipariş);
        System.out.println("Sipariş verildi.");
        sipariş.siparişBilgisiGöster();

        // Sepeti temizle
        müşteri.getSepet().clear();
    }

    /**
     * Yeni ürün ekleme metodu.
     */
    private static void ürünEkle(Admin admin) {
        System.out.print("Ürün tipi (book/magazine): ");
        String tip = scanner.nextLine().toLowerCase();

        System.out.print("Ürün ismi: ");
        String isim = scanner.nextLine();

        System.out.print("Fiyat: ");
        double fiyat;
        try {
            fiyat = Double.parseDouble(scanner.nextLine());
            if (fiyat < 0) {
                System.out.println("Fiyat negatif olamaz.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz fiyat girdiniz.");
            return;
        }

        System.out.print("Stok: ");
        int stok;
        try {
            stok = Integer.parseInt(scanner.nextLine());
            if (stok < 0) {
                System.out.println("Stok negatif olamaz.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz stok girdiniz.");
            return;
        }

        if (tip.equals("book")) {
            System.out.print("Yazar: ");
            String yazar = scanner.nextLine();

            System.out.print("Sayfa sayısı: ");
            int sayfaSayisi;
            try {
                sayfaSayisi = Integer.parseInt(scanner.nextLine());
                if (sayfaSayisi <= 0) {
                    System.out.println("Sayfa sayısı sıfır veya negatif olamaz.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz sayfa sayısı girdiniz.");
                return;
            }

            Book yeniKitap = new Book(Utils.generateSimpleId(), isim, fiyat, stok, yazar, sayfaSayisi);
            ürünler.add(yeniKitap);
            System.out.println(yeniKitap.getIsim() + " adlı kitap eklendi. ID: " + yeniKitap.getId());
        } else if (tip.equals("magazine")) {
            System.out.print("Sayı: ");
            int sayi;
            try {
                sayi = Integer.parseInt(scanner.nextLine());
                if (sayi <= 0) {
                    System.out.println("Sayı sıfır veya negatif olamaz.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz sayı girdiniz.");
                return;
            }

            Magazine yeniDergi = new Magazine(Utils.generateSimpleId(), isim, fiyat, stok, sayi);
            ürünler.add(yeniDergi);
            System.out.println(yeniDergi.getIsim() + " adlı dergi eklendi. ID: " + yeniDergi.getId());
        } else {
            System.out.println("Geçersiz ürün tipi!");
        }
    }

    /**
     * Ürün silme metodu.
     */
    private static void ürünSil(Admin admin) {
        System.out.print("Silmek istediğiniz ürünün ID'sini girin: ");
        String id = scanner.nextLine();
        Product silinecekÜrün = null;

        for (Product product : ürünler) {
            if (product.getId().equals(id)) {
                silinecekÜrün = product;
                break;
            }
        }

        if (silinecekÜrün != null) {
            ürünler.remove(silinecekÜrün);
            System.out.println(silinecekÜrün.getIsim() + " adlı ürün silindi.");
        } else {
            System.out.println("Ürün bulunamadı.");
        }
    }

    /**
     * Ürün güncelleme metodu.
     */
    private static void ürünGüncelle(Admin admin) {
        System.out.print("Güncellemek istediğiniz ürünün ID'sini girin: ");
        String id = scanner.nextLine();
        Product güncellenecekÜrün = null;

        for (Product product : ürünler) {
            if (product.getId().equals(id)) {
                güncellenecekÜrün = product;
                break;
            }
        }

        if (güncellenecekÜrün == null) {
            System.out.println("Ürün bulunamadı.");
            return;
        }

        System.out.print("Yeni fiyat: ");
        double yeniFiyat;
        try {
            yeniFiyat = Double.parseDouble(scanner.nextLine());
            if (yeniFiyat < 0) {
                System.out.println("Fiyat negatif olamaz.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz fiyat girdiniz.");
            return;
        }

        System.out.print("Yeni stok miktarı: ");
        int yeniStok;
        try {
            yeniStok = Integer.parseInt(scanner.nextLine());
            if (yeniStok < 0) {
                System.out.println("Stok negatif olamaz.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz stok girdiniz.");
            return;
        }

        güncellenecekÜrün.setFiyat(yeniFiyat);
        güncellenecekÜrün.setStok(yeniStok);
        System.out.println("Ürün güncellendi.");
    }

    /**
     * Tüm ürünleri listeleme metodu.
     */
    private static void listeleTümÜrünler(Admin admin) {
        System.out.println("\n--- Tüm Ürünler ---");
        for (Product product : ürünler) {
            product.bilgiGöster();
        }
    }

    /**
     * Kullanıcı ekleme metodu.
     */
    private static void kullanıcıEkle(Admin admin) {
        System.out.print("Kullanıcı tipi (customer/admin): ");
        String tip = scanner.nextLine().toLowerCase();

        System.out.print("Kullanıcı Adı: ");
        String kullanıcıAdı = scanner.nextLine();

        System.out.print("Şifre: ");
        String şifre = scanner.nextLine();

        if (tip.equals("customer")) {
            System.out.print("Ad: ");
            String ad = scanner.nextLine();

            System.out.print("Soyad: ");
            String soyad = scanner.nextLine();

            Customer yeniMüşteri = new Customer(kullanıcıAdı, şifre, ad, soyad);
            kullanıcılar.add(yeniMüşteri);
            System.out.println("Yeni müşteri eklendi: " + ad + " " + soyad + " (Kullanıcı Adı: " + kullanıcıAdı + ")");
        } else if (tip.equals("admin")) {
            Admin yeniAdmin = new Admin(kullanıcıAdı, şifre);
            kullanıcılar.add(yeniAdmin);
            System.out.println("Yeni admin eklendi: " + kullanıcıAdı);
        } else {
            System.out.println("Geçersiz kullanıcı tipi!");
        }
    }

    /**
     * Kullanıcı silme metodu.
     */
    private static void kullanıcıSil(Admin admin) {
        System.out.print("Silmek istediğiniz kullanıcının kullanıcı adını girin: ");
        String kullanıcıAdı = scanner.nextLine();
        User silinecekKullanıcı = null;

        for (User user : kullanıcılar) {
            if (user.getKullanıcıAdı().equals(kullanıcıAdı)) {
                silinecekKullanıcı = user;
                break;
            }
        }

        if (silinecekKullanıcı != null) {
            kullanıcılar.remove(silinecekKullanıcı);
            System.out.println(kullanıcıAdı + " adlı kullanıcı silindi.");
        } else {
            System.out.println("Kullanıcı bulunamadı.");
        }
    }

    /**
     * Satış raporu oluşturma metodu.
     */
    private static void satışRaporuOluştur() {
        System.out.println("\n--- Satış Raporu ---");
        if (siparişler.isEmpty()) {
            System.out.println("Hiç sipariş yok.");
            return;
        }

        for (Order sipariş : siparişler) {
            sipariş.siparişBilgisiGöster();
            System.out.println("-------------------------------");
        }
    }
    // Diğer metodlarınızın hemen ardından
private static void indirimiUygula(Customer müşteri) {
    System.out.print("İndirmek istediğiniz ürünün ID'sini girin: ");
    String id = scanner.nextLine();
    Product seçiliÜrün = null;
    for (Product product : ürünler) {
        if (product.getId().equals(id)) {
            seçiliÜrün = product;
            break;
        }
    }

    if (seçiliÜrün == null) {
        System.out.println("Ürün bulunamadı.");
        return;
    }

    // Eğer ürün indirilebilir ise indir metodunu çağırıyoruz
    if (seçiliÜrün instanceof Downloadable) {
        ((Downloadable) seçiliÜrün).indir();
    } else {
        System.out.println("Bu ürün indirilebilir değil.");
    }
}

}
