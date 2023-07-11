<?php
/**
 * Template Name: Template stage driving
 */
$img_onda = get_stylesheet_directory_uri() . '/dist/images/onda-sfumata_v2.png';
$icon_play_store = get_stylesheet_directory_uri() . '/dist/images/google_play_btn.png';
$icon_app_store = get_stylesheet_directory_uri() . '/dist/images/app_store_btn.png';
$wave_background = get_stylesheet_directory_uri() . '/dist/images/header_wave.png';
// FIELD
$description_banner = get_field('description_banner');
$description_banner_bold = get_field('description_banner_bold');
$link_btn_banner = get_field('link_btn_banner');
$logo_banner_top = get_field('img_banner_sx');
$img_banner_dx = get_field('img_banner_dx');
$img_col_dx  = get_field('img_col_dx');
$title_col_dx = get_field('title_col_dx');
$description_col_dx = get_field('description_col_dx');
$img_col_sx = get_field('img_col_sx');
$title_col_sx = get_field('title_col_sx');
$description_col_sx = get_field('description_col_sx');
$img_girl_wave = get_field('img_girl_wave');
$link_play_store = get_field('link_play_store');
$link_app_store = get_field('link_app_store');
$slider_home = get_field('slider_home');
$title_info = get_field('title_info');
$repeater_info = get_field('repeater_info');
$img_store = get_field('img_store');
$title_store = get_field('title_store');
$subtitle_store = get_field('subtitle_store');
$link_contact_us  = get_field('link_contact_us');
// original
//$logo_banner_top = get_stylesheet_directory_uri() . '/dist/images/logo-payoff_V3.png';
//$img_banner_dx = 'http://sd.localhost:8989/app/uploads/2019/10/persone_header.png';
//$img_girl_wave = 'http://sd.localhost:8989/app/uploads/2019/10/scarica_app_01_mobile.png';
//$img_col_dx = 'http://sd.localhost:8989/app/uploads/2019/10/cos_e_V2.png';
//$img_col_sx = 'http://sd.localhost:8989/app/uploads/2019/10/cos_e_mockup_V2.png';
// $img_store = 'http://sd.localhost:8989/app/uploads/2019/10/scarica_app_02.png';
//$pic_info = 'http://sd.localhost:8989/app/uploads/2019/10/passioni_V2.png';
//$img_slider_card = 'http://sd.localhost:8989/app/uploads/2019/10/01_Stage.png';
?>

<style media="screen">
  .wrap-logo-description {
    display: none;
  }
</style>

<div class="background-wave" style="background-image: url(<?php echo $wave_background ?>)">
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 col-sx-banner">
        <?php if ($logo_banner_top != '') { ?>
          <img class="logo-banner" src="<?php echo $logo_banner_top; ?>" alt="Stage Driving">
        <?php } ?>
        <?php if($description_banner != ''){ ?>
          <p class="description-banner montserrat-alternates">
            <?php echo $description_banner; ?>
          </p>
        <?php } ?>
        <?php if ($description_banner_bold != '') { ?>
          <p class="description-banner-bold montserrat-alternates-bold-italic">
            <?php echo $description_banner_bold; ?>
          </p>
        <?php } ?>
        <?php if ($link_btn_banner == 1) { ?>
            <button type="button" class="btn btn-default btn-white">
              <a href="javascript:void(0)" class="montserrat-alternates btn-scroll">
                Scopri di più
              </a>
            </button>
        <?php } ?>
      </div>
      <?php if ($img_banner_dx != '') { ?>
        <div class="col-lg-4 col-md-4 hidden-sm hidden-xs col-dx-banner">
          <img class="people-banner" src="<?php echo  $img_banner_dx ?>" alt="Stage Driving">
        </div>
      <?php } ?>
    </div>
  </div>
</div>

<!--- alternate col ( current img dx text sx ) --->
<div class="container container-margin">
  <div class="row alternate-col alternate-col-dx">
    <?php if ($img_col_dx != '') { ?>
    <div class="col-lg-6 col-md-6 hidden-sm hidden-xs">
      <img src="<?php echo $img_col_dx; ?>" alt="Stage Driving">
    </div>
    <?php } ?>
    <?php if ($title_col_dx != '' || $description_col_dx != '') { ?>
      <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 wrap-text-alternate hook-scroll">
        <div class="box-text-alternate">
          <?php if ($title_col_dx != '') { ?>
            <div class="title-alternate">
              <?php echo $title_col_dx; ?>
            </div>
          <?php } ?>

          <?php if ($description_col_dx != '') { ?>
            <div class="description-alternate ">
              <?php echo $description_col_dx; ?>
            </div>
          <?php } ?>
        </div>
      </div>
    <?php } ?>

    <!----- IMG MOBILE ------->
    <?php if ($img_col_dx != '') { ?>
    <div class="hidden-lg hidden-md col-sm-6 col-xs-6 margin-image">
      <img src="<?php echo $img_col_dx; ?>" alt="Stage Driving">
    </div>
    <?php } ?>
    <?php if ($img_col_sx != '') { ?>
    <div class="hidden-lg hidden-md col-sm-6 col-xs-6 margin-image">
      <img src="<?php echo $img_col_sx; ?>" alt="Stage Driving">
    </div>
    <?php } ?>
    <!----- END IMG MOBILE ------->
  </div>
  <!--- alternate col ( current img sx text dx ) --->
  <?php if ($title_col_sx != '' || $description_col_sx != '' || $img_col_sx != '' ) {  ?>
      <div class="row alternate-col alternate-col-sx">
        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 wrap-text-alternate">
          <div class="box-text-alternate">
            <?php if ($title_col_sx != '') { ?>
              <div class="title-alternate">
                <?php echo $title_col_sx; ?>
              </div>
            <?php } ?>

            <?php if ($description_col_sx != '') { ?>
              <div class="description-alternate ">
                <?php  echo $description_col_sx; ?>
              </div>
            <?php } ?>
          </div>
        </div>
        <?php if ($img_col_sx != '') { ?>
        <div class="col-lg-6 col-md-6 hidden-md hidden-xs">
          <img src="<?php echo $img_col_sx; ?>" alt="Stage Driving">
        </div>
        <?php } ?>
      </div>
      <?php } ?>
</div>

<!--- img wave --->
<div class="container-fluid container-wave">
  <img src="<?php echo $img_onda; ?>" class="show-img">
  <div class="cont-wave container">
    <div class="row">
      <div class="col-lg-7 col-md-7 col-sm-7 col-xs-12 height-wave">
        <div class="wrap-cont-wave">
          <div class="cont-wave-text">
            <div class="title-wave">
              <p>
                Al prossimo evento <br>
                non dire di no.
              </p>
            </div>
            <div class="subtitle-wave">
              <p class="montserrat-alternates">
                Scaricala subito!
              </p>
            </div>
            <div class="buttons-download">
              <?php if ($link_app_store != '') { ?>
                <a href="<?php echo $link_app_store; ?>">
                  <img class="logo-store logo-margin" src="<?php echo $icon_app_store; ?>" alt="App Store">
                </a>
              <?php } ?>
              <?php if ($link_play_store != '') { ?>
                <a href="<?php echo $link_play_store; ?>">
                  <img class="logo-store" src="<?php echo $icon_play_store; ?>" alt="Google Play">
                </a>
              <?php } ?>
            </div>
          </div>
        </div>
      </div>
      <?php if ($img_girl_wave != '') { ?>
        <div class="col-lg-5 col-md-5 col-sm-5 hidden-xs">
          <img class="img-responsive  img-screen-app" src="<?php echo $img_girl_wave; ?>" alt="Stage Driving">
        </div>
      <?php } ?>
    </div>
  </div>
</div>
<!--- end img wave --->

<div class="container">
  <div class="row">
    <?php if (!empty($slider_home)) { ?>
    <div class="hook-slider">
    <?php for ($i=0; $i<count($slider_home); $i++) {
      $img_slider_card = $slider_home[$i]['img_slider'];
      //$img_slider_card = 'http://sd.localhost:8989/app/uploads/2019/10/01_Stage.png';
      $title_slider = $slider_home[$i]['title_slider'];
      $subtitle_slider = $slider_home[$i]['subtitle_slider'];
      $title_detail_slider = $slider_home[$i]['title_detail'];
      $subtitle_detail_slider = $slider_home[$i]['subtitle_detail']; ?>

       <div class="card-slider">
         <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
           <!---- MOBILE ---->
           <div class="description-mobile">
             <?php if ($title_slider != '') { ?>
               <p class="title-slider"> <?php echo $title_slider; ?> </p>
             <?php } ?>
             <?php if ($subtitle_slider != '') { ?>
               <p class="subtitle-slider montserrat-alternates"> <?php echo $subtitle_slider; ?> </p>
             <?php } ?>
           </div>
           <!--- END MOBILE --->
           <?php if($img_slider_card != ''){  ?>
             <img class="img-slider" src="<?php echo $img_slider_card; ?>" alt="Stage Driving">
           <?php } ?>
         </div>
         <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
           <!--- Desktop --->
           <div class="description-desktop">
             <?php if ($title_slider != '') { ?>
               <p class="title-slider"> <?php echo $title_slider; ?> </p>
             <?php } ?>
             <?php if ($subtitle_slider != '') { ?>
               <p class="subtitle-slider montserrat-alternates"> <?php echo $subtitle_slider; ?> </p>
             <?php } ?>
           </div>
           <!--- End Desktop --->
           <?php if ($title_detail_slider != '') { ?>
             <p class="title-detail-slider montserrat-alternates"> <?php echo $title_detail_slider; ?> </p>
           <?php } ?>
           <?php if ($subtitle_detail_slider != '') { ?>
             <p class="subtitle-detail-slider montserrat-alternates"> <?php echo $subtitle_detail_slider; ?> </p>
           <?php } ?>
         </div>
       </div>
    <?php } ?>
    </div>
  <?php } ?>
  </div>
</div>

<div class="container container-info">
  <div class="row">
    <?php if ($title_info != '') { ?>
      <p class="title-info montserrat-alternates"><?php echo $title_info; ?></p>
    <?php } ?>
  </div>
<?php if (!empty($repeater_info)) {
        for ($j=0; $j<count($repeater_info); $j++) {
          $pic_info = $repeater_info[$j]['img_info'];
          //$pic_info = 'http://sd.localhost:8989/app/uploads/2019/10/passioni_V2.png';
          $title_col_info = $repeater_info[$j]['title_col_info'];
          $description_col_info = $repeater_info[$j]['description_col_info']; ?>

          <?php if ($pic_info != '' || $title_col_info != '' || $description_col_info != '') { ?>
            <div class="row row-info">
              <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 col-img">
                <img src="<?php echo $pic_info; ?>" alt="Stage driving">
              </div>
              <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                <?php if ($title_col_info != '') { ?>
                  <p class="title-col-info montserrat-alternates"> <?php echo $title_col_info;?> </p>
                <?php } ?>
                <?php if ($description_col_info != '') { ?>
                  <p class="description-col-info montserrat-alternates"> <?php echo $description_col_info;?> </p>
                <?php } ?>
              </div>
            </div>
          <?php }
        }
      } ?>
</div>

<div class="container container-store">
  <div class="row">
    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 col-img-store">
      <?php if ($img_store != '') { ?>
        <img class="img-store" src="<?php echo $img_store; ?>" alt="Stage Driving">
      <?php } ?>
    </div>
    <?php if ($title_store != '' || $subtitle_store != '') { ?>
    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 wrap-text-store">
      <div class="box-text-store">
        <?php if ($title_store != '') { ?>
          <p class="title-store montserrat-alternates">
            <?php echo $title_store; ?>
          </p>
        <?php } ?>
        <?php if ($subtitle_store != '') { ?>
          <p class="subtitle-store montserrat-alternates">
            <?php echo $subtitle_store; ?>
          </p>
        <?php } ?>
          <?php if ($link_app_store != '' || $link_play_store != '') { ?>
            <p class="title-download montserrat-alternates">Scaricala subito</p>
            <div class="buttons-download buttons-store">
              <?php if ($link_app_store != '') { ?>
                <a href="<?php echo $link_app_store; ?>">
                  <img class="logo-store logo-margin" src="<?php echo $icon_app_store; ?>" alt="App Store">
                </a>
              <?php } ?>
              <?php if ($link_play_store != '') { ?>
                <a href="<?php echo $link_play_store; ?>">
                  <img class="logo-store" src="<?php echo $icon_play_store; ?>" alt="Google Play">
                </a>
              <?php } ?>
              <?php if($link_contact_us != '') { ?>
                <div class="">
                  <a class="btn btn-danger button-beta" href="<?php echo $link_contact_us; ?>" style="color:#fff;margin-top:20px;margin-bottom:10px;">
                    Contatti
                  </a>
                </div>
              <?php } ?>
            </div>
          <?php } ?>
      </div>
    </div>
    <?php } ?>
  </div>
</div>


<?php if (!empty($slider_home)) { ?>
  <script type="text/javascript">
    jQuery( document ).ready(function() {
      jQuery('.hook-slider').slick({
        infinite: true,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,
        speed: 1000,
        dots: true,
        prevArrow: '',
  			nextArrow: '',
      });
    });



  </script>
<?php } ?>

<?php if ($link_btn_banner == 1) { ?>
<!--- controllo btn scroll ---->
  <script type="text/javascript">
    jQuery( document ).ready(function() {
      jQuery('.btn-scroll').click(function() {
        jQuery('html,body').animate({
          scrollTop: jQuery('.hook-scroll').offset().top
        },'slow');
      });
    });
  </script>

<?php } ?>

<script type="text/javascript">
  // animation logo
  jQuery(window).scroll(function() {
    var coordinate_logo = jQuery('.logo-banner').offset();
    var coordinate_logo_top = coordinate_logo.top + 80;
    var current_px = jQuery(this).scrollTop();

    if (current_px > coordinate_logo_top) {
      jQuery('.wrap-logo-description').show('slow');
    } else {
      jQuery('.wrap-logo-description').hide('slow');
    }
  });
</script>
