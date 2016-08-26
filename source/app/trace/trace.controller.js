angular.module('sparqApp.trace', [
  'ui.router'
])

.config($stateProvider =>
  $stateProvider
    .state('trace', {
      url: '/trace/:id',
      templateUrl: 'app/trace/trace.html',
      controller: 'sparqTraceController'
    })
)

.controller('sparqTraceController', ($rootScope, $scope, $window, $state, traceService, $stateParams, $http) => {
  let vm = {}

  let id = $stateParams.id

  traceService.find(id).then(res => {
    console.log(res);
    console.log(res.url);

    vm.trace = res
  })

  $scope.vm = vm;
});
