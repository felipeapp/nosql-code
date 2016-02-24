import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;

import database.MongoDatabase;
import database.RegistroEntradaDAO;
import domain.RegistroEntrada;
import mining.RegistroEntradaMining;

public class MigrationRun {

	public static void main(String[] args) {

		RegistroEntradaDAO dao = new RegistroEntradaDAO();

		MongoOperations mongoOp = MongoDatabase.buildMongoDatabase();

		int max_id_entrada = new RegistroEntradaMining().getMaxIdEntrada();

		System.out.println("�ltima entrada migrada: " + max_id_entrada);
		List<Integer> ids = dao.getIDListGT(max_id_entrada);

		System.out.println("Total de entradas pendentes: " + ids.size());
		long start = System.currentTimeMillis();

		for (int i = 0; i < ids.size(); i++) {
			System.out.println("Migrando entrada: " + ids.get(i) + " - " + (i + 1) + " / " + ids.size());

			RegistroEntrada entrada = dao.findByID(ids.get(i));
			mongoOp.insert(entrada);

			if ((i + 1) % 10 == 0)
				System.out.println("Tempo parcial: " + (System.currentTimeMillis() - start) / 1000.0 + " segundos");
		}

		System.out.println("Tempo total: " + (System.currentTimeMillis() - start) / 1000.0 + " segundos");

	}

}
