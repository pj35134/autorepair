package com.mechanic.autorepair;

public class Service {


        private int id;
        private String title;
        private double price;
        private String image;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    boolean isSelected;

        public Service(int id, String title,double price, /*String shortdesc, double rating, double price,*/ String  image) {
            this.id = id;
            this.title = title;
            this.price = price;
            this.image = image;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

   /* public String getShortdesc() {
        return shortdesc;
    }

    public double getRating() {
        return rating;
    }*/

    public double getPrice() {
        return price;
    }

        public String  getImage() {
            return image;
        }
    }

