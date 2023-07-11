<?php
/*
* HOME SLIDER
*/
$images_slider = get_field('images_slider');
// echo '<pre>';
// print_r($images_slider);
// echo '</pre>';
  if ($images_slider) { ?>

      <!-- <div class="container-fluid"> -->
        <div class="container-fluid">
          <div class="row">
            <div class="slider-home">

              <?php foreach ($images_slider as $key => $value) {
                    $url_image = $value['url']; ?>

                    <div class="image-slider-home" style="background-image: url('<?php echo $url_image;?>');">

                    </div>

              <?php } ?>

            </div>
          </div>
        </div>
      <!-- </div> -->

  <?php } ?>

<script type="text/javascript">

  jQuery( document ).ready(function() {
    jQuery('.slider-home').slick({
      infinite: true,
      slidesToShow: 1,
      slidesToScroll: 1,
      speed: 500,
      dots: true,
      nextArrow: '',
      prevArrow: '',

    });
  });

</script>
