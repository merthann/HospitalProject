<template>
  <div>
    <h1>Doctor List</h1>
    <button @click="navigateToCreateDoctor" class="fancy-button create-button">Create Doctor</button>

    <!-- Display Policlinic Name - Doctor Name below the Create Doctor button -->
    <h2 style="text-align: center; color: #333; margin: 20px 0;">
      Policlinic Name - Doctor Name
    </h2>

    <ul v-if="doctors.length > 0">
      <li v-for="doctor in doctors" :key="doctor.id">
        {{ getPoliclinicName(doctor.policlinicId) }} - 
        {{ capitalize(doctor.title) }}. {{ capitalize(doctor.fname) }} {{ capitalize(doctor.lname) }}
        
        <button @click="navigateToUpdateDoctor(doctor.id)" class="fancy-button update-button">Update</button>
        <button @click="deleteDoctor(doctor.id)" class="fancy-button delete-button">Delete</button>
      </li>
    </ul>
    <p v-else>No doctors found</p>
  </div>
</template>


<script>
import axios from "axios";

export default {
  data() {
    return {
      doctors: [], // List of doctors fetched from the backend
      policlinics: {} // Map policlinicId -> policlinicName
    };
  },
  created() {
    this.fetchDoctors();
    this.fetchPoliclinics();
  },
  methods: {
    // Fetch doctors from backend
    fetchDoctors() {
      axios
        .get("http://localhost:8080/doctor")
        .then((response) => {
          this.doctors = response.data;
          console.log("Doctors fetched:", this.doctors);
        })
        .catch((error) => {
          console.error("Error fetching doctors:", error);
        });
    },
    // Fetch policlinics and map them
    fetchPoliclinics() {
      axios
        .get("http://localhost:8080/policlinic")
        .then((response) => {
          response.data.forEach((policlinic) => {
            this.policlinics[policlinic.id] = policlinic.name;
          });
          console.log("Policlinics fetched:", this.policlinics);
        })
        .catch((error) => {
          console.error("Error fetching policlinics:", error);
        });
    },
    // Retrieve Policlinic Name by ID
    getPoliclinicName(policlinicId) {
      return this.policlinics[policlinicId] || "Unknown Policlinic";
    },
    capitalize(str) {
      return str.charAt(0).toUpperCase() + str.slice(1);
    },
    navigateToUpdateDoctor(id) {
      this.$router.push({ name: "DoctorUpdate", params: { id: id.toString() } });
    },
    deleteDoctor(id) {
      axios.delete(`http://localhost:8080/doctor/${id}`).then(() => {
        this.doctors = this.doctors.filter((doctor) => doctor.id !== id);
        console.log(`Doctor with ID ${id} deleted.`);
      });
    },
    navigateToCreateDoctor() {
      this.$router.push({ name: "CreateDoctor" });
    }
  }
};
</script>

<style>
/* Align the heading with the list items */
.aligned-heading {
  margin: 20px auto;
  text-align: left;
  width: 50%; /* Match the width of the list */
  font-size: 18px;
  font-weight: bold;
}
/* Styling remains the same as provided */
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

.add-button {
  background-color: #4CAF50;
}

.add-button:hover {
  background-color: #45a049;
}
</style>
