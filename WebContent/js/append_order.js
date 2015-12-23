/*function appendOrderSeller(pic,good,url,price,num,state){
		var div1=document.createElement("div");
		var span1=document.createElement("span");

		var span1_a =document.createElement("a");
		var a_img=document.createElement("img");

		var span2=document.createElement("span");
		var span2_a=document.createElement("a");

		var span3=document.createElement("span");
		var span4=document.createElement("span");
		var span5=document.createElement("span");
		var span6=document.createElement("span");

		var div2=document.createElement("div");
		//var button1=document.createElement("button");

		$(div1).addClass("row goods_item");
		$(span1).addClass("col-lg-2 col-md-3 goods_pic");
		$(span2).addClass("col-lg-3 goods_name");
		$(span3).addClass("col-lg-1");
		$(span4).addClass("col-lg-1");
		$(span5).addClass("col-lg-1");
		$(span6).addClass("col-lg-2");
		$(div2).addClass("col-lg-2");
		$(span1_a).append(a_img);
		$(span1).append(span1_a);
		
		$(span2).append(span2_a);
		//$(div2).append(button1);
		$(".list").append(div1);  
		$(".goods_item").append(span1,span2,span3,span4,span5,span6,div2);


		$(a_img).attr("src",pic);
		$(span2_a).attr("href",url);
		span2_a.innerHTML=good;
		span3.innerHTML=price;
		span4.innerHTML=num;
		span5.innerHTML=parseFloat(price)*parseFloat(num);
		span6.innerHTML=state;
		//button1.innerHTML=button_name;
	}

	function appendOrderCustomer(pic,good,url,price,num,state){
		var div1=document.createElement("div");
		var span1=document.createElement("span");

		var span1_a =document.createElement("a");
		var a_img=document.createElement("img");

		var span2=document.createElement("span");
		var span2_a=document.createElement("a");

		var span3=document.createElement("span");
		var span4=document.createElement("span");
		var span5=document.createElement("span");
		var span6=document.createElement("span");

		var div2=document.createElement("div");
		//var button1=document.createElement("button");

		if(state=="待付款"){
			var button1=document.createElement("button");
			var button2=document.createElement("button");
			var br=document.createElement("br");
			button1.innerHTML="付款";
			button2.innerHTML="取消";
			//$(botton1).attr("data-toggle","modal");
			$(button1).click(function(){
				$(this).modal("toggle");
				$(this).target("#myModal")
			});
			//$(botton1).attr("data-target","#myModal");
			$(div2).append(button1,br,button2);
		}

		$(div1).addClass("row goods_item");
		$(span1).addClass("col-lg-2  goods_pic");
		$(span2).addClass("col-lg-3 goods_name");
		$(span3).addClass("col-lg-1");
		$(span4).addClass("col-lg-1");
		$(span5).addClass("col-lg-1");
		$(span6).addClass("col-lg-2");
		$(div2).addClass("col-lg-2");
		$(span1_a).append(a_img);
		$(span1).append(span1_a);
		
		$(span2).append(span2_a);
		
		$(".list").append(div1);  
		$(".goods_item").append(span1,span2,span3,span4,span5,span6,div2);


		$(a_img).attr("src",pic);
		$(span2_a).attr("href",url);
		span2_a.innerHTML=good;
		span3.innerHTML=price;
		span4.innerHTML=num;
		span5.innerHTML=parseFloat(price)*parseFloat(num);
		span6.innerHTML=state;
	
		
	
	}
	*/
	/*添加出售中的宝贝*/
	function appendText(pic, good, url, price, num, button_name) {
		var myTreasure = "<div class='row goods_item'>" +
		"<span class='col-lg-3 col-md-3 goods_pic'>" +
		"<a>" +
		"<img src='" + pic + "'>" +
		"</a>" +
		"</span>" +
		"<span class='col-lg-3 goods_name'>" +
		"<a href='" + url + "'>" + good + "</a>" +
		"</span>" +
		"<span class='col-lg-2'>" + price + "</span>" +
		"<span class='col-lg-2'>" + num + "</span>" +
		"<div class='col-lg-2'>" +
		"<button>" + button_name + "</button>" +
		"</div>" +
		"</div>";

		$(".list").append(myTreasure);
	}	
	/*添加出卖家订单*/
	function appendOrderSeller(pic, good, url, price, num, state) {
		var str="";
		var sum=parseFloat(price)*parseFloat(num);
		if(state=="待发货"){
			str="<button onclick='window.location.reload()'>发货</button>"//待收货
		}else if(state=="申请退款"){
			str="<button data-toggle='modal' data-target='#agree'>同意退款</button>"+"<br>"+//已关闭
			"<button onclick='window.location.reload()'>拒绝</button>"//待发货
		}else if(state=="申请退货"){
			str="<button data-toggle='modal' data-target='#agree'>同意退货</button>"+"<br>"+//待退货
			"<button onclick='window.location.reload()'>拒绝</button>"//待收货
		}else if(state=="退货中"){
			str="<button data-toggle='modal' data-target='#refund'>确认退款</button>"//已关闭
		}else if(state=="已成功"||state=="已关闭"){
			str="<button onclick='window.location.reload()'>删除</button>"
		}
		var myTreasure = "<div class='row goods_item'>" +
		"<span class='col-lg-2 col-md-2 goods_pic'>" +
		"<a>" +
		"<img src='" + pic + "'>" +
		"</a>" +
		"</span>" +
		"<span class='col-lg-3 goods_name'>" +
		"<a href='" + url + "'>" + good + "</a>" +
		"</span>" +
		"<span class='col-lg-1'>" + price + "</span>" +
		"<span class='col-lg-1'>" + num + "</span>" +
		"<span class='col-lg-1'>" + sum + "</span>" +
		"<span class='col-lg-2'>" + state + "</span>" +
		"<div class='col-lg-2'>" +
		str +
		"</div>" +
		"</div>";

		$(".list").append(myTreasure);
	}	
	/*各种提示*/
	function tip1(){
		alert("您已申请退货！请耐心等待卖家审核！");
	}
	function tip2(){
		alert("您已申请退款！请耐心等待卖家审核！");
	}

	/*添加买家订单*/
	function appendOrderCustomer(pic, good, url, price, num, state) {
		var str="";
		var sum=parseFloat(price)*parseFloat(num);
		if(state=="待付款"){
			str="<button data-toggle='modal' data-target='#pay'>付款</button>"+"<br>"+//待发货
			"<button data-toggle='modal' data-target='#cancel_order'>取消</button>"//已关闭
		}else if(state=="待收货"){
			str="<button data-toggle='modal' data-target='#confirm'>确认收货</button>"+"<br>"+//待评价
			"<button onclick='tip1(); window.location.reload()'>退货</button>"//申请退货
		}else if(state=="待发货"){
			str="<button onclick='tip2(); window.location.reload()'>退款</button>"//申请退款
		}/*else if(state=="待评价"){
			str="<button data-toggle='modal' data-target='#evaluate'>评价</button>"+"<br>"+
			"<button onclick='window.location.reload()'>删除</button>"
		}*/else if(state=="待退货"){
			str="<button onclick='window.location.reload()'>退货</button>"//退货中
		}else if(state=="已成功"||state=="已关闭"){
			str="<button onclick='window.location.reload()'>删除</button>"
		}
		var myTreasure = "<div class='row goods_item'>" +
		"<span class='col-lg-2 col-md-2 goods_pic'>" +
		"<a>" +
		"<img src='" + pic + "'>" +
		"</a>" +
		"</span>" +
		"<span class='col-lg-3 goods_name'>" +
		"<a href='" + url + "'>" + good + "</a>" +
		"</span>" +
		"<span class='col-lg-1'>" + price + "</span>" +
		"<span class='col-lg-1'>" + num + "</span>" +
		"<span class='col-lg-1'>" + sum + "</span>" +
		"<span class='col-lg-2'>" + state + "</span>" +
		"<div class='col-lg-2'>" +
		str +
		"</div>" +
		"</div>";

		$(".list").append(myTreasure);
	}	

	/*改变首页展示框的内容*/
	function changeInner(item,src,href){//item{.firsr|.second|.third|.fourth} 分别代表各个展示框  
		var pic=item+" "+"img";			//src 图片链接  href  商品网址
		var url=item+" "+"a";
		$(pic).attr("src",src);
		$(url).attr("href",href);
	}
	/* 改变数量*/
	function add(){
		var num=$("#num").val();
		$("#num").attr("value",parseInt(num)+1);
	}
	function del(){
		var num=$("#num").val();
		if(parseInt(num)>1){
			$("#num").attr("value",parseInt(num)-1);
		}
	}

	/*添加选项*/
	function appendRadio(classname, name){		//classname{size|color}选择添加颜色或者尺码选项，name radio中的name值
		var str="<input type='radio' name='"+ classname +"' value='"+ name + "'><span>"+ name + "</span>";
		if(classname=="size"){
			$("#size").append(str);
		}else if(classname=="color"){
			$("#color").append(str);
		}

	}
	
	/*添加图片*/
	function addPic(url) {
		$("#pic_group").append("<img src='" + url + "'><br><br>");
	}
	/*添加已存在的收货地址*/
	function apendAddress (aid, name) { //地址名称
		var str="<tr class='info'>"+
		"<td class='col-lg-10 col-md-10'>" +name+"</td>"+
		"<td class='col-lg-2 col-md-2'>"+
		"<button class='r_button' onclick='deleteAddress()' id='"+ aid +"'>删除</button>"+
		"</td>"+
		"</tr>";
		$("#address_table").append(str);
	}