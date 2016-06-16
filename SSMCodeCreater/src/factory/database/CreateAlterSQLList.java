package factory.database;

import java.util.ArrayList;
import java.util.List;

import factory.database.dao.SQLTemplate;
import factory.entity.Entity;
import factory.entity.Field;

/**
 * 创建alter 语句
 * 
 * @author huangkai
 * 
 */
public class CreateAlterSQLList {

	public CreateAlterSQLList() {
		// TODO Auto-generated constructor stub
	}

	public static List<String> startParseAndCreateSQL(Entity entity) {
		List<String> sqlList = new ArrayList<String>();
		List<Field> fields = entity.getFields();
		int fieldCount = fields.size();
		String tableName = entity.getEntityName();
		// 分别对应5个状态 0：不处理(默认) 1：新增 2：修改 3：删除字段约束 4：删除该字段
		for (int i = 0; i < fieldCount; i++) {
			Field field = fields.get(i);
			String tag = field.getFieldTag();
			if (tag == "" || tag.length() == 0) {
				tag = "0";
			}
			int fieldSteate = Integer.parseInt(tag);
			String sql = "";
			switch (fieldSteate) {
			case 1: {
				sql = SQLTemplate.addColumnSQL(tableName, field);
				sqlList.add(sql);
			}
				break;
			case 2: {
				List<String> editConstraintList = field.getEditConstraints();
				for (int index = 0; index < editConstraintList.size(); index++) {
					String s = editConstraintList.get(index);
					if (s.equals("p")) {
						sql = SQLTemplate.addColumnPrimaryKeySQL(tableName,
								field);
						sqlList.add(sql);
					}
					if (s.equals("u")) {
						sql = SQLTemplate.addColumnUniqueSQL(tableName, field);
						sqlList.add(sql);
					}
					if (s.equals("n")) {
						sql = SQLTemplate.addColumnNotNullSQL(tableName, field);
						sqlList.add(sql);
					}
					if (s.equals("f")) {
						sql = SQLTemplate.addColumnForignKeySQL(tableName,
								field, field.getForignCondition());
						sqlList.add(sql);
					}
					if (s.equals("c")) {
						sql = SQLTemplate.addColumnCheckSQL(tableName, field,
								field.getCheckCondition());
						sqlList.add(sql);
					}
					if (s.equals("d")) {
						sql = SQLTemplate.addColumnDefaultSQL(tableName, field,
								field.getDefaultCondition());
						sqlList.add(sql);
					}
				}
			}
				break;
			case 3: {
				List<String> editConstraintList = field.getEditConstraints();
				for (int index = 0; index < editConstraintList.size(); index++) {
					String s = editConstraintList.get(index);
					if (s.equals("p")) {
						sql = SQLTemplate.dropColumnPrimaryKeySQL(tableName,
								field);
						sqlList.add(sql);
					}
					if (s.equals("u")) {
						sql = SQLTemplate.dropColumnUniqueSQL(tableName, field);
						sqlList.add(sql);
					}
					if (s.equals("n")) {
						sql = SQLTemplate
								.dropColumnNotNullSQL(tableName, field);
						sqlList.add(sql);
					}
					if (s.equals("f")) {
						sql = SQLTemplate.dropColumnForignKeySQL(tableName,
								field);
						sqlList.add(sql);
					}
					if (s.equals("c")) {
						sql = SQLTemplate.dropColumnCheckSQL(tableName, field);
						sqlList.add(sql);
					}
					if (s.equals("d")) {
						sql = SQLTemplate
								.dropColumnDefaultSQL(tableName, field);
						sqlList.add(sql);
					}
				}
			}
				break;
			case 4:
				sql = SQLTemplate.dropColumnSQL(entity.getEntityName(), field);
				sqlList.add(sql);
				break;
			default:
				break;
			}
		}
		return sqlList;
	}
}
