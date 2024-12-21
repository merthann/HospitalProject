<template>
  <div>
    <h1>Assistant List</h1>
    <button @click="navigateToCreateAssistant" class="fancy-button create-button">Create Assistant</button>
    <h2 style="text-align: center; color: #333; margin: 20px 0;">
      First Name - Last Name - Workdays
    </h2>
    <ul v-if="assistants.length > 0">
      <li v-for="assistant in assistants" :key="assistant.id">
        {{ capitalize(assistant.fname) }} {{ capitalize(assistant.lname) }} - {{ assistant.workdays }}
        <button @click="navigateToUpdateAssistant(assistant.id)" class="fancy-button update-button">Update</button>
        <button @click="deleteAssistant(assistant.id)" class="fancy-button delete-button">Delete</button>
      </li>
    </ul>
    <p v-else>No assistants found</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      assistants: [] // Renamed from 'asistans' to 'assistants'
    };
  },
  created() {
    this.fetchAssistants();
  },
  methods: {
    // Fetch the list of assistants from the backend
    fetchAssistants() {
      axios.get('http://localhost:8080/assistant') // Correct endpoint
        .then(response => {
          this.assistants = response.data;
          this.sortAssistantsById();
          console.log('Fetched assistants:', this.assistants); 
        })
        .catch(error => {
          console.error('Error fetching assistants:', error);
        });
    },
    // Sort assistants by ID
    sortAssistantsById() {
      this.assistants.sort((a, b) => a.id - b.id);
    },
    // Capitalize the first letter of a string
    capitalize(str) {
      return str.charAt(0).toUpperCase() + str.slice(1);
    },
    // Navigate to the assistant update page
    navigateToUpdateAssistant(id) {
      console.log('Attempting to navigate to update with ID:', id);
      if (id) {
        this.$router.push({ name: 'AssistantUpdate', params: { id: id.toString() } })
          .then(() => console.log('Navigation successful'))
          .catch(err => console.error('Navigation failed:', err));
      } else {
        console.error('Assistant ID is undefined');
      }
    },
    // Delete an assistant
    deleteAssistant(id) {
      axios.delete(`http://localhost:8080/assistant/${id}`) // Correct endpoint
        .then(() => {
          this.assistants = this.assistants.filter(assistant => assistant.id !== id);
          console.log(`Deleted assistant with ID: ${id}`);
        })
        .catch(error => {
          console.error('Error deleting assistant:', error);
        });
    },
    // Navigate to the create assistant page
    navigateToCreateAssistant() {
      this.$router.push({ name: 'CreateAssistant' });
    }
  }
};
</script>
<style>
/* Styling remains the same */
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
  background-color: #4CAF50; /* Green */
}

.create-button:hover {
  background-color: #45a049;
}

.update-button {
  background-color: #FFA500; /* Yellow */
}

.update-button:hover {
  background-color: #e59400;
}

.delete-button {
  background-color: #f44336; /* Red */
}

.delete-button:hover {
  background-color: #da190b;
}
</style>
