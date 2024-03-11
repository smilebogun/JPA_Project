
let adminPostObject = {

    init: function(){
        // let _this = this;

        $("#register").on("click", () =>{
            // _this.insertUser();
            this.insertAdminPost(); // this = userObject
        });

        $("#update").on("click", () => {
            // _this.insertUser();
            this.updateAdminPost(); // this = userObject
        });
        $("#post-del").on("click", () => {
            // _this.insertUser();
            this.deleteAdminPost(); // this = userObject
        });
    },
    deleteAdminPost:function (){
        alert("공지사항 삭제 요청");
        let post = {
            id:$("#bno").val()
        };
        console.log(post.id)
        if(confirm("정말로 삭제 하시겠습니까?")) {
            $.ajax({
                url:"/admin/post/delPost",
                type:"DELETE",       // POST도 가능
                data:JSON.stringify(post),
                contentType: "application/json; charset=utf-8",
            }).done(function (response){
                let warning = ""
                // let errors = response.data;
                // if(errors.title != null)warning = warning + errors.title + "\n";
                // if(errors.content != null)warning = warning + errors.content;
                //alert(response.data);
                // alert("삭제 완료")
                // location = "/admin/post";
                if(response.status === 200){
                    alert(response.data)
                    location = "/admin/post";
                } else {
                    console.log(warning);
                    alert(response.data);
                }
            }).fail(function (error){
                alert("에러발생 = " + error);
            });
        } else {
            return false;
        }

    },
    insertAdminPost: function (){
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
        console.log(data.keys())
        $.ajax({
            url: "/admin/post/registerPost",
            contentType: false, //
            processData: false,
            async:false,
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
                location = "/admin/post";
            }else {
                alert(response.data)
            }

        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },

        updateAdminPost:function (){
            alert("포스트 수정 요청");
            let data = new FormData();

            let image = $("#image");
            let files = image[0].files;

            let po= {postTitle : $("#title").val(),
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
           /* alert("data="+data)*/


            // done() : 성공 처리되었을 때
            // fail() : 실패했을 때
            $.ajax({
                url: "/admin/post/update",
                contentType: false, //
                async:false,
                processData: false,
                data: data,
                type: "post"
            }).done(function (response) {
                let warning = "";

                let errors = response.data;
                if(errors.postTitle != null)warning = warning + errors.postTitle + "\n";
                if(errors.postContent != null)warning = warning + errors.postContent;

                if(response.status === 200){
                    alert(response.data);
                    location.pathname = "/admin/post";
                }else {
                    alert(warning)
                }
            }).fail(function(error){
                alert("에러 발생 : " + error);
            });
        }
}

adminPostObject.init();