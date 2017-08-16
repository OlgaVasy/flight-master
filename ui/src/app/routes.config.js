/* @ngInject */
function RoutesConfig($stateProvider, $urlRouterProvider) {
  
    $stateProvider.state('welcome',
        {
            url: '/',
            component: 'startComponent',
            resolve: {
                allFlights: function (startService, $transition$) {
                  return startService.getAllFlights($transition$)
                }
              }
        }      
    ).state('login',
    		{
    	url: '/login',
    	component: 'loginComponent'
    }
    ).state('registration',
    		{
    	url: '/registration',
    	component: 'registrationComponent' 
    }
    ).state('profile',
    		{
    	url: '/profile',
    	component: 'profileComponent' 
    }
    ).state('booking',
    		{
    	url: '/booking',
    	component: 'bookingComponent' ,
    	resolve: {
            allFlights: function (bookingService, $transition$) {
              return bookingService.getRoute($transition$)
            }
          }
    }
    )
    
  $urlRouterProvider.otherwise('/');
}
export default RoutesConfig