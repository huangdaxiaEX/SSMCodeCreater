package factory.create.serviceCreater;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * IService 接口生成
 * 
 * @author huangkai
 * 
 */
public class IServiceCreater extends BaseCreater{

	public IServiceCreater() {
		// TODO Auto-generated constructor stub
		super.initReader();
		setPathKey("service");
		setTemplatePath(PathSetting.iServiceTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		// TODO Auto-generated method stub
		String className = StringCaseUtil.upcaseFirstChar(entity
				.getEntityName()) + "Service";
		createFile(className);
		fillData(className, entity);
	}
	
	protected void createFile(String className) {
		File file = new File(writePath + getPackagePathWithPathKey() + StringCaseUtil.lowcaseFirstChar(className) + "/customInterface");
		if (!file.exists()) {
			file.mkdirs();
			System.out.println("创建文件夹");
		}
	}

/*	protected void fillData(String className, Entity entity){
		Map<String, Object> data = new HashMap<String, Object>();
		// 首字母小写的类名
		data.put("classNameL", StringCaseUtil.lowcaseFirstChar(entity
				.getEntityName()));
		// 首字母大写的类名
		data.put("className", StringCaseUtil.upcaseFirstChar(entity
				.getEntityName()));
		loadTemplateAndWriteFile(className, ".java", data);
	}*/
	
	public void loadTemplateAndWriteFile(String className,String fileType,
			Map<String, Object> data) {
		try {
			Template entityTemplate = cfg.getTemplate(templatePath);
			FileOutputStream entityFos = new FileOutputStream(writePath
					+ getPackagePathWithPathKey() + StringCaseUtil.lowcaseFirstChar(className)
					+ "/customInterface/I" + className + fileType);
			entityTemplate.process(data, new OutputStreamWriter(entityFos,
					"utf-8")); //
			entityFos.flush();
			entityFos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
