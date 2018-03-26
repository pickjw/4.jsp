package spring2;

import java.io.IOException;

//파일에 문자열을 입력을 받아서 출력시켜주는 공통 추상메서드
public interface OutFile {
    void out(String message)throws IOException;
}
