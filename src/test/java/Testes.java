import br.com.mprj.PedeDados;
import br.com.mprj.Promotoria;
import org.junit.Test;

import java.util.ArrayList;

public class Testes {
	@Test
	public void iniciarLista() {
		PedeDados pedeDados = new PedeDados();
		try {
			ArrayList<Promotoria> list = pedeDados.buscarPromotoria("", "");

			for (Promotoria p : list) {
				System.out.println(p);
				System.out.println("-------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}