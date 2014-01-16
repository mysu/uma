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
          $('#basicInfoForm')[0].reset();
        }else{
          showResult("error_update_user", false);
        }
      },
      error: function(err){
        showResult("error_update_user", false);
        $('#basicInfoForm')[0].reset();
        console.log(err);
      }      
    });
  };
  
  var createJson = function(){
    return {
      id: $('#userId').val(),
      currPassword: $('#currPassword').val(),
      password: $('#password').val(),
      retypePassword: $('#password_retype').val()
    }
  };
  
  var showResult = function(message, success){
    var resultSelect = $('#resultUpdateUser');
    resultSelect.removeClass('alert-danger').removeClass('alert-success');
    resultSelect.addClass(success ? 'alert-success' : 'alert-danger');
    resultSelect.html('').html(i18n[message]).show();
    resultSelect.delay(5000).fadeOut(1000);
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