package factory.pathSetting;

/**
 * 路径基站
 * 
 * @author huangkai
 * 
 */
public class PathSetting {
	public static String readPath = "";
	public static String writePath = "";
	
	//template path 
	public static final String baseTemplatePath  = "resource/template/";
	public static final String controllerTemplate = baseTemplatePath + "Controller.temp";
	public static final String daoTemplate = baseTemplatePath + "Dao.temp";
	public static final String serviceTemplate = baseTemplatePath + "Service.temp";
	public static final String iServiceTemplate = baseTemplatePath + "IService.temp";
	public static final String entityTemplate = baseTemplatePath + "Entity.temp";
	public static final String customDaoTemplate = baseTemplatePath + "CustomDao.temp";
	public static final String mapperXMLTemplate = baseTemplatePath + "MapperXML.temp";
	public static final String customMapperXMLTemplate = baseTemplatePath + "CustomMapperXML.temp";
	public static final String customIServiceTemplate = baseTemplatePath + "CustomIService.temp";
	public static final String customServiceTemplate = baseTemplatePath + "CustomService.temp";

}
