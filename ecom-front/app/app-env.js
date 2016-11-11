angular.module('app.env', [])
  .constant('EnvironmentConfig', {
    "GlobalBaseUrl": "http://localhost:8000/",
    "defaultTimeZone": "Europe/paris",
    "defaultDateFormat": "DD-MM-YYYY HH:mm"
  });
