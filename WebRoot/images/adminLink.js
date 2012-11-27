
	var XMLHttpReq;
 	//创建XMLHttpRequest对象       
    function createXMLHttpRequest() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	// 处理增加链接响应函数
    function AddStateChange() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
               AddLinkList();
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
	// 增加链接函数
	function addLink() {
        var name = document.getElementById("name").value;
	    var url1 = document.getElementById("url").value;
	    if(name == "" ) {
	        return;
	    }
	    var url = "action=add" + "&name="+ name + "&url="+ url1;
	    createXMLHttpRequest();
	    XMLHttpReq.onreadystatechange = AddStateChange;
	    XMLHttpReq.open("POST", "adminLinks", true);
    	XMLHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");    
	    XMLHttpReq.send(url);
	}

	function AddLinkList() {
		var xmlDoc =XMLHttpReq.responseXML;
	    var AddID = xmlDoc.getElementsByTagName("id")[0].firstChild.nodeValue;
	    var AddName = xmlDoc.getElementsByTagName("name")[0].firstChild.nodeValue;
	    var AddUrl = xmlDoc.getElementsByTagName("url")[0].firstChild.nodeValue;
	    if (AddID!=null){
			addOneLink(AddID,AddName,AddUrl);
			//清空输入框
		    document.getElementById("name").value = "";
		    document.getElementById("url").value = "";
		}
	}

	// 删除链接函数
	function deleteLink(id) {
	    var url = "adminLinks?action=delete" + "&id=" + id;
	    createXMLHttpRequest();
	    XMLHttpReq.onreadystatechange = DeleteStateChange;
	    XMLHttpReq.open("GET", url, true);
	    XMLHttpReq.send(null);
	}
	// 处理删除链接响应函数
	function DeleteStateChange() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	            deleteLinkList();
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
	}

	function deleteLinkList() {
		deleteID = XMLHttpReq.responseXML.getElementsByTagName("id")[0].firstChild.nodeValue;
		if (deleteID!=null){
		    var rowToDelete = document.getElementById(deleteID);
		    var linkList = document.getElementById("linkList");
		    linkList.removeChild(rowToDelete);
		}
	}

	// 页面装入请求函数
    function loadRequest() {
        var url = "adminLinks?action=load";
	    createXMLHttpRequest();
	    XMLHttpReq.onreadystatechange = loadResponse;
	    XMLHttpReq.open("GET", url, true);
	    XMLHttpReq.send(null);
	}
	// 页面装入处理函数
    function loadResponse() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	            loadLinkList();
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
	// 装入全部链接函数
	function loadLinkList() {
		var xmlDoc =XMLHttpReq.responseXML;
    	var links = xmlDoc.getElementsByTagName("link");
    	var currentLink = null;
    	for(var i = 0; i < links.length; i++) {
        	currentLink = links[i];
        	var id =currentLink.getElementsByTagName("id")[0].firstChild.nodeValue;
        	var name =currentLink.getElementsByTagName("name")[0].firstChild.nodeValue;
        	var url =currentLink.getElementsByTagName("url")[0].firstChild.nodeValue;
			addOneLink(id,name,url);	    
		}
	}
	// 插入一行函数
	function addOneLink(id,name,url) {
		    var row = document.createElement("tr");
		    row.setAttribute("id", id);
		    var cell = document.createElement("td");
		    cell.appendChild(document.createTextNode(name));
		    row.appendChild(cell);
		    
		    cell = document.createElement("td");
		    cell.appendChild(document.createTextNode(url));
		    row.appendChild(cell);
		    
		    var deleteButton = document.createElement("input");
		    deleteButton.setAttribute("type", "button");
		    deleteButton.setAttribute("value", "删除");
		    deleteButton.onclick = function () { deleteLink(id); };
		    cell = document.createElement("td");
		    cell.appendChild(deleteButton);
		    row.appendChild(cell);
		    
		    document.getElementById("linkList").appendChild(row);
	}
	
	