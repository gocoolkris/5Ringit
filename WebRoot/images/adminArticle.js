
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
	// 处理分类更改响应函数
    function sortChangeResponse() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	            updateArticleList();
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
	// 选择分类函数
	function sortChange() {
        var sortid = document.getElementById("sort").value;
	    var url = "action=show" + "&sortid="+ sortid;
	    createXMLHttpRequest();
	    XMLHttpReq.onreadystatechange = sortChangeResponse;
	    XMLHttpReq.open("POST", "adminArticle", true);
    	XMLHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");    
	    XMLHttpReq.send(url);
	}

	function updateArticleList() {
		clearArticleList();
		loadArticleList(); 	
	}

	// 删除链接函数
	function deleteArticle(id) {
	    var url = "adminArticle?action=delete" + "&articleid=" + id;
	    createXMLHttpRequest();
	    XMLHttpReq.onreadystatechange = DeleteStateChange;
	    XMLHttpReq.open("GET", url, true);
	    XMLHttpReq.send(null);
	}
	// 处理删除链接响应函数
	function DeleteStateChange() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	            deleteArticleList();
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
	}

	function deleteArticleList() {
		deleteID = XMLHttpReq.responseXML.getElementsByTagName("id")[0].firstChild.nodeValue;
		if (deleteID!=null){
		    var rowToDelete = document.getElementById(deleteID);
		    var articleList = document.getElementById("articleList");
		    articleList.removeChild(rowToDelete);
		}
	}

	// 页面装入请求函数
    function loadRequest() {
        var url = "adminArticle?action=load";
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
	            loadArticleList();
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
	// 装入分类列表函数
	function loadSortList() {
        var option = document.createElement("option");
        option.appendChild(document.createTextNode("全部文章"));
	    option.setAttribute("value", "０");
        sort.appendChild(option);
		var xmlDoc =XMLHttpReq.responseXML;
    	var sorts = xmlDoc.getElementsByTagName("sort");
    	var currentSort = null;
    	for(var i = 0; i < sorts.length; i++) {
        	currentSort = sorts[i];
        	var id =currentSort.getElementsByTagName("id")[0].firstChild.nodeValue;
        	var name =currentSort.getElementsByTagName("name")[0].firstChild.nodeValue;

	        option = document.createElement("option");
	        option.appendChild(document.createTextNode(name));
		    option.setAttribute("value", id);
	        sort.appendChild(option);
		}
	}
	// 装入文章函数
	function loadArticleList() {
		var xmlDoc =XMLHttpReq.responseXML;
    	var articles = xmlDoc.getElementsByTagName("article");
    	var currentArticle = null;
    	for(var i = 0; i < articles.length; i++) {
        	currentArticle = articles[i];
        	var id =currentArticle.getElementsByTagName("id")[0].firstChild.nodeValue;
        	var name =currentArticle.getElementsByTagName("name")[0].firstChild.nodeValue;
        	var time =currentArticle.getElementsByTagName("time")[0].firstChild.nodeValue;
			addOneArticle(id,name,time);	    
		}
	}
	// 插入一行函数
	function addOneArticle(id,name,time) {
	    var row = document.createElement("tr");
	    row.setAttribute("id", id);
	    var cell = document.createElement("td");
	    cell.appendChild(document.createTextNode(name));
	    row.appendChild(cell);
	    
	    cell = document.createElement("td");
	    cell.appendChild(document.createTextNode(time));
	    row.appendChild(cell);
	    
	    var deleteButton = document.createElement("input");
	    deleteButton.setAttribute("type", "button");
	    deleteButton.setAttribute("value", "删除");
	    deleteButton.onclick = function () { deleteArticle(id); };
	    cell = document.createElement("td");
	    cell.appendChild(deleteButton);
	    row.appendChild(cell);
	    
	    document.getElementById("articleList").appendChild(row);
	}

	function clearArticleList() {
	    var article = document.getElementById("articleList");
	    while(article.childNodes.length > 0) {
	        article.removeChild(article.childNodes[0]);
	    }
	}

	
	