<template>
  <div>
    <h1>Update Patient</h1>
    <form @submit.prevent="submitUpdate">
      <!-- Patient First Name -->
      <div>
        <label for="fname">First Name:</label>
        <input type="text" v-model="patient.fname" id="fname" required />
      </div>

      <!-- Patient Last Name -->
      <div>
        <label for="lname">Last Name:</label>
        <input type="text" v-model="patient.lname" id="lname" required />
      </div>

      <!-- Submit Button -->
      <button type="submit" class="fancy-button update-button">Update</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: ['id'], // Prop to receive the patient ID
  data() {
    return {
      patient: {
        fname: '',
        lname: ''
      }
    };
  },
  created() {
    this.fetchPatient();
  },
  methods: {
    // Fetch patient details by ID
    fetchPatient() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error('No patient ID provided.');
        return;
      }
      axios
        .get(`http://localhost:8080/patient/${id}`)
        .then((response) => {
          this.patient = response.data;
          console.log('Fetched patient:', this.patient);
        })
        .catch((error) => {
          console.error('Error fetching patient:', error);
        });
    },

    // Submit the updated patient details
    submitUpdate() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error('No patient ID provided.');
        return;
      }

      axios
        .put(`http://localhost:8080/patient/${id}`, this.patient)
        .then(() => {
          console.log('Patient updated successfully.');
          this.$router.push('/PatientList'); // Redirect to PatientList
        })
        .catch((error) => {
          console.error('Error updating patient:', error);
        });
    }
  }
};
</script>

<style scoped>
/* Retained your styling */
body {
  background-color: lightblue;
}
h1 {
  color: white;
  text-align: center;
}
form {
  margin: 20px auto;
  width: 50%;
  text-align: center;
}
label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}
input,
select {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.fancy-button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 10px 20px;
  text-align: center;
  font-size: 16px;
  border-radius: 12px;
  transition: background-color 0.3s ease, transform 0.3s ease;
  cursor: pointer;
}
.fancy-button:hover {
  background-color: #45a049;
  transform: scale(1.05);
}
.add-button {
  background-color: #008cba;
}
.add-button:hover {
  background-color: #007bb5;
}
</style>
