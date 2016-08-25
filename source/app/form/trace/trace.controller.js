angular.module('sparqApp.form.trace', [
  'ui.router',
  'ui.mask'
])

.config($stateProvider =>
  $stateProvider
    .state('form.trace', {
      url: '/trace',
      templateUrl: 'app/form/trace/trace.html',
      controller: 'sparqFormTraceController',
      data: {
        title: 'Trace'
      }
    })
)

.controller('sparqFormTraceController', ($scope, $rootScope, $state, $q) => {
  $scope.startTrace = () => {
    let foo = traceDetails.trace_url.value
    debugger;
  }
});
