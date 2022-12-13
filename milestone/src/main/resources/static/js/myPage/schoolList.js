
/*  작은지역들 플래그 */
// let check = false;
/* 하트 플래그 */
let check2 = false;
/* 지역저장 */
let save = [];

$(".location").mouseover(function(){
    $(this).css({"transform":"translateY(-10px)"})
})

$(".location").mouseout(function(){
    $(this).css({"transform":"translateY(0px)"})
})

$(".wholeLocation").css({"background-color":"transparent"});
$(".wholeLocation").css({"border":"solid 1px black"});

/* 전체보기를 눌렀을 땐 작은 지역들 선택을 해제한다 */
$(".wholeLocation").click(function() {
    $(".wholeLocation").css({"background-color":"transparent"});
    $(".wholeLocation").css({"border":"solid 1px black"});
    $(".location2").css({"background-color":"#f2f3f7"})
    $(".location2").css({"border":"none"})
    save=[];
})

/* 지역선택시 지역저장 */
$(".location2").click(function(){
    $(".wholeLocation").css({"background-color":"#f2f3f7"})
    $(".wholeLocation").css({"border":"none"})

    if($(this).css('background-color')=='rgb(242, 243, 247)'){
        $(this).css({"background-color":"transparent"})
        $(this).css({"border":"solid 1px black"})
        /* 담긴게 없다면 무조건 push */
        if(save.length==0){
            save.push($(this).text());
        }else if(save.length>0){
            for (var i = 0; i < save.length; i++) {
                /* 담을 지역이 담은 지역과 겹치는게 없다면 push */
                if(save[i] != $(this).text()){
                    save.push($(this).text());
                    break;
                }
            }
        }
    }else{
        $(this).css({"background-color":"#f2f3f7"})
        $(this).css({"border":"none"})
        save.forEach(element => {
            /* 담을 지역이 담은 지역과 겹치는게 있다면 splice */
            if(element == $(this).text()){
                save.splice(save.indexOf(element),1);
            }
        });
    }
    console.log(save);
})


/* 찜하기 하트 버튼 */
$(".heartWrap").click(function () {
    if ($(".redHeart").eq(0).css("display") == 'block') {
        $(this).closest('article').remove();
    } else {
        $(this).children(".redHeart").show();
    }
})

$("span.heart").mouseover(function(){
    /*$(this).css({"background-color":"rgb(102 102 102 / 29%)"})*/
    $(this).css({"border-radius":"3px"})
    $(this).css({"transition":"all .2s ease"})
})
$("span.heart").mouseout(function(){
    /*$(this).css({"background-color":"transparent"})*/
    $(this).css({"border-radius":"3px"})
    $(this).css({"transition":"all .2s ease"})
})


/*  페이지 이동  */
const $pageNumberLink = $('.page-number-link');

$pageNumberLink.on('mouseover', function () {
    $(this).css('background-color', '#f4f6fa');
    $(this).css('color', '#009ef7');
})

$pageNumberLink.on('mouseout', function () {
    $(this).css('background-color', '#fff');
    $(this).css('color', '#5e6278');
})

window.onresize = function(){
    document.location.reload();
};


$(".dropdown").hide();

if (window.matchMedia('(max-width: 768px)').matches){
    $('div.loactions').css('display','none');
    $('div.dropdown').css('display','block');
    $('div.totalCount2').css('display','none');
}



/*=====================반응형 지역선택=========================*/

var checkDrop=false;
var checkLocal={checkSeoul:false,checkKyungki:false,checkKangwon:false,
    checkChungcheong:false,checkJeolla:false,checkGyeongsang:false,checkJeju:false};
/* 저장된 지역 */
var saveLocal=[];

/* 드롭다운 버튼 */
$("button.dropbtn").on('click',function(){

    if(!checkDrop){
        $(".dropdown-content").show();
        checkDrop=true;
    }else{
        var placeholderText="";

        $(".dropdown-content").hide();
        checkDrop=false;

        /* 눌린 값들 불러와서 검색창에 띄우기 */
        if(0<saveLocal.length && saveLocal.length<7){
            for (let i = 0; i < saveLocal.length; i++) {
                placeholderText += saveLocal[i]+" ";
            }
            $(".placeholder").text(placeholderText);
        }else {
            // console.log(saveLocal.length);
            $(".placeholder").text("전체");
        }
    }
})


/* 드롭다운 버튼 선택(중복가능) */
$(".dropLoc").on('click',function(){

    switch($(this).text()){
        case "서울" :
            if(!checkLocal.checkSeoul && saveLocal){
                $(this).css('background-color','#e2e2e2');
                checkLocal.checkSeoul=true;
                saveLocal.push("서울");
            }else{
                $(this).css('background-color','transparent');
                if(saveLocal){
                    for(var i = 0; i<saveLocal.length; i++){
                        if(saveLocal[i]=="서울"){
                            saveLocal.splice(i,1);
                            checkLocal.checkSeoul=false;
                        }
                    }
                }
            }
            break;
        case "경기도" :
            if(!checkLocal.checkKyungki && saveLocal){
                $(this).css('background-color','#e2e2e2');
                checkLocal.checkKyungki=true;
                saveLocal.push("경기도");
            }else{
                $(this).css('background-color','transparent');
                if(saveLocal){
                    for(var i = 0; i<saveLocal.length; i++){
                        if(saveLocal[i]=="경기도"){
                            saveLocal.splice(i,1);
                            checkLocal.checkKyungki=false;
                        }
                    }
                }
            }
            break;
        case "강원도" :
            if(!checkLocal.checkKangwon && saveLocal){
                $(this).css('background-color','#e2e2e2');
                checkLocal.checkKangwon=true;
                saveLocal.push("강원도");
            }else{
                $(this).css('background-color','transparent');
                if(saveLocal){
                    for(var i = 0; i<saveLocal.length; i++){
                        if(saveLocal[i]=="강원도"){
                            saveLocal.splice(i,1);
                            checkLocal.checkKangwon=false;
                        }
                    }
                }
            }
            break;
        case "충청도" :
            if(!checkLocal.checkChungcheong && saveLocal){
                $(this).css('background-color','#e2e2e2');
                checkLocal.checkChungcheong=true;
                saveLocal.push("충청도");
            }else{
                $(this).css('background-color','transparent');
                if(saveLocal){
                    for(var i = 0; i<saveLocal.length; i++){
                        if(saveLocal[i]=="충청도"){
                            saveLocal.splice(i,1);
                            checkLocal.checkChungcheong=false;
                        }
                    }
                }
            }
            break;
        case "전라도" :
            if(!checkLocal.checkJeolla && saveLocal){
                $(this).css('background-color','#e2e2e2');
                checkLocal.checkJeolla=true;
                saveLocal.push("전라도");
            }else{
                $(this).css('background-color','transparent');
                if(saveLocal){
                    for(var i = 0; i<saveLocal.length; i++){
                        if(saveLocal[i]=="전라도"){
                            saveLocal.splice(i,1);
                            checkLocal.checkJeolla=false;
                        }
                    }
                }
            }
            break;
        case "경상도" :
            if(!checkLocal.checkGyeongsang && saveLocal){
                $(this).css('background-color','#e2e2e2');
                checkLocal.checkGyeongsang=true;
                saveLocal.push("경상도");
            }else{
                $(this).css('background-color','transparent');
                if(saveLocal){
                    for(var i = 0; i<saveLocal.length; i++){
                        if(saveLocal[i]=="경상도"){
                            saveLocal.splice(i,1);
                            checkLocal.checkGyeongsang=false;
                        }
                    }
                }
            }
            break;
        case "제주도" :
            if(!checkLocal.checkJeju && saveLocal){
                $(this).css('background-color','#e2e2e2');
                checkLocal.checkJeju=true;
                saveLocal.push("제주도");
            }else{
                $(this).css('background-color','transparent');
                if(saveLocal){
                    for(var i = 0; i<saveLocal.length; i++){
                        if(saveLocal[i]=="제주도"){
                            saveLocal.splice(i,1);
                            checkLocal.checkJeju=false;
                        }
                    }
                }
            }
            break;
    }
    console.log(saveLocal);
});












/*정서림*/
/*보육원 검색*/
globalThis.page = 0;
let $search = $("#search-bar");
let $pagingBtnFlex = $('.paging-number-flex');
let pageInfo;

// 처음 목록 가져오기
show();

function getList(likeResp) {
    let text = "";
    pageInfo = likeResp.arLikeDTO;
    likeResp.arLikeDTO.content.forEach(likes => {
        text += `<article>`
        text += `<span class="schoolImg">`
        text += `<img src="https://kmong.com/_next/image?url=https%3A%2F%2Fd2v80xjmx68n4w.cloudfront.net%2Fassets%2Fenterprise%2Fquote-guide%2F1-3.jpg&w=320&q=75"><span class="heartWrap">`
        text += `<span role="img" color="#ffffff" rotate="0" class="heart emptyHeart"><svg width="26" height="26" viewBox="0 0 24 24" fill="currentColor" aria-hidden="true" focusable="false" preserveAspectRatio="xMidYMid meet" class="css-7kp13n e181xm9y0"><defs><path d="M16.3680447,3 C14.8701244,3 13.434867,3.60809471 12.3788995,4.68835248 L11.999764,5.07602721 L11.6205012,4.68822239 C9.41974855,2.43790277 5.843218,2.4379028 3.64246538,4.68822244 C1.45251156,6.92750008 1.45251154,10.5501072 3.64246532,12.7893849 L11.4435969,20.7662195 C11.7484384,21.0779268 12.2510896,21.0779268 12.555931,20.7662195 L20.3570626,12.7893849 C21.4096395,11.7136062 22,10.2567327 22,8.73880364 C22,7.22093571 21.409687,5.76411845 20.3570626,4.68822238 C19.3011071,3.60804588 17.8659048,3 16.3680447,3 Z M11.999764,19.1166999 L4.75479942,11.7085588 C3.15293092,10.0706117 3.15293094,7.4069956 4.75479947,5.76904857 C6.34586921,4.14214354 8.91709737,4.14214352 10.5081671,5.76904852 L11.4435969,6.72554678 C11.7484384,7.0372541 12.2510896,7.0372541 12.555931,6.72554678 L13.4913608,5.76904851 C14.2561214,4.98669624 15.2906419,4.5483871 16.3680447,4.5483871 C17.4454474,4.5483871 18.4799679,4.98669624 19.2446013,5.76891842 C20.0136074,6.55487573 20.4466019,7.6234063 20.4466019,8.73880364 C20.4466019,9.85420097 20.0136074,10.9227315 19.2448557,11.7084287 L18.3092987,12.665057 L11.999764,19.1166999 Z" id="heart-outlined-fill-icon"></path></defs><g id="Icons_Favorite_border_fill" stroke="none" stroke-width="1" fill-rule="evenodd"><path d="M16.3680447,3 C14.8701244,3 13.434867,3.60809471 12.3788995,4.68835248 L11.999764,5.07602721 L11.6205012,4.68822239 C9.41974855,2.43790277 5.843218,2.4379028 3.64246538,4.68822244 C1.45251156,6.92750008 1.45251154,10.5501072 3.64246532,12.7893849 L11.4435969,20.7662195 C11.7484384,21.0779268 12.2510896,21.0779268 12.555931,20.7662195 L20.3570626,12.7893849 C21.4096395,11.7136062 22,10.2567327 22,8.73880364 C22,7.22093571 21.409687,5.76411845 20.3570626,4.68822238 C19.3011071,3.60804588 17.8659048,3 16.3680447,3 Z" id="Shape" fill="#212224" fill-rule="nonzero" opacity="0.2"></path><mask id="mask-2" fill="white"><use xlink:href="#heart-outlined-fill-icon"></use></mask><use id="Shape" xlink:href="#heart-outlined-fill-icon"></use></g></svg></span>`
        text += `<span role="img" color="#ff7262" rotate="0" class="heart redHeart"><svg width="26" height="26" viewBox="0 0 24 24" fill="#ff7262" aria-hidden="true" focusable="false" preserveAspectRatio="xMidYMid meet" class="css-7kp13n e181xm9y0"><path xmlns="http://www.w3.org/2000/svg" d="M16.3680447,3 C14.8701244,3 13.434867,3.60809471 12.3788995,4.68835248 L11.999764,5.07602721 L11.6205012,4.68822239 C9.41974855,2.43790277 5.843218,2.4379028 3.64246538,4.68822244 C1.45251156,6.92750008 1.45251154,10.5501072 3.64246532,12.7893849 L11.4435969,20.7662195 C11.7484384,21.0779268 12.2510896,21.0779268 12.555931,20.7662195 L20.3570626,12.7893849 C21.4096395,11.7136062 22,10.2567327 22,8.73880364 C22,7.22093571 21.409687,5.76411845 20.3570626,4.68822238 C19.3011071,3.60804588 17.8659048,3 16.3680447,3 Z"></path></svg></span>`
        text += `</span>`
        text += `</span>`
        text += `<span class="schoolIntroduce">`
        text += `<h2 class="schoolName">`+likes.schoolName+`</h2>`
        text += `<div class="intro">`+ likes.schoolContent+`</div>`
        text += `<div class="miniLocationTag"><div class="schoolLocation1">`
        text += `<span class="schoolLocation2">`+likes.schoolAddress
        text += `</span>`
        text += `<span class="schoolLocation2 schoolName">`+likes.schoolName+`</span>`
        text += `</div>`
        text += `</div>`
        text += `</span>`
        text += `<div class="rightBox">`
        text += `<div class="QR">`
        text += `<img src="https://ko.qr-code-generator.com/wp-content/themes/qr/new_structure/markets/basic_market/generator/dist/generator/assets/images/websiteQRCode_noFrame.png">`
        text += `</div>`
        // text += `<a href="`+ likes.url 인가 ?+`" class="donationButton"><span>보육원 보러가기</span></a>`
        text += `<a class="donationButton"><span>보육원 보러가기</span></a>`
        text += `</div>`
        text += `</article>`
    })
    $(".listSection").html(text)
    pageingBtn();
}


function getListSend(param, callback, error){

    let queryString = "/" + param.page || 1;

    console.log(JSON.stringify(param));
    console.log("queryString : " + queryString);

    $.ajax({
        url : "/myPageRest/likeList" + queryString,
        type : "get",
        success : function (likeResp, status, xhr) {
            if(callback){
                callback(likeResp)
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}


function show() {
    getListSend({
        page: globalThis.page
    }, getList)
}




/* 페이징 처리 */
function pageingBtn() {
    let text = "";
    let nowPage = pageInfo.pageable.pageNumber + 1;
    let endPage = Math.ceil(nowPage / 5) * 5;
    let startPage = endPage - 5 + 1;
    // <!--페이징 처리-->
    // 이전
    if (startPage > 1) {
        text += `<div class="prev-page page-number-padding">`
        text += `<a class="page-number-link" href="` + (startPage - 2) + `">`
        text += `<div class="prev-page-prevArrow"></div>`
        text += `</a>`
        text += `</div>`
    } else {
        text += `<div class="prev-page page-number-padding">`
        text += `</div>`
    }
    // 페이지 버튼

    for (let i = startPage; i < startPage + 5 && i <= pageInfo.totalPages; i++) {
        text += `<div class="page-number-padding">`
        if (i == nowPage) {
            text += `<div class="page-number-link" style="color:#303441">` + i + `</div>`
        } else {
            text += `<a class="page-number-link" href="` + (i - 1) + `">` + i + `</a>`
        }
        text += `</div>`
    }
    //이후
    if (4 < endPage && endPage < pageInfo.totalPages) {
        text += `<div class="next-page page-number-padding">`
        text += `<a class="page-number-link" href="` + (endPage) + `">`
        text += `<div class="prev-page-nextArrow"></div>`
        text += `</a>`
        text += `</div>`
        text += `</div>`
        text += `</div>`
    }
    $pagingBtnFlex.html(text);
}

$pagingBtnFlex.on('click', ".page-number-link", function (e) {
    e.preventDefault();
    window.scrollTo({top: 0})
    globalThis.page = $(this).attr("href");
    show();
})

/*  페이지 이동  */
$pagingBtnFlex.on('mouseover', "a.page-number-link", function () {
    $(this).css('background-color', '#f4f6fa');
    // $(this).css('color', '#009ef7');
})

$pagingBtnFlex.on('mouseout', "a.page-number-link", function () {
    $(this).css('background-color', '#fff');
    $(this).css('color', '#9a9ba7');
})

//좋아요 취소
function cancelLikeSchool(userId, callback, error){
    console.log("좋아요 취소")
    $.ajax({
        url:"/myPageRest/cancel/" + userId,
        type: "get",
        success:function(likeCount){
            // callback(likeCount);
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    })
}

