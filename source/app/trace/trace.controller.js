angular.module('sparqApp.trace', [
  'ui.router'
])

.config($stateProvider =>
  $stateProvider
    .state('trace', {
      url: '/trace',
      templateUrl: 'app/trace/trace.html',
      controller: 'sparqTraceController'
    })
)

.controller('sparqTraceController', ($rootScope, $scope, $window, $state, startTraceService) => {
  let vm = {}

  

  $scope.vm = vm;
});
