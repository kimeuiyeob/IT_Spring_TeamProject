/*----------------------------이메일 유효성 검사----------------------------*/
const $email = $('#email');
let emailFlag = false;
let $warningMsg;
const existingEmail = $email.val();

function email_check(email) {
    var regex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    return (email != '' && email != 'undefined' && regex.test(email));
}
$email.on("keyup", function () {
    $joinBtn.attr("disabled", existingEmail == $email.val())
})

$email.on('blur', function () {
    var email = $(this).val();
    $warningMsg = $(this).next();
    $warningMsg.hide();
    if (email == '' || email == 'undefined') {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('이메일을 입력해 주세요');
        emailFlag = false;
        return;
    }
    if (!email_check(email)) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('이메일 형식이 유효하지 않습니다.');
        emailFlag = false;
        return false;
    }

    // $warningMsg.show();
    // $warningMsg.find(".warningMsg").css("color", "rgb(79 189 18)");
    // $warningMsg.find(".warningMsg").text('사용 가능한 이메일입니다.');
    checkEmail($email.val(), duplicated)
    emailFlag = true;
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


/*------------------------비밀번호 안썻을 때와 유효성 검사----------------------*/
const $password = $('#password');
const $checkPassword = $('#checkPassword');
let tempPw = "";
let pwOk = false;

$password.on("blur", function () {
    //8자리 이상, 대문자/소문자/숫자/특수문자 모두 포함되어 있는 지 검사
    var pwCheck = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,30}$/;
    //한글이 포함되었는 지 검사
    var hangleCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
    //같은 문자 4번 이상
    var wordCheck = /(\w)\1\1\1/;
    //공백검사
    var spaceCheck = /\s/;

    $warningMsg = $(this).next();
    $warningMsg.find('.warningMsg').css("font-size", "0.875rem");

    if (!$password.val()) {
        $warningMsg.show();
        $warningMsg.css("color", "rgb(255, 64, 43)");
        $warningMsg.find('.warningMsg').text("비밀번호를 입력해주세요.");
        pwOk = false;
        return;
    } else {
        $warningMsg.hide();
        $warningMsg.find('.warningMsg').text("");
    }

    if (!pwCheck.test($password.val())) {
        $warningMsg.show();
        $warningMsg.css("color", "rgb(255, 64, 43)");
        $warningMsg.find('.warningMsg').css("font-size", "0.825rem");
        $warningMsg.find('.warningMsg').text("비밀번호는 영문, 숫자, 특수문자가 조합된 8자 ~ 16만 가능합니다.");
        pwOk = false;
        return;
    } else {
        $warningMsg.hide();
        $warningMsg.find('.warningMsg').text("");
    }

    if (spaceCheck.test($password.val())) {
        $warningMsg.show();
        $warningMsg.css("color", "rgb(255, 64, 43)");
        $warningMsg.find('.warningMsg').text("비밀번호는 공백을 포함할 수 없습니다.");
        pwOk = false;
        return;
    } else {
        $warningMsg.hide();
        $warningMsg.find('.warningMsg').text("");
    }
    joinSubmit();
    pwOk = true;
    tempPw = $password.val();
});

/*----------------------------비밀번호 확인----------------------------*/
let passwordCheckFlag = false;

$checkPassword.on("click", function () {
    if (!pwOk) {
        $password.focus();
    }
})

$checkPassword.on("blur", function () {
    passwordCheckFlag = false;
    $warningMsg = $(this).next()
    $warningMsg.find('.warningMsg').css("color", "rgb(255, 64, 43)");
    if (!tempPw) {
        $password.focus();
        return;
    }
    if (!$(this).val()) {
        $warningMsg.show()
        $warningMsg.find('.warningMsg').text("비밀번호를 다시 한 번 입력해 주세요.");
    } else {
        if ($(this).val() == tempPw) {
            $warningMsg.show()
            $warningMsg.find('.warningMsg').css("color", "rgb(79 202 10)");
            $warningMsg.find('.warningMsg').text("비밀번호가 일치합니다.");
            passwordCheckFlag = true;
            joinSubmit();
        } else {
            $warningMsg.show()
            $warningMsg.find('.warningMsg').text("비밀번호가 일치하지 않습니다.");
        }
    }
});

/*----------------------------이름 유효성 검사----------------------------*/
const $name = $('#name');
let nameCheckFlag = false;

var nameCheck = /^[가-힣]{2,15}$/;

$name.on('blur', function () {
    nameCheckFlag = false;
    if (!$name.val()) {
        $name.next().show();
        $name.next().find('.warningMsg').text("이름을 입력해 주세요.");
        return;
    }
    if (!nameCheck.test($name.val())) {
        $name.next().show();
        $name.next().find('.warningMsg').text("이름을 정확히 입력해 주세요.");
    } else {
        $name.next().hide();
        $name.next().find('.warningMsg').text("");
        nameCheckFlag = true;
        joinSubmit();
    }
})

/*----------------------------닉네임 유효성 검사----------------------------*/
const $nickName = $('#nickName');
let nickNameCheckFlag = false;

$nickName.on('blur', function () {
    nickNameCheckFlag = false;
    /*특수문자 포함되었는지 검사*/
    var nickNameCheck = /[#?!@$%^&*-]/;
    /*자음만 쓰였는지 검사*/
    var nickNameConsonantsCheck = /[ㄱ-ㅎ]/;
    /*공백검사*/
    var nickNameSpaceCheck = /\s/;
    $warningMsg = $nickName.next();
    if (!$nickName.val()) {
        $warningMsg.show();
        $warningMsg.find('.warningMsg').text("닉네임을 입력해 주세요");
        return;
    }
    if (nickNameCheck.test($nickName.val())) {
        $warningMsg.show();
        $warningMsg.find('.warningMsg').text("닉네임은 특수문자, 공백를 포함할 수 없습니다.");
        return;
    }
    if (nickNameSpaceCheck.test($nickName.val())) {
        $warningMsg.show();
        $warningMsg.find('.warningMsg').text("닉네임은 특수문자, 공백를 포함할 수 없습니다.");
        return;
    }
    if (nickNameConsonantsCheck.test($nickName.val())) {
        $warningMsg.show();
        $warningMsg.find('.warningMsg').text("닉네임은 자음을 단독으로  사용할 수 없습니다.");
        return;
    }
    nickNameCheckFlag = true;
    $warningMsg.hide();
    $warningMsg.find('.warningMsg').text("");
    joinSubmit();
})

/*----------------------------전화번호 유효성 검사----------------------------*/
const $phone = $('#phone');
const $certificationBtn = $('.certificationBtn');
const $certification = $('#certification');
let phoneFlag = false;
let phoneCheckFlag = false;
let tempPhone;

var phoneCheck = /^[0-9]{11,11}$/;

$certificationBtn.on('click', function () {
    $certification.attr("disabled", true);
    var phone = $(this).prev().val();
    phone = phone.replace(/-/g, "");
    $(this).prev().val(phone);
    $warningMsg = $(this).parent().next();
    if (phone == '' || phone == 'undefined') {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('전화번호를 입력해 주세요');
        $phone.focus();
        phoneFlag = false;
        return;
    }
    if (!phoneCheck.test(phone) || !phone.startsWith("010")) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('전화번호를 정확히 입력해 주세요');
        $phone.focus();
        phoneFlag = false;
        return false;
    }
});
// let allConditionClear = passwordCheckFlag && nameCheckFlag && nickNameCheckFlag && $allCheckboxFlag;
//
// function joinSubmit() {
//     allConditionClear = emailFlag && passwordCheckFlag && nameCheckFlag && nickNameCheckFlag && $allCheckboxFlag;
//     if (allConditionClear) {
//         $joinBtn.attr('disabled', false);
//     } else {
//         $joinBtn.attr('disabled', true);
//     }
// }

/*---------------------------- 가입완료 버튼 활성화----------------------------*/
let allConditionClear = nameCheckFlag && nickNameCheckFlag && $allCheckboxFlag;

function joinSubmit() {
    allConditionClear = emailFlag  && nameCheckFlag && nickNameCheckFlag && $allCheckboxFlag;
    if (allConditionClear) {
        $joinBtn.attr('disabled', false);
    } else {
        $joinBtn.attr('disabled', true);
    }
}

/*-----------------체크박스--------------*/
let $allCheckbox = $('.allCheckbox');
let $allCheckboxFlag = false;
let $smallCheckbox = $('.smallCheckbox');
const $joinBtn = $('.joinBtn');


$allCheckbox.on('click', function () {
    $allCheckboxFlag = false;
    $smallCheckbox.prop('checked', $(this).is(':checked'));
    if ($allCheckbox.is(':checked')) {
        $allCheckboxFlag = true;
        joinSubmit();
    }
})

$smallCheckbox.on('click', function () {
    $allCheckboxFlag = false;
    if (!$(this).is(':checked')) {
        $allCheckbox.prop('checked', false);
        $joinBtn.attr('disabled', true);
    }
    if ($smallCheckbox.filter(':checked').length == $smallCheckbox.length) {
        $allCheckboxFlag = true;
        joinSubmit();
    }
})