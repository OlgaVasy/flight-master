import profileComponent from './profile.component.js'
import profileService from './profile.service'

export default
  angular
    .module('profile.page', [])
    .component('profileComponent', profileComponent)
    .service('profileService', profileService)
    .name