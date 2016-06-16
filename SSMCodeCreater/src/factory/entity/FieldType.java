package factory.entity;

/**
 * 数据库字段类型转化为java对应的类型
 * 
 * @author huangkai
 * 
 */
public class FieldType {

	public FieldType() {
		// TODO Auto-generated constructor stub
	}
	
	private static final String type_string 	= String.class.getSimpleName();
	private static final String type_byte 		= Byte[].class.getSimpleName();
	private static final String type_long  		= Long.class.getSimpleName();
	private static final String type_integer 	= Integer.class.getSimpleName();
	private static final String type_boolean 	= Boolean.class.getSimpleName();
	private static final String type_bigInteger	= java.math.BigInteger.class.getSimpleName();
	private static final String type_float		= Float.class.getSimpleName();
	private static final String type_double		= Double.class.getSimpleName();
	private static final String type_bigDecimal	= java.math.BigDecimal.class.getSimpleName();
	
	//SQL��
	private static final String type_date		= java.sql.Date.class.getSimpleName();
	private static final String type_time		= java.sql.Time.class.getSimpleName();
	private static final String type_timestamp	= java.sql.Timestamp.class.getSimpleName();
	
	public static String transferFieldType(String type){
		type = type.split("\\(")[0];
		if (type.equals("") || type == null || type.equalsIgnoreCase("VARCHAR") || type.equalsIgnoreCase("CHAR") || type.equalsIgnoreCase("TEXT")) 
			return type_string;
		if (type.equalsIgnoreCase("BLOB")) 
			return type_byte;
		if (type.equalsIgnoreCase("INTEGER") || type.equalsIgnoreCase("ID")) 
			return type_long;
		if (type.equalsIgnoreCase("TINYINT") || type.equalsIgnoreCase("SMALLINT") || type.equalsIgnoreCase("MEDIUMINT") || type.equalsIgnoreCase("BOOLEAN")) 
			return type_integer;
		if (type.equalsIgnoreCase("BIT")) 
			return type_boolean;
		if (type.equalsIgnoreCase("BIGINT")) 
			return "java.math." + type_bigInteger;
		if (type.equalsIgnoreCase("FLOAT")) 
			return type_float;
		if (type.equalsIgnoreCase("DOUBLE")) 
			return type_double;
		if (type.equalsIgnoreCase("DECIMAL")) 
			return "java.math." + type_bigDecimal;
		if (type.equalsIgnoreCase("DATE") || type.equalsIgnoreCase("YEAR")) 
			return "java.sql." + type_date;
		if (type.equalsIgnoreCase("TIME")) 
			return "java.sql." + type_time;
		if (type.equalsIgnoreCase("DATETIME") || type.equalsIgnoreCase("TIMESTAMP")) 
			return "java.sql." + type_timestamp;
		
		return type_string;
	}
}
