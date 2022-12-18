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
const $submitButton = $('#submitButton');
const $password = $('#password');
let pwCheckFlag = false;
const $changePassword = $('#changePassword')
let changePwCheckFlag = false;
const $checkPassword = $('#checkPassword')
let checkPwCheckFlag = false;
let tempPw = "";

//8자리 이상, 대문자/소문자/숫자/특수문자 모두 포함되어 있는 지 검사
var pwCheck = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$/;
//공백검사
var spaceCheck = /\s/;

$password.on('blur', function () {
    const checkPassword = $('#password').val();
    if (!$password.val()) {
        $password.focus();
        $password.next().show();
    }
    if ($password.val().length < 8) {
        $password.next().show();
        $password.next().text("8자 이상 입력해 주세요.");
    } else {
        $.ajax({
            type: 'GET',
            url: '/myPageRest/checkPassword',
            data: {'userPassword': checkPassword},
            datatype: "text"
        }).done(function (result) {
            if (result) {
                $password.next().text("비밀번호가 일치합니다.");
                $password.next().css("color", "blue");
                console.log("비밀번호 일치");
                pwCheckFlag = true;
            } else {
                console.log("비밀번호 틀림");
                $password.next().show();
                $password.next().text("비밀번호가 맞지 않습니다.");
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
});


$changePassword.on('blur', function () {
    if (!pwCheck.test($changePassword.val())) {
        if (!$changePassword.val()) {
            console.log("들어옴");
            $changePassword.next().show();
            changePwCheckFlag = false;
        }
        if ($changePassword.val().length < 8) {
            $changePassword.next().show();
            $changePassword.next().text("8자 이상 입력해 주세요.");
            $changePassword.next().css("color", "red");
            changePwCheckFlag = false;
        }
        return;
    }
    if (spaceCheck.test($changePassword.val())) {
        return;
    }
    console.log("$changePassword 떠남")
    tempPw = $changePassword.val();
    $changePassword.next().show();
    $changePassword.next().text("안전한 비밀번호 입니다");
    $changePassword.next().css("color", "blue");
    changePwCheckFlag = true;

    console.log(tempPw);
    console.log(changePwCheckFlag);
})

$checkPassword.on('blur', function () {
        if (!pwCheck.test($checkPassword.val())) {
            if (!$checkPassword.val()) {
                $checkPassword.next().show();
                $checkPassword.next().text("비밀번호를 입력해 주세요");
                $checkPassword.next().css("color", "red");
                checkPwCheckFlag = false;
            }
            if ($checkPassword.val().length < 8) {
                $checkPassword.next().show();
                $checkPassword.next().text("8자 이상 입력해 주세요.");
                $checkPassword.next().css("color", "red");
                checkPwCheckFlag = false;
            }
            return;
        }
        if ($checkPassword.val() == tempPw) {
            console.log("일치");
            $checkPassword.next().show();
            $checkPassword.next().text("비밀번호가 일치합니다");
            $checkPassword.next().css("color", "blue");
            checkPwCheckFlag = true;
        } else {
            console.log("불일치");
            $checkPassword.next().show();
            $checkPassword.next().text("비밀번호가 맞지 않습니다");
            $checkPassword.next().css("color", "red");
            checkPwCheckFlag = false;
        }
        console.log("체크패스워드" + checkPwCheckFlag);
        console.log("체인지패스워드" + changePwCheckFlag);
        console.log("패스워드" + pwCheckFlag);
        if (pwCheckFlag == true && changePwCheckFlag == true && checkPwCheckFlag == true) {
            $submitButton.attr("disabled", false);
            console.log("활성화");
        } else {
            $submitButton.attr("disabled", true);
            console.log("비활성화");
        }
    }
)
$submitButton.on('click', function () {

    if (!$checkPassword.val()) {
        $checkPassword.focus();
        $checkPassword.next().show();
    }
    if (!$changePassword.val()) {
        $changePassword.focus();
        $changePassword.next().show();
    }
    // if (!$password.val()) {
    //     $password.focus();
    //     $password.next().show();
    // }
    // if ($password.val().length < 8) {
    //     $password.next().show();
    //     $password.next().text("8자 이상 입력해 주세요.")
    // } else if (existingPw != $password.val()) {
    //     $password.next().show();
    //     $password.next().text("비밀번호가 맞지 않습니다.")
    // }
});
$password.on('keyup', function () {
    $password.next().show();
    if ($password.val().length < 8) {
        $password.next().text("8자 이상 입력해 주세요.");
        return;
    } else {
        $password.next().text('\u00A0');
    }
    if ($checkPassword != $password.val()) {
        $password.next().text("")
        return;
    }
    $password.next().hide();
});
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
});
$checkPassword.on('keyup', function () {
    $checkPassword.next().show();

    if (tempPw == $checkPassword.val()) {
        console.log("emfdjdha")
    } else {
        $checkPassword.next().text("비밀번호를 확인해 주세요.")
    }
});


console.log($submitButton);
console.log(changePwCheckFlag)
console.log(checkPwCheckFlag)
console.log(pwCheckFlag)
if (changePwCheckFlag && checkPwCheckFlag && pwCheckFlag) {

    $submitButton.submit();
}

