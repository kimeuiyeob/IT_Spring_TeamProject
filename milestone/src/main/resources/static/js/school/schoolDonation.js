$(".orderButton").hover(function () {
    $(this).css({"background-color": "#ca0"})
}, function () {
    $(this).css({"background-color": "#ffd400"})
})


/* 숫자 콤마(,) 찍고 바로 출력 */
const amountInput = document.querySelector('.amountInput');

amountInput.addEventListener('keyup', function (e) {
    let value = e.target.value;
    value = Number(value.replaceAll(',', ''));
    if (isNaN(value)) {
        amountInput.value = 0;
    } else {
        if (value < 5000000000) {
            const formatValue = value.toLocaleString('ko-KR');
            amountInput.value = formatValue;
            printAmount();
        } else {
            alert('500,000,000원 까지 입력가능합니다.');
            amountInput.value = 0;
            printAmount();
        }
    }
})

function printAmount() {
    var amount = document.getElementById('amountInputMoney').value;
    console.log(amount);
    document.getElementById("totalAmountMoney").innerText = amount;
}

//---------------------------황지수-----------------------------



function getSchoolInfo(userId, callback, error) {
    $.ajax({
        url: "/schoolrest/info/" + userId,
        type: "get",
        success: function (schoolDTO) {
            callback(schoolDTO)
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    })
}

function giveMoneyCash(moneyCash, callback, error) {
    console.log(moneyCash)
    $.ajax({
        url: "/schoolrest/payment",
        type: "post",
        data: JSON.stringify(moneyCash),
        contentType: "application/json; charset=utf-8",
        success: function (schoolDTO) {
            location.href = "/school/read?userId="+$('#userId').val();
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    })
}


