import flightMap from './map/map.module'
import startPage from './start/start.module.js'
import regPage from './registration/registration.module.js'
import profilePage from './profile/profile.module.js'
import loginPage from './login/login.module.js'

import apiUrl from './api.url'
import appComponent from './app.component.js'
import routesConfig from './routes.config.js'


export default
  angular
    .module('flight', [
      'ngAria',
      'ngAnimate',
      'ngMaterial',
      'ngMessages',
      'ui.router',

      flightMap,
       startPage,
       regPage,
       profilePage,
       loginPage
      
    ])
    .constant('apiUrl', apiUrl)
    .component('flightApp', appComponent)   
    .config(routesConfig)
    .name
