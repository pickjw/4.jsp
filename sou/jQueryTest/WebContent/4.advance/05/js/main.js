$(function () {

    /*
     * 갤러리
     */
	$('#gallery').each(function(){ //이미지를 반복적으로 불러오기
		//#gallery요소를 객체
		var $container=$(this);//<ul class="gallery"  id="gallery"><li></li></ul>
		//형식)$(선택자).masonry(옵션)->그리드 형태로 배치
		$container.masonry({
			columnWidth:230, //열의길이(필수지정)
			gutter:10, //열사이의 좌우간격
			itemSelector:'.gallery-item' //그리드에 해당하는 요소선택자를 지정(필수)
		});
		/*
		 * {
             "title": "Bad Boy",
             "images": {
                          "thumb": "img/thumb-3-7.jpg",
                           "large": "img/large-3-7.jpg"
           }
		 * 
		 */
		//2.이미지를 불러오기->content.json
		$.getJSON('./data/content.json',function(data){
			var elements=[];//태그포함한 이미지 문자열 배열 생성  {} //객체표시
			//반복해서 불러오기 $.each
			$.each(data,function(i,item){ //i 인덱스번호,item(각 이미지 정보태그)
				var itemHTML='<li class="gallery-item is-loading">'+
				                        '<a href="'+item.images.large+'">'+
				                           '<img src="'+item.images.thumb+
				                           '" alt="'+item.title+'">'+
				                        '</a>'+'</li>';
				//html문자열을 Dom요소->배열에 저장
				elements.push($(itemHTML).get(0));
			})
			//ul태그에 li태그를 추가->부모.append(자식)
			$container.append(elements)
			
			//이미지가 메모리에 다 불러오고나서 길이가 다 다른 이미지->그리드형태
			//이미지를 다 불러왔으면 is-loading클래스 선택자 해제->화면에 보인다.
			$container.imagesLoaded(function(){
				$(elements).removeClass('is-loading');//이미지가 보인다.
				$container.masonry('appended',elements)//호출할 메서드,추가할 항목
			})
		})
	})
});




