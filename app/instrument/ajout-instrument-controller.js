(function () {
  "use strict";

  angular.module('app')
    .controller('AddInstCtrl', ['$scope', '$http', '$log', '$location', 'ApplicationConfig', 'addinstrumentService', function ($scope, $http, $log, $location, ApplicationConfig, addinstrumentService) {
      // The content of the controller.
      // Instead of using this use the variable self.
      var self = this;
        $scope.readFile = function () {            
        $log.log("read file");
        fileReader.readAsDataUrl($scope.file, $scope)
                  .then(function(result) {
                        $scope.imageSrc = result;
                    });
       };

    }]);
}());