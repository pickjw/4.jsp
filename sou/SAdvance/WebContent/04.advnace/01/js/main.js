$(function () {

    /*
     * Slideshow
     */
    // slideshow 클래스를 가진 요소마다 작업을 수행
	
    $('.slideshow').each(function () {
        var $slides = $(this).find('img'), // 모든 슬라이드
            slideCount = $slides.length,   // 슬라이드 점수
            currentIndex = 0;              // 현재 슬라이드를 나타내는 인덱스

        // 첫번째 슬라이드에 페이드 인으로 표시
        $slides.eq(currentIndex).fadeIn();
        // 7500 밀리 초마다 showNextSlide 함수를 실행
        setInterval(showNextSlide, 7500);

        // 다음 슬라이드를 표시하는 함수
        function showNextSlide () {
            // 다음 표시 할 슬라이드의 인덱스
            // (만약 마지막 슬라이드이라면 처음으로 돌아 가기)
            var nextIndex = (currentIndex + 1) % slideCount;
            alert(nextIndex)
            // 현재 슬라이드 페이드 아웃
            $slides.eq(currentIndex).fadeOut();
            // 다음 슬라이드를 페이드 인
            $slides.eq(nextIndex).fadeIn();
            // 현재 슬라이드 인덱스를 업데이트
            currentIndex = nextIndex;
        }
    });
});
