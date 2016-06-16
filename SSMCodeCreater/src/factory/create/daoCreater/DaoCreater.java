package factory.create.daoCreater;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;

/**
 * DAO接口 生成
 * 
 * @author huangkai
 * 
 */
public class DaoCreater extends BaseCreater{

	public DaoCreater() {
		// TODO Auto-generated constructor stub
		super.initReader();
		setPathKey("dao");
		setTemplatePath(PathSetting.daoTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		// TODO Auto-generated method stub
		String className = StringCaseUtil.upcaseFirstChar(entity
				.getEntityName()) + "Dao";
		createFile(className);
		fillData(className, entity);
	}
	
}
