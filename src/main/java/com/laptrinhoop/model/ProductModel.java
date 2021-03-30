package com.laptrinhoop.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class ProductModel {
    public int id;
    public String name;
    public String image;
    public double discount = 0.0;
    public int quantity = 1;
    public Date productDate = new Date();
    public boolean available = true;
    public String description;
    public int viewCount = 0;

}
