export default

/* @ngInject */
class LoginController{
	constructor(loginService){
		
		this.loginService = loginService
		
		this.credentials = {
			    username: '',
			    password: '',
			    newState: 'login'
			  }
	
	}

	}