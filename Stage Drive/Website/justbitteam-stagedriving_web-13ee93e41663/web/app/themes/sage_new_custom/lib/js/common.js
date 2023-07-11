
function upload_image(image64, callback) {

  var url_upload = SD_API_OBJECTS+'/images';
  var response_uri = '';
  var img64j = { "content":image64 };
  console.log('image64',img64j);
  jQuery.ajax({
      async: callback !== undefined ? true : false,
      url: url_upload,
      type: "POST",
      contentType: "application/json",
      data : JSON.stringify(img64j),
      success: function(response,status, xhr){
        console.log(response.uri);
        response_uri = response.uri;
        if (callback !== undefined) {
          callback(response_uri)
        }
      },
      error: function(response) {
        console.log('errore su upload ',response);
        window.call_finish_global = true;
        //call_finish = true;
        callback('fail');
      },
  });
  if (response_uri !='')
    return response_uri;
}

function getUrlParameter(name) {
  name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
  var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
  var results = regex.exec(location.search);
  return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};

function userHasRole(user, role) {
  if (user['groups'] != null) {
    for(var i=0; i<user['groups'].length; i++) {
        console.log('group', user['groups'][i]);
        if (user['groups'][i].name === role) {
            return true;
        }
    }
  }

  return false;
}

// Bootstrap
jQuery( document ).ready(function() {

  Noty.overrideDefaults({
    layout   : 'topCenter',
    theme    : 'mint',
    closeWith: ['click', 'button'],
    timeout: 3000,
    animation: {
        open : 'animated fadeInRight',
        close: 'animated fadeOutRight'
    }
});

  // When the user scrolls the page, execute myFunction 
  window.onscroll = function() {myFunction()};

  // Get the offset position of the navbar
  var sticky = 10;

  // Add the sticky class to the header when you reach its scroll position. Remove "sticky" when you leave the scroll position
  function myFunction() {
    if (window.pageYOffset > sticky) {
      jQuery('.nav-primary').addClass("sticky");
    } else {
      jQuery('.nav-primary').removeClass("sticky");
    }
  }
});