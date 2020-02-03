<script>
import Vue from 'vue'
import applicationConstants from '@/constants/ApplicationConstants'

export default {
  name: 'DepartmentService',
  methods: {
    getAll: function () {
      return Vue.http.get(applicationConstants.serverBaseUrl + '/api/department')
    },
    getAllLikeMap: function () {
      return Vue.http.get(applicationConstants.serverBaseUrl + '/api/department/map')
    }
  }
}
</script>
