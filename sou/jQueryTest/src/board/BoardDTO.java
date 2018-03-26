package board;

//게시글->게시판의 각 필드에 데이터값을 저장,출력목적
import java.sql.Timestamp;//날짜 전용 자료형

public class BoardDTO {
   //분류필드(int)->공지(1),자유(2),답변(3)  (varchar(1)->n,f,r)
	private int num;//게시물 구분번호(article)
	private String writer;//작성자
	private String subject;//글제목->글 상세보기로 연결
	private String email;//이메일
	private String content;//글내용 보기->varchar2(4000)-->text
	private String passwd;//암호 (글쓰기,->글수정,글삭제할때 필요)
	//작성날짜
	private Timestamp reg_date;//작성날짜->oracle(sysdate),Mysql->now()
	                                            //select 함수명(); 실행시킬때
	private int readcount;//조회수->default ->0
	private String ip;//작성자의 ip주소를 출력->request.getRemoteAddr()
	//+ 댓글을 다는 필드를 추가(3개)
	private int ref;//글 그룹번호(=게시물 번호)
	private int re_step;//답변글의 순서를 지정(=같은 그룹일때의 답변글 순서)
	private int re_level;//depth(깊이)->답변글의  답변에 대한 깊이->들여쓰기
	//+파일 업로드(자료실)->다운로드
	//private String file;//파일의 정보 추가(용량 제한)
	//->업로드할 파일 한개이상->테이블을 하나 더 추가해야 한다.
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
}





