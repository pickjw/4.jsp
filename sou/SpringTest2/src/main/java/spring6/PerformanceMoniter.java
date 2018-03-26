package spring6;

//문자를 전달(SmsSender)->SystemMoniter(출력)
//성능을 체크해주는 클래스
import java.util.List;

public class PerformanceMoniter {
   private List<Double> number;//성능의 차이
   
   public void setNumber(List<Double> number) {
	   this.number=number;
	   System.out.println("setNumber() 호출됨!!");
   }
   public String toString() {
	   return "PerformanceMoniter[number="+number+"]";
   }
}



