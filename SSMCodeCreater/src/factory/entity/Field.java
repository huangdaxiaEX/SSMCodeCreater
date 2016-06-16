package factory.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库字段属性
 * 
 * @author huangkai
 * 
 */
public class Field {

	public Field() {
		// TODO Auto-generated constructor stub
	}
	
	private String fieldName;
	private String fieldType;
	private String fieldTypeCount;
	private String fieldConstraint;
	private String fieldTag;
	private String fieldDescription;
	private String fieldTagConstraint;
	private boolean isPrimaryKey = false;
	private boolean isForignKey = false;
	private boolean isDefault = false;
	private boolean isCheck = false;
	private boolean isNotNull = false;
	private boolean isUnique = false;
	private String forignCondition = "";
	private String checkCondition = "";
	private String defaultCondition = "";
	private List<String> editConstraints = new ArrayList<String>();
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldTypeCount() {
		return fieldTypeCount;
	}
	public void setFieldTypeCount(String fieldTypeCount) {
		this.fieldTypeCount = fieldTypeCount;
	}
	public String getFieldConstraint() {
		return fieldConstraint;
	}
	public void setFieldConstraint(String fieldConstraint) {
		if (fieldConstraint.equals("") || fieldConstraint.length() == 0) {
			
		} else {
			String[] constraintArr = fieldConstraint.split(";");
			for (int i = 0; i < constraintArr.length; i++) {
				String s = constraintArr[i];
				String preS = s.substring(0, 1).toLowerCase();
				String arr[] = s.split(",");
				String condition = "";
				if (arr.length > 1) {
					condition = s.split(",")[1];
				}
				if (preS.equals("p")) {
					setPrimaryKey(true);
				} else if (preS.equals("d")) {
					setDefault(true);
					setDefaultCondition(condition);
				} else if (preS.equals("c")) {
					setCheck(true);
					setCheckCondition(condition);
				} else if (preS.equals("u")) {
					setUnique(true);
				} else if (preS.equals("f")) {
					setForignKey(true);		
					setForignCondition(condition);
				} else if (preS.equals("n")) {
					setNotNull(true);		
				}
			}
		}
		this.fieldConstraint = fieldConstraint;
	}
	public String getFieldTag() {
		return fieldTag;
	}
	public void setFieldTag(String fieldTag) {
		this.fieldTag = fieldTag;
	}
	public String getFieldDescription() {
		return fieldDescription;
	}
	public void setFieldDescription(String fieldDescription) {
		this.fieldDescription = fieldDescription;
	}
	public String getFieldTagConstraint() {
		return fieldTagConstraint;
	}
	public void setFieldTagConstraint(String fieldTagConstraint) {
		this.fieldTagConstraint = fieldTagConstraint;
	}
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public boolean isForignKey() {
		return isForignKey;
	}
	public void setForignKey(boolean isForignKey) {
		this.isForignKey = isForignKey;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	public boolean isNotNull() {
		return isNotNull;
	}
	public void setNotNull(boolean isNotNull) {
		this.isNotNull = isNotNull;
	}
	public boolean isUnique() {
		return isUnique;
	}
	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}
	public String getForignCondition() {
		return forignCondition;
	}
	public void setForignCondition(String forignCondition) {
		this.forignCondition = forignCondition;
	}
	public String getCheckCondition() {
		return checkCondition;
	}
	public void setCheckCondition(String checkCondition) {
		this.checkCondition = checkCondition;
	}
	public String getDefaultCondition() {
		return defaultCondition;
	}
	public void setDefaultCondition(String defaultCondition) {
		this.defaultCondition = defaultCondition;
	}
	public List<String> getEditConstraints() {
		return editConstraints;
	}
	public void setEditConstraints(List<String> editConstraints) {
		this.editConstraints = editConstraints;
	}
	@Override
	public String toString() {
		return "Field [fieldName=" + fieldName + ", fieldType=" + fieldType
				+ ", fieldTypeCount=" + fieldTypeCount + ", fieldConstraint="
				+ fieldConstraint + ", fieldTag=" + fieldTag
				+ ", fieldDescription=" + fieldDescription
				+ ", fieldTagConstraint=" + fieldTagConstraint
				+ ", isPrimaryKey=" + isPrimaryKey + ", isForignKey="
				+ isForignKey + ", isDefault=" + isDefault + ", isCheck="
				+ isCheck + ", isNotNull=" + isNotNull + ", isUnique="
				+ isUnique + ", forignCondition=" + forignCondition
				+ ", checkCondition=" + checkCondition + ", defaultCondition="
				+ defaultCondition + ", editConstraints=" + editConstraints
				+ "]";
	}
	
}
