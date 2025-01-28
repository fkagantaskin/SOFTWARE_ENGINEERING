# Online Kitap Satış Sistemi

Bu proje, Nesne Yönelimli Programlama (OOP) prensiplerini kullanarak geliştirilmiş basit bir online kitap satış sistemidir. Java dilinde yazılmıştır ve aşağıdaki özellikleri içerir:

## Özellikler

- **Kalıtım (Inheritance):** `Product` sınıfından `Book` ve `Magazine` sınıfları türetilmiştir.
- **Soyut Sınıf ve Arayüz (Abstract Class & Interface):** `Product` soyut sınıfı ve `Downloadable` arayüzü kullanılmıştır.
- **Fazladan Yükleme (Overloading):** `Admin`, `Customer`, `Book`, `Magazine`, `Payment`, ve `Reporting` sınıflarında metod aşırı yüklemeleri bulunmaktadır.
- **Yöntem (Methods):** Projede toplamda 20'den fazla farklı metot bulunmaktadır.
- **Yapılandırıcı (Constructors):** Her sınıf için uygun yapıcı metodlar mevcuttur.
- **Exception (Hatalar):** `GeçersizGirişException` ve `StokYetersizException` adlı özel istisnalar kullanılmıştır.
- **Erişim Belirleme (Access Modifiers):** Tüm sınıflarda `private`, `protected`, ve `public` erişim belirleyicileri kullanılmıştır.
- **İçiçe Class (Inner Class):** `Customer` sınıfı içinde `Cart` ve `CartItem` iç sınıfları bulunmaktadır.

## Paket Yapısı

- `exceptions`: Özel istisna sınıfları.
- `interfaces`: Arayüzler.
- `models`: Uygulama modelleri (sınıflar).
- `utils`: Yardımcı sınıflar ve metodlar.
- `main`: Uygulamanın ana sınıfı.

## Kurulum ve Çalıştırma

1. **Java Kurulumu:** Projeyi çalıştırmak için Java JDK'nın bilgisayarınıza kurulu olması gerekmektedir. [Java İndir](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

2. **Projeyi İndirme:**
   - Proje dosyalarını bilgisayarınıza indirin veya klonlayın.

3. **Derleme:**
   - Terminal veya komut istemcisini açın.
   - Proje dizinine gidin.
   - Aşağıdaki komutları kullanarak projeyi derleyin:

     ```bash
     javac -d bin src/exceptions/*.java src/interfaces/*.java src/models/*.java src/utils/*.java src/main/*.java
     ```

4. **Çalıştırma:**
   - Derlenen sınıfları çalıştırmak için aşağıdaki komutu kullanın:

     ```bash
     java -cp bin main.Main
     ```

## Kullanım

1. **Giriş Yapma:**
   - Ana menüden `Müşteri Girişi` veya `Yönetici Girişi` seçeneklerinden birini seçebilirsiniz.
   - Başlangıçta bir yönetici (`admin/admin123`) ve bir müşteri (`customer1/cust123`) hesabı mevcuttur.

2. **Müşteri İşlemleri:**
   - Ürünleri listeleyebilir, sepetinize ürün ekleyebilir, iade işlemi yapabilir ve sipariş verebilirsiniz.

3. **Yönetici İşlemleri:**
   - Ürün ekleme, silme, güncelleme, kullanıcı ekleme/silme ve satış raporu oluşturma işlemlerini gerçekleştirebilirsiniz.

## Notlar

- Bu proje, OOP konseptlerini öğrenmek ve uygulamak için basit bir örnek teşkil etmektedir.

## İletişim

Herhangi bir sorunuz veya öneriniz varsa, lütfen [f.kagan.taskin@gmail.com](mailto:f.kagan.taskin@gmail.com) adresinden bana ulaşın.

---

