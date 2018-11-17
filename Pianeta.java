package il.mio.sistema.di.pianeti;
import java.util.*;
public class Pianeta {
	public static final int MAX_NUMERO_PIANETI=56000;
	public static final int MAX_RAGGIO_PIANETA=50;
	Random r=new Random();
	private ArrayList<Luna> satelliti=new ArrayList<Luna>();
	private Coordinata posizionePianeta=new Coordinata(0,0);
	protected String codiceUnivoco="";
	private double massa=0;
	private double raggioOrbita=0;
	
	public Pianeta(double massa, double coordinataX, String codiceUnivoco) {
		this.massa=massa;
		posizionePianeta.setX(coordinataX);
		raggioOrbita=r.nextDouble()*MAX_RAGGIO_PIANETA;
		this.codiceUnivoco=codiceUnivoco+"P";
	}
	public Pianeta(double massa, double coordinataX, double coordinataY, String codiceUnivoco) {
		this.massa=massa;
		posizionePianeta.setX(coordinataX);
		posizionePianeta.setY(coordinataY);
		this.codiceUnivoco=codiceUnivoco+"P";
	}

	public String getCodiceUnivoco() {
		return codiceUnivoco;
	}
	public void setCodiceUnivoco(String codiceUnivoco) {
		this.codiceUnivoco = codiceUnivoco;
	}

	public double getMassa() {
		return massa;
	}

	public void setMassa(double massa) {
		this.massa = massa;
	}

	public Coordinata getPosizionePianeta() {
		return posizionePianeta;
	}
	
	public void setPosizionePianeta() {
		posizionePianeta.setY(Math.sqrt(Math.pow(raggioOrbita, 2)-Math.pow(posizionePianeta.getX(), 2)));
	}
	public double getRaggioOrbita() {
		return raggioOrbita;
	}

	public void setRaggioOrbita(Pianeta stella) {
		raggioOrbita=posizionePianeta.distanza(stella.getPosizionePianeta());
	}
	public Luna getLuna(int indice) {
		return satelliti.get(indice);
	}
	public void aggiungiLuna(Luna satellite) {
		satelliti.add(satellite);
	}
	public void rimuoviLuna(String satellite) {
		for(int i=0;i<satelliti.size();i++) {
			if(satellite.equals(satelliti.get(i).getCodiceUnivoco()))
				satelliti.remove(satelliti.get(i));
		}
	}
	
	public int quanteLune() {
		return satelliti.size();
	}
	
	public boolean cercaLuna(String luna) {
		for(int i=0;i<satelliti.size();i++) {
			if(luna.equalsIgnoreCase(satelliti.get(i).getCodiceUnivoco()))
				return true;
		}
		return false;
	}
	public Luna cercaUnaLuna(String luna) {
		for(int i=0;i<satelliti.size();i++) {
			if(satelliti.get(i).getCodiceUnivoco().equals(luna))
				return satelliti.get(i);
		}
		return null;
	}
	public void visualizzaLune() {
		for(int i=0;i<satelliti.size();i++) {
			System.out.printf("%s di coordinate %.0f %.0f e massa %.2f\n",satelliti.get(i).getCodiceUnivoco(),satelliti.get(i).getPosizione().getX(),
					satelliti.get(i).getPosizione().getY(),satelliti.get(i).getMassa());
		}
	}
	
	

}
