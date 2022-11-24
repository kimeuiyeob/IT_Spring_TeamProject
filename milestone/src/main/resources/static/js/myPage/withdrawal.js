const $precautionCheck = $('.precautionCheck');
const $withdrawalBtn = $('.withdrawalBtn');
const $reasonRadio = $('input[name = "reasonRadio"]');
const $reasonInput = $('.reasonInputWrap');

console.log($precautionCheck);

$precautionCheck.on('click',function(){
    $withdrawalBtn.attr("disabled",!$precautionCheck.is(':checked'));
    // if($precautionCheck.is(':checked')){
    //     // $withdrawalBtn.css({"background-color":"#fff","color":"#303441","cursor":"pointer"})
    //     $withdrawalBtn.attr("disabled",false);
    // }else{
    //     // $withdrawalBtn.css({"background-color":"rgb(228, 229, 237)","color":"rgb(154, 155, 167)","cursor":"no-drop"})
    //     $withdrawalBtn.attr("disabled",true);
    // }

})

$reasonRadio.on('click',function(){
    if($reasonRadio.last().is(':checked')){
        $reasonInput.show();
    }else{
        $reasonInput.hide();
    }

})