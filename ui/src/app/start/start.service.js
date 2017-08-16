export default
/* @ngInject */
class StartService {
  constructor ($http, apiUrl) {
    this.$http = $http
    this.apiUrl = apiUrl
  }

  getAllFlights () {
    return this.$http
      .get(`${this.apiUrl}/flights`)
      .then(result =>{
    		  console.log(result.data)    		  
    		  this.flightsOffered = result.data      
  })
}
  changePage () {
	    if (sessionStorage.getItem('username') !== null && sessionStorage.getItem('password') !== null) {
	      return {visibility: 'hidden'}
	    } else {
	      return {visibility: 'visible'}
	    }
	  }
	  logOut () {
	    sessionStorage.clear()
	  }

	  loggedInButton () {
	    if(sessionStorage.length === 0){
	      return {visibility: 'hidden'}
	    } else {
	      return {visibility: 'visible'}
	    }
	  }
 }


