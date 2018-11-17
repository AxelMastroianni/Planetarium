package il.mio.sistema.di.pianeti;
import java.util.*;
import it.unibs.fp.mylib.*;

public class Planetarium {
	public static final int MASSIMA_COORDINATA_X=50;
	public static final int MASSIMA_COORDINATA_Y=50;
	public static final int MASSIMO_NUMERO_LUNE=5;
	public static final int MINIMA_MASSA_PIANETA=20;
	public static final String[] pianetiGiaPresenti={"Karakura", "Hueco Mundo","Soul Society","Calisthenics"};
	public static final String[] luneGiaPresenti= {"Ichigo","Inoue","Chad","Ishida","Urahara","Aizen","Ulqiorra","Nnoitra","Grimmjow","Toshiro",
			"Yamamoto","Kenpachi","Umberto","Andrea Larosa","Gaggi Yatarov","Gufo","Falco Pellegrino","Barbagianni","Gheppio","Sagiri"};
	public static Luna creaLuna() {
		double massa=InputDati.leggiDouble("Inserisci la massa della luna: ");
		double coordinataX=InputDati.leggiDouble("Inserisci la coordinata x della luna: ");
		double coordinataY=InputDati.leggiDouble("Inserisci la coordinata y della luna: ");
		String codiceUnivoco=InputDati.leggiStringaNonVuota("Inserisci il nome della luna: ");
		return new Luna(massa,coordinataX,coordinataY,codiceUnivoco);
	}
	public static Pianeta creaPianeta() {
		double massa=InputDati.leggiDouble("Inserisci la massa del pianeta: ");
		double coordinataX=InputDati.leggiDouble("Inserisci la coordinata x del pianeta: ");
		double coordinataY=InputDati.leggiDouble("Inserisci la coordinata y del pianeta: ");
		String codiceUnivoco=InputDati.leggiStringaNonVuota("Inserisci il nome del pianeta: ");
		return new Pianeta(massa,coordinataX, coordinataY,codiceUnivoco);
	}
	public static Pianeta creaStella() {
		double massa=InputDati.leggiDouble("Inserisci la massa della stella del sistema: ");
		String codiceUnivoco=InputDati.leggiStringaNonVuota("Inserisci il nome della stella: ");
		return new Pianeta(massa,0,0,codiceUnivoco);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner tastiera=new Scanner(System.in);
		String[] voci = new String[]{"Visualizza Pianeti","Aggiungi Pianeta","Aggiungi Luna","Viaggia con Noi","Controlla presenza Pianeta",
				"Controlla Presenza Luna","Calcola Centro Di Massa","Elimina Pianeta","Elimina Luna","Visualizza Lune Pianeta","Percorso Luna"};
		MyMenu scelte=new MyMenu("Scegli cosa fare", voci);
		Random r=new Random();
		String nomePianeta="", partenza="", arrivo="", nomeLuna="", risposta="";
		int numeroLune=0, numeroPianeti=0;
		System.out.println("Benvenuto nel nostro personale planetario! Divertiti a distruggerlo :D");
		Pianeta stella=creaStella();
		Sistema mioSistema=new Sistema(stella);
		//inizializzo il sistema
		for(int i=0;i<pianetiGiaPresenti.length;i++) {
			double massa=r.nextDouble()*MINIMA_MASSA_PIANETA+stella.getMassa()/2;
			double coordinataX=r.nextDouble()*MASSIMA_COORDINATA_X;
			double coordinataY=r.nextDouble()*MASSIMA_COORDINATA_Y;
			String nome=pianetiGiaPresenti[i];
			mioSistema.aggiungiPianeta(new Pianeta(massa,coordinataX,coordinataY,nome));
			mioSistema.getPianeta(i).setRaggioOrbita(stella);
			numeroPianeti++;
		}
		for(int i=0;i<mioSistema.numeroPianeti();i++) { //aggiungo le lune ai pianeti già presenti
			for(int j=0;j<MASSIMO_NUMERO_LUNE;j++) {
				double massaLuna=r.nextDouble()*mioSistema.getPianeta(i).getMassa();
				double coordinataX=r.nextDouble()*MASSIMA_COORDINATA_X;
				double coordinataY=r.nextDouble()*MASSIMA_COORDINATA_Y;
				String nome=luneGiaPresenti[numeroLune++];
				mioSistema.getPianeta(i).aggiungiLuna(new Luna(massaLuna,coordinataX,coordinataY,nome));
				mioSistema.getPianeta(i).getLuna(j).setRaggio(mioSistema.getPianeta(i));
			}
		}
		while(!risposta.equals("no")) {
			int scelta=scelte.scegli();
			switch(scelta) {
			case 1:  mioSistema.visualizzaSistema(); break;
			case 2: mioSistema.aggiungiPianeta(creaPianeta()); break;
			case 3:{
				do {
				System.out.println("A che pianeta vuoi aggiungerla? "); nomePianeta=tastiera.nextLine();
				}while(!mioSistema.cercaPianeta(nomePianeta));
				Luna nuovaLuna=creaLuna();
				mioSistema.cercaUnPianeta(nomePianeta).aggiungiLuna(nuovaLuna);
				break;
			}
			case 4:{
				do {
					System.out.println("Da dove partire? "); partenza=tastiera.nextLine();
					System.out.println("Dove vuoi arrivare? "); arrivo=tastiera.nextLine();
				}while(!mioSistema.cercaCorpoCeleste(partenza) && !mioSistema.cercaCorpoCeleste(arrivo));
				System.out.println("Percorso: "+mioSistema.getRotta(partenza, arrivo));
				System.out.printf("Percorrerai una distanza di %.2f anni luce\n",mioSistema.calcolaRotta(partenza, arrivo));
				break;
			}
			case 5:{
				System.out.println("Quale pianeta vuoi controllare che ci sia? "); nomePianeta=tastiera.nextLine();
				if(mioSistema.cercaPianeta(nomePianeta))
					System.out.println("Il pianeta è presente!");
				else
					System.out.println("Il pianeta non esiste, prova ad usare meglio il telescopio");
				break;
			}
			case 6:{
				System.out.println("Quale luna vuoi controllare che ci sia? "); nomeLuna=tastiera.nextLine();
				if(mioSistema.cercaLuna(nomeLuna))
					System.out.println("La luna è presente! Appartiene al pianeta "+mioSistema.cercaPianetaDiLuna(nomeLuna).getCodiceUnivoco());
				else
					System.out.println("La luna non esiste, prova ad usare meglio il telescopio");
				break;
			}
			case 7:{
				Coordinata cdm=mioSistema.calcolaCentroDiMassa();
				System.out.printf("Il centro di massa ha le seguenti coordinate: %.2f %.2f\n",cdm.getX(),cdm.getY());
				break;
			}
			case 8:{
				System.out.println("Che pianeta vuoi rimuovere? "); nomePianeta=tastiera.nextLine();
				mioSistema.rimuoviPianeta(nomePianeta);
				break;
			}
			case 9:{
				System.out.println("Che luna vuoi rimuovere? "); nomeLuna=tastiera.nextLine();
				mioSistema.rimuoviLuna(nomeLuna);
				break;
			}
			case 10:{
				System.out.println("Di che pianeta vuoi visualizzare le lune? "); nomePianeta=tastiera.nextLine();
				mioSistema.visualizzaLunePianeta(nomePianeta);
				break;
			}
			case 11:{
				System.out.println("Di quale luna ti interessa vedere il percorso? "); nomeLuna=tastiera.nextLine();
				System.out.println("Il percorso è: "+mioSistema.daLunaAStella(nomeLuna));
			}
			}
			risposta=InputDati.leggiStringaNonVuota("Vuoi continuare ad interagire con il sistema? ");
		}
		

	}

}
