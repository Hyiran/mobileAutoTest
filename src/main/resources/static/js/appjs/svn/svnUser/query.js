var prefixSvnUser = "/svn/svnUser";
	
$(function() {
	var svnRepoName = '';
	getTreeData();
	load(svnRepoName);
});

function load(svnRepoName) {
	$('#svnUserQueryTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefixSvnUser + "/query", // 服务器数据的加载地址
				showRefresh : true,
				showToggle : true,
				// showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
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
						svnUserName: $('#searchSvnUserName').val(),
						svnRepoName : svnRepoName,
						name : $('#searchName').val()
					};
				},
				// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar中的参数 如果
				// queryParamsType = 'limit' ,返回参数必须包含
				// limit, offset, search, sort, order 否则, 需要包含:
				// pageSize, pageNumber, searchText, sortName,
				// sortOrder.
				// 返回false将会终止请求
				columns : [
/*					{
						checkbox : true
					},*/
					{
						field : 'svnRepoId', // 列字段名
						title : 'SVN产品仓库序号', // 列标题
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
						field : 'name',
						title : '用户姓名',
						align : 'center'
					},
					{
						field : 'svnUserName',
						title : 'SVN用户名',
						align : 'center'
					}
					/*,
					{
						title : '操作',
						align : 'center',
						formatter : function(value, row, index) {
							var h = '<a  class="btn btn-primary btn-sm ' + s_passTest_h + '" href="#" mce_href="#" title="测试SVN用户联通性" onclick="passTest(\''
								+ row.svnUserName + '\'' + ',' + '\''+ row.svnRepoName
								+ '\')"><i class="fa fa-anchor"></i></a> ';
							return h;
						}
					} */
				]
			});
}

function reLoad() {
	$('#svnUserQueryTable').bootstrapTable('refresh');
}

/*function passTest(svnUserName, svnRepoName) {
	layer.open({
		type : 2,
		title : '测试SVN用户联通性',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '420px' ],
		content : prefixSvnUser + '/passTest/' + svnUserName + '/' + svnRepoName
	});
}*/


function getTreeData() {
	$.ajax({
		type : "GET",
		url : "/svn/svnRepo/tree",
		success : function(tree) {
			loadTree(tree);
		}
	});
}

function loadTree(tree) {
	$('#jstree').jstree({
		'core' : {
			'data' : tree
		},
		"plugins" : [ "search" ]
	});
	$("#searchRepo").submit(function (e) {
		e.preventDefault();	
		$("#jstree").jstree(true).search($("#svnRepoName").val());
	});
	$('#jstree').jstree().open_all();
}
$('#jstree').on("changed.jstree", function(e, data) {
	if (data.selected == -1) {
		var opt = {
			query : {
				svnRepoName : '',
			}
		};
		$('#svnUserQueryTable').bootstrapTable('refresh', opt);
	} else {
		var opt = {
			query : {
				svnRepoName : data.node.text,
			}
		};
		$('#svnUserQueryTable').bootstrapTable('refresh', opt);
	}
});
