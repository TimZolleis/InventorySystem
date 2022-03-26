package com.tim.backendapplication.product;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {


    private int id;
    private String name;
    private String description;
    private String category;
    private String position;
    private int quantity;
    private int jobID;
    private String serialNumber;
    private String inventoryNumber;
    private String imageURL;
    private String occupation;


}
