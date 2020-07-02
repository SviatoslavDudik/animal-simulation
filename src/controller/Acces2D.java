package controller;

public interface Acces2D<T,V> extends Iterable<T> {
	
	public T getElement(int i, int j);
	
	public void setElementContent(V v, int i, int j);
	
	public int getLargeur();

	public int getHauteur();

}
