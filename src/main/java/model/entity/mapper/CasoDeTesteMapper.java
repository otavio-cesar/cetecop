package model.entity.mapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Caso_De_Teste_Mapper")
public class CasoDeTesteMapper {

	@Id
	@GeneratedValue
	private Integer id;

	private Integer problemaIdSource;

	private Integer casoDeTesteIdSource;

	private Integer casoDeTesteIdExternal;

	public CasoDeTesteMapper() {

	}

	public CasoDeTesteMapper(Integer casoDeTesteIdSource, Integer casoDeTesteIdExternal, Integer problemaIdCetecop) {
		this.casoDeTesteIdSource = casoDeTesteIdSource;
		this.problemaIdSource = problemaIdCetecop;
		this.casoDeTesteIdExternal = casoDeTesteIdExternal;
	}

	public Integer getProblemaIdSource() {
		return problemaIdSource;
	}

	public void setProblemaIdSource(Integer problemaIdSource) {
		this.problemaIdSource = problemaIdSource;
	}

	public Integer getCasoDeTesteIdSource() {
		return casoDeTesteIdSource;
	}

	public void setCasoDeTesteIdSource(Integer casoDeTesteIdSource) {
		this.casoDeTesteIdSource = casoDeTesteIdSource;
	}

	public Integer getCasoDeTesteIdExternal() {
		return casoDeTesteIdExternal;
	}

	public void setCasoDeTesteIdExternal(Integer casoDeTesteIdExternal) {
		this.casoDeTesteIdExternal = casoDeTesteIdExternal;
	}

}
