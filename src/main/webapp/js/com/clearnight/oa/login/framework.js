
	function serializeObject(form){
		var o = {};
		var array = form.serializeArray();
		$.each(array,function(index,value){
			if(value.name && value.value){
				o[value.name] = value.value;
			}
		});
		return o;
	};
	
	function drawTopBorderForDataGridTools(datagrid,topBorderWidth){
		
		var panelSelector = datagrid.datagrid("getPanel");
		if(panelSelector.length != 0){
			var toolbarSelector = $(panelSelector).children(".datagrid-toolbar:first");
			if(panelSelector.length != 0){
				topBorderWidth = topBorderWidth == undefined?"1":topBorderWidth;
				toolbarSelector.css({"border-top-width":""+topBorderWidth+"px"});
			}
		}
	}


