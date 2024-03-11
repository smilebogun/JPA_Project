let SCObject = {
    init: function(){
        // let _this = this;

        $("#btn-register").on("click", () =>{
            this.insertAnnounce();
        });
        $("#btn-update").on("click", () =>{
            this.updateAnnounce();
        });
        $("#sc-del").on("click", () =>{
            this.deleteAnnounce();
        });

    },

    insertAnnounce: function (){
        alert("공지사항 등록")
        //입력 받은 값 받아오기
        let data = new FormData();
        let image = $("#image");
        let files = image[0].files[0];
        let anno=
            {
                scTitle : $("#title").val(),
                scContent : $("#content").val(),
                scCategory : $("#category").text()
            }
        let anounce = JSON.stringify(anno);

        data.append("sc",new Blob([anounce],{ type: 'application/json' }));
        $(files).each(function (i, file) { // 여러 개의 파일을 업로드할 때 formData에 저장
            data.append("data", file);
        });

        $.ajax({
            url: "/admin/registerAnno",
            contentType: false, //
            processData: false,
            data: data,
            type: "post"
        }).done(function (response) {


            let warning = ""

            let errors = response.data;
            if(errors.scTitle != null)warning = warning + errors.scTitle + "\n";
            if(errors.scContent != null)warning = warning + errors.scContent;

            if(response.status!==200){
                alert(warning)
                return;
            }

                alert(response.data);
                location.href = "/admin/sc";
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },

    updateAnnounce:function (){
        alert("포스트 수정 요청");
        let data = new FormData();

        let image = $("#image");
        let files = image[0].files;
        //alert(files)


        let po= {scTitle : $("#title").val(),
            scContent : $("#content").val(),
            scCategory : $("#category").val(),
            id:$("#id").val()
        }
        //alert(po.scContent)
        let SC = JSON.stringify(po);
        //FormData에 입력받은 값 DataForm에 집어 넣기
        data.append("SC",new Blob([SC],{ type: 'application/json' }));
        $(files).each(function (i, file) { // 여러 개의 파일을 업로드할 때 formData에 저장
            data.append("data", file);

        });

        //alert("data="+data)


        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            url: "/admin/sc/update",
            contentType: false, //
            async:false,
            processData: false,
            data: data,
            type: "post"
        }).done(function (response) {
            let warning = ""

            let errors = response.data;
            if(errors.scTitle != null)warning = warning + errors.scTitle + "\n";
            if(errors.scContent != null)warning = warning + errors.scContent;

            if(response.status === 200){
                alert(response.data);
                location.href = "/admin/sc";
            }else {
                alert(response.data)
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
    deleteAnnounce:function (){
        let id = $("#id").val()
        //alert(id)
        if(confirm("정말로 삭제 하시겠습니까?")) {
            $.ajax({
                url:"/admin/sc/del/"+id,
                async:false,
                type:"post"
            }).done(function (response) {
                if(response.status!==200){
                    alert(response.data)
                }else {
                    alert(response.data);
                    location.href = "/admin/sc";
                }
            }).fail(function(error){
                alert("에러 발생 : " + error);
            });
        } else {
            return false;
        }

    }
}

SCObject.init();