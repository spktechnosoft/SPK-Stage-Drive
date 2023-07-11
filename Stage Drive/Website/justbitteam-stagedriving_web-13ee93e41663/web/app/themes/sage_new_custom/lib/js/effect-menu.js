
// --------- EFFECT MENU ------------- //

jQuery( document ).ready(function() {
    (function($) {
    var ost = 0;
    $(window).scroll(function() {
      var cOst = $(this).scrollTop();

      if(cOst > 200 && cOst > ost) {
         $('.hook-header').addClass('fixed-hook').removeClass('default-hook');
      }
      else {
         $('.hook-header').addClass('default-hook').removeClass('fixed-hook');
      }

      ost = cOst;
    });
  })(jQuery);
});
