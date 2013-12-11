/**
 * 
 */
define([ 'jquery', 'jquery.validate', 'backbone',
    'json!locale/registerUser.json' ], function($, Validator, Backbone, i18n) {

  $.validator.setDefaults({
    debug : true
  });

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
        }
      });
    }

  });

  return RegisterUserView;
});