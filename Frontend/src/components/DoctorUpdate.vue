<template>
  <div>
    <h1>Update Doctor</h1>
    <form @submit.prevent="submitUpdate">
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
        <label for="policlinicId">Policlinic:</label>
        <select v-model.number="doctor.policlinicId" id="policlinicId" required>
          <option v-for="policlinic in policlinics" :key="policlinic.id" :value="policlinic.id">
            {{ policlinic.name }}
          </option>
        </select>
      </div>

      <div>
        <label for="assistantId">Assistant:</label>
        <select v-model.number="doctor.assistantId" id="assistantId" required>
          <option v-for="assistant in assistants" :key="assistant.id" :value="assistant.id">
            {{ assistant.fname }} {{ assistant.lname }}
          </option>
        </select>
      </div>

      <button type="submit" class="fancy-button add-button">Update</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: ["id"],
  data() {
    return {
      doctor: {
        title: "",
        fname: "",
        lname: "",
        policlinicId: null,
        assistantId: null
      },
      policlinics: [],
      assistants: [] // Renamed for consistency
    };
  },
  created() {
    this.fetchDoctor();
    this.fetchPoliclinics();
    this.fetchAssistants();
  },
  methods: {
    // Fetch doctor details
    fetchDoctor() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error("No doctor ID provided");
        return;
      }
      axios
        .get(`http://localhost:8080/doctor/${id}`)
        .then((response) => {
          this.doctor = response.data;
          console.log("Doctor fetched:", this.doctor);
        })
        .catch((error) => {
          console.error("Error fetching doctor:", error);
        });
    },

    // Fetch policlinics
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

    // Fetch assistants
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

    // Submit updated doctor details
    submitUpdate() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error("No doctor ID provided");
        return;
      }

      axios
        .put(`http://localhost:8080/doctor/${id}`, this.doctor)
        .then(() => {
          console.log("Doctor updated successfully");
          this.$router.push("/DoctorList"); // Redirect to Doctor List
        })
        .catch((error) => {
          console.error("Error updating doctor:", error);
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
