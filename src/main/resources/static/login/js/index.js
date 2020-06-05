$(document).ready(function () {
    $('.modal').modal();
});

const submitBtn = document.querySelector("#submit-btn");
const idInput = $("#id");
const pwdInput = $("#pwd")
const errorHeader = document.querySelector("#error-header");
const errorContent = document.querySelector("#error-content");
submitBtn.addEventListener("click", () => {
    submit();
});
function submit() {
    if (check()) {
        let id = idInput.val();
        let pwd = pwdInput.val();
        // id = $.md5(id);
        // pwd = $.md5(pwd);
        id = btoa(id);
        pwd = btoa(pwd)
        console.log(id, pwd);
        getLogin(id, pwd);
    }
}

const check = () => {
    if (idInput.val().length != 16) {
        Materialize.toast('卡号长度应为16位', 4000);
        return false;
    }
    if (pwdInput.val().length != 6) {
        Materialize.toast('卡号长度应为6位', 4000);
        return false;
    }
    return true;
}

const getLogin = async (id, pwd) => {
    const response = await fetch(`/login/check?id=${id}&pwd=${pwd}`);
    if (response.status != 200) {
        warning("服务器错误", "请检查网络连接")
    }
    const data = await response.json();
    console.log(data);
    if (data === 0) {
        window.location = "/index";
    } else if (data === 4) {
        warning("卡号不存在", "请检查卡号");
    } else if (data === 3) {
        warning("卡已被锁定", "密码错误达到3次，请联系工作人员");
    } else {
        warning("密码错误", "您已错误" + data + "次，达到三次将会锁卡");
    }
}

const warning = (header, content) => {
    errorHeader.innerHTML = header;
    errorContent.innerHTML = content;
    $('#error-modal').modal('open');
}

