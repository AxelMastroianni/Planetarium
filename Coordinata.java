package il.mio.sistema.di.pianeti;

public class Coordinata {
	private double x=0;
	private double y=0;
	
	public Coordinata(double x, double y) {
		this.x=x;
		this.y=y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	public double distanza(Coordinata altroPunto) {
		double distanza=Math.sqrt(Math.pow(x-altroPunto.getX(),2)+Math.pow(y-altroPunto.getY(), 2));
		return distanza;
	}

}
