
var XMLHttpReq;
 	   
function createXMLHttpRequest() {
    if (window.XMLHttpRequest) { 
        XMLHttpReq = new XMLHttpRequest();
    } else {
        if (window.ActiveXObject) { 
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

function loadRequest() {
    createXMLHttpRequest();
    var url = "first";
    XMLHttpReq.open("GET", url, true);
    XMLHttpReq.onreadystatechange = processLoadResponse;
    XMLHttpReq.send(null);  
}

function processLoadResponse() {
	if (XMLHttpReq.readyState == 4) { 
    	if (XMLHttpReq.status == 200) {
            AddBlogList();
            AddArticleList();
			setTimeout("loadRequest()", 10000);
        } else {
            window.alert("您所请求的页面有异常。");
        }
   	}
}
	
	function AddBlogList() {
		deleteBlogList();
		var xmlDoc =XMLHttpReq.responseXML;
    	var blogs = xmlDoc.getElementsByTagName("blog");
    	var currentBlog = null;
    	for(var i = 0; i < blogs.length; i++) {
        	currentBlog = blogs[i];
        	var id =currentBlog.getElementsByTagName("id")[0].firstChild.nodeValue;
        	var name =currentBlog.getElementsByTagName("name")[0].firstChild.nodeValue;

		    var row = document.createElement("tr");
		    row.setAttribute("id",id);
		    var cell = document.createElement("td");
		    cell.appendChild(document.createTextNode("NO " + (i+1) + "."));
		    row.appendChild(cell);
		    
		    cell = document.createElement("td");
		    var href = document.createElement("a");
		   	href.setAttribute("href","openBlog?blogid=" + id);
		    
			href.appendChild(document.createTextNode(name));
		    cell.appendChild(href);
		    row.appendChild(cell);
		    
		    document.getElementById("blogList").appendChild(row);
		}
	}

	
	function AddArticleList() {
		deleteArticleList();
		var xmlDoc =XMLHttpReq.responseXML;
    	var articles = xmlDoc.getElementsByTagName("article");
    	var currentArticle = null;
    	for(var i = 0; i < articles.length; i++) {
        	currentArticle = articles[i];
        	var id =currentArticle.getElementsByTagName("id")[0].firstChild.nodeValue;
        	var name =currentArticle.getElementsByTagName("title")[0].firstChild.nodeValue;
        	var time =currentArticle.getElementsByTagName("time")[0].firstChild.nodeValue;

		    var row = document.createElement("tr");
		    row.setAttribute("id",id);
		    var cell = document.createElement("td");
		    var href = document.createElement("a");
		   	href.setAttribute("href","openArticle?articleid=" + id);
			href.appendChild(document.createTextNode(name));
		    cell.appendChild(href);
		    row.appendChild(cell);

		    cell = document.createElement("td");
		    cell.appendChild(document.createTextNode(time));
		    cell.setAttribute("width",110);
		    row.appendChild(cell);

		    document.getElementById("articleList").appendChild(row);
		}
	}
	
	function deleteBlogList() {
		var blogList = document.getElementById("blogList");
		while (blogList.hasChildNodes()) {
			blogList.removeChild(blogList.firstChild);
		}
	}
	
	
	function deleteArticleList() {
		var articleList = document.getElementById("articleList");
		while (articleList.hasChildNodes()) {
			articleList.removeChild(articleList.firstChild);
		}
	}
	
	
	function loginRequest(url) {
		createXMLHttpRequest();
		XMLHttpReq.open("GET",url, true);
     	XMLHttpReq.onreadystatechange = processLoginResponse;
    	XMLHttpReq.send(null);
	}

    function processLoginResponse() {
    	if (XMLHttpReq.readyState == 4) {
        	if (XMLHttpReq.status == 200) { 
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.nodeValue;
                if (res==1){
                	window.alert("用户名错误！");                
                }
                else if (res==2){
                	window.alert("密码错误！");                
                }
                else if (res==3){
                	window.alert("验证码错误！");                
                }
                else if (res==0){
            		var id=XMLHttpReq.responseXML.getElementsByTagName("id")[0].firstChild.nodeValue;
                	window.location = "openBlog?blogid=" + id;                
                }
            } else {
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    function userCheck() {
		uname = document.loginForm.uname.value;
		psw = document.loginForm.psw.value;
		checkwd = document.loginForm.checkwd.value;
		if(uname=="") {
			window.alert("用户名不能为空。");
			document.loginForm.uname.focus();
			return false;
		}
		else {
			loginRequest("login?uname=" + uname + "&psw=" + psw + "&checkwd=" + checkwd);
		}
	}
	