var loadPage = $.ajax({
    type:'POST',
    url:"http://bbd.songzhj.com/home_action",
    data:{user_type:"seller"},
    dataType:"json",
    success: function (data) {
    	alert("222");
        $("#span1").html(data.wait_pay);
        $("#span2").html(data.wait_out);
        $("#span3").html(data.already_out);
        $("#span4").html(data.refund);
        $("#name").html(data.user_id);
    }
});