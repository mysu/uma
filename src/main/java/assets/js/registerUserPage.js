/**
 * 
 */
// config requirejs
require.config({
  shim: {
    backbone:{
      deps:['jquery'],
      exports: 'Backbone'
    },
    'jquery.validate': ['jquery']
  },
  paths:{
    jquery: '/assets/webjars/jquery/2.0.3/jquery',
    'jquery.validate': '/assets/webjars/jquery-validation/1.11.1/jquery.validate',
    backbone: '/assets/webjars/backbonejs/1.0.0/backbone',
    underscore: '/assets/webjars/underscorejs/1.4.3/underscore',
    json: '/assets/webjars/requirejs-plugins/3ff54566f8/json',
    text: '/assets/webjars/requirejs-text/2.0.10/text'
  }  
});

define(['jquery', 'backbone', 'underscore', 'views/registerUserView'], 
    function($, Backbone, _, RegisterUserView){
  var view = new RegisterUserView();
});