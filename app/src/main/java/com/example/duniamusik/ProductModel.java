package com.example.duniamusik;

import java.io.Serializable;
public class ProductModel implements Serializable {
    String thumbnail, nama, harga, description;

    public String getThumbnail() {
        return thumbnail;
    }

    public String getNama() {
        return nama;
    }

    public String getHarga() {
        return harga;
    }

    public String getDescription() {
        return description;
    }
    public ProductModel(String thumbnail, String nama, String harga, String description) {
        this.thumbnail = thumbnail;
        this.nama = nama;
        this.harga = harga;
        this.description = description;
    }
}
