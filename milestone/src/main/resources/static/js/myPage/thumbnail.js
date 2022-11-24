const $file = $(`#profile`);
const $thumbnail = $(`.profile`);

$file.on('change',function(e){
    var reader = new FileReader();

    reader.readAsDataURL(e.target.files[0]);
    reader.onload = function(e){
        let url = e.target.result;
        if(url.includes('image')){
            $thumbnail.css('background-image', "url('" + url + "')");
        }else{
            $thumbnail.css('background-image', "url('imgs/fix/normalProfile.png')");
        }
    }
})