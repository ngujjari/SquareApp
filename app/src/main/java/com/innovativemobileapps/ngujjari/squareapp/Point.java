package com.innovativemobileapps.ngujjari.squareapp;

public class Point {
	 int x, y;

	    public Point(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }

	    @Override
	    public String toString() {
	        return "[" + x + ", " + y + "]";
	    }
}
