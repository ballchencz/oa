

/*console.info($("#chooseFile").get(0));*/
var uploader = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button :$("#chooseFile").get(0), // you can pass an id...
	container:$("#uploadFile_container").get(0), // ... or DOM Element itself
	url : 'upload.php',
	flash_swf_url :contextPath+ '/js/plupload-2.1.8/js/Moxie.swf',
	silverlight_xap_url :contextPath+ '/js/plupload-2.1.8/js/Moxie.xap',

	filters : {
		max_file_size : '10mb',
		mime_types: [
			{title : "Image files", extensions : "jpg,gif,png"},
			{title : "Zip files", extensions : "zip"}
		]
	},

	init: {
		PostInit: function() {
			/*document.getElementById('filelist').innerHTML = '';

			 document.getElementById('uploadfiles').onclick = function() {
			 uploader.start();
			 return false;
			 };*/
			//console.info($("#chooseFile"));
			var rows =  $("#file_content_table").datagrid("getRows");
			$.each(rows,function(index,value){
				$("#file_content_table").deleteRow(index);
			});
		},

		FilesAdded: function(up, files) {

			plupload.each(files, function(file) {
				var data = {"id":file.id,"fileName":file.name,"fileSize":plupload.formatSize(file.size),"progress":""};
				$("#file_content_table").datagrid("appendRow",data);
				//document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
			});
		},

		UploadProgress: function(up, file) {
			//document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
		},

		Error: function(up, err) {
			// document.getElementById('console').appendChild(document.createTextNode("\nError #" + err.code + ": " + err.message));
		}
	}
});
uploader.init();
