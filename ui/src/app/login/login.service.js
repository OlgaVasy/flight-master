export default
/* @ngInject */
class LoginService {
  constructor ($http, apiUrl, $localStorage, $state) {
    this.$http = $http
    this.apiUrl = apiUrl
    this.$localStorage = $localStorage
    this.$state = $state
  }

  getLogin (creds) {
	    console.log(creds.username)
	    if (creds.username !== '' || creds.password !== '') {
	    return this.$http({
	      method: 'GET',
	      params: {password: creds.password, username: creds.username},
	      url: '${this.apiUrl}/user/validate/credentials/exists/@{' + creds.username + '}'
	    }).then(
	     (successResponse) => {
	       console.log(successResponse.data)
	       if (successResponse.data) {
	    	 this.$localStorage.setItem('username', creds.username)
	         this.$localStorage.setItem('password', creds.password)
	         this.$state.go('profile', {username: creds.username})
	       }
	     },
	     (failureResponse) => {
	       console.log('fail')
	      
	     }
	   )
	 }}

	}
