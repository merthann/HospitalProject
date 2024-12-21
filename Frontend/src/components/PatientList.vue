<template>
  <div>
    <h1>Patient List</h1>
    <button @click="navigateToCreatePatient" class="fancy-button create-button">Create Patient</button>
    <h2 style="text-align: center; color: #333; margin: 20px 0;">
      First Name - Last Name
    </h2>
    <ul v-if="patients.length > 0">
      <li v-for="patient in patients" :key="patient.id">
        {{ capitalize(patient.fname) }} {{ capitalize(patient.lname) }}
        <button @click="navigateToUpdatePatient(patient.id)" class="fancy-button update-button">Update</button>
        <button @click="deletePatient(patient.id)" class="fancy-button delete-button">Delete</button>
      </li>
    </ul>
    <p v-else>No patients found</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      patients: [],
    };
  },
  created() {
    this.fetchPatients();
  },
  methods: {
    fetchPatients() {
      axios
        .get("http://localhost:8080/patient")
        .then((response) => {
          this.patients = response.data;
        })
        .catch((error) => {
          console.error("Error fetching patients:", error);
        });
    },
    deletePatient(id) {
      axios
        .delete(`http://localhost:8080/patient/${id}`)
        .then(() => {
          this.patients = this.patients.filter((patient) => patient.id !== id);
        })
        .catch((error) => {
          console.error("Error deleting patient:", error);
        });
    },
    navigateToCreatePatient() {
      this.$router.push({ name: "CreatePatient" });
    },
    navigateToUpdatePatient(id) {
      this.$router.push({ name: "PatientUpdate", params: { id } });
    },
    capitalize(str) {
      return str.charAt(0).toUpperCase() + str.slice(1);
    },
  },
};
</script>

<style>
body {
  background-color: lightblue;
}
h1 {
  color: white;
  text-align: center;
}
ul {
  text-align: center;
}
li {
  text-align: center;
  list-style-type: none;
}
div {
  text-align: center;
}
.fancy-button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.fancy-button:hover {
  background-color: #45a049;
  transform: scale(1.05);
}

.create-button {
  background-color: #008CBA;
}

.create-button:hover {
  background-color: #007bb5;
}

.update-button {
  background-color: #FFA500;
}

.update-button:hover {
  background-color: #e59400;
}

.delete-button {
  background-color: #f44336;
}

.delete-button:hover {
  background-color: #da190b;
}
</style>
