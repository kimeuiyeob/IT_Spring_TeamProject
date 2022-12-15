
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
    globalThis.page=0;
    save=[];
    show();
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
        show();
    }
    console.log(save);
})

let likeId;

/* 찜하기 하트 버튼 */
$("section.schoolList").on('click', "span.heartWrap", function () {
    $(this).closest('article').remove();
    likeId = $(this).closest('article').find('input[name="likeId"]').val();
    cancelLikeSchool(likeId);
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


if (window.matchMedia('(max-width: 768px)').matches){
    $('div.loactions').css('display','none');
    $('div.dropdown').css('display','block');
    $('div.totalCount2').css('display','none');
}



/*정서림*/
/*보육원 검색*/
globalThis.page = 0;
let $pagingBtnFlex = $('.paging-number-flex');
let pageInfo;
const $location = $(".location");

show();
likeCount();

function getList(likeResp) {

    console.log("fkdkdddd아앙아ㅏㅇ : "+JSON.stringify(likeResp))

    let text = "";
    pageInfo = likeResp.arLikeDTO;

    likeResp.arLikeDTO.content.forEach(likes => {

        // console.log("1 rmfjsrk?: " +likes.files[0].fileName)
        // console.log("1 안돼?: " +likes.files[0])
        // console.log("2 : "+ likes.files[1].filePath)
        // console.log("3 : "+likes.files[2].fileUuid)

        // console.log("/file/display?fileName="+ likes.files[1].filePath + "/" + likes.files[2].fileUuid + "_" + likes.files[0].fileName)

        text += `<article>`
        text += `<span class="schoolImg">`


        if(likes.files[0] == null){
            text += `<img src="/imgs/school/defaultSchoolImg.png">`
        }else{
            text += `<img width="195px" height="195px" src= "/file/display?fileName=`+ likes.files[1].filePath + `/` + likes.files[0].fileUuid + `_` + likes.files[0].fileName +`">`
            // text += `<img class="donater-info-img1" src="/file/display?fileName=`+ school.userProfile.filePath + `/` + school.userProfile.fileUuid + `_` + school.userProfile.fileName +`">`

        }

        text += `<span class="heartWrap">`
        text += `<span role="img" color="#ffffff" rotate="0" class="heart emptyHeart"><svg width="26" height="26" viewBox="0 0 24 24" fill="currentColor" aria-hidden="true" focusable="false" preserveAspectRatio="xMidYMid meet" class="css-7kp13n e181xm9y0"><defs><path d="M16.3680447,3 C14.8701244,3 13.434867,3.60809471 12.3788995,4.68835248 L11.999764,5.07602721 L11.6205012,4.68822239 C9.41974855,2.43790277 5.843218,2.4379028 3.64246538,4.68822244 C1.45251156,6.92750008 1.45251154,10.5501072 3.64246532,12.7893849 L11.4435969,20.7662195 C11.7484384,21.0779268 12.2510896,21.0779268 12.555931,20.7662195 L20.3570626,12.7893849 C21.4096395,11.7136062 22,10.2567327 22,8.73880364 C22,7.22093571 21.409687,5.76411845 20.3570626,4.68822238 C19.3011071,3.60804588 17.8659048,3 16.3680447,3 Z M11.999764,19.1166999 L4.75479942,11.7085588 C3.15293092,10.0706117 3.15293094,7.4069956 4.75479947,5.76904857 C6.34586921,4.14214354 8.91709737,4.14214352 10.5081671,5.76904852 L11.4435969,6.72554678 C11.7484384,7.0372541 12.2510896,7.0372541 12.555931,6.72554678 L13.4913608,5.76904851 C14.2561214,4.98669624 15.2906419,4.5483871 16.3680447,4.5483871 C17.4454474,4.5483871 18.4799679,4.98669624 19.2446013,5.76891842 C20.0136074,6.55487573 20.4466019,7.6234063 20.4466019,8.73880364 C20.4466019,9.85420097 20.0136074,10.9227315 19.2448557,11.7084287 L18.3092987,12.665057 L11.999764,19.1166999 Z" id="heart-outlined-fill-icon"></path></defs><g id="Icons_Favorite_border_fill" stroke="none" stroke-width="1" fill-rule="evenodd"><path d="M16.3680447,3 C14.8701244,3 13.434867,3.60809471 12.3788995,4.68835248 L11.999764,5.07602721 L11.6205012,4.68822239 C9.41974855,2.43790277 5.843218,2.4379028 3.64246538,4.68822244 C1.45251156,6.92750008 1.45251154,10.5501072 3.64246532,12.7893849 L11.4435969,20.7662195 C11.7484384,21.0779268 12.2510896,21.0779268 12.555931,20.7662195 L20.3570626,12.7893849 C21.4096395,11.7136062 22,10.2567327 22,8.73880364 C22,7.22093571 21.409687,5.76411845 20.3570626,4.68822238 C19.3011071,3.60804588 17.8659048,3 16.3680447,3 Z" id="Shape" fill="#212224" fill-rule="nonzero" opacity="0.2"></path><mask id="mask-2" fill="white"><use xlink:href="#heart-outlined-fill-icon"></use></mask><use id="Shape" xlink:href="#heart-outlined-fill-icon"></use></g></svg></span>`
        text += `<span role="img" color="#ff7262" rotate="0" class="heart redHeart"><svg width="26" height="26" viewBox="0 0 24 24" fill="#ff7262" aria-hidden="true" focusable="false" preserveAspectRatio="xMidYMid meet" class="css-7kp13n e181xm9y0"><path xmlns="http://www.w3.org/2000/svg" d="M16.3680447,3 C14.8701244,3 13.434867,3.60809471 12.3788995,4.68835248 L11.999764,5.07602721 L11.6205012,4.68822239 C9.41974855,2.43790277 5.843218,2.4379028 3.64246538,4.68822244 C1.45251156,6.92750008 1.45251154,10.5501072 3.64246532,12.7893849 L11.4435969,20.7662195 C11.7484384,21.0779268 12.2510896,21.0779268 12.555931,20.7662195 L20.3570626,12.7893849 C21.4096395,11.7136062 22,10.2567327 22,8.73880364 C22,7.22093571 21.409687,5.76411845 20.3570626,4.68822238 C19.3011071,3.60804588 17.8659048,3 16.3680447,3 Z"></path></svg></span>`
        text += `</span>`
        text += `</span>`
        text += `<span class="schoolIntroduce">`
        text += `<h2 class="schoolName">`+likes.schoolName+`</h2>`
        text += `<div class="intro">`+ likes.schoolContent+`</div>`
        text += `<div class="miniLocationTag"><div class="schoolLocation1">`
        text += `<span class="schoolLocation2" style="margin-right: 5px;">`+likes.schoolAddress
        text += `<input type="hidden" name="likeId" value="`+likes.likeId+`">`
        text += `</span>`
        text += `<span class="schoolLocation2 schoolName">`+likes.schoolName+`</span>`
        text += `</div>`
        text += `</div>`
        text += `</span>`
        text += `<div class="rightBox">`
        text += `<div class="QR">`
        text += `<img src="`+likes.schoolQr+`">`
        text += `</div>`
        text += `<a class="donationButton" href="`+likes.schoolId+`"><span>보육원<br>보러가기</span></a>`
        text += `</div>`
        text += `</article>`
    })
    $(".listSection").html(text)
    pageingBtn();
}


function getListSend(param, callback, error){

    let queryString = "/" + param.page || 1;

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

function getListSearch(param, callback, error){
    let existAddress = param.schoolAddress.length != 0;
    let existSchoolName =  param.schoolName != null && param.schoolName.length != 0;
    let queryString = "/" + param.page || 1;
    queryString += existAddress ? "/" + param.schoolAddress : "";
    if (!existAddress && existSchoolName) {
        queryString += "/null";
    }
    queryString += existSchoolName ? "/" + param.schoolName : "";
    $.ajax({
        url : "/myPageRest/likeListSearch" + queryString,
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

/*좋아요 취소*/
function cancelLikeSchool(likeId){
    console.log("likeId : "+likeId)
    $.ajax({
        url:"/myPageRest/cancel/" + likeId,
        type: "get",
        success:function(result){
            likeCount()
            search()
        },
    })
}

/*좋아요 개수*/
function likeCount(){
    $.ajax({
        url:"/myPageRest/likeCount",
        type: "get",
        success:function(result){
            $("#totalCountNumber").html(result);
        },
    })
}


function show() {
    getListSend({
        page: globalThis.page
    }, getList)
}

function search() {
    getListSearch({
        schoolAddress: save,
        schoolName: $("#search-box").val(),
        page: globalThis.page
    }, getList);
}


//지역으로 검색
$location.on('click', function () {
    globalThis.page = 0;
    search()
})

//보육원 이름으로 검색
$("#search-box").on('keyup', function (e) {
    if (e.keyCode == 13) {
        globalThis.page = 0;
        search()
    }
})

//    보육원 상세 이동
$(".schoolList").on('click', '.donationButton', function (e) {
    e.preventDefault();
    location.href = "/school/read?userId=" + $(this).attr("href");
})


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

