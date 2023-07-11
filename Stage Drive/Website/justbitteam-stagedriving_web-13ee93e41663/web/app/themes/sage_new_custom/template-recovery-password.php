<?php
/**
 * Template Name: Template recovery password
 */
 $link_register = '#';
?>

<div class="container">
    <div class="row">
      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
        <div class="box-recovery">
          <!-- wrap content -->
          <div class="content-recovery">
            <h1 class="title-page">Rigenera la tua password</h1>
            <div class="wrap-input">
              <div class="form-group">
                <label class="" for="usr">Email:</label>
                <input type="text" class=" form-control" id="usr">
              </div>
            </div>

            <?php if ($link_register != '') { ?>
              <!--- cta register now --->
              <div class="wrap-sign-in" style="text-align:center;">
                <a href="<?php echo $link_register; ?>">
                  <p class="">Non sono registrato</p>
                </a>
              </div>
              <!--- end cta register now --->
            <?php } ?>

            <div class="wrap-button" style="text-align:center;">
              <button type="button" class="btn btn-danger button-beta" id="request-button">
                RICHIEDI
              </button>
            </div>
            <div class="message-error" style="text-align: center;">
              <p class="text-error" style="color:#ef726d;"></p>
            </div>
            <div class="message-request" style="text-align: center;">
              <p class="text-send-mail" style="color:#000;"></p>
            </div>
          </div>
        </div><!-- end box login -->
      </div>
    </div>
</div>

<script type="text/javascript">

  // click button request //
  jQuery('#request-button').click(function(){
    console.log('inside click');
    jQuery('.text-error').text('');
    jQuery('.text-send-mail').text('');

    var url = SD_API_AUTH+'/recover';
    var user = jQuery('#usr').val();
    var pass = '';

    if (user != '') {
      console.log('valore popolato ');
      console.log('val user --> ' , user );

      var detail = {
        identifier : user,
        password : pass,
      }
      // test send
      jQuery.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(detail),

        success: function (data) {
          console.log(' all data success --> ', data);
          //Query('.text-send-mail').text('Ti abbiamo inviato la nuova password.');

          window.showAdvMessage('Fatto', 'Riceverai a breve una nuova password sulla tua email.',
            function () {
              window.location.href = '/login';
            });
        },

        error: function (data) {
          //console.log(' error ' , data );
          //jQuery('.text-error').text('Mail non presente.');
          new Noty({
            type: 'error',
            text: 'Email non esistente',
          }).show();
        },
      });

    } else {
      // mail non presente
      jQuery('.text-error').text('Inserisci una mail.');
      new Noty({
            type: 'error',
            text: 'Inserisci una email',
          }).show();
    }
  })
</script>
