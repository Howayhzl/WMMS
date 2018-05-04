var oldName=null;
var oldId=null;
/*左侧菜单点击*/
$(".side-menu").on('click', 'li a', function(e) {
	    var animationSpeed = 300;
	    var $this = $(this);
	    var checkElement = $this.next();
	    if (checkElement.is('.menu-item-child') && checkElement.is(':visible')) {// 已经 有二级菜单并可见
	        if(checkElement.parent().parent().hasClass('side-menu')==false){
	          checkElement.slideUp(animationSpeed, function() {
	                //checkElement.parents('.menu-item-child').removeClass('menu-open').hide();
	                checkElement.removeClass('menu-open');
	          });
	          checkElement.parent("li").removeClass("active");
	          checkElement.parents(".menu-item").removeClass("active");
	          checkElement.parents(".menu-item-child").find('.sign img').attr('src',""+projectName+"/static/image/xiao.png");
	          var parImg=$(checkElement.parents('.menu-item').find('img').get(0)).attr('src').split('image/')[1];
	          if(parImg.indexOf("-active")==-1){
	                var newParImg=parImg.split('.')[0];
	            }else{
	                var newParImg=parImg.split('-active')[0];
	            }
	//          $(checkElement.parents('.menu-item').find('img').get(0)).attr('src',""+projectName+"/static/image/"+newParImg+".png");//设置菜单背景图（白色）
	          }else{
	               checkElement.slideUp(animationSpeed, function() {
	                //checkElement.find('.menu-item-child').removeClass('menu-open').hide();//点击无子集菜单li影藏展开部分
	               checkElement.removeClass('menu-open');
	              });
	              checkElement.find("li").removeClass("active");
	              checkElement.parents(".menu-item").removeClass("active");
	              var parImg=checkElement.prev().find('img').attr('src').split('image/')[1]
	             
	               if(parImg.indexOf("-active")==-1){
	                    var newParImg=parImg.split('.')[0];
	                }else{
	                    var newParImg=parImg.split('-active')[0];
	                }
	//              checkElement.prev().find('img').attr('src',""+projectName+"/static/image/"+newParImg+".png");
	      }
	      /*$('.wrap').css('width',"120px");
	      $('.menu_l').css('width',"120px");*/
	} //如果菜单是不可见的
	    else if ((checkElement.is('.menu-item-child')) && (!checkElement.is(':visible'))) {// 已经 有二级菜单并不可见
	       //获取上级菜单
	      var parent = $this.parents('ul').first();
	      //从父级开始找所有打开的菜单并关闭
	      var ul = parent.find('ul:visible').slideUp(animationSpeed);
	      //在父级中移出menu-open标记
	      ul.removeClass('menu-open');
	     
	      if(oldName!=null&&parent.hasClass('menu-item-child')==false){
	          for(var s=0; s<menus.length; s++){   
	            var item = menus[s];
	                if(item.name==oldName){
	                    if(item.childMenus){
	                        $("#"+oldId).parent().children('img').attr('src',""+projectName+"/static/image/"+item.icon+".png");
	                    }
	                }
	            }
	        }
	      
	      //获取父级li
	      var parent_li = $this.parent("li");
	      $('.wrap').css('width',"400px");
	      $('.menu_l').css('width',"404px");
	//打开菜单时添加menu-open标记
	checkElement.slideDown(animationSpeed, function() {
	        //添加样式active到父级li
	    checkElement.addClass('menu-open');
	    parent.find('li.active').removeClass('active');
	    parent_li.addClass('active');
	    if(parent_li.hasClass('menu-item')){
	        if(parent_li.parents('.menu-item').find('.menu-item-child').eq(0).css('display')=='block'){
	            
	            $(parent_li.parents('.menu-item').find('img').get(0)).attr('src',""+projectName+"/static/image/"+item.icon+"-active.png");
	        }else{
	            $(parent_li.parents('.menu-item').find('img').get(0)).attr('src',""+projectName+"/static/image/"+item.icon+".png");
	        }
	        $(parent_li).find('ul img').attr('src',""+projectName+"/static/image/xiao.png");
	     }else{
	        if(parent_li.hasClass('degre2')){
	            $(parent_li.find('img').get(0)).attr('src',""+projectName+"/static/image/xiao-active.png");
	            var otherImg=$(parent_li).siblings();
	            for(var i=0;i<otherImg.length;i++){
	                $(otherImg.eq(i).find('img').get(0)).attr('src',""+projectName+"/static/image/xiao.png");
	                otherImg.eq(i).removeClass('active');
	                $(otherImg.eq(i).find('.degre2').get(0)).removeClass(' menu-open').hide();
	                $($(otherImg.eq(i).find('.degre2').get(0)).find('.degre3 img').get(0)).attr('src',""+projectName+"/static/image/xiao.png");
	                
	                if(otherImg.eq(i).find('.degre3').length>0){
	                    $(otherImg.eq(i).find('.degre3').find('img').get(0)).attr('src',""+projectName+"/static/image/xiao.png");
	                }
	            }
	        }
	        if(parent_li.hasClass('degre3')){
	            $(parent_li.find('img').get(0)).attr('src',""+projectName+"/static/image/xiao-active.png");
	            var otherImg=$(parent_li).siblings();
	            for(var i=0;i<otherImg.length;i++){
	                $(otherImg.eq(i).find('img').get(0)).attr('src',""+projectName+"/static/image/xiao.png");
	                otherImg.eq(i).removeClass('active');
	                $(otherImg.eq(i).find('.degre3').get(0)).removeClass(' menu-open').hide();
	                if(otherImg.eq(i).find('.degre4').length>0){
	                    $(otherImg.eq(i).find('.degre4').find('img').get(0)).attr('src',""+projectName+"/static/image/xiao.png");
	                }
	             }
	          }
	       }
	   });
	}
		for(var i=0; i<menus.length; i++){   
		    var item = menus[i];
		    if(item.name==$this.children("span").text()){
		        if($this.parents('.menu-item').find('.menu-item-child').length>0){
		        setTimeout(function(){
		            if($this.parents('.menu-item').find('.menu-item-child').eq(0).css('display')=='block'){
		                
		                $this.children('img').attr('src',""+projectName+"/static/image/"+item.icon+"-active.png");
		            }else{
		                $this.children('img').attr('src',""+projectName+"/static/image/"+item.icon+".png");
		            }
		        },500)
		        }else{
		            $this.children('img').attr('src',""+projectName+"/static/image/"+item.icon+"-active.png");
		
		        }
		//          oldName=item.name;
		        oldId=item.menuId;
		        $this.append("<input type='hidden' id="+item.menuId+" />");
		        var otherImg=$this.parent('.menu-item').siblings();
		        
		        for(var i=0;i<otherImg.length;i++){
		            
		            var curImg=$(otherImg.eq(i).find('img').get(0)).attr('src').split("image/")[1];
		            
		            if(curImg.indexOf("-active")==-1){
		                var newImg=curImg.split('.')[0];
		            }else{
		                
		                var newImg=curImg.split('-active.')[0];
		            }
		            $(otherImg.eq(i).find('img').get(0)).attr('src',""+projectName+"/static/image/"+newImg+".png");
		            otherImg.eq(i).removeClass('active');
		            $(otherImg.eq(i).find('.menu-item-child').get(0)).removeClass(' menu-open').hide();
		        }
		    }
		}
		    //防止有链接跳转
		    e.preventDefault();
		    addIframe($this);
});
function siblingReset(obj){
    
}
//点击空白影藏2级菜单    .menu_l部分
$('body').on('click',function(e){  
    var evt=e.target;
    if($(evt).hasClass('menu_l')==true||$(evt).parents().hasClass('menu_l')==false){
    	$('.menu-item').removeClass('active');
      $('.menu-item-child').removeClass('menu-open');
      $('.menu-item-child').css('display','none');
      $('.wrap').css('width',"120px");
      $('.menu_l').css('width',"120px");
      var otherImg=$('.menu-item');
      
      for(var i=0;i<otherImg.length;i++){
          
          var curImg=$(otherImg.eq(i).find('img').get(0)).attr('src').split("image/")[1];
          
          if(curImg.indexOf("-active")==-1){
              var newImg=curImg.split('.')[0];
          }else{
              
              var newImg=curImg.split('-active.')[0];
          }
          $(otherImg.eq(i).find('img').get(0)).attr('src',""+projectName+"/static/image/"+newImg+".png");
          otherImg.eq(i).removeClass('active');
          $(otherImg.eq(i).find('.menu-item-child').get(0)).removeClass(' menu-open').hide();
      }
    }
});


/*添加iframe*/
function addIframe(cur){
    var $this = cur;
    var h = $this.attr("href"),
        m = $this.data("index"),
        label = $this.find("span").text(),
        isHas = false;
    if (h == "" || $.trim(h).length == 0) {
        return false;
    }
    
    var fullWidth = $(window).width();
    if(fullWidth >= 750){
        $(".layout-side").show();
    }else{
        $(".layout-side").hide();
    }
    
    $(".content-tab").each(function() {
        if ($(this).data("id") == h) {
            if (!$(this).hasClass("active")) {
                $(this).addClass("active").siblings(".content-tab").removeClass("active");
                addTab(this);
            }
            isHas = true;
        }
    });
    if(isHas){
        $(".body-iframe").each(function() {
            if ($(this).data("id") == h) {
                $(this).show().siblings(".body-iframe").hide();
            }
        });
    }
    if (!isHas) {
        var tab = "<a href='javascript:;' class='content-tab active' data-id='"+h+"'>"+ label +" <i class='iconfont icon-guanbi'>&#xe675;</i></a>";
        $(".content-tab").removeClass("active");
        $(".tab-nav-content").append(tab);
        var iframe = "<iframe class='body-iframe' name='iframe"+ m +"'  width='100%' height='99%' src='"+ h +"' frameborder='0' data-id='"+ h +"' seamless></iframe>";
        $(".layout-main-body").find("iframe.body-iframe").hide().parents(".layout-main-body").append(iframe);
        addTab($(".content-tab.active"));
    }
    return false;
}


/*添加iframe 子页面打开 */
function addIframeTop(href,index,text){
    var h = href,
        m = index,
        label = text,
        isHas = false;
    if (h == "" || $.trim(h).length == 0) {
        return false;
    }
    
    var fullWidth = $(window).width();
    if(fullWidth >= 750){
        $(".layout-side").show();
    }else{
        $(".layout-side").hide();
    }
    
    $(".content-tab").each(function() {
        if ($(this).data("id") == h) {
            if (!$(this).hasClass("active")) {
                $(this).addClass("active").siblings(".content-tab").removeClass("active");
                addTab(this);
            }
            isHas = true;
        }
    });
    if(isHas){
        $(".body-iframe").each(function() {
            if ($(this).data("id") == h) {
                $(this).show().siblings(".body-iframe").hide();
            }
        });
    }
    if (!isHas) {
        var tab = "<a href='javascript:;' class='content-tab active' data-id='"+h+"'>"+ label +" <i class='iconfont icon-guanbi'>&#xe675;</i></a>";
        $(".content-tab").removeClass("active");
        $(".tab-nav-content").append(tab);
        var iframe = "<iframe class='body-iframe' name='iframe"+ m +"' width='100%' height='99%' src='"+ h +"' frameborder='0' data-id='"+ h +"' seamless></iframe>";
        $(".layout-main-body").find("iframe.body-iframe").hide().parents(".layout-main-body").append(iframe);
        addTab($(".content-tab.active"));
    }
    return false;
}
/*添加tab*/
function addTab(cur) {
    var prev_all = tabWidth($(cur).prevAll()),
        next_all = tabWidth($(cur).nextAll());
    var other_width =tabWidth($(".layout-main-tab").children().not(".tab-nav"));
    var navWidth = $(".layout-main-tab").outerWidth(true)-other_width;//可视宽度
    var hidewidth = 0;
    if ($(".tab-nav-content").width() < navWidth) {
        hidewidth = 0
    } else {
        if (next_all <= (navWidth - $(cur).outerWidth(true) - $(cur).next().outerWidth(true))) {
            if ((navWidth - $(cur).next().outerWidth(true)) > next_all) {
                hidewidth = prev_all;
                var m = cur;
                while ((hidewidth - $(m).outerWidth()) > ($(".tab-nav-content").outerWidth() - navWidth)) {
                    hidewidth -= $(m).prev().outerWidth();
                    m = $(m).prev()
                }
            }
        } else {
            if (prev_all > (navWidth - $(cur).outerWidth(true) - $(cur).prev().outerWidth(true))) {
                hidewidth = prev_all - $(cur).prev().outerWidth(true)
            }
        }
    }
    $(".tab-nav-content").animate({
        marginLeft: 0 - hidewidth + "px"
    },
    "fast");
}

/*获取宽度*/
function tabWidth(tabarr) {
    var allwidth = 0;
    $(tabarr).each(function() {
        allwidth += $(this).outerWidth(true)
    });
    return allwidth;
}

/*左按钮事件*/
$(".btn-left").on("click", leftBtnFun);
/*右按钮事件*/
$(".btn-right").on("click", rightBtnFun);
/*选项卡切换事件*/
$(".tab-nav-content").on("click", ".content-tab", navChange);
/*选项卡关闭事件*/
$(".tab-nav-content").on("click", ".content-tab i", closePage);
/*选项卡双击关闭事件*/
$(".tab-nav-content").on("dblclick", ".content-tab", closePage);


/*左按钮方法*/
function leftBtnFun() {
    var ml = Math.abs(parseInt($(".tab-nav-content").css("margin-left")));
    var other_width = tabWidth($(".layout-main-tab").children().not(".tab-nav"));
    var navWidth = $(".layout-main-tab").outerWidth(true)-other_width;//可视宽度
    var hidewidth = 0;
    if ($(".tab-nav-content").width() < navWidth) {
        return false
    } else {
        var tabIndex = $(".content-tab:first");
        var n = 0;
        while ((n + $(tabIndex).outerWidth(true)) <= ml) {
            n += $(tabIndex).outerWidth(true);
            tabIndex = $(tabIndex).next();
        }
        n = 0;
        if (tabWidth($(tabIndex).prevAll()) > navWidth) {
            while ((n + $(tabIndex).outerWidth(true)) < (navWidth) && tabIndex.length > 0) {
                n += $(tabIndex).outerWidth(true);
                tabIndex = $(tabIndex).prev();
            }
            hidewidth = tabWidth($(tabIndex).prevAll());
        }
    }
    $(".tab-nav-content").animate({
        marginLeft: 0 - hidewidth + "px"
    },
    "fast");
}

/*右按钮方法*/
function rightBtnFun() {
    var ml = Math.abs(parseInt($(".tab-nav-content").css("margin-left")));
    var other_width = tabWidth($(".layout-main-tab").children().not(".tab-nav"));
    var navWidth = $(".layout-main-tab").outerWidth(true)-other_width;//可视宽度
    var hidewidth = 0;
    if ($(".tab-nav-content").width() < navWidth) {
        return false
    } else {
        var tabIndex = $(".content-tab:first");
        var n = 0;
        while ((n + $(tabIndex).outerWidth(true)) <= ml) {
            n += $(tabIndex).outerWidth(true);
            tabIndex = $(tabIndex).next();
        }
        n = 0;
        while ((n + $(tabIndex).outerWidth(true)) < (navWidth) && tabIndex.length > 0) {
            n += $(tabIndex).outerWidth(true);
            tabIndex = $(tabIndex).next()
        }
        hidewidth = tabWidth($(tabIndex).prevAll());
        if (hidewidth > 0) {
            $(".tab-nav-content").animate({
                marginLeft: 0 - hidewidth + "px"
            },
            "fast");
        }
    }
}

/*选项卡切换方法*/
function navChange() {
    if (!$(this).hasClass("active")) {
        var k = $(this).data("id");
        $(".body-iframe").each(function() {
            if ($(this).data("id") == k) {
            	$(this).show();
                $(this).css({'height':($(window).height()-180)+'px','overflowY':'scroll'});
                $(this).siblings(".body-iframe").hide();
                return false;
            }
        });
        $(this).addClass("active").siblings(".content-tab").removeClass("active");
        addTab(this);
    }
}

/*选项卡关闭方法*/
function closePage() {
    var url = $(this).parents(".content-tab").data("id");
    var cur_width = $(this).parents(".content-tab").width();
    if ($(this).parents(".content-tab").hasClass("active")) {
        if ($(this).parents(".content-tab").next(".content-tab").size()) {
            var next_url = $(this).parents(".content-tab").next(".content-tab:eq(0)").data("id");
            $(this).parents(".content-tab").next(".content-tab:eq(0)").addClass("active");
            $(".body-iframe").each(function() {
                if ($(this).data("id") == next_url) {
                    $(this).show().siblings(".body-iframe").hide();
                    return false
                }
            });
            var n = parseInt($(".tab-nav-content").css("margin-left"));
            if (n < 0) {
                $(".tab-nav-content").animate({
                    marginLeft: (n + cur_width) + "px"
                },
                "fast")
            }
            $(this).parents(".content-tab").remove();
            $(".body-iframe").each(function() {
                if ($(this).data("id") == url) {
                    $(this).remove();
                    return false
                }
            })
        }
        if ($(this).parents(".content-tab").prev(".content-tab").size()) {
            var prev_url = $(this).parents(".content-tab").prev(".content-tab:last").data("id");
            $(this).parents(".content-tab").prev(".content-tab:last").addClass("active");
            $(".body-iframe").each(function() {
                if ($(this).data("id") == prev_url) {
                    $(this).show().siblings(".body-iframe").hide();
                    return false
                }
            });
            $(this).parents(".content-tab").remove();
            $(".body-iframe").each(function() {
                if ($(this).data("id") == url) {
                    $(this).remove();
                    return false
                }
            })
        }
    } else {
        $(this).parents(".content-tab").remove();
        $(".body-iframe").each(function() {
            if ($(this).data("id") == url) {
                $(this).remove();
                return false
            }
        });
        addTab($(".content-tab.active"))
    }
    return false
}
var menus=null;
function initData(menu){
    menus=menu;
}

/*循环菜单*/
function initMenu(menu,parent){
    
    for(var i=0; i<menu.length; i++){   
        var item = menu[i];
        var str = "";
        try{
            if(item.isHeader == "1"){
                str = "<li class='menu-header'>"+item.name+"</li>";
                $(parent).append(str);
                if(item.childMenus != ""){
                    initMenu(item.childMenus,parent);
                }
            }else{
                if(item.childMenus == ""){
                    if(item.icon==''){
                        
                        str = "<li><a onclick='openWin(this,\""+item.url+"\",\""+item.menuId+"\")'><span>"+item.name+"</span></a></li>";
                    }else{
                        if(item.icon=='xiao'&&item.parentId.length=='5'){
                            str = "<li><a class='level3' onclick='openWin(this,\""+item.url+"\",\""+item.menuId+"\")'><img src=\""+projectName+"/static/image/"+item.icon+".png\"/><span>"+item.name+"</span></a></li>";

                        }else if(item.menuId.length=='5'){
                            str = "<li><a onclick='openWin(this,\""+item.url+"\",\""+item.menuId+"\")'><img src=\""+projectName+"/static/image/"+item.icon+".png\"/><span>"+item.name+"</span></a></li>";

                        }else{
                        str = "<li><a onclick='openWin(this,\""+item.url+"\",\""+item.menuId+"\")'><span>"+item.name+"</span></a></li>";
                        }
                    }
                    $(parent).append(str);
                }else{
                    //判断级别
                    var leve_class="";
                    if(item.code.length=='6'){
                        leve_class='degre2';
                    }else if(item.code.length=='8'){
                        leve_class='degre3';
                    }else if(item.code.length=='10'){
                        leve_class='degre4';
                    }else if(item.code.length=='12'){
                        leve_class='degre5';
                    }
                    str = "<li class='"+leve_class+"'><a class='sign'><img src=\""+projectName+"/static/image/"+item.icon+".png\"/><span>"+item.name+"</span></a>";
                    str +="<ul class='menu-item-child "+leve_class+"' id='menu-child-"+item.menuId+"'></ul></li>";
                    $(parent).append(str);
                    var childParent = $("#menu-child-"+item.menuId);
                    initMenu(item.childMenus,childParent);
                }
            }
        }catch(e){}
    }
}

function openWin(obj, url, menuId,icon)
{
    ajaxRecordMenuId(menuId);
    obj.href = url;
    $(obj).parents('.menu-item-child').hide();
    var curSrc=$(obj).parents('.menu-item').find(".sign img").attr('src');
    if(curSrc!=='undefined'&&curSrc!==undefined){
        if($(obj).hasClass('level3')==false){
        $(obj).parent().parent().parent().parent().find('.sign img').attr('src',""+projectName+"/static/image/xiao.png");
        var curImg=$($(obj).parents('.menu-item').find('.sign').get(0)).find("img").attr('src').split('image')[1];
        var newImg=curImg.split('/')[1].split('.')[0].split('-')[0];
        $($(obj).parents('.menu-item').find('.sign').get(0)).find("img").attr('src',""+projectName+"/static/image/"+newImg+".png");
        }else{
            //没有二级菜单
            $(obj).find('.sign img').attr('src',""+projectName+"/static/image/xiao.png");
            var curImg=$($(obj).parents('.menu-item').find('.sign').get(0)).find("img").attr('src').split('image')[1];
            var newImg=curImg.split('/')[1].split('.')[0].split('-')[0];
            $($(obj).parents('.menu-item').find('.sign').get(0)).find("img").attr('src',""+projectName+"/static/image/"+newImg+".png");
        }
    }else{
        var otherImg=$(obj).parents('.menu-item').siblings();
        for(var i=0;i<otherImg.length;i++){
            var curImg=$(otherImg.eq(i).find('img').get(0)).attr('src').split("image/")[1];
            
            if(curImg.indexOf("-active")==-1){
                var newImg=curImg.split('.')[0];
            }else{
                var newImg=curImg.split('-active.')[0];
            }
            $(otherImg.eq(i).find('img').get(0)).attr('src',""+projectName+"/static/image/"+newImg+".png");
        }
    }
   
    $('.wrap').css('width',"120px");
    $('.menu_l').css('width',"120px");
}

/**系统记录用户点击按钮id*/
function ajaxRecordMenuId(menuId){
    myajax.json_ajax({"url":"saveUserClickedMenuId","type":"post","data":{"menuId":menuId}});
}



/*头部下拉框移入移出*/
$(document).on("mouseover",".header-bar-nav",function(){
    $(this).addClass("open");
});
$(document).on("mouseout",".header-bar-nav",function(){
    $(this).removeClass("open");
});

/*左侧菜单展开和关闭按钮事件*/
/*$(document).on("click",".layout-side-arrow",function(){
    if($(".layout-side").hasClass("close")){
        $(".layout-side").removeClass("close");
        $(".layout-main").removeClass("full-page");
        $(".layout-footer").removeClass("full-page");
        $(this).removeClass("close");
        $(".layout-side-arrow-icon").removeClass("close");
    }else{
        $(".layout-side").addClass("close");
        $(".layout-main").addClass("full-page");
        $(".layout-footer").addClass("full-page");
        $(this).addClass("close");
        $(".layout-side-arrow-icon").addClass("close");
    }
});*/
/*$(document).on("click",".layout-side-arrow",function(){
    var sideWidth=$('.layout-side').width();
    if(sideWidth==209){
        $('.layout-side').addClass('aleft');//菜单宽度变成40px
        $('.layout-side>.side-menu>.menu-item span').hide();
        $('.layout-side>.side-menu>.menu-item i').show();
        $('.layout-side>.side-menu>.menu-item span').next('i').hide();
        $('.layout-main').animate({left:'40px'},".5s");//section向左移动
        $('.layout-side-arrow').addClass('switchover');//图标切换
        $('.side-menu .menu-item.active .menu-open>a>span').next('i').hide();
    }else{
        $('.layout-side-arrow').removeClass('switchover');
        $('.layout-side>.side-menu>.menu-item span').css('display','inline');
        $('.layout-side>.side-menu>.menu-item i').css('display','inline');
        $('.layout-side>.side-menu>.menu-item span').next('i').css('display','inline');
        $('.layout-side').removeClass('aleft').animate('0.3s');
        $('.layout-main').animate({left:'210px'},".5s");//section向右移动
    }
})*/
/*头部菜单按钮点击事件*/
$(".header-menu-btn").click(function(){
    $(".layout-side").removeClass("close");
    $(".layout-main").removeClass("full-page");
    $(".layout-footer").removeClass("full-page");
    $(".layout-side-arrow").removeClass("close");
    $(".layout-side-arrow-icon").removeClass("close");
    $(".layout-side").slideToggle();
});

/*左侧菜单响应式*/
$(window).resize(function() {  
    var width = $(this).width();  
    if(width >= 750){
        $(".layout-side").show();
    }else{
        $(".layout-side").hide();
    }
});

/*皮肤选择*/
$(".dropdown-skin li a").click(function(){
    var v = $(this).attr("data-val");
    var hrefStr=$("#layout-skin").attr("href");
    var hrefRes=hrefStr.substring(0,hrefStr.lastIndexOf('skin/'))+'skin/'+v+'/skin.css';
    $(window.frames.document).contents().find("#layout-skin").attr("href",hrefRes);
    
    setCookie("scclui-skin", v);
});

/*获取cookie中的皮肤*/
function getSkinByCookie(){
    var v = getCookie("scclui-skin");
    var hrefStr=$("#layout-skin").attr("href");
    if(v == null || v == ""){
        v="qingxin";
    }
    if(hrefStr != undefined){
        var hrefRes=hrefStr.substring(0,hrefStr.lastIndexOf('skin/'))+'skin/'+v+'/skin.css';
        $("#skin").attr("href",hrefRes);
    }
}

/*随机颜色*/
function getMathColor(){
    var arr = new Array();
    arr[0] = "#ffac13";
    arr[1] = "#83c44e";
    arr[2] = "#2196f3";
    arr[3] = "#e53935";
    arr[4] = "#00c0a5";
    arr[5] = "#16A085";
    arr[6] = "#ee3768";

    var le = $(".menu-item > a").length;
    for(var i=0;i<le;i++){
        var num = Math.round(Math.random()*5+1);
        var color = arr[num-1];
        $(".menu-item > a").eq(i).find("i:first").css("color",color);
    }
}

/*
  初始化加载
*/
/*$(function(){
    获取皮肤*/
    //getSkinByCookie();

    /*菜单json
    var menu = [{"id":"1","name":"主菜单","parentId":"0","url":"","icon":"","order":"1","isHeader":"1","childMenus":[
                    {"id":"3","name":"商品管理","parentId":"1","url":"","icon":"&#xe604;","order":"1","isHeader":"0","childMenus":[
                        {"id":"4","name":"品牌管理","parentId":"3","url":"static/tpl/test1.html","icon":"","order":"1","isHeader":"0","childMenus":""},
                        {"id":"5","name":"分类管理","parentId":"3","url":"static/tpl/test2.html","icon":"","order":"1","isHeader":"0","childMenus":""}
                    ]},
                    {"id":"6","name":"订单管理","parentId":"1","url":"","icon":"&#xe602;","order":"1","isHeader":"0","childMenus":[
                        {"id":"7","name":"已付款","parentId":"6","url":"static/tpl/home3.html","icon":"","order":"1","isHeader":"0","childMenus":""},
                        {"id":"8","name":"未付款","parentId":"6","url":"static/tpl/home4.html","icon":"","order":"1","isHeader":"0","childMenus":""}
                    ]}
                ]},
                {"id":"2","name":"框架案例","parentId":"0","url":"","icon":"","order":"2","isHeader":"1","childMenus":[
                    {"id":"9","name":"新功能","parentId":"2","url":"","icon":"","order":"1","isHeader":"0","childMenus":""},
                    {"id":"10","name":"多级","parentId":"2","url":"","icon":"","order":"1","isHeader":"0","childMenus":[
                        {"id":"11","name":"一级","parentId":"10","url":"","icon":"","order":"1","isHeader":"0","childMenus":""},
                        {"id":"12","name":"一级","parentId":"10","url":"","icon":"","order":"1","isHeader":"0","childMenus":[
                            {"id":"13","name":"二级","parentId":"12","url":"","icon":"","order":"1","isHeader":"0","childMenus":""},
                            {"id":"14","name":"二级","parentId":"12","url":"","icon":"","order":"1","isHeader":"0","childMenus":[
                                {"id":"15","name":"三级","parentId":"14","url":"","icon":"","order":"1","isHeader":"0","childMenus":""},
                                {"id":"16","name":"三级","parentId":"14","url":"","icon":"","order":"1","isHeader":"0","childMenus":[
                                    {"id":"17","name":"四级","parentId":"16","url":"","icon":"","order":"1","isHeader":"0","childMenus":""},
                                    {"id":"18","name":"四级","parentId":"16","url":"","icon":"","order":"1","isHeader":"0","childMenus":""}
                                ]}
                            ]}
                        ]}
                    ]}
                ]}
                ];*/
    
    /*获取菜单icon随机色
    //getMathColor();
}); */
//处理最后一个菜单
/*function lastMenu(){
	var lastLiH=$('.side-menu>li.menu-item:last-child').prop("offsetTop");
	var h=$(window).height();
	if((lastLiH+180)>h){
		$('.side-menu>li.menu-item:last-child').css('marginBottom','20px');
		$('.side-menu>li.menu-item:last-child').children('.menu-item-child').css('bottom','0px');
	}
}  */
  