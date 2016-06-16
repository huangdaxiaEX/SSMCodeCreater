package factory.fileReader.wordReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import factory.entity.Entity;
import factory.fileReader.IFileReader;
import factory.parse.IParse;
import factory.parse.parseWord.ParseWord;
import factory.pathSetting.PathSetting;

/**
 * 读取word
 * 
 * @author huangkai
 * 
 */
public class WordReader implements IFileReader{
	@Override
	public List<Entity> readFile(String path) throws IOException {
		// TODO Auto-generated method stub
		IParse parse = new ParseWord(getTables(path));
		return parse.startParse();
	}
	
	public TableIterator getTables(String path) throws IOException{
		// 获取文档的读取范围
		FileInputStream in = new FileInputStream(PathSetting.readPath);// 加载文档
		InputStreamReader isr = new InputStreamReader(in);
		System.out.println("文件编码格式:" + isr.getEncoding());
		POIFSFileSystem pfs = new POIFSFileSystem(in);
		HWPFDocument hwpf = new HWPFDocument(pfs);
		Range range = hwpf.getRange();
		TableIterator it = new TableIterator(range);
		return it;
	}

}
