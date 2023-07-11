<?php
/*
*  footer
*/
$img_logo_bianco = get_stylesheet_directory_uri() . '/dist/images/logo-bianco-web_v2.png';
?>

<?php if (is_front_page()) { ?>
<footer class="footer-container">
  <div class="container">
    <div class="row">
      <div class="col-lg-2 col-md-2 col-sm-12 col-xs-12" style="text-align:center;">
        <img class="logo_footer" style="height: auto;width:100%;max-width:200px;" src="<?php echo $img_logo_bianco; ?>" alt="Stage driving">
      </div>
      <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12 content-footer">
        <p class="first"> Via San Lorenzo 164, 86100 Campobasso, Italia | P. IVA 01790560708 | Surfingevents Srl </p>
        <p class="second">Realizzato con gli aiuti del POR FESR FSE 2014/2020 Azione 1.3.1 - Avviso Pubblico HIGH TECH BUSINESS - CUP D39J17000820007</p>
        <p class="third">
          POR FESR-FSE MOLISE 2014-20 ASSE 1 – RICERCA, SVILUPPO TECNOLOGICO E INNOVAZIONE Azione 1.3.1 – “Sostegno alla creazione ed al consolidamento di start up innovative ad alta intensità di applicazione di conoscenza e alle iniziative di spin-off della ricerca “AVVISO HIGH TECH BUSINESS" Progetto cofinanziato dal Fondo Europeo di Sviluppo Regionale (FESR) Beneficiario: Surfingevents S.r.l. CUP: D39J17000820007 Provvedimento di concessione – Determinazione Dirigenziale n. 6164 del 12/12/2017 Agevolazione concessa - € 101.174,96
        </p>
      </div>

    </div>
  </div>
  <!--- old footer --->
  <!-- <div class="container-fluid footer-cont">
    <div class="detail-footer">
      <div class="detail-footer-img">
        <img class="logo_footer" style="height: 64px;" src="<?php echo $img_logo_bianco; ?>" alt="Stage driving">
      </div>
      <div class="detail-footer-text">
        <p class="  first-row"> Via San Lorenzo 164, 86100 Campobasso, Italia | P. IVA 01790560708 | Surfingevents Srl </p>
        <p class="  second-row">
          Realizzato con gli aiuti del POR FESR FSE 2014/2020 Azione 1.3.1 - Avviso Pubblico HIGH TECH BUSINESS - CUP D39J17000820007
        </p>
        <p class="  third-row">
          POR FESR-FSE MOLISE 2014-20 ASSE 1 – RICERCA, SVILUPPO TECNOLOGICO E INNOVAZIONE Azione 1.3.1 – “Sostegno alla creazione ed al consolidamento di start up innovative ad alta intensità di applicazione di conoscenza e alle iniziative di spin-off della ricerca “AVVISO HIGH TECH BUSINESS" Progetto cofinanziato dal Fondo Europeo di Sviluppo Regionale (FESR) Beneficiario: Surfingevents S.r.l. CUP: D39J17000820007 Provvedimento di concessione – Determinazione Dirigenziale n. 6164 del 12/12/2017 Agevolazione concessa - € 101.174,96
        </p>
      </div>

    </div>
  </div> -->
</footer>
<?php } ?>

<style media="screen">
@media (min-width:991px) {
  .content-footer {
    padding-left: 0;
  }
}
.content-footer p {
  color: #fff;
  text-align: center;
  margin-bottom: 15px !important;
}
.content-footer .first {
  font-size: 20px;
  font-weight: 200;
}
.content-footer .second {
  font-size: 16px;
  font-weight: 200;
}
.content-footer .third {
  font-size: 12px;
  font-weight: 100;
}
/* end new  */
  .footer-cont {
    height: 150px;
    position: relative;
  }
  .footer-container {
    background-color: #fa8448;
    padding-top: 15px;
    padding-bottom: 15px;
  }
  .detail-footer {
    width: 80%;
    /* background: blue; */
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    top: 0;
    bottom: 0;
  }
  .logo_footer {
    margin-top: 15px;
  }
  .detail-footer-img {
    width: 25%;
    /* background: red; */
    height: 100%;
    float: left;
  }
  .detail-footer-text {
    width: 75%;
    float: left;
    height:100%;
    /* background: #fff; */
  }
  .detail-footer-text .first-row {
    font-size: 24px;
    text-align: center;
    margin-top: 8px;
    color: #fff;
    font-weight: 100;
    letter-spacing: 1px;
  }
  .detail-footer-text .second-row {
    color: #fff;
    font-size: 16px;
    font-weight: 100;
    text-align: center;
    margin-bottom: 10px;
    transform: translateY(5px);
    letter-spacing: 1px;
  }
  .detail-footer-text .third-row {
    color: #fff;
    font-size: 12px;
    font-weight: 100;
    text-align: center;
    margin-bottom: 0;
    transform: translateY(5px);
    letter-spacing: 1px;
  }
  .helvetica-neue {
    font-family: 'Helvetica Neue' !important;
  }
  @media (max-width: 1700px) {
    .detail-footer-img {
      width: 17%;
    }
    .detail-footer-text {
      width: 83%;
    }
  }
  @media (max-width: 1500px) {
    .detail-footer {
      width: 90%;
    }
  }
  @media (max-width: 1400px) {
    .detail-footer-text .first-row {
      letter-spacing: normal !important;
    }
    .detail-footer-text .second-row {
      letter-spacing: normal !important;
    }
  }
  @media (max-width: 1300px) {
    .logo_footer {
      height: 40px;
    }
    .detail-footer {
      width: 95%;
    }
  }
  @media (max-width: 1120px) {
    .detail-footer-img {
      width: 15%;
    }
    .detail-footer-text {
      width: 85%;
    }
    .detail-footer .first-row {
      font-size: 22px;
    }
    .detail-footer .second-row {
      font-size: 14px;
    }
  }
  @media (max-width: 1000px) {
    .detail-footer {
      position: static;
      left: auto;
      transform: none;
      top: auto;
      bottom: auto;
      width: 100%;
    }
    .detail-footer-text {
      width: 100%;
      float: none;
      height: auto;
    }
    .detail-footer-img {
      display: block;
      width: 100%;
      height: auto;
      text-align: center;
    }
    .footer-cont {
      height: auto;
    }
    .first-row, .second-row, .third-row {
      height: auto !important;
    }

    .logo_footer {
      margin-top: auto;
      margin-bottom: 10px;
    }
    .footer-container {
      padding-bottom: 50px;
    }
  }
</style>

<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/headroom/0.9.4/headroom.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/headroom/0.9.4/jQuery.headroom.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.3.3/js/swiper.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.3.3/js/swiper.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
