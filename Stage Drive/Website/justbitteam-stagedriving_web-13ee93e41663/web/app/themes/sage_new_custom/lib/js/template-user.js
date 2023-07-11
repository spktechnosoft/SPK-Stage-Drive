function get_user_date(){
    var account_id = 'fouad.charfeddine14@gmail.com';
    var url = 'https://api.staging.stagedriving.com/v1/accounts/'+ account_id +'/reviews';
    console.log(document.cookie);

    jQuery.ajax({ 
        type: "GET",
        dataType: 'json',
        url: 'https://api.staging.stagedriving.com/v1/accounts/fouad.charfeddine14@gmail.com/reviews',
        success: function(response){        
          alert(response);
        },
        error: function(xhr) {
          console.log('error');
          window.call_finish_global = true;
          //call_finish = true;
        },
     });

}