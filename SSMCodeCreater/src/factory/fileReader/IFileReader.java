package factory.fileReader;

import java.io.IOException;
import java.util.List;

import factory.entity.Entity;

/**
 * 操作文件的接口
 * 
 * @author huangkai
 * 
 */
public interface IFileReader {
	public List<Entity> readFile(String path) throws IOException;
}
