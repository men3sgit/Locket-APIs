package com.rse.webservice.locket.test.designpattern.prototype;

public class Circle extends Shape{
    private double area;
    public Circle(double area){
        this.area = area;
    }
    @Override
    public Circle clone(){
        return new Circle(this.area);
    }

    public static void main(String[] args) {
        Circle c1 = new Circle(100);
        Circle c2 = c1.clone();
        c1.area = 999;
        System.out.println(c2.area);
    }
}
