// 변경할 비밀번호 유효성검사
document.updatePasswordFrm.addEventListener('submit', (e) => {
    const frm = e.target;
    const newPassword = frm.newPassword;
    const newPasswordConfirmation = frm.newPasswordConfirmation;

    // 비밀번호 - 영문자/숫자/특수문자 !@#$% 4글자이상
    const regExps  = [
        {
            re: /[A-Za-z]/,
            msg: '비밀번호는 영문자를 하나이상 포함해주세요.'
        },
        {
            re: /\d/,
            msg: '비밀번호는 숫자를 하나이상 포함해주세요.'
        },
        {
            re: /[!@#$%]/,
            msg: '비밀번호는 특수문자 !@#$% 중에 하나를  포함해주세요.'
        },
        {
            re: /^.{4,}$/,
            msg: '비밀번호는 4글자이상 작성해주세요'
        }
    ];
    for (let i = 0; i < regExps.length; i++) {
        const {re, msg} = regExps[i];
        if (!re.test(newPassword.value)) {
            alert(msg);
            e.preventDefault(); // 이벤트처리를 막아줌
            return;
        }
        // 변경할비밀번호 일치여부 확인
        if (newPassword.value !== newPasswordConfirmation.value) {
            alert('두 비밀번호가 다릅니다.')
            e.preventDefault();
            return;
        }
    }
});
