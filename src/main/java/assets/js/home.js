/**
 * JS for logged-in users
 */
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
    'jquery.validate': ['jquery'],
    bootstrap: ['jquery']
  },
  paths:{
    jquery: '/assets/webjars/jquery/2.0.3/jquery',
    'jquery.validate': '/assets/webjars/jquery-validation/1.11.1/jquery.validate',
    backbone: '/assets/webjars/backbonejs/1.0.0/backbone',
    underscore: '/assets/webjars/underscorejs/1.4.3/underscore',
    json: '/assets/webjars/requirejs-plugins/3ff54566f8/json',
    text: '/assets/webjars/requirejs-text/2.0.10/text',
    bootstrap: '/assets/webjars/bootstrap/3.0.2/js/bootstrap'
  }  
});

define(['jquery', 'backbone', 'underscore', 'views/userSettings', 'bootstrap'], 
    function($, Backbone, _, UserSettings, x){
  var view = new UserSettings();
});