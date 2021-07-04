function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if (!content) {
        alert("error");
        return;
    }
    $.ajax({
        type: "post",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();

            } else {
                if (response.code == 2003) {
                    var b = confirm(response.message);
                    if (b) {
                        window.open("https://github.com/login/oauth/authorize?client_id=c12ba731ee2598f7e0c6&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                } else {
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType: "json"
    })
    console.log(questionId);
    console.log(content);
}