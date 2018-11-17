package il.mio.sistema.di.pianeti;

public class CorpoCeleste {
	private String codiceUnivoco="";
	
	public CorpoCeleste(String codiceUnivoco) {
		this.codiceUnivoco=codiceUnivoco;
	}

	public String getCodiceUnivoco() {
		return codiceUnivoco;
	}

	public void setCodiceUnivoco(String codiceUnivoco) {
		this.codiceUnivoco = codiceUnivoco;
	}

}
