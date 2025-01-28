package models;

import java.util.List;

/**
 * Sipariş sınıfı, müşterilerin verdikleri siparişleri temsil eder.
 */
public class Order {
    private String orderId;
    private Customer müşteri;
    private List<Product> ürünler;
    private double toplamTutar;

    public Order(String orderId, Customer müşteri, List<Product> ürünler, double toplamTutar) {
        this.orderId = orderId;
        this.müşteri = müşteri;
        this.ürünler = ürünler;
        this.toplamTutar = toplamTutar;
    }

    /**
     * Sipariş bilgilerini gösterme metodu.
     */
    public void siparişBilgisiGöster() {
        System.out.println("Sipariş ID: " + orderId);
        System.out.println("Müşteri: " + müşteri.getAd() + " " + müşteri.getSoyad());
        System.out.println("Ürünler:");
        for (Product product : ürünler) {
            System.out.println("- " + product.getIsim() + " : " + product.getFiyat() + " TL");
        }
        System.out.println("Toplam Tutar: " + toplamTutar + " TL");
    }

    // Getter ve Setter metodları
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getMüşteri() {
        return müşteri;
    }

    public void setMüşteri(Customer müşteri) {
        this.müşteri = müşteri;
    }

    public List<Product> getÜrünler() {
        return ürünler;
    }

    public void setÜrünler(List<Product> ürünler) {
        this.ürünler = ürünler;
    }

    public double getToplamTutar() {
        return toplamTutar;
    }

    public void setToplamTutar(double toplamTutar) {
        this.toplamTutar = toplamTutar;
    }
}
