(function(){
  angular.module('templates', []); // rolodex...

  angular.module('sparqApp', [
    'restangular',
    'ui.router',
    'bendy.templates',
    'bendy.env',

    'sparqApp.form',
    'sparqApp.trace',
    'sparqApp.traceService'
  ])
  .config(routeConfig)

  // .config(urlHashConfig)
  ;

  function routeConfig($urlRouterProvider, $stateProvider) {
    $stateProvider.state('main', { url: '/' });
    $urlRouterProvider.otherwise('/form');
  }

  // function urlHashConfig($locationProvider) {
  //   // Removes the hash from the URL
  //   $locationProvider.html5Mode({ enabled: true, rewriteLinks: false }).hashPrefix('!');
  // }

})();
