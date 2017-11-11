package model.entity.mapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Equipe_Mapper")
public class EquipeMapper {

	@Id
	@GeneratedValue
	private Integer id;

	private Integer equipeIdSource;

	private Integer equipeIdExternal;

	public EquipeMapper() {

	}

	public EquipeMapper(Integer equipeIdSource, Integer equipeIdExternal) {
		this.equipeIdSource = equipeIdSource;
		this.equipeIdExternal = equipeIdExternal;
	}

	public Integer getEquipeIdSource() {
		return equipeIdSource;
	}

	public void setEquipeIdSource(Integer equipeIdSource) {
		this.equipeIdSource = equipeIdSource;
	}

	public Integer getEquipeIdExternal() {
		return equipeIdExternal;
	}

	public void setEquipeIdExternal(Integer equipeIdExternal) {
		this.equipeIdExternal = equipeIdExternal;
	}

}
