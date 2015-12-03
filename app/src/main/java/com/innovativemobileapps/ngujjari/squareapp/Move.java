package com.innovativemobileapps.ngujjari.squareapp;

public class Move {

	Point fromPt;
	Point toPt;
	
	
	public Move(Point fromPt, Point toPt){
		this.fromPt = fromPt;
		this.toPt = toPt;
	}
	
	public int hashcode(){
		return this.fromPt.hashCode() * this.toPt.hashCode() * 121; 
	}
	
	public boolean equals (Object mv){
		Move compMv = (Move) mv;
		if(this.fromPt.x == compMv.fromPt.x && this.fromPt.y == compMv.fromPt.y
				&& this.toPt.x == compMv.toPt.x && this.toPt.y == compMv.toPt.y){
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		if(this.fromPt != null && this.toPt != null){
			return "Move is fromMv x = "+this.fromPt.x + " fromMv y = "+this.fromPt.y + " toMv x = "+this.toPt.x+ " toMv y = "+this.toPt.y;
		}
		else if(this.fromPt == null && this.toPt != null){
			return "Move is fromMv is NULL "+ " toMv x = "+this.toPt.x+ " toMv y = "+this.toPt.y;
		}
		return "MOVE is BLANK !!!!";
	}
}
