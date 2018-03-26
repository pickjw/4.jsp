package hewon;

//우편번호 검색을 위한 테이블과 연관 빈즈클래스(DTO)
public class ZipcodeDTO {

	private String zipcode;// 우편번호=>멤버변수명은 테이블의 필드와 
	                                                      //같을수도 다를수도 있다.
	private String area1;// 시,도
	private String area2;// 구,소도시
	private String area3;// 동,면,리
	private String area4;// 나머지 주소

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getArea1() {
		return area1;
	}

	public void setArea1(String area1) {
		this.area1 = area1;
	}

	public String getArea2() {
		return area2;
	}

	public void setArea2(String area2) {
		this.area2 = area2;
	}

	public String getArea3() {
		return area3;
	}

	public void setArea3(String area3) {
		this.area3 = area3;
	}

	public String getArea4() {
		return area4;
	}

	public void setArea4(String area4) {
		this.area4 = area4;
	}

}
