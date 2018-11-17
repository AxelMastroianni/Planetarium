package il.mio.sistema.di.pianeti;
import java.util.*;
public class Sistema {
	private ArrayList<Pianeta> pianeti=new ArrayList<Pianeta>();
	private Pianeta stella;
	public Sistema( Pianeta stella) {
		this.stella=stella;
	}
	
	public void aggiungiPianeta(Pianeta p) {
		pianeti.add(p);
	}
	public void rimuoviPianeta(String p) {
		for(int i=0;i<pianeti.size();i++)
			if(pianeti.get(i).getCodiceUnivoco().equals(p))
				pianeti.remove(pianeti.get(i));
	}
	public void rimuoviLuna(String luna) {
		for(int i=0;i<pianeti.size();i++) {
			if(pianeti.get(i).cercaLuna(luna))
				pianeti.get(i).rimuoviLuna(luna);
		}
	}
	
	public boolean cercaPianeta(String p) {
		for(int i=0;i<pianeti.size();i++) {
			if(pianeti.get(i).getCodiceUnivoco().equalsIgnoreCase(p))
				return true;
		}
		return false;
	}
	public Pianeta cercaUnPianeta(String p) {
		for(int i=0;i<pianeti.size();i++) {
			if(pianeti.get(i).getCodiceUnivoco().equalsIgnoreCase(p))
				return pianeti.get(i);
		}
		return null;
	}
	public boolean cercaLuna(String luna) {
		for(int i=0;i<pianeti.size();i++) {
			if(pianeti.get(i).cercaLuna(luna))
				return true;
		}
		return false;
	}
	public Luna cercaUnaLuna(String luna) {
		int i=0;
		for(i=0;i<pianeti.size();i++) {
			if(pianeti.get(i).cercaLuna(luna))
				break;
		}
		if(i<pianeti.size())
			return pianeti.get(i).cercaUnaLuna(luna);
		return null;
	}
	public Pianeta cercaPianetaDiLuna(String luna) {
		for(int i=0;i<pianeti.size();i++) {
			if(pianeti.get(i).cercaLuna(luna))
				return pianeti.get(i);
		}
		return null;
	}
	public String daLunaAStella(String luna) {
		for(int i=0;i<pianeti.size();i++) {
			if(pianeti.get(i).cercaLuna(luna))
				return luna+"--->"+pianeti.get(i).getCodiceUnivoco()+"--->"+stella.getCodiceUnivoco();
		}
		return "Non riesco a trovare la Luna da cui vuoi partire";
	}
	public String daPianetaAStella(String p) {
		if(cercaPianeta(p))
			return p+"--->"+stella.getCodiceUnivoco();
		return "Non riesco a trovare il pianeta da cui vuoi partire";
	}
	public String daStellaAPianeta(String p) {
		if(cercaPianeta(p))
			return "--->"+p;
		return "Non riesco a trovare il pianeta in cui vuoi approdare";
	}
	public String daStellaALuna(String luna) {
		for(int i=0;i<pianeti.size();i++) {
			if(pianeti.get(i).cercaLuna(luna)) 
				return "--->"+pianeti.get(i).getCodiceUnivoco()+"--->"+luna;
		}
		return "Non trovo la luna in cui vuoi approdare";
	}
	public String getRotta(String partenza, String arrivo) {
		if(partenza.endsWith("L") && arrivo.endsWith("L"))
			return daLunaAStella(partenza)+daStellaALuna(arrivo);
		else if(partenza.endsWith("L") && arrivo.endsWith("P"))
			return daLunaAStella(partenza)+daStellaAPianeta(arrivo);
		else if(partenza.endsWith("P") && arrivo.endsWith("L"))
			return daPianetaAStella(partenza)+daStellaALuna(arrivo);
		else if(partenza.endsWith("P") && arrivo.endsWith("P"))
			return daPianetaAStella(partenza)+daStellaAPianeta(arrivo);
		return "Rotta non trovata";
	}
	public double calcolaRotta(String partenza, String arrivo) {
		double distanza=cercaCoordinateCorpoCeleste(partenza).distanza(cercaCoordinateCorpoCeleste(arrivo));
		return distanza;
	}
	
	public void visualizzaSistema() {
		for(int i=0;i<pianeti.size();i++) {
			System.out.printf("Il pianeta %s, di coordinate %.0f %.0f e massa %.0f ha le seguenti lune: \n", pianeti.get(i).getCodiceUnivoco(),pianeti.get(i).getPosizionePianeta().getX()
					,pianeti.get(i).getPosizionePianeta().getY(),pianeti.get(i).getMassa());
			for(int j=0;j<pianeti.get(i).quanteLune();j++) {
				System.out.printf("%s, di coordinate %.0f %.0f e massa %.0f\n",pianeti.get(i).getLuna(j).getCodiceUnivoco(),pianeti.get(i).getLuna(j).getPosizione().getX(),
						pianeti.get(i).getLuna(j).getPosizione().getY(),pianeti.get(i).getLuna(j).getMassa());
			}
			System.out.println("-----------------------------------");
		}
	}
	public void visualizzaLunePianeta(String pianeta) {
		cercaUnPianeta(pianeta).visualizzaLune();
	}
	public int numeroPianeti() {
		return pianeti.size();
	}
	public Pianeta getPianeta(int indice) {
		return pianeti.get(indice);
	}
	public boolean cercaCorpoCeleste(String unCorpo) {
		if(cercaPianeta(unCorpo))
			return true;
		else if(cercaLuna(unCorpo))
			return true;
		return false;
	}
	public Coordinata cercaCoordinateCorpoCeleste(String corpoCeleste) {
		if(cercaPianeta(corpoCeleste))
			return cercaUnPianeta(corpoCeleste).getPosizionePianeta();
		else if(cercaLuna(corpoCeleste))
			return cercaUnaLuna(corpoCeleste).getPosizione();
		return null;
	}
	
	public Coordinata calcolaCentroDiMassa() {
		double sommaPesataX=0;
		double sommaPesataY=0;
		double sommaMasse=0;
		for(int i=0;i<pianeti.size();i++) {
			sommaPesataX+=pianeti.get(i).getMassa()*pianeti.get(i).getPosizionePianeta().getX();
			sommaPesataY+=pianeti.get(i).getMassa()*pianeti.get(i).getPosizionePianeta().getY();
			sommaMasse+=pianeti.get(i).getMassa();
			for(int j=0;j<pianeti.get(i).quanteLune();j++) {
				sommaPesataX+=pianeti.get(i).getLuna(j).getMassa()*pianeti.get(i).getLuna(j).getPosizione().getX();
				sommaPesataY+=pianeti.get(i).getLuna(j).getMassa()*pianeti.get(i).getLuna(j).getPosizione().getY();
				sommaMasse+=pianeti.get(i).getLuna(j).getMassa();
			}
		}
		return new Coordinata(sommaPesataX/sommaMasse,sommaPesataY/sommaMasse);
	}

}
