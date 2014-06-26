BarChart = function(elemenId,width,height, margin){
	
	var _chart = null;
	var _data = null;
	var _width = 0;
	var _height = 0;
	var _x0 = null;
	var _x1 = null;
	var _x2 = null;
	var _y = null;
	var _color = null;
	var _axisProp = null;
	var _xAxis = null;
	var _yAxis = null;
	
	this.legendEnabled = false;
	this.rotateXAxisText = false;
	this.yAxisLabel = "";
	this.xAxisLabel = "";
	
	this.init = function(width, height , margin){
		margin = margin ? margin : {left:10, right:10, top:10, bottom:10};
		_width = width - margin.left - margin.right,
		_height = height - margin.top - margin.bottom;
		
		_chart = d3.select(elemenId).append("svg")
	    .attr("width", _width + margin.left + margin.right)
	    .attr("height", _height + margin.top + margin.bottom)
	  	.append("g")
	    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
		
		_color = d3.scale.ordinal()
	    .range(["#ff534a", "#21b499", "#7b6888", "#6b486b", "#a05d56", "#d0743c", "#ff8c00"]);
				
		_x0 = d3.scale.ordinal()
	    .rangeRoundBands([0, _width], .1);
		
		_x2 = d3.scale.ordinal();
		
		_y = d3.scale.linear()
			.range([_height, 0]);
		
		_xAxis = d3.svg.axis()
	    .scale(_x0)
		.tickSize(0)
	    .orient("bottom");
		
		_yAxis = d3.svg.axis()
	    .scale(_y)
	    .tickSize(0)
		.ticks(2)
	    .orient("left");
		
	};
	
	this.setData = function(data, axisProp , colors){
		_data = data;
		colors = colors ? colors : ["#ff534a", "#21b499", "#7b6888"];
		_color = d3.scale.ordinal()
	    .range(colors);
		_x1 = _x2;
		_axisProp = axisProp;
		var maxW = 100*data.length < _width ? 100*data.length : _width;
		_x0.rangeRoundBands([0, maxW], 0.2);
		_x0.domain(data.map(function(d) { return d[_axisProp.x[0]]; }));
		_x1.domain(axisProp.y).rangeRoundBands([0, _x0.rangeBand()]);
		if(axisProp.y.length == 1){
			_x2 = _x1;
			_x1 = _x0;
		}
		var yMax = d3.max(data, function(d) { return d3.max(d.props, function(d) { return d.value; }); });
		yMax = yMax < 10 ? 10 : yMax;
		
		//round the number to show only 2 ticks
		var y1 = yMax;
		var ranges = [0.5, 1, 2, 5];
		var divideBy = 1;
		while(divideBy.toString().length < yMax.toString().length){
			divideBy = divideBy*10;
		}
		var y0 = yMax/divideBy;
		for(var i=0;i<ranges.length;i++){
			if(y0 <= ranges[i]*2){
				y1 = ranges[i]*2*divideBy;
				break;
			}
		}
		yMax = y1;
		_y.domain([0, yMax]);
		
	};
	
	this.update = function(){
		//
	 	this.clear();
	 	
		var state = _chart.selectAll(".state")
		.data(_data)
	    .enter().append("g")
	    .attr("class", "g")
	    .attr("transform", function(d) { return "translate(" + _x0(d[_axisProp.x[0]]) + ",0)"; });

		_xAxis.tickFormat(function(d , i){
			
			if(_x1.rangeBand < 7 && i%5 != 0 && i != _data.length-1){
				return "";
			}
			
			if(typeof(d) != "number" && typeof(d) != "string"){
				var dt = d.getDate();
				dt = dt < 10 ? "0"+dt : dt;
				return dt;
			}
			return d;
		 });
		
		var axis = _chart.append("g")
	      .attr("class", "x axis")
	      .attr("transform", "translate(0," + (_height) + ")")
	      .call(_xAxis)
		  .append("text")
		   .attr("dx", _width/2)
		  .attr("dy", 30)
	      .attr("class","axis-label")
		  .style("text-anchor", "middle")
		  .text(this.xAxisLabel);
		 axis.selectAll("text").attr("dy", "1.71em");
	      
	   if(this.rotateXAxisText){
		   axis.selectAll("text") // select all the text elements for the xaxis
	        .attr("transform", function(d,index) {
	           return "translate(" + -13 + "," + (this.getBBox().height+44) + ") rotate(-90)";
	       })
	       .attr("text-achor","left");
	   }
		
		_chart.append("g")
	      .attr("class", "y axis")
	      .call(_yAxis)
	      .append("text")
	      .attr("class","axis-label")
	      .attr("transform", "translate(" + 0 + "," + _height/2 + ") rotate(-90)")
	      .attr("y", 0)
	      .attr("stroke-width","1")
	      .attr("dy", "-2.2em")
	      .style("text-anchor", "end")
	      .text(this.yAxisLabel);
		
		var rectWidth = _x1.rangeBand() > 12 ? 12 : _x1.rangeBand();
		
		state.selectAll("rect")
		      .data(function(d) { return d.props; })
		      .enter().append("rect")
		      .attr("width", rectWidth)
		      .attr("x", function(d,i) { 
					var dx =  _x1.rangeBand()/2;
					if(_axisProp.y.length == 1){
						dx =  _x1.rangeBand()/2;
					}else if(i == 0){
						dx =  _x1.rangeBand() - rectWidth;
					}else{
						dx = _x1.rangeBand() - rectWidth*0.2;
					}

					return dx; })
		      .attr("y", function(d) { return  _height - _y(d.value) < 2 ? _height-2 : _y(d.value); })
		      .attr("height", function(d) { var h = _height - _y(d.value); return h < 2 ? 2 : h; })
		      .style("fill", function(d) { return _color(d.name); });
		
		_chart.selectAll(".g").tooltip(function(d, i){
			var xprop = _axisProp.x[0];
			var yprop = _axisProp.y[0];
			var dx = 0;
			var dy = _y(d[yprop]);
			var nextTick = 0;

			var xVal = d[xprop];

			if(nextTick > i){
				return { type:"none", text: " "};
			}

			if(typeof(xVal) != "number" && typeof(xVal) != "string"){
				var dx = margin.left + _x0(d[xprop]) + _x1.rangeBand();
				var dy = _y(d[yprop]);
				var label = null;
				var today = $.datepicker.formatDate('yy-mm-dd' , new Date());
				if($.datepicker.formatDate('yy-mm-dd', xVal) == today){
					label = "Today";
				}else if(xVal.getDate() == 1){
					label = $.datepicker.formatDate('M', xVal);
				}
				
				if($.browser.mozilla){
					//hack to fix the unexplain offset in firefox
					dx -= 31.5;
					dy -= 9;
				}

				if(label){
					nextTick = i+5;
					return {
						  type: "callout",
						  text: label,
						  class: yprop,
						  detection: "shape",
						  placement: "fixed",
						  gravity: "top",
						  position: [dx, dy],
						  displacement: [0, -20],
						  mousemove: false
						};
				}
			}
			
			if((i-5)%10 != 0 && i%10 != 0){
				return { type:"none", text: " "};
			}
			
			if(typeof(xVal) != "number" && typeof(xVal) != "string"){
				for(var p=i;p<i+3;p++){
					var d1 = _data[p] ? _data[p][xprop] : null;
					if(d1 && (d1.getDate() == 1 || $.datepicker.formatDate('yy-mm-dd', d1) == today)){
						nextTick = p;
						return { type:"none", text: " "};		
					}
				}
			}

			if(_axisProp.y.length == 1){
				dx = -_x1.rangeBand()/2 + rectWidth/2;
			}else if(i%10 == 0){
				dx = -rectWidth/2;
			}else if((i-5)%10 == 0 ){
				yprop = _axisProp.y.length > 1 ? _axisProp.y[1] : _axisProp.y[0];
				dx = -rectWidth*0.2 + rectWidth/2;
			}

			dx = margin.left + _x0(d[xprop]) + _x1.rangeBand() + dx;
			next = i+5;
			
			if($.browser.mozilla){
				//hack to fix the unexplain offset in firefox
				dx -= 31.5;
				dy -= 9;
			}
			return {
					  type: "callout",
					  text: d[yprop],
					  class: yprop,
					  detection: "shape",
					  placement: "fixed",
					  gravity: "top",
					  position: [dx, dy],
					  displacement: [0, -20],
					  mousemove: false
					};
		
		});
		
		
		if(this.legendEnabled){
			var legend = _chart.selectAll(".legend")
	     	.data(_axisProp.y.slice().reverse())
	     	.enter().append("g")
	     	.attr("class", "legend")
	     	.attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });
			
	
			legend.append("rect")
			      .attr("x", _width - 18)
			      .attr("width", 18)
			      .attr("height", 18)
			      .style("fill", _color);
			
			legend.append("text")
			      .attr("x", _width - 24)
			      .attr("y", 8)
			      .attr("dy", ".35em")
			      .style("text-anchor", "end")
			      .text(function(d) { return d; });
		}
		

		//x-axis dark stroke
		_chart.append("line")
			.attr("x1", 0)
		    .attr("y1", _height)
		    .attr("x2", _width)
		    .attr("y2", _height)
			.attr("class", "axis-line");
	};
	
	this.dataError = function(){
		this.clear();
		_chart.append("text")
			.attr("x", _width/2)
			.attr("y",  _height/2)
			.attr("class","data-error")
			.style("text-anchor", "middle")
			.text("No data");
	}
	
	this.clear = function(){
		d3.selectAll(".callout").remove();
		_chart.selectAll(".axis-line").remove();
		_chart.selectAll(".g").remove();
		_chart.selectAll(".y.axis").remove();
	 	_chart.selectAll(".x.axis").remove();
		_chart.selectAll("rect").remove();
		_chart.selectAll(".legend").remove();
		_chart.selectAll(".data-error").remove();
	}
	
	this.init(width, height, margin);
};