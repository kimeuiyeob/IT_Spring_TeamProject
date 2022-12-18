console.log("반갑스비낭")

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
            $("input[name='schoolZipcode']").val(data.zonecode);
            $("input[name='schoolAddress']").val(addr);
            // 커서를 상세주소 필드로 이동한다.
            $("input[name='schoolAddressDetail']")[0].focus();
            addressCheckFlag = true;
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

/*---------------------------------유효성 검사-------------------------------*/
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
    var pwCheck = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$/;
    //공백검사
    var spaceCheck = /\s/;

    $warningMsg = $(this).next();
    $warningMsg.find('.warningMsg').css("font-size", "0.875rem");
    $warningMsg.show();
    $warningMsg.css("color", "rgb(255, 64, 43)");
    pwOk = false;

    if (!$password.val()) {
        $warningMsg.find('.warningMsg').text("비밀번호를 입력해주세요.");
        return;
    }

    if (!pwCheck.test($password.val())) {
        $warningMsg.find('.warningMsg').css("font-size", "0.825rem");
        $warningMsg.find('.warningMsg').text("비밀번호는 영문, 숫자, 특수문자가 조합된 8자 ~ 16만 가능합니다.");
        return;
    }

    if (spaceCheck.test($password.val())) {
        $warningMsg.find('.warningMsg').text("비밀번호는 공백을 포함할 수 없습니다.");
        return;
    }
    $warningMsg.find('.warningMsg').text("");
    $warningMsg.hide();
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
    $warningMsg.show()
    if (!$(this).val()) {
        $warningMsg.find('.warningMsg').text("비밀번호를 다시 한 번 입력해 주세요.");
    } else {
        if ($(this).val() == tempPw) {
            $warningMsg.find('.warningMsg').css("color", "rgb(79 202 10)");
            $warningMsg.find('.warningMsg').text("비밀번호가 일치합니다.");
            passwordCheckFlag = true;
        } else {
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
    $name.next().show();
    if (!$name.val()) {
        $name.next().find('.warningMsg').text("이름을 입력해 주세요.");
        return;
    }
    if (!nameCheck.test($name.val())) {
        $name.next().find('.warningMsg').text("이름을 정확히 입력해 주세요.");
    } else {
        $name.next().hide();
        nameCheckFlag = true;
    }
})

/*-------------------------보육원 이름--------------------------*/
const $schoolName = $('#schoolName');
let schoolNameCheckFlag = false;

$schoolName.on('blur', function () {
    schoolNameCheckFlag = false;
    $schoolName.next().show();
    if (!$schoolName.val()) {
        $schoolName.next().find('.warningMsg').text("보육원 이름을 입력해 주세요.");
    } else {
        schoolNameCheckFlag = true;
        $schoolName.next().hide();
    }
})

/*-------------------------보육원 주소--------------------------*/
/*보육원 주소*/
const $address = $('#schoolAddress');
const existingAddress = $address.val();
let addressCheckFlag = false;

/*보육원 상세 주소*/
const $addressDetail = $('#schoolAddressDetail');
const existingAddressDetail = $addressDetail.val();
let addressDetailCheckFlag = false;

$addressDetail.on('click', function () {
    $warningMsg = $(this).parent().prev().find(".warningMsg").parent();
    if (!$address.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("주소 검색 버튼을 눌러주세요")
    }
})

$addressDetail.on('blur', function () {
    $warningMsg = $(this).next();
    if (!$addressDetail.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("상세주소가 없다면 주소지의 특징을 입력해 주세요.")
    } else {
        addressDetailCheckFlag = true;
        $warningMsg.hide();
    }
})

/*-------------------------보육원 교사 수--------------------------*/
const $teacherPersonnel = $('#teacherPersonnel');

$teacherPersonnel.on('keyup', function () {
    let teacherPersonnel = $teacherPersonnel.val();
    teacherPersonnel = $teacherPersonnel.val().replace(/[^0-9]/g, '');
    $teacherPersonnel.val(teacherPersonnel);
})

/*-------------------------보육원 아이들 수--------------------------*/
const $childPersonnel = $("#childPersonnel");
const existingChildPersonnel = $childPersonnel.val();
let childPersonnelCheckFlag = false;

$childPersonnel.on("keyup", function () {
    let childPersonnel = $childPersonnel.val();
    childPersonnel = $childPersonnel.val().replace(/[^0-9]/g, '');
    $childPersonnel.val(childPersonnel);
})

$childPersonnel.on('blur', function () {
    childPersonnelCheckFlag = false;
    $warningMsg = $(this).next();
    if (!$childPersonnel.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("아이들 수를 입력해 주세요.");
        $childPersonnel.val("");
    } else {
        childPersonnelCheckFlag = true;
        $warningMsg.hide();
    }
})

/*-------------------------1년 예산--------------------------*/
const $budget = $("#budget");

$budget.on("keyup", function () {
    let budget = $budget.val();
    budget = $budget.val().replace(/[^0-9]/g, '');
    $budget.val(budget);
})

/*-------------------------계좌번호--------------------------*/
const $accountNumber = $("#accountNumber");
let accountNumberCheckFlag = false;

$accountNumber.on("keyup", function () {
    let accountNumber = $accountNumber.val();
    accountNumber = $accountNumber.val().replace(/[^0-9]/g, '');
    $accountNumber.val(accountNumber);
})

$accountNumber.on('click', function () {
    if (!$bank.val()) {
        $('#bankFocus').focus();
    }
})

$accountNumber.on('blur', function () {
    accountNumberCheckFlag = false;
    $warningMsg = $(this).next();
    if (!$accountNumber.val()) {
        $warningMsg.show();
        $warningMsg.find(".warningMsg").text("계좌번호를 입력해 주세요.");
        $accountNumber.val("");
    } else {
        accountNumberCheckFlag = true;
        $warningMsg.hide();
    }
})

/*-------------------------핸드폰--------------------------*/
const $phone = $('#phone');
const $certificationBtn = $('.certificationBtn');
const $certification = $('#certification');
let phoneFlag = false;
let phoneCheckFlag = false;
let tempPhone;

var phoneCheck = /^[0-9]{11,11}$/;
var code = "";

$certificationBtn.on('click', function () {
    $certification.attr("disabled", true);
    var userPhoneNumber = $(this).prev().val();
    console.log(userPhoneNumber);
    userPhoneNumber = userPhoneNumber.replace(/-/g, "");
    $(this).prev().val(userPhoneNumber);
    $warningMsg = $(this).parent().next();
    $.ajax({
        type: "get",
        url: "phoneCheck?phone=" + userPhoneNumber,
        cache: false,
        success: function (data) {
            $warningMsg.show();
            $warningMsg.find(".warningMsg").css("color", "rgb(79 189 18)");
            $warningMsg.find(".warningMsg").text('입력하신 전화번호로 인증번호가 전송되었습니다.');
            tempPhone = userPhoneNumber;
            $certification.attr("disabled", false)
            $certification.focus();
            phoneFlag = true;
            if (data == "error" || userPhoneNumber == '' || userPhoneNumber == 'undefined') {
                $warningMsg.show();
                $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
                $warningMsg.find(".warningMsg").text('전화번호를 입력해 주세요');
                $phone.focus();
                phoneFlag = false;
                return;
            }
            if (data == "error" || !phoneCheck.test(userPhoneNumber) || !userPhoneNumber.startsWith("010")) {
                $warningMsg.show();
                $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
                $warningMsg.find(".warningMsg").text('전화번호를 정확히 입력해 주세요');
                $phone.focus();
                phoneFlag = false;
                return false;
            }
            code = data;
        }
    });
});

$phone.on('blur', function () {
    var phone = $(this).val();
    phone = phone.replace(/-/g, "");
    $warningMsg = $(this).parent().next();
    $nextWarningMsg = $(this).closest('.inputWrap').next().find('.warningMsg').parent()
    if (tempPhone && !(phone == tempPhone)) {
        $warningMsg.find(".warningMsg").css("color", "rgb(255, 64, 43)");
        $warningMsg.find(".warningMsg").text('전화번호가 변경 되었습니다 인증을 다시 받아주세요');
        phoneFlag = false;
        tempPhone = "";
        $nextWarningMsg.hide();
        $nextWarningMsg.prev().val("");
        $certification.attr("disabled", true);
    }
});

/*인증번호*/
$certification.on('blur', function () {
    phoneCheckFlag = false;
    if (phoneFlag) {
        $warningMsg = $(this).closest(".inputWrap").find(".warningMsg");
        if (!$certification.val()) {
            $warningMsg.closest(".flexRow").show();
            $warningMsg.css("color", "rgb(255, 64, 43)");
            $warningMsg.text("인증번호를 입력해 주세요")
        } else if (code == $certification.val()) {
            $warningMsg.closest(".flexRow").show();
            phoneCheckFlag = true;
            $warningMsg.css("color", "rgb(79 189 18)");
            $warningMsg.text("인증번호가 일치합니다.")
            joinSubmit();
        } else {
            $warningMsg.closest(".flexRow").show();
            $warningMsg.css("color", "rgb(255, 64, 43)");
            $warningMsg.text("인증번호가 일치하지 않습니다.")
        }
    }
})

/*-------------------------추가 연락처--------------------------*/
const $plusPhone = $('#plusPhone');

let plusPhoneCheckFlag = false;

var plusPhoneCheck = /^[0-9]{7,}$/;

$plusPhone.on('blur', function () {
    plusPhoneCheckFlag = false;
    var plusPhone = $(this).val();
    plusPhone = plusPhone.replace(/-/g, "");
    $(this).val(plusPhone);
    $warningMsg = $(this).parent().next();
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

/*-------------------------체크 박스--------------------------*/
let $allCheckbox = $('.allCheckbox');
let allCheckboxFlag = false;
let $smallCheckbox = $('.smallCheckbox');


$allCheckbox.on('click', function () {
    $smallCheckbox.prop('checked', $(this).is(':checked'));
    allCheckboxFlag = $allCheckbox.is(':checked');
})

$smallCheckbox.on('click', function () {
    $allCheckbox.prop('checked', $smallCheckbox.filter(':checked').length == $smallCheckbox.length);
    allCheckboxFlag = $smallCheckbox.filter(':checked').length == $smallCheckbox.length;
})

/*-------------------------가입 버튼 활성화--------------------------*/
const $joinBtn = $('.joinBtn');

$joinBtn.on('click', function () {
    if (!$email.val() || !emailCheckFlag) {
        setTimeout(() => {
            $email.focus();
        }, 100)
        $email.next().show();
        return;
    }
    if (!$password.val() || !pwOk) {
        setTimeout(() => {
            $password.focus();
        }, 100)
        $password.next().show();
        return;
    }
    if (!$checkPassword.val() || !passwordCheckFlag) {
        setTimeout(() => {
            $checkPassword.focus();
        }, 100)
        $checkPassword.next().show();
        return;
    }
    if (!$name.val() || !nameCheckFlag) {
        setTimeout(() => {
            $name.focus();
        }, 100)
        $name.next().show();
        return;
    }
    if (!$schoolName.val() || !schoolNameCheckFlag) {
        setTimeout(() => {
            $schoolName.focus();
        }, 100)
        $schoolName.next().show();
        return;
    }
    if (!$address.val() || !addressCheckFlag) {
        setTimeout(() => {
            $('#addressFocus').focus();
        }, 100)
        $address.parent().next().show();
        return;
    }
    if (!$addressDetail.val() || !addressDetailCheckFlag) {
        setTimeout(() => {
            $addressDetail.focus();
        }, 100)
        $addressDetail.next().show();
        return;
    }
    if (!$childPersonnel.val() || !childPersonnelCheckFlag) {
        setTimeout(() => {
            $childPersonnel.focus();
        }, 100)
        $childPersonnel.next().show();
        return;
    }
    if (!$bank.val()) {
        setTimeout(() => {
            $('#bankFocus').focus();
        }, 100)
        $bank.parent().parent().next().show();
        return;
    }
    if (!$accountNumber.val() || !accountNumberCheckFlag) {
        setTimeout(() => {
            $accountNumber.focus();
        }, 100)
        $accountNumber.next().show();
        return;
    }
    if ($phone.val() && !phoneFlag) {
        setTimeout(() => {
            $phone.focus();
        }, 100)
        $phone.parent().next().find('.warningMsg').text("인증번호를 받아주세요")
        $phone.parent().next().show();
        return;
    }
    if (!$phone.val() || !phoneFlag) {
        setTimeout(() => {
            $phone.focus();
        }, 100)
        $phone.parent().next().show();
        return;
    }
    if (!$certification.val() || !phoneCheckFlag) {
        setTimeout(() => {
            $certification.focus();
        }, 100)
        $certification.next().show();
        return;
    }
    if ($plusPhone.val()) {
        if (!plusPhoneCheckFlag) {
            setTimeout(() => {
                $plusPhone.focus();
            }, 100)
            $plusPhone.parent().next().show();
            return;
        }
    }

    if (!allCheckboxFlag) {
        return;
    }

    joinForm.submit();
})