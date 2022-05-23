package com.example.SwapMateWeb.Model;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Getter
@Setter
public class Showroom {
    private String category;
    private String date;
    private String points;
    private String size;
    private String name;
    private String srid;
    private String notes;
    private ArrayList<String> pictures;
}
