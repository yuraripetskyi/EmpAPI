<script>
import Vue from 'vue'
import applicationConstants from '@/constants/ApplicationConstants'

export default {
  name: 'EmployeeService',
  methods: {
    getAll: function () {
      return Vue.http.get(applicationConstants.serverBaseUrl + '/api/employee')
    },
    create: function (employee) {
      return Vue.http.post(applicationConstants.serverBaseUrl + '/api/employee', employee)
    },
    update: function (employee) {
      return Vue.http.put(applicationConstants.serverBaseUrl + '/api/employee', employee)
    },
    delete: function (id) {
      return Vue.http.delete(applicationConstants.serverBaseUrl + '/api/employee/' + id)
    }
  }
}
</script>
