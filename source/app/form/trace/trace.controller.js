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
    // Maybe I should post a request to the server which will return a traceId
    // add a view to display the trace details (in progress or completed) for the traceId
    // This will let me play with spark a little bit more
    // and actually start to solve the problem..
  }
});
