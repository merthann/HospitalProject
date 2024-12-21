<template>
    <div>
      <h1>Create Policlinic</h1>
      <form @submit.prevent="createPoliclinic">
        <div>
          <label for="name">Name:</label>
          <input type="text" v-model="policlinic.name" id="name" required />
        </div>
        <div>
          <label for="floor">Floor:</label>
          <input type="number" v-model="policlinic.floor" id="floor" required />
        </div>
        <button type="submit" class="fancy-button add-button">Create Policlinic</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        policlinic: {
          name: '',
          floor: ''
        }
      };
    },
    methods: {
      createPoliclinic() {
        axios.post('http://localhost:8080/policlinic', this.policlinic)
          .then(response => {
            console.log('Policlinic created:', response.data);
            this.$router.push({ name: 'PoliclinicList' });
          })
          .catch(error => {
            console.error('Error creating policlinic:', error);
          });
      }
    }
  };
  </script>
