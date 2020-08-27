package com.example.goshoes;

public class CartDetails {

    public String shoename;
    public String shoeprice;

    public CartDetails() {
    }

    public CartDetails(String shoename, String shoeprice) {
        this.shoename = shoename;
        this.shoeprice = shoeprice;
    }

    public String getShoename() {
        return shoename;
    }

    public void setShoename(String shoename) {
        this.shoename = shoename;
    }

    public String getShoeprice() {
        return shoeprice;
    }

    public void setShoeprice(String shoeprice) {
        this.shoeprice = shoeprice;
    }
}
