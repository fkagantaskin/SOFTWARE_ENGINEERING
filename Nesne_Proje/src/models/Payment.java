package models;

/**
 * Ödeme sınıfı, siparişler için ödeme bilgilerini temsil eder.
 */
public class Payment {
    private String ödemeId;
    private double miktar;
    private String yöntem; // Örneğin: "Kredi Kartı", "Paypal"

    public Payment(String ödemeId, double miktar, String yöntem) {
        this.ödemeId = ödemeId;
        this.miktar = miktar;
        this.yöntem = yöntem;
    }

    /**
     * Ödeme bilgilerini gösterme metodu.
     */
    public void ödemeBilgisiGöster() {
        System.out.println("Ödeme ID: " + ödemeId);
        System.out.println("Miktar: " + miktar + " TL");
        System.out.println("Yöntem: " + yöntem);
    }

    // Getter ve Setter metodları
    public String getÖdemeId() {
        return ödemeId;
    }

    public void setÖdemeId(String ödemeId) {
        this.ödemeId = ödemeId;
    }

    public double getMiktar() {
        return miktar;
    }

    public void setMiktar(double miktar) {
        this.miktar = miktar;
    }

    public String getYöntem() {
        return yöntem;
    }

    public void setYöntem(String yöntem) {
        this.yöntem = yöntem;
    }

    // Fazladan yükleme (Overloading) örnekleri
    public void ödemeBilgisiGöster(String format) {
        if (format.equalsIgnoreCase("short")) {
            System.out.println("Ödeme ID: " + ödemeId + ", Miktar: " + miktar + " TL");
        } else {
            ödemeBilgisiGöster();
        }
    }

    public void ödemeBilgisiGöster(boolean detayli) {
        if (detayli) {
            ödemeBilgisiGöster();
            System.out.println("Detaylı ödeme bilgisi.");
        } else {
            ödemeBilgisiGöster("short");
        }
    }
}
