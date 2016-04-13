package br.com.gpfurlaneto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_SETTING")
public class Setting {
	
	@Id
	@Column(name = "ID_SETTING")
	private Long id;
	
	@Column(name = "NM_SETTING")
	private String name;
	
	@Column(name = "VL_SETTING")
	private String value;
	
	@Column(name = "DS_CONSTANT")
	private String constant;
 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getConstant() {
		return constant;
	}
	public void setConstant(String constant) {
		this.constant = constant;
	}
	
	
}
