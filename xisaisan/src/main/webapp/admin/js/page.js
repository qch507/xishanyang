function pageto(pindex) {
	$('#pageIndex').val(pindex);
	$('#pageForm').submit();
}

function pageprev() {
	var page = Number($('#pageIndex').val()) - 1;
	if (page > 0) {
		$('#pageIndex').val(page);
		$('#pageForm').submit();
	}
}

function pagenext() {
	var page = Number($('#pageIndex').val()) + 1;
	if (page <= Number($('#pageCount').val())) {
		$('#pageIndex').val(page);
		$('#pageForm').submit();
	}
}

// container 容器，count 总页数 pageindex 当前页数
function setPage(container, count, pageindex) {
	var a = [];
	// 总页数少于10 全部显示,大于10 显示前3 后3 中间3 其余....
	if (pageindex == 1) {
		a[a.length] = "<li><a onclick=\"\" style=\"cursor:pointer\" class=\"unclickprev \" href=\"#\">&lt;&nbsp;上一页</a></li>";
	} else {
		a[a.length] = "<li><a onclick=\"pageprev()\" style=\"cursor:pointer\">&lt;&nbsp;上一页</a></li>";
	}
	function setPageList() {
		if (pageindex == i) {
			a[a.length] = "<li><a onclick=\"pageto(" + i
					+ ")\" class=\"on\" style=\"cursor:pointer\">" + i
					+ "</a></li>";
		} else {
			a[a.length] = "<li><a onclick=\"pageto(" + i
					+ ")\" style=\"cursor:pointer\">" + i + "</a></li>";
		}
	}
	// 总页数小于10
	if (count <= 10) {
		for (var i = 1; i <= count; i++) {
			setPageList();
		}
		;
	} else {
		// 总页数大于10页
		if (pageindex <= 4) {
			for (var i = 1; i <= 5; i++) {
				setPageList();
			}
			a[a.length] = "<li><a class=\"pagination-noborder\">...</a></li><li><a style=\"cursor:pointer\" onclick=\"pageto("
					+ count + ")\">" + count + "</a></li>";
		} else if (pageindex >= count - 3) {
			a[a.length] = "<li><a onclick=\"pageto(1)\">1</a></li><li><a style=\"cursor:pointer\" class=\"pagination-noborder\">...</a></li>";
			for (var i = count - 4; i <= count; i++) {
				setPageList();
			}
			;
		} else { // 当前页在中间部分
			a[a.length] = "<li><a onclick=\"pageto(1)\" style=\"cursor:pointer\">1</a></li><li><a style=\"cursor:pointer\" class=\"pagination-noborder\">...</a></li>";
			for (var i = pageindex - 2; i <= pageindex + 2; i++) {
				setPageList();
			}
			a[a.length] = "<li><a class=\"pagination-noborder\">...</a></li><li><a style=\"cursor:pointer\" onclick=\"pageto("
					+ count + ")\">" + count + "</a></li>";
		}
	}
	if (pageindex == count) {
		a[a.length] = "<li><a onclick=\"\" style=\"cursor:pointer\" class=\"hide_page_next pagehover unclicknext \">下一页&nbsp;&gt;</a></li> ";
	} else {
		a[a.length] = "<li><a style=\"cursor:pointer\" onclick=\"pagenext()\" "
				+ ">下一页&nbsp;&gt;</a></li> ";
	}
	container.innerHTML = a.join("");
}