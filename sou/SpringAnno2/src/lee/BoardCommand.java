package lee;

//BoardDTO와 같은 역할을 한다.(테이블의 필드와 연결)
//사용자로부터 순수 입력받는값만 처리해주는 클래스
//BoardDTO에서는 테이블의 필드만 적용시키기위해서 수정X

public class BoardCommand {
	//추가
	private int num;
    private String author,title,content;
    //추가
    private String writeday;//작성날짜
    private int readcnt;//조회수
    
    //검색분야,검색어->HashMap을 가지고 작업(다음주 페이징처리하면서 같이작업)
    private String searchName;
    private String searchValue;
    //
    public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriteday() {
		return writeday;
	}

	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	//---------------------------------------------------------------------
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
		System.out.println("setAuthor()메서드 호출됨!");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		System.out.println("setTitle()메서드 호출됨!");
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		System.out.println("setContent()메서드 호출됨!");
	}
}
