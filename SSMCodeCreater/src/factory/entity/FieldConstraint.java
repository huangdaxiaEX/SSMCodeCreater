package factory.entity;

/**
 * 数据库字段约束
 * 
 * @author huangkai
 * 
 */
public class FieldConstraint {

	public FieldConstraint() {
		// TODO Auto-generated constructor stub
	}
	//Լ��
	private static final String PRIMARYKEY 	= "primary key";
	private static final String UNIQUE 		= "unique";
	private static final String CHECK  		= "check";
	private static final String DEFAULT 	= "default";
	private static final String FOREIGNKEY 	= "foreign key";
	private static final String NOTNULL 	= "not null";
	
	public static String transferFieldConstraint(String s){
		if (s.equals("") || s == null) 
			return "";
		String preS = s.substring(0, 1).toLowerCase();
		if (preS.equals("p")) 
			return PRIMARYKEY;
		if (preS.equals("u"))
			return UNIQUE;
		if (preS.equals("c"))
			return CHECK;
		if (preS.equals("d"))
			return DEFAULT;
		if (preS.equals("f")) 
			return FOREIGNKEY ;
		if (preS.equals("n"))
			return NOTNULL;
		return "";
	}
}
