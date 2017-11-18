package automatedjudge.domjudge.service.thread;

import controller.VisualizarProblemaBean;
import model.entity.Solucao;
import model.entity.mapper.SolucaoMapper;
import repository.SolucaoRepository;
import repository.mapper.SolucaoMapperRepository;

public class AtualizadaSolucaoThread extends Thread {

	private SolucaoMapper solucaoMapper;

	private SolucaoMapperRepository solucaoMapperRepository;

	private SolucaoRepository solucaoRepository;

	public AtualizadaSolucaoThread(SolucaoMapper solucaoMapper) {
		this.solucaoMapper = solucaoMapper;
		this.solucaoMapperRepository = new SolucaoMapperRepository();
		this.solucaoRepository = new SolucaoRepository();
	}

	@Override
	public void run() {
		String resultado;
		Solucao solucao;

		while (true) {
			try {

				sleep(2000);

				resultado = solucaoMapperRepository.buscaResultadoSolucao(solucaoMapper);

				if (!resultado.equals(VisualizarProblemaBean.ON_QUEUE)) {
					solucao = solucaoRepository.buscarPorId(solucaoMapper.getProblemaIdSource());

					solucao.setResultado(resultado);

					solucaoRepository.atualizaSolucao(solucao);

					// solucaoMapperRepository.excluirMapper(solucaoMapper);

					// TODO delete external solution too

					return;
				}

				System.out.println("1000");

			} catch (Exception e) {
				// if a exception here is throw, it means the solution is not yet judged
				e.printStackTrace();
			}
		}
	}
}
