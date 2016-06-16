package factory.create.serviceCreater;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 用户自定义Service类 生成
 * 
 * @author huangkai
 * 
 */
public class CustomServiceCreater extends ServiceCreater{

	public CustomServiceCreater() {
		// TODO Auto-generated constructor stub
		super.initReader();
		setPathKey("service");
		setTemplatePath(PathSetting.customServiceTemplate);
	}

	protected void loadTemplateAndWriteFile(String className,String fileType,
			Map<String, Object> data) {
		try {
			Template entityTemplate = cfg.getTemplate(templatePath);
			FileOutputStream entityFos = new FileOutputStream(writePath
					+ getPackagePathWithPathKey() + StringCaseUtil.lowcaseFirstChar(className)
					+ "/Custom" + className + "Impl" + fileType);
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
