
	var XMLHttpReq;
	 	//创建XMLHttpRequest对象       
	function createXMLHttpRequest() {
	    if (window.XMLHttpRequest) { //Mozilla 浏览器
	        XMLHttpReq = new XMLHttpRequest();
	    } else {
	        if (window.ActiveXObject) { // IE浏览器
	            try {
	                XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
	            }
	            catch (e) {
	                try {
	                    XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
	                }
	                catch (e) {
	                }
	            }
	        }
	    }
	}
	//发送请求函数
	function refreshList() {
	    var sort = document.getElementById("sortOk").checked;
	    if(sort == false ) {
	        clearList();
	        return;
	    }
	    var url = "dyList";	        
	    createXMLHttpRequest();
	    XMLHttpReq.open("GET", url, true);
	    XMLHttpReq.onreadystatechange = processListResponse;//指定响应函数
	    XMLHttpReq.send(null);  // 发送请求
	}

	// 处理返回信息函数
    function processListResponse() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	updateList();
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
	// 
	function updateList() {
	    clearList();
	    var category = document.getElementById("category");

		var xmlDoc =XMLHttpReq.responseXML;
    	var sorts = xmlDoc.getElementsByTagName("sort");
    	var currentSort = null;
    	for(var i = 0; i < sorts.length; i++) {
        	currentSort = sorts[i];
        	var id =currentSort.getElementsByTagName("id")[0].firstChild.nodeValue;
        	var name =currentSort.getElementsByTagName("name")[0].firstChild.nodeValue;

	    	var option = null;
	        option = document.createElement("option");
	        option.appendChild(document.createTextNode(name));
	        option.setAttribute("value",id);
	        category.appendChild(option);

	    }
	}

	function clearList() {
	    var sort = document.getElementById("category");
	    while(sort.childNodes.length > 0) {
	        sort.removeChild(sort.childNodes[0]);
	    }
	}

	
	//发送请求函数
	function saveRequest(url) {
		var title = document.articleForm.entrytitle.value;
		var sort =0;
		if (document.articleForm.sortOk.checked) {
			sort = document.articleForm.category.value;
		}
		var content = document.articleForm.entrycontent.value;
	    var url = "title=" + title + "&sortid=" + sort + "&content=" + content;	        
		createXMLHttpRequest();
		XMLHttpReq.open("POST","saveArticle", true);
     	XMLHttpReq.onreadystatechange = processSaveResponse;//指定响应函数
    	XMLHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");    
    	XMLHttpReq.send(url);  // 发送请求
	}
	// 处理返回信息函数
    function processSaveResponse() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseText;
                window.alert(res);                
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
	