<script>
import Vue from 'vue'
import applicationConstants from '@/constants/ApplicationConstants'

export default {
  name: 'UserService',
  methods: {
    login: function (userCredentials) {
      return Vue.http.post(applicationConstants.serverBaseUrl + '/api/user/login', userCredentials)
    },
    register: function (userCredentials) {
      return Vue.http.post(applicationConstants.serverBaseUrl + '/api/user/register', userCredentials)
    }
  }
}
</script>
