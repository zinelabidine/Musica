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
              headerData: ['headerService', function (headerService) {
                return headerService.initHeader();
              }]
            }
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer.html'
            //controller: 'AppCtrl as AppCtrl'
          }
        }
        //url: '/app',
        //templateUrl: 'app.html'
      }).state('app.home', {
      url: '/home',

      views: {
        'content@': {
          templateUrl: 'home/home.html',
          controller: 'HomeCtrl as home'
        }
      }
    }).state('app.recherche', {
      url: '/recherche/:motcles',
      views: {
        'content@': {
          templateUrl: 'recherche/recherche.html',
          controller: 'RechercheCtrl as recherche'
        }
      }
    }).state('app.cart', {
      url: '/cart',
      views: {
        'content@': {
          templateUrl: 'cart/basket.html',
          controller: 'CartCtrl as cart'
        }
      }
    }).state('app.checkout', {
      url: '/checkout/personalinfo',
      views: {
        'content@': {
          templateUrl: 'checkout/personalinfo.html',
          controller: 'CheckoutCtrl as checkout'
        }
      }
    }).state('app.paymentinfo', {
      url: '/checkout/paymentinfo',
      views: {
        'content@': {
          templateUrl: 'checkout/paymentinfo.html',
          controller: 'CheckoutCtrl as checkout'
        }
      }
    }).state('app.orderreview', {
      url: '/checkout/orderreview',
      views: {
        'content@': {
          templateUrl: 'checkout/orderreview.html',
          controller: 'CartCtrl as checkout'
        }
      }
    })
      .state('app.displaycommand', {
        url: '/checkout/displaycommand/:commande',
        views: {
          'content@': {
            templateUrl: 'checkout/displaycommand.html',
            controller: 'DisplayCommandeCtrl as displaycommand'
          }
        }
      })

      .state('app.instrument', {
        url: '/instrument/:instrumentId',
      views: {
        'content@': {
          templateUrl: './instrument/details-instrument.html',
          controller: 'DetailsInstCtrl as detailsInstCtrl'
        }
      }
    }).state('app.register', {
      url: '/register',
      views: {
        'content@': {
          templateUrl: 'authentification/register.html',
          controller: 'RegisterCtrl as register'
        }
      }
      }).state('app.addInstrument', {
        url: '/addInstrument',
        views: {
          'content@': {
            templateUrl: './instrument/ajout-instrument.html',
            // controller: 'AddInstCtrl as addInstCtrl'
          }
        }
      })
      .state('app.addUtilisateur', {
        url: '/addUtilisateur',
        views: {
          'content@': {
            templateUrl: './utilisateur/ajout-utilisateur.html',
            controller: 'UtilsateurCtrl as utilisateurCtrl'
          }
        }
    });
  }]);
