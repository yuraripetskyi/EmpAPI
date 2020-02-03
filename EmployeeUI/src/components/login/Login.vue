<style src="./login.css"></style>

<template src="./login.html"></template>

<script>
import UserService from '@/services/UserService'
import VueCookies from 'vue-cookies'
import Router from '@/router'

export default {

  name: 'Login',
  data () {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    login: function () {
      UserService.methods.login(this.getAuthBody()).then((response) => {
        let authToken = response.data.jwt
        VueCookies.set('auth-jwt', authToken, 86400)
        Router.go({name: 'Employees'})
        this.clearUserCredentials()
      }, (response) => {
        this.clearUserCredentials()
        this.$alert('Something went wrong try again')
      })
    },
    register: function () {
      UserService.methods.register(this.getAuthBody()).then((response) => {
        let authToken = response.data.jwt
        VueCookies.set('auth-jwt', authToken, 86400)
        Router.go({name: 'Employees'})
        this.clearUserCredentials()
      }, (response) => {
        this.clearUserCredentials()
        this.$alert('Something went wrong!! Try again.')
      })
    },
    getAuthBody: function () {
      let authBody = {
        'username': this.username,
        'password': this.password
      }
      return authBody
    },
    clearUserCredentials () {
      this.username = ''
      this.password = ''
    },
    isDisabled: function () {
      if (this.username === '' || this.password === '') {
        return true
      } else {
        return false
      }
    }
  }

}

</script>
