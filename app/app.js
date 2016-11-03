'use strict';

var appName = "app";
angular.module(appName, [
    'ngAnimate',
    'ui.router',
    'ngCookies',
    'ngResource',
    'ui.bootstrap',
    'angular.filter',
    'ngRoute',
    'app.env'
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
          'top@': {
            templateUrl: "partials/blocks/top.html"
          },
          'header@': {
            templateUrl: "partials/blocks/header.html",
            controller: 'HeaderCtrl as headerCtrl'
          },
          'content@': {
            templateUrl: 'home/home.html',
            controller: 'HomeCtrl as home'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer2.html'
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
            templateUrl: 'view1/view1.html',
            controller: 'View1Ctrl as view1Ctrl'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer2.html'
          }
        }
      });
  }]);
