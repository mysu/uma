/**
 * 
 */
define(['jquery', 'jquery.validate', 'backbone', 'json!locale/registerUser.json'], 
    function($, Validator, Backbone, messages){

  var RegisterUserView = Backbone.View.extend({
    initialize: function(){
      $('#registerUserForm').validate();
    },
    events: {
      'keypress #username': 'validateUsername'
    },
    
    validateUsername: function(){
      
    }
    
  });
  
  return RegisterUserView;
});