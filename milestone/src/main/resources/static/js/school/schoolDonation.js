$(".orderButton").hover(function(){
    $(this).css({"background-color":"#ca0"})
},function(){
    $(this).css({"background-color":"#ffd400"})
})


/* 숫자 콤마(,) 찍고 바로 출력 */
const amountInput = document.querySelector('.amountInput');

amountInput.addEventListener('keyup', function(e) {
    let value = e.target.value;
    value = Number(value.replaceAll(',', ''));
    if(isNaN(value)) {
        amountInput.value = 0;
    }else {
        if(value<5000000000){
            const formatValue = value.toLocaleString('ko-KR');
            amountInput.value = formatValue;
            printAmount();
        }else{
            alert('500,000,000원 까지 입력가능합니다.');
            amountInput.value = 0;
            printAmount();
        }
    }
})

function printAmount(){
    var amount = document.getElementById('amountInputMoney').value;
    console.log(amount);
    document.getElementById("totalAmountMoney").innerText = amount;
}
