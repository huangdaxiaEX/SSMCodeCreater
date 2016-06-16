package factory.create;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import factory.entity.Entity;
import factory.fileReader.propertiesReader.PropertiesReader;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 抽象类 封装了基本的模板读写
 * 
 * @author huangkai
 * 
 */
public abstract class BaseCreater {
	private String pathKey = "";
	private static PropertiesReader reader;
	protected static Map<String, String> map;
	protected String templatePath = "";
	protected String writePath = (PathSetting.writePath.endsWith("/") ?PathSetting. writePath : PathSetting.writePath
			+ "/");
	protected Configuration cfg = new Configuration();
	
	protected void initReader(){
		if (reader == null) {
			reader = new PropertiesReader("resource/paramSetting.properties");
			map = reader.getPropMap();
		}
	}
	
	protected void createFile(String className) {
		File file = new File(writePath + getPackagePathWithPathKey()
				+ StringCaseUtil.lowcaseFirstChar(className));
		if (!file.exists()) {
			file.mkdirs();
			System.out.println("创建文件夹");
		}
	}
	
	public void setPathKey(String key){
		this.pathKey = key;
	}
	
	protected String getPackagePath() {
		return getParam("package");
	}
	
	protected String getPackagePathWithPathKey() {
		return getPackagePath() + getParam(pathKey);
	}
	
	protected String getPackagePathWithPathKey(String key) {
		return getPackagePath() + getParam(key);
	}
	
	protected void setTemplatePath(String str){
		this.templatePath = str;
	}
	
	private String getParam(String key) {
		if (map.containsKey(key))
			return map.get(key);
		else {
			System.err.println("读取" + key + "出错");
			return "";
		}
	}
	
	public abstract void executeCreateTask(Entity entity);
	
	protected void fillData(String className, Entity entity){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("author", entity.getAuthor());
		// 首字母小写的类名
		data.put("classNameL", StringCaseUtil.lowcaseFirstChar(entity.getEntityName()));
				// 全部小写的类名
		data.put("classNameLL", StringCaseUtil.lowcaseAll(entity
								.getEntityName()));
		data.put("package", getPackagePathWithPathKey().replaceAll("/", "."));
		data.put("dakuohao", "{");
		data.put("dollor", "$");
		data.put("jinhao", "#");
		// 首字母大写的类名
		data.put("className", StringCaseUtil.upcaseFirstChar(entity.getEntityName()));
		data.put("daoPackage", getPackagePathWithPathKey("dao").replaceAll("/", "."));
		data.put("entityPackage", getPackagePathWithPathKey("entity").replaceAll("/", "."));
		data.put("servicePackage", getPackagePathWithPathKey("service").replaceAll("/", "."));
		loadTemplateAndWriteFile(className, ".java", data);
	}
	
	protected void loadTemplateAndWriteFile(String className,String fileType,
			Map<String, Object> data) {
		try {
			Template entityTemplate = cfg.getTemplate(templatePath);
			FileOutputStream fos = new FileOutputStream(writePath
					+ getPackagePathWithPathKey() + StringCaseUtil.lowcaseFirstChar(className)
					+ "/" + className + fileType);
			entityTemplate.process(data, new OutputStreamWriter(fos,
					"utf-8")); //
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
