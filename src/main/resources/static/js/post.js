
let postObject = {

    init: function(){
        // let _this = this;

        $("#register").on("click", () =>{
            // _this.insertUser();
            this.insertPost(); // this = userObject
        });
        $("#temp").on("click", () => {
            // _this.insertUser();
            this.tempPost(); // this = userObject
        });
        $("#update").on("click", () => {
            // _this.insertUser();
            this.updatePost(); // this = userObject
        });
        $("#post-del").on("click", () => {
            // _this.insertUser();
            this.deletePost(); // this = userObject
        });
    },
    deletePost:function (){
        alert("게시글 삭제 요청");
        let post = {
            id:$("#bno").val()
        };
        if(confirm("정말로 삭제 하시겠습니까?")) {
            $.ajax({
                url: "/post/delPost",
                type: "DELETE",     // POST도 가능
                data: JSON.stringify(post),
                contentType: "application/json; charset=utf-8",
            }).done(function (response) {
                let warning = ""

                let errors = response.data;
                if (errors.title != null) warning = warning + errors.title + "\n";
                if (errors.content != null) warning = warning + errors.content;

                if (response.status === 200) {
                    alert(response.data);
                    location = "/post/";
                } else {
                    console.log(warning);
                    alert(response.data);
                }
            }).fail(function (error) {
                alert("에러발생 = " + error);
            });
        } else {
            return false;
        }
    },
    insertPost: function (){
        //입력 받은 값 받아오기
        let data = new FormData();
        let image = $("#image");
        let files = image[0].files;
        let po=
            {postTitle : $("#title").val(),
                postContent : $("#content").val(),
                postCategory : $("#category").val()}
        let post = JSON.stringify(po);
        //FormData에 입력받은 값 DataForm에 집어 넣기
        data.append("post",new Blob([post],{ type: 'application/json' }));
        $(files).each(function (i, file) { // 여러 개의 파일을 업로드할 때 formData에 저장
            data.append("data", file);

        });
        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            url: "/post/registerPost",
            contentType: false, //
            processData: false,
            data: data,
            type: "post"
        }).done(function (response) {
            let warning = ""
            let errors = response.data;
            if(errors.postTitle != null)warning = warning + errors.postTitle + "\n";
            if(errors.postContent != null)warning = warning + errors.postContent;
            //alert("감사합니다")
            //location = '/post';
            if(response.status === 200){
                alert(response.data);
                location = '/post/';
            }else {
                console.log(warning);
                alert(warning);
            }

        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },

    tempPost: function () {
        alert("포스트 등록 요청");
        let data = new FormData();

        let image = $("#image");
        let files = image[0].files;

        let po=
            {postTitle : $("#title").val(),
                postContent : $("#content").val(),
                postCategory : $("#category").val()}
        let post = JSON.stringify(po);
        //FormData에 입력받은 값 DataForm에 집어 넣기
        data.append("post",new Blob([post],{ type: 'application/json' }));
        $(files).each(function (i, file) { // 여러 개의 파일을 업로드할 때 formData에 저장
            data.append("data", file);

        });

        //alert("data="+data)


        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            url: "/post/temp",
            contentType: false, //
            async:false,
            processData: false,
            data: data,
            type: "post"
        }).done(function (response) {
            let warning = ""

            let errors = response.data;
            if(errors.postTitle != null)warning = warning + errors.postTitle + "\n";
            if(errors.postContent != null)warning = warning + errors.postContent;

            if(response.status === 200){
                alert(response.data);
                location = "/post/";
            }else {
                console.log(warning);
                alert(warning);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
    updatePost:function (){
        alert("포스트 수정 요청");
        let data = new FormData();

        let image = $("#image");
        let files = image[0].files;
        //let category =$("#category").val()
        //alert(files)


        let po= {
            postTitle : $("#title").val(),
            postContent : $("#content").val(),
            postCategory : $("#category").val(),
            id:$("#bno").val()
        }

        let post = JSON.stringify(po);
        //FormData에 입력받은 값 DataForm에 집어 넣기
        data.append("post",new Blob([post],{ type: 'application/json' }));
        $(files).each(function (i, file) { // 여러 개의 파일을 업로드할 때 formData에 저장
            data.append("data", file);

        });

        //alert("data="+data)


        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            url: "/post/update",
            contentType: false, //
            async:false,
            processData: false,
            data: data,
            type: "post"
        }).done(function (response) {
            let warning = ""

            let errors = response.data;
            if(errors.postTitle != null)warning = warning + errors.postTitle + "\n";
            if(errors.postContent != null)warning = warning + errors.postContent;
            //alert(response.status)
            if(response.status === 200){
                alert(response.data);
                location = "/post/get/" + po.id;
            }else {
                console.log(warning);
                alert(warning);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    }

}

let cnt = 1;
// accept="image/gif, image/jpeg, image/png" => gif, jpg, png 파일만 선택하도록 설정 속성
function fileAppend(){
    if(cnt <3){
        let fileElement ='<input type="file" class="form-control" id = "image" accept="image/gif, image/jpeg, image/png" onchange="preView(this)" name="file'+cnt+'"><img /><button type="button" class="btn-close border bg-danger" style="display:none; position:relative; left:-24px; top:-30px;" onclick="delInput(this)"></button>';

        $("#div-file").append(fileElement);
        cnt++;
    }else{
        alert("이미지는 최대 3개까지 입력 가능합니다.")
    }

}
function fileRemove(){
    $("#div-file").empty();
    let fileElement ='<input type="file" class="form-control" accept="image/gif, image/jpeg, image/png" onchange="preView(this)" name="file'+cnt+'"><img /><button type="button" class="btn-close border bg-danger" style="display:none; position:relative; left:-24px; top:-30px;" onclick="delInput(this)"></button>';
    $("#div-file").append(fileElement);
    cnt = 1;
}



postObject.init();