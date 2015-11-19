package drawing;

import javax.swing.*;
import java.awt.*;
import java.util.*;
/**
 * JPanel pouvant afficher des objets de type Shape
 */
public class Drawing extends JPanel implements Iterable<Shape> {
	private Vector<Observer> observers = new Vector<>();
	private static final long serialVersionUID = 1L;
	int cpt=0;
	ArrayList<Shape> shapes;

	public Drawing(){
		super();
		shapes = new ArrayList<Shape>();
	}
	//methode pour ajouter des Observateurs a notre vecteur
	public void addObserver(Observer obs){
		observers.add(obs);
	}
	//methode pour supprimer des Observateurs a notre vecteur
	public void removeObserver(Observer obs){
		observers.remove(obs);
	}
	private void notifyObservers(){
		for (Observer obs : observers) {
			obs.update(cpt);
		}
	}
	/**
	 * ImplŽmentation de l'interface Iterable<Shape>
	 */
	public Iterator<Shape> iterator(){
		return shapes.iterator();
	}

	/**
	 * Ajoute une forme au dessin et redessine
	 */
	public void addShape(Shape s){
		shapes.add(s);
		cpt++;
		this.repaint();
		notifyObservers();
	}

	/** 
	 * RedŽfinition de la mŽthode paintComponent() de JComponent
	 */

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Shape s : shapes){
			s.paint(g);

		}
		notifyObservers();
	}

	/**
	 * Enl�ve toutes les formes et redessine
	 */
	public void clear(){

		shapes.clear();
		cpt=0;
		this.repaint();
		notifyObservers();
	}



}
