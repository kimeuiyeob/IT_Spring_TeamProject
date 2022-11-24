let check2 = false;

/* 찜하기 버튼 마우스 올렸을 때*/
$("button.zzimButton").mouseover(function(){
    $(this).css({"background-color":"rgb(102 102 102 / 29%)"})
    $(this).css({"border-radius":"3px"})
    $(this).css({"transition":"all .2s ease"})
})
$("button.zzimButton").mouseout(function(){
    $(this).css({"background-color":"transparent"})
    $(this).css({"border-radius":"3px"})
    $(this).css({"transition":"all .2s ease"})
})

$(".redHeart").css({"display":"none"})

/* 찜하기 버튼 클릭 */
$(".zzimButton").click(function(){
    if(!check2){
        $(this).children(".redHeart").css({"display":"inline"})
        $(this).children(".emptyHeart").css({"display":"none"})
        check2=true;
    }else{
        $(this).children(".redHeart").css({"display":"none"})
        $(this).children(".emptyHeart").css({"display":"inline"})
        check2=false;
    }
})

/* 기부하기 버튼 마우스 올렸을 때 */
$("button#donate").mouseover(function(){
    $(this).css({"background-color":"#eac405"})
    $(this).css({"transition":"all .2s ease"})
})
$("button#donate").mouseout(function(){
    $(this).css({"background-color":"#ffd400"})
    $(this).css({"transition":"all .2s ease"})
})

/* 더보기 버튼 마우스 올렸을 때 */
$("button.moreButton").mouseover(function(){
    $(this).css({"background-color":"rgb(237 237 237)"})
    $(this).css({"transition":"all .2s ease"})
})
$("button.moreButton").mouseout(function(){
    $(this).css({"background-color":"transparent"})
    $(this).css({"transition":"all .2s ease"})
})

/* 우측 어사이드 바 클릭 */
$(".intro").click(function(){
    $("#introRightBox").css({"display":"block"})

    $("#pseudo").css({"transform":"translateX(0%)"})
    $(this).css({"color":"rgb(48, 52, 65)"})
    $(this).css({"font-weight":"700"})
    $(".total").css({"font-weight":"normal"})
    $(".total").css({"color":"rgb(154, 155, 167)"})
    $(".ranking").css({"font-weight":"normal"})
    $(".ranking").css({"color":"rgb(154, 155, 167)"})

    $("#totalRightBox").css({"display":"none"})
    $("#rankingRightBox").css({"display":"none"})
})

/* 우측 어사이드 바 클릭 */
$(".total").click(function(){
    $("#totalRightBox").css({"display":"block"})

    $("#pseudo").css({"transform":"translateX(100%)"})
    $(this).css({"color":"rgb(48, 52, 65)"})
    $(this).css({"font-weight":"700"})
    $(".intro").css({"font-weight":"normal"})
    $(".intro").css({"color":"rgb(154, 155, 167)"})
    $(".ranking").css({"font-weight":"normal"})
    $(".ranking").css({"color":"rgb(154, 155, 167)"})

    $("#introRightBox").css({"display":"none"})
    $("#rankingRightBox").css({"display":"none"})
})

/* 우측 어사이드 바 클릭 */
$(".ranking").click(function(){
    $("#rankingRightBox").css({"display":"block"})

    $("#pseudo").css({"transform":"translateX(200%)"})
    $(this).css({"color":"rgb(48, 52, 65)"})
    $(this).css({"font-weight":"700"})
    $(".intro").css({"font-weight":"normal"})
    $(".intro").css({"color":"rgb(154, 155, 167)"})
    $(".total").css({"font-weight":"normal"})
    $(".total").css({"color":"rgb(154, 155, 167)"})

    $("#introRightBox").css({"display":"none"})
    $("#totalRightBox").css({"display":"none"})
})

/* QR 보러가기 모달창 */
function openModal(){
    $("#modal").css({"display":"block"})
    document.body.style.overflow = "hidden";
}

function closeModal(){
    $("#modal").css({"display":"none"})
    document.body.style.overflow = "unset";
}
/* 외부영역 클릭시 모달 닫기 */
$(document).mouseup(function (e){
    if($("#modal").has(e.target).length === 0){
        $("#modal").hide();
        document.body.style.overflow = "unset";
    }
});

/* 슬라이드 */
/* 클릭인덱스 */
/*var i = 0;*/
/* 좌클릭 */
/*$(".prevButton").click(function() {
    if (i > 0) {
        i = i - 1;
        $(".images").stop().animate({
            "left": -171 * i + "px"
        }, "slow");
    }
});*/


/* 우클릭 */
/*$(".nextButton").click(function() {
    if (i < 2) {
        i = i + 1;
        console.log(i);
        $(".images").stop().animate({
            "left": -205 * i + "px"
        }, "slow");
        if(i==2){
            $(".images").stop().animate({
                "left": -188 * i + "px"
            }, "slow");
        }
    }

});*/


/* 사진 클릭시 큰 사진으로 바뀜 */
var mainImage = document.querySelector('#mainImage');

/* 사진 클릭시 큰 사진으로 바뀜 */
var mainImage = document.querySelector('#mainImage');

$(".one").click(function(){
    $("#mainImage").attr('src' , $(this).attr('src'));
})
