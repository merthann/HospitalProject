<template>
  <div>
    <h1>Update Assistant</h1>
    <form @submit.prevent="submitUpdate">
      <div>
        <label for="fname">First Name:</label>
        <input type="text" v-model="assistant.fname" id="fname" required />
      </div>

      <div>
        <label for="lname">Last Name:</label>
        <input type="text" v-model="assistant.lname" id="lname" required />
      </div>

      <div>
        <label for="workdays">Workdays:</label>
        <input type="text" v-model="assistant.workdays" id="workdays" required />
      </div>

      <button type="submit" class="fancy-button add-button">Update</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: ['id'], // The assistant ID passed as a prop
  data() {
    return {
      assistant: {
        fname: '',
        lname: '',
        workdays: ''
      }
    };
  },
  created() {
    this.fetchAssistant();
  },
  methods: {
    // Fetch assistant details by ID
    fetchAssistant() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error('No assistant ID provided');
        return;
      }

      axios
        .get(`http://localhost:8080/assistant/${id}`)
        .then((response) => {
          this.assistant = response.data;
          console.log('Fetched assistant:', this.assistant);
        })
        .catch((error) => {
          console.error('Error fetching assistant:', error);
        });
    },

    // Submit the updated assistant data
    submitUpdate() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error('No assistant ID provided');
        return;
      }

      axios
        .put(`http://localhost:8080/assistant/${id}`, this.assistant)
        .then(() => {
          console.log('Assistant updated successfully');
          this.$router.push('/AssistantList'); // Redirect to Assistant List
        })
        .catch((error) => {
          console.error('Error updating assistant:', error);
        });
    }
  }
};
</script>

<style scoped>
/* Retain your existing styling */
body {
  background-color: lightblue;
}

h1 {
  color: white;
  text-align: center;
}

form {
  text-align: center;
  margin: 20px auto;
  width: 50%;
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
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 10px;
  cursor: pointer;
  border-radius: 12px;
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
