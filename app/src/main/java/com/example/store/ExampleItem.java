package com.example.store;

public class ExampleItem {
    private String address;
    private Double longitude;
    private Double latitude;
    private String _id;

    public ExampleItem(String address, Double longitude, Double latitude) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String get_id() {
        return _id;
    }
}
