/**
 * 
 */
$(window).on('hashchange',function() {
		goToFromHash();
});
function goToFromHash(a) {
	var hashtag = location.hash.substring(1, location.hash.length).replace(/ /gi, '%20');
    console.log(hashtag);
    // 태그 페이지의 이중 주소 정규표현식입니다.
    var tag = /^tag\/([\s|0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|\-|\_]+)$/i;
             
    // 글 목록 페이지의 이중 주소 정규표현식입니다.
    var archive = /^archive\/([0-9]+)$/i;
    var category = /^category\/([\s|0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|\%|\/]+)$/i;
             
    // 검색 페이지의 이중 주소 정규표현식입니다.
    var s = /^search\/([\s|0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|\%|\/|\-|\_|\[|\]]+)$/i;
    var html = '';
    switch(hashtag) {
    case 'chat' : html = '/chat'; break;
    case 'main' : html = '/'; break;
    }
    $(".content").load(html);
}