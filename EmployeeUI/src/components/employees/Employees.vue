<style src="./employees.css"></style>

<template src="./employees.html"></template>

<script>
import EmployeeService from '../../services/EmployeeService'
import DepartmentService from '../../services/DepartmentService'
import Lodash from 'lodash'

export default {
  name: 'Employees',
  data () {
    return {
      employees: {},
      filteredEmployees: {},
      departments: [],
      departmentsMap: {},
      searchEmp: '',
      editEmployeeForm: {
        id: '',
        name: '',
        active: false,
        departmentId: ''
      },
      type: 'Create'
    }
  },
  computed: {
    resultQuery () {
      if (this.searchEmp) {
        return this.employees.filter((item) => {
          return this.searchEmp.toLowerCase().split(' ').every(v => item.name.toLowerCase().startsWith(v))
        })
      } else {
        return this.employees
      }
    }
  },
  methods: {
    editEmployee: function (id, name, active, depId) {
      this.editEmployeeForm.id = id
      this.editEmployeeForm.name = name
      this.editEmployeeForm.active = active
      this.editEmployeeForm.departmentId = depId
      console.log('edit employee ' + id)
      this.type = 'Edit'
    },
    confirmEdition () {
      let employee = {
        name: this.editEmployeeForm.name,
        active: this.editEmployeeForm.active,
        depId: this.editEmployeeForm.departmentId
      }
      if (this.type === 'Create') {
        EmployeeService.methods.create(employee).then((response) => {
          this.employees.push(response.data)
        }, () => {})
        this.clearEditForm()
      } else if (this.type === 'Edit') {
        let id = this.editEmployeeForm.id
        employee['id'] = id
        EmployeeService.methods.update(employee).then((response) => {
          this.employees.forEach(emp => {
            if (emp.id === id) {
              console.log(response.data)
              emp.name = response.data.name
              emp.active = response.data.active
              emp.depId = response.data.depId
            }
          })
        }, () => {})
        this.clearEditForm()
      }
    },
    deleteEmployee: function (employee) {
      EmployeeService.methods.delete(employee.id).then((response) => {
        this.employees = Lodash.filter(this.employees, emp => {
          return emp !== employee
        })
      }, () => {})
    },
    loadDepartments () {
      DepartmentService.methods.getAll().then((response) => {
        this.departments = response.data
      }, () => {})
      DepartmentService.methods.getAllLikeMap().then((response) => {
        this.departmentsMap = response.data
      }, () => {})
    },
    loadEmployees () {
      EmployeeService.methods.getAll().then((response) => {
        this.employees = response.data
        this.filteredEmployees = response.data
      }, () => {})
    },
    clearEditForm () {
      this.editEmployeeForm.id = ''
      this.editEmployeeForm.name = ''
      this.editEmployeeForm.active = false
      this.editEmployeeForm.departmentId = '1'
    }
  },
  created () {
    this.loadDepartments()
    this.loadEmployees()
  }
}
</script>
