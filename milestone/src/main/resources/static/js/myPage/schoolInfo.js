const $submitBtn = $('.submitBtn');
/*--------------------------다음 우편 api------------------------------*/
/*-----------------------------------보육원 주소-----------------------------------*/
const $address = $('#schoolAddress');
const existingAddress = $address.val();

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
            $("input[name='schoolZipcode']").val(data.zonecode);
            $("input[name='schoolAddress']").val(addr);
            // 커서를 상세주소 필드로 이동한다.
            $("input[name='schoolAddressDetail']")[0].focus();
            $('#searchAddressBtn').parent().next().hide();
            $submitBtn.attr("disabled", existingAddress == addr);
        }
    }).open();
}

/* -----------------------은행 목록 드롭다운----------------------- */
const $moreSelect = $('div.inputCos');
const $moreSelectList = $('div.moreSelectWrap');
const $moreSelectItems = $('div.moreSelectItem');
const $bank = $('#schoolBank');
const existingBank = $bank.val();

$moreSelectItems.on('click', function () {
    $bank.css("color", '#303441');
    $bank.val($(this).text());
    $moreSelectList.hide();
    $warningMsg = $bank.closest(".inputCos").next();
    $submitBtn.attr("disabled", existingBank == $bank.val())

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
const $content = $('#schoolContent');
const $textareaCos = $('.textareaCos');
const $contentLength = $('.contentLength');
const maxContent = 500;
const existingContent = $content.val();

$content.focusin(function () {
    $textareaCos.css("border", "1px solid rgb(118, 118, 118)")
})
$content.focusout(function () {
    $textareaCos.css("border", "1px solid rgb(228, 229, 237)")
    if($content.val()){
        $content.closest('.relative').next().hide();
    }
})
$contentLength.text($content.val().length)
$content.keyup(function () {
    $contentLength.text($content.val().length)

    $submitBtn.attr("disabled", existingContent == $content.val())
    if ($content.val().length > maxContent) {
        $content.val($content.val().substring(0, maxContent));
        $contentLength.text(maxContent);
    }
})


/*----------------------사진 추가 썸네일---------------------------*/
// const $fileTest = $(`#schoolImg`);
// const $thumbnail = $(`.profile`);
//
// $fileTest.on('change', function (e) {
//     var reader = new FileReader();
//     let text = "";
//     reader.readAsDataURL(e.target.files[0]);
//     reader.onload = function (e) {
//         let url = e.target.result;
//         text += `<div class = "imgsWrap">`;
//         if (url.includes('image')) {
//             text += `<img src="` + url + `" width="136" height="100">`;
//             // $thumbnail.css('background-image', "url('" + url + "')");
//         } else {
//             text += `<img src="/imgs/fix/normalProfile.png" width="136" height="100">`;
//             // $thumbnail.css('background-image', "url('imgs/fix/normalProfile.png')");
//         }
//         text += `</div>`;
//         $('.imgList').append(text);
//         if ($('.imgsWrap').length > 0) {
//             $('.imgBox').show();
//         } else {
//             $('.imgBox').hide();
//         }
//     }
//
// })

/*-----------------------------------유효성 검사-------------------------------*/
/*----------------------------이메일 유효성 검사----------------------------*/
const $email = $('#userEmail');
let emailCheckFlag = false;
let $warningMsg;
const existingEmail = $email.val();

function email_check(email) {
    var regex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    return (email != '' && email != 'undefined' && regex.test(email));
}

$email.on("keyup", function () {
    $submitBtn.attr("disabled", existingEmail == $email.val())
    // $warningMsg = $(this).next();
    // if (existingEmail == $email.val()) {
    //     $warningMsg.hide();
    // }
})

$email.on('blur', function () {
    var email = $(this).val();
    $warningMsg = $(this).next();
    $warningMsg.hide();
    if (existingEmail == $email.val()) return;

    if (email == '' || email == 'undefined') {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('이메일을 입력해 주세요');
        emailCheckFlag = false;
        return;
    }
    if (!email_check(email)) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('이메일 형식이 유효하지 않습니다.');
        emailCheckFlag = false;
        return false;
    }
    checkEmail($email.val(), duplicated)
    emailCheckFlag = true;
});

function checkEmail(userEmail, callback){
    $.ajax({
        url: "/myPageRest/checkEmail",
        type: "post",
        data: userEmail,
        contentType: "application/json; charset=utf-8",
        success: function(userId){
            console.log(userId)
            callback(userId)
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    })
}

// 이메일 중복일 경우 실행할 메소드
function duplicated (userId){
    console.log(userId)
    if(userId){
        $warningMsg.show();
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('사용 중인 이메일 입니다.');
        emailCheckFlag = false;
    }
}

/*-----------------------------------보육원 이름-------------------------------*/
const $schoolName = $('#schoolName');
const existingSchoolName = $schoolName.val();

$schoolName.on('keyup', function () {
    $submitBtn.attr("disabled", existingSchoolName == $schoolName.val())
})

$schoolName.on('blur', function () {
    if (!$schoolName.val()) {
        $schoolName.next().show();
    } else {
        $schoolName.next().hide();
    }
})

/*-----------------------------------보육원 상세 주소-----------------------------------*/
const $addressDetail = $('#schoolAddressDetail');
const existingAddressDetail = $addressDetail.val();

$addressDetail.on('click', function () {
    $warningMsg = $(this).prev().prev();
    if (!$address.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("주소 검색 버튼을 눌러주세요")
    }
})

$addressDetail.on('keyup', function () {
    $submitBtn.attr("disabled", existingAddressDetail == $addressDetail.val())
})

$addressDetail.on('blur', function () {
    $warningMsg = $(this).next();
    if (!$addressDetail.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("상세주소를 입력해 주세요. 상세주소가 없다면 주소지의 특징을 입력해 주세요.")
    } else {
        $warningMsg.hide();
    }
})

/*-----------------------------------이름-----------------------------------*/

const $name = $('#userName');
var nameCheck = /^[가-힣]{2,15}$/;
const existingName = $name.val();
let nameCheckFlag = false;

$name.on('keyup', function () {
    $submitBtn.attr("disabled", existingName == $name.val())
})

$name.on('blur', function () {
    nameCheckFlag = false;
    $warningMsg = $(this).next();
    if (!$name.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("이름을 입력해 주세요.")
    } else if (!nameCheck.test($name.val())) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("이름을 정확히 입력해 주세요.")
    } else {
        nameCheckFlag = true;
        $warningMsg.hide();
    }
})

/*-----------------------------------연락처-----------------------------------*/
const $phone = $('#userPhoneNumber');
const $certificationBtn = $('.certificationBtn');
const $certification = $('#certification');
let phoneFlag = false;
let tempPhone;
const existingPhone = $phone.val().replace(/-/g, "");
let phoneCheckFlag = false;
let certificationNumber;

const phoneCheck = /^[0-9]{11,11}$/;

let phone;

$phone.on('keyup', function () {
    $submitBtn.attr("disabled", existingPhone == $phone.val())
})

$phone.on('keyup', function () {
    var phone = $(this).val();
    phone = phone.replace(/-/g, "");
    phoneFlag = false;
    $warningMsg = $(this).parent().next();
    $nextWarningMsg = $(this).parent().next().next().next();
    if (existingPhone != phone) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('전화번호가 변경 되었습니다. 인증을 다시 받아주세요.');
    } else {
        $warningMsg.hide();
        phoneFlag = true;
    }
    if (tempPhone && !(phone == tempPhone)) {
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('전화번호가 변경 되었습니다. 인증을 다시 받아주세요.');
        tempPhone = "";
        $certification.val("");
        $nextWarningMsg.hide();
        $nextWarningMsg.prev().val("");
    }
});

$certificationBtn.on('click', function () {
    var phone = $(this).prev().val();
    phone = phone.replace(/-/g, "");
    $certification.attr("disabled", true);
    $warningMsg = $(this).parent().next();
    $(this).prev().val(phone);
    if (!phoneCheck.test(phone) || !phone.startsWith("010")) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('전화번호를 정확히 입력해 주세요.');
        $phone.focus();
        phoneFlag = false;
        return false;
    } else {
        $warningMsg.show();
        if (existingPhone != $phone.val()) {
            console.log($phone.val())
            getCertificationNumber($phone.val());
            console.log(certificationNumber);
            $warningMsg.find(".warningMsg").css("color", "rgb(79 189 18)");
            $warningMsg.find(".warningMsg").text('입력하신 전화번호로 인증번호가 전송되었습니다.');

        } else {
            $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
            $warningMsg.find(".warningMsg").text('변동사항이 없습니다.');
            return;
        }
        tempPhone = phone;
        $certification.attr("disabled", false);
        $certification.focus();
        phoneFlag = true;
    }
});

function getCertificationNumber(userPhoneNumber){
    console.log(userPhoneNumber)
    $.ajax({
        url: "/myPageRest/phoneCheck?phone=" + userPhoneNumber,
        type: "get",
        cache: false,
        success: function(data){
            certificationNumber = data;
        }
    })
}

/*인증번호*/
$certification.on('blur', function () {
    console.log(certificationNumber)
    console.log($certification.val())
    phoneCheckFlag = false;
    if (phoneFlag) {
        $warningMsg = $(this).next();
        if (!$certification.val()) {
            $warningMsg.show();
            $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
            $warningMsg.find(".warningMsg").text("인증번호를 입력해 주세요")
        } else if (certificationNumber == $certification.val()) {
            $warningMsg.closest(".flexRow").show();
            phoneCheckFlag = true;
            $warningMsg.find(".warningMsg").css("color", "rgb(79 189 18)");
            $warningMsg.find(".warningMsg").text("인증번호가 일치합니다.")

        } else {
            $warningMsg.show();
            $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
            $warningMsg.find(".warningMsg").text("인증번호가 일치하지 않습니다.")
        }
    }
})

/*-------------------------추가 연락처--------------------------*/
const $plusPhone = $('#schoolPhoneNumber');
const existingPlusPhone = $('#schoolPhoneNumber').val()
let plusPhoneCheckFlag = false;

var plusPhoneCheck = /^[0-9]{7,}$/;

$plusPhone.on('keyup', function () {
    $submitBtn.attr('disabled', existingPlusPhone == $plusPhone.val());
})

$plusPhone.on('blur', function () {
    plusPhoneCheckFlag = false;
    var plusPhone = $(this).val();
    plusPhone = plusPhone.replace(/-/g, "");
    $(this).val(plusPhone);
    $warningMsg = $(this).next();
    $warningMsg.show();
    $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
    if (plusPhone == '' || plusPhone == 'undefined') {
        $warningMsg.find(".warningMsg").text('전화번호를 입력해 주세요');
        return;
    }
    if (!plusPhoneCheck.test(plusPhone)) {
        $warningMsg.find(".warningMsg").text('전화번호를 정확히 입력해 주세요');
        return false;
    } else {
        $warningMsg.hide();
        plusPhoneCheckFlag = true;
    }
})

/*-----------------------------------보육원 교사 수-----------------------------------*/
const $teacherPersonnel = $("#schoolTeachers");
const existingTeacherPersonnel = $teacherPersonnel.val();

$teacherPersonnel.on("keyup", function () {
    $submitBtn.attr("disabled", existingTeacherPersonnel == $teacherPersonnel.val())
})

$teacherPersonnel.on("blur", function () {
    let teacherPersonnel = $teacherPersonnel.val();
    teacherPersonnel = $teacherPersonnel.val().replace(/[^0-9]/g, '');
    $teacherPersonnel.val(teacherPersonnel);
})

/*-----------------------------------보육원 아이들 수-----------------------------------*/
const $childPersonnel = $("#schoolKids");
const existingChildPersonnel = $childPersonnel.val();
let childPersonnel = false;

$childPersonnel.on("keyup", function () {
    let childPersonnel = $childPersonnel.val();
    childPersonnel = $childPersonnel.val().replace(/[^0-9]/g, '');
    $childPersonnel.val(childPersonnel);
    $submitBtn.attr("disabled", existingChildPersonnel == $childPersonnel.val())
})

$childPersonnel.on('blur', function () {
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
})

/*-----------------------------------1년 예산-----------------------------------*/
const $budget = $("#schoolBudget");
const existingBudget = $budget.val();

$budget.on("keyup", function () {
    let budget = $budget.val();
    budget = $budget.val().replace(/[^0-9]/g, '');
    $budget.val(budget);
    $submitBtn.attr("disabled", existingBudget == $budget.val())
})

/*-----------------------------------계좌번호-----------------------------------*/
const $accountNumber = $("#schoolAccount");
const existingAccountNumber = $accountNumber.val();

$accountNumber.on("keyup", function () {
    let accountNumber = $accountNumber.val();
    accountNumber = $accountNumber.val().replace(/[^0-9]/g, '');
    $accountNumber.val(accountNumber);
    $submitBtn.attr("disabled", existingAccountNumber == $accountNumber.val())
})

$accountNumber.on('blur', function () {
    $warningMsg = $(this).next();
    if (!$accountNumber.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("계좌번호를 입력해 주세요.");
        $accountNumber.val("");
    } else {
        $warningMsg.hide();
    }
})

/*-----------------------------------소개글-----------------------------------*/
//제목
const $title = $('#schoolTitle');
const existingTitle = $title.val();

$title.on('keyup', function () {
    $submitBtn.attr("disabled", existingTitle == $title.val())
})

$title.on('blur', function () {
    $warningMsg = $(this).next();
    if (!$title.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("소개글 제목을 입력해 주세요.");
    } else {
        $warningMsg.hide();
    }
})

//소개글
// const $content = $('#content');
// const existingContent = $content.val();
//
// $content.on('keyup', function () {
//     $submitBtn.attr("disabled", existingTitle == $title.val())
// })
//
// $title.on('blur', function () {
//     $warningMsg = $(this).next();
//     if (!$title.val()) {
//         $warningMsg.show();
//         $warningMsg.find(".warningMsg").text("소개글 제목을 입력해 주세요.");
//     } else {
//         $warningMsg.hide();
//     }
// })



$submitBtn.on('click', function () {
//    회원가입에서 받았으니 널일 수 가 없음(변경 됐는지만 확인하면 됨)
//    이메일
    if (existingEmail != $email.val()) {
        if (!emailCheckFlag) {
            $email.focus();
            $warningMsg = $email.next();
            $warningMsg.show();
            $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
            return;
        }
    }
//    이름
    if (existingName != $name.val()) {
        if (!nameCheckFlag) {
            $name.focus();
            $warningMsg = $name.next();
            $warningMsg.show();
            return;
        }
    }
//    보육원 이름
    if (!$schoolName.val()) {
        $schoolName.focus();
        $schoolName.next().show();
        return;
    }
//    상세주소
    if (!$addressDetail.val()) {
        $addressDetail.focus();
        $addressDetail.next().show();
        return;
    }
//    휴대폰
    if (existingPhone != $phone.val()) {
        if (!phoneCheckFlag) {
            $phone.parent().next().show();
            $phone.parent().next().find(".warningMsg").css("color", "rgb(255, 64, 43)");
            $phone.parent().next().find(".warningMsg").text('전화번호가 변경 되었습니다. 인증을 다시 받아주세요.');
            $phone.focus();
            return;
        }
    }
//    추가 연락처
    if(existingPlusPhone != $plusPhone.val()){
        if(!plusPhoneCheckFlag){
            $plusPhone.focus();
            return;
        }
    }
//    아이들 수
    if (!$childPersonnel.val()) {
        $childPersonnel.focus();
        $childPersonnel.next().show();
        return;
    }
//    은행
//    계좌번호
    if (!$accountNumber.val()) {
        $accountNumber.focus();
        $accountNumber.next().show();
        return;
    }
    
//    소개 제목
    if (!$title.val()) {
        $title.focus();
        $title.next().show();
        return;
    }
    
//    소개 내용
    if (!$content.val()) {
        $content.focus();
        $content.closest('.relative').next().show();
        return;
    }

    $.ajax({
        url: "/file/delete",
        type: "post",
        data: {uploadPath: uploadPaths, fileName: fileNames},
        success: function () {
            // $imgsWrap.remove();
            arrayFile.splice(i, 1);
            const dataTransfer = new DataTransfer();
            arrayFile.forEach(file => dataTransfer.items.add(file));
            $("input[type='file']")[1].files = dataTransfer.files;
        }
    });
    updateForm.submit();
})

/*-============================황지수 ajax=======================*/
function showSchoolImg(callback){
    $.ajax({
        url: "/file/schoolImg",
        type: "get",
        contentType: false,
        processData: false,
        success: function(data){
            callback(data);
        }
    })

}