package factory.create.controllerCreater;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;

/**
 * Controller类 生成
 * 
 * @author huangkai
 * 
 */
public class ControllerCreater extends BaseCreater{

	public ControllerCreater() {
		// TODO Auto-generated constructor stub
		super.initReader();
		setPathKey("controller");
		setTemplatePath(PathSetting.controllerTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		// TODO Auto-generated method stub
		String className = StringCaseUtil.upcaseFirstChar(entity
				.getEntityName()) + "Controller";
		createFile(className);
		fillData(className, entity);
	}

}
