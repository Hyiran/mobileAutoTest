var prefix = "/svn/svnRepo";
	
$(function() {
	load();
});

function load() {
	$('#svnRepoTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/querySvnRepo", // 服务器数据的加载地址
				showRefresh : true, // 是否显示刷新按钮
				showToggle : true, // 是否显示详细视图和列表视图的切换按钮
				// showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar', //工具按钮用哪个容器
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				// search : true, // 是否显示搜索框
				showColumns : true, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
				// "server"
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						svnRepoName : $('#searchSvnRepoName').val()
					};
				},
				// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar中的参数 如果
				// queryParamsType = 'limit' ,返回参数必须包含
				// limit, offset, search, sort, order 否则, 需要包含:
				// pageSize, pageNumber, searchText, sortName,
				// sortOrder.
				// 返回false将会终止请求
				columns : [
					{
						checkbox : true,
						align : 'center'
					},
					{
						field : 'id', // 列字段名
						title : '序号', // 列标题
						align : 'center'
					},
					{
						field : 'svnRepoName',
						title : 'SVN产品仓库名',
						align : 'center'
					},
					{
						field : 'svnRepoPath',
						title : 'SVN产品仓库路径',
						align : 'center'
					},
					{
						field : 'svnRepoUrl',
						title : 'SVN产品仓库URL',
						align : 'center'
					},
					{
						field : 'svnRepoDes',
						title : 'SVN产品仓库描述',
						align : 'center'
					},
					{
						title : '操作',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.svnRepoName
								+ '\')"><i class="fa fa-edit"></i></a> ';
/*							var d = '<a  class="btn btn-primary btn-sm ' + s_addSvnUser_h + '" href="#" mce_href="#" title="添加SVN用户" onclick="addSvnUser(\''
								+ row.svnRepoName
								+ '\')"><i class="fa fa-plus"></i></a> ';
							var f = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.svnRepoName
								+ '\')"><i class="fa fa-remove"></i></a> '; */
							return e;
						}
					} ]
			});
}

function reLoad() {
	$('#svnRepoTable').bootstrapTable('refresh');
}

function add() {
	// iframe层
	layer.open({
		type : 2,
		title : '添加SVN产品仓库',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '410px' ],
		content : prefix + '/add'
	});
}

function edit(svnRepoName) {
	layer.open({
		type : 2,
		title : '修改SVN产品仓库',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '410px' ],
		content : prefix + '/edit/' + svnRepoName // iframe的url
	});
}

/*function addSvnUser(svnRepoName) {
	layer.open({
		type : 2,
		title : '添加SVN用户',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/svn/svnUser/add/' + svnRepoName 
	});
}*/

function remove(svnRepoName) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + '/deleteSvnRepo',
			type : "GET",
			data : {
				'svnRepoName' : svnRepoName
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	});
}

function batchRemove() {
	var rows = $('#svnRepoTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var svnRepoIds = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			svnRepoIds[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"svnRepoIds" : svnRepoIds
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}