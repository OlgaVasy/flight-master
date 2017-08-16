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
    	        email: user.profile.email,
    	        firstName: user.profile.firstName,
    	        lastName: user.profile.lastName,
    	        phone: user.profile.phone
    	      }
    	    }
    return this.$http({
      method: 'POST',
      url: '${this.apiUrl}/client/users',
      data: newUser
    }).then(
      (success) => {
        console.log('User has been created')        
      },
      (failure) => {}
    )
  }
}