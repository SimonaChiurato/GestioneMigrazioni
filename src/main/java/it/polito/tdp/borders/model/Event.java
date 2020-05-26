package it.polito.tdp.borders.model;

public class Event implements Comparable<Event>{
	
	private int t;
	private Country stato; // stato in cui arrivano
	private int n; // numero di migranti che arrivano
	
	
	
	public Event(int t, Country stato, int n) {
		super();
		this.t = t;
		this.stato = stato;
		this.n = n;
	}
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public Country getStato() {
		return stato;
	}
	public void setStato(Country stato) {
		this.stato = stato;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	@Override
	public int compareTo(Event o) {
		return Integer.compare(this.t, o.t);
	}


}
