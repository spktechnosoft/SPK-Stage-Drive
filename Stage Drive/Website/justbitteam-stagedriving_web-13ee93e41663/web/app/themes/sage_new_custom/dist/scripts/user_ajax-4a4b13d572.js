function DeleteEventByid(e,a){spinnerInit("start");var t=SD_API_EVENTS+"/"+e;jQuery.ajax({dataType:"json",headers:{Authorization:"Bearer "+a},url:t,type:"DELETE",async:!0,contentType:"application/json",success:function(e,a,t){spinnerInit("stop"),window.showAdvMessage("Evento eliminato","Il tuo evento è stato eliminato!",function(){location.href="/my-events"})},error:function(e){console.log("Error --> ",e),spinnerInit("stop"),window.showAdvMessage("Ops..","Il tuo evento non è stato eliminato, prova più tardi!",function(){location.href="/my-events"})}})}window.showMessage=function(e,a,t){jQuery.fancybox.open('<div class="message"><h3>'+e+"</h3><p>"+a+"</p></div>")},window.showMessageDeleteEvent=function(e,a,t,n){var o='<div class="message"><h2>'+e+"</h2><p>"+a+'</p><p class="delete-event value-delete-event" style="float:left;">SI</p><p class="value-delete-event" data-value="0" data-fancybox-close style="float:right;">NO</p></div>';jQuery.fancybox.open(o),jQuery(".delete-event").click(function(){console.log(" click delete event with id --> ",t),jQuery.fancybox.close(!0),DeleteEventByid(t,n)})},window.showAdvMessage=function(e,a,t){jQuery.fancybox.close(!0),opts=jQuery.extend(!0,{title:e,message:a,okButton:"OK",noButton:"Annulla",callback:t},{}),jQuery.fancybox.open({type:"html",src:'<div class="fc-content"><h3>'+opts.title+"</h3><p>"+opts.message+'</p><p class="tright"><a class="detail-button" data-fancybox-close data-value="1" style="cursor: pointer;">'+opts.okButton+"</a></p></div>",opts:{animationDuration:350,animationEffect:"material",infobar:!1,toolbar:!1,modal:!1,hash:!1,baseTpl:'<div class="fancybox-container fc-container" role="dialog" tabindex="-1"><div class="fancybox-bg"></div><div class="fancybox-inner"><div class="fancybox-stage"></div></div></div>',afterClose:function(e,a,t){var n=t?t.target||t.currentTarget:null,o=n?jQuery(n).data("value"):0;opts.callback&&opts.callback(o)}}})},window.showConfirm=function(e){jQuery.fancybox.close(!0),e=jQuery.extend(!0,{title:"Non sei registrato",message:"",okButton:"OK",noButton:"Non mi interessa",callback:jQuery.noop},e||{}),jQuery.fancybox.open({type:"html",src:'<div class="fc-content"><h3>'+e.title+"</h3><p>"+e.message+'</p><p class="tright"><a data-value="1" class="detail-button" data-fancybox-close>'+e.okButton+'</a><a data-value="0" class="detail-button" data-fancybox-close>'+e.noButton+"</a></p></div>",opts:{animationDuration:350,animationEffect:"material",infobar:!1,toolbar:!1,modal:!1,hash:!1,baseTpl:'<div class="fancybox-container fc-container" role="dialog" tabindex="-1"><div class="fancybox-bg"></div><div class="fancybox-inner"><div class="fancybox-stage"></div></div></div>',afterClose:function(a,t,n){var o=n?n.target||n.currentTarget:null,s=o?jQuery(o).data("value"):0;e.callback(s)}}})};