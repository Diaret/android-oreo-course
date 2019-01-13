package com.example.dmitrij.numbershapes;

public class Number{
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isTriangular() {
        double x;
        x = (-1 + Math.sqrt(1+8*getNumber()))/2;
        if (x == Math.round(x) & x > 0)  {
            return true;
        } else {
            return false;
        }

    }

    public boolean isSquare() {
        double x;
        x = Math.sqrt(getNumber());
        if (x == Math.round(x))  {
            return true;
        } else {
            return false;
        }

    }
}
