/**
 * Created by youness on 03/11/2016.
 */
(function () {
  "use strict";

  angular.module('app')
    .directive('wrapOwlcarousel', function () {
      return {
        restrict: 'E',
        link: function (scope, element, attrs) {
          var options = scope.$eval($(element).attr('data-options'));
          $(element).owlCarousel(options);
        }
      };
    });
}());