// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VueResource from 'vue-resource'
import VueCookies from 'vue-cookies'
import VueSimpleAlert from 'vue-simple-alert'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

Vue.config.productionTip = false
Vue.use(VueResource)
Vue.use(VueSimpleAlert)
Vue.use(Vuetify)

const opts = {}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  vuetify: new Vuetify(opts),
  components: { App },
  watch: {
    '$route' () {
      if (VueCookies.get('auth-jwt') == null || VueCookies.get('auth-jwt').length === 0) {
        this.$router.replace({name: 'Login'})
      }
    }
  },
  created () {
    const authToken = VueCookies.get('auth-jwt')
    if (authToken) {
      Vue.http.headers.common['auth-jwt'] = 'Bearer ' + authToken
      this.$router.push({name: 'Employees'})
    } else {
      this.$router.push({name: 'Login'})
    }
  },
  template: '<App/>'
})
