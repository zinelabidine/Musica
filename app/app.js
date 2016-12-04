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
      url: '/cart/:client',
      views: {
        'content@': {
          templateUrl: 'cart/basket.html',
          controller: 'CartCtrl as cart'
        }
      }
    }).state('app.checkout', {
      url: '/checkout/personalinfo/:client',
      views: {
        'content@': {
          templateUrl: 'checkout/personalinfo.html',
          controller: 'CheckoutCtrl as checkout'
        }
      }
    }).state('app.paymentinfo', {
      url: '/checkout/paymentinfo/:client',
      views: {
        'content@': {
          templateUrl: 'checkout/paymentinfo.html',
          controller: 'CheckoutCtrl as checkout'
        }
      }
    }).state('app.orderreview', {
      url: '/checkout/orderreview/:client',
      views: {
        'content@': {
          templateUrl: 'checkout/orderreview.html',
          controller: 'CartCtrl as checkout'
        }
      }
    }).state('app.instrument', {
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
    });
  }])
  .service('globalService', ['$cookies', '$cookieStore',
    function ($cookies, $cookieStore) {
      function overrideAndReturn(name, value) {

        if (value) {
          $cookies[name] = value;
        }
        return $cookies[name];
      }

      function getPreferences(preferences) {
        var data = overrideAndReturnFromLocalStorage('clePortalPreferences', JSON.stringify(preferences));
        return angular.fromJson(data);
      }

      function overrideAndReturnFromLocalStorage(name, value) {
        if (typeof(Storage) !== "undefined") {
          if (value) {
            localStorage.setItem(name, value);
          }
          return localStorage.getItem(name);
        } else {
          alert("Your browser is not compatible with this website, please use a modern browser : Firefox, Chrome, Internet Explorer 10, Edge...")
        }
      }

      return {
        overrideAndReturnFromLocalStorage: function (name, value) {
          return overrideAndReturnFromLocalStorage(name, value);
        },
        login: function (login) {
          return overrideAndReturnFromLocalStorage('login', login);
        },
        personalDatas: function (personalDatas) {
          return JSON.parse(overrideAndReturnFromLocalStorage('personalDatas', personalDatas));
        },
        token: function (token) {
          return overrideAndReturnFromLocalStorage('token', token);
        },
        expirationDate: function (expirationDate) {
          return overrideAndReturnFromLocalStorage('expirationDate', expirationDate);
        },
        renewPassword: function (renewPassword) {
          return overrideAndReturnFromLocalStorage('renewPassword', renewPassword);
        },
        cleanSessionStorage: function () {
          if (typeof(Storage) !== "undefined") {
            localStorage.removeItem('login');
            localStorage.removeItem('permissions');
            localStorage.removeItem('token');
            localStorage.removeItem('expirationDate');
            localStorage.removeItem('personalDatas');
            localStorage.removeItem('userId')
            localStorage.removeItem('renewPassword');
          }
        },
        findElementInList: function (list, key, element) {
          var item;
          var result = false;
          for (var i = 0; i < list.length; i++) {
            item = list[i];
            if (item.hasOwnProperty(key) && item[key] === element) {
              result = true;
            }
          }
          return result;
        }
      };
    }
  ])

;
