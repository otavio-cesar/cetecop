package model.entity;

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
	
	private Integer casoDeTesteIdCetecop;
	
	private Integer problemaIdCetecop;
	
	private Integer casoDeTesteIdExternal;

	public CasoDeTesteMapper () {
		
	}
	
	public CasoDeTesteMapper(Integer casoDeTesteIdCetecop, Integer casoDeTesteIdExternal, Integer problemaIdCetecop) {
		this.setCasoDeTesteIdCetecop(casoDeTesteIdCetecop);
		this.setProblemaIdCetecop(problemaIdCetecop);
		this.setCasoDeTesteIdExternal(casoDeTesteIdExternal);
	}

	public Integer getCasoDeTesteIdExternal() {
		return casoDeTesteIdExternal;
	}

	public void setCasoDeTesteIdExternal(Integer casoDeTesteIdExternal) {
		this.casoDeTesteIdExternal = casoDeTesteIdExternal;
	}

	public Integer getProblemaIdCetecop() {
		return problemaIdCetecop;
	}

	public void setProblemaIdCetecop(Integer problemaIdCetecop) {
		this.problemaIdCetecop = problemaIdCetecop;
	}

	public Integer getCasoDeTesteIdCetecop() {
		return casoDeTesteIdCetecop;
	}

	public void setCasoDeTesteIdCetecop(Integer casoDeTesteIdCetecop) {
		this.casoDeTesteIdCetecop = casoDeTesteIdCetecop;
	}

}
