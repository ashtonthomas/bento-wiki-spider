angular.module('sparqApp.form', [
  'ui.router',
  'sparqApp.form.trace',
])

.config($stateProvider =>
  $stateProvider
    .state('form', {
      url: '/form',
      templateUrl: 'app/form/form.html',
      controller: 'sparqFormController',
      abstract: true
    })
)

.controller('sparqFormController', ($rootScope, $scope, $window, $state) => {
  // I don't need this
  // I'm just demonstrating a useless abstract state and a parent controller
});
