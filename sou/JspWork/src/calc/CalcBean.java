package calc;

public class CalcBean {
	// 멤버변수는 <input type="text" name="num1"와 반드시 일치
	// ->액션태그사용하기위해서

	private int num1, num2;//초기값은 자동적으로 0
	private String operator = "";// 연산자->NullPointerException이 발생
	private int result;// 연산결과값을 저장할 변수

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
		System.out.println("setNum1()호출됨!");
	}
	/*
	 * Tomcat 8.5 기준
	 * <jsp:setProperty name="~" property="*" /> ->에러유발
	 *  멤버변수의 자료형과 Setter Method의 자료형이 반드시 같아야 된다.
	 * public void setNum1(String num1){
	 *     this.num1=Integer.parseInt(num1);
	 * }
	 */

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
		System.out.println("setNum2()호출됨!");
	}
   //계산결과값을 화면에 출력
	public int getResult() {
		return result;
	}
    //계산을 해주는 메서드->calculate->매개변수 X  반환값 X
	public void calculate() { //비지니스로직에 관련된 메서드->DAO
		//+
		if(operator.equals("+")) {
			result=num1+num2;
		}
		//-
		if(operator.equals("-")) {
			result=num1-num2;
		}
		//*
		if(operator.equals("*")) {
			result=num1*num2;
		}
		// /
		if(operator.equals("/")) {
			result=num1/num2;
		}
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
		System.out.println("setOperator()호출됨!");
	}

}


