let $cancels = $('.items-cancel');
let $modalWrap = $('.modalWrap');
let body = $('body');
let $modalBtn = $('.modalBtn');
let modalWrap = document.querySelector('.modalWrap');



$cancels.on('click',function(){
    $modalWrap.show();
    body.css('overflow','hidden');
})

document.addEventListener('click',function(e){
    if(e.target == modalWrap || e.target == $modalBtn[1]){
        $modalWrap.hide();
        body.css('overflow','auto');
    }
    console.log(e.target);
})
