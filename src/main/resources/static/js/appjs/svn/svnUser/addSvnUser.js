$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/svn/svnUser/addSvnUser",
		data : $('#addSvnUserForm').serialize(),// 你的formid
		beforeSend : beforeSend, //用于在向服务器发送请求之前执行显示进度条
		async : true, //异步
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
			} else {
				parent.layer.alert(data.msg);
			}
		},
		complete : complete
	});
}

function beforeSend(XMLHttpRequest) {
	// 禁用按钮防止重复提交
	$("#submit").attr({disabled : "disabled"});
	// 显示进度条
	$("#showLoading").append("<div><img src='/img/loading-bar.gif' /><div>");
}

function complete(XMLHttpRequest, textStatus) {
	// 取消禁用按钮
	$("#submit").removeAttr("disabled");
	// 隐藏进度条
	$("#showLoading").remove();
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#addSvnUserForm").validate({
		rules : {
			svnRepoName : {
				required : true
			},
			svnUserName : {
				required : true
			},
			svnPassword : {
				required : true,
				minlength : 6,
			},
			confirm_password : {
				required : true,
				minlength : 6,
				equalTo : "#svnPassword"
			}
		},
		messages : {
			svnRepoName : {
				required : icon + "请输入SVN产品仓库名"
			},
			svnUserName : {
				required : icon + "请输入SVN用户名"
			},
			svnPassword : {
				required : icon + "请输入SVN密码",
				minlength : icon + "密码必须6个字符以上"
			},
			confirm_password : {
				required : icon + "请再次输入SVN密码",
				minlength : icon + "密码必须6个字符以上",
				equalTo : icon + "两次输入的密码不一致"
			}
		}
	});
}
