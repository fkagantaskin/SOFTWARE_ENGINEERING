package models;

import exceptions.GeçersizGirişException;

/**
 * Kullanıcı sınıfı, tüm kullanıcıların temelini oluşturur. Soyut bir sınıftır.
 */
public abstract class User {
    private String kullanıcıAdı;
    private String şifre;

    public User(String kullanıcıAdı, String şifre) {
        this.kullanıcıAdı = kullanıcıAdı;
        this.şifre = şifre;
    }

    /**
     * Kullanıcı giriş metodu. Soyut olduğu için alt sınıflarda uygulanmalıdır.
     */
    public abstract void girişYap() throws GeçersizGirişException;

    // Getter ve Setter metodları
    public String getKullanıcıAdı() {
        return kullanıcıAdı;
    }

    public void setKullanıcıAdı(String kullanıcıAdı) {
        this.kullanıcıAdı = kullanıcıAdı;
    }

    public String getŞifre() {
        return şifre;
    }

    public void setŞifre(String şifre) {
        this.şifre = şifre;
    }
}
