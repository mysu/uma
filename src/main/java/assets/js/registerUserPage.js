/**
 * 
 */
// config requirejs
require.config({
  shim: {
    backbone:{
      deps:['jquery'],
      exports: 'Backbone'
    }
  },
  paths:{
    jquery: '/assets/webjars/jquery/1.9.0/jquery',
    backbone: '/assets/webjars/backbonejs/1.0.0/backbone',
    underscore: '/assets/webjars/underscore/1.4.3/underscore',
  }  
});