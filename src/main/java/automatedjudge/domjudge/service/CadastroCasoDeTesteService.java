package automatedjudge.domjudge.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.entity.CasoDeTeste;
import model.entity.mapper.CasoDeTesteMapper;
import repository.mapper.CasoDeTesteMapperRepository;
import repository.mapper.ProblemaMapperRepository;

public class CadastroCasoDeTesteService {

	private ProblemaMapperRepository problemaMapperRepository;

	public CadastroCasoDeTesteService() {
		problemaMapperRepository = new ProblemaMapperRepository();
	}

	public void guardar(List<CasoDeTeste> casosDeTeste) throws NoSuchAlgorithmException {
		ArrayList<CasoDeTesteMapper> casosDeTesteMapper = new ArrayList<>();
		Integer problemaIdExternal;
		
		EntityManager managerDomjudge = Persistence.createEntityManagerFactory("domjudge").createEntityManager();

		for (CasoDeTeste casoDeTeste : casosDeTeste) {
			problemaIdExternal = problemaMapperRepository.buscaProblemaIdExternal(casoDeTeste.getProblema().getId());
			casosDeTesteMapper.add(inserirCasosDeTeste(managerDomjudge, casoDeTeste, problemaIdExternal));
		}

		for (CasoDeTesteMapper casoDeTesteMapper : casosDeTesteMapper) {
			new Thread(new InsereCasoDeTesteMapper(casoDeTesteMapper)).start();
		}

		managerDomjudge.close();
	}

	// Insere registro por vez.
	private static synchronized CasoDeTesteMapper inserirCasosDeTeste(EntityManager manager, CasoDeTeste casoDeTeste, 
			Integer problemaIdExternal) throws NoSuchAlgorithmException {

		MessageDigest m = MessageDigest.getInstance("MD5");
		
		Integer casoDeTesteId;
		Integer rank;
		String entradaCadasoDeTesteMD5;
		String saidaCadasoDeTesteMD5;
		String entradaCadasoDeTeste;
		String saidaCadasoDeTeste;

		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		rank = (Integer) manager.createNativeQuery("SELECT MAX(rank) FROM testcase where probid = :probid")
				.setParameter("probid", problemaIdExternal)
				.getSingleResult();

		if (rank == null) {
			rank = 1;
		} else {
			rank++;
		}
		
		entradaCadasoDeTeste = casoDeTeste.getEntrada().trim().concat("\n");
		saidaCadasoDeTeste = casoDeTeste.getSaida().trim().concat("\n");
		
		m.update(entradaCadasoDeTeste.getBytes(), 0, entradaCadasoDeTeste.length());
		entradaCadasoDeTesteMD5 = new BigInteger(1, m.digest()).toString(16);
		
		m.update(saidaCadasoDeTeste.getBytes(), 0, saidaCadasoDeTeste.length());
		saidaCadasoDeTesteMD5 = new BigInteger(1, m.digest()).toString(16);
		
		manager.createNativeQuery("INSERT INTO testcase (probid, rank, description, input, output, md5sum_input,"
				+ " md5sum_output) VALUES (:probid, :rank, :description, :input, :output, :md5in, :md5out)")
				.setParameter("probid", problemaIdExternal)
				.setParameter("rank", rank)
				.setParameter("description", casoDeTeste.getDescricao().getBytes())
				.setParameter("input", entradaCadasoDeTeste.getBytes())
				.setParameter("output",saidaCadasoDeTeste.getBytes())
				.setParameter("md5in",entradaCadasoDeTesteMD5)
				.setParameter("md5out",saidaCadasoDeTesteMD5)
				.executeUpdate();

		casoDeTesteId = (Integer) manager.createNativeQuery("SELECT MAX(testcaseid) FROM testcase").getSingleResult();
		
		trx.commit();

		return new CasoDeTesteMapper(casoDeTeste.getId(), casoDeTesteId, casoDeTeste.getProblema().getId());
	}

	private class InsereCasoDeTesteMapper implements Runnable {
		private CasoDeTesteMapperRepository casoDeTesteMapperRepository;
		private CasoDeTesteMapper casoDeTesteMapper;

		public InsereCasoDeTesteMapper(CasoDeTesteMapper casoDeTesteMapper) {
			this.casoDeTesteMapperRepository = new CasoDeTesteMapperRepository();
			this.casoDeTesteMapper = casoDeTesteMapper;
		}

		@Override
		public void run() {
			casoDeTesteMapperRepository.guardar(casoDeTesteMapper);
		}
	}
}
