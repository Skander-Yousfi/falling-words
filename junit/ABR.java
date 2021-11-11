package org.junit;


public abstract class ABR<T extends Comparable<T>> {

	protected T valeur;
	
	public ABR(T t) {
		valeur = t;
	}

	public abstract int max();
	public abstract boolean contains(int t);
	public abstract boolean find(T t);
	public static void main (String[] args) {
	}
}
