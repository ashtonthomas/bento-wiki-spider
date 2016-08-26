angular.module('sparqApp.startTraceService', [
  'ngCookies'
])

.factory('startTraceService', ($window, $cookies, $cacheFactory, $state) => {
  let cache = $cacheFactory('sparqApp');

  return {
    startTrace: startTrace
  };

  function startTrace(url) {
    $window.alert(url);

    $state.go('trace');
  };
});
