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
	
	private Integer problemaIdSource;
	
	private Integer problemaIdExternal;
	
	public ProblemaMapper () {
		
	}
	
	public ProblemaMapper(Integer problemaIdSource, Integer problemaIdExternal) {
		this.problemaIdSource = problemaIdSource;
		this.problemaIdExternal = problemaIdExternal;
	}

	public Integer getProblemaIdSource() {
		return problemaIdSource;
	}
	public void setProblemaIdSource(Integer problemaIdSource) {
		this.problemaIdSource = problemaIdSource;
	}
	public Integer getProblemaIdExternal() {
		return problemaIdExternal;
	}
	public void setProblemaIdExternal(Integer problemaIdExternal) {
		this.problemaIdExternal = problemaIdExternal;
	}
	
}
