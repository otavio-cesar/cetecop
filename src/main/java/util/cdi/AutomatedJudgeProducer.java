package util.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import automatedjudge.domjudge.DomjudgeApi;

@ApplicationScoped
public class AutomatedJudgeProducer {

	@Produces
	@RequestScoped
	@Domjudge
	public DomjudgeApi create() {
		return new DomjudgeApi();
	}

}
