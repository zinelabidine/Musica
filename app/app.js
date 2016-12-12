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
    'app.env',
    'ngDialog',
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
      })

      .state('app.home', {
        url: '/home',
        views: {
          'content@': {
            templateUrl: 'home/home.html',
            controller: 'HomeCtrl as home'
          }
        }
      })

      .state('app.recherche', {
        url: '/recherche?motcles&categorieName',
        views: {
          'content@': {
            templateUrl: 'recherche/recherche.html',
            controller: 'RechercheCtrl as recherche'
          }
        }
      })

      .state('app.cart', {
        url: '/cart',
        views: {
          'content@': {
            templateUrl: 'cart/basket.html',
            controller: 'CartCtrl as cart'
          }
        }
      })

      .state('app.checkout', {
        url: '/checkout/personalinfo',
        views: {
          'content@': {
            templateUrl: 'checkout/personalinfo.html',
            controller: 'CheckoutCtrl as checkout'
          }
        }
      })

      .state('app.paymentinfo', {
        url: '/checkout/paymentinfo',
        views: {
          'content@': {
            templateUrl: 'checkout/paymentinfo.html',
            controller: 'CheckoutCtrl as checkout'
          }
        }
      })

      .state('app.orderreview', {
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

      .state('app.profilcommande', {
        url: '/profil/commande',
        views: {
          'content@': {
            templateUrl: 'profil/clientcommande.html',
            controller: 'ProfilCommandeCtrl as profilcommande'
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
      })

      .state('app.register', {
      url: '/register',
      views: {
        'content@': {
          templateUrl: 'authentification/register.html',
          controller: 'RegisterCtrl as register'
        }
      }
      })

      .state('app.addInstrument', {
        url: '/addInstrument',
        views: {
          'content@': {
            templateUrl: './instrument/ajout-instrument.html',
            controller: 'AddInstCtrl as addInstCtrl',
            resolve: {
              catmarData: ['addinstrumentService', function (addinstrumentService) {
                return addinstrumentService.getCategorieEtMarque();
              }]
            }
          }
        }
      })

      .state('app.profilUtilisateur', {
        url: '/profil',
        views: {
          'content@': {
            templateUrl: './utilisateur/profil-utilisateur.html',
            controller: 'ProfilCtrl as profilCtrl'
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
  ]);

/**
 *  Utility method for the whole application that verify is the variable is not defined or null
 */
angular.isUndefinedOrNull = function (val) {
  return angular.isUndefined(val) || val === null
};

/**
 * Utility method, check if an object is empty ie. ={} or null
 */
angular.isEmpty = function (obj) {

  // null and undefined are "empty"
  if (obj == null) return true;

  // Assume if it has a length property with a non-zero value
  // that that property is correct.
  if (obj.length > 0) return false;
  if (obj.length === 0) return true;

  // Otherwise, does it have any properties of its own?
  // Note that this doesn't handle
  // toString and valueOf enumeration bugs in IE < 9
  for (var key in obj) {
    if (hasOwnProperty.call(obj, key)) return false;
  }

  return true;
};

function isDefined(elem) {
  return angular.isDefined(elem) && elem !== null;
}

function isUndefined(elem) {
  return !isDefined(elem);
}
