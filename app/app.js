'use strict';

// Declare app level module which depends on views, and components
var appName = "app";
angular.module(appName, [
    'ngAnimate',
    'ui.router',
    'ngCookies',
    'ngResource',
    'ui.bootstrap',
    'angular.filter',
    'ngRoute',
    'app.env',
    'myApp.view1',
    'myApp.view2',
    'myApp.version'
  ])
  .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider
      .state('app', {
        abstract: true
        //url: '/app',
        //templateUrl: 'app.html'
      })
      .state('app.home', {
        url: '/home',

        views: {
          'header@': {
            templateUrl: "partials/blocks/header.html",
            controller: 'HeaderCtrl as headerCtrl'
          },
          'content@': {
            templateUrl: 'home/home.html',
            controller: 'HomeCtrl as home'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer.html'
            //controller: 'AppCtrl as AppCtrl'
          }
        }
      })
      .state('app.view1', {
        url: '/view1',

        views: {
          'header@': {
            templateUrl: "partials/blocks/header.html",
            controller: 'HeaderCtrl as headerCtrl'
          },
          'content@': {
            templateUrl: 'view1/view1.html'
            //controller: 'FlotChartDemoCtrl'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer.html'
          }
        }
      });
  }]);
