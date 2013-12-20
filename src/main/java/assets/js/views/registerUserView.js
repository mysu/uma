/**
 * 
 */
define([ 'jquery', 'jquery.validate', 'backbone',
    'json!locale/registerUser.json' ], function($, Validator, Backbone, i18n) {

  var submitForm = function(form){
    var jsonData = createJson();
    $.ajax({
      url: '/register',
      data: jsonData,
      type: 'POST',
      dataType: 'json',
      success: function(data){
        if(data.success){
          window.location.href='/'
        }else{
          $('#resultInfo').addClass('alert-error').html(data.error).show();
          console.log('server error creating user' + data.error);
        }
      },
      error: function(err){
        $('#resultInfo').addClass('alert-error').html('Unknow error').show();
        console.log(err);
      }
      
    });
  };
  
  var createJson = function(){
    return {
      username: $('#username').val(),
      email: $('#email').val(),
      password: $('#password').val(),
      'password_retype': $('#password_retype').val()
    }
  }
  var RegisterUserView = Backbone.View.extend({
    initialize : function() {
      $('#registerUserForm').validate({
        messages : {
          username : {
            remote : i18n['error_username_forbidden']
          },
          email: {
            remote: i18n['error_email_forbidden']
          }
        },
        submitHandler: submitForm
      });
    },

  });

  return RegisterUserView;
});