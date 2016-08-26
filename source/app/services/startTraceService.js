angular.module('sparqApp.startTraceService', [
  'ngCookies'
])

.factory('startTraceService', ($window, $cookies, $cacheFactory, $state, $http) => {
  let cache = $cacheFactory('sparqApp');

  return {
    startTrace: startTrace
  };

  function startTrace(url) {

    let t = {
      url: url
    }

    console.log('start trace...');
    console.log(url);

    $http.post('/api/v1/trace', t).success(function (data) {
        $state.go('trace');
    }).error(function (data, status) {
        console.log('Error ' + data)
    })


  };
});
