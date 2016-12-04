(function () {
  "use strict";

  angular.module('app').factory('registerService', ['$http', '$log', "$q", '$cookies', 'EnvironmentConfig', 'globalService', '$filter', function ($http, $log, $q, $cookies, EnvironmentConfig, globalService, $filter) {
    return {

      signin: function (clientLoginData) {

        var deferred = $q.defer();
        globalService.cleanSessionStorage();
        globalService.login(clientLoginData.login);
        var basicToken = clientLoginData.login + ':' + clientLoginData.password;
        // we support IE10+
        var tokenBase64 = window.btoa(unescape(encodeURIComponent(basicToken)));

        $http({
          url: EnvironmentConfig.GlobalBaseUrl + "/auth/login",
          method: "POST",
          headers: {
            'Authorization': 'Basic ' + tokenBase64,
            'Content-Type': 'application/json; charset=utf-8'
          },
          data: clientLoginData
        }).success(function (data, status, headers, config) {

            globalService.token(tokenBase64);
            // use new Date().getTime()
            // globalService.expirationDate(data.expirationDate);

            // Store personal data
            data.userId = clientLoginData.login;
            console.log(data);
            globalService.personalDatas(JSON.stringify(data));

            deferred.resolve(data, status);
          })
          .error(function (data, status, headers, config, statusText) {
            globalService.cleanSessionStorage();
            console.log(status);
            console.log(statusText);
            var failedDetail = {
              "status": status,
              "reason": data.reason
            };
            return deferred.reject(failedDetail);
          });
        return deferred.promise;
        //return $http.post(EnvironmentConfig.GlobalBaseUrl + "/auth/login", data)
        //  .then(function (response) {
        //    return response.data;
        //  }, function (errResponse) {
        //    $log.log("Error in AJAX call " + errResponse);
        //  })
      },
      logout: function () {
        return $http.get(EnvironmentConfig.GlobalBaseUrl + "/accueil/initializehomecontent")
          .then(function (response) {
            return response.data;
          }, function (errResponse) {
            $log.log("Error in AJAX call " + errResponse);
          })
      }

      ,
      register: function (data) {
        return $http.post(EnvironmentConfig.GlobalBaseUrl + "/auth/register", data)
          .then(function (response) {
            return response.data;
          }, function (errResponse) {
            $log.log("Error in AJAX call " + errResponse);
          })
      }
      //isConnected: function () {
      //  var accessToken = clePortal.token();
      //
      //  console.log("[authenticationService.isConnected] clePortal.expirationDate() " + clePortal.expirationDate());
      //  if (clePortal.expirationDate() == null || clePortal.expirationDate() == undefined) {
      //    return false;
      //  }
      //  var expDate = new Date(parseInt(clePortal.expirationDate()));
      //  var now = new Date();
      //
      //  var isTokenStillValid = (expDate.getTime() - now.getTime()) > 0;
      //
      //  console.log("[authenticationService.isConnected] expDate = " + expDate + " ; isTokenStillValid ? " + isTokenStillValid);
      //  console.log("[authenticationService.isConnected] isConnected ? " + (accessToken != null && accessToken != undefined && isTokenStillValid));
      //
      //  return (accessToken != null && accessToken != undefined && isTokenStillValid);
      //}

    }
  }
  ]);
}());
