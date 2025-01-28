
package models;

import exceptions.GeçersizGirişException;
import exceptions.StokYetersizException;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String ad;
    private String soyad;
    private final Cart sepet; 

    public Customer(String kullanıcıAdı, String şifre, String ad, String soyad) {
        super(kullanıcıAdı, şifre);
        this.ad = ad;
        this.soyad = soyad;
        this.sepet = new Cart();
    }

    @Override
    public void girişYap() throws GeçersizGirişException {
        // Giriş kontrolü
        if (getKullanıcıAdı() == null || getKullanıcıAdı().isEmpty() ||
            getŞifre() == null || getŞifre().isEmpty()) {
            throw new GeçersizGirişException("Kullanıcı adı veya şifre boş olamaz.");
        }
        System.out.println(ad + " " + soyad + " olarak giriş yapıldı.");
    }

    // (Overloading) 
    public void girişYap(String kullanıcıAdı) throws GeçersizGirişException {
        if (kullanıcıAdı == null || kullanıcıAdı.isEmpty()) {
            throw new GeçersizGirişException("Kullanıcı adı boş olamaz.");
        }
        setKullanıcıAdı(kullanıcıAdı);
        System.out.println(ad + " " + soyad + " olarak kullanıcı adı ile giriş yapıldı.");
    }

    public void girişYap(String kullanıcıAdı, String şifre, boolean hatırlasın) throws GeçersizGirişException {
        if (kullanıcıAdı == null || kullanıcıAdı.isEmpty() ||
            şifre == null || şifre.isEmpty()) {
            throw new GeçersizGirişException("Kullanıcı adı veya şifre boş olamaz.");
        }
        setKullanıcıAdı(kullanıcıAdı);
        setŞifre(şifre);
        System.out.println(ad + " " + soyad + " olarak giriş yapıldı. Hatırlasın seçeneği: " + hatırlasın);
    }

    /**
     * Tek bir ürün satın alma
     */
    public void satınAl(Product product) throws StokYetersizException {
        if (product.getStok() <= 0) {
            throw new StokYetersizException("Stokta yeterli ürün yok: " + product.getIsim());
        }
        sepet.ekle(product, 1);
        product.setStok(product.getStok() - 1);
        System.out.println(product.getIsim() + " sepete eklendi.");
    }

    /**
     * Belirli miktarda ürün satın alma
     */
    public void satınAl(Product product, int miktar) throws StokYetersizException {
        if (product.getStok() < miktar) {
            throw new StokYetersizException("Stokta yeterli ürün yok: " + product.getIsim());
        }
        sepet.ekle(product, miktar);
        product.setStok(product.getStok() - miktar);
        System.out.println(miktar + " adet " + product.getIsim() + " sepete eklendi.");
    }

    /**
     * Çoklu ürün satın alma
     */
    public void satınAl(List<Product> products) throws StokYetersizException {
        for (Product product : products) {
            if (product.getStok() <= 0) {
                throw new StokYetersizException("Stokta yeterli ürün yok: " + product.getIsim());
            }
            sepet.ekle(product, 1);
            product.setStok(product.getStok() - 1);
            System.out.println(product.getIsim() + " sepete eklendi.");
        }
    }

    /**
     * Sepeti gösterme
     */
    public void sepetiGöster() {
        sepet.listele();
    }

    /**
     * Ürün iade etme 
     */
    public void iadeEt(Product product) {
        sepet.sil(product);
        product.setStok(product.getStok() + 1);
        System.out.println(product.getIsim() + " iade edildi.");
    }

    // Getter ve Setter metodları
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public Cart getSepet() {
        return sepet;
    }

    /**
     * İç içe sınıf: Sepet
     */
    public class Cart {
        private final List<CartItem> items; 

        public Cart() {
            items = new ArrayList<>();
        }

        /**
         * Sepete ürün ekleme metodu.
         */
        public void ekle(Product product, int miktar) {
            // Sepette ürün zaten varsa miktarı artır
            for (CartItem item : items) {
                if (item.getProduct().getId().equals(product.getId())) {
                    item.setMiktar(item.getMiktar() + miktar);
                    return;
                }
            }
            // Yoksa yeni CartItem ekle
            items.add(new CartItem(product, miktar));
        }

        /**
         * Sepeti listeleme metodu.
         */
        public void listele() {
            System.out.println("Sepetinizdeki Ürünler:");
            for (CartItem item : items) {
                System.out.println("- " + item.getProduct().getIsim() + " x " + item.getMiktar());
            }
        }

        /**
         * Sepetten ürün silme metodu.
         */
        public void sil(Product product) {
            items.removeIf(item -> item.getProduct().getId().equals(product.getId()));
        }

        /**
         * Sepeti temizleme metodu.
         */
        public void clear() {
            items.clear();
            System.out.println("Sepetiniz temizlendi.");
        }

        /**
         * Toplam tutarı hesaplama metodu.
         */
        public double toplamTutar() {
            double toplam = 0;
            for (CartItem item : items) {
                toplam += item.getProduct().getFiyat() * item.getMiktar();
            }
            return toplam;
        }

        /**
         * Sepetteki öğeleri döndüren metot.
         */
        public List<CartItem> getItems() {
            return items;
        }

        /**
         * İç içe sınıf: CartItem
         */
        public class CartItem {
            private final Product product; 
            private int miktar;

            public CartItem(Product product, int miktar) {
                this.product = product;
                this.miktar = miktar;
            }

            public Product getProduct() {
                return product;
            }

            public int getMiktar() {
                return miktar;
            }

            public void setMiktar(int miktar) {
                this.miktar = miktar;
            }
        }
    }
}
