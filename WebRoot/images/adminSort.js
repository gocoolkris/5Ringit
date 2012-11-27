
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
	// 处理增加类别响应函数
    function AddStateChange() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	            AddSortList();
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
	// 增加类别函数
	function addSort() {
        name = document.getElementById("name").value;
	    if(name == "" ) {
	        return;
	    }
	    var url = "action=add" + "&name="+ name;
	    createXMLHttpRequest();
	    XMLHttpReq.onreadystatechange = AddStateChange;
	    XMLHttpReq.open("POST", "adminSort", true);
    	XMLHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");    
	    XMLHttpReq.send(url);
	}

	function AddSortList() {
	    var AddID = XMLHttpReq.responseXML.getElementsByTagName("id")[0].firstChild.nodeValue;
	    if (AddID!=null){
			addOneSort(AddID,name);
			//清空输入框
		    document.getElementById("name").value = "";
		}
	}

	// 删除类别函数
	function deleteSort(id) {
	    var url = "adminSort?action=delete" + "&id=" + id;
	    createXMLHttpRequest();
	    XMLHttpReq.onreadystatechange = DeleteStateChange;
	    XMLHttpReq.open("GET", url, true);
	    XMLHttpReq.send(null);
	}
	// 处理删除响应函数
	function DeleteStateChange() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	            deleteSortList();
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
	}

	function deleteSortList() {
		deleteID = XMLHttpReq.responseXML.getElementsByTagName("id")[0].firstChild.nodeValue;
		if (deleteID!=null){
		    var rowToDelete = document.getElementById(deleteID);
		    var sortList = document.getElementById("sortList");
		    sortList.removeChild(rowToDelete);
		}
	}

	// 页面装入请求函数
    function loadRequest() {
        var url = "adminSort?action=load";
	    createXMLHttpRequest();
	    XMLHttpReq.onreadystatechange = loadResponse;
	    XMLHttpReq.open("GET", url, true);
	    XMLHttpReq.send(null);
	}
	// 页面装入处理函数
    function loadResponse() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	            loadSortList();
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
	// 装入全部分类函数
	function loadSortList() {
		var xmlDoc =XMLHttpReq.responseXML;
    	var sorts = xmlDoc.getElementsByTagName("sort");
    	var currentSort = null;
    	for(var i = 0; i < sorts.length; i++) {
        	currentSort = sorts[i];
        	var id =currentSort.getElementsByTagName("id")[0].firstChild.nodeValue;
        	var name =currentSort.getElementsByTagName("name")[0].firstChild.nodeValue;
			addOneSort(id,name);	    
		}
	}
	// 插入一行函数
	function addOneSort(id,name) {
		    var row = document.createElement("tr");
		    row.setAttribute("id", id);
		    var cell = document.createElement("td");
		    cell.appendChild(document.createTextNode(name));
		    row.appendChild(cell);
		    
		    var deleteButton = document.createElement("input");
		    deleteButton.setAttribute("type", "button");
		    deleteButton.setAttribute("value", "删除");
		    deleteButton.onclick = function () { deleteSort(id); };
		    cell = document.createElement("td");
		    cell.appendChild(deleteButton);
		    row.appendChild(cell);
		    
		    document.getElementById("sortList").appendChild(row);
	}
	
	