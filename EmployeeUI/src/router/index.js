import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login/Login.vue'
import Employees from '@/components/employees/Employees.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/employees',
      name: 'Employees',
      component: Employees
    }
  ]
})
