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
        url: '/recherche/:motcles',
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
        url: '/cart/:client',
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
        url: '/checkout/personalinfo/:client',
        views: {
          'content@': {
            templateUrl: 'checkout/personalinfo.html',
            controller: 'CheckoutCtrl as checkout'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer.html'
            //controller: 'AppCtrl as AppCtrl'
          }
        }
      })

      .state('app.paymentinfo', {
        url: '/checkout/paymentinfo/:client',
        views: {
          'content@': {
            templateUrl: 'checkout/paymentinfo.html',
            controller: 'CheckoutCtrl as checkout'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer.html'
            //controller: 'AppCtrl as AppCtrl'
          }
        }
      })

      .state('app.orderreview', {
        url: '/checkout/orderreview/:client',
        views: {
          'content@': {
            templateUrl: 'checkout/orderreview.html',
            controller: 'CartCtrl as checkout'
          },
          'footer@': {
            templateUrl: 'partials/blocks/footer.html'
            //controller: 'AppCtrl as AppCtrl'
          }
        }
      })

      .state('app.displaycommand', {
        url: '/checkout/displaycommand/:commande',
        views: {
          'content@': {
            templateUrl: 'checkout/displaycommand.html',
            controller: 'DisplayCommandeCtrl as displaycommand'
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
