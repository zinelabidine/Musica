(function () {
  "use strict";

  angular.module('app')
    .directive('instrumentHighlight', function ($timeout) {
      return {
        restrict: 'E',
        scope: {
          instrument: '=',
          openInstrumentDetails: '&',
          addInstrumentToCart: '&'
        },
        templateUrl: 'recherche/instrumentHighlight-directive.html',
        link: function (scope, element) {
          scope.openInstrumentDetailsLocal = function (instrumentId) {
            scope.openInstrumentDetails({
              'instrumentId': instrumentId
            });
          };

          scope.addInstrumentToCartLocal = function (instrumentId) {
            scope.addInstrumentToCart({
              'instrumentId': instrumentId
            });
          };
        }
      }
    });
}());