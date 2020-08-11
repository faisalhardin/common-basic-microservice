package com.ecomm.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ProductRequest {
    private Long id;
    private String name;
    private  String imageUrl;
    private String tag;
    private String creationTime;

    public ProductRequest(@JsonProperty("id") Long id,
                          @JsonProperty("name") String name,
                          @JsonProperty("imageUrl") String imageUrl,
                          @JsonProperty("tag") String tag,
                          @JsonProperty("timestamp") String creationTime) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.tag = tag;
        this.creationTime = creationTime;

    }

    public ProductRequest() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "ProductRequest { " +
                "id ='" + id + "'" +
                ", name ='" + name + "'" +
                ", imageUrl ='" + imageUrl + "'" +
                ", tag ='" + tag + "'" +
                ", timestamp ='" + creationTime + "'" +
                " }";
    }
}
