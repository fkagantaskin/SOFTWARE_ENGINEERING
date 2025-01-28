package models;

import java.util.List;

/**
 * Raporlama sınıfı, satış raporları oluşturmak için kullanılır.
 */
public class Reporting {
    /**
     * Genel satış raporu oluşturma metodu.
     */
    public void satışRaporu(List<Order> siparişler) {
        System.out.println("Satış Raporu:");
        for (Order order : siparişler) {
            order.siparişBilgisiGöster();
            System.out.println("-----------------------");
        }
    }

    // Fazladan yükleme (Overloading) örnekleri
    public void satışRaporu(List<Order> siparişler, String tarih) {
        System.out.println("Satış Raporu - " + tarih + ":");
        for (Order order : siparişler) {
            order.siparişBilgisiGöster();
            System.out.println("-----------------------");
        }
    }

    public void satışRaporu(List<Order> siparişler, String tarih, String bölge) {
        System.out.println("Satış Raporu - " + tarih + " - " + bölge + ":");
        for (Order order : siparişler) {
            order.siparişBilgisiGöster();
            System.out.println("-----------------------");
        }
    }
}
