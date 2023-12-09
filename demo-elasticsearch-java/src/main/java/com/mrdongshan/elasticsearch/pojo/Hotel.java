package com.mrdongshan.elasticsearch.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Hotel {
    private Long id;
    private String name;
    private String address;
    private Integer price;
    private Integer score;
    private String brand;
    private String city;
    private String starName;
    private String business;
    private String latitude;
    private String longitude;
    private String pic;

    public static Hotel buildDefaultData() {
        Hotel hotel = new Hotel();
        hotel.id = 1l;
        hotel.name = "测试文档名";
        hotel.address = "地址";
        hotel.price = 10;
        hotel.score = 10;
        hotel.brand = "HP";
        hotel.city = "北京";
        hotel.starName = "starName";
        hotel.business = "商业";
        hotel.latitude = "lat";
        hotel.longitude = "lon";
        hotel.pic = "pic";
        return hotel;
    }

    public static List<Hotel> buildDefaultDataList() {
        List<Hotel> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Hotel hotel = new Hotel();
            hotel.id = (long) i;
            hotel.name = "测试文档名" + i;
            hotel.address = "地址" + i;
            hotel.price = 10;
            hotel.score = 10;
            hotel.brand = "HP";
            hotel.city = "北京";
            hotel.starName = "starName";
            hotel.business = "商业";
            hotel.latitude = "lat" + i;
            hotel.longitude = "lon" + i;
            hotel.pic = "pic";
            list.add(hotel);
        }
        return list;
    }
}
