package factory.create.mapperXMLCreater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.entity.Field;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;

/**
 * 实体对象mapperXML
 * 
 * @author huangkai
 * 
 */
public class MapperXMLCreater extends BaseCreater{

	public MapperXMLCreater() {
		super.initReader();
		setPathKey("mapper");
		setTemplatePath(PathSetting.mapperXMLTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		// TODO Auto-generated method stub
		String className = StringCaseUtil.upcaseFirstChar(entity
				.getEntityName());
		createFile(className);
		fillData(className, entity);
	}
	
	protected void fillData(String className, Entity entity){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("author", entity.getAuthor());
		// 首字母小写的类名
		data.put("classNameL", StringCaseUtil.lowcaseFirstChar(entity
				.getEntityName()));
		// 全部小写的类名
		data.put("classNameLL", StringCaseUtil.lowcaseAll(entity
						.getEntityName()));
		data.put("dakuohao", "{");
		data.put("dollor", "$");
		data.put("jinhao", "#");
		// 首字母大写的类名
		data.put("className", StringCaseUtil.upcaseFirstChar(entity
				.getEntityName()));
		data.put("daoPackage", getPackagePathWithPathKey("dao").replaceAll("/", "."));
		data.put("entityPackage", getPackagePathWithPathKey("entity").replaceAll("/", "."));
		data.put("entityID", StringCaseUtil.lowcaseAll(entity
				.getEntityName()));
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<Field> fieldList = entity.getFields();
		for (int i = 0; i < fieldList.size(); i++) {
			Map<String,String> fieldMap = new HashMap<String,String>();
			fieldMap.put("fieldNameL", StringCaseUtil.lowcaseFirstChar(fieldList.get(i).getFieldName()));
			fieldMap.put("fieldNameLL", StringCaseUtil.lowcaseAll(fieldList.get(i).getFieldName()));
			list.add(fieldMap);
		}
		data.put("fieldList", list);
		
		loadTemplateAndWriteFile(className, "Mapper.xml", data);
	}

}
