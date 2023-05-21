package com.kusitms.samsion.domain.funeralshop.domain.entity;

import com.kusitms.samsion.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FuneralShop extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private RunType runType;

    private String area;

    private String shopName;

    private String address;

    private String phoneNumber;

    private Integer minPrice;

    private Integer maxPrice;

    private String openTime;

    private String closingTime;

    private String url;

    @Builder
    public FuneralShop(String imageUrl, RunType runType, String area, String shopName, String address, String phoneNumber,
                       Integer minPrice, Integer maxPrice, String openTime, String closingTime, String url) {
        this.imageUrl = imageUrl;
        this.runType = runType;
        this.area = area;
        this.shopName = shopName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.openTime = openTime;
        this.closingTime = closingTime;
        this.url = url;
    }
}
