const submitBtn = document.querySelector("#submit-btn");
submitBtn.addEventListener("click", () => {
    submit();
});
const oldInput = $("#old");
const newPwdInput = $("#newPwd");
const repeatInput = $("#repeat");
function submit() {
    if (check()) {
        let old = oldInput.val();
        let newPwd = newPwdInput.val();
        //old = $.md5(id);
        old = btoa(old)
        //newPwd = $.md5(pwd);
        newPwd = btoa(newPwd)
        console.log(old, newPwd);
        changePassword(old, newPwd);
    }
}

const check = () => {
    if (oldInput.val().length != 6) {
        Materialize.toast('旧密码长度应为6位', 4000);
        return false;
    }
    if (newPwdInput.val().length != 6) {
        Materialize.toast('新密码长度应为6位', 4000);
        return false;
    }
    if (newPwdInput.val() != repeatInput.val()) {
        Materialize.toast('重复密码与新密码不一致', 4000);
        return false;
    }
    return true;
}
const changePassword = async (old, newPwd) => {
    const response = await fetch(`/change?old=${old}&newPwd=${newPwd}`);
    if(response.status !== 200) Materialize.toast('网络错误', 4000);
    const data = await response.json();
    if (data === 0) {
        Materialize.toast('修改成功', 4000);
    } else if (data === 1){
        Materialize.toast('用户不存在', 4000);
    } else if (data === 2) {
        Materialize.toast('原密码错误', 4000);
    } else {
        Materialize.toast('服务器错误', 4000);
    }
}