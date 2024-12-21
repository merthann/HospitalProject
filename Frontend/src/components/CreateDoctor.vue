<template>
  <div>
    <h1>Create Doctor</h1>
    <form @submit.prevent="createDoctor">
      <div>
        <label for="title">Title:</label>
        <input type="text" v-model="doctor.title" id="title" required />
      </div>

      <div>
        <label for="fname">First Name:</label>
        <input type="text" v-model="doctor.fname" id="fname" required />
      </div>

      <div>
        <label for="lname">Last Name:</label>
        <input type="text" v-model="doctor.lname" id="lname" required />
      </div>

      <div>
        <label for="policlinic">Policlinic:</label>
        <select v-model="doctor.policlinicId" id="policlinic" required>
          <option v-for="policlinic in policlinics" :key="policlinic.id" :value="policlinic.id">
            {{ policlinic.name }}
          </option>
        </select>
      </div>

      <div>
        <label for="assistant">Assistant:</label>
        <select v-model="doctor.assistantId" id="assistant" required>
          <option v-for="assistant in assistants" :key="assistant.id" :value="assistant.id">
            {{ assistant.fname }} {{ assistant.lname }}
          </option>
        </select>
      </div>

      <button type="submit" class="fancy-button add-button">Create Doctor</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      doctor: {
        title: "",
        fname: "",
        lname: "",
        policlinicId: "",
        assistantId: "" // Fixed naming
      },
      policlinics: [],
      assistants: [] // Fixed naming
    };
  },
  created() {
    this.fetchPoliclinics();
    this.fetchAssistants();
  },
  methods: {
    // Fetch policlinics from the backend
    fetchPoliclinics() {
      axios
        .get("http://localhost:8080/policlinic")
        .then((response) => {
          this.policlinics = response.data;
          console.log("Policlinics fetched:", this.policlinics);
        })
        .catch((error) => {
          console.error("Error fetching policlinics:", error);
        });
    },

    // Fetch assistants from the backend
    fetchAssistants() {
      axios
        .get("http://localhost:8080/assistant") // Corrected endpoint
        .then((response) => {
          this.assistants = response.data;
          console.log("Assistants fetched:", this.assistants);
        })
        .catch((error) => {
          console.error("Error fetching assistants:", error);
        });
    },

    // Create a new doctor
    createDoctor() {
      axios
        .post("http://localhost:8080/doctor", this.doctor)
        .then((response) => {
          console.log("Doctor created successfully:", response.data);
          this.$router.push({ name: "DoctorList" });
        })
        .catch((error) => {
          console.error("Error creating doctor:", error);
        });
    }
  }
};
</script>

<style scoped>
/* Your CSS styles */
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
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 10px 20px;
  text-align: center;
  font-size: 16px;
  border-radius: 12px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.3s ease;
}
.fancy-button:hover {
  background-color: #45a049;
  transform: scale(1.05);
}
.add-button {
  background-color: #008CBA;
}
.add-button:hover {
  background-color: #007bb5;
}
</style>
