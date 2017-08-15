import loginComponent from './login.component.js'
import loginService from './login.service'

export default
  angular
    .module('login.page', [])
    .component('loginComponent', loginComponent)
    .service('loginService', loginService)
    .name