package fractal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Fractal {
	protected int order;
    protected int delay;
    protected List<Side> sidesToDraw;

    protected Consumer<Side> partialResultConsumer;

    public void setPartialResultConsumer(Consumer<Side> c){
        this.partialResultConsumer = c;
    }

	public Fractal() {
        order = 0;
        sidesToDraw = new ArrayList<>();
    }

	/**
	 * Returns the title.
	 * @return the title
	 */
	public abstract String getTitle();

	/** Sets the order of the fractal to order.
	 * @param order the new order of the fractal
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/** 
	 * Returns the order of the fractal. 
	 * @return the order of the fractal*/
	public int getOrder(){
		return order;
	}

	/**
	 * Returns a string representation of this fractal
	 * @return a string representation of this fractal
	 */
	public String toString() {
		return getTitle();
	}


    public abstract List<Side> getSidesToDraw();
    public int getDelay(){
        return  delay;
    }

    public void setDelay(int newDelay) {
        this.delay = newDelay;
    }

    protected void animationPause() {
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void addSide(Side e) {
        this.sidesToDraw.add(e);
        if(this.partialResultConsumer!= null){
            this.partialResultConsumer.accept(e);
        }
        this.animationPause();
    }
}
