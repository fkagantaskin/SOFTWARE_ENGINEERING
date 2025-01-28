package models;

/**
 * Ürün sınıfı, tüm ürünlerin temelini oluşturur. Soyut bir sınıftır.
 */
public abstract class Product {
    private String id;
    private String isim;
    private double fiyat;
    private int stok;

    public Product(String id, String isim, double fiyat, int stok) {
        this.id = id;
        this.isim = isim;
        this.fiyat = fiyat;
        this.stok = stok;
    }

    /**
     * Ürün bilgilerini gösteren soyut metot.
     */
    public abstract void bilgiGöster();

    // Getter ve Setter metodları
    public String getId() {
        return id;
    }

    public String getIsim() {
        return isim;
    }

    public double getFiyat() {
        return fiyat;
    }

    public int getStok() {
        return stok;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
