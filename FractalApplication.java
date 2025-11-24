package fractal;

import fractal.view.FractalView;
import koch.Koch;

public class FractalApplication {
	public static void main(String[] args) {
        // Ändra så att fractals har längden 2 istället för 1
		Fractal[] fractals = new Fractal[1];
        Point start = new Point(150,
                (int) (200 + Math.sqrt(3.0) * 75));
        fractals[0] = new Koch(300, start);
	    // Lägg till Mountain-fraktalen på plats 1 i fractals.
        new FractalView(fractals, "Fraktaler", 700, 600);
	}

}
