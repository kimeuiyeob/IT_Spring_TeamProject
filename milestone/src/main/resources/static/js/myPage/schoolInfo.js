/*--------------------------다음 우편 api------------------------------*/
function find() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $("input[name='zipCode']").val(data.zonecode);
            $("input[name='address']").val(addr);
            // 커서를 상세주소 필드로 이동한다.
            $("input[name='addressDetail']")[0].focus();
            $('#searchAddressBtn').parent().next().hide();
        }
    }).open();
}

/* -----------------------은행 목록 드롭다운----------------------- */
const $moreSelect = $('div.inputCos');
const $moreSelectList = $('div.moreSelectWrap');
const $moreSelectItems = $('div.moreSelectItem');
const $bank = $('#bank');
const existingBank = $bank.val();

$moreSelectItems.on('click', function () {
    $bank.css("color", '#303441');
    $bank.val($(this).text());
    $moreSelectList.hide();
    $warningMsg = $bank.closest(".inputCos").next();
    $warningMsg.hide();
})

document.addEventListener('click', function (e) {
    if (!(e.target == $moreSelectList[0])) {
        if ($moreSelectList.css("display") == 'block') {
            $moreSelectList.hide();
            return;
        }
    }
    if ($(e.target).closest('.inputCos')[0] === $moreSelect[0]) {
        $moreSelectList.toggle();
    }
})

/*--------------------------게시글 작성 글자수 제한----------------------------*/
const $content = $('#content');
const $textareaCos = $('.textareaCos');
const $contentLength = $('.contentLength');
const maxContent = 500;

$content.focusin(function () {
    $textareaCos.css("border", "1px solid rgb(118, 118, 118)")
})
$content.focusout(function () {
    $textareaCos.css("border", "1px solid rgb(228, 229, 237)")
})

$content.keyup(function (e) {
    $contentLength.text($content.val().length)
    if ($content.val().length > maxContent) {
        $content.val($content.val().substring(0, maxContent));
        $contentLength.text(maxContent);
    }
})


/*사진 추가 썸네일*/
const $fileTest = $(`#schoolImg`);
const $thumbnail = $(`.profile`);

$fileTest.on('change', function (e) {
    var reader = new FileReader();
    let text = "";
    reader.readAsDataURL(e.target.files[0]);
    reader.onload = function (e) {
        let url = e.target.result;
        text += `<div class = "imgsWrap">`;
        if (url.includes('image')) {
            text += `<img src="` + url + `" width="136" height="100">`;
            // $thumbnail.css('background-image', "url('" + url + "')");
        } else {
            text += `<img src="/imgs/fix/normalProfile.png" width="136" height="100">`;
            // $thumbnail.css('background-image', "url('imgs/fix/normalProfile.png')");
        }
        text += `</div>`;
        $('.imgList').append(text);
        if ($('.imgsWrap').length > 0) {
            $('.imgBox').show();
        } else {
            $('.imgBox').hide();
        }
    }

})

/*-----------------------------------유효성 검사-------------------------------*/

/*보육원 주소*/
const $address = $('#address');
const existingAddress = $address.val();

/*보육원 상세 주소*/
const $addressDetail = $('#addressDetail');
const existingAddressDetail = $addressDetail.val();

$addressDetail.on('click', function () {
    $warningMsg = $(this).prev().prev();
    if (!$address.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("주소 검색 버튼을 눌러주세요")
    }
})

$addressDetail.on('keyup', function () {
    $warningMsg = $(this).next();
    if (!$addressDetail.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("상세주소를 입력해 주세요. 상세주소가 없다면 주소지의 특징을 입력해 주세요.")
    } else {
        $warningMsg.hide();
    }
    checkSubmit();
})

/*원장님 이름*/
const $name = $('#name');
var nameCheck = /^[가-힣]{2,15}$/;
const existingName = $name.val();
let nameCheckFlag = false;

$name.on('keyup', function () {
    nameCheckFlag = false;
    $warningMsg = $(this).next();
    if (!$name.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("원장님 이름을 입력해 주세요.")
    } else if (!nameCheck.test($name.val())) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("이름을 정확히 입력해 주세요.")
    } else {
        nameCheckFlag = true;
        $warningMsg.hide();
    }
    checkSubmit();
})

/*연락처*/
const $phone = $('#phone');
const existingPhone = $phone.val().replace(/-/g, "");
let phoneCheckFlag = false;

const phoneCheck = /^[0-9]{11,11}$/;

let phone;

$phone.on('keyup', function () {
    phoneCheckFlag = false;
    $warningMsg = $(this).next();
    phone = $(this).val().replace(/-/g, "");
    $phone.val(phone);
    if (!phone) {
        $warningMsg.hide();
        return;
    } else if (!phoneCheck.test(phone)) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("전화번호를 정확히 입력해 주세요.")
    } else {
        phoneCheckFlag = true;
        $warningMsg.hide();
    }
    checkSubmit();
})

/*보육원 교사 수*/
const $teacherPersonnel = $("#teacherPersonnel");
const existingTeacherPersonnel = $teacherPersonnel.val();

$teacherPersonnel.on("keyup", function () {
    let teacherPersonnel = $teacherPersonnel.val();
    teacherPersonnel = $teacherPersonnel.val().replace(/[^0-9]/g, '');
    $teacherPersonnel.val(teacherPersonnel);
    checkSubmit();
})

/*보육원 아이들 수*/
const $childPersonnel = $("#childPersonnel");
const existingChildPersonnel = $childPersonnel.val();
let childPersonnel = false;

$childPersonnel.on("keyup", function () {
    let childPersonnel = $childPersonnel.val();
    childPersonnel = $childPersonnel.val().replace(/[^0-9]/g, '');
    $childPersonnel.val(childPersonnel);
})

$childPersonnel.on('keyup', function () {
    childPersonnel = false;
    $warningMsg = $(this).next();
    if (!$childPersonnel.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("아이들 수를 입력해 주세요.");
        $childPersonnel.val("");
    } else {
        childPersonnel = true;
        $warningMsg.hide();
    }
    checkSubmit();
})

/*1년 예산*/
const $budget = $("#budget");
const existingBudget = $budget.val();

$budget.on("keyup", function () {
    let budget = $budget.val();
    budget = $budget.val().replace(/[^0-9]/g, '');
    $budget.val(budget);
    checkSubmit();
})

/*계좌번호*/
const $accountNumber = $("#accountNumber");
const existingAccountNumber = $accountNumber.val();

$accountNumber.on("keyup", function () {
    let accountNumber = $accountNumber.val();
    accountNumber = $accountNumber.val().replace(/[^0-9]/g, '');
    $accountNumber.val(accountNumber);
})

$accountNumber.on('keyup', function () {
    $warningMsg = $(this).next();
    if (!$accountNumber.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("계좌번호를 입력해 주세요.");
        $accountNumber.val("");
    } else {
        $warningMsg.hide();
    }
    checkSubmit();
})

/*소개글 제목*/
const $title = $('#title');
const existingTitle = $title.val();

$title.on('keyup', function () {
    $warningMsg = $(this).next();
    if (!$title.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("소개글 제목을 입력해 주세요.");
    } else {
        $warningMsg.hide();
    }
    checkSubmit();
})

/*소개글*/
const existingContent = $content.val();

$content.on("keyup", function () {
    $warningMsg = $(this).closest('.relative').next();
    if (!$content.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("내용을 작성해 주세요.");
    } else {
        $warningMsg.hide();
    }
    checkSubmit();
})

/*서브밋 최종확인*/
function checkSubmit() {
    /*나의 정보중 바뀐정보가 있다면*/
    /*수정하기 버튼 활성화*/
    if (existingAddress != $address.val() || existingAddressDetail != $addressDetail.val() || existingName != $name.val() || existingPhone != $phone.val().replace(/-/g, "") ||
        existingTeacherPersonnel != $teacherPersonnel.val() || existingChildPersonnel != $childPersonnel.val() ||
        existingBudget != $budget.val() || existingBank != $('#bank').val() || existingAccountNumber != $accountNumber.val() || existingTitle != $title.val() || existingContent != $content.val()) {
        $submitBtn.attr("disabled", false)
    } else {
        $submitBtn.attr("disabled", true)
    }

    return true;
}

function nullCheck() {
    /*주소*/
    if (!$address.val()) {
        $warningMsg = $address.parent().next();
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("주소 검색 버튼을 눌러주세요.")
        window.scrollTo(500, 0);
        return;
    }
    /*상세 주소*/
    if (!$addressDetail.val()) {
        $warningMsg = $addressDetail.next();
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("상세주소를 입력해 주세요. 상세주소가 없다면 주소지의 특징을 입력해 주세요.")
        setTimeout(() => {
            $addressDetail.focus();
        }, 0)
        return;
    }
    /*원장님 이름*/
    if (!$name.val()) {
        $warningMsg = $name.next();
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("원장님 이름을 입력해 주세요.")
        setTimeout(() => {
            $name.focus();
        }, 0)
        return;
    }
    /*이름*/
    if (existingName != $name.val()) {
        if (!(nameCheckFlag)) {
            setTimeout(() => {
                console.log("ㄴㅇ러ㅣㄴ어리")
                $name.focus();
            }, 0)
            return;
        }
    }
    /*휴대전화*/
    if (existingPhone != $phone.val().replace(/-/g, "")) {
        if (!(phoneCheckFlag)) {
            setTimeout(() => {
                $phone.focus();
            }, 0)
            return;
        }
    }
    /*아이들 수*/
    if (!$childPersonnel.val()) {
        $warningMsg = $childPersonnel.next();
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("아이들 수를 입력해 주세요.");
        setTimeout(() => {
            $childPersonnel.focus();
        }, 0)
        return;
    }
    /*은행*/
    if (!$bank.val()) {
        $warningMsg = $bank.closest(".inputCos").next();
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("은행을 선택해 주세요.");
        setTimeout(() => {
            $('#bankFocus').focus();
        }, 0)
        return;
    }
    /*계좌번호*/
    if (!$accountNumber.val()) {
        $warningMsg = $accountNumber.next();
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("계좌번호를 입력해 주세요.");
        setTimeout(() => {
            $accountNumber.focus();
        }, 0)
        return;
    }
    /*소개글 제목*/
    if (!$title.val()) {
        $warningMsg = $title.next();
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("소개글 제목을 입력해 주세요.");
        setTimeout(() => {
            $title.focus();
        }, 0)
        return;
    }
    /*소개글 내용*/
    if (!$content.val()) {
        $warningMsg = $content.closest(".relative").next();
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("내용을 작성해 주세요.");
        setTimeout(() => {
            $content.focus();
        }, 0)
        return;
    }
    return true;
}

const $submitBtn = $('.submitBtn');

$submitBtn.on('click', function (e) {
    if (checkSubmit() && nullCheck()) {
        updateForm.submit();
    }
})
// $submitBtn.on('click', function (e) {
//     e.preventDefault();
//     setTimeout(() => {
//         if (nullCheck()) {
//             if (checkSubmit()) {
//                 updateForm.submit();
//             }
//         }
//     }, 500);
// })

