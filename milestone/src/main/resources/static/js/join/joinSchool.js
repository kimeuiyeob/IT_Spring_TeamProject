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
let emailFlag = false;
let $warningMsg;

function email_check(email) {
    var regex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    return (email != '' && email != 'undefined' && regex.test(email));
}

$email.on('blur', function () {
    var email = $(this).val();
    $warningMsg = $(this).next();
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

    $warningMsg.show();
    $warningMsg.find(".warningMsg").css("color", "rgb(79 189 18)");
    $warningMsg.find(".warningMsg").text('사용 가능한 이메일입니다.');
    emailFlag = true;
});
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
const $address = $('#address');
const existingAddress = $address.val();
let addressCheckFlag = false;

/*보육원 상세 주소*/
const $addressDetail = $('#addressDetail');
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

$accountNumber.on("keyup", function () {
    let accountNumber = $accountNumber.val();
    accountNumber = $accountNumber.val().replace(/[^0-9]/g, '');
    $accountNumber.val(accountNumber);
})

$accountNumber.on('click', function () {
    if(!$bank.val()){
        $('#bankFocus').focus();
    }
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