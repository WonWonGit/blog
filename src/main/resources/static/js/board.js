
let index = {
    init: function (){
        $('#btnSave').on("click",()=>{
            this.check();
        });
        $('#btnDelete').on("click",()=>{
            this.deleteById();
        });
        $('#btnUpdate').on("click",()=>{
            this.update();
        });
        $('#btnReplySave').on("click",()=>{
            this.replySave();
        });
    },
    check:function (){
        let title = $("#title").val();
        let content = $("#content").val();
        if(title!==""||content!==""){
            this.save();
        }else {
            alert('빈칸이 있습니다');
        }
    },
    save:function (){
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "POST",
            url:"/api/board",
            data: JSON.stringify(data),
            contentType:"application/json; charset=utr-8",
            dataType: "json",
        }).done(function (resp){
            alert("글쓰기 완료");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        }); //ajax통신을 이용해서 파라미터 json으로 변경해서 insert요청
    },
    update:function (){
        let id = $('#id').val();
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "PUT",
            url:"/api/board/"+id,
            data: JSON.stringify(data),
            contentType:"application/json; charset=utr-8",
            dataType: "json",
        }).done(function (resp){
            alert("수정 완료");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        }); //ajax통신을 이용해서 파라미터 json으로 변경해서 insert요청
    },
    deleteById:function (){
        let id= $("#id").text();

        $.ajax({
            type: "DELETE",
            url:"/api/board/"+id,
            dataType: "json",
        }).done(function (resp){
            alert("삭제 완료");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
         }); //ajax통신을 이용해서 파라미터 json으로 변경해서 insert요청
     },
    replySave:function (){
        let data = {
            boardId: $("#boardId").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "POST",
            url:"/api/board",
            data: JSON.stringify(data),
            contentType:"application/json; charset=utr-8",
            dataType: "json",
        }).done(function (resp){
            alert("글쓰기 완료");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        }); //ajax통신을 이용해서 파라미터 json으로 변경해서 insert요청
    },
}

index.init();