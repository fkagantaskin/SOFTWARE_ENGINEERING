package models;

/**
 * İndirim sınıfı, ürünlere indirim uygulamak için kullanılır.
 */
public class Discount {
    private double oran; // Örneğin: 0.10 for 10%

    public Discount(double oran) {
        this.oran = oran;
    }

    /**
     * İndirimi hesaplama metodu.
     */
    public double indirimiHesapla(double fiyat) {
        return fiyat * (1 - oran);
    }

    // Getter ve Setter metodları
    public double getOran() {
        return oran;
    }

    public void setOran(double oran) {
        this.oran = oran;
    }

    // Fazladan yükleme (Overloading) örnekleri
    public double indirimiHesapla(double fiyat, boolean uygulansın) {
        if (uygulansın) {
            return indirimiHesapla(fiyat);
        } else {
            return fiyat;
        }
    }

    public double indirimiHesapla(double fiyat, int ekstraIndirim) {
        return fiyat * (1 - oran - (ekstraIndirim / 100.0));
    }
}
