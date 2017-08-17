export default
/* @ngInject */
class ProfileService {
	 constructor ($http, apiUrl, $localStorage) {
		    this.$http = $http
		    this.apiUrl = apiUrl
		    this.$localStorage = $localStorage
		  }

		  getBookedFlights (username) {			 
			  console.log(username)
		    return this.$http
		      .get(`${this.apiUrl}/user/users/@${username}/bookedFlights`)
		      .then(result =>{
		    		  console.log(result.data) 
		    		  console.log('success')
		    		  this.flightsBooked = result.data      
		  },
		
		(failureResponse) => {
		       console.log('fail')
		      
		   }
	   )
	 }			
				  logOut () {
					  this.$localStorage.username = null
					  this.$localStorage.password = null				 
				      this.$state.go('welcome')				  
			  }

		 }