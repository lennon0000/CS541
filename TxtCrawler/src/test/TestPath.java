package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("");
		String path = f.getAbsolutePath();
		path+="/newFile/i.txt";
		System.out.println(path);
		File writename = new File(path); // 相对路径，如果没有则要建立一个新的output。txt文件  
        try {
			writename.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 创建新文件  
        BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(writename));
			 out.write("我会写入文件啦\r\n"); // \r\n即为换行  
		        out.flush(); // 把缓存区内容压入文件  
		        out.close(); // 最后记得关闭文件  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
	}

}
