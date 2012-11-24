// NOTICE!! DO NOT USE ANY OF THIS JAVASCRIPT
// IT'S ALL JUST JUNK FOR OUR DOCS!
// ++++++++++++++++++++++++++++++++++++++++++
function ajaxrequest(serverPage, tagID,redirect) {
  var  url = serverPage;
     if(redirect==true)
      window.history.pushState("","New Page:"+url,url);

      $(tagID).html('<center><img src="http://labs.shubhamtipstricks.com/assets/img/loader/10.gif"/></center>').load(url).fadeIn();
}
window.onpopstate = function(event) {
  	if (event.state != null) {
	  	ajaxrequest(window.location.pathname,'#load',true);
  	}
};
-->
function showerror(tagID,msg){
$(tagID).fadeIn(1000).html('<div id="error"> <noscript> <div class="error-notice"> <img src="assets/img/notice-error.png" alt="Error" align="left"/> JavaScript is disabled in your browser. For STT Lab to function properly, you must enable JavaScript. If you do not enable JavaScript, certain features in STT Lab will not function correctly. </div> </noscript> <div id="login-status" class="error-notice"> <span class="login-status-icon"></span> <div id="login-status-message">'+msg+'</div> </div> </div>');
}
function showinfo(tagID,msg){
$(tagID).fadeIn(1000).html('<div id="notify"> <noscript> <div class="info-notice"> <img src="assets/img/notice-error.png" alt="Error" align="left"/> JavaScript is disabled in your browser. For STT Lab to function properly, you must enable JavaScript. If you do not enable JavaScript, certain features in STT Lab will not function correctly. </div> </noscript> <div id="login-status" class="info-notice"> <span class="login-status-icon"></span> <div id="login-status-message">'+msg+'</div> </div> </div>');
}
function showsuccess(tagID,msg){
$(tagID).fadeIn(1000).html('<div id="notify"> <noscript> <div class="success-notice"> <img src="assets/img/notice-error.png" alt="Error" align="left"/> JavaScript is disabled in your browser. For STT Lab to function properly, you must enable JavaScript. If you do not enable JavaScript, certain features in STT Lab will not function correctly. </div> </noscript> <div id="login-status" class="success-notice"> <span class="login-status-icon"></span> <div id="login-status-message">'+msg+'</div> </div> </div>');
}
function showanswer(tagID,msg){
$(tagID).fadeIn(1000).html('<div id="answer"> <noscript> <div class="answer-notice"> <img src="assets/img/notice-error.png" alt="Error" align="left"/> JavaScript is disabled in your browser. For STT Lab to function properly, you must enable JavaScript. If you do not enable JavaScript, certain features in STT Lab will not function correctly. </div> </noscript> <div id="login-status" class="answer-notice"> <span class="login-status-icon"></span> <div id="login-status-message">'+msg+'</div> </div> </div>');
}

function showwarn(tagID,msg){
$(tagID).fadeIn(1000).html('<div id="warn"> <noscript> <div class="warn-notice"> <img src="assets/img/notice-error.png" alt="Error" align="left"/> JavaScript is disabled in your browser. For STT Lab to function properly, you must enable JavaScript. If you do not enable JavaScript, certain features in STT Lab will not function correctly. </div> </noscript> <div id="login-status" class="warn-notice"> <span class="login-status-icon"></span> <div id="login-status-message">'+msg+'</div> </div> </div>');
}
!function ($) {

  $(function(){

    // Disable certain links in docs
    $('section [href^=#]').click(function (e) {
      e.preventDefault()
    })

    // make code pretty
    window.prettyPrint && prettyPrint()

    // add-ons
    $('.add-on :checkbox').on('click', function () {
      var $this = $(this)
        , method = $this.attr('checked') ? 'addClass' : 'removeClass'
      $(this).parents('.add-on')[method]('active')
    })

    // position static twipsies for components page
    if ($(".twipsies a").length) {
      $(window).on('load resize', function () {
        $(".twipsies a").each(function () {
          $(this)
            .tooltip({
              placement: $(this).attr('title')
            , trigger: 'manual'
            })
            .tooltip('show')
          })
      })
    }

    // add tipsies to grid for scaffolding
    if ($('#grid-system').length) {
      $('#grid-system').tooltip({
          selector: '.show-grid > div'
        , title: function () { return $(this).width() + 'px' }
      })
    }

    // fix sub nav on scroll
    var $win = $(window)
      , $nav = $('.subnav')
      , navTop = $('.subnav').length && $('.subnav').offset().top - 40
      , isFixed = 0

    processScroll()

    $win.on('scroll', processScroll)

    function processScroll() {
      var i, scrollTop = $win.scrollTop()
      if (scrollTop >= navTop && !isFixed) {
        isFixed = 1
        $nav.addClass('subnav-fixed')
      } else if (scrollTop <= navTop && isFixed) {
        isFixed = 0
        $nav.removeClass('subnav-fixed')
      }
    }

    // tooltip demo
    $('.tooltip-demo.well').tooltip({
      selector: "a[rel=tooltip]"
    })

    $('.tooltip-test').tooltip()
    $('.popover-test').popover()

    // popover demo
    $("a[rel=popover]")
      .popover()
      .click(function(e) {
        e.preventDefault()
      })

    // button state demo
    $('#fat-btn')
      .click(function () {
        var btn = $(this)
        btn.button('loading')
        setTimeout(function () {
          btn.button('reset')
        }, 3000)
      })

    // carousel demo
    $('#myCarousel').carousel()

    // javascript build logic
    var inputsComponent = $("#components.download input")
      , inputsPlugin = $("#plugins.download input")
      , inputsVariables = $("#variables.download input")

    // toggle all plugin checkboxes
    $('#components.download .toggle-all').on('click', function (e) {
      e.preventDefault()
      inputsComponent.attr('checked', !inputsComponent.is(':checked'))
    })

    $('#plugins.download .toggle-all').on('click', function (e) {
      e.preventDefault()
      inputsPlugin.attr('checked', !inputsPlugin.is(':checked'))
    })

    $('#variables.download .toggle-all').on('click', function (e) {
      e.preventDefault()
      inputsVariables.val('')
    })

    // request built javascript
    $('.download-btn').on('click', function () {

      var css = $("#components.download input:checked")
            .map(function () { return this.value })
            .toArray()
        , js = $("#plugins.download input:checked")
            .map(function () { return this.value })
            .toArray()
        , vars = {}
        , img = ['glyphicons-halflings.png', 'glyphicons-halflings-white.png']

    $("#variables.download input")
      .each(function () {
        $(this).val() && (vars[ $(this).prev().text() ] = $(this).val())
      })

      $.ajax({
        type: 'POST'
      , url: 'http://bootstrap.herokuapp.com'
      , dataType: 'jsonpi'
      , params: {
          js: js
        , css: css
        , vars: vars
        , img: img
      }
      })
    })

  })

// Modified from the original jsonpi https://github.com/benvinegar/jquery-jsonpi
$.ajaxTransport('jsonpi', function(opts, originalOptions, jqXHR) {
  var url = opts.url;

  return {
    send: function(_, completeCallback) {
      var name = 'jQuery_iframe_' + jQuery.now()
        , iframe, form

      iframe = $('<iframe>')
        .attr('name', name)
        .appendTo('head')

      form = $('<form>')
        .attr('method', opts.type) // GET or POST
        .attr('action', url)
        .attr('target', name)

      $.each(opts.params, function(k, v) {

        $('<input>')
          .attr('type', 'hidden')
          .attr('name', k)
          .attr('value', typeof v == 'string' ? v : JSON.stringify(v))
          .appendTo(form)
      })

      form.appendTo('body').submit()
    }
  }
})

}(window.jQuery)
jQuery(document).ready(function(){
	jQuery('.ajax-link a').live('click', function(e){
		e.preventDefault();
		var link = jQuery(this).attr('href');
		var rel = jQuery(this).attr('rel');
showloading('.content');
		jQuery('.content').load(link);
	});


	jQuery('.loading_link a').live('click', function(e){
		e.preventDefault();
		var link = jQuery(this).attr('href');
		var rel = jQuery(this).attr('rel');
		showloading('.content');
		jQuery(html).load(link, function(response, status, xhr) {
		  if (status == "error") {
		      var msg = "Sorry but there was an error: ";
		      $(body).html(msg + xhr.status + " " + xhr.statusText);
		  }
		});
	});
	jQuery('a#logout_link').live('click', function(e){
		e.preventDefault();
		var link = jQuery(this).attr('href');
		var rel = jQuery(this).attr('rel');
		showloading(this);
    history.pushState({'logout':'true'}, "New URL: "+link, link);
var window_refresh = setInterval(
function ()
{
window.location.href="/logout.php";
}, 2000);
	});

	jQuery('.caption a').live('click', function(e){
		e.preventDefault();
		var link = jQuery(this).attr('href');
		var rel = jQuery(this).attr('rel');
		showloading('.caption a[rel='+rel+']');
    history.pushState('', "New URL: "+link, link);
		jQuery('.caption a[rel='+rel+']').load(link);

	});

$(".collapse").collapse();
  var ck_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;  

$("a[rel*=leanmodal]").leanModal({ top : 200, overlay : 0.4, closeButton: ".modal_close" });
$('.modal_close').click(function(){
$('#leanmodal').fadeOut('slow').hide();
});
		$('#ajax-form').ajaxForm({target: '#result'  }).submit(function(){showloading('#loading').fadeOut(2000)});
});
$(function() {
$(".more").click(function() {
var element = $(this);
var msg = element.attr("id");
$(".morebox").html('Loading');

$.ajax({
type: "POST",
url: "/load_messages.php",
data: "lastmsg="+ msg,
cache: false,
success: function(html){

$("#content").append(html);
$(".more").remove();

}
});
return false;
});
});