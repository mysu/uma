/*
* Javascript to update user settings
*/

define(['jquery', 'jquery.validate', 'backbone', 'json!locale/userSettings.json'], 
    function($, Validator, Backbone, i18n){
  var submitForm = function(form){
    $.ajax({
      url: '/api/updatebasicinfo',
      data: createJson(),
      type: 'POST',
      dataType: 'json',
      success: function(data){
        if(data.success){
          showResult("success_update_user", true);
        }else{
          showResult("error_update_user, " + data.error, false);
        }
      },
      error: function(err){
        showResult("error_update_user_unknown", false);
        console.log(err);
      }
      
    });
  };
  
  var createJson = function(){
    return {
      currPassword: $('#currPassword').val(),
      password: $('#password').val(),
      'password_retype': $('#password_retype').val()
    }
  };
  
  var showResul = function(message, success){
    var resultSelect = $('#resultUpdateUser');
    resultSelect.remClass('alert-error').remClass('alert-info');
    resultSelect.addClass(success ? 'alert-info' : 'alert-error');
    resultSelect.html(i18n[message]).show();
    resultSelect.wait(5000).fadeOut(1000);
  };
    
  
  var UserSettings = Backbone.View.extend({
    initialize: function(){
      $('#userSettings').unbind('click').click(function(){
        $('#modalUserSettings').modal('show');
      });
      $('#basicInfoForm').validate({
        submitHandler: submitForm
      });
    }
  });
  
  return UserSettings;
});