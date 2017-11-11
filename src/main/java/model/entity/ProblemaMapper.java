package model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Problema_Mapper")
public class ProblemaMapper {

	@Id
	@GeneratedValue
	private Integer id;

	private Integer problemaIdCetecop;

	private Integer problemaIdExternal;

	public ProblemaMapper() {

	}

	public ProblemaMapper(Integer problemaIdSource, Integer problemaIdExternal) {
		this.problemaIdCetecop = problemaIdSource;
		this.problemaIdExternal = problemaIdExternal;
	}

	public Integer getProblemaIdExternal() {
		return problemaIdExternal;
	}

	public void setProblemaIdExternal(Integer problemaIdExternal) {
		this.problemaIdExternal = problemaIdExternal;
	}

	public Integer getProblemaIdCetecop() {
		return problemaIdCetecop;
	}

	public void setProblemaIdCetecop(Integer problemaIdCetecop) {
		this.problemaIdCetecop = problemaIdCetecop;
	}

}
