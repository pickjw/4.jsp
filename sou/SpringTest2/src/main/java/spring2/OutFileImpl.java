package spring2;

import java.io.FileWriter;
import java.io.IOException;

public class OutFileImpl implements OutFile {

	private String filePath;//경로명 및 만들어질 파일명 저장
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		System.out.println("setFilePath()호출됨!"+filePath);
	}
    //MessageBeanImplDI (sayHello())->OutFileImpl클래스의 out()
	@Override
	public void out(String message) throws IOException {
		// TODO Auto-generated method stub
        //파일을 생성->한글데이터 저장->FileOutputStream(영문데이터),FileWriter(한글데이터)
		FileWriter fw=new FileWriter(filePath);//파일생성->경로지정(xml)
		fw.write(message);
		fw.close();
	}
}









