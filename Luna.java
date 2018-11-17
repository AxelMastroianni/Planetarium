package il.mio.sistema.di.pianeti;
import java.util.*;
public class Luna {
	public static final int MASSIMO_NUMERO_LUNE=26000;
	public static final int MASSIMO_RAGGIO_LUNA=8;
	Random r=new Random();
	private Coordinata posizione=new Coordinata(0,0);
	private String codiceUnivoco="";
	private double massa=0;
	private double raggioOrbita=0;
	
	public Luna(double massa, double coordinataX, String codiceUnivoco) {
		this.massa=massa;
		posizione.setX(coordinataX);
		codiceUnivoco=this.codiceUnivoco+"L";
		raggioOrbita=r.nextDouble()*MASSIMO_RAGGIO_LUNA;
	}
	public Luna(double massa, double coordinataX, double coordinataY, String codiceUnivoco) {
		this.massa=massa;
		posizione.setX(coordinataX);
		posizione.setY(coordinataY);
		this.codiceUnivoco=codiceUnivoco+"L";
	}
	public void setPosizioneLuna(Coordinata posizionePianeta) {
		double differenzaQuadrati=Math.pow(raggioOrbita, 2)-Math.pow(posizione.getX()-posizionePianeta.getX(),2);
		posizione.setY(Math.sqrt(differenzaQuadrati)+posizionePianeta.getY());
	}
	public Coordinata getPosizione() {
		return posizione;
	}
	public double getRaggio() {
		return raggioOrbita;
	}
	public void setRaggio(Pianeta p) {
		raggioOrbita=posizione.distanza(p.getPosizionePianeta());
	}
	public void setMassa(double massa) {
		this.massa=massa;
	}
	public double getMassa() {
		return massa;
	}
	public void setCodiceUnivoco(String codiceUnivoco) {
		this.codiceUnivoco=codiceUnivoco;
	}
	public String getCodiceUnivoco() {
		return codiceUnivoco;
	}
}
