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

.controller('sparqTraceController', ($rootScope, $scope, $window, $state, startTraceService, $stateParams) => {
  let vm = {}

  // $window.alert($stateParams.id)

  $scope.vm = vm;
});
