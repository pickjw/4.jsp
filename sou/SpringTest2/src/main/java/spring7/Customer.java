package spring7;

//고객->웹서버(책대여 사이트)->ip주소,접속시간을 설정
import java.util.Set;

public class Customer {
   private Set<Integer> subSet;//책대여 정보

   //DI중의 <property name="subSet">
   public void setSubSet(Set<Integer> subSet) {
	this.subSet = subSet;
	System.out.println("setSubSet()호출됨!");
 }
   
   public String toString() {
	   return "Customer[subSet="+subSet+"]";
   }
}


