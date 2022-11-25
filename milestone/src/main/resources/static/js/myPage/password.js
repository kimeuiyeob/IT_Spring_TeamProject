// /*------------------------비밀번호 안썻을 때와 유효성 검사----------------------*/
// const $password = $('#password');
// const $checkPassword = $('#checkPassword');
// let tempPw = "";
// let pwOk = false;
//
// $password.on("blur", function () {
//     //8자리 이상, 대문자/소문자/숫자/특수문자 모두 포함되어 있는 지 검사
//     var pwCheck = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$/;
//     //공백검사
//     var spaceCheck = /\s/;
//
//     $warningMsg = $(this).next();
//     $warningMsg.find('.warningMsg').css("font-size", "0.875rem");
//     $warningMsg.show();
//     $warningMsg.css("color", "rgb(255, 64, 43)");
//     pwOk = false;
//
//     if (!$password.val()) {
//         $warningMsg.find('.warningMsg').text("비밀번호를 입력해주세요.");
//         return;
//     }
//
//     if (!pwCheck.test($password.val())) {
//         $warningMsg.find('.warningMsg').css("font-size", "0.825rem");
//         $warningMsg.find('.warningMsg').text("비밀번호는 영문, 숫자, 특수문자가 조합된 8자 ~ 16만 가능합니다.");
//         return;
//     }
//
//     if (spaceCheck.test($password.val())) {
//         $warningMsg.find('.warningMsg').text("비밀번호는 공백을 포함할 수 없습니다.");
//         return;
//     }
//     $warningMsg.find('.warningMsg').text("");
//     $warningMsg.hide();
//     pwOk = true;
//     tempPw = $password.val();
// });
//
// /*----------------------------비밀번호 확인----------------------------*/
// let passwordCheckFlag = false;
//
// $checkPassword.on("click", function () {
//     if (!pwOk) {
//         $password.focus();
//     }
// })
//
// $checkPassword.on("blur", function () {
//     passwordCheckFlag = false;
//     $warningMsg = $(this).next()
//     $warningMsg.find('.warningMsg').css("color", "rgb(255, 64, 43)");
//     $warningMsg.show()
//     if (!$(this).val()) {
//         $warningMsg.find('.warningMsg').text("비밀번호를 다시 한 번 입력해 주세요.");
//     } else {
//         if ($(this).val() == tempPw) {
//             $warningMsg.find('.warningMsg').css("color", "rgb(79 202 10)");
//             $warningMsg.find('.warningMsg').text("비밀번호가 일치합니다.");
//             passwordCheckFlag = true;
//         } else {
//             $warningMsg.find('.warningMsg').text("비밀번호가 일치하지 않습니다.");
//         }
//     }
// });

/*-----------------------------------------------------------------*/
const $submitBtn = $('.submitBtn');
const $password = $('#password');
let pwCheckFlag = false;
const $changePassword = $('#changePassword')
let changePwCheckFlag = false;
const $checkPassword = $('#checkPassword')
let checkPwCheckFlag = false;
let tempPw = "";
const existingPw = "qwer1234!";

//8자리 이상, 대문자/소문자/숫자/특수문자 모두 포함되어 있는 지 검사
var pwCheck = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$/;
//공백검사
var spaceCheck = /\s/;

$password.on('blur', function () {
    pwCheckFlag = existingPw == $password.val();
})

$changePassword.on('blur', function () {
    if (!pwCheck.test($changePassword.val())) {
        return;
    }
    if (spaceCheck.test($changePassword.val())) {
        return;
    }
    tempPw = $changePassword.val();
    changePwCheckFlag = true;
})

$checkPassword.on('blur', function () {
    if ($checkPassword) {
        checkPwCheckFlag = tempPw == $checkPassword.val();
    }
})

$submitBtn.on('click', function () {

    if (!$checkPassword.val()) {
        $checkPassword.focus();
        $checkPassword.next().show();
    }
    if (!$changePassword.val()) {
        $changePassword.focus();
        $changePassword.next().show();
    }
    if (!$password.val()) {
        $password.focus();
        $password.next().show();
    }
    if ($password.val().length < 8) {
        $password.next().show();
        $password.next().text("8자 이상 입력해 주세요.")
    } else if (existingPw != $password.val()) {
        $password.next().show();
        $password.next().text("비밀번호가 맞지 않습니다.")
    }

    $password.on('keyup', function () {
        $password.next().show();
        if ($password.val().length < 8) {
            $password.next().text("8자 이상 입력해 주세요.")
            return;
        }
        if (existingPw != $password.val()) {
            $password.next().text("")
            return;
        }
        $password.next().hide();
    })
    $changePassword.on('keyup', function () {
        $changePassword.next().show();
        if (!pwCheck.test($changePassword.val())) {
            return;
        }
        if (spaceCheck.test($changePassword.val())) {
            return;
        }
        tempPw = $changePassword.val();
        $changePassword.next().hide();
    })
    $checkPassword.on('keyup', function () {
        $checkPassword.next().show();

        if (tempPw == $checkPassword.val()) {
            console.log("emfdjdha")
            $checkPassword.next().hide();
        } else {
            $checkPassword.next().text("비밀번호를 확인해 주세요.")
        }
    })
    console.log(changePwCheckFlag)
    console.log(checkPwCheckFlag)
    console.log(pwCheckFlag)
    if (changePwCheckFlag && checkPwCheckFlag && pwCheckFlag) {
        updatePassword.submit();
    }
})
