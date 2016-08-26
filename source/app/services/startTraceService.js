angular.module('sparqApp.traceService', [
  'ngCookies'
])

.factory('traceService', ($window, $cookies, $cacheFactory, $state, $http, $q) => {
  let cache = $cacheFactory('sparqApp');

  return {
    post: post,
    find: find
  };

  function post(url) {

    let t = {
      url: url
    }

    console.log('start trace...');
    console.log(url);

    $http.post('/api/v1/trace', t).success(function (data) {
        $state.go("trace", { "id": data.id});
    }).error(function (data, status) {
        console.log('Error ' + data)
    })


  };

  function find(id) {
    let defer = $q.defer();

    $http.get('/api/v1/trace/' + id).success(function (data) {
      defer.resolve(data);
    }).error(function (data, status) {
      console.log('Error ' + data)
      defer.reject();
    });

    return defer.promise;
  };
});
