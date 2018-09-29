var currentName = "";
var message_nums = document.getElementsByClassName("message-num");
for (var i = 0; i < message_nums.length; i++) {
    if (parseInt(message_nums[i].innerText) == 0) {
        message_nums[i].style.display = "none";
    }
}
var msgObject = {
    username: $("#usernameDiv").text(),
    msg: '',
    receivename: '',
};
console.log($("#usernameDiv"));
if (window.WebSocket) {
    var websocket = new WebSocket("ws://192.168.0.11:8080");
    websocket.onopen = function (ev) {
        console.log("connect");
        websocket.send(JSON.stringify(msgObject));
    };
    websocket.onclose = function (ev) {
        console.log("close");
    };
    websocket.onmessage = function (ev) {
        ev.content = ev.data;
        ev.addtime = new Date();
        // 判断发来消息的用户是不是当前窗口
        console.log("ppppppppp");
        console.log(ev);
        var o = JSON.parse(ev.data);
        console.log(o.sendname,currentName);
        if (o.sendname == currentName) {
            reviceMsg(o);

        } else {
            console.log("qqqqqq");
            // 提示逻辑
            var peopleDom = document.getElementById(o.sendname);
            var numDom = peopleDom.querySelector(".message-num");
            numDom.style.display = "inline-block";
            numDom.innerText = parseInt(numDom.innerText) + 1;
        }

    }
}

screenFuc();
function screenFuc() {
    var topHeight = $(".chatBox-head").innerHeight();//聊天头部高度
    //屏幕小于768px时候,布局change
    var winWidth = $(window).innerWidth();
    if (winWidth <= 768) {
        var totalHeight = $(window).height(); //页面整体高度
        $(".chatBox-info").css("height", totalHeight - topHeight);
        var infoHeight = $(".chatBox-info").innerHeight();//聊天头部以下高度
        //中间内容高度
        $(".chatBox-content").css("height", infoHeight - 46);
        $(".chatBox-content-demo").css("height", infoHeight - 46);

        $(".chatBox-list").css("height", totalHeight - topHeight);
        $(".chatBox-kuang").css("height", totalHeight - topHeight);
        $(".div-textarea").css("width", winWidth - 106);
    } else {
        $(".chatBox-info").css("height", 495);
        $(".chatBox-content").css("height", 448);
        $(".chatBox-content-demo").css("height", 448);
        $(".chatBox-list").css("height", 495);
        $(".chatBox-kuang").css("height", 495);
        $(".div-textarea").css("width", 260);
    }
}
(window.onresize = function () {
    screenFuc();
})();
//未读信息数量为空时
var totalNum = $(".chat-message-num").html();
if (totalNum == "") {
    $(".chat-message-num").css("padding", 0);
}
$(".message-num").each(function () {
    var wdNum = $(this).html();
    if (wdNum == "") {
        $(this).css("padding", 0);
    }
});


//打开/关闭聊天框
$(".chatBtn").click(function () {
    $(".chatBox").toggle(10);
});
$(".chat-close").click(function () {
    $(".chatBox").toggle(10);
});
//进聊天页面
$(".chat-list-people").each(function () {
    $(this).click(function () {
        var recordVoice = document.getElementById('recordVoice');
        recordVoice.style.display = "block";
        var n = $(this).index();
        $(".chatBox-head-one").toggle();
        $(".chatBox-head-two").toggle();
        $(".chatBox-list").fadeToggle();
        $(".chatBox-kuang").fadeToggle();
        // $().innerText = 0;
        var numDom = this.querySelector(".message-num");
        numDom.innerText = "0";
        numDom.style.display = "none";
        // console.log($(this).children(".message-num"));
        already();
        //传名字
        $(".ChatInfoName").text($(this).children(".chat-name").children("p").eq(0).html());
        currentName = $(".ChatInfoName").text();
        msgObject.receivename = $(".ChatInfoName").text();


        //传头像
        $(".ChatInfoHead>img").attr("src", $(this).children().eq(0).children("img").attr("src"));
        // 清空消息
        $("#chatBox-content-demo").text("");
        // 拉取消息
        pullMsg(msgObject.receivename);
        //聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
    })
});

//返回列表
$(".chat-return").click(function () {
    $(".chatBox-head-one").toggle(1);
    $(".chatBox-head-two").toggle(1);
    $(".chatBox-list").fadeToggle(1);
    $(".chatBox-kuang").fadeToggle(1);
    currentName = " ";
    var recordVoice = document.getElementById('recordVoice');
    recordVoice.style.display = "none";
});

//      发送信息
$("#chat-fasong").click(function () {
    var textContent = $(".div-textarea").html().replace(/[\n\r]/g, '<br>')
    if (textContent != "") {
        $(".chatBox-content-demo").append("<div class=\"clearfloat\">" +
            "<div class=\"author-name\"><small class=\"chat-date\">"+new Date()+"</small> </div> " +
            "<div class=\"right\"> <div class=\"chat-message\"> " + textContent + " </div> " +
            "<div class=\"chat-avatars\"><img src=\"img/icon01.png\" alt=\"头像\" /></div> </div> </div>");
        wsSend(textContent);
        //发送后清空输入框
        $(".div-textarea").html("");
        //聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
    }
});

//      发送表情
$("#chat-biaoqing").click(function () {
    $(".biaoqing-photo").toggle();
});
$(document).click(function () {
    $(".biaoqing-photo").css("display", "none");
});
$("#chat-biaoqing").click(function (event) {
    event.stopPropagation();//阻止事件
});

$(".emoji-picker-image").each(function () {
    $(this).click(function () {
        var bq = $(this).parent().html();
        console.log(bq);
        var time = new Date();
        var timeStr = time.getFullYear() + "年" + time.getMonth() + "月" + time.getDay() + "日" +
            time.getHours() + "点" +
            time.getMinutes() + "分" +
            time.getSeconds() + "秒";
        $(".chatBox-content-demo").append("<div class=\"clearfloat\">" +
            "<div class=\"author-name\"><small class=\"chat-date\">" + timeStr + "</small> </div> " +
            "<div class=\"right\"> <div class=\"chat-message\"> " + bq + " </div> " +
            "<div class=\"chat-avatars\"><img src=\"img/icon01.png\" alt=\"头像\" /></div> </div> </div>");
        //发送后关闭表情框
        $(".biaoqing-photo").toggle();
        //聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
    })
});

//      发送图片
function selectImg(pic) {
    if (!pic.files || !pic.files[0]) {
        return;
    }
    var reader = new FileReader();
    reader.onload = function (evt) {
        var images = evt.target.result;
        $(".chatBox-content-demo").append("<div class=\"clearfloat\">" +
            "<div class=\"author-name\"><small class=\"chat-date\">2017-12-02 14:26:58</small> </div> " +
            "<div class=\"right\"> <div class=\"chat-message\"><img src=" + images + "></div> " +
            "<div class=\"chat-avatars\"><img src=\"img/icon01.png\" alt=\"头像\" /></div> </div> </div>");
        //聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
    };
    reader.readAsDataURL(pic.files[0]);

}
// 收到消息

function reviceMsg(e) {
    var revice = '';
    var ss = e.content.split('/');
    if (ss[0] == "data:audio") {
        revice = " <div class=\"clearfloat\">\n" +
            "                            <div class=\"author-name\">\n" +
            "                                <small class=\"chat-date\">"+e.addtime+"</small>\n" +
            "                            </div>\n" +
            "                            <div class=\"left\">\n" +
            "                                <div class=\"chat-avatars\"><img src=\"img/icon01.png\" alt=\"头像\"/></div>\n" +
            "                                <div class=\"chat-message\">\n" +
            "<audio controls='controls' src='"+e.content+"'></audio>"+
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>";
    } else {
        revice = " <div class=\"clearfloat\">\n" +
            "                            <div class=\"author-name\">\n" +
            "                                <small class=\"chat-date\">"+e.addtime+"</small>\n" +
            "                            </div>\n" +
            "                            <div class=\"left\">\n" +
            "                                <div class=\"chat-avatars\"><img src=\"img/icon01.png\" alt=\"头像\"/></div>\n" +
            "                                <div class=\"chat-message\">\n" +
            "                                    <!--<img src=\"img/1.png\" alt=\"\">-->\n" +
            "                                    \n" +
            e.content
        "                                </div>\n" +
        "                            </div>\n" +
        "                        </div>";
    }

    // if (textContent != "") {
    $(".chatBox-content-demo").append(revice);
    //     //发送后清空输入框
    //     $(".div-textarea").html("");
    //聊天框默认最底部
    $(document).ready(function () {
        $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
    });
}
function wsSend(textContent) {
    msgObject.msg = textContent;
    console.log(msgObject);
    if (websocket.readyState == websocket.OPEN) {
        websocket.send(JSON.stringify(msgObject));
    } else {
        alert("连接尚未建立");
    }
}
function pullMsg(reviceName) {
    var userId = $("#userIdDiv").text();
    $.ajax({
        url: "/ok/getMsgListByName?sendName="+ msgObject.username + "&receiveName=" + reviceName,
        async: false,
        success: function (e) {
            console.log(e);
            for (var i = 0; i < e.length; i++) {
                if (e[i].sendname == msgObject.username) {
                    addSendMsg(e[i]);
                    console.log("自己发送");
                } else {
                    reviceMsg(e[i]);
                    console.log("他人发送");
                }
            }
        }
    })
}
function addSendMsg(e){
    var ss = e.content.split('/');
    if (ss[0] == "data:audio"){
        console.log(460);
        $(".chatBox-content-demo").append(
            "<div class=\"clearfloat\">"+
            "<div class=\"author-name\">" +
            "<small class=\"chat-date\">"+e.addtime+"</small> </div> " +
            "<div class=\"right\"> <div class=\"chat-message\"> " +
            "<audio controls=\"controls\" src=\""+e.content+"\"></audio></div>"+
            "<div class=\"chat-avatars\"><img src=\"img/icon01.png\" alt=\"头像\" /></div> </div> </div>");
    }else {
        $(".chatBox-content-demo").append("<div class=\"clearfloat\">" +
            "<div class=\"author-name\"><small class=\"chat-date\">"+e.addtime+"</small> </div> " +
            "<div class=\"right\"> <div class=\"chat-message\"> " + e.content + " </div> " +
            "<div class=\"chat-avatars\"><img src=\"img/icon01.png\" alt=\"头像\" /></div> </div> </div>");
    }

    //聊天框默认最底部
    $(document).ready(function () {
        $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
    });
}

// 设置对话消息全读
function already() {
    $.ajax({
        url: "/ok/already?sendName="+msgObject.receivename+"&receiveName="+msgObject.username,
    })

}
function recordVoice() {


};
var clickNum = 0;
var isIng = false;
var recordVoice = document.getElementById('recordVoice');
recordVoice.onclick = function (ev) {
    if (clickNum == 0) {
        alert("由于服务器性能原因仅仅保存一条语音记录，请见谅");
        clickNum++;
    }
    if (isIng) {
        sendVioce();
        isIng = false;
        recordVoice.innerText = "点击录音";
    }else {
        var luyin  = document.getElementById('luyin');
        luyin.click();
        recordVoice.innerText = "再次点击发送语音";
        isIng = true;
    }
};
function sendVioce() {
    var voiceBase = '';
    var file = send();
    var read = new FileReader();
    read.readAsDataURL(file.blob);
    read.onload = function (ev) {
        console.log(ev.target.result);
        var kk = document.getElementById('kk');
        kk.setAttribute('src',ev.target.result);
        wsSend(ev.target.result);
        voiceBase = ev.target.result;
        var e = {
            content:  ev.target.result,
            addtime: new Date(),

        };
        addSendMsg(e);
    };
}