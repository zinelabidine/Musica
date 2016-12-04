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
    'app.config',
    'app.env'
  ])
  .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider
      .state('app', {
        abstract: true,
        views: {
          'header@': {
            templateUrl: "header/header.html",
            controller: 'HeaderCtrl as headerCtrl',
            resolve: {
              headerData:  ['headerService', function (headerService) {
                return headerService.initHeader();
              }]
            }
          }
        }
        //url: '/app',
        //templateUrl: 'app.html'
      })

      .state('app.home', {
        url: '/home',

        views: {
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

      .state('app.recherche', {
        url: '/recherche/:marque&:categorie&:motcles',
        views: {
          'content@': {
            templateUrl: 'recherche/recherche.html',
            controller: 'RechercheCtrl as recherche'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer.html'
            //controller: 'AppCtrl as AppCtrl'
          }
        }
      })

      .state('app.cart', {
        url: '/cart/:motcles',
        views: {
          'content@': {
            templateUrl: 'cart/basket.html',
            controller: 'CartCtrl as cart'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer.html'
            //controller: 'AppCtrl as AppCtrl'
          }
        }
      })

      .state('app.checkout', {
        url: '/checkout/:motcles',
        views: {
          'content@': {
            templateUrl: 'checkout/checkout1.html',
            controller: 'CheckoutCtrl as checkout'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer.html'
            //controller: 'AppCtrl as AppCtrl'
          }
        }
      })

      .state('app.instrument', {
        url: '/instrument/:instrumentId',

        views: {
          'content@': {
            templateUrl: './instrument/details-instrument.html',
            controller: 'DetailsInstCtrl as detailsInstCtrl'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer.html'
            //controller: 'AppCtrl as AppCtrl'
          }
        }
      })

      .state('app.addInstrument', {
        url: '/addInstrument',
        views: {
          'content@': {
            templateUrl: './instrument/ajout-instrument.html',
            // controller: 'AddInstCtrl as addInstCtrl'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer.html'
            //controller: 'AppCtrl as AppCtrl'
          }
        }
      })

      .state('app.addUtilisateur', {
        url: '/addUtilisateur',
        views: {
          'content@': {
            templateUrl: './utilisateur/ajout-utilisateur.html',
            controller: 'UtilsateurCtrl as utilisateurCtrl'
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
            templateUrl: 'view1/view1.html',
            controller: 'View1Ctrl as view1Ctrl'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer2.html'
          }
        }
      });
  }]);
