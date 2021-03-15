import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login'
import Main from '../views/Main'
import Register from "../views/Register";
import Forget from "../views/Forget";
import Personal from "../views/Personal";

Vue.use(Router)


export default new Router({
  mode:'history',
  routes: [
    {
      path:'/',
      component: Login,
    },
    {
      path: '/main',
      component: Main
    },
    {
      path:'/register',
      component: Register
    },
    {
      path: '/forget',
      component: Forget
    },
    {
      path: '/personal',
      component: Personal
    }
  ]
})
