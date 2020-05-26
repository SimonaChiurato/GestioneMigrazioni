package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

public class Simulator {
//valori di output
	
	private int T=-1; // passi simulati
	private Map<Country,Integer> stanziali;
	
	//parametri simulazione
	
	private final int N_MIGRANTI=10000;
	private Country partenza;
	
	//modello del mondo
	
	private Graph<Country, DefaultEdge> grafo;
	
	// coda prioritaria
	private PriorityQueue<Event> queue;
	
	
	public void init(Country partenza, Graph<Country, DefaultEdge> grafo) {
		this.partenza=partenza;
		this.grafo=grafo;
		
		this.T=1;
		this.stanziali= new HashMap<>();
		
		for(Country c: this.grafo.vertexSet()) {
			stanziali.put(c, 0);
		}
		this.queue= new PriorityQueue<Event>();
		queue.add(new Event(T,partenza, N_MIGRANTI));
	}
	
	public void run() {
		Event e;
		while((e= this.queue.poll())!=null) {
			this.T=e.getT();
			int nPersone= e.getN();
			Country stato= e.getStato();
			//cerco i vicini di "stato"
			List<Country> vicini= Graphs.neighborListOf(grafo, stato);
			
			int migranti= (nPersone/2)/vicini.size();
			
			if(migranti>0) {
				for(Country c: vicini) {
					this.queue.add(new Event(T+1,c,migranti));
				}
			}
			int perStanziali= nPersone-(migranti*vicini.size());
			this.stanziali.put(stato, this.stanziali.get(stato)+perStanziali);
			
		}
	}
	
	public Map<Country, Integer> getStanziali(){
		return this.stanziali;
	}
	
	public int getT() {
		return this.T;
	}
}
