package drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
/**
 * JPanel pouvant afficher des objets de type Shape
 */
public class Drawing extends JPanel implements Iterable<Shape> {
	private Vector<Observer> observers = new Vector<>();
	private static final long serialVersionUID = 1L;
	int cpt=0;
	int nbshape_selectioner = 0;
	ArrayList<Shape> shapes;
	ArrayList<Shape> shapeSelect ;

	public Drawing(){
		super();
		shapes = new ArrayList<Shape>();
		shapeSelect = new ArrayList<Shape>();
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
	// methode pour notifier les observes des shapes selectiner

	public void notifyObservers_for_selected_shape(int value){
		for (Observer obs : observers) {
			obs.updateSelectShape(value);

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
	public void regroup_shape(Shape s){


		//if(s.isOn(e.getPoint())){

		this.shapeSelect.add(s);
		nbshape_selectioner ++;
		//drawing.updateNbSelect(nbshape_selectioner);
		this.notifyObservers_for_selected_shape(nbshape_selectioner);
		//drawing.updateStatus("Selected objects : " + shapeList.size());
		//}


	}
	public void addgrouShape(Shape s){
		shapeSelect.add(s);
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
	/*public void Duplicate(Event e){
		
			shapes.add(s);
			cpt++;
			this.repaint();
			notifyObservers();
		
	}*/

	public void clearShapeselectedgroup(){
		shapeSelect.clear();
	}
	public void dupliquer() {
		for (Shape s: shapeSelect){
			Point p = new Point(s.getOrigin());
			p.y += 100;

			if (s instanceof Rectangle){
				Rectangle r = (Rectangle) s;
				shapes.add(new Rectangle(p, r.getWidth(), r.getHeight(), r.getColor()));
				cpt++;
				notifyObservers();
			}

			if (s instanceof Circle){
				Circle c = (Circle) s;
				shapes.add(new Circle(p, c.getRadius(), c.getColor()));
				cpt++;
				notifyObservers();
			}
		}

		shapeSelect.clear();
		this.repaint();
}
	public int getshapeSelectSize() {
		return shapeSelect.size();
	}



}
