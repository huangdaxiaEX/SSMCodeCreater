package factory.entity;

import java.util.List;

/**
 * 实体类
 * 
 * @author huangkai
 * 
 */
public class Entity {

	public Entity() {
		// TODO Auto-generated constructor stub
	}

	private String entityName;
	private String author;
	private int entitySteate;
	private List<Field> fields;
	
	public String getEntityName() {
		return entityName;
	}
	
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getEntitySteate() {
		return entitySteate;
	}
	
	public void setEntitySteate(int entitySteate) {
		this.entitySteate = entitySteate;
	}
	
	public List<Field> getFields() {
		return fields;
	}
	
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "Entity [entityName=" + entityName + ", author=" + author
				+ ", entitySteate=" + entitySteate + ", fields=" + fields + "]";
	}
	
	
}
