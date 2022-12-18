const $submitBtn = $('.submitBtn');

/*----------------------------이메일 유효성 검사----------------------------*/
const $email = $('#email');
let emailCheckFlag = false;
let $warningMsg;
const existingEmail = $email.val();

function email_check(email) {
    var regex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    return (email != '' && email != 'undefined' && regex.test(email));
}

$email.on("keyup", function () {
    $submitBtn.attr("disabled", existingEmail == $email.val())
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

    // $warningMsg.show();
    // $warningMsg.find(".warningMsg").css("color", "rgb(79 189 18)");
    // $warningMsg.find(".warningMsg").text('사용 가능한 이메일입니다.');
    checkEmail($email.val(), duplicated)
    emailCheckFlag = true;
});

/*----------------------------이름 유효성 검사----------------------------*/
const $name = $('#name');
const existingName = $name.val();
let nameCheckFlag = false;

var nameCheck = /^[가-힣]{2,15}$/;

$name.on('keyup', function () {
    $warningMsg = $(this).next();
    if (existingName == $name.val()) {
        $warningMsg.hide();
    }
    joinSubmit();
})

$name.on('blur', function () {
    nameCheckFlag = false;
    if (!$name.val()) return;
    if (!nameCheck.test($name.val())) {
        $name.next().show();
        $name.next().find('.warningMsg').text("이름을 정확히 입력해 주세요.");
    } else {
        $name.next().hide();
        $name.next().find('.warningMsg').text("");
        nameCheckFlag = true;
    }
    joinSubmit();
})
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

/*----------------------------닉네임 유효성 검사----------------------------*/
const $nickName = $('#nickName');
const existingNickName = $nickName.val();
let nickNameCheckFlag = false;

$nickName.on('keyup', function () {
    $warningMsg = $(this).next();
    joinSubmit();
    if (existingNickName == $nickName.val()) {
        $warningMsg.hide();
    }
})

$nickName.on('blur', function () {
    nickNameCheckFlag = false;
    /*특수문자 포함되었는지 검사*/
    var nickNameCheck = /[#?!@$%^&*-]/;
    /*자음만 쓰였는지 검사*/
    var nickNameConsonantsCheck = /[ㄱ-ㅎ]/;
    /*공백검사*/
    var nickNameSpaceCheck = /\s/;

    $warningMsg = $nickName.next();
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
const existingPhone = $phone.val().replace(/-/g, "");
const $certificationBtn = $('.certificationBtn');
const $certification = $('#certification');
let phoneFlag = false;
let phoneCheckFlag = false;
let tempPhone;

var phoneCheck = /^[0-9]{11,11}$/;
var code = "";


$certificationBtn.on('click', function () {
    joinSubmit();
    $certification.attr("disabled", true);
    var phone = $(this).prev().val();
    phone = phone.replace(/-/g, "");
    $warningMsg = $(this).parent().next();
    $(this).prev().val(phone);
    console.log(phone);
    $.ajax({
        type: "get",
        url: "phoneCheck?phone=" + phone,
        cache: false,
        success: function (data) {
            if (data == "error" || !phoneCheck.test(phone) || !phone.startsWith("010")) {
                $warningMsg.show();
                $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
                $warningMsg.find(".warningMsg").text('전화번호를 정확히 입력해 주세요.');
                $phone.focus();
                phoneFlag = false;
                return false;
            } else {
                $warningMsg.show();
                $warningMsg.find(".warningMsg").css("color", "rgb(79 189 18)");
                $warningMsg.find(".warningMsg").text('입력하신 전화번호로 인증번호가 전송되었습니다.');
                tempPhone = phone;
                $certification.attr("disabled", false)
                $submitBtn.attr("disabled", false);
                $certification.focus();
                phoneFlag = true;
            }
            code = data;
        }
    });
});

$phone.on('keyup', function () {
    joinSubmit();
    var phone = $(this).val();
    phone = phone.replace(/-/g, "");
    $warningMsg = $(this).parent().next();
    $nextWarningMsg = $(this).parent().next()
    if (existingPhone != phone) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('전화번호가 변경 되었습니다. 인증을 다시 받아주세요.');
        $certificationBtn.attr("disabled", false)
    } else {
        $warningMsg.hide();
        $certificationBtn.attr("disabled", true)
    }
    if (tempPhone && !(phone == tempPhone)) {
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('전화번호가 변경 되었습니다. 인증을 다시 받아주세요.');
        phoneFlag = false;
        tempPhone = "";
        $nextWarningMsg.hide();
        $nextWarningMsg.prev().val("");
        $certification.attr("disabled", true);
    }
});
/*인증번호*/
$certification.on('blur', function () {
    joinSubmit();
    $certification.attr("disabled", false);
    phoneCheckFlag = false;
    if (phoneFlag) {
        $warningMsg = $(this).next();
        if (!$certification.val()) {
            $warningMsg.show();
            $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
            $warningMsg.find(".warningMsg").text("인증번호를 입력해 주세요")
        } else if (code == $certification.val()) {
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


/*수정하기 최종확인*/
let joinSubmitCheck = false;

function joinSubmit() {
    /*나의 정보중 바뀐정보가 있다면*/
    /*수정하기 버튼 활성화*/
    if (existingName != $name.val() || existingNickName != $nickName.val() || existingEmail != $email.val() || existingPhone != $phone.val().replace(/-/g, "")) {
        $submitBtn.attr("disabled", false)
    } else {
        $submitBtn.attr("disabled", true)
    }

    /*정보가 바뀌었고 검사가 통과했을 때에는 무시*/
    /*이메일*/
    if (existingEmail != $email.val()) {
        if (!(emailCheckFlag)) {
            $email.focus();
            return;
        }
    }
    /*이름*/
    if (existingName != $name.val()) {
        if (!(nameCheckFlag)) {
            $name.focus();
            return;
        }
    }
    /*닉네임*/
    if (existingNickName != $nickName.val()) {
        if (!(nickNameCheckFlag)) {
            $nickName.focus();
            return;
        }
    }
    /*휴대전화*/
    if (existingPhone != $phone.val().replace(/-/g, "")) {
        if (!(phoneCheckFlag)) {
            $phone.focus();
            return;
        }
    }

    return true;
}

$submitBtn.on('click', function (e) {
    e.preventDefault();
    if (joinSubmit()) {
        updateForm.submit();
    }
})