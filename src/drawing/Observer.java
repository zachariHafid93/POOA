package drawing;

public interface Observer {

	// Observer avec méthode PUSH : c'est le sujet qui envoie l'état (paramètre value)
	
	public void update(int value);
	public void updateSelectShape(int value);
	
	// Méthode PULL : sans paramètre
	// c'est les observateurs qui, une fois notifiés, doivent demander l'état
	// public void update();
}

