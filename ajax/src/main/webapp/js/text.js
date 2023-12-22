/**
 * $.ajax
 *
 */
document.querySelector("#btn1").addEventListener("click", (e) => {
    $.ajax({
        url : `${contextPath}/text`,
        method : "get",
        data : {
            name : "홍길동",
            num : 123
        },
        dataType : "text",
        beforeSend() {
            console.log('beforeSend');
        },
        success(response){
            console.log('success');
            console.log(response);
        },
        error(jqXHR, textStatus, errorThrown) {
            console.log('error');
            console.log(jqXHR, textStatus, errorThrown);
        },
        complete() {
            console.log('complete');
        }

    });
});


$("#studentSearch").autocomplete({
    source(request, callback) {
        // console.log(request);
        // console.log(callback); // 콜백함수

        $.ajax({
            url : `${contextPath}/text/studentSearch`,
            method : 'get',
            data : request,
            dataType : 'text',
            success(response) {
                console.log(response);
                /*
                  15,한보경
                  16,한승훈
                  17,한준희
                  {
                      label : '한보경', // 노출값
                      value : '한보경' // 내부값
                  }
                 */
                if(response) {
                    const temp = response.split("\r\n");
                    const students = temp.map((student) => {
                        const [id, name] = student.split(',');
                        return {
                            label : `${name}(${id})`,
                            value : id
                        };
                    });
                    console.log(students);
                    callback(students); // jqueryui#autocomplete에 전달. 화면자동으로 렌더.
                }
            }
        });
    },
    select(e, selected) {
        console.log(e, selected);
        const {item : {value}} = selected;
        location.href = `${contextPath}/student/studentDetail?id=${value}`;
    }
});