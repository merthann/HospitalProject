<template>
  <div>
    <h1>Create Medicine</h1>
    <form @submit.prevent="createMedicine">
      <div>
        <label for="name">Name:</label>
        <input type="text" v-model="medicine.name" id="name" required />
      </div>
      <div>
        <label for="description">Description:</label>
        <input type="text" v-model="medicine.description" id="description" required />
      </div>
      <div>
        <label for="category">Category:</label>
        <select v-model="medicine.categoryId" id="category" required>
          <option v-for="category in categories" :key="category.id" :value="category.id">
            {{ category.name }}
          </option>
        </select>
      </div>
      <button type="submit" class="fancy-button add-button">Create Medicine</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      medicine: {
        name: "",
        description: "",
        categoryId: null, // This holds the selected category ID
      },
      categories: [], // Holds the list of categories fetched from the backend
    };
  },
  created() {
    this.fetchCategories();
  },
  methods: {
    // Fetch categories from the backend
    fetchCategories() {
      axios
        .get("http://localhost:8080/category") // Update the endpoint if necessary
        .then((response) => {
          this.categories = response.data; // Assuming the backend returns an array of categories
          console.log("Fetched categories:", this.categories);
        })
        .catch((error) => {
          console.error("Error fetching categories:", error);
        });
    },
    // Create a new medicine
    createMedicine() {
  axios.post('http://localhost:8080/medicine', {
    ...this.medicine,
    category: { id: this.medicine.category } // Wrap the selected category ID into an object
  })
  .then(response => {
    console.log('Medicine created:', response.data);
    this.$router.push({ name: 'MedicineList' });
  })
  .catch(error => {
    console.error('Error creating medicine:', error);
  });
}
,
  },
};
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  margin: 20px auto;
  width: 50%;
}
div {
  margin-bottom: 10px;
}
label {
  margin-bottom: 5px;
  font-weight: bold;
}
input,
select {
  padding: 8px;
  width: 100%;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.fancy-button {
  padding: 10px;
  background-color: #008cba;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}
.fancy-button:hover {
  background-color: #005f8a;
}
.add-button {
  align-self: flex-start;
}
</style>
