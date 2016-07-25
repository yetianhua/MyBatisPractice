function deleteBatch(basePath) {
	var form = document.getElementById('mainForm');
	form.action = basePath + "/deleteBatch";
	form.submit();
}

function changeCurrentPage(page){
	document.getElementById('currentPage').value = page;
	var basePath = document.getElementById("basePath").value;
	var form = document.getElementById('mainForm');
	form.action = basePath + "/MessageList";
	form.submit();
}

function go(){
	var page = document.getElementById("page").value;
	changeCurrentPage(page);
}