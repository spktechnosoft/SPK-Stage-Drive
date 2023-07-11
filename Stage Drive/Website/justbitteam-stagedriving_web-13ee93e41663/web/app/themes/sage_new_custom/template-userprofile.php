<?php
/**
 * Template Name: Template User Profile
 */

//$wp_session = WP_Session::get_instance();


wp_head();
get_template_part('/lib/svg-image.php', null );


?>
<?php echo $icon_images; ?>

<div id="page-user">
    <div class="container-fluid head-user" style="margin-top:32px;">
        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4 image-container">
                <div id="img-profile"></div>
                <p class="user-name"></p>
            </div>
            <div class="col-sm-4"></div>
    </div>

    </div>
    <div class="container max-width" >
        <div class="row">
            <div class="col-sm-12">
                <div class="card" >
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <a href="/modifica-profilo/">
                                <div class="img-svg"><svg class="icon icon-modifica_icon"><use xlink:href="#icon-modifica_icon"></use></svg></div>
                                <div class="description-modal  "><p>Modifica profilo</p></div>
                                <div class="arrow"><svg class="icon icon-arrow-right-icon"><use xlink:href="#icon-arrow-right-icon"></use></svg></div>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <div class="img-svg"><svg class="icon icon-password_icon"><use xlink:href="#icon-password_icon"></use></svg></div>
                                <div class="description-modal  "><p>Cambia Password</p></div>
                                <div class="arrow"><svg class="icon icon-arrow-right-icon"><use xlink:href="#icon-arrow-right-icon"></use></svg></div>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <div class="img-svg"><svg class="icon icon-mobile_icon"><use xlink:href="#icon-mobile_icon"></use></svg></div>
                                <div class="description-modal  "><p>Configura numero di telefono</p></div>
                                <div class="arrow"><svg class="icon icon-arrow-right-icon"><use xlink:href="#icon-arrow-right-icon"></use></svg></div>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <div class="img-svg"><svg class="icon icon-notifiche_icon"><use xlink:href="#icon-notifiche_icon"></use></svg></div>
                                <div class="description-modal  "><p>Imposta notifiche</p></div>
                                <div class="arrow"><svg class="icon icon-arrow-right-icon"><use xlink:href="#icon-arrow-right-icon"></use></svg></div>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <div class="img-svg"><svg class="icon icon-pagamenti-icon"><use xlink:href="#icon-pagamenti-icon"></use></svg></div>
                                <div class="description-modal  "><p>Pagamenti effettuati</p></div>
                                <div class="arrow"><svg class="icon icon-arrow-right-icon"><use xlink:href="#icon-arrow-right-icon"></use></svg></div>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <div class="img-svg"><svg class="icon icon-credits-icon"><use xlink:href="#icon-credits-icon"></use></svg></div>
                                <div class="description-modal  "><p>Crediti ottenuti</p></div>
                                <div class="arrow"><svg class="icon icon-arrow-right-icon"><use xlink:href="#icon-arrow-right-icon"></use></svg></div>
                            </a>
                        </li>
                        <li class="list-group-item no_border">
                            <a href="#">
                                <div class="img-svg"><svg class="icon icon-quit_icon"><use xlink:href="#icon-quit_icon"></use></svg></div>
                                <div class="description-modal  "><p>Esci</p></div>
                                <div class="arrow"><svg class="icon icon-arrow-right-icon"><use xlink:href="#icon-arrow-right-icon"></use></svg>
                            </a>
                        </li>
                        <li class="list-group-item title">
                            <div class="description-modal   no-icon"><p>Altro</p></div>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <div class="description-modal   no-icon" ><p>Segnala</p></div>
                                <div class="arrow"><svg class="icon icon-arrow-right-icon"><use xlink:href="#icon-arrow-right-icon"></use></svg>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <div class="description-modal   no-icon" ><p>Stato servizio</p></div>
                                <div class="arrow"><svg class="icon icon-arrow-right-icon"><use xlink:href="#icon-arrow-right-icon"></use></svg>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <div class="description-modal   no-icon" ><p>Privacy</p></div>
                                <div class="arrow"><svg class="icon icon-arrow-right-icon"><use xlink:href="#icon-arrow-right-icon"></use></svg>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <div class="description-modal   no-icon" ><p>Licenze</p></div>
                                <div class="arrow"><svg class="icon icon-arrow-right-icon"><use xlink:href="#icon-arrow-right-icon"></use></svg>
                            </a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">
                                <div class="description-modal   no-icon" ><p>Segnale</p></div>
                                <div class="arrow"><svg class="icon icon-arrow-right-icon"><use xlink:href="#icon-arrow-right-icon"></use></svg>
                            </a>
                        </li>
                    </ul>
                </div>
            </div> <!-- col -->
        </div><!-- row -->
    </div><!--container-->
</div>

<?php

wp_footer();

?>

<script>
jQuery( document ).ready(function() {

    var token = '<?php echo $_COOKIE['accessToken'] ?>';
    var url = SD_API_ACCOUNTS+'/me';

    jQuery.ajax({
        type: "GET",
        dataType: "json",
        url: url,
        success: function(response,status, xhr){
            jQuery( "#img-profile" ).append('<img src="'+response['images'][0]['normalUri']+'" class="rounded-circle" alt="immagine profilo" height="90" width="90">');
            jQuery( ".user-name" ).append( '<span>'+response['firstname']+' '+ response['lastname']+'</span>');
            //storage_user(response);
            if (response['groups'] != null) {
                for(var i=0; i<response['groups'].length; i++) {
                    console.log('group', response['groups'][i]);
                    if (response['groups'][i].name === 'organizer') {

                    }
                }
            }
        },
        error: function(response) {
          console.log('error'+response);
          window.call_finish_global = true;
          //call_finish = true;
        },
     });

});
function storage_user(user){
    console.log(user);
    jQuery.ajax({
                url: "<?php echo get_template_directory_uri() ?>/lib/user-class.php",
                type: "GET",
                data: {user: user},
                async: false,
                succes: function(risp){
                    console.log(risp);
                }

    });
}
</script>
