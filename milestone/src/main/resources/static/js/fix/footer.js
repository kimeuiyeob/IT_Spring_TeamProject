let sessionId = $("#sessionId").val();
console.log("sessionId : "+sessionId);
console.log("세션아디 : "+  JSON.stringify($("#sessionId")));

function adminLogin() {
    if(sessionId=="admin"){
        location.href="../admin/user"
    }else if(sessionId =="school" || sessionId =="people"){
        alert('관리자로 로그인 후 이용하실 수 있습니다.')
    }else{
        alert('로그인 후 이용하실 수 있습니다.')
    }
}
