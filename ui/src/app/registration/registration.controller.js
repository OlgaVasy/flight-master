export default

/* @ngInject */
class RegistrationController{
	constructor(registrationService, $state){
		
		this.registrationService = registrationService	
		this.$state = $state

  this.user = {

				credentials: {
				      password: 'password',
				      username: 'username'
				    },
				    profile: {
				      email: 'Email',
				      firstName: '',
				      lastName: '',
				      phone: ''
				    }
				  }
	}
  createUser () {
    this.registrationService.createUser(this.user).then(
      (success) => {
        this.$state.go('login')
        console.log(this.user)
      },
      (failure) => {
        alert('invalid information entered')
      }
    )
  }
}