<?php
// header //

global $wp;

?>
<header>
  <div class="container-fluid">
    <div class="row custom-header">
     <div class="nav-primary hook-header" >

        <!--- nav left --->
        <div class="nav-left">
          <div class="wrap-logo-description">
            <a href="<?php echo get_home_url(); ?>">
            <img class="logo-left-header" src="<?php echo get_template_directory_uri(); ?>/assets/images/logo-bianco-web_v2.png">
            </a>
          </div>

        </div>
        <div id="event-title" class="nav-center" >
            <!-- <h1><span class="glyphicon glyphicon-chevron-left"></span></h1> -->
        </div>
        <!--  end nav left -->

        <div class="nav-right">

          <div class="wrap-logo-right">
              <?php $is_home_page=is_front_page();
              // if ($is_home_page != true) {
              ?>
                <div class="dropdown">
                  <div class="custom_hamburger">
                    <div class="wrap_hamburger ">
                      <span></span>
                      <span></span>
                      <span></span>
                    </div>
                  </div>
                  <!---- old menu ---->
                  <!-- <img class="logo-right-header" data-toggle="dropdown" src="<?php echo get_template_directory_uri(); ?>/assets/images/image_user_profile_withe.png" alt="user">   -->
                      <?php
                      $menu_name = 'Principal-menu';
                      $locations = get_nav_menu_locations();
                      $menu = wp_get_nav_menu_object($locations[$menu_name]);
                      $menuitems = wp_get_nav_menu_items($menu->term_id, array());
                      $disable_menu = true;

                      if (!empty($menuitems) && $disable_menu == false) { ?>
                        <ul class="dropdown-menu dropdown-menu-custom">
                  <?php
                        foreach ($menuitems as $key => $item) {
                          $name_page = $item->title;
                          $url_page = $item->url; ?>

                          <li>
                            <a href="<?php echo $url_page; ?>">
                              <?php echo $name_page; ?>
                            </a>
                          </li>

                  <?php } ?>
                      </ul>
                <?php }  ?>
                      <!-- end old menu -->
                </div>
              </div>
          <?php// }?>
        </div>
      </div>
     </div>
  </div>
</header>

<div class="content-menu">
  <div class="wrap-menu">
  <!--- close btn --->
    <div class="close-menu">
      <div class="custom-close-menu">
        <div class="wrap_hamburger">
          <span></span>
          <span></span>
        </div>
      </div>
    </div>
    <!--- all menu --->
    <div class="align-menu">
      <div class="container">
        <div class="row">
          <div class="col-sm-12 text-center">
            <!---- NEW MENU ---->
            <p>
              <a href="/events">Eventi</a>
            </p>
            <span class="line"></span>
            <p>
              <a href="/create-event">Crea evento</a>
            </p>
            <span class="line"></span>
            <p>
              <a href="/my-events">I miei eventi</a>
            </p>

            <?php if (!isset($GLOBALS['SD_TOKEN']) || $GLOBALS['SD_TOKEN'] === '') { ?>
              <span class="line"></span>
                <p>
                  <a href="/login">Login</a>
                </p>
            <?php } ?>
            <span class="line"></span>
            <p>
              <a href="/contatti">Contatti</a>
            </p>
            <?php if (isset($GLOBALS['SD_TOKEN']) && $GLOBALS['SD_TOKEN'] !== '') { ?>
              <span class="line"></span>
                <p>
                  <a href="/profile">Profilo</a>
                </p>
               <span class="line"></span>
                <p>
                  <a href="#" id="exit-button">Esci</a>
                </p>
            <?php } ?>
            <!-- END NEW MENU -->
          </div>
        </div>
      </div>
    </div>
   <!--- end all menu --->
  </div>
</div>

<script>
jQuery('#exit-button').click(function () {
  console.log('exit');
  window.setCookie('accessToken', '', 0);
  localStorage.setItem("user", null);
  setTimeout(() => {
    window.location.href = '/';
  }, 800);
});

// open menu //
jQuery('.custom_hamburger').click(function () {
  jQuery(window).scrollTop(0);
  jQuery('body').css({'overflow':'hidden'});
  jQuery('.content-menu').fadeIn();
  jQuery('.content-menu').css({"transform":"scale(1)","-ms-transform":"scale(1)","-webkit-transform":"scale(1)"});
});

// close menu //
jQuery('.close-menu').click(function () {
  jQuery('.content-menu').fadeOut();
  jQuery('.content-menu').css({"transform":"scale(0)","-ms-transform":"scale(0)","-webkit-transform":"scale(0)"});
  jQuery('body').css({'overflow':'unset'})
});

</script>
