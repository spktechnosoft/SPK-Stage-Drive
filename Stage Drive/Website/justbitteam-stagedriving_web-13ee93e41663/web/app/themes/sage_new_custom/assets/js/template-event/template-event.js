
//*
//*  RESIZER CARD //
//*
function getPercentageChange(oldNumber, newNumber){
  var decreaseValue = oldNumber - newNumber;
  return (decreaseValue / oldNumber) * 100;
}

function getPercentage(max_height, percentageToRemove){
  var num = max_height;
  var val = num - (num * percentageToRemove);
  console.log('val -> ' , val );
  return val;
}

function resizeCard(current_width) {

  var max_widht = 1600;
  var max_height= 1200;
  console.log('hereee');
  if (current_width < max_widht) {
    console.log('current width -> ' , current_width );
    // passiamo la larghezza massima ( che segna il 100% ) e la larghezza attuale in modo da riprendere la differenza
    var DifferenceToRemove = getPercentageChange(max_widht, current_width)
    console.log('percentuale mancante al complessivo -> ' , DifferenceToRemove );
    // arrotondo il risultato
    var round_difference = Math.round(DifferenceToRemove);
    console.log(' round difference --> ' , round_difference);
    //round_difference = 10;
    // passiamo l atezza massima ( che segna il 100% ) e il valore da rimuovere Es: 100 - 0,10% = 90
    if (round_difference < 10) {
      round_difference = parseFloat('0.0' + round_difference);
      console.log('round_difference  ->' ,  round_difference );
    } else {
      round_difference = parseFloat('0.' + round_difference);
    }
    var height = getPercentage(max_height, round_difference);

    console.log('percent -> ' , height );

    jQuery('.content-card-event').css('height' ,  height +'px');
  }
}

//*
//* END RESIZER CARD //
//*
// ---------------------------------------------------//
//*
//*  RESIZER CARD //
//*
