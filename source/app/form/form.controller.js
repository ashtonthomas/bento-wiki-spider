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
    vm.loading = true;
    let url = traceDetails.trace_url.value;
    traceService.post(url)
  }

  vm.loading = false;

  // Let's just use the Main Page as a default starting point
  vm.trace_url = "https://en.wikipedia.org/wiki/Main_Page";

  $scope.vm = vm;
});
