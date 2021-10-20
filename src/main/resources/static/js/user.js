
let index = {
    init: function (){
        $('#btnSave').on("click",()=>{
            this.check();

        });
        $('#btnUpdate').on("click",()=>{
            this.update();

        });
    },
    check:function (){
        let username = $("#username").val();
        let password = $("#password").val();
        let email = $("#username").val();
        if(username!==""||password!==""|email!==""){
            this.save();
        }else {
            alert('빈칸이 있습니다');
        }
    },
    save: function (){
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        };

        $.ajax({
            type: "POST",
            url:"/auth/joinProc",
            data: JSON.stringify(data),
            contentType:"application/json; charset=utr-8",
            dataType: "json",
        }).done(function (resp){
            alert("회원가입 완료");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
         }); //ajax통신을 이용해서 파라미터 json으로 변경해서 insert요청
     },
    update: function (){
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        };

        $.ajax({
            type: "PUT",
            url:"/user",
            data: JSON.stringify(data),
            contentType:"application/json; charset=utr-8",
            dataType: "json",
        }).done(function (resp){
            alert("회원수정 완료");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        }); //ajax통신을 이용해서 파라미터 json으로 변경해서 insert요청
    }
}

index.init();