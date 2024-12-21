<template>
  <div>
    <h1>Create Assistant</h1>
    <form @submit.prevent="createAssistant">
      <div>
        <label for="fname">First Name:</label>
        <input
          type="text"
          id="fname"
          v-model="assistant.fname"
          placeholder="Enter first name"
          required
        />
      </div>
      <div>
        <label for="lname">Last Name:</label>
        <input
          type="text"
          id="lname"
          v-model="assistant.lname"
          placeholder="Enter last name"
          required
        />
      </div>
      <div>
        <label for="workdays">Workdays:</label>
        <input
          type="text"
          id="workdays"
          v-model="assistant.workdays"
          placeholder="e.g., Monday, Tuesday"
          required
        />
      </div>
      <button type="submit" class="fancy-button add-button">Create</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      assistant: {
        fname: "",
        lname: "",
        workdays: ""
      }
    };
  },
  methods: {
    // Method to create a new assistant
    createAssistant() {
      // Input validation (optional)
      if (!this.assistant.fname || !this.assistant.lname || !this.assistant.workdays) {
        console.error("All fields are required.");
        alert("Please fill in all fields.");
        return;
      }

      // Post data to the backend
      axios
        .post("http://localhost:8080/assistant", this.assistant) // Fixed endpoint
        .then((response) => {
          console.log("Assistant created successfully:", response.data);
          this.$router.push({ name: "AssistantList" }); // Navigate to the assistant list
        })
        .catch((error) => {
          console.error("Error creating assistant:", error);
          alert("Failed to create assistant. Please check the backend.");
        });
    }
  }
};
</script>

<style scoped>
/* Retained existing styles */
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
input {
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
  transition: background-color 0.3s ease, transform 0.3s ease;
  cursor: pointer;
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
