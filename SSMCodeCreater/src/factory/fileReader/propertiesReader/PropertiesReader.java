package factory.fileReader.propertiesReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 读取properties文件
 * 
 * @author huangkai
 * 
 */
public class PropertiesReader {
	private Properties properties = new Properties();
	private Map<String, String> propMap = new HashMap<String, String>();
	
	public Map<String, String> getPropMap() {
		return propMap;
	}

	public PropertiesReader(String filePath) {
		// TODO Auto-generated constructor stub
		System.out.println("读取配置信息");
		try {
			InputStream inStream = new FileInputStream(filePath);
			if (inStream != null) {
				try {
					properties.load(inStream);
					Set<Object> Setkeyset = properties.keySet();
					for (Object object : Setkeyset) {
						String propValue = properties.getProperty(
								object.toString()).toString();
						propMap.put(object.toString(), propValue);
					}
				} catch (IOException e) {
					System.out.println("读取配置出错");
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e1) {
			System.err.println("文件路径出错");
			e1.printStackTrace();
		}
	}
	
	public String getParam(String key) {
		if (propMap.containsKey(key))
			return propMap.get(key);
		else {
			Object o = properties.getProperty(key);
			if (o != null) {
				propMap.put(key, o.toString());
				return o.toString();
			} else {
				System.err.println("读取" + key + "出错");
				return null;
			}
		}
	}
	
}
