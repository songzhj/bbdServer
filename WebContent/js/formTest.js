//表单验证  
$('form :input').blur(function(){
  var $parent = $(this).parent();
  $parent.find(".formtips").remove();
      //验证用户名
      if( $(this).is('#inputUsername') ){
        if( this.value=="" || ( this.value!="" && !/^\w{5,13}$/.test(this.value) ) ){
          var errorMsg = '请输入5-13位字母或数字组成用户名.';
          $parent.append('<span class="formtips onError text-danger">'+errorMsg+'</span>');
        }else{
          var okMsg = '用户名可用.';
          $parent.append('<span class="formtips onSuccess text-success">'+okMsg+'</span>');
        }
      }
      //验证邮件
      if( $(this).is('#inputEmail') ){
        if( this.value=="" || ( this.value!="" && !/.+@.+\.[a-zA-Z]{2,4}$/.test(this.value) ) ){
          var errorMsg = '请输入正确的E-Mail地址.';
          $parent.append('<span class="formtips onError text-danger">'+errorMsg+'</span>');
        }else{
          var okMsg = '该邮箱可用.';
          $parent.append('<span class="formtips onSuccess text-success">'+okMsg+'</span>');
        }
      }
      //验证密码
      if( $(this).is('#inputPassword') ){

        if( this.value=="" || ( this.value!="" && !/^.{6,15}$/.test(this.value) ) ){
          var errorMsg = '请输入正确的密码(6-15位).';
          $parent.append('<span class="formtips onError text-danger">'+errorMsg+'</span>');
        }else{
          var okMsg = '该密码可用.';
          $parent.append('<span class="formtips onSuccess text-success">'+okMsg+'</span>');
        }
      }

      //验证二次密码
      if( $(this).is('#inputConfirmPassword') ){

        if(this.value!="" && this.value != ($("#inputPassword")).val() ){
          var errorMsg = '两次输入密码不一致请更改';
          $parent.append('<span class="formtips onError text-danger">'+errorMsg+'</span>');

        }else{
          if(this.value!="" && /^.{6,15}$/.test(this.value) ){
            var okMsg = '输入密码一致.';
            $parent.append('<span class="formtips onSuccess text-success">'+okMsg+'</span>');
          }
        }
      }


      //验证真实姓名
      if( $(this).is('#realName') ){
        if( this.value=="" || ( this.value!="" && !/^[\u4e00-\u9fa5]{2,4}$/.test(this.value) ) ){
          var errorMsg = '请输入正确的真实姓名.';
          $parent.append('<span class="formtips onError text-danger">'+errorMsg+'</span>');
        }//else

      }

      //验证生日
      if( $(this).is('#birthday') ){
        if( this.value=="" || ( this.value!="" && !/^\d{4}-\d{2}-\d{2}$/.test(this.value) ) ){
          var errorMsg = '请输入正确的出生日期格式xxxx-xx-xx.';
          $parent.append('<span class="formtips onError text-danger">'+errorMsg+'</span>');
        }//else

      }

      //验证身份证号
      if( $(this).is('#idcardNum') ){
        if( this.value=="" || ( this.value!="" && !/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/.test(this.value) ) ){
          var errorMsg = '请输入正确的二代身份证号.';
          $parent.append('<span class="formtips onError text-danger">'+errorMsg+'</span>');
        }else{
          var okMsg = '该二代身份证号可用.';
          $parent.append('<span class="formtips onSuccess text-success">'+okMsg+'</span>');
        }
      }

      //验证手机号
      if( $(this).is('#phoneNum') ){
        if( this.value=="" || ( this.value!="" && !/^\d{12}$/.test(this.value) ) ){
          var errorMsg = '请输入正确的手机号.';
          $parent.append('<span class="formtips onError text-danger">'+errorMsg+'</span>');
        }else{
          var okMsg = '手机号符合要求.';
          $parent.append('<span class="formtips onSuccess text-success">'+okMsg+'</span>');
        }
      }






    }).keyup(function(){
     $(this).triggerHandler("blur");
   }).focus(function(){
     $(this).triggerHandler("blur");
});//end blur





//图片上传预览    IE是用了滤镜。
function previewImage(file)
{
  var MAXWIDTH  = 260; 
  var MAXHEIGHT = 180;
  var div = document.getElementById('preview');
  if (file.files && file.files[0])
  {
    div.innerHTML ='<img id=imghead>';
    var img = document.getElementById('imghead');
    img.onload = function(){
      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
      img.width  =  rect.width;
      img.height =  rect.height;
        //                 img.style.marginLeft = rect.left+'px';
        img.style.marginTop = rect.top+'px';
      }
      var reader = new FileReader();
      reader.onload = function(evt){img.src = evt.target.result;}
      reader.readAsDataURL(file.files[0]);
    }
          else //兼容IE
          {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
              file.select();
              var src = document.selection.createRange().text;
              div.innerHTML = '<img id=imghead>';
              var img = document.getElementById('imghead');
              img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
              var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
              status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
              div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
            }
          }
          function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight )
            {
              rateWidth = width / maxWidth;
              rateHeight = height / maxHeight;

              if( rateWidth > rateHeight )
              {
                param.width =  maxWidth;
                param.height = Math.round(height / rateWidth);
              }else
              {
                param.width = Math.round(width / rateHeight);
                param.height = maxHeight;
              }
            }

            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
          }