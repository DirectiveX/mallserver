let elementsByTagName = document.getElementsByTagName("script");
let element = elementsByTagName[elementsByTagName.length - 1];
let variables = element.src.split("?")[1].split("&");
let callback;

let type;
let pageNo;
for (let item of variables) {
    let key = item.split("=")[0];
    let value = item.split("=")[1];
    if ("callback" === key){
        callback = eval(value)
    }else if("type" === key){
        type = value
    }else if("pageNo" === key){
        pageNo = value
    }
}

let xhr = new XMLHttpRequest();
xhr.open("GET","http://localhost:8092/resource/index?type="+type+"&pageNo="+pageNo,true)
xhr.send(null)
xhr.onreadystatechange = function () {
    if(xhr.readyState == 4) {
        let status=xhr.status;
        if(status>=200&&status<300){
            callback(xhr.responseText);
        }
    }
}

