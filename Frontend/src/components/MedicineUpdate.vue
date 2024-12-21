<template>
  <div>
    <h1>Update Medicine</h1>
    <form @submit.prevent="submitUpdate">
      <!-- Medicine Name -->
      <div>
        <label for="name">Name:</label>
        <input type="text" v-model="medicine.name" id="name" required />
      </div>

      <!-- Medicine Description -->
      <div>
        <label for="description">Description:</label>
        <input type="text" v-model="medicine.description" id="description" required />
      </div>

      <!-- Category Dropdown -->
      <div>
        <label for="category">Category:</label>
        <select v-model="medicine.category.id" id="category" required>
          <option v-for="category in categories" :key="category.id" :value="category.id">
            {{ category.name }}
          </option>
        </select>
      </div>

      <!-- Submit Button -->
      <button type="submit" class="fancy-button add-button">Update</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: ["id"], // Prop to receive the medicine ID
  data() {
    return {
      medicine: {
        id: null,
        name: "",
        description: "",
        category: {
          id: null,
          name: ""
        }
      },
      categories: [] // List of categories fetched from the backend
    };
  },
  created() {
    this.fetchMedicine();
    this.fetchCategories();
  },
  methods: {
    // Fetch medicine details by ID
    fetchMedicine() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error("No medicine ID provided.");
        return;
      }
      axios
        .get(`http://localhost:8080/medicine/${id}`)
        .then((response) => {
          this.medicine = response.data; // Directly bind response to `medicine`
          console.log("Fetched medicine:", this.medicine);
        })
        .catch((error) => {
          console.error("Error fetching medicine:", error);
        });
    },

    // Fetch categories dynamically from backend
    fetchCategories() {
      axios
        .get("http://localhost:8080/category")
        .then((response) => {
          this.categories = response.data;
          console.log("Fetched categories:", this.categories);
        })
        .catch((error) => {
          console.error("Error fetching categories:", error);
        });
    },

    // Submit the updated medicine details
    submitUpdate() {
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error("No medicine ID provided.");
        return;
      }

      // Prepare medicine object for submission
      const updatedMedicine = {
        id: this.medicine.id,
        name: this.medicine.name,
        description: this.medicine.description,
        category: {
          id: this.medicine.category.id
        }
      };

      axios
        .put(`http://localhost:8080/medicine/${id}`, updatedMedicine)
        .then(() => {
          console.log("Medicine updated successfully.");
          this.$router.push("/MedicineList"); // Redirect to MedicineList
        })
        .catch((error) => {
          console.error("Error updating medicine:", error);
        });
    }
  }
};
</script>

<style scoped>
/* Styling remains unchanged */
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
