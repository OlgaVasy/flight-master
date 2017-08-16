export default
/* @ngInject */
class RegistrationService {
  constructor ($http, apiUrl) {
    this.$http = $http
    this.apiUrl = apiUrl   
  }

  createUser (user) {
    let newUser = {
    		credentials: {
    	        password: user.credentials.password,
    	        username: user.credentials.username
    	      },
    	      profile: {
    	        email: user.profile.email    	        
    	      }
    	    }
    return this.$http({
      method: 'POST',
      url: `${this.apiUrl}/user/users`,
      data: newUser,
      params: {firstName: user.profile.firstName, lastName: user.profile.lastName, phone: user.profile.phone}
    }).then(
      (success) => {
        console.log('User has been created')        
      },
      (failure) => {
    	  
      }
    )
  }
}