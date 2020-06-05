$(document).ready(function () {
    $('.modal').modal();
});


let money;
const savingData = document.querySelector("#saving-data")
document.querySelector("#saving-btn").addEventListener("click", () => {
    money = Math.floor(Math.random() * 200) * 100;
    savingData.innerHTML = "存入" + money + "元";
    $('#saving-modal').modal('open');
})
document.querySelector("#saving-confirm").addEventListener("click", () => {
    save(money);
    $('#receipt-modal').modal('open');
})
const save = async (money) => {
    const response = await fetch(`/save?money=${money}`);
    if (response.status != 200) {
        errorHeader.innerHTML = "服务器错误";
        errorContent.innerHTML = "请检查网络连接";
        $('#error-modal').modal('open');
    }
}


let withdrawMoney = 0;
const withdrawInput = $("#withdraw");
const withdrawData = document.querySelector("#withdraw-data")
const errorHeader = document.querySelector("#error-header");
const errorContent = document.querySelector("#error-content");
document.querySelector("#withdraw-btn").addEventListener("click", () => {
    if (withdrawMoney > 50000){
        Materialize.toast('单日取出超过50000', 4000);
        return;
    }

    if (withdrawInput.val() <= 0){
        Materialize.toast('金额要为正整数', 4000);
        return;
    }
    if (withdrawInput.val()%100!=0){
        Materialize.toast('金额只能为整百', 4000);
        return;
    }
    if (withdrawInput.val() >10000){
        Materialize.toast('单次取出限额10000元', 4000);
        return;
    }
    if (withdrawInput.val().length == 0) {
        Materialize.toast('请输入取出金额', 4000);
        return;
    }
    money = withdrawInput.val();
    withdrawData.innerHTML = "取出" + withdrawInput.val() + "元";
    $('#withdraw-modal').modal('open');
})
document.querySelector("#withdraw-confirm").addEventListener("click", () => {
    withdraw(money);
})
const withdraw = async (money) => {
    withdrawMoney += money;
    const response = await fetch(`/withdraw?money=${money}`);
    if (response.status != 200) {
        errorHeader.innerHTML = "服务器错误";
        errorContent.innerHTML = "请检查网络连接";
        $('#error-modal').modal('open');
    }
    const data = response.json();
    if (data == 0) {
        console.log(data);
        $('#receipt-modal').modal('open');
    } else if (data == 1) {
        errorHeader.innerHTML = "余额不足";
        errorContent.innerHTML = "请检查余额";
        $('#error-modal').modal('open');
    }
}
const warning = (header, content) => {
    errorHeader.innerHTML = header;
    errorContent.innerHTML = content;
    $('#error-modal').modal('open');
}


const idInput = $("#id");
const moneyInput = $("#money");
const transferData = document.querySelector("#transfer-data");
document.querySelector("#transfer-btn").addEventListener("click", () => {
    if (idInput.val().length != 16) {
        Materialize.toast('卡号不足16位', 4000);
        return;
    }
    if (idInput.val()== "1234567890123456") {
        Materialize.toast('不能转账给自己', 4000);
        return;
    }
    if (moneyInput.val().length == 0) {
        Materialize.toast('请输入金额', 4000);
        return;
    }
    if (moneyInput.val() == 0) {
        Materialize.toast('金额不能为0', 4000);
        return;
    }
    transferData.innerHTML = "将向卡号" + idInput.val() + "转账" + moneyInput.val() + "元";
    $('#transfer-modal').modal('open');
})

document.querySelector("#transfer-confirm").addEventListener("click", () => {
    // const idMd5 = $.md5(idInput.val());
    const id = idInput.val();
    const idMd5 = btoa(id);
    const money = moneyInput.val();
    console.log(id, idMd5, money)
    transfer(idMd5, money);
})

const transfer = async (id, money) => {
    const response = await fetch(`/transfer?id=${id}&money=${money}`);
    if (response.status !== 200) {
        errorHeader.innerHTML = "服务器错误";
        errorContent.innerHTML = "请检查网络连接";
        $('#error-modal').modal('open');
    }
    const data = await response.json();
    if (data === 0) {
        console.log(data);
        $('#receipt-modal').modal('open');
    } else if (data === 1) {
        errorHeader.innerHTML = "余额不足";
        errorContent.innerHTML = "请检查余额";
        $('#error-modal').modal('open');
    } else if (data === 2) {
        errorHeader.innerHTML = "对方账户不存在";
        errorContent.innerHTML = "请检查卡号";
        $('#error-modal').modal('open');
    }
}