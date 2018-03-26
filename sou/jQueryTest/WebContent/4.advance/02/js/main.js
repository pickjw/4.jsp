$(function () {

    /*
     * Slideshow
     */
	$('.slideshow').each(function(){
		//var 변수명=값,변수명2=값2,,,
		var $container=$(this),// div태그 영역 전체
		      $slideGroup=$container.find('.slideshow-slides'), //모든 슬라이드그룹
		      $slides=$slideGroup.find('.slide'), //각 슬라이드객체
		      $nav=$container.find('.slideshow-nav'), //네비게이션 객체
		      $indicator=$container.find('.slideshow-indicator'), //인디케이터
		
		      slideCount=$slides.length,   //슬라이드 갯수(현재 4장)
		      indicatorHTML='', //indicator 표시문자열 저장변수
		      currentIndex=0,  //현재 슬라이드를 나타내는 인덱스번호
		      duration=500, //다음슬라이드로 전환시 애니매이션의 소요시간
		      easing='easeInOutExpo',  //다음 슬라이드로 전환시 적용되는 애니매이션의 종류(이징)
		      //이징(화면 전환의 종류)->linear,swing+~종류(jQuery UI) jqueyui.com
		      interval=7500, //자동으로 다음슬라이드로 옮길때까지의 시간
		      timer; //타이머->중간에 일시적으로 멈추는 기능까지 구현
		      
		      //1.각 슬라이드의 위치를 결정하고 인디케이터의 링크를 만들 함수
		      $slides.each(function(i){ //슬라이드를 반복(4개)
		    	  $(this).css({left:100*i+'%'}) //0%,100%,200%,300% 슬라이드위치
		    	  indicatorHTML+='<a href="#">'+(i+1)+'</a>';//1,2,3,4
		      })
		      
		      //인디케이터에 링크문자열을 삽입
		      $indicator.html(indicatorHTML)//text(),html(),val()
		      
		      //2.모든 슬라이드를 표시하는 함수
		      function goToSlide(index){
		    	  //슬라이드 그룹을 대상 위치에 맞게 이동(effect 효과)
		    	  //형식)$(선택자).animate(1.css속성(위치),2.효과시간,3.easing(종류)
		    	  $slideGroup.animate({left:-100*index+'%'},duration,easing);
		    	  //현재 슬라이드의 인덱스를 매개변수 index변경
		    	  currentIndex=index;
		    	  //현재 슬라이드가 결정이 나면 <,>의 비활성화 부분을 설정
		    	  updateNav();
		      }
		      
		      //3.슬라이드의 상태에 따라 탐색(<,>) 및 표시를 업데이트하는 함수
		      function updateNav(){
		    	  var $navPrev=$nav.find('.prev'), //Prev(이전)링크
		    	        $navNext=$nav.find('.next');//Next(다음) 링크
		    	  //1.현재슬라이드가 첫번째 슬라이드 < 비활성
		    	  if(currentIndex===0){
		    		  $navPrev.addClass('disabled') //< 비활성화
		    	  }else{
		    		  $navPrev.removeClass('disabled')//< 활성화 1,2,3,4
		    	  }
		    	  //2.만약에 마지막 슬라이드라면 Next > 비활성
		    	  if(currentIndex===slideCount-1){//3을 입력하는것보다 낫다
		    		  $navNext.addClass('disabled') //> 비활성화
		    	  }else{
		    		  $navNext.removeClass('disabled')//> 활성화 1,2,3,4
		    	  }
		    	  //현재 슬라이드의 표시를 해제
		    	  $indicator.find('a').removeClass('active').
		             eq(currentIndex).addClass('active')
		    	    //현재선택표시해제->다른슬라이드 체크선택
		      }
		      
		      //4.타이머를 작동(시작)하는 함수
		      function startTimer(){
		    	  timer=setInterval(function(){
		    		  //현재 슬라이드의 인덱스에 따라 다음 표시할 슬라이드 결정
		    		  var nextIndex=(currentIndex+1)%slideCount;
		    		  goToSlide(nextIndex)//1,2,3,0,1,2,3,,,,
		    	  },interval); //500->0.5초
		      }
		      
		      //5.타이머를 중지시키는 함수
		      function stopTimer(){
		    	  clearInterval(timer) //clearInterval(해제시킬 설정정보)
		      }
		      
		      //6.네비게이션링크를 클릭하면 해당 슬라이드를 표시(이벤트처리)
		      //형식) $(선택자).on(1.이벤트종류,2.이벤트대상자,3.처리해주는 함수)
		      $nav.on('click','a',function(event){
		    	  event.preventDefault();//링크의 기본기능을 제거
		    	  //현재 < 클릭했다면->현재 클릭한 a 링크(현재 발생시킨 이벤트객체)
		    	  //형식) $(선택자).hasClass('찾고자하는 클래스명')->true or false
		    	  if ($(this).hasClass('prev')){
		    		  goToSlide(currentIndex-1)
		    	  }else{
		    		  goToSlide(currentIndex+1)
		    	  }
		    	  //return false
		      })
		      
		      //7.indicator의 링크를 클릭하면 해당 슬라이드를 표시
		      $indicator.on('click','a',function(event){
		    	  event.preventDefault();//링크의 기본기능을 제거
		    	  if(!$(this).hasClass('active')){//active클래스 속성을 가지고 있지않다면
		    		  goToSlide($(this).index()) //$(선택자).index()->인덱스번호 구해줌
		    	  }
		      })
		      
		      //8.mouseenter시 타이머 정지,moueleave->타이머 작동
		      $container.on({
		    	  //이벤트종류명:호출할 함수명 or 익명함수 function(){}
		    	  mouseenter:stopTimer,
		    	  mouseleave:startTimer
		      })
		      //슬라이드쇼 시작(작동)
		      goToSlide(currentIndex);//0
		      //타이머도 작동(시작)
		      startTimer();//이동하라
	})
});
