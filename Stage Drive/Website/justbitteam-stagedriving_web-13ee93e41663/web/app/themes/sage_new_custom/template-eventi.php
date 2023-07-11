<?php
/**
 * Template Name: Template eventi
 */

// include image //
get_template_part('/lib/svg-image.php', null );

?>

<div class="max-width" style="margin-top:40px;">
  <div class="box-custom-6" style="height:60px;">
    <div class="col-custom-6">
      <div class="cont-custom-6 cont-custom-dx">
        <div class="button-event button-time" data-toggle="modal" data-target="#modal-time">
          <div class="align-img">
            <div class="img-svg img-replace-time">
              <span class="icon-questo_mese_icon icon-font font-35"> </span>
            </div>
          </div>
          <div class="align-text">
            <p class="first-text ">Quando?</p>
            <p class="second-text  text-replace-time" value="this_month">questo mese</p>
          </div>

        </div>
        <div id="clear-filter" class="button-close">
            <div class="img-svg-close">
              <span class="icon-chiudi-icon-hover icon-font font-15"> </span>
            </div>
          </div>

      </div>
    </div>
    <div class="col-custom-6">
      <div class="cont-custom-6 cont-custom-sx">
        <div class="button-event" data-toggle="modal" data-target="#modal-place" >
          <div class="align-img">
            <div class="img-svg img-replace-place">
              <!-- <span class="icon-ditendenza_icon icon-font font-35" > </span> -->
              <span class="icon-ditendenza_icon icon-font font-35"></span>
              <!-- <span class="icon-ditendenza_custom icon-font font-35"></span> -->
            </div>
          </div>
          <div class="align-text">
            <p class="first-text ">Eventi interessanti</p>
            <p class="second-text  text-replace-place">di tendenza</p>
          </div>
        </div>
      </div>
    </div>
  </div>
  <style media="screen">
    .icon-ditendenza_custom {
      background-image: url('http://www.stagedriving.com/app/uploads/2020/06/di_tendenza-1.png');
      height: 30px;
      width: 30px;
    }
  </style>
  <!--- modal month --->
  <div id="modal-time" class="modal fade" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
        <div class="modal-body">
          <div class="content-body-modal">
            <?php
            if (!empty($modal_periods['items'])) {
              foreach ($modal_periods['items'] as $key => $period) {
                $period_value = $period['value'];
                $period_img = $period['image'];
                $period_text = $period['text']; ?>

                <div class="custom-row ">
                  <div class="modal-period" value="<?php echo $period_value; ?>">
                    <div class="img-svg">
                      <?php echo $period_img; ?>
                    </div>
                    <div class="description-modal ">
                      <p class="text"><?php echo $period_text; ?></p>
                    </div>
                    <div class="arrow-modal">
                      <div class="img-svg-arrow-right">
                        <span class="icon-arrow-right-icon icon-font font-15"></span>
                      </div>
                    </div>
                  </div>
                </div>
        <?php  }
            } ?>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- end modal month -->

  <!--- modal place --->
  <div id="modal-place" class="modal fade" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
        <div class="modal-body">
          <div class="content-body-modal">
            <div class="content-body-modal">
      <?php
            if (!empty($modal_places['items'])) {
              foreach ($modal_places['items'] as $key => $place) {
                $place_value = $place['value'];
                $place_img = $place['image'];
                $place_text = $place['text']; ?>

                <div class="custom-row ">
                  <div class="modal-place" value="<?php echo $place_value; ?>">
                    <div class="img-svg">
                      <?php echo  $place_img ; ?>
                    </div>
                    <div class="description-modal">
                      <p class="text"><?php echo $place_text; ?></p>
                    </div>
                    <div class="arrow-modal">
                      <div class="img-svg-arrow-right">
                        <span class="icon-arrow-right-icon icon-font font-15"></span>
                      </div>
                    </div>
                  </div>
                </div>
        <?php }
            } ?>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- end modal place -->
  <!-- content cards events -->
  <div class="wrap-all-event">
    <div class="all-cards">
      <!---qui inseriamo i post principali della pagina --->
    </div>
    <p class='no-post' style="display:none;">Nessun evento disponibile al momento</p>
    <div class="post-infinite-scroll">
      <!--- qui inseriamo i post dell infinite scroll  --->
    </div>
    <div class="hook-height" style="height:40px; transform: translateY(-98vh)">
   <!--<p style="text-align:center">hook one page scroll</p>-->
    </div>
  </div>
    <!-- end content crd events -->
</div>

<script type="text/javascript">
  var token = '<?php echo isset($_COOKIE['accessToken']) ? $_COOKIE['accessToken'] : "" ?>';
  var today = '';

  var filter = '';
  var start = '';
  var end = '';


  var page_val = 0;
  var limit_val = SD_API_LIMIT;
  var page_val_scroll = 0;
  var limit_val_scroll = SD_API_LIMIT;

  jQuery( document ).ready(function() {

    //return;

    //spinjs
    spinnerInit('start');

    //clear filter
    jQuery('#clear-filter').hide();
    // resize card //
    var current_width = jQuery(window).width();
    setTimeout(function(){
      console.log("interval");
      resizeCard(current_width);
    }, 1000);

    // resize mobile //
    jQuery(window).resize(function() {
      var current_width = jQuery(window).width();
      resizeCard(current_width);
    });

    // CUSTOM MODALS //
      modal_time();
      modal_place();
      prepare_date();
      filter = '';
      start = moment().startOf('month').format();
      end = '';
      page_call_event();

  });
  jQuery('div#clear-filter').click(function() {
        console.log("click clear filter");
        jQuery('#clear-filter').hide();
        jQuery('.all-cards').html('');
        start = moment().startOf('month').format(); // faccio ripartire gli eventi dal mese presente
        end = '';
        filter = '';
        page_val = 0;
        limit_val = SD_API_LIMIT;
        page_val_scroll = 0;
        limit_val_scroll = SD_API_LIMIT;
        jQuery('p.text-replace-time').text('questo mese');
        jQuery('p.text-replace-time').val('this_month');
        jQuery('div.img-replace-time').html('');
        jQuery('div.img-replace-time').append(' <span class="icon-questo_mese_icon icon-font font-35"> </span>');
        jQuery('.all-cards').html('');
        page_call_event();
   });



</script>
<style>
/* .date-card {
    width: 50%;
    } */
/* .subtitle-card {
    width: 50%;
} */
/* .button-close {
    left: 46%;
    top: 166px;
    } */
    /* ---- hidden footer ---- */
    .footer-container {
      display: none;
    }
    /*--- end hidden footer --- */
</style>
