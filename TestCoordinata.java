package il.mio.sistema.di.pianeti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCoordinata {

	@Test
	void testDistanza() {
		Coordinata c=new Coordinata(2,0);
		Coordinata c2=new Coordinata(4,0);
		double distanza=c.distanza(c2);
		assertEquals(2, distanza);
		
	}

}
