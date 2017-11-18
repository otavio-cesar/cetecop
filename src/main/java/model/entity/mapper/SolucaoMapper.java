package model.entity.mapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Solucao_Mapper")
public class SolucaoMapper {

	@Id
	@GeneratedValue
	private Integer id;

	private Integer problemaIdSource;

	private Integer problemaIdExternal;

	public SolucaoMapper() {

	}

	public SolucaoMapper(Integer problemaIdSource, Integer problemaIdExternal) {
		this.problemaIdSource = problemaIdSource;
		this.problemaIdExternal = problemaIdExternal;
	}

	public Integer getProblemaIdExternal() {
		return problemaIdExternal;
	}

	public void setProblemaIdExternal(Integer problemaIdExternal) {
		this.problemaIdExternal = problemaIdExternal;
	}

	public Integer getProblemaIdSource() {
		return problemaIdSource;
	}

	public void setProblemaIdSource(Integer problemaIdSource) {
		this.problemaIdSource = problemaIdSource;
	}

}
