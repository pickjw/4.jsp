$(function () {

    /*
     * Slideshow영역에 반복적으로 존재하는 이미지 4개를 읽어들여서 설정
     */
	$('.slideshow').each(function(){
		//var 변수명=값,변수명2=값2,,,
		var $slides=$(this).find('img'), //모든 슬라이드(이미지 배열)
		      slideCount=$slides.length,   //슬라이드 갯수(현재 4장)
		      currentIndex=0;                 //현재 슬라이드를 나타내는 인덱스번호
		
		  //첫번째 슬라이드에 페이드인으로 표시->$(선택자).fadeIn() 서서히 나옴
		  $slides.eq(currentIndex).fadeIn();
		  //화면에 보여주는 이미지 7500밀리초마다 새로운 슬라이드를 화면에 출력
		  //7.5초마다 반복해서 슬라이드를 변경해서 화면에 출력
		  setInterval(showNextSlide,7500)
		  //인덱스 번호에 따라서 새로운 슬라이드를 보여주는 함수(반복적으로 코딩)
		  function showNextSlide(){
			  //다음 표시할 슬라이드의 인덱스번호필요
			  var nextIndex=(currentIndex+1)%slideCount;//(0+1)%4=1
			  alert(nextIndex)//0->1->2->3
			  //현재 슬라이드 페이드 아웃->서서히 사라진다.->fadeOut()
			  $slides.eq(currentIndex).fadeOut();
			  //다음 슬라이드를 페이드 인->서서히 나타나게 설정
			  $slides.eq(nextIndex).fadeIn();
			  //현재 슬라이드 인덱스 번호를 업데이트 해야 한다.
			  currentIndex=nextIndex; //0<-1
		  }
	})
});





