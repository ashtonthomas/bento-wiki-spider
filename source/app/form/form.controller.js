angular.module('sparqApp.form', [
  'ui.router'
])

.config($stateProvider =>
  $stateProvider
    .state('form', {
      url: '/form',
      templateUrl: 'app/form/form.html',
      controller: 'sparqFormController'
    })
)

.controller('sparqFormController', ($rootScope, $scope, $window, $state, traceService) => {
  let vm = {}

  vm.startTrace = () => {

    let url = traceDetails.trace_url.value;

    // Maybe I should post a request to the server which will return a traceId
    // add a view to display the trace details (in progress or completed) for the traceId
    // This will let me play with spark a little bit more
    // and actually start to solve the problem..
    // Also need to deploy to Heroku

    traceService.post(url)
  }

  vm.loading = true;

  // Let's just use the Main Page as a default starting point
  vm.trace_url = "https://en.wikipedia.org/wiki/Main_Page";


  $scope.vm = vm;
});
