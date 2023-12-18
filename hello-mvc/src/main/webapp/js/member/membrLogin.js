
// 로그인폼 제출시 아이디저장
document.loginFrm.addEventListener('submit', (e) => {
    const checkbox = e.target.saveId;
    const id = e.target.id;
    //아이디저장 체크시
    if(checkbox.checked) {
        // 키,값으로 로컬저장소에 저장
        localStorage.setItem('saveId', id.value);
    }
    else {
        // 아이디저장 체크해제하면 로컬저장소의 해당키 제거
        localStorage.removeItem('saveId');
    }
});
// 다시 로그인 페이지 로드시
// 저장된 로컬저장소의 키를 조회
const checkbox = localStorage.getItem('saveId');
if(checkbox){
    // 로컬저장소 키를 불러와 아이디 input태그에 저장된 값를 담아줌
    document.querySelector('#id').value = checkbox;
    // 아이디 저장후 다시로그인할때 체크박스에 체크가 되도록 true
    document.querySelector('#saveId').checked = true;
}